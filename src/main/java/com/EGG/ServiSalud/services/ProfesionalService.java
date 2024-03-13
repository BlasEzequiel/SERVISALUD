/*package com.EGG.ServiSalud.services;

import com.EGG.ServiSalud.entities.Profesional;
import com.EGG.ServiSalud.persistent.ProfesionalPersistent;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfesionalService {
    @Autowired
    private ProfesionalPersistent perRepositorio;

    @Transactional
    public Profesional createProfesional(Profesional profesional) {
        return perRepositorio.save(profesional);
    }
    public Optional<Profesional> buscarPorId(Long idProfesional){
        return perRepositorio.findById(idProfesional);
    }
    public List<Profesional> devolverProfesionales(){
        return perRepositorio.findAll();
    }

    public void cambiarNombreProfesional(Long idProfesional, Profesional profesional) {
        perRepositorio.actualizarNombreProfesional(profesional.getNombreCompleto, idProfesional);
    }
    @Transactional
    public void deletePersona(Long id) {
        perRepositorio.deleteById(id);

    }

}*/

