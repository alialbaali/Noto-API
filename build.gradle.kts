import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        jcenter()
        mavenLocal()
        maven { url = uri("https://kotlin.bintray.com/ktor") }
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.72")
    }
}

allprojects {
    this.version = App.APP_VERSION
    this.group = App.APP_GROUP

    apply(plugin = "kotlin")

    repositories {
        jcenter()
        mavenCentral()
    }

    tasks.withType<KotlinCompile>().all {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xallow-result-return-type")
            jvmTarget = "1.8"
        }
    }
}

subprojects {
    apply(plugin = "java-library")
    apply(plugin = "kotlin")
}

tasks.register("cleaning", Delete::class.java) {
    delete(rootProject.buildDir)
}