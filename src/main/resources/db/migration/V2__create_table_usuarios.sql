CREATE TABLE usuarios(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL UNIQUE, 
    correo_electronico VARCHAR(255) UNIQUE,
    contrasena VARCHAR(255) NOT NULL, 
    perfil_id BIGINT,
    CONSTRAINT fk_usuarios_from_perfil FOREIGN KEY (perfil_id) REFERENCES perfiles(id)
)