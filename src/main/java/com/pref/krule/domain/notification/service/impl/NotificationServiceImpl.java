package com.pref.krule.domain.notification.service.impl;

import com.pref.krule.domain.account.service.AccountService;
import com.pref.krule.domain.notification.repository.NotificationRepository;
import com.pref.krule.domain.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NotificationServiceImpl implements NotificationService {

    // service
    private final AccountService accountService;

    // repository
    private final NotificationRepository notificationRepository;

    // ===== ===== ===== ===== ===== method ===== ===== ===== ===== =====

}
