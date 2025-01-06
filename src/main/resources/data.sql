-- Insert Roles
INSERT INTO role (id, name) VALUES
    (1, 'USER'),
(2, 'PREMIUM'),
(3, 'ADMIN');

-- Insert Users
INSERT INTO users (id, username, password, email) VALUES
    (1, 'user1', 'password1', 'user1@example.com'),
(2, 'user2', 'password2', 'user2@example.com'),
(3, 'admin', 'adminpassword', 'admin@example.com');

-- Assign Roles to Users
INSERT INTO user_role (user_id, role_id) VALUES
    (1, 1),
(2, 2),
(3, 3);

-- Insert Statuses
INSERT INTO status (id, name) VALUES
    (1, 'Open'),
(2, 'In Progress'),
(3, 'Completed'),
(4, 'Archived');

-- Insert Notes
INSERT INTO note (id, title, finish_date, user_id) VALUES
    (1, 'Note 1 Title', '2025-01-10', 1),
(2, 'Note 2 Title', NULL, 2);

-- Assign Statuses to Notes
INSERT INTO note_status (note_id, status_id) VALUES
    (1, 1),
(2, 2);
