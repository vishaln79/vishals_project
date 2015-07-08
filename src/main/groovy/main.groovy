public class Main {
    static void main(String[] args) {
        def main = new org.apache.camel.Main()

        // add routes & other configuration
        ConfigureMain(main)

        // run until you terminate the JVM
        main.run()
    }

    def static void ConfigureMain(org.apache.camel.Main main) {
        // enable hangup support so you can press ctrl + c to terminate the JVM
        main.enableHangupSupport()

        def cfg = new ConfigSlurper().parse(Config)
        def skuMapping = new ConfigSlurper().parse(SkuMapping)
        def novatelTemplate = new ConfigSlurper().parse(NovatelTemplate)

        main.addRouteBuilder new MainRouteBuilder(cfg, skuMapping)
        main.addRouteBuilder new TrimbleRouteBuilder(cfg, skuMapping)
        main.addRouteBuilder new NovatelRouteBuilder(cfg, skuMapping, novatelTemplate)

        println "Starting Camel. Use ctrl + c to terminate the JVM."
    }
}
