package com.example.formservice.service.impl;

import com.example.formservice.dto.EmailInvitationDto;
import com.example.formservice.handler.error.exception.ResourceNotFoundException;
import com.example.formservice.model.EmailInvitation;
import com.example.formservice.repository.EmailInvitationRepository;
import com.example.formservice.service.EmailInvitationService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class EmailInvitationServiceImpl implements EmailInvitationService {

    private final EmailInvitationRepository emailInvitationRepository;
    private final RestTemplate restTemplate;

    @Override
    public EmailInvitation sendEmailInvitation(EmailInvitationDto emailInvitation) {
        try {
            String emailServiceUrl = "http://localhost:8082/emails/send";  // Adjust the URL as needed
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("to", emailInvitation.getRecipientEmail());
            requestBody.put("subject", "Invitation for populating forms");
            requestBody.put("text", "Your Email Content");

            LocalDateTime sentTime = restTemplate.postForObject(emailServiceUrl, requestBody, LocalDateTime.class);

            // Optionally, you can update the saved invitation with the sent time or log it
            EmailInvitation savedInvitation = new EmailInvitation(emailInvitation.getRecipientEmail(),sentTime);
            return emailInvitationRepository.save(savedInvitation);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException("Failed to send email invitation. Possible data integrity violation.");
        }
    }

    @Override
    public EmailInvitation getEmailInvitationById(UUID id) {
        return emailInvitationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Email invitation not found with id: " + id));
    }

    @Override
    public List<EmailInvitation> getAllEmailInvitations() {
        return emailInvitationRepository.findAll();
    }

    @Override
    public EmailInvitation updateEmailInvitation(UUID id, EmailInvitation emailInvitation) {
        if (!emailInvitationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Email invitation not found with id: " + id);
        }
        emailInvitation.setId(id);
        try {
            return emailInvitationRepository.save(emailInvitation);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException("Failed to update email invitation. Possible data integrity violation.");
        }
    }

    @Override
    public void deleteEmailInvitation(UUID id) {
        if (!emailInvitationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Email invitation not found with id: " + id);
        }
        try {
            emailInvitationRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException("Failed to delete email invitation. Possible data integrity violation.");
        }
    }
}
