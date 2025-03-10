package com.pointdafamilia.pointdafamilia.order.enums;

public enum OrderStatus {
    PENDENTE("Aguardando pagamento"),
    PREPARANDO("Preparando pedido"),
    SAIU_ENTREGA("Saiu para entrega"),
    ENTREGUE("Entregue"),
    CANCELADO("Cancelado");

    private final String displayName;

    OrderStatus(String displayName){
        this.displayName = displayName;
    }

    public String getDisplayName(){
        return this.displayName;
    }
}
