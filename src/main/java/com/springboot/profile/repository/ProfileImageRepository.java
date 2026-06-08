package com.springboot.profile.repository;

import com.springboot.profile.entity.ProfileImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfileImageRepository extends JpaRepository<ProfileImage, Long> {
    Page<ProfileImage> findAllByMemberProfileImages_Member_MemberId(Long memberId, Pageable pageable);
    Page<ProfileImage> findByProfileImageIdNotIn(List<Long> profileImageIds, Pageable pageable);
}