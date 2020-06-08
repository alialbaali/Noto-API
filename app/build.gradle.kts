plugins {
    application
    kotlin("jvm")
}
repositories {
    maven { url = uri("https://kotlin.bintray.com/ktor") }
}
application {
    mainClassName = "io.ktor.server.netty.EngineMain"
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(Modules.LOCAL))
    implementation(project(Modules.DOMAIN))
    implementation(project(Modules.DI))
    implementation(Libraries.JAKARTA_MAIL)
    implementation(Libraries.KTOR_NETTY)
    implementation(Libraries.LOG_BACK)
    implementation(Libraries.KTOR_CORE)
    implementation(Libraries.KTOR_HOST_COMMON)
    implementation(Libraries.AUTH_JWT)
    implementation(Libraries.CONTENT_NEGOTIATION)
    testImplementation(Libraries.KTOR_TEST)
}