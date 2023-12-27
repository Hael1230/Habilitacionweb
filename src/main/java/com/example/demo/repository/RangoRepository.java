package main.java.com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Rango;

public interface RangoRepository extends JpaRepository<Rango, Integer> {
    
}
