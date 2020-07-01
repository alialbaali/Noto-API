object Libraries {

    const val KTOR_HOST_COMMON = "io.ktor:ktor-server-host-common:"

    const val KTOR_CORE = "io.ktor:ktor-server-core:${Versions.KTOR}"

    const val KTOR_NETTY = "io.ktor:ktor-server-netty:${Versions.KTOR}"

    const val KOTLIN = "stdlib-jdk8:${Versions.KOTLIN}"

    const val LOG_BACK = "ch.qos.logback:logback-classic:${Versions.LOG_BACK}"

    const val KTOR_TEST = "io.ktor:ktor-server-tests:${Versions.KTOR}"

    const val KOIN = "org.koin:koin-ktor:${Versions.KOIN}"

    const val KOTLIN_GRADLE = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"

    const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES}"

    const val EXPOSED_CORE = "org.jetbrains.exposed:exposed-core:${Versions.EXPOSED}"

    const val EXPOSED_JDBC = "org.jetbrains.exposed:exposed-jdbc:${Versions.EXPOSED}"

    const val EXPOSED_JODA_TIME = "org.jetbrains.exposed:exposed-jodatime:${Versions.EXPOSED}"

    const val JODA_TIME = "joda-time:joda-time:${Versions.JODA_TIME}"

    const val SLF4J = "org.slf4j:slf4j-nop:${Versions.SLF4J}"

    const val POSTGRESQL = "org.postgresql:postgresql:${Versions.POSTGRESQL}"

    const val AUTH_JWT = "io.ktor:ktor-auth-jwt:${Versions.KTOR}"

    const val CONTENT_NEGOTIATION = "io.ktor:ktor-jackson:${Versions.KTOR}"

    const val JAKARTA_MAIL = "com.sun.mail:jakarta.mail:${Versions.JAKARTA_MAIL}"
    
    const val JACKSON_DATE = "com.fasterxml.jackson.datatype:jackson-datatype-joda:${Versions.JACKSON_DATE}"

}

private object Versions {
    const val KTOR = "1.3.2"
    const val KOTLIN = "1.3.72"
    const val LOG_BACK = "1.2.1"
    const val KOIN = "2.1.5"
    const val COROUTINES = "1.3.3"
    const val EXPOSED = "0.26.1"
    const val JODA_TIME = "2.10.6"
    const val SLF4J = "1.7.30"
    const val POSTGRESQL = "42.2.2"
    const val JAKARTA_MAIL = "1.6.5"
    const val JACKSON_DATE = "2.11.1"
}


object Modules {
    const val BUILD_SRC = ":buildsrc"
    const val DATA = ":data"
    const val DI = ":di"
    const val RESOURCES = ":resources"
    const val LOCAL = ":local"
    const val REMOTE = ":remote"
    const val DOMAIN = ":domain"
    const val APP = ":app"
}

object App {
    const val APP_GROUP = "com.api-noto"
    const val APP_NAME = "Noto-API"
    const val APP_VERSION = "0.0.5"
}


object Plugins {
    const val ANDROID_APPLICATION = "com.android.application"
    const val ANDROID_LIBRARY = "com.android.library"
    const val KOTLIN_ANDROID = "android"
    const val KOTLIN_ANDROID_EXTENSIONS = "kotlin-android-extensions"
    const val KOTLIN_KAPT = "kapt"
    const val NAVIGATION_SAFE_ARGS = "androidx.navigation.safeargs.kotlin"
}
