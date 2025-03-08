package com.example.SubscriptionManagementSystem.Repository;

import com.example.SubscriptionManagementSystem.Entity.Book;
import com.example.SubscriptionManagementSystem.Entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    @Query(value="SELECT * FROM member m  WHERE LOWER(m.first_name) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(m.last_name) LIKE LOWER(CONCAT('%', :search, '%')) OR m.phone_number LIKE LOWER(CONCAT('%', :search, '%'))",nativeQuery = true)
    Page<Member> searchMember(@Param("search") String search, Pageable pageable);
}
