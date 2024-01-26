package advisor.pattern.invoker;

import advisor.server.HTTPClientServer;
import advisor.server.SpotifyAuthService;

import java.io.IOException;

public class AuthButton {

    public boolean getAccessToken(SpotifyAuthService authService) {
        System.out.println("making http request for access_token...");
        var authResponse = authService.authenticate();
        if (authResponse.has("error")) {
            System.out.println("Invalid authorization code");
            return true;
        }
        System.out.println(authResponse);
        return false;
    }

    public void getAuthorizationCode(SpotifyAuthService authService, HTTPClientServer server) {
        var response = authService.authorize();
        if (response != null) {
            System.out.println("use link to request access code:");
            System.out.println(response.uri());
            try {
                server.listen();
                server.start();
                server.awaitResponse();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("code received");
        authService.setCODE(server.getCODE());
    }
}
