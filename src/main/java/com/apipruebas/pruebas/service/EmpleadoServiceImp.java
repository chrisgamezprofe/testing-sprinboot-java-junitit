package com.apipruebas.pruebas.service;

import com.apipruebas.pruebas.exception.NotFoundException;
import com.apipruebas.pruebas.interfaces.EmpleadoRepository;
import com.apipruebas.pruebas.model.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImp implements EmpleadoService{

    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Override
    public Empleado save(Empleado empleado) {
        Optional<Empleado> nuevoEmpleado = empleadoRepository.findByEmail(empleado.getEmail());
        if(nuevoEmpleado.isPresent()){
            throw new NotFoundException("Ya existe un empleado con email:"+empleado.getEmail());
        }
        return empleadoRepository.save(empleado);
    }

    @Override
    public List<Empleado> getAll() {
        return empleadoRepository.findAll();
    }

    @Override
    public Optional<Empleado> getById(long id) {
        return empleadoRepository.findById(id);
    }

    @Override
    public Empleado update(Empleado empleado) {
        Optional<Empleado> actualizarEmpleado = empleadoRepository.findByEmail(empleado.getEmail());
        if(!actualizarEmpleado.isPresent()){
            throw new NotFoundException("No existe un empleado con email:"+empleado.getEmail());
        }
        return empleadoRepository.save(empleado);
    }

    @Override
    public void delete(long id) {
        empleadoRepository.deleteById(id);
    }
}
