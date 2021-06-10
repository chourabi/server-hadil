package com.bsofts.fidelity.services;

import com.bsofts.fidelity.domain.Mail;

import javax.mail.MessagingException;

public interface SendMailService {
    void sendMail(Mail mail);

    void sendMailWithAttachments(Mail mail) throws MessagingException;
}
