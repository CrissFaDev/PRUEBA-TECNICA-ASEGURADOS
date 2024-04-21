package prueba.pruebatecnica.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import prueba.pruebatecnica.modelo.Amparos;

import java.util.List;

@Repository
public interface AmparosRepositorio extends JpaRepository<Amparos, Integer> {
    @Query("select a from Amparos a where a.id_amparos in (:codigoList)")
    List<Amparos> finAllByCodigo(@Param("codigoList") List<Integer> codigoList);
}
