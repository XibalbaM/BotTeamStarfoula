package fr.xibalba.botTeamStarfoula.commands.impl;

import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.rest.util.PermissionSet;
import fr.xibalba.botTeamStarfoula.commands.Command;
import fr.xibalba.botTeamStarfoula.commands.ICommandExecutor;

public class PingCommand extends Command {

    public PingCommand() {

        super(new Executor(), PermissionSet.none(), "Ping", "Test the ping of the bot", "!ping", "ping");
    }

    static class Executor implements ICommandExecutor {

        @Override
        public void execute(MessageCreateEvent event) {

            System.out.println("ping");
        }
    }
}