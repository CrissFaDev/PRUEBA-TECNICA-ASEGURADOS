package prueba.pruebatecnica.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Amparos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id_amparos;

    @Column(name = "nombre")
    private String nombre;

}
