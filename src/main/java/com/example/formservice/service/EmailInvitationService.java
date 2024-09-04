package com.example.formservice.service;

import com.example.formservice.dto.EmailInvitationDto;
import com.example.formservice.model.EmailInvitation;

import java.util.List;
import java.util.UUID;

public interface EmailInvitationService {
    EmailInvitation sendEmailInvitation(EmailInvitationDto emailInvitation);
    EmailInvitation getEmailInvitationById(UUID id);
    List<EmailInvitation> getAllEmailInvitations();
    EmailInvitation updateEmailInvitation(UUID id, EmailInvitation emailInvitation);
    void deleteEmailInvitation(UUID id);
}
