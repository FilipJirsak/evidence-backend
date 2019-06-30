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
    compile("io.micronaut", "micronaut-http-server-netty", "1.1.3")
    compile("io.micronaut", "micronaut-http-client", "1.1.3")
    compile("io.micronaut", "micronaut-inject", "1.1.3")
    compile("io.micronaut", "micronaut-security-jwt", "1.1.1")
    compile("io.micronaut", "micronaut-runtime", "1.1.3")
    compile("io.micronaut.configuration", "micronaut-jdbc-hikari", "1.1.1")

    compile("org.jdbi", "jdbi3-core", "3.8.2")
    compile("org.jdbi", "jdbi3-sqlobject", "3.8.2")
    compile("org.jdbi", "jdbi3-postgres", "3.8.2")

    compile("com.orientechnologies", "orientdb-client", "3.0.21")

    compile("org.dom4j", "dom4j", "2.1.1")
//    compile("javax.json", "javax.json-api", "1.1.4")

    compile("org.jodd", "jodd-core", "5.0.13")
    //endregion

    //region compileOnly
    compileOnly("io.micronaut", "micronaut-inject-java", "1.1.3")
    //endregion

    //region annotationProcessor
    annotationProcessor("io.micronaut", "micronaut-inject-java", "1.1.3")
    //endregion

    //region runtime
//    runtime("mysql", "mysql-connector-java", "8.0.15")
    runtime("org.postgresql", "postgresql", "42.2.6")
    runtime("org.slf4j", "jul-to-slf4j", "1.7.26")
//    runtime("org.glassfish", "javax.json", "1.1.4")
    runtime("ch.qos.logback", "logback-classic", "1.2.3")
    //endregion

    //region testImplementation
    testImplementation("org.junit.jupiter", "junit-jupiter-api", "5.4.2")
    testImplementation("io.micronaut.test", "micronaut-test-junit5", "1.0.4")
    testImplementation("org.mockito", "mockito-junit-jupiter", "2.22.0")
    //endregion

    //region testRuntimeOnly
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine", "5.4.2")
    //endregion

    //region testAnnotationProcessor
    testAnnotationProcessor("io.micronaut", "micronaut-inject-java", "1.1.3")
    //endregion

}

application {
    mainClassName = "net.czela.backend.evidence.Application"
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}