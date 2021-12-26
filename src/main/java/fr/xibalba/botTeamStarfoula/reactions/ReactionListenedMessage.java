package fr.xibalba.botTeamStarfoula.reactions;

import discord4j.common.util.Snowflake;
import discord4j.core.event.domain.message.ReactionAddEvent;
import discord4j.core.event.domain.message.ReactionRemoveEvent;
import discord4j.core.object.entity.Guild;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.TextChannel;
import discord4j.core.object.reaction.ReactionEmoji;
import fr.xibalba.botTeamStarfoula.Bot;

import java.util.List;
import java.util.function.Consumer;

public class ReactionListenedMessage {

    private Guild guild;
    private TextChannel channel;
    private Message message;
    private List<Reactions> reactionConsumer;

    public ReactionListenedMessage(Snowflake guildId, Snowflake channelId, Snowflake messageId, Reactions... reactions) {

        this.reactionConsumer = List.of(reactions);

        this.guild = Bot.getClient().getGuildById(guildId).block();
        this.channel = (TextChannel) guild.getChannelById(channelId).block();
        this.message = channel.getMessageById(messageId).block();
    }

    public Guild getGuild() {

        return guild;
    }

    public TextChannel getChannel() {

        return channel;
    }

    public Message getMessage() {

        return message;
    }

    public List<Reactions> getReactions() {

        return reactionConsumer;
    }

    public static record Reactions(ReactionEmoji emoji, Consumer<ReactionAddEvent> addEvent, Consumer<ReactionRemoveEvent> removeEvent) {}
}