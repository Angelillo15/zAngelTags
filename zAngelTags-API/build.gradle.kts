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