package com.springboot.member.service;

import com.springboot.auth.utils.AuthorityUtils;
import com.springboot.challenge.entity.ChallengeCategory;
import com.springboot.challenge.repository.ChallengeCategoryRepository;
import com.springboot.challenge.repository.ChallengeRepository;
import com.springboot.collectioncamera.entity.CameraImage;
import com.springboot.collectioncamera.entity.CollectionCamera;
import com.springboot.collectioncamera.repository.CameraImageRepository;
import com.springboot.collectioncamera.repository.CollectionCameraRepository;
import com.springboot.collectioncamera.service.CollectionCameraService;
import com.springboot.exception.BusinessLogicException;
import com.springboot.exception.ExceptionCode;
import com.springboot.member.entity.Member;
import com.springboot.member.entity.MemberChallenge;
import com.springboot.member.entity.MemberProfileImage;
import com.springboot.member.entity.MemberTitle;
import com.springboot.member.repository.MemberRepository;
import com.springboot.profile.entity.ProfileImage;
import com.springboot.profile.repository.ProfileImageRepository;
import com.springboot.title.entity.Title;
import com.springboot.title.repository.TitleRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityUtils authorityUtils;
    private final RedisTemplate<String, String> redisTemplate;
    private final ChallengeRepository challengeRepository;
    private final ProfileImageRepository profileImageRepository;
    private final TitleRepository titleRepository;
    private final CameraImageRepository cameraImageRepository;
    private final CollectionCameraRepository collectionCameraRepository;
    private final ProfileImageRepository imageRepository;
    private final ChallengeCategoryRepository challengeCategoryRepository;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, AuthorityUtils authorityUtils, RedisTemplate<String, String> redisTemplate, ChallengeRepository challengeRepository, ProfileImageRepository profileImageRepository, TitleRepository titleRepository, CameraImageRepository cameraImageRepository, CollectionCameraRepository collectionCameraRepository, ProfileImageRepository imageRepository, ChallengeCategoryRepository challengeCategoryRepository) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityUtils = authorityUtils;
        this.redisTemplate = redisTemplate;
        this.challengeRepository = challengeRepository;
        this.profileImageRepository = profileImageRepository;
        this.titleRepository = titleRepository;
        this.cameraImageRepository = cameraImageRepository;
        this.collectionCameraRepository = collectionCameraRepository;
        this.imageRepository = imageRepository;
        this.challengeCategoryRepository = challengeCategoryRepository;
    }

    public Member createMember(Member member, long profileImageId) {
        // 중복 아이디 여부 확인
        verifyExistsLoginId(member.getLoginId());

        // 중복 닉네임 여부 확인
        verifyExistsName(member.getNickName());

        // 중복 이메일 여부 확인
        verifyExistsEmail(member.getEmail());

        // 이메일 검증 완료 했는지 확인
        verifyEmailIsVerified(member.getEmail());

        // 중복 핸드폰 번호 여부 확인
        verifyExistsPhoneNumber(member.getPhoneNumber());

        member.setPassword(passwordEncoder.encode(member.getPassword()));

        // 회원 기본 셋팅(이미지)
        MemberProfileImage memberProfileImage = new MemberProfileImage();
        ProfileImage profileImage = new ProfileImage();
        profileImage.setProfileImageId(1L);
        memberProfileImage.setProfileImage(profileImage);

        ProfileImage profileImageTwo = new ProfileImage();
        profileImageTwo.setProfileImageId(2L);
        memberProfileImage.setProfileImage(profileImageTwo);

        member.setMemberProfileImage(memberProfileImage);

        // 회원 기본 도전과제 셋팅
        long challengeSize = challengeRepository.count();

        for (int i = 1; i <= challengeSize; i++) {
            MemberChallenge memberChallenge = new MemberChallenge();
            ChallengeCategory challengeCategory = new ChallengeCategory();
            challengeCategory.setChallengeCategoryid(i);
            memberChallenge.setChallengeCategory(challengeCategory);
            memberChallenge.setMember(member);

            // 🔥 초보자 도전과제는 기본 세팅 (level 1, count 0)
            ChallengeCategory foundCategory = challengeCategoryRepository.findById((long) i)
                    .orElseThrow(() -> new BusinessLogicException(ExceptionCode.CHALLENGE_CATEGORY_NOT_FOUND));

            if ("초보자".equals(foundCategory.getCategory())) {
                memberChallenge.setCurrentLevel(0);
                memberChallenge.setCurrentCount(0);
            }

            member.getMemberChallenges().add(memberChallenge);
        }

        List<String> roles = authorityUtils.createAuthorities(member.getEmail());

        member.setRoles(roles);

        Optional<ProfileImage> findProfileImage = profileImageRepository.findById(profileImageId);
        if (findProfileImage.isPresent()) {
            member.setProfile(findProfileImage.get().getImagePath());
        } else {
            throw new BusinessLogicException(ExceptionCode.PROFILE_IMAGE_NOT_FOUND);
        }

        List<Title> titles = titleRepository.findAll();

        // 기본 타이틀 셋팅
        titles.stream().filter(title -> title.getName().equals("초보자"))
                .findFirst()
                .ifPresent(title -> {
                    MemberTitle memberTitle = new MemberTitle();
                    memberTitle.setTitle(title);
                    memberTitle.setMember(member);
                    member.getMemberTitles().add(memberTitle);
                    member.setMemberTitle(memberTitle);
                    member.setActiveTitleId(memberTitle.getTitle().getTitleId());
                });

        // Member 저장
        Member savedMember = memberRepository.save(member);

        // 기본 도감 카메라 세팅 (직접)
        CameraImage defaultCameraImage = cameraImageRepository.findById(1L)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.CAMERA_IMAGE_NOT_FOUND));

        CollectionCamera defaultCollection = new CollectionCamera();
        defaultCollection.setCustomCategoryName("도감 예시");
        defaultCollection.setCameraImage(defaultCameraImage);
        defaultCollection.setMember(savedMember); // 연관관계 설정
        defaultCollection.setCollectionStatus(CollectionCamera.CollectionStatus.PUBLIC);

        collectionCameraRepository.save(defaultCollection);

        return savedMember;
    }

    public Member updateMember(Member member, long memberId) {
        // 1. 로그인한 유저의 기존 정보 가져옴
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        // 2. 닉네임이 null 아니고 수정하려는 값과 다른 경우에만
        if (member.getNickName() != null && !member.getNickName().equals(findMember.getNickName())) {
            // 2-1. 중복 닉네임 검증
            verifyExistsName(member.getNickName());

            // 2-2. 닉네임 유효성 검사 (길이, 공백 등은 Controller 단 @Valid로 하는 게 일반적이긴 함)
            findMember.setNickName(member.getNickName());
        }

        Optional.ofNullable(member.getActiveImageId())
                .ifPresent(findMember::setActiveImageId);
        // 엔티티의 이미지 주소도 변경 매핑, 메서드 분리 필요
        String proFileImagePath = member.getMemberProfileImages()
                .stream()
                .filter(memberProfileImage -> memberProfileImage.getProfileImage().getProfileImageId() == member.getActiveImageId())
                .map(profileImage -> profileImage.getProfileImage().getImagePath())
                .findFirst().orElse(null);
        member.setProfile(proFileImagePath);

        Optional.ofNullable(member.getActiveTitleId())
                .ifPresent(findMember::setActiveTitleId);
        // 타이틀을 보유하지 않는다면 예외 발생, 분리 필요
        member.getMemberTitles()
                .stream()
                .filter(memberTitle -> memberTitle.getTitle().getTitleId() == member.getActiveTitleId())
                .findFirst().orElseThrow(() -> new BusinessLogicException(ExceptionCode.TITLE_NOT_FOUND));
        Optional.ofNullable(member.getPhoneNumber())
                .ifPresent(findMember::setPhoneNumber);
        // 3. 저장
        return memberRepository.save(findMember);
    }

    // 회원 단일 조회
    public Member findMember(long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
    }

    // 회원 탈퇴 처리 (상태값 변경)
    public void deleteMember(Member member) {
        // 1. 이메일 인증 여부 확인 (Redis에 :verified 존재 확인)
        verifyEmailIsVerified(member.getEmail());

        // 2. 사용자 정보 가져오기
        Member findMember = memberRepository.findByEmail(member.getEmail())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        // 3. 비밀번호 일치 여부 확인
        if (!passwordEncoder.matches(member.getPassword(), findMember.getPassword())) {
            throw new BusinessLogicException(ExceptionCode.PASSWORD_MISMATCH);
        }
        // 4. 상태값 변경 → 탈퇴 처리
        findMember.setMemberStatus(Member.MemberStatus.MEMBER_QUIT);
        memberRepository.save(findMember);
    }

    // 아이디 찾기 (닉네임과 전화번호로 조회, 이메일 인증 필수)
    public String findLoginId(String email, String phoneNumber) {
        // 이메일 인증 여부 확인
        verifyEmailIsVerified(email);

        // 이메일 + 전화번호로 회원 검색
        Member member = memberRepository.findByEmailAndPhoneNumber(email, phoneNumber)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        return member.getLoginId(); // 사용자 ID 반환
    }

    // 비밀번호 재설정 (아이디 + 이메일로 회원 찾고, 비밀번호 변경)
    public void resetPassword(String loginId, String email, String newPassword) {
        // 이메일 인증 여부 확인
        verifyEmailIsVerified(email);

        // 회원 검색
        Member member = memberRepository.findByLoginIdAndEmail(loginId, email)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        // 기존 비밀번호와 새 비밀번호가 같은 경우
        if (passwordEncoder.matches(newPassword, member.getPassword())) {
            throw new BusinessLogicException(ExceptionCode.PASSWORD_DUPLICATED);
        }

        // 새로운 비밀번호로 인코딩 후 변경
        member.setPassword(passwordEncoder.encode(newPassword));

        // 저장
        memberRepository.save(member);
    }

    // 이메일 중복 여부 확인 메서드
    public void verifyExistsEmail(String email){
        Optional<Member> member = memberRepository.findByEmail(email);

        if (member.isPresent())
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
    }

    // 아이디 중복 여부 확인 메서드
    public void verifyExistsLoginId(String loginId){
        Optional<Member> member = memberRepository.findByLoginId(loginId);

        if (member.isPresent())
            throw new BusinessLogicException(ExceptionCode.DUPLICATE_LOGIN_ID);
    }

    // 닉네임 중복 여부 확인 메서드
    public void verifyExistsName(String name){
        Optional<Member> member = memberRepository.findByNickName(name);

        if(member.isPresent())
            throw new BusinessLogicException(ExceptionCode.MEMBER_NAME_EXISTS);
    }

    // 폰번호 중복 여부 확인 메서드
    public void verifyExistsPhoneNumber(String phoneNumber){
        Optional<Member> member = memberRepository.findByPhoneNumber(phoneNumber);

        if(member.isPresent())
            throw new BusinessLogicException(ExceptionCode.MEMBER_PHONE_NUMBER_EXISTS);
    }

    // 이메일 인증이 되었는지 검증 메서드
    private void verifyEmailIsVerified(String email) {
        String key = email + ":verified";
        String verified = redisTemplate.opsForValue().get(key);
        if (!"true".equals(verified)) {
            throw new BusinessLogicException(ExceptionCode.EMAIL_NOT_VERIFIED);
        }
    }

    private void setChallenge(Member member) {

    }
}