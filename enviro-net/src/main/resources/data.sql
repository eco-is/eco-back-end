INSERT INTO users (email, enabled, name, surname, last_password_reset_date, password, phone_number, username, role, active, date_of_birth, gender)
VALUES
    ('peraperic@gmail.com', true, 'Pera', 'Perić', null, '$2a$10$siTymq2.Fp0J/K6vnBernO/vOXAHUXa4BCRFp14yoJvJbvE8qckGW', '+38763245234', 'peraperic', 'ADMINISTRATOR', true, '1990-01-01', 'MALE'),
    ('markoni@gmail.com', true, 'Marko', 'Milošević', null, '$2a$10$siTymq2.Fp0J/K6vnBernO/vOXAHUXa4BCRFp14yoJvJbvE8qckGW', '+38763245321', 'markom', 'REGISTERED_USER', true, '1985-05-15', 'MALE'),
    ('tati@gmail.com', true, 'Tatjana', 'Ljubičić', null, '$2a$10$siTymq2.Fp0J/K6vnBernO/vOXAHUXa4BCRFp14yoJvJbvE8qckGW', '+38763245987', 'ljubi.tatjana', 'REGISTERED_USER', true, '1978-11-25', 'FEMALE'),
    ('nikolagoric@gmail.com', true, 'Nikola', 'Gorić', null, '$2a$10$siTymq2.Fp0J/K6vnBernO/vOXAHUXa4BCRFp14yoJvJbvE8qckGW', '+38763245876', 'nikola.g', 'REGISTERED_USER', true, '1995-09-10', 'MALE'),
    ('jelastef@gmail.com', true, 'Jelena', 'Stefanović', null, '$2a$10$siTymq2.Fp0J/K6vnBernO/vOXAHUXa4BCRFp14yoJvJbvE8qckGW', '+38763245432', 'jelen.as', 'ACCOUNTANT', true, '1980-03-20', 'FEMALE'),
    ('goranniko@gmail.com', false, 'Goran', 'Nikolić', null, '$2a$10$siTymq2.Fp0J/K6vnBernO/vOXAHUXa4BCRFp14yoJvJbvE8qckGW', '+38763245678', 'niko.goran', 'BOARD_MEMBER', false, '1975-07-12', 'MALE'),
    ('markovicmil@gmail.com', true, 'Miloš', 'Marković', null, '$2a$10$siTymq2.Fp0J/K6vnBernO/vOXAHUXa4BCRFp14yoJvJbvE8qckGW', '+38763245123', 'milosm', 'BOARD_MEMBER', true, '1989-12-30', 'MALE'),
    ('anamatic@gmail.com', true, 'Ana', 'Matić', null, '$2a$10$siTymq2.Fp0J/K6vnBernO/vOXAHUXa4BCRFp14yoJvJbvE8qckGW', '+38763245234', 'ana.m', 'BOARD_MEMBER', true, '1977-04-05', 'FEMALE'),
    ('anastasijapetrovic@gmail.com', true, 'Anastasija', 'Petrović', null, '$2a$10$siTymq2.Fp0J/K6vnBernO/vOXAHUXa4BCRFp14yoJvJbvE8qckGW', '+38763245876', 'anapetrovic', 'PROJECT_MANAGER', true, '1992-08-18', 'FEMALE'),
    ('stevamil@gmail.com', true, 'Stevan', 'Milićić', null, '$2a$10$siTymq2.Fp0J/K6vnBernO/vOXAHUXa4BCRFp14yoJvJbvE8qckGW', '+38763245321', 'stevaj', 'PROJECT_MANAGER', true, '1983-06-25', 'MALE'),
    ('stefanstefanovic@gmail.com', true, 'Stefan', 'Belić', null, '$2a$10$siTymq2.Fp0J/K6vnBernO/vOXAHUXa4BCRFp14yoJvJbvE8qckGW', '+38763245987', 'stefans', 'PROJECT_COORDINATOR', true, '1987-10-15', 'MALE'),
    ('micaj@gmail.com', true, 'Milica', 'Jović', null, '$2a$10$siTymq2.Fp0J/K6vnBernO/vOXAHUXa4BCRFp14yoJvJbvE8qckGW', '+38763245432', 'mica', 'PROJECT_COORDINATOR', true, '1990-02-28', 'FEMALE'),
    ('marijamarijanovic@gmail.com', false, 'Marija', 'Marijanović', null, '$2a$10$siTymq2.Fp0J/K6vnBernO/vOXAHUXa4BCRFp14yoJvJbvE8qckGW', '+38763245678', 'marija.m', 'EDUCATOR', true, '1986-09-07', 'FEMALE'),
    ('ljubabuba@gmail.com', true, 'Ljubica', 'Atić', null, '$2a$10$siTymq2.Fp0J/K6vnBernO/vOXAHUXa4BCRFp14yoJvJbvE8qckGW', '+38763245123', 'ljubica.a', 'EDUCATOR', true, '1994-03-12', 'FEMALE');

