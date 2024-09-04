package com.example.formservice.controller;

import com.example.formservice.dto.EmailInvitationDto;
import com.example.formservice.handler.successful.SuccessResponseHandler;
import com.example.formservice.handler.successful.SuccessfulResponse;
import com.example.formservice.model.EmailInvitation;
import com.example.formservice.service.EmailInvitationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/forms/email-invitations")
@AllArgsConstructor
public class EmailInvitationController {

    private final EmailInvitationService emailInvitationService;
    private final SuccessResponseHandler successResponseHandler;

    @PostMapping
    public ResponseEntity<SuccessfulResponse<EmailInvitation>> createEmailInvitation(@RequestBody EmailInvitationDto emailInvitation) {
        EmailInvitation createdEmailInvitation = emailInvitationService.sendEmailInvitation(emailInvitation);
        return successResponseHandler.created(createdEmailInvitation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessfulResponse<EmailInvitation>> getEmailInvitationById(@PathVariable UUID id) {
        EmailInvitation emailInvitation = emailInvitationService.getEmailInvitationById(id);
        return successResponseHandler.ok(emailInvitation);
    }

    @GetMapping
    public ResponseEntity<SuccessfulResponse<List<EmailInvitation>>> getAllEmailInvitations() {
        List<EmailInvitation> emailInvitations = emailInvitationService.getAllEmailInvitations();
        return successResponseHandler.ok(emailInvitations);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessfulResponse<EmailInvitation>> updateEmailInvitation(@PathVariable UUID id, @RequestBody EmailInvitation emailInvitation) {
        EmailInvitation updatedEmailInvitation = emailInvitationService.updateEmailInvitation(id, emailInvitation);
        return successResponseHandler.ok(updatedEmailInvitation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessfulResponse<EmailInvitation>> deleteEmailInvitation(@PathVariable UUID id) {
        emailInvitationService.deleteEmailInvitation(id);
        return successResponseHandler.noContent();
    }
}
