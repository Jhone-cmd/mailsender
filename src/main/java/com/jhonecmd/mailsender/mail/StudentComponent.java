package com.jhonecmd.mailsender.mail;

import com.jhonecmd.mailsender.model.StudentEntity;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;

@Component
public class StudentComponent extends MailComponent {

    private final TemplateEngine templateEngine;
    public StudentComponent(JavaMailSender javaMailSender,TemplateEngine templateEngine) {
        super(javaMailSender);
        this.templateEngine = templateEngine;
    }

    public void sendSimpleWelcomeEmail(StudentEntity student) {

        MailMessage mailMessage = MailMessage.builder()
                .to(student.getEmail())
                .from("jhone.nodejs@gmail.com")
                .subject("Java Mail Sender")
                .message(String.format("Seja Bem Vindo %s! Sucesso na sua jornada!", student.getName())).build();

        sendSimpleMail(mailMessage);
    }

    public void sendAdvancedWelcomeEmail(StudentEntity student) {

        Context context = new Context();
        context.setVariable("name", student.getName());
        context.setVariable("email", student.getEmail());
        context.setVariable("birthday", student.getBirthday());
        context.setVariable("date", LocalDateTime.now());

        String templateHtml = templateEngine.process("welcome-template",context);


        MailMessage mailMessage = MailMessage.builder()
                .to(student.getEmail())
                .from("jhone.nodejs@gmail.com")
                .subject("Java Mail Sender")
                .message(templateHtml)
                .attachment("JavaMailSender.pdf", new ClassPathResource("static/docs/JavaMailSender.pdf"))
                .bodyFile("headerLogo", new ClassPathResource("static/images/welcome.png"))
                .build();

        sendAdvancedMail(mailMessage);
    }
}
