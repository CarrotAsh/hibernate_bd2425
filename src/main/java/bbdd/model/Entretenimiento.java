package bbdd.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

// @TODO Realiza todas las anotaciones necesarias en esta clase para que
// que sus instancias sean guardadas en la base de datos utilizando
// Hibernate. Respecta las restricciones de modelado impuestas en el
// enunciado de la práctica. No es necesario modificar el código de esta
// clase, únicamente debes hacer las anotaciones que consideres
// necesarias.

@Entity
@Table(name = "entretenimiento")
public class Entretenimiento {

    @Id
    @Column(name = "Id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @OneToMany
    private Set<Gasto> gastos = new HashSet<>();

    public Entretenimiento() {
        // requerido por Hibernate
    }

    public Entretenimiento(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() { 
        return nombre; 
    }
    
    public Set<Gasto> getGastos() { 
        return gastos; 
    }

}
