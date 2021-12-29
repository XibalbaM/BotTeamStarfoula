package fr.xibalba.botTeamStarfoula.commands;

import fr.xibalba.botTeamStarfoula.commands.impl.ClearCommand;
import fr.xibalba.botTeamStarfoula.commands.impl.HelpCommand;
import fr.xibalba.botTeamStarfoula.commands.impl.PingCommand;

public enum CommandType {
    GLOBAL(new Command[]{
            new PingCommand(),
            new HelpCommand(),
            new ClearCommand(),
    }, "!");

    private Command[] commands;
    private String prefix;

    CommandType(Command[] commands, String prefix) {

        this.commands = commands;
        this.prefix = prefix;
    }

    public Command[] getCommands() {

        return commands;
    }

    public String getPrefix() {

        return prefix;
    }
}