package prueba.pruebatecnica.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntradaDatosDTO {
    private Integer tipo_identificacion;
    @NotBlank
    private String nro_identificacion;
    private Integer valor_asegurado;

}
