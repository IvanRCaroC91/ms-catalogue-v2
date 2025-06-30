package com.relatosdepapel.catalogue.controller;

import com.relatosdepapel.catalogue.entity.LibroEntity;
import com.relatosdepapel.catalogue.service.LibroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Controlador REST que expone endpoints para gestionar libros.
 * Ruta base: /api/libros
 */
@RestController
@RequestMapping("/api/libros") // Ruta raíz del controlador
@RequiredArgsConstructor // Genera constructor con dependencias requeridas (LibroService)
public class LibroController {

    private final LibroService libroService;

    /**
     * GET /api/libros
     * Retorna todos los libros.
     * Permite filtrar libros por título, autor o rango de fechas.
     */
    @GetMapping
    public List<LibroEntity> obtenerYFiltrarLibros(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String autor,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) Integer valoracion,
            @RequestParam(required = false) LocalDate fechaInicio,
            @RequestParam(required = false) LocalDate fechaFin
    ) {
        return libroService.buscarLibros(titulo, autor, fechaInicio, fechaFin, categoria, valoracion);
    }

    /**
     * GET /api/libros/{id}
     * Retorna un libro por su ID.
     */
    @GetMapping("/{id}")
    public LibroEntity obtenerPorId(@PathVariable Long id) {
        return libroService.obtenerPorId(id);
    }

    /**
     * POST /api/libros
     * Crea un nuevo libro.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LibroEntity crearLibro(@RequestBody LibroEntity libro) {
        return libroService.guardarLibro(libro);
    }

    /**
     * PUT /api/libros/{id}
     * Reemplaza todos los datos de un libro existente.
     */
    @PutMapping("/{id}")
    public LibroEntity actualizarLibro(@PathVariable Long id, @RequestBody LibroEntity libroActualizado) {
        return libroService.actualizarLibroCompleto(id, libroActualizado);
    }

    /**
     * PATCH /api/libros/{id}/stock
     * Actualiza únicamente el stock de un libro.
     */
    @PatchMapping("/{id}/stock")
    public LibroEntity actualizarStock(@PathVariable Long id, @RequestParam Integer cantidad) {
        return libroService.actualizarStock(id, cantidad);
    }

    /**
     * DELETE /api/libros/{id}
     * Elimina un libro por ID.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarLibro(@PathVariable Long id) {
        libroService.eliminarLibro(id);
    }
}
