import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.LoggingLevel
import groovy.util.logging.Slf4j

class NovatelRouteBuilder extends RouteBuilder {
    def cfg
    def skuMapping
    def novatelTemplate

    NovatelRouteBuilder(cfg, skuMapping, novatelTemplate) {
        this.novatelTemplate = novatelTemplate
        this.cfg = cfg
        this.skuMapping = skuMapping
    }

    void configure() {
        from("direct:placeNovatelOrder")
            .routeId('placeNovatelOrder')
            .log(LoggingLevel.DEBUG, "Processing a novatel order")
            .process {
                it.in.body << novatelTemplate
            }
            .to("log:com.agcocorp.feature-unlock-api?level=INFO")
            // Exchange exchange = template.send("cxfrs://http://localhost:" + getPort2() + "/" + getClass().getSimpleName() + "/testQuery?httpClientAPI=true&q1=12&q2=13"
            //.unmarshal().xmljson()
            //.process { println 'After conversion: ' + it.in }
            //.transform().constant("Order Placed")
    }
}
