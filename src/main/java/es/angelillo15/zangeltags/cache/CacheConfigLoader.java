package es.angelillo15.zangeltags.cache;

import es.angelillo15.zangeltags.ZAngelTags;
import es.angelillo15.zangeltags.config.ConfigManager;

public class CacheConfigLoader {

    private static ConfigManager tagsCache;
    private final ZAngelTags plugin;

    public CacheConfigLoader(ZAngelTags plugin) {
        this.plugin = plugin;
        loadTagsCache();
    }

    public static ConfigManager getTagsCache() {
        return tagsCache;
    }

    public void loadTagsCache() {
        tagsCache = new ConfigManager(plugin, "cache/", "tags.yml");
        tagsCache.saveDefaultConfig();
    }

}
