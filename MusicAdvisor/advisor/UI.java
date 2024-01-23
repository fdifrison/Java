package advisor;

import advisor.pattern.Receiver;
import advisor.pattern.command.AuthCmd;
import advisor.pattern.command.CategoryCmd;
import advisor.pattern.command.FeatureCmd;
import advisor.pattern.command.NewReleaseCmd;
import advisor.pattern.command.PlaylistCmd;
import advisor.pattern.command.QuitCmd;
import advisor.pattern.invoker.AuthButton;
import advisor.pattern.invoker.CategoryButton;
import advisor.pattern.invoker.FeatureButton;
import advisor.pattern.invoker.NewReleaseButton;
import advisor.pattern.invoker.PlaylistButton;
import advisor.pattern.invoker.QuitButton;

import java.util.Scanner;

public class UI {

    Scanner scanner = new Scanner(System.in);

    Receiver receiver = new Receiver(
            new AuthCmd((new AuthButton())),
            new NewReleaseCmd(new NewReleaseButton()),
            new FeatureCmd(new FeatureButton()),
            new CategoryCmd(new CategoryButton()),
            new PlaylistCmd(new PlaylistButton()),
            new QuitCmd(new QuitButton())
            );

    public void run() {
        while (true) {
            var input = scanner.nextLine();
            switch (input) {
                case "auth" -> receiver.getAuth();
                case "new" -> receiver.getNewRelease();
                case "featured" -> receiver.getFeatured();
                case "categories" -> receiver.getCategories();
                case "playlists Mood" -> receiver.getPlaylist();
                case "exit" -> {receiver.quit(); return;}
                default -> {
                    return;
                }
            }
        }
    }
}
