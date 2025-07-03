package com.relatosdepapel.catalogue.service;

import com.relatosdepapel.catalogue.entity.LibroEntity;
import com.relatosdepapel.catalogue.repository.LibroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class LibroService {

    private final LibroRepository libroRepository;

    public List<LibroEntity> buscarLibros(String titulo, String autor, LocalDate fechaInicio, LocalDate fechaFin, String categoria, Integer valoracion) {
        if (titulo != null && !titulo.isEmpty()) {
            return libroRepository.findByTituloContaining(titulo);
        }
        if (autor != null && !autor.isEmpty()) {
            return libroRepository.findByAutorContaining(autor);
        }
        if (categoria != null && !categoria.isEmpty()) {
            return libroRepository.findByCategoria(categoria);
        }
        if (valoracion != null) {
            return libroRepository.findByValoracionGreaterThanEqual(valoracion);
        }
        // Si necesitas filtrar por fechas, implementa una query personalizada.
        // Aquí devolvemos todos los libros si no hay filtro.
        return StreamSupport
                .stream(libroRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public LibroEntity obtenerPorId(String id) {
        return libroRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Libro no encontrado con ID: " + id));
    }

    public LibroEntity guardarLibro(LibroEntity libro) {
        return libroRepository.save(libro);
    }

    public LibroEntity actualizarLibroCompleto(String id, LibroEntity libroActualizado) {
        LibroEntity libro = libroRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se puede actualizar. Libro no encontrado con ID: " + id));

        libro.setTitulo(libroActualizado.getTitulo());
        libro.setAutor(libroActualizado.getAutor());
        libro.setFechaPublicacion(libroActualizado.getFechaPublicacion());
        libro.setCategoria(libroActualizado.getCategoria());
        libro.setIsbn(libroActualizado.getIsbn());
        libro.setValoracion(libroActualizado.getValoracion());
        libro.setVisible(libroActualizado.getVisible());
        libro.setStock(libroActualizado.getStock());
        libro.setPrecio(libroActualizado.getPrecio());
        libro.setFechaCreacion(libroActualizado.getFechaCreacion());
        libro.setFechaActualizacion(libroActualizado.getFechaActualizacion());

        return libroRepository.save(libro);
    }

    public LibroEntity actualizarStock(String id, Integer cantidad) {
        LibroEntity libro = libroRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se puede actualizar stock. Libro no encontrado con ID: " + id));

        libro.setStock(libro.getStock() - cantidad);
        return libroRepository.save(libro);
    }

    public void eliminarLibro(String id) {
        LibroEntity libro = libroRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se puede eliminar. Libro no encontrado con ID: " + id));
        libroRepository.delete(libro);
    }

    public List<LibroEntity> autocompletarTitulo(String query) {
        return libroRepository.autocompleteTitulo(query);
    }

    public List<LibroEntity> busquedaFullText(String texto) {
        return libroRepository.fullTextSearch(texto);
    }

    public List<LibroEntity> listarTodos() {
        return StreamSupport
                .stream(libroRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
