package com.EGG.ServiSalud.services;

import com.EGG.ServiSalud.persistent.TurnoPersistent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*Esta clase tiene la responsabilidad de llevar adelante las funcionalidades necesarias para
administrar (consulta, creación, modificación y dar de baja).*/
@Service
public class TurnoService {
    @Autowired
    private TurnoPersistent turnoPer;
}
