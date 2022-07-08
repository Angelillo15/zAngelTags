package es.angelillo15.zangeltags.gui;

import es.angelillo15.zangeltags.ZAngelTags;
import es.angelillo15.zangeltags.config.ConfigLoader;
import es.angelillo15.zangeltags.utils.ColorUtils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TagsGui {
    private ZAngelTags plugin;
    private Player p;

    public TagsGui(ZAngelTags plugin, Player p) {
        this.plugin = plugin;
        this.p = p;
        CreateGUI();
    }

    public void CreateGUI() {
        FileConfiguration cache = plugin.tagsCache.getCache();
        Set<String> tagsArray = cache.getConfigurationSection("Tags").getKeys(false);
        FileConfiguration config = ConfigLoader.getGuiConfig().getConfig();

        String Title = config.getString("Gui.tittle");

        Material material = Material.valueOf(config.getString("Gui.tagItemMaterial"));

        String BarrierCloseName = config.getString("Gui.barrierCloseName");

        String DisableName = config.getString("Gui.disableTag");

        Inventory tagGui = Bukkit.createInventory(null, 54, ChatColor.translateAlternateColorCodes('&', Title));
        int slots = 1;
        for (String s : tagsArray) {
            if (slots <= 46) {
                ItemStack item = new ItemStack(material);
                ItemMeta meta = item.getItemMeta();
                ArrayList<String> list = new ArrayList<>();
                String name = s;
                String inGameTag = ChatColor.translateAlternateColorCodes('&', cache.getString("Tags."+s+".inGameTag"));
                String permission = cache.getString("Tags."+s+".permission");

                FileConfiguration gui = ConfigLoader.getGuiConfig().getConfig();
                List<String> message = (List<String>) gui.getList("Gui.itemLore");
                for (String c : message) {
                    list.add(ColorUtils.translateColorCodes( c
                                    .replace("{tag_name}", name))
                            .replace("{tag_displayName}", ColorUtils.translateColorCodes(inGameTag))
                            .replace("{tag_perm}", permission));
                }
                meta.setDisplayName(ColorUtils.translateColorCodes( inGameTag));
                meta.setLore(list);
                item.setItemMeta(meta);
                tagGui.addItem(item);
            }
        }
        ItemStack barrier = new ItemStack(Material.BARRIER);
        ItemMeta barrierMeta = barrier.getItemMeta();
        barrierMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', BarrierCloseName));
        barrier.setItemMeta(barrierMeta);
        tagGui.setItem(49, barrier);

        ItemStack disable = new ItemStack(Material.NAME_TAG);
        ItemMeta disableMeta = barrier.getItemMeta();
        disableMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', DisableName));
        disable.setItemMeta(disableMeta);
        tagGui.setItem(53, disable);

        p.openInventory(tagGui);

    }


}
