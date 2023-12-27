package main.java.com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Habilidad;

public interface HabilidadRepository extends JpaRepository<Habilidad, Integer> {
    
}
