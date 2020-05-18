import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

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
    implementation(kotlin(Libraries.KOTLIN))
    implementation(Libraries.KTOR_NETTY)
    implementation(Libraries.LOG_BACK)
    implementation(Libraries.KTOR_CORE)
    implementation(Libraries.KTOR_HOST_COMMON)
    implementation(Libraries.AUTH_JWT)
    implementation(project(Modules.DI))
    implementation(project(Modules.LOCAL))
    implementation(Libraries.CONTENT_NEGOTIATION)
    implementation(project(Modules.DOMAIN))
    testImplementation(Libraries.KTOR_TEST)
}