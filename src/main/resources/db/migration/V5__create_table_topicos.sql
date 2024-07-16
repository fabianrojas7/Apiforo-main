CREATE TABLE topicos(
    id BIGINT AUTO_INCREMENT PRIMARY KEY, 
    titulo VARCHAR(255) NOT NULL UNIQUE,
    mensaje TEXT UNIQUE, 
    fecha_creacion DATETIME,
    status ENUM('ACTIVO', 'INACTIVO'), 
    autor_id BIGINT, 
    curso_id BIGINT, 
    CONSTRAINT fk_usuario FOREIGN KEY (autor_id) REFERENCES usuarios(id), 
    CONSTRAINT fk_curso FOREIGN KEY (curso_id) REFERENCES cursos(id)
) 