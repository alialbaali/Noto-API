dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(project(Modules.DATA))
    implementation(Libraries.EXPOSED_CORE)
    implementation(Libraries.EXPOSED_JDBC)
    implementation(Libraries.EXPOSED_JODA_TIME)
    implementation(Libraries.POSTGRESQL)
}