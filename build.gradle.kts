plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}
tasks.shadowJar {
    relocate("es.angelillo15.configmanager", "es.angelillo15.mast.libs.config.manager")
    relocate("org.yaml.snakeyaml", "es.angelillo15.mast.libs.snakeyaml")
    relocate("org.simpleyaml", "es.angelillo15.mast.libs.simpleyaml")
    relocate("es.angelillo15.glow", "es.angelillo15.mast.libs.glow")
    relocate("net.kyori.adventure", "es.angelillo15.mast.libs.adventure")
    relocate("mc.obliviate", "es.angelillo15.mast.libs.obliviate")
    relocate("com.zaxxer.hikari", "es.angelillo15.mast.libs.hikari")
    relocate("com.google.common", "es.angelillo15.mast.libs.google.common")
    relocate("com.google.gson", "es.angelillo15.mast.libs.google.gson")
    relocate("com.google.thirdparty", "es.angelillo15.mast.libs.google.thirdparty")
    relocate("com.google.errorprone", "es.angelillo15.mast.libs.google.errorprone")
    relocate("com.google.j2objc", "es.angelillo15.mast.libs.google.j2objc")
    relocate("javax.annotation", "es.angelillo15.mast.libs.javax.annotation")
    relocate("org.checkerframework", "es.angelillo15.mast.libs.checkerframework")
    relocate("net.byteflux.libby", "es.angelillo15.mast.libs.libby")
    relocate("ru.vyarus.yaml.updater", "es.angelillo15.mast.libs.yaml-config-updater")
    relocate("kong.unirest", "es.angelillo15.mast.libs.unirest")
    relocate("org.apache.http", "es.angelillo15.mast.libs.apache.http")
    relocate("org.apache.commons.logging", "es.angelillo15.mast.libs.commons-logging")
    relocate("org.reflections", "es.angelillo15.mast.libs.reflections")
    relocate("io.papermc.lib", "es.angelillo15.mast.libs.paperlib")
}

dependencies {
    implementation(project(":zAngelTags-API"))
    implementation("com.github.Carleslc.Simple-YAML:Simple-Yaml:1.8.3")
    implementation("com.github.Angelillo15:ConfigManager:1.4")
    implementation("com.github.hamza-cskn.obliviate-invs:core:4.1.10")
    implementation("com.github.hamza-cskn.obliviate-invs:advancedslot:4.1.10")
    implementation("com.github.hamza-cskn.obliviate-invs:pagination:4.1.10")
    implementation("com.github.hamza-cskn.obliviate-invs:configurablegui:4.1.10")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("net.byteflux:libby-bukkit:1.1.5")
    implementation("org.reflections:reflections:0.10.2")
    implementation("io.papermc:paperlib:1.0.7")
}

val javaVersion = JavaVersion.VERSION_1_8
version = "5.0.0-SNAPSHOT"

allprojects {
    apply(plugin = "java")

    version = rootProject.version

    repositories {
        mavenCentral()
        maven("https://jitpack.io")
        maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
        maven("https://oss.sonatype.org/content/repositories/snapshots")
        maven("https://oss.sonatype.org/content/repositories/central")
        maven("https://repo.dmulloy2.net/repository/public/")
        maven("https://repo.alessiodp.com/releases/")
        maven("https://papermc.io/repo/repository/maven-releases/")
        maven("https://repo.papermc.io/repository/maven-public/")
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    java {
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }

    dependencies {
        compileOnly("org.projectlombok:lombok:1.18.24")
        annotationProcessor("org.projectlombok:lombok:1.18.24")
    }
}