package fr.xibalba.botTeamStarfoula;

import com.moandjiezana.toml.Toml;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TomlManager {

    private Toml toml;

    public TomlManager(File file) {

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Please enter the bot token: ");

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {

                writer.write("token = '" + new Scanner(System.in).nextLine() + "'");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        this.toml = new Toml().read(file);
    }

    public Toml getToml() {
        return toml;
    }

    public void setToml(Toml toml) {
        this.toml = toml;
    }
}
