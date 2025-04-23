package org.example.yolofarmbe.Repository;

import org.example.yolofarmbe.DTO.UserView;
import org.example.yolofarmbe.Entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {
    boolean existsByUsername(String username);
    Optional<UserAccount> findByUsername(String username);
    List<UserView> findAllByFarmId(int farmId);
    Optional<UserView> findProjectedByUsername(String username);
}
