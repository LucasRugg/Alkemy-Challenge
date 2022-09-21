package com.alkemydisney.controladores;

import com.alkemydisney.entidades.Genero;
import com.alkemydisney.servicios.GeneroServicio;
import java.io.IOException;
import java.util.HashSet;
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
@RequestMapping("/genero")
public class GeneroControlador {

    @Autowired
    private GeneroServicio generoServicio;

    @GetMapping("/lista")
    public String listar(ModelMap modelo) {

        List<Genero> generos = generoServicio.listar();

        modelo.put("generos", generos);
        return "";

    }

    @PostMapping("/crear")
    public String crear(@RequestParam String genero, ModelMap modelo) throws IOException {

        generoServicio.crear(genero);

        return "";

    }

    @GetMapping("/eliminar/{id}")
    public String darBaja(@PathVariable String id) {
        generoServicio.darBaja(id);
        return "";

    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable String id, ModelMap modelo) {
        Genero genero = generoServicio.buscarPorId(id);
        modelo.put("genero", genero);
        return "";

    }

    @PostMapping("/editar")
    public String editar(@RequestParam String id, @RequestParam String nombre) {
        try {
            generoServicio.editar(id, nombre);

        } catch (Exception e) {
        }

        return "";

    }

}
