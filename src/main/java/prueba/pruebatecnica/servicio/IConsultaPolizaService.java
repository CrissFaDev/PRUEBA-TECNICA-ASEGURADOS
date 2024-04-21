package prueba.pruebatecnica.servicio;

import prueba.pruebatecnica.dto.EntradaDatosDTO;
import prueba.pruebatecnica.dto.SalidaDatosDTO;

import java.text.ParseException;

public interface IConsultaPolizaService {
    SalidaDatosDTO ConsultaPoliza(EntradaDatosDTO entradaDatosDTO) throws ParseException;
}
