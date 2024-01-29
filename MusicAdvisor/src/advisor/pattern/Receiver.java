package advisor.pattern;

import advisor.pattern.command.Command;
import advisor.pattern.command.LastCmd;
import advisor.server.SpotifyAPIService;
import advisor.server.SpotifyAuthService;

public class Receiver {

    Command<SpotifyAuthService> authCmd;
    Command<SpotifyAPIService> newReleaseCmd;
    Command<SpotifyAPIService> featuredCmd;
    Command<SpotifyAPIService> categoriesCdm;
    Command<SpotifyAPIService> playlistCmd;
    Command<SpotifyAPIService> quitCmd;

    LastCmd lastCmd;

    SpotifyAuthService authService = new SpotifyAuthService();
    SpotifyAPIService apiService = new SpotifyAPIService();

    public Receiver(Command<SpotifyAuthService> authCmd,
                    Command<SpotifyAPIService> newReleaseCmd,
                    Command<SpotifyAPIService> featuredCmd,
                    Command<SpotifyAPIService> categoriesCdm,
                    Command<SpotifyAPIService> playlistCmd,
                    Command<SpotifyAPIService> quitCmd) {
        this.authCmd = authCmd;
        this.newReleaseCmd = newReleaseCmd;
        this.featuredCmd = featuredCmd;
        this.categoriesCdm = categoriesCdm;
        this.playlistCmd = playlistCmd;
        this.quitCmd = quitCmd;
    }

    public void getAuth() {
        authCmd.execute(authService);
        apiService.setToken(authService.getAccessToken());
    }

    public void getNewRelease() {
        if (authService.isLogged()) {
            newReleaseCmd.execute(apiService);
            this.lastCmd = new LastCmd(newReleaseCmd);
        } else authService.noAuth();
    }

    public void getFeatured() {
        if (authService.isLogged()) {
            featuredCmd.execute(apiService);
            this.lastCmd = new LastCmd(featuredCmd);
        } else authService.noAuth();
    }

    public void getCategories() {
        if (authService.isLogged()) {
            categoriesCdm.execute(apiService);
            this.lastCmd = new LastCmd(categoriesCdm);
        } else authService.noAuth();
    }

    public void getPlaylist(String playlist) {
        apiService.setSelectedPlaylist(playlist);
        if (authService.isLogged()) {
            playlistCmd.execute(apiService);
            this.lastCmd = new LastCmd(playlistCmd);
        }
        else authService.noAuth();
    }

    public void getLastCommandNext(String s) {
        if (lastCmd == null) return;
        lastCmd.execute(apiService, s);
    }

    public void getLastCommandPrev(String s) {
        if (lastCmd == null) return;
        lastCmd.execute(apiService, s);
    }

    public void quit() {
        quitCmd.execute(apiService);
    }


    public void resetLastCmd() {
        lastCmd = null;
    }

}
