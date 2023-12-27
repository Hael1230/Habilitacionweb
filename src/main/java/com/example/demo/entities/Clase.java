package main.java.com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Clase implements Serializable {
    @Id
	@SequenceGenerator(name="clase_id_seq",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "clase_id_seq")
	private Integer id;
	private String descripcion;
	private String nuuid;
    @JsonIgnore
	@OneToMany(mappedBy = "clase", cascade = CascadeType.ALL)
    private List<Jugador> jugadores = new ArrayList<>();
}
