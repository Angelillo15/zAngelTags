package es.angelillo15.zat.api.config;

import lombok.SneakyThrows;
import ru.vyarus.yaml.updater.YamlUpdater;

import java.io.File;
import java.io.InputStream;

public class ConfigMerge {
    @SneakyThrows
    public static void merge(File file, InputStream Input) {
        YamlUpdater.create(file, Input)
                .update();
    }
}
