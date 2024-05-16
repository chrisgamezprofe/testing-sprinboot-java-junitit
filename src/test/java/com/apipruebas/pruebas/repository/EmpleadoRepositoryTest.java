package com.apipruebas.pruebas.repository;

import com.apipruebas.pruebas.interfaces.EmpleadoRepository;
import com.apipruebas.pruebas.model.Empleado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EmpleadoRepositoryTest {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    private Empleado empleado;
    @BeforeEach
    void setup(){
        empleado = Empleado.builder()
                .email("e1@gmail.com")
                .nombre("Camilo Prueba")
                .salario(900)
                .build();
    }

    @DisplayName("Test Guardar nuevo Empleado")
    @Test
    void saveEmpleado(){


        Empleado empleado1 = empleadoRepository.save(empleado);

        assertThat(empleado1.getEmail()).isEqualTo("e1@gmail.com");
        assertThat(empleado1.getNombre()).isEqualTo("Camilo Prueba");
        assertThat(empleado1.getSalario()).isEqualTo(900);
        assertThat(empleado1.getId()).isGreaterThan(0);


    }

    @DisplayName("Test Actualizar Empleado")
    @Test
    void updateEmpleado(){
        Empleado empleado1 = empleadoRepository.save(empleado);

        empleado1.setNombre("Javier Camilo");
        empleado1.setSalario(2500);

        Empleado empleadoActualizado = empleadoRepository.save(empleado1);

        assertThat(empleadoActualizado.getNombre()).isEqualTo("Javier Camilo");
        assertThat(empleadoActualizado.getSalario()).isEqualTo(2500);

    }

    @DisplayName("Test Eliminar Empleado")
    @Test
    void deleteEmpleado(){
        Empleado empleado1 = empleadoRepository.save(empleado);

        Optional<Empleado> empleadoConsultado = empleadoRepository.findById(empleado1.getId());
        empleadoRepository.deleteById(empleado1.getId());

        Optional<Empleado> empleadoEliminado = empleadoRepository.findById(empleado1.getId());
        assertThat(empleadoConsultado).isNotEmpty();
        assertThat(empleadoEliminado).isEmpty();

    }
}
