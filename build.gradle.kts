plugins {
    java
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "4.0.4"
}


tasks.shadowJar {
    archiveFileName.set("zAngelTags.jar")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":common"))
    implementation(project(":bukkit"))
    implementation(project(":api"))
}

group = "es.angelillo15"
version = "4.4"
description = "ZAngelTags"

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.processResources {
    expand("{version}" to project.version)
}