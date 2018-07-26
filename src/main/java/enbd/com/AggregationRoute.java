package enbd.com;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Created by martins_rh on 26/07/2018.
 */

@Component
public class AggregationRoute  extends RouteBuilder{

    @Override
    public void configure() throws Exception {
        from("{{caller.inbound}}").setHeader(Exchange.CONTENT_TYPE,  simple("text/html") )
                .multicast(new ConcatenatingAggregator())
                .parallelProcessing()
                .to("{{aggregation.first}}")
                .to("{{aggregation.second}}")
                .end();
    }
}