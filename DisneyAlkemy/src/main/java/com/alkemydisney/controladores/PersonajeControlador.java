package com.alkemydisney.controladores;

import com.alkemydisney.entidades.Personaje;
import com.alkemydisney.servicios.PersonajeServicio;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/personajes")
public class PersonajeControlador {

    @Autowired
    private PersonajeServicio personajeServicio;

    @GetMapping("/lista")
    public String listar(ModelMap modelo) {
        List<Personaje> personajes = personajeServicio.listar();
        modelo.put("personajes", personajes);
        return "";

    }

    @PostMapping("/crear")
    public String crear(@RequestParam String nombre, @RequestParam Integer edad, @RequestParam Integer peso, @RequestParam String historia, @RequestParam MultipartFile imagen) throws IOException {
        personajeServicio.crear(nombre, edad, peso, historia, imagen);
        return "";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable String id, ModelMap modelo) throws Exception {
        Personaje personaje = personajeServicio.buscarPorId(id);
        modelo.put("personaje", personaje);
        return "";

    }

    @PostMapping("/editar")
    public String editar(@RequestParam String id, @RequestParam String nombre, @RequestParam Integer edad, @RequestParam Integer peso, @RequestParam String historia, @RequestParam MultipartFile imagen) throws Exception {
        personajeServicio.editar(id, nombre, edad, peso, historia, imagen);
        return "";
    }

    @GetMapping("/eliminar/{id}")
    public String darBaja(@PathVariable String id) throws Exception {
        personajeServicio.darBaja(id);
        return "";
    }

    @GetMapping("/detalle/{id}")
    public String detalle(@PathVariable String id, ModelMap modelo) {
        try {
            Personaje personaje = personajeServicio.buscarPorId(id);
            modelo.put("personaje", personaje);
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
            return "error";

        }
        return "";
    }

}
