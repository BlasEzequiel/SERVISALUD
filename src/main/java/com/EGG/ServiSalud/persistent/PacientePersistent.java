package com.EGG.ServiSalud.persistent;
import com.EGG.ServiSalud.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PacientePersistent extends JpaRepository<Paciente, Long> {
    @Query("SELECT p FROM Paciente p WHERE p.mail = :mail")
    Paciente buscarPorEmail(@Param("mail") String mail);

    @Query("SELECT p FROM Paciente p WHERE p.mail = :mail")
    Optional<Paciente> buscarPorEmailOptional(@Param("mail") String mail);

}
