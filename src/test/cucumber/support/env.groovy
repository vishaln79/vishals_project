import static cucumber.api.groovy.Hooks.*
def main = new org.apache.camel.Main()

Before(){
    Main.ConfigureMain(main)

    // run until you terminate the JVM
    main.start()
}

After(){
    main.stop()
}
