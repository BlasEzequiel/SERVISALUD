package com.EGG.ServiSalud.persistent;

import com.EGG.ServiSalud.entities.Turno;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*Contiene los métodos necesarios para guardar/actualizar en la base de datos, realizar consultas o dar de baja
según corresponda
*/
@Repository
@Transactional
public interface TurnoPersistent extends JpaRepository<Turno, Long> {
}
