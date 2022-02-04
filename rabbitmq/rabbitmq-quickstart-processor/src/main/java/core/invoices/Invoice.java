package core.invoices;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Invoice {

    public String id;
    public String product;
    public String customer;
    public int price;

    public Invoice() {
    }

    public Invoice(String id, String product, String customer, int price) {
        this.id = id;
        this.product = product;
        this.customer = customer;
        this.price = price;
    }
}
