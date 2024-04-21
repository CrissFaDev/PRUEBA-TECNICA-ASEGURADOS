package prueba.pruebatecnica.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SalidaDatosDTO {
    @NotEmpty
    private Integer tipo_identificacion;
    @NotBlank
    private String nro_identificacion;
    @Size(min = 1, message = "el valor debe ser mayor a 0")
    private Integer valor_asegurado;

    private List<LiquidacionDTO> liquidacion;

    private Integer valor_total;


}
