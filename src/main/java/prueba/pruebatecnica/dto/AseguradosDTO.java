package prueba.pruebatecnica.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AseguradosDTO {
    private Integer IdAsegurado;
    private Integer tipoIdentificacion;
    private String numeroIdentificacion;
    private String apellidos;
    private String nombres;
    private Integer Sexo;
    private String fechaNacimiento;
}
