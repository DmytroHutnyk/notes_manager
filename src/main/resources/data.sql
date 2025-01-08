-- Insert Roles
INSERT INTO role ( name) VALUES
    ( 'USER'),
('PREMIUM'),
('ADMIN');

-- Insert Users
INSERT INTO users (username, password, email) VALUES
    ('user1', 'password1', 'user1@example.com'),
('user2', 'password2', 'user2@example.com'),
('admin', 'adminpassword', 'admin@example.com');

-- Assign Roles to Users
INSERT INTO user_role (user_id, role_id) VALUES
    (1, 1),
(2, 2),
(3, 3);

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
