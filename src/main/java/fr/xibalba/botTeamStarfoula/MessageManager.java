package fr.xibalba.botTeamStarfoula;

import discord4j.core.event.domain.message.MessageCreateEvent;
import fr.xibalba.botTeamStarfoula.commands.Command;
import fr.xibalba.botTeamStarfoula.commands.CommandType;

public class MessageManager {

    public static void onMessage(MessageCreateEvent event) {

        String content = event.getMessage().getContent();

        for (CommandType commandType : CommandType.values()) {

            if (content.startsWith(commandType.getPrefix())) {

                String commandName = content.substring(commandType.getPrefix().length());

                for (Command command : commandType.getCommands()) {

                    for (String alias : command.getAlias()) {

                        if (commandName.startsWith(alias)) {

                            if (event.getMember().get().getBasePermissions().block().containsAll(command.getPermissions())) {
                                command.getExecutor().execute(event);
                            } else {
                                event.getMessage().getChannel().flatMap(messageChannel -> messageChannel.createMessage("You have not the permission to execute the command " + command.getName())).subscribe();
                            }
                        }
                    }
                }
            }
        }
    }
}