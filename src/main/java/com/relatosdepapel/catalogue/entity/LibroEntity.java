package com.relatosdepapel.catalogue.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Data
@Builder
@Document(indexName = "libros")
public class LibroEntity {
    @Id
    private String id;

    @Field(type = FieldType.Search_As_You_Type)
    private String titulo;

    @Field(type = FieldType.Keyword)
    private String autor;

    @Field(type = FieldType.Date)
    private LocalDate fechaPublicacion;

    @Field(type = FieldType.Keyword)
    private String categoria;

    @Field(type = FieldType.Keyword)
    private String isbn;

    @Field(type = FieldType.Integer)
    private Integer valoracion;

    @Field(type = FieldType.Boolean)
    private Boolean visible;

    @Field(type = FieldType.Integer)
    private Integer stock;

    @Field(type = FieldType.Double)
    private BigDecimal precio;

    @Field(type = FieldType.Date)
    private LocalDateTime fechaCreacion;

    @Field(type = FieldType.Date)
    private LocalDateTime fechaActualizacion;
}
