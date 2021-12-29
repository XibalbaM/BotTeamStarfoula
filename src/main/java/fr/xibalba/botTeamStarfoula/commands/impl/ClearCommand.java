package fr.xibalba.botTeamStarfoula.commands.impl;

import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.rest.util.Permission;
import discord4j.rest.util.PermissionSet;
import fr.xibalba.botTeamStarfoula.commands.Command;
import fr.xibalba.botTeamStarfoula.commands.ICommandExecutor;

public class ClearCommand extends Command {

    public ClearCommand() {

        super(new Executor(), PermissionSet.of(Permission.MANAGE_MESSAGES), "Clear", "Delete all messages of the channel", "!clear", "clear");
    }

    static class Executor implements ICommandExecutor {

        @Override
        public void execute(MessageCreateEvent event) {

            for (Message message : event.getMessage().getChannel().map(messageChannel -> messageChannel.getMessagesBefore(event.getMessage().getId())).block().collectList().block()) {
                message.delete().subscribe();
            }

            event.getMessage().getChannel().map(messageChannel -> messageChannel.createMessage("All messages deleted"));
        }
    }
}