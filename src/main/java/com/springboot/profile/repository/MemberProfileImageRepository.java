package com.springboot.profile.repository;

import com.springboot.member.entity.MemberProfileImage;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberProfileImageRepository extends JpaRepository<MemberProfileImage, Long> {
    boolean existsByMember_MemberIdAndProfileImage_ProfileImageId(Long memberId, Long profileImageId);
    @Query("SELECT mpi.profileImage.profileImageId FROM MemberProfileImage mpi WHERE mpi.member.memberId = :memberId")
    List<Long> findProfileImageIdsByMemberId(@Param("memberId") Long memberId);

}