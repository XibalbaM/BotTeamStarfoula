package fr.xibalba.botTeamStarfoula;

import discord4j.core.event.domain.guild.MemberJoinEvent;
import discord4j.core.event.domain.guild.MemberLeaveEvent;
import discord4j.core.object.entity.Guild;
import discord4j.core.object.entity.Member;

import java.time.Instant;

public class MemberListener {

    public static void join(MemberJoinEvent event) {

        Member member = event.getMember();

        event.getGuild().flatMap(Guild::getSystemChannel).block().createEmbed(legacyEmbedCreateSpec -> legacyEmbedCreateSpec.setTitle("Bienvenu").setTimestamp(
                Instant.now()).setThumbnail(member.getAvatarUrl()).setDescription(member.getMention() + " nous à rejoint ! Nous somme maintenant " +
                                                                              event.getGuild().map(guild -> guild.getMemberCount()).block() + " sur le serveur")).subscribe();
    }

    public static void leave(MemberLeaveEvent event) {

    }
}