-- Delete existing data
DELETE FROM members;

-- Insert initial data
INSERT INTO members (name, email) VALUES ('John Doe', 'john.doe@example.com');
INSERT INTO members (name, email) VALUES ('Jane Doe', 'jane.doe@example.com');
