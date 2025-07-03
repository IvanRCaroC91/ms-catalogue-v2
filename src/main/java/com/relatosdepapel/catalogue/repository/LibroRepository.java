package com.relatosdepapel.catalogue.repository;

import com.relatosdepapel.catalogue.entity.LibroEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.annotations.Query;
import java.util.List;

public interface LibroRepository extends ElasticsearchRepository<LibroEntity, String> {

    List<LibroEntity> findByTituloContaining(String titulo);

    List<LibroEntity> findByAutorContaining(String autor);

    List<LibroEntity> findByCategoria(String categoria);

    List<LibroEntity> findByVisible(Boolean visible);

    List<LibroEntity> findByPrecioBetween(Double min, Double max);

    List<LibroEntity> findByValoracionGreaterThanEqual(Integer valoracion);

    @Query("""
    {
      "multi_match": {
        "query": "?0",
        "type": "bool_prefix",
        "fields": [
          "titulo",
          "titulo._2gram",
          "titulo._3gram"
        ]
      }
    }
    """)
    List<LibroEntity> autocompleteTitulo(String query);

    @Query("""
    {
      "multi_match": {
        "query": "?0",
        "fields": [ "titulo", "contenido" ]
      }
    }
    """)
    List<LibroEntity> fullTextSearch(String texto);
}
