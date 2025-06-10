-- scripts/init.sql
CREATE DATABASE catalogue_db;

\c catalogue_db;

-- Crear tabla de libros
CREATE TABLE IF NOT EXISTS books (
                                     id BIGSERIAL PRIMARY KEY,
                                     titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    fecha_publicacion DATE NOT NULL,
    categoria VARCHAR(100) NOT NULL,
    isbn VARCHAR(13) UNIQUE NOT NULL,
    valoracion INTEGER CHECK (valoracion >= 1 AND valoracion <= 5),
    visible BOOLEAN NOT NULL DEFAULT true,
    stock INTEGER NOT NULL DEFAULT 0 CHECK (stock >= 0),
    precio DECIMAL(10,2) NOT NULL CHECK (precio >= 0),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

-- Insertar datos de prueba
INSERT INTO books (titulo, autor, fecha_publicacion, categoria, isbn, valoracion, visible, stock, precio) VALUES
('Cien años de soledad', 'Gabriel García Márquez', '1967-05-30', 'Realismo mágico', '9780060883287', 5, true, 25, 19.99),
('El principito', 'Antoine de Saint-Exupéry', '1943-04-06', 'Literatura infantil', '9780156012195', 5, true, 30, 12.99),
('1984', 'George Orwell', '1949-06-08', 'Distopía', '9780451524935', 4, true, 20, 15.99),
('Don Quijote de la Mancha', 'Miguel de Cervantes', '1605-01-16', 'Clásicos', '9780060934347', 5, true, 15, 24.99),
('Matar a un ruiseñor', 'Harper Lee', '1960-07-11', 'Drama', '9780061120084', 4, true, 18, 14.99),
('Libro oculto de prueba', 'Autor Secreto', '2023-01-01', 'Misterio', '9781234567890', 3, false, 5, 9.99);
