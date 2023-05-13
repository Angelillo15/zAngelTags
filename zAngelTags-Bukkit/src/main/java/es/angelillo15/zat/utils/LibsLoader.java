package es.angelillo15.zat.utils;

import es.angelillo15.zat.TagsPlugin;
import es.angelillo15.zat.api.libs.LibsManager;
import net.byteflux.libby.BukkitLibraryManager;
import net.byteflux.libby.Library;

public class LibsLoader {
    public static void load() {
        BukkitLibraryManager manager = new BukkitLibraryManager(TagsPlugin.getInstance());

        manager.addMavenCentral();
        manager.addJitPack();
        manager.addSonatype();

        LibsManager.load();
        LibsManager.getLibs().forEach(manager::loadLibrary);
    }
}
