package resources;

import core.invoices.Invoice;
import io.quarkus.logging.Log;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Random;
import java.util.UUID;

@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/invoices")
public class InvoiceResource {

    Random random = new Random();

    @Channel("invoice-requests")
    Emitter<Invoice> invoiceRequestEmitter;

    @POST
    public Invoice createRequest(){
        String uuid = UUID.randomUUID().toString();
        Invoice invoice = new Invoice();
        invoice.id = uuid;
        invoice.price = random.nextInt();
        invoice.customer = uuid;
        invoiceRequestEmitter.send(invoice);
        Log.info(invoice.id);
        return invoice;
    }

}
