CREATE TABLE respuestas(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    mensaje TEXT NOT NULL,
    fecha_creacion DATETIME, 
    topico_id BIGINT, 
    autor_id BIGINT, 
    CONSTRAINT fk_topico FOREIGN KEY (topico_id) REFERENCES topicos(id), 
    CONSTRAINT fk_autor FOREIGN KEY (autor_id) REFERENCES usuarios(id)
)