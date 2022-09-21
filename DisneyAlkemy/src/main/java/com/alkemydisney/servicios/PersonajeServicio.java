package com.alkemydisney.servicios;

import com.alkemydisney.entidades.Pelicula;
import com.alkemydisney.entidades.Personaje;
import com.alkemydisney.repositorios.PersonajeRepositorio;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PersonajeServicio {

    @Autowired
    private PersonajeRepositorio personajeRepositorio;

    @Transactional(rollbackFor = {Exception.class})
    public void crear(String nombre, Integer edad, Integer peso, String historia, MultipartFile imagen) throws IOException {
        Personaje personaje = new Personaje();
        personaje.setNombre(nombre);
        personaje.setEdad(edad);
        personaje.setHistoria(historia);
        personaje.setImagen(imagen.getBytes());
        personaje.setPeso(peso);
        personaje.setAlta(true);

        personajeRepositorio.save(personaje);
    }

    @Transactional(readOnly = true)
    public Personaje buscarPorId(String id) throws Exception {
        Optional<Personaje> respuesta = personajeRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Personaje personaje = respuesta.get();
            return personaje;
        } else {
            throw new Exception("No existe ese personaje");
        }

    }

    @Transactional(rollbackFor = {Exception.class})
    public Personaje editar(String id, String nombre, Integer edad, Integer peso, String historia, MultipartFile imagen) throws Exception {

        Personaje personaje = buscarPorId(id);
        personaje.setNombre(nombre);
        personaje.setEdad(edad);
        personaje.setHistoria(historia);
        personaje.setImagen(imagen.getBytes());
        personaje.setPeso(peso);

        return personajeRepositorio.save(personaje);
    }

    @Transactional(rollbackFor = {Exception.class})
    public Personaje darBaja(String id) throws Exception {
        Personaje personaje = buscarPorId(id);

        personaje.setAlta(false);
        return personaje;
    }

    @Transactional(readOnly = true)
    public List<Personaje> listar() {
        List<Personaje> todos = personajeRepositorio.findAll();
        List<Personaje> altas = new ArrayList();
        for (Personaje todo : todos) {
            if (todo.getAlta()) {
                altas.add(todo);
            }
        }
        return altas;
    }

}
