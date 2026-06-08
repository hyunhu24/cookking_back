package com.springboot.challenge.service;

import com.springboot.challenge.entity.ChallengeCategory;
import com.springboot.challenge.repository.ChallengeRepository;
import com.springboot.challenge.repository.MemberChallengeRepository;
import com.springboot.dto.PageInfo;
import com.springboot.exception.BusinessLogicException;
import com.springboot.exception.ExceptionCode;
import com.springboot.member.entity.MemberChallenge;
import com.springboot.member.repository.MemberRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class ChallengeService {
    private final MemberRepository memberRepository;
    private final ChallengeRepository challengeRepository;
    private final MemberChallengeRepository memberChallengeRepository;

    public ChallengeService(MemberRepository memberRepository, ChallengeRepository challengeRepository, MemberChallengeRepository memberChallengeRepository) {
        this.memberRepository = memberRepository;
        this.challengeRepository = challengeRepository;
        this.memberChallengeRepository = memberChallengeRepository;
    }

    public Page<MemberChallenge> findAllChallenges(int page, int size, long memberId) {
        verifyMemberExists(memberId);
        Page<MemberChallenge> challengeCategories = memberChallengeRepository.findByMember_MemberId(memberId, PageRequest.of(page, size, Sort.by("createdAt").descending()));
        return challengeCategories;
    }

    public ChallengeCategory findChallenge(long challengeId, long memberId) {
        return null;
    }

    // 도전과제 존재 여부
    public ChallengeCategory verifyChallengeExists(long challengeId) {
        return challengeRepository.findById(challengeId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.CHALLENGE_NOT_FOUND));
    }

    private void verifyMemberExists(long memberId) {
        memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
    }
}