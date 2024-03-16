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
