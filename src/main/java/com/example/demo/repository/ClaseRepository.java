package main.java.com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Clase;

public interface ClaseRepository extends JpaRepository<Clase, Integer>  {
    
}
