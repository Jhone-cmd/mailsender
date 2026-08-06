package com.jhonecmd.mailsender.mail;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import org.springframework.core.io.ClassPathResource;

import java.util.Map;

@Data
@Builder
public class MailMessage {

    private String to;
    private String from;
    private String subject;
    private String message;

    @Singular
    private Map<String, ClassPathResource> attachments;

    @Singular
    private Map<String, ClassPathResource> bodyFiles;

}
