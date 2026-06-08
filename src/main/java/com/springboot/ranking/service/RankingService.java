package com.springboot.ranking.service;

import com.springboot.member.repository.MemberRepository;
import com.springboot.profile.entity.ProfileImage;
import com.springboot.profile.repository.ProfileImageRepository;
import com.springboot.ranking.dto.MemberRankingResponseDto;
import com.springboot.ranking.dto.RankingResponseDto;
import com.springboot.ranking.repository.RankingRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RankingService {

    private final RankingRepository rankingRepository;
    private final ProfileImageRepository profileImageRepository;
    private final MemberRepository memberRepository;

    public RankingService(RankingRepository rankingRepository, ProfileImageRepository profileImageRepository, MemberRepository memberRepository) {
        this.rankingRepository = rankingRepository;
        this.profileImageRepository = profileImageRepository;
        this.memberRepository = memberRepository;
    }

    public List<RankingResponseDto> getTopRecipeBoardWriters() {
        List<Object[]> results = rankingRepository.findTopMembersByRecipeBoardCount();

        return results.stream()
                .map(obj -> buildRankingResponse(obj))
                .collect(Collectors.toList());
    }

    public List<RankingResponseDto> getTopLikeReceivers() {
        List<Object[]> results = rankingRepository.findTopMembersByLikeCount();

        return results.stream()
                .map(obj -> buildRankingResponse(obj))
                .collect(Collectors.toList());
    }

    public List<RankingResponseDto> getTopBookmarkReceivers() {
        List<Object[]> results = rankingRepository.findTopMembersByBookmarkCount();

        return results.stream()
                .map(obj -> buildRankingResponse(obj))
                .collect(Collectors.toList());
    }

    public List<RankingResponseDto> getTopTitleCollectors() {
        List<Object[]> results = rankingRepository.findTopMembersByTitleCount();

        return results.stream()
                .map(obj -> buildRankingResponse(obj))
                .collect(Collectors.toList());
    }

    public MemberRankingResponseDto getMemberAllRanks(long memberId) {
        Integer recipeBoardRank = rankingRepository.findRecipeBoardRankByMemberId(memberId);
        Integer likeRank = rankingRepository.findLikeRankByMemberId(memberId);
        Integer bookmarkRank = rankingRepository.findBookmarkRankByMemberId(memberId);
        Integer titleRank = rankingRepository.findTitleRankByMemberId(memberId);

        return new MemberRankingResponseDto(recipeBoardRank, likeRank, bookmarkRank, titleRank);
    }

    // 랭킹 Response 메서드
    private RankingResponseDto buildRankingResponse(Object[] obj) {
        long memberId = ((Number) obj[0]).longValue();
        String nickName = (String) obj[1];
        String fallbackImagePath = (String) obj[2];
        long count = ((Number) obj[3]).longValue();
        LocalDateTime timestamp = (obj[4] != null) ? ((Timestamp) obj[4]).toLocalDateTime() : null;

        // 최신 프로필 이미지 경로 조회
        String imagePath = memberRepository.findById(memberId)
                .flatMap(member -> Optional.ofNullable(member.getActiveImageId()))
                .flatMap(profileImageRepository::findById)
                .map(ProfileImage::getImagePath)
                .orElse(fallbackImagePath);

        return new RankingResponseDto(memberId, nickName, imagePath, count, timestamp);
    }
}