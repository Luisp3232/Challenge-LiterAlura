package com.alura.LiterAlura.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libros")

public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;
    private Double numeroDeDescargas;

    @Enumerated(EnumType.STRING)
    private List<DatosAutor> autores;
    private List<String> idiomas;


    public Libro(DatosLibros datosLibros){
        this.titulo = datosLibros.titulo();
        this.autores =datosLibros.autores();
        this.numeroDeDescargas = datosLibros.numeroDeDescargas();
        this.idiomas = datosLibros.idiomas();

    } public Long getId() {
        return id;
    }

    public List<DatosAutor> getAutores() {
        return autores;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public void setAutores(List<DatosAutor> autores) {
        this.autores = autores;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", numeroDeDescargas=" + numeroDeDescargas +
                ", autores=" + autores +
                ", idiomas=" + idiomas +
                '}';
    }
}
