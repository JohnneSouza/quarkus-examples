package core.invoices;

import io.smallrye.reactive.messaging.annotations.Blocking;
import io.vertx.core.json.JsonObject;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class InvoiceProcessor {

    @Blocking
    @Incoming("invoices")
    public Invoice process(JsonObject request) {
        return request.mapTo(Invoice.class);
    }

}
