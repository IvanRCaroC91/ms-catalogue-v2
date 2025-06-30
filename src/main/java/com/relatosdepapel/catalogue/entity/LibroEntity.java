package com.relatosdepapel.catalogue.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

// Fechas para los campos de auditoría
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.math.BigDecimal;


/**
 * Entidad que representa un libro en el sistema.
 * Mapea la tabla 'libros' en la base de datos.
 */

@Entity // Marca esta clase como una entidad JPA (se guardará como una tabla en la base de datos)
@Table(name = "libros") // Nombre de la tabla en la base de datos
@Data // Lombok: genera automáticamente getters, setters, toString, equals y hashCode
@NoArgsConstructor // Lombok: genera un constructor sin argumentos
@AllArgsConstructor // Lombok: genera un constructor con todos los argumentos
@Builder // Lombok: permite usar el patrón Builder para crear objetos de esta clase

public class LibroEntity {

    @Id // Indica que este campo es la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Se genera automáticamente con autoincremento (para PostgreSQL)
    private Long id;

    @NotBlank(message = "El titulo del libro es obligatorio")// No puede ser null ni estar vacío
    @Size(max = 255) // Longitud máxima de 255 caracteres, Validacion a nivel de aplicacion, valida antes de que los datos lleguen a la base de datos.
    @Column(name = "titulo", nullable = false, length = 255) // Se mapea con la columna 'titulo', no puede ser null
    private String titulo;

    @NotBlank(message = "El autor es obligatorio")
    @Size(max = 255)
    @Column(name = "autor", nullable = false, length = 255)
    private String autor;

    @NotNull(message = "La fecha de publicación es obligatoria")
    @PastOrPresent(message = "La fecha no puede ser futura") // Solo fechas pasadas o presentes
    @Column(name = "fecha_publicacion", nullable = false)
    private LocalDate fechaPublicacion;

    @NotBlank(message = "La categoría es obligatoria")
    @Size(max = 100)
    @Column(name = "categoria", nullable = false)
    private String categoria;

    @NotBlank(message = "El ISBN es obligatorio")
    @Pattern(regexp = "^\\d{13}$", message = "ISBN inválido") // Valida que el ISBN tenga exactamente 13 dígitos
    @Column(name = "isbn", unique = true, length = 13) // Es único y con longitud máxima de 13
    private String isbn;

    @Min(1) @Max(5) // Valoración entre 1 y 5
    @Column(name = "valoracion")
    private Integer valoracion;

    @NotNull
    @Column(name = "visible", nullable = false)
    private Boolean visible = true; // Por defecto visible es true

    @Min(0) // No se permite stock negativo
    @NotNull
    @Column(name = "stock", nullable = false)
    private Integer stock = 0; // Stock inicial por defecto es 0

    @DecimalMin("0.0") // No se permite precio negativo
    @NotNull
    @Column(name = "precio", precision = 10, scale = 2) // Permite precios con 2 decimales (hasta 99999999.99)
    private BigDecimal precio;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion; // Fecha de creación

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion; // Fecha de última actualización

    @PrePersist // Metodo que se ejecuta justo antes de insertar un nuevo registro
    protected void prePersist() {
        this.fechaCreacion = LocalDateTime.now(); // Se registra la fecha de creación
        this.fechaActualizacion = LocalDateTime.now(); // Se registra también como fecha de última actualización
    }

    @PreUpdate // Metodo que se ejecuta justo antes de actualizar un registro
    protected void preUpdate() {
        this.fechaActualizacion = LocalDateTime.now(); // Se actualiza la fecha de modificación
    }

}
