import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.*
import groovy.util.logging.Slf4j

class TrimbleRouteBuilder extends RouteBuilder {
    def cfg
    def skuMapping

    TrimbleRouteBuilder(cfg, skuMapping) {
        this.cfg = cfg
        this.skuMapping = skuMapping
    }

    void configure() {
        from("direct:placeTrimbleOrder")
            .routeId('placeTrimbleOrder')
            .process { println "Processing a trimble order" }
            .to("log:com.agcocorp.feature-unlock-api?level=INFO")
    }
}
