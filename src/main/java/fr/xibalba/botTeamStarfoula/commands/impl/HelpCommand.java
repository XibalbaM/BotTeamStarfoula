package fr.xibalba.botTeamStarfoula.commands.impl;

import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.rest.util.Permission;
import discord4j.rest.util.PermissionSet;
import fr.xibalba.botTeamStarfoula.commands.Command;
import fr.xibalba.botTeamStarfoula.commands.CommandType;
import fr.xibalba.botTeamStarfoula.commands.ICommandExecutor;

public class HelpCommand extends Command {

    public HelpCommand() {

        super(new Executor(), PermissionSet.none(), "Help", "Help you about bot commands", "!help [command]", "help", "h");
    }

    static class Executor implements ICommandExecutor {

        @Override
        public void execute(MessageCreateEvent event) {

            String command = event.getMessage().getContent();

            if (command.contains(" ")) {

                String commandName = command.substring(command.indexOf(" ") + 1);

                for (CommandType type : CommandType.values()) {

                    for (Command valueCommand : type.getCommands()) {

                        for (String alias : valueCommand.getAlias()) {

                            if (alias.equalsIgnoreCase(commandName)) {

                                String aliases = "";
                                for (String valueCommandAlias : valueCommand.getAlias()) {
                                    aliases += valueCommandAlias + ", ";
                                }
                                String finalAliases = aliases.substring(0, aliases.length() - 2);

                                String permissions = "";
                                for (Permission permission : valueCommand.getPermissions()) {
                                    permissions += permission.name() + ", ";
                                }
                                if (permissions == "")
                                    permissions = "none";
                                else
                                    permissions = permissions.substring(0, permissions.length() - 2);
                                String finalPermissions = permissions;

                                event.getMessage().getChannel().flatMap(messageChannel -> messageChannel.createMessage(valueCommand.getName() + " : \n\t" + valueCommand.getDescription() + "\n\t Usage : " + valueCommand.getUse() + "\n\t Permissions : " + finalPermissions + "\n\t Alias : " + finalAliases)).subscribe();
                                return;
                            }
                        }
                    }
                }

                event.getMessage().getChannel().flatMap(messageChannel -> messageChannel.createMessage("You need to specify a valid command")).subscribe();
            } else {

                String result = "Commands : ";


                for (CommandType type : CommandType.values()) {

                    for (Command valueCommand : type.getCommands()) {

                        String aliases = "";
                        for (String valueCommandAlias : valueCommand.getAlias()) {
                            aliases += valueCommandAlias + ", ";
                        }
                        String finalAliases = aliases.substring(0, aliases.length() - 2);

                        String permissions = "";
                        for (Permission permission : valueCommand.getPermissions()) {
                            permissions += permission.name() + ", ";
                        }
                        if (permissions == "")
                            permissions = "none";
                        else
                            permissions = permissions.substring(0, permissions.length() - 2);
                        String finalPermissions = permissions;

                        result += ("\n\t" + valueCommand.getName() + " : \n\t\t" + valueCommand.getDescription() + "\n\t\t Usage : " + valueCommand.getUse() + "\n\t\t Permissions : " + finalPermissions + "\n\t\t Alias : " + finalAliases);
                    }
                }

                String finalResult = result;
                event.getMessage().getChannel().flatMap(messageChannel -> messageChannel.createMessage(finalResult)).subscribe();
            }
        }
    }
}