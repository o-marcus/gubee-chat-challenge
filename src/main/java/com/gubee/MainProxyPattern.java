package com.gubee;

import com.gubee.usecases.PersistentPoolingUseCaseNotification;
import com.gubee.usecases.UseCaseNotification;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import static com.gubee.enums.ProxyType.VIA_PATTERN_PROXY;
import static com.gubee.factory.ProxyProvider.getFactory;

public class MainProxyPattern {
    public static void main(String[] args) {
        ScheduledExecutorService controller = Executors.newSingleThreadScheduledExecutor();
        var repository = getFactory(VIA_PATTERN_PROXY).createRepository();
        var notificationUseCase = new PersistentPoolingUseCaseNotification(repository);
        UseCaseNotification.PresenterNotification emailPresenter = (message) -> System.out.printf("email %s", message);
        UseCaseNotification.PresenterNotification whatsAppPresenter = (message) -> System.out.printf("whatApp %s", message);
        UseCaseNotification.PresenterNotification smsPresenter = (message) -> System.out.printf("sms %s", message);
        UseCaseNotification.PresenterNotification[] notifications = {emailPresenter, whatsAppPresenter, smsPresenter};
        controller.scheduleAtFixedRate(() -> {
            var nextPos = Math.abs(new Random().nextInt()) % 3;
            notificationUseCase.notifyEveryHour(UUID.randomUUID().toString(), notifications[nextPos]);
            System.out.println();
        }, 1, 1, TimeUnit.SECONDS);
    }
}
