-- Insert Users
INSERT INTO User (id, username, email, password, role)
VALUES (UUID_TO_BIN(UUID()), 'user1', 'user1@example.com', '$2a$10$X4d5xdIlRikKBJO54XuHGeuBUkZulBbVGUeMtXJ01cVqpYFSb565.', 'USER_ROLE');

INSERT INTO User (id, username, email, password, role)
VALUES (UUID_TO_BIN(UUID()), 'user2', 'user2@example.com', '$2a$10$lOyQeRo/.aqCUUa7x9Iuc.HMBiwFUMBn6O..jvaC4Kw9/v9HMSdWa', 'USER_ROLE');

-- Insert Forms
INSERT INTO Form (id, title, description, is_anonymous, created_at, created_by)
VALUES
    (UUID_TO_BIN(UUID()), 'Customer Satisfaction Survey', 'Survey to measure customer satisfaction.', false, '2024-08-26', (SELECT id FROM User WHERE email = 'user1@example.com')),
    (UUID_TO_BIN(UUID()), 'Product Feedback', 'Feedback form for our new product.', true, '2024-08-26', (SELECT id FROM User WHERE email = 'user2@example.com'));


-- Inserting initial data into CustomField table
INSERT INTO Custom_Field (id, field_type, field_name, field_value) VALUES
    (UUID_TO_BIN(UUID()), 'TEXT', 'Additional Comments', 'Please provide any additional comments here.');


-- Inserting initial data into Question table
INSERT INTO Question (id, question_text, question_type, is_required, form_id, custom_field_id) VALUES
                                                                                                   (UUID_TO_BIN(UUID()), 'How satisfied are you with our service?', 'RATING', true, (SELECT id FROM Form WHERE title = 'Customer Satisfaction Survey'), (SELECT id FROM Custom_Field WHERE field_type = 'TEXT')),
                                                                                                   (UUID_TO_BIN(UUID()), 'Would you recommend our product?', 'YES_NO', true, (SELECT id FROM Form WHERE title = 'Product Feedback'), (SELECT id FROM Custom_Field WHERE field_type = 'TEXT'));

-- Inserting initial data into Response table
INSERT INTO Response (id, user_email, submitted_at, form_id) VALUES
                                                                 (UUID_TO_BIN(UUID()), 'respondent1@example.com', '2024-08-26 10:00:00', (SELECT id FROM Form WHERE title = 'Customer Satisfaction Survey')),
                                                                 (UUID_TO_BIN(UUID()), 'respondent2@example.com', '2024-08-26 11:00:00', (SELECT id FROM Form WHERE title = 'Product Feedback'));

-- Inserting initial data into Answer table
INSERT INTO Answer (id, answer_text, response_id, question_id) VALUES
                                                                   (UUID_TO_BIN(UUID()), 'Very Satisfied', (SELECT id FROM Response WHERE user_email = 'respondent1@example.com'), (SELECT id FROM Question WHERE question_text = 'How satisfied are you with our service?')),
                                                                   (UUID_TO_BIN(UUID()), 'Yes', (SELECT id FROM Response WHERE user_email = 'respondent2@example.com'), (SELECT id FROM Question WHERE question_text = 'Would you recommend our product?'));

-- Inserting initial data into EmailInvitation table
INSERT INTO Email_Invitation (id, recipient_email, sent_at, form_id) VALUES
                                                                         (UUID_TO_BIN(UUID()), 'invitee1@example.com', '2024-08-25 09:00:00', (SELECT id FROM Form WHERE title = 'Customer Satisfaction Survey')),
                                                                         (UUID_TO_BIN(UUID()), 'invitee2@example.com', '2024-08-25 09:30:00', (SELECT id FROM Form WHERE title = 'Product Feedback'));

-- Inserting initial data into Statistics table
INSERT INTO Statistics (id, total_responses, total_anonymous_responses, average_score, form_id, question_id) VALUES
                                                                                                                 (UUID_TO_BIN(UUID()), 100, 30, 4.5, (SELECT id FROM Form WHERE title = 'Customer Satisfaction Survey'), (SELECT id FROM Question WHERE question_text = 'How satisfied are you with our service?')),
                                                                                                                 (UUID_TO_BIN(UUID()), 200, 50, 4.8, (SELECT id FROM Form WHERE title = 'Product Feedback'), (SELECT id FROM Question WHERE question_text = 'Would you recommend our product?'));
