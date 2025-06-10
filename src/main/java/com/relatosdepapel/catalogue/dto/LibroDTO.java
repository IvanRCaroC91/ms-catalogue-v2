package com.relatosdepapel.catalogue.dto;

import com.relatosdepapel.catalogue.entity.LibroEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * DTO (Data Transfer Object) para representar una vista resumida de un libro.
 * Útil para listados o respuestas parciales donde no se requiere toda la información.
 */
@Data
public class LibroDTO {
    private String titulo;
    private String autor;
    private String categoria;
    private BigDecimal precio;

    /**
     * Convierte el DTO en una entidad Libro.
     * Solo se llenan los campos disponibles en el DTO.
     */
    public LibroEntity toEntity() {
        return LibroEntity.builder()
                .titulo(this.titulo)
                .autor(this.autor)
                .categoria(this.categoria)
                .precio(this.precio)
                .build();
    }

    /**
     * Crea un DTO a partir de una entidad Libro.
     */
    public static LibroDTO fromEntity(LibroEntity libro) {
        LibroDTO dto = new LibroDTO();
        dto.setTitulo(libro.getTitulo());
        dto.setAutor(libro.getAutor());
        dto.setCategoria(libro.getCategoria());
        dto.setPrecio(libro.getPrecio());
        return dto;
    }
}
