plugins {
    id("java")
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(project(":zAngelTags-API"))
    compileOnly("org.spigotmc:spigot-api:1.19-R0.1-SNAPSHOT")
    compileOnly("com.destroystokyo.paper:paper-api:1.16.5-R0.1-SNAPSHOT")
    compileOnly("org.yaml:snakeyaml:2.0")
    compileOnly("com.github.Carleslc.Simple-YAML:Simple-Yaml:1.8.3")
    compileOnly("com.zaxxer:HikariCP:5.0.1")
    compileOnly("com.comphenix.protocol:ProtocolLib:4.8.0")
    compileOnly("com.github.hamza-cskn.obliviate-invs:core:4.1.10")
    compileOnly("com.github.hamza-cskn.obliviate-invs:configurablegui:4.1.10")
    compileOnly("com.github.hamza-cskn.obliviate-invs:advancedslot:4.1.10")
    compileOnly("com.github.hamza-cskn.obliviate-invs:pagination:4.1.10")
    compileOnly("me.clip:placeholderapi:2.11.2")
    compileOnly("com.github.MilkBowl:VaultAPI:1.7.1")
    compileOnly("com.konghq:unirest-java:3.11.09")
    compileOnly("com.github.MrGraycat:eGlow:master-SNAPSHOT")
    compileOnly("net.byteflux:libby-bukkit:1.1.5")
    compileOnly("io.papermc:paperlib:1.0.7")
}

tasks.processResources {
    filesMatching("plugin.yml") {
        expand("version" to (parent?.version ?: project.version))
    }
}