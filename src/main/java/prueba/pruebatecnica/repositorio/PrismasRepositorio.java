package prueba.pruebatecnica.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import prueba.pruebatecnica.modelo.Prismas;

import java.util.List;

@Repository
public interface PrismasRepositorio extends JpaRepository<Prismas, Integer> {

    @Query(value = "select p from Prismas p where :edad between p.edadMinima and p.edadMaxima")
    List<Prismas> ConsultaPrimas(@Param("edad") Integer edad);
}
