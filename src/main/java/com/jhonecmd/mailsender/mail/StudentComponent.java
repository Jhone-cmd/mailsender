package com.jhonecmd.mailsender.mail;

import com.jhonecmd.mailsender.model.StudentEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class StudentComponent extends MailComponent {
    public StudentComponent(JavaMailSender javaMailSender) {
        super(javaMailSender);
    }

    public void sendSimpleWelcomeEmail(StudentEntity student) {

        MailMessage mailMessage = MailMessage.builder()
                .to(student.getEmail())
                .from("no-reply@javamailsender.com")
                .subject("Java Mail Sender")
                .message(String.format("Seja Bem Vindo %s! Sucesso na sua jornada!", student.getName())).build();

        sendSimpleMail(mailMessage);
    }
}
