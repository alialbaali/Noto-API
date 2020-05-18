dependencies {
    implementation(kotlin("stdlib-jdk8"))
    api(Libraries.KOIN)
    implementation(project(Modules.DATA))
    implementation(project(Modules.DOMAIN))
    implementation(project(Modules.LOCAL))
}