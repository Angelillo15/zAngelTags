package es.angelillo15.zangeltags.listener;

import es.angelillo15.zangeltags.ZAngelTags;
import es.angelillo15.zangeltags.config.ConfigLoader;
import es.angelillo15.zangeltags.database.SQLQuerys;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import java.util.List;

public class TagsInventoryClickEvent implements Listener {
    private ZAngelTags plugin;
    public TagsInventoryClickEvent(ZAngelTags plugin){
        this.plugin = plugin;
    }
    @EventHandler
    public void clickEvent(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        String titleConfig = ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&',ConfigLoader.getGuiConfig().getConfig().getString("Gui.tittle")));
        String titleStrip = ChatColor.stripColor(e.getView().getTitle());

        if(titleStrip.toLowerCase().startsWith(titleConfig.toLowerCase())){
            if(e.getCurrentItem() == null || e.getSlotType() == null || e.getCurrentItem().getType() == Material.AIR){
                e.setCancelled(true);
                return;
            }else{
                String close = ChatColor.translateAlternateColorCodes('&', ConfigLoader.getGuiConfig().getConfig().getString("Gui.barrierCloseName"));
                String disable = ChatColor.translateAlternateColorCodes('&', ConfigLoader.getGuiConfig().getConfig().getString("Gui.disableTag"));
                String DisableTag = ChatColor.translateAlternateColorCodes('&', ConfigLoader.getGuiConfig().getConfig().getString("Gui.disableTag"));
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals(close)){
                    e.getWhoClicked().closeInventory();
                    e.setCancelled(true);
                    return;
                }
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals(disable)){
                    p.performCommand("zat tag disable");
                    e.getWhoClicked().closeInventory();
                    e.setCancelled(true);
                    return;
                }
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals(DisableTag)){
                    p.performCommand("zat tag disable");

                    return;
                }

                List<String> tagsArraya = SQLQuerys.getTagsName(plugin.getConnection());

                p.performCommand("zat tag set "+tagsArraya.get(e.getSlot()));
                p.closeInventory();


                e.setCancelled(true);
            }
        }


    }
}
