
-- Insert Users
INSERT INTO users (username, password, email, is_enable, role) VALUES
('user1', '$2a$10$hjGxvkN0w.TCcjcFNQNiGOtBZlBaSwwkvcBgbvhgUWLLcxfz8oplS', 'user1@example.com', TRUE, 'ROLE_USER'),
('user2', '$2a$10$88uhV4Gcc6oErp.hQfXuJumNeLmEjAY9GK5z8ERgF9fDFk8G.KlCS', 'user2@example.com', TRUE, 'ROLE_USER'),
('admin', '$2a$10$zInLivtLP/C1VXF9OuRtCu6qXxF3mkpzQtdt9xJ21Bde.rXBPym.6', 'admin@example.com', TRUE, 'ROLE_USER');



-- Insert Statuses
INSERT INTO status (name) VALUES
('Open'),
('In Progress'),
('Completed'),
('Archived');

-- Insert Notes
INSERT INTO note (title, description, finish_date, user_id) VALUES
('Note 1 Title', 'This is the description for Note 1', '2025-01-10', 1),
('Note 2 Title', 'This is the description for Note 2', NULL, 2);


-- Assign Statuses to Notes
INSERT INTO note_status (note_id, status_id) VALUES
(1, 1),
(2, 2);
