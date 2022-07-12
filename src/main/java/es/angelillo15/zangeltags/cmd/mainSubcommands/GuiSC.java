package es.angelillo15.zangeltags.cmd.mainSubcommands;

import es.angelillo15.zangeltags.ZAngelTags;
import es.angelillo15.zangeltags.cmd.SubCommand;
import es.angelillo15.zangeltags.gui.TagsGui;
import org.bukkit.entity.Player;

public class GuiSC extends SubCommand {
    private ZAngelTags plugin;

    public GuiSC(ZAngelTags plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "Gui";
    }

    @Override
    public String getDescription() {
        return "Open plugin gui";
    }

    @Override
    public String getSyntax() {
        return "/zat gui or /zat";
    }

    @Override
    public String getPermission() {
        return "zAngelTags.list/";
    }

    @Override
    public void execute(Player player, String[] args) {
        TagsGui tagsGui = new TagsGui(plugin, player);
    }
}
