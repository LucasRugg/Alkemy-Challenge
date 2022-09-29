package com.alkemydisney.servicios;

import com.alkemydisney.entidades.Pelicula;
import com.alkemydisney.entidades.Personaje;
import com.alkemydisney.repositorios.PeliculaRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PeliculaServicio {
    
    private PeliculaRepositorio peliculaRepositorio;
    
    @Transactional(rollbackFor = {Exception.class})
    public void crear(String titulo, Date fechaDeCreacion, Integer calificacion, ArrayList<Personaje> personajes) {
        
        Pelicula pelicula = new Pelicula();
        
        pelicula.setCalificacion(calificacion);
        pelicula.setFechaDeCreacion(fechaDeCreacion);
        pelicula.setTitulo(titulo);
        pelicula.setAlta(true);
        pelicula.setPersonajes(personajes);
        
        peliculaRepositorio.save(pelicula);
        
    }
    
    @Transactional(readOnly = true)
    public Pelicula buscarPorId(String id) throws Exception {
        Optional<Pelicula> respuesta = peliculaRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Pelicula pelicula = respuesta.get();
            return pelicula;
        } else {
            throw new Exception("No existe ese libro");
        }
    }
    
    public Pelicula editar(String id, String titulo, Date fechaDeCreacion, Integer calificacion, List<Personaje> personajes) throws Exception {
        Pelicula pelicula = buscarPorId(id);
        
        pelicula.setCalificacion(calificacion);
        pelicula.setFechaDeCreacion(fechaDeCreacion);
        pelicula.setTitulo(titulo);
        pelicula.setPersonajes(personajes);
        
        return peliculaRepositorio.save(pelicula);
    }
    
    @Transactional(rollbackFor = {Exception.class})
    public Pelicula darBaja(String id) throws Exception {
        Pelicula pelicula = buscarPorId(id);
        pelicula.setAlta(false);
        return pelicula;
    }
    
    @Transactional(readOnly = true)
    public List<Pelicula> listar() {
        List<Pelicula> todas = peliculaRepositorio.findAll();
        List<Pelicula> altas = new ArrayList();
        
        for (Pelicula toda : todas) {
            if (toda.getAlta()) {
                altas.add(toda);
            }
        }
        return altas;
        
    }
    
}
