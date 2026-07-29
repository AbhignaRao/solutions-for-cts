package com.library;

public interface ExternalApi {
    String getData();
    String getDataById(int id);
    void sendNotification(String message);
}