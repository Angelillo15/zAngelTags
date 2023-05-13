import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

plugins {
    id("java")
    id("net.kyori.blossom") version "1.3.1"
    id("org.ajoberstar.grgit") version "4.1.0"
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.13-R0.1-SNAPSHOT")
    compileOnly("me.clip:placeholderapi:2.11.2")
    compileOnly("com.github.mrgraycat:eGlow:master-SNAPSHOT")
    compileOnly("net.byteflux:libby-bukkit:1.1.5")
    compileOnly("com.github.hamza-cskn.obliviate-invs:core:4.1.10")
    compileOnly("com.github.hamza-cskn.obliviate-invs:configurablegui:4.1.10")
    compileOnly("com.github.hamza-cskn.obliviate-invs:advancedslot:4.1.10")
    compileOnly("com.github.hamza-cskn.obliviate-invs:pagination:4.1.10")
    compileOnly("ru.vyarus:yaml-config-updater:1.4.2")
    compileOnly("org.yaml:snakeyaml:1.33")
    compileOnly("com.github.Carleslc.Simple-YAML:Simple-Yaml:1.8.3")
}

blossom {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    val current = LocalDateTime.now().format(formatter)


    replaceTokenIn("src/main/java/es/angelillo15/zat/api/Constants.java")
    replaceToken("{version}", project.version)
    replaceToken("{git-commit}",  grgit.head().abbreviatedId ?: "undefined")
    replaceToken("{git-user}", grgit.head().committer.name ?: "undefined")
    replaceToken("{git-date}", current ?: "undefined")
    replaceToken("{git-branch}", grgit.branch.current().name ?: "undefined")
}