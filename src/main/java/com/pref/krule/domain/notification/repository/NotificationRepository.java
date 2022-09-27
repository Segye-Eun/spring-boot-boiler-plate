package com.pref.krule.domain.notification.repository;

import com.pref.krule.domain.notification.entity.Notification;
import com.pref.krule.domain.notification.repository.extension.NotificationRepositoryExtension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long>, NotificationRepositoryExtension {

}
