package com.sofia.userservice.model;

public class Notifications {

    public String notificationId;
    public String notificationDesc;

    public Notifications(String notificationId, String notificationDesc) {
        this.notificationId = notificationId;
        this.notificationDesc = notificationDesc;
    }

    public Notifications() {
        super();
    }

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public String getNotificationDesc() {
        return notificationDesc;
    }

    public void setNotificationDesc(String notificationDesc) {
        this.notificationDesc = notificationDesc;
    }
}
