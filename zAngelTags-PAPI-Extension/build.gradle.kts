plugins {
    id("java")
    id("net.kyori.blossom") version "1.3.1"
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.13-R0.1-SNAPSHOT")
    compileOnly("me.clip:placeholderapi:2.11.3")
    compileOnly(project(":zAngelTags-API"))
    compileOnly("com.github.Mindgamesnl:storm:prod125")
}

blossom {
    replaceTokenIn("src/main/java/es/angelillo15/zat/papi/TagsExtension.java")
    replaceToken("{version}", project.version)
}