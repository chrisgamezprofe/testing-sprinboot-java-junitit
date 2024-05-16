package com.apipruebas.pruebas.service;

import com.apipruebas.pruebas.model.Empleado;

import java.util.List;
import java.util.Optional;

public interface EmpleadoService {
    Empleado save(Empleado e);
    List<Empleado> getAll();

    Optional<Empleado> getById(long id);
    Empleado update(Empleado e);

    void delete(long id);
}
