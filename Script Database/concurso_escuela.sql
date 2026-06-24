use concurso_escuela;

CREATE TABLE participante (
    id SERIAL PRIMARY KEY,
    cedula VARCHAR(10) NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    edad INTEGER NOT NULL,
    correo VARCHAR(100) NOT NULL,
    estado_civil VARCHAR(20) NOT NULL,
    jornada VARCHAR(20) NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    observaciones varchar(100)
);


INSERT INTO participante
(cedula, nombre, apellido, edad, correo, estado_civil, jornada, categoria, observaciones)
VALUES
('1723456789', 'Ana', 'Mendoza', 16, 'ana.mendoza@colegio.edu.ec', 'Soltero', 'Matutina', 'Oratoria',
 'Participante con experiencia en concursos internos'),

('1712345678', 'Luis', 'Villacís', 17, 'luis.villacis@colegio.edu.ec', 'Soltero', 'Vespertina', 'Matemáticas',
 'Representante del curso de tercero de bachillerato'),

('1709876543', 'Camila', 'Paredes', 15, 'camila.paredes@colegio.edu.ec', 'Soltero', 'Matutina', 'Ciencias',
 'Presentará un proyecto sobre energías renovables');
 
 
 select * from participante