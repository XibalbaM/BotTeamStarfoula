package fr.xibalba.botTeamStarfoula.commands;

import discord4j.core.event.domain.message.MessageCreateEvent;

public interface ICommandExecutor {

    void execute(MessageCreateEvent event);
}