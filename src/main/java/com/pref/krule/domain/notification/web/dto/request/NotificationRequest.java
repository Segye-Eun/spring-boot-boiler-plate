package com.pref.krule.domain.notification.web.dto.request;

import com.pref.krule.domain.notification.entity.enums.NotificationType;
import lombok.Data;

@Data
public class NotificationRequest {

    private NotificationType notificationType;
    private String title;
    private String link;
    private String message;
}
