package fr.xibalba.botTeamStarfoula;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.guild.MemberJoinEvent;
import discord4j.core.event.domain.guild.MemberLeaveEvent;
import discord4j.core.object.entity.User;
import discord4j.gateway.intent.IntentSet;

import java.io.File;
import java.util.Scanner;

public class Bot {

    private static GatewayDiscordClient client;

    public static void start() {

        TomlManager tomlManager = new TomlManager(new File(System.getProperty("user.dir"), "config.toml"));

        client = DiscordClient.create(tomlManager.getToml().getString("token")).gateway().setEnabledIntents(
                IntentSet.all()).login().block();

        //ReactionManager.init();

        client.on(MemberJoinEvent.class).subscribe(MemberListener::join);
        client.on(MemberLeaveEvent.class).subscribe(MemberListener::leave);

        //client.on(MessageCreateEvent.class).subscribe(MessageManager::onMessage);

        //client.on(ReactionAddEvent.class).subscribe(ReactionManager::onReact);
        //client.on(ReactionRemoveEvent.class).subscribe(ReactionManager::onRemoveReact);


        final User self = client.getSelf().block();
        System.out.printf("Logged in as %s#%s%n", self.getUsername(), self.getDiscriminator());
        System.out.println("Invite link : https://discord.com/oauth2/authorize?client_id=" + client.getSelfId().asLong() + "&scope=bot&permissions=8");
        System.out.println("Type 'stop' to stop the bot");

        new Thread(() -> {

            Scanner scanner = new Scanner(System.in);

            while (!Thread.currentThread().isInterrupted()) {

                switch (scanner.nextLine()) {

                    case "stop": {

                        getClient().logout().subscribe();
                        Thread.currentThread().interrupt();

                        break;
                    }

                    default: {

                    }
                }
            }

        }, "Input Thread").start();

        client.onDisconnect().block();
    }

    public static GatewayDiscordClient getClient() {

        return client;
    }
}