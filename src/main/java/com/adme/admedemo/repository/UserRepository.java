package com.adme.admedemo.repository;

import com.adme.admedemo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // id 값을 토큰 생성 정보로 사용
    User getByUid(String uid);
    User findByUid(String uid);
}
