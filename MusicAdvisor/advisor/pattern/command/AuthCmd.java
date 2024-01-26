package advisor.pattern.command;

import advisor.dto.Pagination;
import advisor.pattern.invoker.AuthButton;
import advisor.server.HTTPClientServer;
import advisor.server.SpotifyAuthService;

import java.io.IOException;

public class AuthCmd extends Command<SpotifyAuthService> {

    HTTPClientServer server;

    {
        try {
            server = new HTTPClientServer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final AuthButton button;

    public AuthCmd(AuthButton button) {
        this.button = button;
    }

    @Override
    public synchronized void execute(SpotifyAuthService service) {
        button.getAuthorizationCode(service, server);
        if (button.getAccessToken(service)) return;
        service.setLogged(true);
        service.okAuth();
        System.out.println();
    }

    @Override
    public void execute(SpotifyAuthService service, String s) {

    }

    @Override
    public Pagination getPagination() {
        return null;
    }


}
