package com.event.event_app.service.async;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailNotificationService {

    private final JavaMailSender mailSender;

    @Async
    public CompletableFuture<Void> sendRegistrationConfirmation(String toEmail, String eventTitle) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject("Registration Confirmed: " + eventTitle);
            message.setText("You have successfully registered for the event: " + eventTitle);
            mailSender.send(message);
            log.info("Registration confirmation email sent to {}", toEmail);
        } catch (Exception e) {
            log.error("Failed to send email to {}: {}", toEmail, e.getMessage());
        }
        return CompletableFuture.completedFuture(null);
    }

    @Async
    public CompletableFuture<Void> sendCancellationNotification(String toEmail, String eventTitle) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject("Registration Cancelled: " + eventTitle);
            message.setText("Your registration for the event: " + eventTitle + " has been cancelled.");
            mailSender.send(message);
            log.info("Cancellation email sent to {}", toEmail);
        } catch (Exception e) {
            log.error("Failed to send email to {}: {}", toEmail, e.getMessage());
        }
        return CompletableFuture.completedFuture(null);
    }

    @Async
    public CompletableFuture<Void> sendEventReminder(String toEmail, String eventTitle) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject("Event Reminder: " + eventTitle);
            message.setText("Reminder: Your event " + eventTitle + " is starting soon!");
            mailSender.send(message);
            log.info("Reminder email sent to {}", toEmail);
        } catch (Exception e) {
            log.error("Failed to send reminder to {}: {}", toEmail, e.getMessage());
        }
        return CompletableFuture.completedFuture(null);
    }
}
