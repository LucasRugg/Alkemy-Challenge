
package com.alkemydisney.repositorios;

import com.alkemydisney.entidades.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  GeneroRepositorio extends JpaRepository<Genero, String> {
    
}