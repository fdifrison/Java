package advisor.pattern;

import advisor.pattern.command.Command;
import advisor.server.SpotifyAPIService;
import advisor.server.SpotifyAuthService;

public class Receiver {

    Command<SpotifyAuthService> authCmd;
    Command<SpotifyAPIService> newReleaseCmd;
    Command<SpotifyAPIService> featuredCmd;
    Command<SpotifyAPIService> categoriesCdm;
    Command<SpotifyAPIService> playlistCmd;
    Command<SpotifyAPIService> quitCmd;

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
        } else authService.noAuth();
    }

    public void getFeatured() {
        if (authService.isLogged()) featuredCmd.execute(apiService);
        else authService.noAuth();
    }

    public void getCategories() {
        if (authService.isLogged()) categoriesCdm.execute(apiService);
        else authService.noAuth();
    }

    public void getPlaylist(String playlist) {
        apiService.setSelectedPlaylist(playlist);
        if (authService.isLogged()) playlistCmd.execute(apiService);
        else authService.noAuth();
    }

    public void quit() {
    }

}
