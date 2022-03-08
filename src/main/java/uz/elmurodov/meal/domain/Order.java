package uz.elmurodov.meal.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Long mealId;

    private Long userId;

    @Column(name = "received")
    private Boolean received;

    @Column(name = "order_date")
    private LocalDate orderDate;

}