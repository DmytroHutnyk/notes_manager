
-- Insert Users
INSERT INTO users (username, password, email, is_enable, role) VALUES
('user1', '$2a$10$hjGxvkN0w.TCcjcFNQNiGOtBZlBaSwwkvcBgbvhgUWLLcxfz8oplS', 'user1@example.com', TRUE, 'ROLE_USER'),
('admin', '$2a$10$88uhV4Gcc6oErp.hQfXuJumNeLmEjAY9GK5z8ERgF9fDFk8G.KlCS', 'user2@example.com', TRUE, 'ROLE_ADMIN'),
('admin2', '$2a$10$zInLivtLP/C1VXF9OuRtCu6qXxF3mkpzQtdt9xJ21Bde.rXBPym.6', 'admin@example.com', TRUE, 'ROLE_USER');



-- Insert Statuses
INSERT INTO status (name) VALUES
('Open'),
('In Progress'),
('Completed'),
('Archived');

-- Insert Notes
INSERT INTO note (title, description, finish_date, user_id) VALUES
('Note 1 Title', 'This is the description for Note 1', '2025-01-10', 1),
('Note 2 Title', 'This is the description for Note 2', '2025-05-10', 2),
('Note 3 Title', 'This is the description for Note 3', '2025-05-10', 2),
('Note 4 Title', 'This is the description for Note 4', '2025-05-10', 2),
('Note 5 Title', 'This is the description for Note 5', '2025-05-10', 2),
('Note 6 Title', 'This is the description for Note 6', '2025-05-10', 2),
('Note 7 Title', 'This is the description for Note 7', '2025-05-10', 2),
('Note 8 Title', 'This is the description for Note 8', '2025-05-10', 2),
('Note 9 Title', 'This is the description for Note 9', '2025-05-10', 2),
('Note 10 Title', 'This is the description for Note 10', '2025-05-10', 2),
('Note 10 Title', 'This is the description for Note 10', '2025-05-10', 2),
('Note 12 Title', 'This is the description for Note 12', '2025-05-10', 3),
('Note 13 Title', 'This is the description for Note 13', '2025-05-10', 3);



-- Assign Statuses to Notes
INSERT INTO note_status (note_id, status_id) VALUES
(1, 1),
(2, 2),
(3, 2),
(4, 2),
(5, 3),
(6, 4),
(7, 1),
(8, 2),
(9, 4),
(10, 1),
(11, 3),
(12, 3),
(13, 3);

