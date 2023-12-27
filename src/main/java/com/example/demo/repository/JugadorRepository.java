package main.java.com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Jugador;

public interface JugadorRepository extends JpaRepository<Jugador, Integer> {
    
}
