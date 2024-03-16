package com.EGG.ServiSalud.services;

import com.EGG.ServiSalud.Enums.Especialidad;
import com.EGG.ServiSalud.entities.Profesional;
import com.EGG.ServiSalud.exceptions.ProfesionalException;
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

    public Optional<Profesional> buscarPorId(Long idProfesional) {
        return perRepositorio.findById(idProfesional);
    }

    public List<Profesional> buscarPorEspecialidad(Especialidad especialidad){
        return perRepositorio.buscarPorEspecialidad(especialidad);
    }

    public List<Profesional> listarProfesionales() {
        return perRepositorio.findAll();
    }

    public void validarInicioDeSesion(String email, String password) throws ProfesionalException {
        Optional<Profesional> optional= perRepositorio.buscarPorMail(email);
        if (optional.isPresent()){
            Profesional profesional = optional.get();
            if (!profesional.getPassword().equals(password)){
                throw new ProfesionalException("La contraseña ingresada no es correcta.");
            }
        } else {
            throw new ProfesionalException("El mail ingresado no es correcto.");
        }

    }
    @Transactional
    public void deletePersona(Long id) {
        perRepositorio.deleteById(id);

    }
    public Optional<Profesional> buscarPorEmail(String email){
        return perRepositorio.buscarPorMail(email);
    }
}

