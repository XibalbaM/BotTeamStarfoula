package fr.xibalba.botTeamStarfoula.commands;

import discord4j.rest.util.PermissionSet;

public class Command {

    private ICommandExecutor executor;
    private PermissionSet permissions;
    private String name;
    private String description;
    private String use;
    private String[] alias;

    public Command(ICommandExecutor executor, PermissionSet permissions, String name, String description, String use, String... alias) {

        this.executor = executor;
        this.permissions = permissions;
        this.name = name;
        this.description = description;
        this.use= use;
        this.alias = alias;
    }

    public ICommandExecutor getExecutor() {

        return executor;
    }

    public PermissionSet getPermissions() {

        return permissions;
    }

    public String getName() {

        return name;
    }

    public String getDescription() {

        return description;
    }

    public String getUse() {

        return use;
    }

    public String[] getAlias() {

        return alias;
    }
}