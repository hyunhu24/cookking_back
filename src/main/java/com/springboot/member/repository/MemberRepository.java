package com.springboot.member.repository;

import com.springboot.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    Optional<Member> findByLoginId(String loginId);
    Optional<Member> findByPhoneNumber(String phoneNumber);
    Optional<Member> findByNickNameAndPhoneNumber(String nickName, String phoneNumber);
    Optional<Member> findByNickName(String nickName);
    Page<Member> findByMemberStatus(Member.MemberStatus status, Pageable pageable);
    Optional<Member> findByEmailAndPhoneNumber(String email, String phone);
    Optional<Member> findByLoginIdAndEmail(String loginId, String email);
}
