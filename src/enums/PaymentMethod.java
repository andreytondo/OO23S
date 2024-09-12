package enums;

public enum PaymentMethod {

    CREDIT_CARD("Cartão de Crédito"),
    PIX("PIX"),
    BOLETO("Boleto"),
    PAYPAL("PayPal");

    private final String description;

    PaymentMethod(String description) {
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
