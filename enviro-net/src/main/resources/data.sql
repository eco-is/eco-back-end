-- Users
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

-- Education
INSERT INTO education.lecture_category (description)
VALUES
    ('Forest Ecosystems'),
    ('Marine Biology'),
    ('Desert Wildlife'),
    ('Arctic Exploration'),
    ('Rainforest Ecology');

INSERT INTO education.lecture (diffuculty, max_recommended_age, min_recommended_age, creator_id, content, name)
VALUES
    (1, 12, 8, 15, '# Introduction to Forest Ecosystems\n' ||
                   'This lecture provides an overview of forest ecosystems, covering various aspects such as biodiversity, ecological interactions, and human impact on forests.\n' ||
                   'Forests are vital for the health of our planet, serving as habitats for countless species and playing a crucial role in carbon sequestration.\n' ||
                   'In this session, we will explore the different layers of a forest, from the forest floor to the emergent layer, and discuss the unique characteristics of each.\n' ||
                   'Additionally, we will delve into the various types of forests found around the world, including tropical rainforests, temperate forests, and boreal forests.\n' ||
                   'Throughout the lecture, we will examine the complex relationships between plants, animals, and microorganisms that make up forest ecosystems.\n' ||
                   'Topics such as nutrient cycling, energy flow, and ecological succession will be explored to provide a comprehensive understanding of forest dynamics.\n' ||
                   'Furthermore, we will address the threats facing forests today, such as deforestation, habitat fragmentation, and climate change, and discuss strategies for conservation and sustainable management.\n' ||
                   'By the end of this lecture, participants will gain a deeper appreciation for the importance of forests and the urgent need to protect them for future generations.\n',
     'Forest 101'),
    (2, 14, 10, 15, '# Exploring Marine Habitats\n' ||
                    'Marine habitats encompass a vast and diverse array of ecosystems, ranging from coral reefs and kelp forests to deep-sea trenches and hydrothermal vents.\n' ||
                    'In this lecture, we will embark on a journey beneath the waves to discover the fascinating world of marine life.\n' ||
                    'We will begin by examining the unique physical and chemical properties of the ocean that shape marine habitats and influence the distribution of species.\n' ||
                    'From there, we will explore the incredible diversity of marine organisms, from microscopic plankton to majestic whales, and everything in between.\n' ||
                    'Participants will learn about the different zones of the ocean, including the sunlit surface waters, the twilight zone, and the dark depths of the abyss.\n' ||
                    'Throughout the lecture, we will discuss the various adaptations that enable marine organisms to thrive in their respective environments, from camouflage and bioluminescence to hydrodynamic shapes and specialized organs.\n' ||
                    'We will also examine the complex ecological relationships that exist within marine ecosystems, such as predator-prey interactions, symbiotic partnerships, and competitive exclusion.\n' ||
                    'Furthermore, we will address the human impacts on marine habitats, including pollution, overfishing, and climate change, and explore potential solutions to mitigate these threats.\n' ||
                    'By the end of this lecture, participants will develop a deeper understanding of the importance of marine habitats and the urgent need to conserve and protect them for future generations.\n',
     'Marine Life Overview'),
    (0, 10, 6, 15, '# Adaptations of Desert Animals\n' ||
                   'Deserts are harsh and unforgiving environments characterized by extreme temperatures, sparse vegetation, and limited water resources.\n' ||
                   'Despite these challenges, desert animals have evolved a remarkable array of adaptations to survive in this hostile habitat.\n' ||
                   'In this lecture, we will explore the fascinating world of desert wildlife and uncover the secrets of their survival.\n' ||
                   'We will begin by discussing the physical adaptations of desert animals, such as camels'' humps for water storage, kangaroos'' efficient kidneys for water conservation, and fennec foxes'' large ears for heat regulation.\n' ||
                'Participants will learn about the behavioral adaptations employed by desert animals to cope with the scarcity of resources, including nocturnal activity to avoid daytime heat, burrowing to escape temperature extremes, and communal living for protection.\n' ||
                'Furthermore, we will examine the ecological roles played by desert species in maintaining ecosystem balance, such as pollination, seed dispersal, and predator-prey dynamics.\n' ||
                'Throughout the lecture, we will highlight some of the iconic desert animals, including the Arabian camel, the desert tortoise, the meerkat, and the sidewinder rattlesnake.\n' ||
                'We will also discuss the conservation challenges facing desert ecosystems, including habitat destruction, climate change, and human encroachment, and explore potential conservation strategies.\n' ||
                'By the end of this lecture, participants will gain a newfound appreciation for the resilience and adaptability of desert animals and the importance of conserving their fragile habitats.\n',
        'Desert Survival'),
    (1, 16, 12, 15, '# Life in the Arctic\n' ||
                    'The Arctic is a vast and remote region located at the northernmost part of Earth, characterized by icy landscapes, extreme cold, and unique wildlife.\n' ||
                    'In this lecture, we will embark on an expedition to explore the wonders of the Arctic and learn about the remarkable adaptations of its inhabitants.\n' ||
                    'We will begin by examining the physical geography of the Arctic, including its ice caps, glaciers, and permafrost, and discuss the role of the Arctic in regulating global climate patterns.\n' ||
                    'Participants will discover the rich biodiversity of the Arctic, from polar bears and Arctic foxes to seals, whales, and migratory birds.\n' ||
                    'We will delve into the unique adaptations of Arctic animals, such as thick fur and blubber for insulation, specialized paws for walking on ice, and hibernation strategies to survive the long winter months.\n' ||
                    'Furthermore, we will explore the cultural heritage of the indigenous peoples of the Arctic, including their traditional knowledge, art, and ways of life.\n' ||
                    'Throughout the lecture, we will address the environmental challenges facing the Arctic, including melting sea ice, habitat loss, and pollution, and discuss efforts to protect this fragile ecosystem.\n' ||
                    'By the end of this lecture, participants will develop a deeper understanding of the Arctic ecosystem and the urgent need to conserve its biodiversity and cultural heritage for future generations.\n',
     'Arctic Adventures'),
    (0, 8, 4, 15, '# Discovering Rainforest Biodiversity\n' ||
                  'Rainforests are some of the most biodiverse ecosystems on Earth, teeming with life and exhibiting a remarkable array of plants, animals, and microorganisms.\n' ||
                  'In this lecture, we will venture into the heart of the rainforest to uncover its hidden treasures and explore the wonders of its biodiversity.\n' ||
                  'We will begin by discussing the structure and layers of the rainforest, from the emergent layer to the forest floor, and examine the unique adaptations of plants and animals to life in this dense environment.\n' ||
                  'Participants will learn about the intricate web of ecological relationships that exist within the rainforest, including symbiotic interactions, predator-prey dynamics, and nutrient cycling.\n' ||
                  'We will highlight some of the iconic species found in the rainforest, such as jaguars, toucans, orchids, and giant ceiba trees, and discuss their ecological importance.\n' ||
                  'Furthermore, we will explore the cultural significance of rainforests to indigenous peoples, including their traditional knowledge, spiritual beliefs, and sustainable practices.\n' ||
                  'Throughout the lecture, we will address the threats facing rainforest ecosystems, including deforestation, habitat fragmentation, and climate change, and discuss strategies for conservation and sustainable management.\n' ||
                  'By the end of this lecture, participants will develop a deeper appreciation for the beauty and complexity of rainforest biodiversity and the urgent need to protect these vital ecosystems.\n',
     'Rainforest Wonders');

INSERT INTO education.has_category (category_id, lecture_id)
VALUES
    (1, 1), -- Forest 101 belongs to Forest Ecosystems category
    (2, 2), -- Marine Life Overview belongs to Marine Biology category
    (3, 3), -- Desert Survival belongs to Desert Wildlife category
    (4, 4), -- Arctic Adventures belongs to Arctic Exploration category
    (5, 5), -- Rainforest Wonders belongs to Rainforest Ecology category
    (1, 3), -- Desert Survival also belongs to Forest Ecosystems category
    (3, 1); -- Forest 101 also belongs to Desert Wildlife category


-- Finance
INSERT INTO accountants (id, wage)
VALUES (5, 18.7), (6, 18.7);

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
