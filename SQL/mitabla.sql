CREATE TABLE Usuario(
    id SERIAL PRIMARY KEY,
    correo VARCHAR(100),
    password VARCHAR(20),
    isAcademico BOOLEAN DEFAULT FALSE,
    isAdministrador BOOLEAN DEFAULT FALSE,
    RFC VARCHAR(18),
    depto VARCHAR(40),
    nombre VARCHAR(200),
    apellido1 VARCHAR(200),
    apellido2 VARCHAR(200)
);

