CREATE TABLE cursos(
    id BIGINT AUTO_INCREMENT PRIMARY KEY, 
    nombre VARCHAR(255) NOT NULL UNIQUE, 
    categoria_id BIGINT, 
    CONSTRAINT fk_categoria FOREIGN KEY (categoria_id) REFERENCES categorias(id)
)