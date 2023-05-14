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
    compileOnly("com.github.Angelillo15:ConfigManager:1.4")
    compileOnly("net.byteflux:libby-bukkit:1.1.5")
    compileOnly("com.github.hamza-cskn.obliviate-invs:core:4.1.10")
    compileOnly("com.github.hamza-cskn.obliviate-invs:configurablegui:4.1.10")
    compileOnly("com.github.hamza-cskn.obliviate-invs:advancedslot:4.1.10")
    compileOnly("com.github.hamza-cskn.obliviate-invs:pagination:4.1.10")
    compileOnly("ru.vyarus:yaml-config-updater:1.4.2")
    compileOnly("org.yaml:snakeyaml:2.0")
    compileOnly("com.github.Carleslc.Simple-YAML:Simple-Yaml:1.8.3")
    compileOnly("net.md-5:bungeecord-chat:1.16-R0.4")
    compileOnly("com.github.Mindgamesnl:storm:prod125")
    compileOnly("com.zaxxer:HikariCP:4.0.3")
    compileOnly("com.github.ben-manes.caffeine:caffeine:2.9.2")
}

blossom {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    val current = LocalDateTime.now().format(formatter)

    val und = "undefined"

    replaceTokenIn("src/main/java/es/angelillo15/zat/api/Constants.java")
    replaceToken("{version}", project.version)
    replaceToken("{git-commit}",  grgit.head().abbreviatedId ?: und)
    replaceToken("{git-user}", grgit.head().committer.name ?: und)
    replaceToken("{git-date}", current ?: und)
    replaceToken("{git-branch}", grgit.branch.current().name ?: und)
}