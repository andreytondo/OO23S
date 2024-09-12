package enums;

public enum OrderStatus {

    PENDING("Pendente"),
    SHIPPED("Enviado"),
    DELIVERED("Entregue"),
    ;

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return status;
    }
}
