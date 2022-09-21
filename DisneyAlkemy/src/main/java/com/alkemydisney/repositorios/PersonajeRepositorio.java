package com.alkemydisney.repositorios;

import com.alkemydisney.entidades.Personaje;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonajeRepositorio extends JpaRepository<Personaje, String> {
    
  
    @Query("SELECT p FROM Personaje p WHERE p.nombre = :nombre")
    public Personaje buscarPorNombre(@Param("nombre") String nombre);
    
    @Query("SELECT p FROM Personaje p WHERE p.edad = :edad")
    public List<Personaje> buscarPorEdad(@Param("edad") Integer edad);
}
