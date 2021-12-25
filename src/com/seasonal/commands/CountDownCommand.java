package com.seasonal.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import com.seasonal.misc.CountDownEvent;
import com.seasonal.misc.Seasons;

import java.util.Locale;

@CommandInfo(
        name = {"countdown", "cd"},
        description = "his command sends a pin to use in-game by using /link #### to link your Discord account to your MineCraft account, this allows you to receive Discord notifications about your account status, special discounts and more."
)
public class CountDownCommand extends Command {

    public CountDownCommand() {
        this.name = "cd";
        this.help = "Use the countdown to seasonal dates currently you can use the following: \n Easter: \n `easter` \n `holy` \n Christmas: \n `xmas` \n `christmas` \n `chrimbo` \n and finally for Halloween \n `hween` \n `halloween` \n `h'ween`.";
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent commandEvent) {
        if(commandEvent.getArgs().isEmpty()) {
            commandEvent.reply("You didn't supply anything");
        }
        switch (commandEvent.getArgs().toLowerCase(Locale.ROOT)) {
            case "xmas":
            case "christmas":
            case "chrimbo":
                CountDownEvent.genericCountDown(commandEvent, Seasons.XMAS);
                break;
            case "easter":
            case "holy":
                commandEvent.reply("Need to re-do.");
                break;
            case "hween":
            case "halloween":
            case "h'ween":
                CountDownEvent.genericCountDown(commandEvent, Seasons.HALLOWEEN);
                break;
            default:
                commandEvent.reply("I'm not sure what Season this is, if you feel this in correct please contact Thrantax#0001");
                break;
        }
    }
}
