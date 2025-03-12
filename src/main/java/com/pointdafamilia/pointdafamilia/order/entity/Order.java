package com.pointdafamilia.pointdafamilia.order.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.pointdafamilia.pointdafamilia.drink.entity.Drink;
import com.pointdafamilia.pointdafamilia.food.entity.Food;
import com.pointdafamilia.pointdafamilia.order.dtos.response.OrderDto;
import com.pointdafamilia.pointdafamilia.order.enums.OrderStatus;
import com.pointdafamilia.pointdafamilia.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "orders")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne 
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    private double totalAmount;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public Order(OrderDto data){
        this.orderItems = data.orderItems();
        this.orderStatus = OrderStatus.PENDENTE;
        this.totalAmount = calculateAmout(this.orderItems);
    }

    public double calculateAmout(List<OrderItem> data){
        double resultado = 0;
        Food auxFood = new Food();
        Drink auxDrink = new Drink();
        for(int i = 0 ; i < data.size() ; i++){
            if(data.get(i).getFood().equals(auxFood)){
                resultado += data.get(i).getFood().getPrice();
            }
            if(data.get(i).getDrink().equals(auxDrink)){
                resultado += data.get(i).getDrink().getPrice();
            }
        }
        return resultado;
    }

    
}