INSERT INTO projects (name, description, duration_months, budget, type, status, manager_id)
VALUES
    ('Conservation of Wetlands', 'Protecting and restoring wetland ecosystems', 24, 50000.00, 'INTERNAL', 'DRAFT', 9),
    ('Reforestation Initiative', 'Planting trees and restoring forest habitats', 18, 30000.00, 'EXTERNAL', 'PENDING', 9),
    ('Urban Green Spaces Development', 'Creating parks and green areas in urban settings', 12, 25000.00, 'INTERNAL', 'APPROVED', 9),
    ('River Cleanup Campaign', 'Cleaning up polluted rivers and waterways', 6, 10000.00, 'EXTERNAL', 'ONGOING', 9),
    ('Biodiversity Monitoring Program', 'Monitoring and preserving local biodiversity', 36, 75000.00, 'INTERNAL', 'REJECTED', 9),
    ('Sustainable Agriculture Project', 'Promoting eco-friendly farming practices', 24, 60000.00, 'EXTERNAL', 'ARCHIVED', 9),
    ('Ocean Conservation Initiative', 'Protecting marine life and habitats', 36, 80000.00, 'INTERNAL', 'ONGOING', 9),
    ('Eco-friendly Transportation Campaign', 'Promoting sustainable transport options', 12, 20000.00, 'EXTERNAL', 'PENDING', 9),
    ('Wildlife Habitat Restoration', 'Restoring habitats for endangered species', 24, 60000.00, 'INTERNAL', 'ONGOING', 10),
    ('Green Energy Development Project', 'Promoting renewable energy solutions', 18, 35000.00, 'EXTERNAL', 'APPROVED', 10),
    ('Community Gardens Initiative', 'Creating communal spaces for sustainable gardening', 9, 15000.00, 'INTERNAL', 'ONGOING', 10),
    ('Eco-tourism Promotion Program', 'Encouraging responsible tourism practices', 24, 40000.00, 'EXTERNAL', 'PENDING', 10),
    ('Waste Recycling Campaign', 'Promoting waste reduction and recycling', 6, 10000.00, 'INTERNAL', 'ARCHIVED', 13),
    ('Green Buildings Certification Project', 'Promoting eco-friendly building standards', 36, 90000.00, 'EXTERNAL', 'APPROVED', 10),
    ('Climate Change Adaptation Strategy', 'Developing strategies for climate resilience', 24, 70000.00, 'INTERNAL', 'ONGOING', 10);

INSERT INTO documents (document_id, project_id, name, progress, status)
VALUES
    (1, 1, 'Conservation Plan', 0.25, 'In Progress - less than halfway'), -- Conservation of Wetlands
    (1, 2, 'Reforestation Plan', 0.10, 'In Progress - less than halfway'), -- Reforestation Initiative
    (1, 3, 'Green Spaces Proposal', 0.50, 'Halfway Done'), -- Urban Green Spaces Development
    (1, 4, 'Cleanup Strategy', 0.75, 'In Progress - more than halfway'), -- River Cleanup Campaign
    (1, 5, 'Monitoring Protocol', 0.40, 'In Progress - less than halfway'), -- Biodiversity Monitoring Program
    (1, 6, 'Agriculture Plan', 0.60, 'In Progress - more than halfway'), -- Sustainable Agriculture Project
    (1, 7, 'Ocean Protection Plan', 0.80, 'In Progress - more than halfway'), -- Ocean Conservation Initiative
    (1, 8, 'Transportation Proposal', 0.20, 'In Progress - less than halfway'), -- Eco-friendly Transportation Campaign
    (1, 9, 'Habitat Restoration Plan', 0.70, 'In Progress - more than halfway'), -- Wildlife Habitat Restoration
    (1, 10, 'Energy Development Plan', 0.30, 'In Progress - less than halfway'), -- Green Energy Development Project
    (1, 11, 'Gardens Blueprint', 0.90, 'Completed'), -- Community Gardens Initiative
    (1, 12, 'Tourism Promotion Plan', 0.15, 'In Progress - less than halfway'), -- Eco-tourism Promotion Program
    (1, 13, 'Recycling Strategy', 1.00, 'Completed'), -- Waste Recycling Campaign
    (1, 14, 'Green Buildings Plan', 0.45, 'In Progress - less than halfway'), -- Green Buildings Certification Project
    (1, 15, 'Climate Adaptation Strategy', 0.55, 'In Progress - more than halfway'); -- Climate Change Adaptation Strategy

INSERT INTO document_versions (version, document_id, project_id, file_path, author)
VALUES
    (1, 1, 1, '/path/to/document1.pdf', 9), -- Conservation of Wetlands
    (1, 2, 2, '/path/to/document2.pdf', 9), -- Reforestation Initiative
    (1, 3, 3, '/path/to/document3.pdf', 9),  -- Urban Green Spaces Development
    (1, 4, 4, '/path/to/document4.pdf', 9), -- River Cleanup Campaign
    (1, 5, 5, '/path/to/document5.pdf', 9), -- Biodiversity Monitoring Program
    (1, 6, 6, '/path/to/document6.pdf', 9), -- Sustainable Agriculture Project
    (1, 7, 7, '/path/to/document7.pdf', 9), -- Ocean Conservation Initiative
    (1, 8, 8, '/path/to/document8.pdf', 9), -- Eco-friendly Transportation Campaign
    (1, 9, 9, '/path/to/document9.pdf', 10), -- Wildlife Habitat Restoration
    (1, 10, 10, '/path/to/document10.pdf', 10), -- Green Energy Development Project
    (1, 11, 11, '/path/to/document11.pdf', 10), -- Community Gardens Initiative
    (1, 12, 12, '/path/to/document12.pdf', 10), -- Eco-tourism Promotion Program
    (1, 13, 13, '/path/to/document13.pdf', 10), -- Waste Recycling Campaign
    (1, 14, 14, '/path/to/document14.pdf', 10), -- Green Buildings Certification Project
    (1, 15, 15, '/path/to/document15.pdf', 10); -- Climate Change Adaptation Strategy

