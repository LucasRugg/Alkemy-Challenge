
package com.alkemydisney.controladores;

import com.alkemydisney.entidades.Usuario;
import com.alkemydisney.servicios.UsuarioServicio;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class PortalControlador {
    @Autowired
    private UsuarioServicio usuarioServicio;
    @GetMapping("/")
    public String index(){
        return "index.html";
        
    }
    
    @GetMapping("/register")
    public String registrar(){
        return "";
    }
    
    @PostMapping("/auth/register")
    public String registro(ModelMap modelo,@RequestParam String nombre,@RequestParam String password,@RequestParam String email){
        try {
            usuarioServicio.registrar(nombre, password, email);
            modelo.put("exito","Usuario registrado con exito");
            return "";
        } catch (Exception e) {
            return "";
        }
        
    }
    @GetMapping("/auth/login")
    public String login(ModelMap modelo, @RequestParam(required = false)String error){
        if (error != null) {
            modelo.put("error","Usuario o contraseña incorrectos");
        }
        return "";
    }
    
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/inicio")
    public String inicio(HttpSession sesion){
        Usuario logueado = (Usuario) sesion.getAttribute("usuariosession");
        
        if (logueado.getRol().toString().equals("ADMIN")) {
            return "redirect:/admin/dashboard";
            
        }
        
        return "inicio.html";
    }
    
  
}
