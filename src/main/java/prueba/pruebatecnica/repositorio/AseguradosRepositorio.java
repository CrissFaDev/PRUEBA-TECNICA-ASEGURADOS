package prueba.pruebatecnica.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prueba.pruebatecnica.modelo.Asegurados;

import java.util.Optional;

@Repository
public interface AseguradosRepositorio extends JpaRepository<Asegurados, Integer> {
    Optional<Asegurados> findByNumeroIdentificacion(String numero_identificacion);

}
