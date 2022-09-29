package com.alkemydisney.controladores;

import com.alkemydisney.entidades.Pelicula;
import com.alkemydisney.entidades.Personaje;
import com.alkemydisney.servicios.PeliculaServicio;
import com.alkemydisney.servicios.PersonajeServicio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/peliculas")
public class PeliculaControlador {

    @Autowired
    private PeliculaServicio pelicualServicio;
    
    @Autowired
    private PersonajeServicio personajeServicio;

    @GetMapping("/lista")
    public String listar(ModelMap modelo) {
        List<Pelicula> peliculas = pelicualServicio.listar();
        modelo.put("peliculas", peliculas);
        return "";
    }

    @PostMapping("/crear")
    public String crear(@RequestParam String titulo, @RequestParam Date fechaDeCreacion, @RequestParam Integer calificacion, ArrayList<Personaje> personajes) {
        pelicualServicio.crear(titulo, fechaDeCreacion, calificacion, personajes);
        return "";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable String id, ModelMap modelo) throws Exception {
        Pelicula pelicula = pelicualServicio.buscarPorId(id);
        modelo.put("pelicula", pelicula);
        return "";
    }

    @PostMapping("/editar")
    public String editar(@RequestParam String titulo, @RequestParam List<Personaje> personajes, @RequestParam Date fechaDeCreacion, @RequestParam Integer calificacion) throws Exception {
        pelicualServicio.editar(titulo, titulo, fechaDeCreacion, calificacion, personajes);
        return "";

    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id) throws Exception {
        pelicualServicio.darBaja(id);
        return "";

    }

    @GetMapping("/detalle/{id}")
    public String detalle(@PathVariable String id, ModelMap modelo) {
        try {
            Pelicula pelicula = pelicualServicio.buscarPorId(id);
            modelo.put("pelicula", pelicula);
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
            return "error";

        }
        return "";
    }
    
    @PostMapping("/{idPelicula}/personaje/{idPersonaje}")
    public String agregarPersonaje(@PathVariable String idPelicula,@PathVariable String idPersonaje) throws Exception{
        Pelicula pelicula = pelicualServicio.buscarPorId(idPelicula);
        Personaje personaje = personajeServicio.buscarPorId(idPersonaje);
        List<Personaje> pers = pelicula.getPersonajes();
        pers.add(personaje);
        pelicula.setPersonajes(pers);
        return "";
        
    }

}
