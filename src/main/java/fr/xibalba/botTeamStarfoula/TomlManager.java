package fr.xibalba.botTeamStarfoula;

import com.moandjiezana.toml.Toml;

import java.io.File;

public class TomlManager {

    private Toml toml;

    public TomlManager(File file) {

        this.toml = new Toml().read(file);
    }

    public Toml getToml() {
        return toml;
    }

    public void setToml(Toml toml) {
        this.toml = toml;
    }
}
