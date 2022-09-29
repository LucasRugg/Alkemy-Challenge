package com.alkemydisney.servicios;

import com.alkemydisney.entidades.Genero;
import com.alkemydisney.entidades.Pelicula;
import com.alkemydisney.repositorios.GeneroRepositorio;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GeneroServicio {

    @Autowired
    private GeneroRepositorio generoRepositorio;

    @Transactional(rollbackFor = {Exception.class})
    public void crear(String nombre, List<Pelicula>peliculas) throws IOException {

        Genero genero = new Genero();

        genero.setNombre(nombre);
        genero.setPeliculas(peliculas);
        genero.setAlta(true);

        generoRepositorio.save(genero);

    }

    @Transactional(readOnly = true)
    public List<Genero> listar() {
        List<Genero> generos = generoRepositorio.findAll();
        List<Genero> generoAlta = new ArrayList();
        for (Genero genero : generos) {
            if (genero.getAlta()) {
                generoAlta.add(genero);

            }
        }
        return generoAlta;

    }

    @Transactional(readOnly = true)
    public Genero buscarPorId(String id) {
        Optional<Genero> respuesta = generoRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Genero genero = respuesta.get();
            return genero;
        } else {
            throw new Error("No existe ese genero");
        }

    }

    @Transactional(rollbackFor = {Exception.class})
    public Genero darBaja(String id) {
        Genero genero = buscarPorId(id);
        genero.setAlta(false);
        return genero;
    }

    @Transactional(rollbackFor = {Exception.class})
    public Genero editar(String id, String nombre, List<Pelicula>peliculas) {
        Genero genero = buscarPorId(id);
        genero.setNombre(nombre);
        genero.setPeliculas(peliculas);
        generoRepositorio.save(genero);
        return genero;

    }
}
