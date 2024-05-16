package com.apipruebas.pruebas.controller;

import com.apipruebas.pruebas.model.Empleado;
import com.apipruebas.pruebas.service.EmpleadoService;
import com.apipruebas.pruebas.service.EmpleadoServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoServiceImp empleadoServiceImp;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Empleado save(@RequestBody Empleado empleado){
        return empleadoServiceImp.save(empleado);
    }

    @GetMapping
    public List<Empleado> getAll(){
        return empleadoServiceImp.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> getById(@PathVariable("id") long idEmpleado){
        return empleadoServiceImp.getById(idEmpleado)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleado> update(@PathVariable("id") long idEmpleado, @RequestBody Empleado empleado){
        return empleadoServiceImp.getById(idEmpleado)
                .map(empleadoEncontrado ->{
                    empleadoEncontrado.setNombre(empleado.getNombre());
                    empleadoEncontrado.setEmail(empleado.getEmail());
                    empleadoEncontrado.setSalario(empleado.getSalario());

                    Empleado empleadoActualizado = empleadoServiceImp.update(empleadoEncontrado);
                    return new ResponseEntity<>(empleadoActualizado,HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long idEmpleado){
        empleadoServiceImp.delete(idEmpleado);
        return new ResponseEntity<String>("Empleado eliminado",HttpStatus.OK);
    }
}
