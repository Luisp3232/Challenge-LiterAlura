package com.alura.LiterAlura.repository;
import com.alura.LiterAlura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro , Long> {

    Optional<Libro> findByTituloContainsIgnoreCase(String nombreLibro);

}
