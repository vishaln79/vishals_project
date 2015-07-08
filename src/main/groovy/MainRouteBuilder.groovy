import org.apache.camel.builder.RouteBuilder
import org.apache.camel.model.rest.RestBindingMode
import org.apache.camel.processor.validation.PredicateValidationException
import org.apache.camel.Exchange
import groovy.util.logging.Slf4j

class MainRouteBuilder extends RouteBuilder {
    def cfg
    def skuMapping

    MainRouteBuilder(cfg, skuMapping) {
        this.cfg = cfg
        this.skuMapping = skuMapping
    }

    void configure() {
        def getSkuData = { skuMapping.validSkus[it.in.body.skuNumber] }
        def getVendor = { getSkuData(it).vendor }

        // configure we want to use spark as the component for the rest DSL
        // and we enable json binding mode
        restConfiguration()
            .component("spark-rest")
            .bindingMode(RestBindingMode.json)
            .dataFormatProperty("prettyPrint", "true")
            .port(cfg.port)

        // setting up default exception handling for validation exceptions
        onException(PredicateValidationException)
                .handled(true)
                .process { it.in.setHeader(Exchange.HTTP_RESPONSE_CODE, 400) }
                .transform(exceptionMessage());

        rest("/")
            .consumes("application/json")
            .produces("application/json")
            .post("/vendorOrder")
                .to("direct:placeVendorOrder")

        from("direct:placeVendorOrder")
            .routeId('placeVendorOrder')
            .to("log:com.agcocorp.feature-unlock-api?level=INFO")
            .validate {
                it.in.body.orderNumber && getSkuData(it)
            }
            .choice()
                .when { getVendor(it) == 'Novatel' }.to('direct:placeNovatelOrder')
                .when { getVendor(it) == 'Trimble' }.to('direct:placeTrimbleOrder')
                .otherwise().to('direct:noOp')
            .end()

        from("direct:noOp")
            .process { println "No op" }
            .to("log:com.agcocorp.feature-unlock-api?level=INFO")
            .transform().constant("No op")
    }
}
