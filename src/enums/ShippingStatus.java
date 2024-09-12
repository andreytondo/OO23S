package enums;

public enum ShippingStatus {

    PENDING("Pendente"),
    SHIPPED("Enviado"),
    DELIVERED("Entregue");

    private final String description;

    ShippingStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}
