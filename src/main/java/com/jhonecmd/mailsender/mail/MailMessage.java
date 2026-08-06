package com.jhonecmd.mailsender.mail;

import lombok.Data;
import org.springframework.core.io.ClassPathResource;

import java.util.Map;

@Data
public class MailMessage {

    private String to;
    private String from;
    private String subject;
    private String message;

    private Map<String, ClassPathResource> attachments;
    private Map<String, ClassPathResource> bodyFiles;

}
