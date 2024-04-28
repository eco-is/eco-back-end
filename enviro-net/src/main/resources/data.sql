INSERT INTO users (email, enabled, name, surname, last_password_reset_date, password, phone_number, username, role, active, date_of_birth, gender)
VALUES
    ('peraperic@gmail.com', true, 'Pera', 'Perić', null, '$2a$10$siTymq2.Fp0J/K6vnBernO/vOXAHUXa4BCRFp14yoJvJbvE8qckGW', '+38763245234', 'peraperic', 'ADMINISTRATOR', true, '1990-01-01', 'MALE'),
    ('markoni@gmail.com', true, 'Marko', 'Milošević', null, '$2a$10$siTymq2.Fp0J/K6vnBernO/vOXAHUXa4BCRFp14yoJvJbvE8qckGW', '+38763245321', 'markom', 'REGISTERED_USER', true, '1985-05-15', 'MALE'),
    ('tati@gmail.com', true, 'Tatjana', 'Ljubičić', null, '$2a$10$siTymq2.Fp0J/K6vnBernO/vOXAHUXa4BCRFp14yoJvJbvE8qckGW', '+38763245987', 'ljubi.tatjana', 'REGISTERED_USER', true, '1978-11-25', 'FEMALE'),
    ('nikolagoric@gmail.com', true, 'Nikola', 'Gorić', null, '$2a$10$siTymq2.Fp0J/K6vnBernO/vOXAHUXa4BCRFp14yoJvJbvE8qckGW', '+38763245876', 'nikola.g', 'REGISTERED_USER', true, '1995-09-10', 'MALE'),
    ('jelastef@gmail.com', true, 'Jelena', 'Stefanović', null, '$2a$10$siTymq2.Fp0J/K6vnBernO/vOXAHUXa4BCRFp14yoJvJbvE8qckGW', '+38763245432', 'ac1', 'ACCOUNTANT', true, '1980-03-20', 'FEMALE'),
    ('helena.jankovic@gmail.com', true, 'Helena', 'Janković', null, '$2a$10$siTymq2.Fp0J/K6vnBernO/vOXAHUXa4BCRFp14yoJvJbvE8qckGW', '+381651231234', 'ac2', 'ACCOUNTANT', true, '1987-03-28', 'FEMALE'),
    ('goranniko@gmail.com', false, 'Goran', 'Nikolić', null, '$2a$10$siTymq2.Fp0J/K6vnBernO/vOXAHUXa4BCRFp14yoJvJbvE8qckGW', '+38763245678', 'niko.goran', 'BOARD_MEMBER', false, '1975-07-12', 'MALE'),
    ('markovicmil@gmail.com', true, 'Miloš', 'Marković', null, '$2a$10$siTymq2.Fp0J/K6vnBernO/vOXAHUXa4BCRFp14yoJvJbvE8qckGW', '+38763245123', 'milosm', 'BOARD_MEMBER', true, '1989-12-30', 'MALE'),
    ('anamatic@gmail.com', true, 'Ana', 'Matić', null, '$2a$10$siTymq2.Fp0J/K6vnBernO/vOXAHUXa4BCRFp14yoJvJbvE8qckGW', '+38763245234', 'ana.m', 'BOARD_MEMBER', true, '1977-04-05', 'FEMALE'),
    ('anastasijapetrovic@gmail.com', true, 'Anastasija', 'Petrović', null, '$2a$10$siTymq2.Fp0J/K6vnBernO/vOXAHUXa4BCRFp14yoJvJbvE8qckGW', '+38763245876', 'anapetrovic', 'PROJECT_MANAGER', true, '1992-08-18', 'FEMALE'),
    ('stevamil@gmail.com', true, 'Stevan', 'Milićić', null, '$2a$10$siTymq2.Fp0J/K6vnBernO/vOXAHUXa4BCRFp14yoJvJbvE8qckGW', '+38763245321', 'stevaj', 'PROJECT_MANAGER', true, '1983-06-25', 'MALE'),
    ('stefanstefanovic@gmail.com', true, 'Stefan', 'Belić', null, '$2a$10$siTymq2.Fp0J/K6vnBernO/vOXAHUXa4BCRFp14yoJvJbvE8qckGW', '+38763245987', 'stefans', 'PROJECT_COORDINATOR', true, '1987-10-15', 'MALE'),
    ('micaj@gmail.com', true, 'Milica', 'Jović', null, '$2a$10$siTymq2.Fp0J/K6vnBernO/vOXAHUXa4BCRFp14yoJvJbvE8qckGW', '+38763245432', 'mica', 'PROJECT_COORDINATOR', true, '1990-02-28', 'FEMALE'),
    ('marijamarijanovic@gmail.com', false, 'Marija', 'Marijanović', null, '$2a$10$siTymq2.Fp0J/K6vnBernO/vOXAHUXa4BCRFp14yoJvJbvE8qckGW', '+38763245678', 'marija.m', 'EDUCATOR', true, '1986-09-07', 'FEMALE'),
    ('ljubabuba@gmail.com', true, 'Ljubica', 'Atić', null, '$2a$10$siTymq2.Fp0J/K6vnBernO/vOXAHUXa4BCRFp14yoJvJbvE8qckGW', '+38763245123', 'ljubica.a', 'EDUCATOR', true, '1994-03-12', 'FEMALE');
INSERT INTO accountants (id, wage)
VALUES (5, 18.7), (6, 18.7);

-- Finance
INSERT INTO finance.budget_plan (status,  start_date, end_date, name, description, author, last_updated_on_date)
VALUES
    ('DRAFT', '2024-01-01', '2024-12-31', '2024 Annual Budget', 'Annual Budget Plan', 5, '2024-04-04'),
    ('DRAFT', '2025-01-01', '2025-12-31', '2025 Annual Budget', 'Annual Budget Plan', 5, '2024-04-04'),
    ('PENDING', '2024-04-01', '2024-06-30', 'Q2 2024 Budget', 'Quarterly Budget Plan', 5, '2024-04-04'),
    ('APPROVED', '2024-01-01', '2024-12-31', '2024 Annual Budget (Approved)', 'Approved Annual Budget Plan', 5, '2024-04-04'),
    ('REJECTED', '2024-01-01', '2024-12-31', '2024 Annual Budget (Rejected)', 'Rejected Annual Budget Plan', 5, '2024-04-04'),
    ('CLOSED', '2024-01-01', '2024-12-31', '2024 Annual Budget (Closed)', 'Closed Annual Budget Plan', 5, '2024-04-04'),
    ('DRAFT', '2026-01-01', '2026-12-31', '2026 Annual Budget', 'Annual Budget Plan', 5, '2024-04-04'),
    ('DRAFT', '2027-01-01', '2027-12-31', '2027 Annual Budget', 'Annual Budget Plan', 6, '2024-04-04'),
    ('PENDING', '2024-07-01', '2024-09-30', 'Q3 2024 Budget', 'Quarterly Budget Plan', 5, '2024-04-04'),
    ('APPROVED', '2025-01-01', '2025-12-31', '2025 Annual Budget (Approved)', 'Approved Annual Budget Plan', 6, '2024-04-04'),
    ('REJECTED', '2024-07-01', '2024-12-31', '2024 Annual Budget (Rejected)', 'Rejected Annual Budget Plan', 6, '2024-04-04');
