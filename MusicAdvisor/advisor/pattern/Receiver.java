package advisor.pattern;

import advisor.Menu;
import advisor.pattern.command.ICommand;

public class Receiver {

    boolean login = false;
    ICommand authCmd;
    ICommand newReleaseCmd;
    ICommand featuredCmd;
    ICommand categoriesCdm;
    ICommand playlistCmd;
    ICommand quitCmd;

    public Receiver(ICommand authCmd, ICommand newReleaseCmd, ICommand featuredCmd, ICommand categoriesCdm,
                    ICommand playlistCmd, ICommand quitCmd) {
        this.authCmd = authCmd;
        this.newReleaseCmd = newReleaseCmd;
        this.featuredCmd = featuredCmd;
        this.categoriesCdm = categoriesCdm;
        this.playlistCmd = playlistCmd;
        this.quitCmd = quitCmd;
    }

    public void getAuth() {
        authCmd.execute();
        login = true;
    }

    public void getNewRelease() {
        if (login) newReleaseCmd.execute();
        else Menu.noAuth();
    }

    public void getFeatured() {
        if (login) featuredCmd.execute();
        else Menu.noAuth();
    }

    public void getCategories() {
        if (login) categoriesCdm.execute();
        else Menu.noAuth();
    }
    public void getPlaylist() {
        if (login) playlistCmd.execute();
        else Menu.noAuth();
    }
    public void quit() {

    }


}
