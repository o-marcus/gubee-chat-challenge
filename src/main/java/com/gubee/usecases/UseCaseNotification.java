package com.gubee.usecases;

public interface UseCaseNotification {

    void notifyEveryHour(String customerId, PresenterNotification presenter);

    @FunctionalInterface
    interface PresenterNotification {
        void notification(String message);
    }
}


