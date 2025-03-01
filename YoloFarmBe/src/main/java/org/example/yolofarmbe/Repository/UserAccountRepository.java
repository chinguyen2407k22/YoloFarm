package org.example.yolofarmbe.Repository;

import org.example.yolofarmbe.Entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {
    boolean existsByUsername(String username);
    Optional<UserAccount> findByUsername(String username);
}
