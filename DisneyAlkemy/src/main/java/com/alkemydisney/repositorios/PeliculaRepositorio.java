
package com.alkemydisney.repositorios;

import com.alkemydisney.entidades.Genero;
import com.alkemydisney.entidades.Pelicula;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaRepositorio extends JpaRepository<Pelicula, String>{
    
    @Query("SELECT p FROM Pelicula p WHERE p.titulo = :titulo")
    public Pelicula buscarPorTitulo(@Param("titulo") String titulo);
    
    @Query("SELECT p FROM Pelicula p WHERE p.genero = :genero")
    public List<Pelicula> buscarPorGenero(@Param("genero") Genero genero);
    
    @Query("SELECT p FROM Pelicula p ORDER By p.fechaDeCreacion ASC")
    public List<Pelicula> ordenarPorFechaASC();    
    
    @Query("SELECT p FROM Pelicula p ORDER By p.fechaDeCreacion DESC")
    public List<Pelicula> ordenarPorFechaDESC();
}
