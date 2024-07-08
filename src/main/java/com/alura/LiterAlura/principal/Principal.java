package com.alura.LiterAlura.principal;

import com.alura.LiterAlura.model.Datos;
import com.alura.LiterAlura.model.DatosAutor;
import com.alura.LiterAlura.model.DatosLibros;
import com.alura.LiterAlura.repository.LibroRepository;
import com.alura.LiterAlura.service.ConsumoAPI;
import com.alura.LiterAlura.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner scanner = new Scanner(System.in);
    private List<DatosLibros> librosEncontrados = new ArrayList<>();
    private List<DatosAutor> autoresEncontrados = new ArrayList<>();
    private LibroRepository repositorio;


    public Principal(LibroRepository repository) {
        this.repositorio = repository;
    }


    public void muestraElMenu() {
        var opcion = -1;

        while (opcion != 0) {
            System.out.println("""
                    1-Buscar libros por titulo
                    2-Mostrar lista de titulos buscados
                    3-Mostrar lista de autores
                    4-Mostrar autores vivos en un año
                    0-Salir""");
            opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer del Scanner

            switch (opcion) {
                case 1:
                    buscarLibrosPorNombre();
                    break;
                case 2:
                    mostrarListaDeLibros();
                    break;
                case 3:
                    mostrarListaDeAutores();
                    break;

                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        }
    }

    public void buscarLibrosPorNombre() {
        System.out.println("Ingrese el nombre del libro");
        var tituloLibro = scanner.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);

        Optional<DatosLibros> libroBuscado = datosBusqueda.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();

        if (libroBuscado.isPresent()) {
            DatosLibros libroEncontrado = libroBuscado.get();
            librosEncontrados.add(libroEncontrado);

            // Agregar autores del libro encontrados a la lista de autores encontrados
            autoresEncontrados.addAll(libroEncontrado.autores());

            System.out.println("Libro Encontrado ");
            System.out.println(libroBuscado);

        } else {
            System.out.println("Libro no encontrado");
        }
    }

    public void mostrarListaDeLibros() {
        if (librosEncontrados.isEmpty()) {
            System.out.println("No se han encontrado libros.");
        } else {
            System.out.println("Lista de libros encontrados:");
            for (DatosLibros libro : librosEncontrados) {
                System.out.println(libro);
            }
        }
    }

    public void mostrarListaDeAutores() {
        if (autoresEncontrados.isEmpty()) {
            System.out.println("No se han encontrado autores.");
        } else {
            System.out.println("Lista de autores encontrados:");
            for (DatosAutor autor : autoresEncontrados) {
                System.out.println(autor.nombre());
            }
        }
    }

}