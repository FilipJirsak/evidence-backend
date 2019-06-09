plugins {
    java
    application
}

group = "net.czela"
version = "0.1.0"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<JavaCompile>().configureEach {
    options.apply {
        isIncremental = true
        encoding = "UTF-8"
        compilerArgs.add("-parameters")
    }
}

repositories {
    mavenCentral()
}

dependencies {
    //region compile
    compile("io.micronaut", "micronaut-http-server-netty", "1.1.1")
    compile("io.micronaut", "micronaut-http-client", "1.1.1")
    compile("io.micronaut", "micronaut-inject", "1.1.1")
    compile("io.micronaut", "micronaut-security-jwt", "1.1.1")
    compile("io.micronaut", "micronaut-runtime", "1.1.1")
    compile("io.micronaut.configuration", "micronaut-jdbc-hikari", "1.1.0")

    compile("org.jdbi", "jdbi3-core", "3.7.1")
    compile("com.orientechnologies", "orientdb-client", "3.0.18")

    compile("org.dom4j", "dom4j", "2.1.1")
//    compile("javax.json", "javax.json-api", "1.1.4")

    compile("org.jodd", "jodd-core", "5.0.12")
    //endregion

    //region compileOnly
    compileOnly("io.micronaut", "micronaut-inject-java", "1.1.1")
    //endregion

    //region annotationProcessor
    annotationProcessor("io.micronaut", "micronaut-inject-java", "1.1.1")
    //endregion

    //region runtime
    runtime("mysql", "mysql-connector-java", "8.0.15")

//    runtime("org.glassfish", "javax.json", "1.1.4")

    runtime("ch.qos.logback", "logback-classic", "1.2.3")
    //endregion

    //region testImplementation
    testImplementation("org.junit.jupiter", "junit-jupiter-api", "5.4.2")
    //endregion

    //region testRuntimeOnly
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine", "5.4.2")
    //endregion
}

application {
    mainClassName = "net.czela.backend.evidence.Application"
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}