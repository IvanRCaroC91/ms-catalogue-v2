package com.relatosdepapel.catalogue.repository;

// Importación de la entidad Libro
import com.relatosdepapel.catalogue.entity.LibroEntity;

// Importaciones necesarias para el uso de Spring Data JPA
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    /**
     * Consulta personalizada con JPQL que permite buscar libros de forma avanzada.
     * Esta búsqueda permite combinar varios filtros opcionales:
     * - Título parcial (ignora mayúsculas)
     * - Autor parcial (ignora mayúsculas)
     * - Rango de fechas de publicación (fechaInicio y fechaFin)
     *
     * Si algún parámetro es null, se ignora ese filtro.
     */
    @Query("SELECT l FROM LibroEntity l WHERE " +
            "(:titulo IS NULL OR LOWER(l.titulo) LIKE LOWER(CONCAT('%', :titulo, '%'))) AND " + //Usar LIKE con LOWER() garantiza búsquedas insensibles a mayúscula
            "(:autor IS NULL OR LOWER(l.autor) LIKE LOWER(CONCAT('%', :autor, '%'))) AND " + //Los parámetros :titulo, :autor, etc., pueden ser null para hacer búsquedas flexibles.
            "(:fechaInicio IS NULL OR l.fechaPublicacion >= :fechaInicio) AND " +
            "(:fechaFin IS NULL OR l.fechaPublicacion <= :fechaFin)")
    List<LibroEntity> buscarLibros(
            @Param("titulo") String titulo,             // Parámetro de búsqueda por título parcial
            @Param("autor") String autor,               // Parámetro de búsqueda por autor parcial
            @Param("fechaInicio") LocalDate fechaInicio, // Fecha mínima de publicación
            @Param("fechaFin") LocalDate fechaFin        // Fecha máxima de publicación
    );
}
