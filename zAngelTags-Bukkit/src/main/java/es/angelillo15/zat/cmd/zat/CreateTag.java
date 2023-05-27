package es.angelillo15.zat.cmd.zat;

import es.angelillo15.zat.api.TagsInstance;
import es.angelillo15.zat.api.cmd.CommandData;
import es.angelillo15.zat.api.cmd.SubCommand;
import es.angelillo15.zat.api.cmd.sender.CommandSender;
import es.angelillo15.zat.api.config.Messages;
import es.angelillo15.zat.api.database.PluginConnection;
import es.angelillo15.zat.api.models.TagModel;

import java.sql.SQLException;

@CommandData(
        name = "createtag",
        description = "Create a tag",
        permission = "zat.admin",
        usage = "/zat createtag <tag>"
)
public class CreateTag extends SubCommand {
    @Override
    public String getName() {
        return "createtag";
    }

    @Override
    public String getDescription() {
        return "Create a tag";
    }

    @Override
    public String getSyntax() {
        return "/zat createtag <name> <permission> <display...>";
    }

    @Override
    public String getPermission() {
        return "zat.admin.create";
    }

    @Override
    public void onCommand(CommandSender sender, String label, String[] args) {
        new Thread(() -> {
            TagsInstance.getLogger().info(String.valueOf(args.length));
            if (args.length  < 4) {
                sender.sendMessage(Messages.Command.Zat.Create.usage());
                return;
            }

            String name = args[1];
            String permission = args[2];
            String display;

            StringBuilder sb = new StringBuilder();

            for (int i = 3; i < args.length; i++) {
                sb.append(args[i]).append(" ");
            }

            display = sb.toString().trim();

            boolean exists = TagModel.exists(name);

            if (exists) {
                sender.sendMessage(Messages.Command.Zat.Create.alreadyExists(name));
                return;
            }

            TagModel tag = new TagModel();

            tag.setName(name);
            tag.setPermission(permission);
            tag.setDisplayName(display);
            tag.setMaterial("STONE");

            try {
                PluginConnection.getStorm().save(tag);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            sender.sendMessage(Messages.Command.Zat.Create.success(name, permission, display));

        }).start();
    }
}
