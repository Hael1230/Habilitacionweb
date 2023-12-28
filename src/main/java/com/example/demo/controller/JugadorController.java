package main.java.com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.entities.*;
import com.example.demo.repository.*;

import main.java.com.example.demo.entities.Jugador;
import main.java.com.example.demo.repository.HabilidadRepository;
import main.java.com.example.demo.repository.JugadorRepository;

@RestController
@RequestMapping("/jugadores")

public class JugadorController {

    @Autowired
	JugadorRepository jugadorRepository;
	@Autowired
	HabilidadRepository habilidadRepository;

    @PostMapping("/crear")
	public ResponseEntity<Jugador> crearJugador(@RequestBody Jugador nuevoJugador) {
	    Jugador jugadorExistente = jugadorRepository.findByEmail(nuevoJugador.getEmail());

	    if (jugadorExistente != null) {
	        return ResponseEntity.badRequest().body(null);
	    }

	    Jugador jugadorCreado = jugadorRepository.save(nuevoJugador);
	    return ResponseEntity.status(HttpStatus.CREATED).body(jugadorCreado);
	}

    @GetMapping("/lista")
	public ResponseEntity<List<Jugador>> listarJugadores() {
	    List<Jugador> jugadores = jugadorRepository.findAll();

	    if (jugadores.isEmpty()) {
	        return ResponseEntity.noContent().build();
	    }

	    return ResponseEntity.ok(jugadores);
	}

    @PostMapping("/login")
	public ResponseEntity<Map<String, String>> obtenerNUUIDPorEmail(@RequestParam String email) {
	    Jugador jugador = jugadorRepository.findByEmail(email);

	    if (jugador == null) {
	        return ResponseEntity.notFound().build();
	    }

	    Map<String, String> response = new HashMap<>();
	    response.put("nuuid", jugador.getNuuid());

	    return ResponseEntity.ok(response);
	}

    @GetMapping("/{clase}")
	public ResponseEntity<List<Jugador>> listarJugadorPorClase(@PathVariable("clase") String claseNuuid) {
	    Clase clase = claserepository.findByClase(claseNuuid);

	    if (clase == null) {
	        return ResponseEntity.notFound().build();
	    }

	    List<Jugador> pjugadores = clase.getJugadores();

	    if (pokemones.isEmpty()) {
	        return ResponseEntity.notFound().build();
	    }

	    return ResponseEntity.ok(jugadores);
	}

    @PostMapping
    public ResponseEntity<Jugador> registrarJugador(@RequestBody Jugador nuevoJugador) {
        if (nuevoJugador.getNuuid() != null && jugadorRepository.findByNuuid(nuevoJugador.getNuuid()) != null) {
            return ResponseEntity.badRequest().body(null); 
        }

        Jugador jugadorGuardado = jugadorRepository.save(nuevoJugador);
        return ResponseEntity.ok(jugadorGuardado);
    }

    @GetMapping("/{nuuid}/habilidades")
	 public ResponseEntity<List<Jugador>> listarHabilidadesDeJugador(@PathVariable("nuuid") String nuuid) {
	     Jugador jugador = jugadorRepository.findByNuuid(nuuid);

	     if (jugador == null) {
	         return ResponseEntity.notFound().build();
	     }

	     List<Habilidad> habilidades = jugador.getHabilidades();

	     return ResponseEntity.ok(habilidades);
	 }

     @PostMapping("/{nuuid}/habilidades/{nuuid}")
	 public ResponseEntity<String> agregarHabilidadAJugador(
	         @PathVariable("nuuid") String jugadorNuuid,
	         @PathVariable("nuuid") String habilidadNuuid) {

	     Jugador jugador = jugadorRepository.findByNuuid(jugadorNuuid);
	     Habilidad habilidad = habilidadRepository.findByNuuid(habilidadNuuid);

	     if (jugador == null || habilidad == null) {
	         return ResponseEntity.notFound().build();
	     }

	     List<Habilidad> habilidadesJugador = jugador.getHabilidades();

	     boolean yaAsociado = HabilidadesJugador.contains(habilidad);

	     if (yaAsociado) {
	         return ResponseEntity.badRequest().body("La habilidad ya fue registrada al jugador");
	     }

	     habilidadesJugadordor.add(habilidad);
	     jugadorRepository.save(jugador);

	     return ResponseEntity.ok().build();
	 }
     
     @PutMapping("/{nuuid}")
     public ResponseEntity<Jugador> actualizarJugadorPorNuuid(@PathVariable String nuuid, @RequestBody Jugador jugadorActualizado) {
         Optional<Jugador> optionalJugador = jugadorRepository.findByNuuid(nuuid);
 
         if (optionalJugador.isPresent()) {
             Jugador jugador = optionalJugador.get();
 
             jugador.setNombre(jugadorActualizado.getNombre());
             jugador.setFechaNacimiento(jugadorActualizado.getFechaNacimiento());
             jugador.setDescripcion(jugadorActualizado.getDescripcion());
             jugador.setEmail(jugadorActualizado.getEmail());
             jugador.setClase(jugadorActualizado.getClase());
             jugador.setGenero(jugadorActualizado.getGenero());
             jugador.setRango(jugadorActualizado.getRango());
             jugador.setNuuid(jugadorActualizado.getNuuid());
 
             Jugador jugadorGuardado = jugadorRepository.save(jugador);
 
             return ResponseEntity.ok(jugadorGuardado);
         } else {
             return ResponseEntity.notFound().build();
         }
     }

}
