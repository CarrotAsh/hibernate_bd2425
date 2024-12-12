package bbdd.model;

import javax.persistence.*;


// @TODO Realiza todas las anotaciones necesarias en esta clase para que
// que sus instancias sean guardadas en la base de datos utilizando
// Hibernate. Respecta las restricciones de modelado impuestas en el
// enunciado de la práctica. No es necesario modificar el código de esta
// clase, únicamente debes hacer las anotaciones que consideres
// necesarias.

@Entity
@Table(name = "gastos")

public class Gasto {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pasajero")
    private Pasajero pasajero;

    @ManyToOne
    @JoinColumn(name = "entretenimiento")
    private Entretenimiento entretenimiento;

    @Column(name = "cantidad",nullable = false)
    private Integer cantidad;

    public Gasto() {
        // requerido por Hibernate
    }

    public Gasto(Pasajero pasajero, Entretenimiento entretenimiento, Integer cantidad) {
        this.pasajero = pasajero;
        this.entretenimiento = entretenimiento;
        this.cantidad = cantidad;
    }

    public Long getId() {
        return id;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public Entretenimiento getEntretenimiento() {
        return entretenimiento;
    }

    public Integer getCantidad() {
        return cantidad;
    }

}

