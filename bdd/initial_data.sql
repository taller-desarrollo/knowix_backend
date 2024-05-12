-- Categorias de cursos:
INSERT INTO Category (category_description, category_name, status) VALUES 
('Desarrollo de software, programación en diversos lenguajes', 'Desarrollo de Software', TRUE),
('Diseño gráfico, uso de software y técnicas de diseño', 'Diseño Gráfico', TRUE),
('Marketing digital, SEO, campañas en redes sociales', 'Marketing Digital', TRUE),
('Fotografía profesional, técnicas y edición', 'Fotografía', TRUE),
('Gestión empresarial, liderazgo y manejo de equipos', 'Gestión Empresarial', TRUE),
('Cocina internacional, técnicas y recetas', 'Cocina', TRUE),
('Idiomas y lingüística aplicada, inglés, francés y más', 'Idiomas', TRUE),
('Fitness y bienestar personal, rutinas y dietas', 'Fitness y Bienestar', TRUE),
('Programación web, desde HTML hasta frameworks avanzados', 'Desarrollo Web', TRUE),
('Música y teoría musical, instrumentos y producción', 'Música', TRUE);


-- Idiomas disponibles:

INSERT INTO language (language_name, status) VALUES 
('Inglés', TRUE),
('Mandarín', TRUE),
('Español', TRUE),
('Italiano', TRUE),
('Árabe', TRUE),
('Aleman', TRUE),
('Portugués', TRUE),
('Ruso', TRUE),
('Japonés', TRUE),
('Quechua', TRUE);

-- tipo de cuenta bancaria:
INSERT INTO account_type (account_type_id, description) VALUES (1, 'Caja de Ahorro');
INSERT INTO account_type (account_type_id, description) VALUES (2, 'Cuenta Corriente');

-- bancos:
INSERT INTO bank (bank_id, bank_name, phone_number, webpage) VALUES (1, 'Banco Nacional de Bolivia', '800 17 7272', 'https://www.bnb.com.bo');
INSERT INTO bank (bank_id, bank_name, phone_number, webpage) VALUES (2, 'Banco Fortaleza', '2114141', 'https://www.bcp.com.bo');
INSERT INTO bank (bank_id, bank_name, phone_number, webpage) VALUES (3, 'Banco Mercantil Santa Cruz', '800 10 2020', 'www.bmsc.com.bo');
INSERT INTO bank (bank_id, bank_name, phone_number, webpage) VALUES (4, 'Banco Ganadero', '800 10 12', 'www.bg.com.bo');
INSERT INTO bank (bank_id, bank_name, phone_number, webpage) VALUES (5, 'Banco Bisa', '800 10 212', 'www.bisa.com');
INSERT INTO bank (bank_id, bank_name, phone_number, webpage) VALUES (6, 'Banco Económico', '800 10 21', 'www.baneco.com.bo');
INSERT INTO bank (bank_id, bank_name, phone_number, webpage) VALUES (7, 'Banco Unión', '800 10 66', 'bancounion.com.bo');
INSERT INTO bank (bank_id, bank_name, phone_number, webpage) VALUES (8, 'Banco Sol', '800 10 666', 'www.bancosol.com.bo');


-- ejemplos de usuarios:

insert into kc_group(kc_group_name, status, tx_date, tx_user, tx_host) values 
('knowix', true, CURRENT_TIMESTAMP, 'admin', 'localhost');

INSERT INTO kc_user (kc_uuid, first_name, last_name, email, status, tx_date, tx_user, tx_host, s3_profile_picture, kc_group_kc_group_id) VALUES
('c3ae71d6-a8aa-4110-baed-ce6f185aebe3', 'Jose', 'Campero', 12345, TRUE, CURRENT_TIMESTAMP, 'admin', 'localhost', 67890, 1);


INSERT INTO kc_user (kc_uuid, first_name, last_name, email, status, tx_date, tx_user, tx_host, s3_profile_picture, kc_group_kc_group_id) VALUES
('9bebffb8-b0de-443e-a774-f2e04b49ada7', 'Fernanda', 'Gutierrez', 12345, TRUE, CURRENT_TIMESTAMP, 'admin', 'localhost', 67890, 1);