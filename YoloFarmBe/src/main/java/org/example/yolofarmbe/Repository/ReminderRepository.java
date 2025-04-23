package org.example.yolofarmbe.Repository;

import org.example.yolofarmbe.DTO.ReminderView;
import org.example.yolofarmbe.Entity.Reminder;
import org.example.yolofarmbe.Entity.ReminderId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, ReminderId> {
    List<ReminderView> findByUserAccount_Username(String username);
    Optional<ReminderView> findReminderById(ReminderId reminderId);
}
