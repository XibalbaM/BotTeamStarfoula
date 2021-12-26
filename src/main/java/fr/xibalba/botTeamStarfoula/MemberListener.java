package fr.xibalba.botTeamStarfoula;

import discord4j.core.event.domain.guild.MemberJoinEvent;
import discord4j.core.event.domain.guild.MemberLeaveEvent;
import discord4j.core.object.entity.Guild;

public class MemberListener {

    public static void join(MemberJoinEvent event) {

        event.getGuild().flatMap(Guild::getSystemChannel).block().createEmbed(legacyEmbedCreateSpec -> legacyEmbedCreateSpec.addField("Test", "SAlut", false)).subscribe();
    }

    public static void leave(MemberLeaveEvent event) {

    }
}