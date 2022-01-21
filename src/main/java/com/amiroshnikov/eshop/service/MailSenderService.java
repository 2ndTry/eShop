package com.amiroshnikov.eshop.service;

import com.amiroshnikov.eshop.domain.User;

public interface MailSenderService {
    void sendActivateCode(User user);
}
