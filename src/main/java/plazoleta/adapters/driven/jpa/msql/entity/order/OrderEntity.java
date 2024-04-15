package plazoleta.adapters.driven.jpa.msql.entity.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import plazoleta.adapters.driven.jpa.msql.entity.plate.PlateEntity;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "pedido")
@AllArgsConstructor
@RequiredArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private int id;
    @Column(name = "id_usuario")
    private int userId;
    @Column(name = "fecha")
    private LocalDate date;
    @Column(name = "estado")
    private String state;
    @Column(name = "id_chef")
    private int chefId;
    @Column(name = "id_restaurant")
    private int restaurantId;
    @OneToMany(mappedBy = "order")
    private List<OrderPlateEntity>  plateEntityList;
}