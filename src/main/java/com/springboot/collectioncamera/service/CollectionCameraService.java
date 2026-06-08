package com.springboot.collectioncamera.service;

import com.springboot.challenge.entity.ChallengeCategory;
import com.springboot.challenge.repository.ChallengeCategoryRepository;
import com.springboot.challenge.repository.MemberChallengeRepository;
import com.springboot.collectioncamera.dto.CollectionCameraDto;
import com.springboot.collectioncamera.entity.CameraImage;
import com.springboot.collectioncamera.entity.CollectionCamera;
import com.springboot.collectioncamera.entity.CollectionItem;
import com.springboot.collectioncamera.repository.CameraImageRepository;
import com.springboot.collectioncamera.repository.CollectionCameraRepository;
import com.springboot.collectioncamera.repository.CollectionItemRepository;
import com.springboot.exception.BusinessLogicException;
import com.springboot.exception.ExceptionCode;
import com.springboot.file.service.StorageService;
import com.springboot.member.entity.Member;
import com.springboot.member.entity.MemberChallenge;
import com.springboot.member.repository.MemberRepository;
import com.springboot.member.service.MemberService;
import com.springboot.title.service.TitleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class CollectionCameraService {
    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final CollectionCameraRepository collectionCameraRepository;
    private final CollectionItemRepository collectionItemRepository;
    private final CameraImageRepository cameraImageRepository;
    private final StorageService storageService;
    private final ChallengeCategoryRepository challengeCategoryRepository;
    private final MemberChallengeRepository memberChallengeRepository;
    private final TitleService titleService;

    public CollectionCameraService(MemberService memberService, MemberRepository memberRepository, CollectionCameraRepository collectionCameraRepository, CollectionItemRepository collectionItemRepository, CameraImageRepository cameraImageRepository, StorageService storageService, ChallengeCategoryRepository challengeCategoryRepository, MemberChallengeRepository memberChallengeRepository, TitleService titleService) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
        this.collectionCameraRepository = collectionCameraRepository;
        this.collectionItemRepository = collectionItemRepository;
        this.cameraImageRepository = cameraImageRepository;
        this.storageService = storageService;
        this.challengeCategoryRepository = challengeCategoryRepository;
        this.memberChallengeRepository = memberChallengeRepository;
        this.titleService = titleService;
    }


    // 도감 카테고리 생성
    @Transactional
    public CollectionCamera createCollectionCamera(CollectionCamera collectionCamera, long cameraImageId, long memberId, Boolean isPrivate) {
        // (1) 멤버 검증
        Member member = memberService.findMember(memberId);

        // (2) 도감 카테고리명 중복 검증
        if (collectionCameraRepository.existsByMemberAndCustomCategoryName(member, collectionCamera.getCustomCategoryName())) {
            throw new BusinessLogicException(ExceptionCode.DUPLICATE_COLLECTION_CATEGORY);
        }

        // (3) CameraImage 조회
        CameraImage cameraImage = cameraImageRepository.findById(cameraImageId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.CAMERA_IMAGE_NOT_FOUND));

        // (4) 연관관계 설정
        collectionCamera.setMember(member);
        collectionCamera.setCameraImage(cameraImage);  // 양방향 메서드로 연결

        // 🔥 (5) 공개/비공개 설정
        if (isPrivate != null) {
            collectionCamera.setCollectionStatus(isPrivate ? CollectionCamera.CollectionStatus.PRIVATE : CollectionCamera.CollectionStatus.PUBLIC);
        }

        // (5) 저장
        return collectionCameraRepository.save(collectionCamera);
    }

    public void updateCollectionInfo(long collectionCameraId, CollectionCameraDto.PatchInfo patchInfoDto, long memberId) {
        CollectionCamera existing = collectionCameraRepository.findById(collectionCameraId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.COLLECTION_NOT_FOUND));

        if (existing.getMember().getMemberId() != memberId) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_ACCESS_DENIED);
        }

        // 🔍 중복 카테고리 이름 검증
        verifyDuplicateCategoryName(memberId, collectionCameraId, patchInfoDto.getCustomCategoryName());

        // ✏️ 수정
        existing.setCustomCategoryName(patchInfoDto.getCustomCategoryName());
        if (patchInfoDto.getIsPrivate() != null) {
            existing.setCollectionStatus(patchInfoDto.getIsPrivate() ? CollectionCamera.CollectionStatus.PRIVATE : CollectionCamera.CollectionStatus.PUBLIC);
        }

        collectionCameraRepository.save(existing);
    }

    public void updateCollectionCamera(long collectionCameraId, Long cameraImageId, long memberId) {
        CollectionCamera existing = collectionCameraRepository.findById(collectionCameraId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.COLLECTION_NOT_FOUND));

        if (existing.getMember().getMemberId() != memberId) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_ACCESS_DENIED);
        }

        CameraImage newCameraImage = cameraImageRepository.findById(cameraImageId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.CAMERA_IMAGE_NOT_FOUND));
        existing.setCameraImage(newCameraImage);

        collectionCameraRepository.save(existing);
    }

    // 도감 카테고리 전체 조회
    public List<CollectionCamera> findCollections(long memberId) {
        Member member = verifyMemberExists(memberId);
        return collectionCameraRepository.findByMember(member);
    }

    // 도감 카테고리 삭제
    public void deleteCollection(long collectionId, long memberId) {
        CollectionCamera collectionCamera = verifyOwnedCollection(collectionId, memberId); // 존재 + 소유자 검증
        collectionCameraRepository.delete(collectionCamera);
    }

    // 도감 카테고리 내 메뉴 목록 조회
    public List<CollectionItem> findCollectionItems(long collectionId, long memberId) {
        CollectionCamera collectionCamera = verifyOwnedCollection(collectionId, memberId); // 소유자 확인까지 포함
        return collectionItemRepository.findByCollectionCamera(collectionCamera);
    }

    // 도감 카테고리 메뉴 추가
    public CollectionItem addCollectionItem(long collectionId, CollectionItem collectionItem, String imageUrl, long memberId) {
        CollectionCamera collectionCamera = verifyOwnedCollection(collectionId, memberId); // 도감 소유자 검증

        // 중복 메뉴 이름 검증
        boolean isDuplicate = collectionCamera.getCollectionItems().stream()
                .anyMatch(item -> item.getMenuName().equals(collectionItem.getMenuName()));
        if (isDuplicate) {
            throw new BusinessLogicException(ExceptionCode.DUPLICATE_COLLECTION_MENU);
        }

        // 이미지 URL 검증
        if (imageUrl == null || imageUrl.isEmpty()) {
            throw new BusinessLogicException(ExceptionCode.IMAGE_REQUIRED);
        }

        collectionItem.setImageUrl(imageUrl);
        collectionItem.setCollectionCamera(collectionCamera); // 소속 설정

        CollectionItem saved = collectionItemRepository.save(collectionItem);

        // 🔥 도전과제 증가 로직
        ChallengeCategory challengeCategory = challengeCategoryRepository.findByCategory("도감")
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.CHALLENGE_CATEGORY_NOT_FOUND));
        MemberChallenge memberChallenge = memberChallengeRepository.findByMember_MemberIdAndChallengeCategory_ChallengeCategoryid(
                        memberId, challengeCategory.getChallengeCategoryid())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_CHALLENGE_NOT_FOUND));

        titleService.incrementChallengeCount(memberChallenge.getMemberChallengeId(), memberId);

        return saved;
    }

    // 도감 카테고리 메뉴 삭제
    public void deleteCollectionItem(long collectionItemId, long memberId) {
        CollectionItem item = verifyCollectionItemExists(collectionItemId);
        verifyCollectionItemOwner(item.getCollectionCamera(), memberId);
        collectionItemRepository.delete(item);
    }

    public List<CameraImage> findCameraImagesByColor(Long cameraColorId) {
        return cameraImageRepository.findAllByCameraColor_CameraColorId(cameraColorId);
    }

    // 회원 존재 확인
    public Member verifyMemberExists(long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
    }

    // 도감 존재 확인
    public CollectionCamera verifyCollectionExists(long collectionId) {
        return collectionCameraRepository.findById(collectionId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.COLLECTION_NOT_FOUND));
    }

    // 도감 소유자 검증
    public void verifyCollectionOwner(CollectionCamera collectionCamera, long memberId) {
        if (collectionCamera.getMember().getMemberId() != memberId) {
            throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED_ACCESS);
        }
    }

    // 도감 + 소유자 한 번에 검증
    public CollectionCamera verifyOwnedCollection(long collectionId, long memberId) {
        CollectionCamera collectionCamera = verifyCollectionExists(collectionId);
        verifyCollectionOwner(collectionCamera, memberId);
        return collectionCamera;
    }

    // 도감 메뉴 존재 확인
    public CollectionItem verifyCollectionItemExists(long collectionItemId) {
        return collectionItemRepository.findById(collectionItemId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.COLLECTION_ITEM_NOT_FOUND));
    }

    // 도감 메뉴 소유자 확인
    public void verifyCollectionItemOwner(CollectionCamera collectionCamera, long memberId) {
        if (collectionCamera.getMember().getMemberId() != memberId) {
            throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED_ACCESS);
        }
    }

    // MemberId와 CustomCategoryName으로 중복 체크 (단, 본인 도감 제외)
    public void verifyDuplicateCategoryName(long memberId, long collectionCameraId, String customCategoryName) {
        boolean exists = collectionCameraRepository.existsByMember_MemberIdAndCustomCategoryNameAndCollectionCameraIdNot(
                memberId, customCategoryName, collectionCameraId);
        if (exists) {
            throw new BusinessLogicException(ExceptionCode.COLLECTION_CATEGORY_NAME_EXISTS);
        }
    }

    @Transactional
    public void createDefaultCollection(Member member) {
        // 이미 기본 도감이 있는지 확인 (안 겹치게)
        boolean exists = collectionCameraRepository.existsByMemberAndCustomCategoryName(member, "도감 예시");
        if (exists) return;  // 이미 있으면 생략

        // CameraImage 1번 (파란색 첫번째)
        CameraImage cameraImage = cameraImageRepository.findById(1L)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.CAMERA_IMAGE_NOT_FOUND));

        // 도감 생성
        CollectionCamera defaultCollection = new CollectionCamera();
        defaultCollection.setCustomCategoryName("도감 예시");
        defaultCollection.setCameraImage(cameraImage);
        defaultCollection.setCollectionStatus(CollectionCamera.CollectionStatus.PUBLIC);
        defaultCollection.setMember(member);  // 양방향 세팅

        collectionCameraRepository.save(defaultCollection);
    }
}

