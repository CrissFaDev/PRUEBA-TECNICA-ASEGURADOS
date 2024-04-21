package prueba.pruebatecnica.servicio;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import prueba.pruebatecnica.dto.EntradaDatosDTO;
import prueba.pruebatecnica.dto.LiquidacionDTO;
import prueba.pruebatecnica.dto.SalidaDatosDTO;
import prueba.pruebatecnica.modelo.Asegurados;
import prueba.pruebatecnica.modelo.Prismas;
import prueba.pruebatecnica.repositorio.AmparosRepositorio;
import prueba.pruebatecnica.repositorio.AseguradosRepositorio;
import prueba.pruebatecnica.repositorio.PrismasRepositorio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

@Service
public class ConsultaPolizaService implements IConsultaPolizaService {

    private final AseguradosRepositorio aseguradosRepositorio;
    private final AmparosRepositorio amparosRepositorio;
    private final PrismasRepositorio prismasRepositorio;


    //Contructor
    public ConsultaPolizaService(AseguradosRepositorio aseguradosRepositorio, AmparosRepositorio amparosRepositorio, PrismasRepositorio prismasRepositorio) {
        this.aseguradosRepositorio = aseguradosRepositorio;
        this.amparosRepositorio = amparosRepositorio;
        this.prismasRepositorio = prismasRepositorio;

    }


    // Convierte una cadena que representa una fecha de nacimiento en un objeto Date
    private Object stringToDate(String fechaNacimiento) throws ParseException {
        // Define el formato de entrada para la fecha de nacimiento
        SimpleDateFormat formatoEntrada = new SimpleDateFormat("dd-MMM-yy", new Locale("es", "ES"));
        // Define el formato de salida deseado para la fecha
        SimpleDateFormat formatoSalida = new SimpleDateFormat("yyyy-MM-dd");

        // Parsea la cadena de fecha de nacimiento al formato de Date
        Date fecha = formatoEntrada.parse(fechaNacimiento);

        // Formatea la fecha al formato de salida deseado y la imprime en la consola
        String fechaFormateada = formatoSalida.format(fecha);
        System.out.println(fechaFormateada + "fecha formateada en formato yyyy-MM-dd");

        // Devuelve la fecha como objeto Date
        return fecha;
    }

    // Método para calcular la edad a partir de una fecha de nacimiento
    private Object calcularEdad(Date fecha) {
        // Convertir Date a LocalDate
        LocalDate fechaNacional = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        // Obtener la fecha actual
        LocalDate fechaactual = LocalDate.now();
        System.out.println(fechaactual + "fecha actual....");

        // Calcular el periodo entre las fechas
        Period periodo = Period.between(fechaNacional, fechaactual);

        // Devolver la edad en años
        System.out.println(periodo.getYears() + "años que tiene la persona");
        return periodo.getYears();
    }

    @Override
    public SalidaDatosDTO ConsultaPoliza(EntradaDatosDTO entradaDatosDTO) throws ParseException {
        // Inicialización de variables
        var prima = 0.0;
        // Se obtiene el asegurado correspondiente al número de identificación proporcionado
        var asegurado = aseguradosRepositorio.findByNumeroIdentificacion(entradaDatosDTO.getNro_identificacion()).get();
        System.out.println(asegurado);

        // Se convierte la fecha de nacimiento del asegurado a un objeto Date y se calcula la edad
        var fecha = stringToDate(asegurado.getFechaNacimiento());
        System.out.println(fecha);
        var edad = calcularEdad((Date) fecha);
        System.out.println(edad);

        // Se consultan las primas correspondientes a la edad del asegurado
        var primas = prismasRepositorio.ConsultaPrimas((Integer) edad);
        System.out.println(primas);

        // Se obtienen los amparos disponibles para las primas consultadas
        var amparos = amparosRepositorio.finAllByCodigo(primas.stream().map(Prismas::getCodigo).toList());
        System.out.println(amparos + "amparos.......");

        // Se inicializan listas para almacenar información sobre la liquidación
        List<LiquidacionDTO> liquidacionDTOS = new ArrayList<>();
        List<Double> sum = new ArrayList<>();

        // Si hay primas disponibles
        if (primas.size() > 0) {
            // Por cada amparo disponible
            amparos.forEach(amparo -> {
                var liquidacion = new LiquidacionDTO();
                // Por cada prima correspondiente al amparo
                primas.forEach(prima1 -> {
                    // Si la prima corresponde al amparo
                    if (prima1.getCodigo() == amparo.getId_amparos()) {
                        // Se establece el valor de la prima y se calcula la suma
                        liquidacion.setValor_prima(prima1.getPorcentajePrima());
                        var i = entradaDatosDTO.getValor_asegurado() * prima1.getPorcentajePrima();
                        sum.add(i);
                    }
                });

                // Se establece la información del amparo en la liquidación
                liquidacion.setCodigo_amparo(amparo.getId_amparos());
                liquidacion.setNombre_amparo(amparo.getNombre());
                liquidacionDTOS.add(liquidacion);
            });
        }

        // Se calcula el total de la suma de las primas
        var total = sum.stream().mapToInt(Double::intValue).sum();
        System.out.println(total + "total .........................");

        // Se crea el objeto SalidaDatosDTO con la información obtenida
        var result = new SalidaDatosDTO();
        result.setNro_identificacion(asegurado.getNumeroIdentificacion());
        result.setTipo_identificacion(asegurado.getTipoIdentificacion());
        result.setValor_asegurado(entradaDatosDTO.getValor_asegurado());
        result.setLiquidacion(liquidacionDTOS);
        result.setValor_total(total);

        // Se retorna el resultado de la consulta
        return result;
    }

}
