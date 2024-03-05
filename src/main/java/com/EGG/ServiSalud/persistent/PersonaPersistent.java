package com.EGG.ServiSalud.persistent;
import com.EGG.ServiSalud.entities.Persona;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/*Contiene los métodos necesarios para guardar/actualizar en la base de datos, realizar consultas o dar de baja
según corresponda
*/

@Repository
@Transactional
public interface PersonaPersistent extends JpaRepository<Persona, Long> {
    @Query("SELECT p FROM Persona p WHERE p.id = :id")
    public Persona buscarPorId(@Param("id") Long id);
    @Query("SELECT p FROM Persona p WHERE p.nombre = :nombre")
    public List<Persona> buscarPorNombre(@Param("nombre") String nombre);
    @Query("SELECT p FROM Persona p WHERE p.apellido = :apellido")
    public List<Persona> buscarPorApellido(@Param("apellido") String apellido);
}
