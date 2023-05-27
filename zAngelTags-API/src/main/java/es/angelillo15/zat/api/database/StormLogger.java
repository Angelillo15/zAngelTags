package es.angelillo15.zat.api.database;

import es.angelillo15.zat.api.TagsInstance;

public class StormLogger implements com.craftmend.storm.logger.StormLogger {
    @Override
    public void warning(String string) {
        TagsInstance.getLogger().warn(string);
    }

    @Override
    public void info(String string) {
        TagsInstance.getLogger().info(string);
    }
}
