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

    /**
     * Consulta personalizada con JPQL que permite buscar libros de forma avanzada.
     * Esta búsqueda permite combinar varios filtros opcionales:
     * - Título parcial (ignora mayúsculas)
     * - Autor parcial (ignora mayúsculas)
     * - Rango de fechas de publicación (fechaInicio y fechaFin)
     *
     * Si algún parámetro es null, se ignora ese filtro.
     */
    @Query(value = "SELECT * FROM libros l WHERE " +
            "(:titulo IS NULL OR l.titulo ILIKE CONCAT('%', :titulo, '%')) AND " +
            "(:autor IS NULL OR l.autor ILIKE CONCAT('%', :autor, '%')) AND " +
            "(:categoria IS NULL OR l.categoria ILIKE CONCAT('%', :categoria, '%')) AND " +
            "(:valoracion IS NULL OR l.valoracion = :valoracion) AND " +
            "(:fechaInicio IS NULL OR l.fecha_publicacion >= :fechaInicio) AND " +
            "(:fechaFin IS NULL OR l.fecha_publicacion <= :fechaFin)",
            nativeQuery = true)
    List<LibroEntity> buscarLibros(
            @Param("titulo") @Nullable String titulo,
            @Param("autor") @Nullable String autor,
            @Param("categoria") @Nullable String categoria,
            @Param("valoracion") @Nullable Integer valoracion,
            @Param("fechaInicio") @Nullable LocalDate fechaInicio,
            @Param("fechaFin") @Nullable LocalDate fechaFin
    );

}
