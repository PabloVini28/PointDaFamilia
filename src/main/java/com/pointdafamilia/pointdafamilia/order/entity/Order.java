package com.pointdafamilia.pointdafamilia.order.entity;

import java.math.BigDecimal;
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
    private BigDecimal totalAmount;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public Order(OrderDto data, User user, List<OrderItem> orderItems) {
        this.user = user;
        this.orderItems = orderItems;
        this.orderStatus = OrderStatus.PENDENTE;
        this.createdAt = LocalDateTime.now(); 
        this.totalAmount = calculateTotalAmount(orderItems);
    }

    public BigDecimal calculateTotalAmount(List<OrderItem> orderItems) {
        return orderItems.stream()
            .map(item -> {
                String priceStr = item.getFood() != null ? item.getFood().getPrice() : item.getDrink().getPrice();
                BigDecimal price = convertToBigDecimal(priceStr);
                return price.multiply(BigDecimal.valueOf(item.getQuantity()));
            })
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal convertToBigDecimal(String priceStr) {
        if (priceStr == null || priceStr.isEmpty()) {
            return BigDecimal.ZERO;
        }
        try {
            return new BigDecimal(priceStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Preço inválido: " + priceStr);
        }
    }
}
