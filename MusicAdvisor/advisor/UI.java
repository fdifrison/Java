package advisor;

import advisor.pattern.Receiver;
import advisor.pattern.command.AuthCmd;
import advisor.pattern.command.CategoryCmd;
import advisor.pattern.command.FeaturedCmd;
import advisor.pattern.command.NewReleaseCmd;
import advisor.pattern.command.PlaylistCmd;
import advisor.pattern.command.QuitCmd;
import advisor.pattern.invoker.AuthButton;
import advisor.pattern.invoker.CategoryButton;
import advisor.pattern.invoker.FeatureButton;
import advisor.pattern.invoker.NewReleaseButton;
import advisor.pattern.invoker.PlaylistButton;
import advisor.pattern.invoker.QuitButton;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class UI {

    private final Scanner scanner = new Scanner(System.in);
    private static String input;

    Receiver receiver = new Receiver(
            new AuthCmd((new AuthButton())),
            new NewReleaseCmd(new NewReleaseButton()),
            new FeaturedCmd(new FeatureButton()),
            new CategoryCmd(new CategoryButton()),
            new PlaylistCmd(new PlaylistButton()),
            new QuitCmd(new QuitButton())
    );

    public void run() {
        while (true) {
            input = scanner.nextLine();
            String playlist = getPlaylist();
            switch (input) {
                case "auth" -> receiver.getAuth();
                case "new" -> {
                    receiver.resetLastCmd();
                    receiver.getNewRelease();
                }
                case "featured" -> {
                    receiver.resetLastCmd();
                    receiver.getFeatured();
                }
                case "categories" -> {
                    receiver.resetLastCmd();
                    receiver.getCategories();
                }
                case "playlists" -> {
                    receiver.resetLastCmd();
                    receiver.getPlaylist(playlist);
                }
                case "next" -> receiver.getLastCommandNext(input);
                case "prev" -> receiver.getLastCommandPrev(input);
                case "exit" -> {
                    receiver.quit();
                    return;
                }
                default -> {
                    return;
                }
            }
        }
    }

    private static String getPlaylist() {
        String playlist = null;
        if (input.startsWith("playlists")) {
            playlist = input.replace("playlists ", "");
            input = "playlists";
        }
        return playlist;
    }


}
