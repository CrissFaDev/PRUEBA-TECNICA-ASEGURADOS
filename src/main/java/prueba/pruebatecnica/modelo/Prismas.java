package prueba.pruebatecnica.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Prismas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_prismas;

    @Column (name = "codigo")
    private Integer codigo;

    @Column(name = "edad_minima")
    private Integer edadMinima;

    @Column(name = "edad_maxima")
    private Integer edadMaxima;

    @Column(name = "porcentaje_prima")
    private Double porcentajePrima;

}
