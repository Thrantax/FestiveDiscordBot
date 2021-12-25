package com.seasonal;

import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import com.jagrosh.jdautilities.examples.command.AboutCommand;
import com.jagrosh.jdautilities.examples.command.ShutdownCommand;
import com.seasonal.commands.CountDownCommand;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.exceptions.RateLimitedException;

import javax.security.auth.login.LoginException;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class BotLoader {

    public static void main(String[] args) throws IOException, LoginException, IllegalArgumentException, RateLimitedException {

        List<String> list = Files.readAllLines(Paths.get("configuration.txt"));

        String TOKEN = list.get(0);

        String OWNER_ID = list.get(1);

        String ACTIVITY = list.get(2);

        String COMMAND_SYNTAX = list.get(3);

        EventWaiter waiter = new EventWaiter();
        CommandClientBuilder client = new CommandClientBuilder();

        client.useDefaultGame();
        client.setOwnerId(OWNER_ID);
        client.setPrefix(COMMAND_SYNTAX);

        client.addCommands(
                // command to show information about the bot
                new AboutCommand(Color.BLUE, "an example bot",
                        new String[]{"Cool commands","Nice examples","Lots of fun!"},
                        new Permission[]{Permission.ADMINISTRATOR}),

                new CountDownCommand(),
                new ShutdownCommand());

        JDABuilder.createDefault(TOKEN).setStatus(OnlineStatus.ONLINE).setActivity(Activity.playing(ACTIVITY)).addEventListeners(waiter, client.build()).build();
    }
}

