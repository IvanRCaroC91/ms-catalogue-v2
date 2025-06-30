package com.relatosdepapel.catalogue.repository;

// Importación de la entidad Libro
import com.relatosdepapel.catalogue.entity.LibroEntity;

// Importaciones necesarias para el uso de Spring Data JPA
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.util.List;

/**
 * Repositorio para operaciones de base de datos relacionadas con la entidad Libro.
 * Extiende JpaRepository, lo que proporciona métodos CRUD y consultas básicas automáticamente.
 */
public interface LibroRepository extends JpaRepository<LibroEntity, Long> {

    // Busca libros cuyo título contenga el texto proporcionado, sin importar mayúsculas o minúsculas.
    List<LibroEntity> findByTituloContainingIgnoreCase(String titulo);

    // Busca libros cuyo autor contenga el texto proporcionado, sin importar mayúsculas o minúsculas.
    List<LibroEntity> findByAutorContainingIgnoreCase(String autor);


}
