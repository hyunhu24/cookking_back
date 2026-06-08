package com.springboot.collectioncamera.repository;

import com.springboot.collectioncamera.entity.CollectionCamera;
import com.springboot.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollectionCameraRepository extends JpaRepository<CollectionCamera, Long> {
    boolean existsByMemberAndCustomCategoryName(Member member, String customCategoryName);
    boolean existsByCustomCategoryNameAndMember_MemberId(String customCategoryName, long memberId);
    List<CollectionCamera> findByMember(Member member);
    boolean existsByMember_MemberIdAndCustomCategoryNameAndCollectionCameraIdNot(long memberId, String customCategoryName, long collectionCameraId);
}
