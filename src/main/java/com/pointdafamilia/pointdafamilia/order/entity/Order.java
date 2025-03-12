package com.pointdafamilia.pointdafamilia.order.entity;

import java.time.LocalDateTime;
import java.util.List;
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
        this.orderStatus = OrderStatus.PENDENTE;
        this.totalAmount = calculateAmout(this.orderItems);
    }

    public double calculateAmout(List<OrderItem> orderItems2){
            double total = 0;
            for (OrderItem orderItem : orderItems2) {
            total += orderItem.getTotalAmount();
        }
        return total;
    }

    
}
