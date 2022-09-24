plugins {
    id("java")
}

group = "es.angelillo15"
version = "4.4"

repositories {
}

dependencies {

}

tasks.processResources {
    filesMatching("plugin.yml") {
        expand("version" to (parent?.version ?: project.version))
    }
}