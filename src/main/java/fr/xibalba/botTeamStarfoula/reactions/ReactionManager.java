package fr.xibalba.botTeamStarfoula.reactions;

import discord4j.core.event.domain.message.ReactionAddEvent;
import discord4j.core.event.domain.message.ReactionRemoveEvent;

import java.util.List;

public class ReactionManager {

    private static List<ReactionListenedMessage> listenedMessages = List.of();

    public static void init() {

        for (ReactionListenedMessage listenedMessage : listenedMessages) {
            for (ReactionListenedMessage.Reactions reaction : listenedMessage.getReactions()) {
                listenedMessage.getMessage().addReaction(reaction.emoji()).subscribe();
            }
        }
    }

    public static void onReact(ReactionAddEvent event) {

        for (ReactionListenedMessage listenedMessage : listenedMessages)
            if (event.getMessage().map(message -> message.getId()).block().asLong() == listenedMessage.getMessage().getId().asLong())
                for (ReactionListenedMessage.Reactions reaction : listenedMessage.getReactions())
                    if (reaction.emoji().equals(event.getEmoji())) {
                        reaction.addEvent().accept(event);
                        break;
                    }
    }

    public static void onRemoveReact(ReactionRemoveEvent event) {

        for (ReactionListenedMessage listenedMessage : listenedMessages)
            if (event.getMessage().map(message -> message.getId()).block().asLong() == listenedMessage.getMessage().getId().asLong())
                for (ReactionListenedMessage.Reactions reaction : listenedMessage.getReactions())
                    if (reaction.emoji().equals(event.getEmoji())) {
                        reaction.removeEvent().accept(event);
                        break;
                    }
    }
}