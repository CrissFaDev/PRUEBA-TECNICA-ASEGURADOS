package prueba.pruebatecnica.controlador;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import prueba.pruebatecnica.dto.EntradaDatosDTO;
import prueba.pruebatecnica.dto.SalidaDatosDTO;
import prueba.pruebatecnica.servicio.IConsultaPolizaService;

import java.text.ParseException;


@RestController
@RequestMapping("/api/poliza")
public class ConsultaPolizaControlador {
    private IConsultaPolizaService iConsultaPolizaService;

    public ConsultaPolizaControlador(IConsultaPolizaService iConsultaPolizaService){
        this.iConsultaPolizaService = iConsultaPolizaService;
    }

    @PostMapping
    SalidaDatosDTO getData(@Valid @RequestBody EntradaDatosDTO entradaDatosDTO) throws ParseException {
        return iConsultaPolizaService.ConsultaPoliza(entradaDatosDTO);
    }
}
