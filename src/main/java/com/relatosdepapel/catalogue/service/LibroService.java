package com.relatosdepapel.catalogue.service;

// Importación de la entidad Libro
import com.relatosdepapel.catalogue.entity.LibroEntity;

// Importación del repositorio que interactúa con la base de datos
import com.relatosdepapel.catalogue.repository.LibroRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * Servicio que contiene la lógica de negocio para las operaciones relacionadas con libros.
 * Está marcado con @Service para que Spring lo gestione como un componente de servicio.
 */
@Service

/**
 * Genera automáticamente un constructor con los campos marcados como `final`.
 * Evita la necesidad de escribir manualmente la inyección de dependencias.
 */
@RequiredArgsConstructor

/**
 * Marca toda la clase como transaccional.
 * Cualquier metodo fallido revierte automáticamente los cambios a nivel de base de datos.
 */
@Transactional
public class LibroService {

    // Inyección del repositorio para acceder a la base de datos
    private final LibroRepository libroRepository;

    /**
     * Devuelve todos los libros si no hay filtros,
     * o realiza una búsqueda aplicando los filtros proporcionados.
     */
    public List<LibroEntity> buscarLibros(String titulo, String autor, LocalDate fechaInicio, LocalDate fechaFin, String categoria, Integer valoracion) {
        // Si todos los parámetros están vacíos, devuelve todos
        boolean sinFiltros =
                (titulo == null || titulo.isBlank()) &&
                (autor == null || autor.isBlank()) &&
                (categoria == null || categoria.isBlank()) &&
                valoracion == null &&
                fechaInicio == null &&
                fechaFin == null;


        if (sinFiltros) {
            return libroRepository.findAll();
        }

        // Si hay al menos un filtro, ejecutar búsqueda filtrada
        return libroRepository.buscarLibros(titulo, autor, categoria, valoracion, fechaInicio, fechaFin);
    }



    /** Retorna un libro por ID o lanza excepción si no existe **/
    public LibroEntity obtenerPorId(Long id) {
        return libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado con ID: " + id));
    }

    /**
     * Guarda un nuevo libro o actualiza uno existente.
     * Spring Data JPA decide si insertar o actualizar según si el ID existe.
     */
    public LibroEntity guardarLibro(LibroEntity libro) {
        return libroRepository.save(libro);
    }


    /** Actualiza todos los campos de un libro existente **/
    public LibroEntity actualizarLibroCompleto(Long id, LibroEntity libroActualizado) {
        LibroEntity libro = obtenerPorId(id);
        libro.setTitulo(libroActualizado.getTitulo());
        libro.setAutor(libroActualizado.getAutor());
        libro.setFechaPublicacion(libroActualizado.getFechaPublicacion());
        libro.setCategoria(libroActualizado.getCategoria());
        libro.setIsbn(libroActualizado.getIsbn());
        libro.setValoracion(libroActualizado.getValoracion());
        libro.setVisible(libroActualizado.getVisible());
        libro.setStock(libroActualizado.getStock());
        libro.setPrecio(libroActualizado.getPrecio());
        return libroRepository.save(libro);
    }

    /**
     * Actualiza el stock de un libro restando una cantidad específica.
     * Si el libro no existe, lanza una excepción.
     */
    public LibroEntity actualizarStock(Long id, Integer cantidad) {

        LibroEntity libro = libroRepository.findById(id).orElseThrow(); // Busca el libro por ID, o lanza excepción si no se encuentra
        libro.setStock(libro.getStock() - cantidad); // Resta la cantidad al stock actual
        return libroRepository.save(libro); // Guarda los cambios en la base de datos
    }

    /** Elimina un libro por ID **/
    public void eliminarLibro(Long id) {
        libroRepository.deleteById(id);
    }
}
