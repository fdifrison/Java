package advisor.pattern.command;

import advisor.dto.Pagination;
import advisor.pattern.invoker.QuitButton;
import advisor.server.SpotifyAPIService;

public class QuitCmd extends Command<SpotifyAPIService> {

    private final QuitButton button;

    public QuitCmd(QuitButton button) {
        this.button = button;
    }

    @Override
    public void execute(SpotifyAPIService service) {
        button.print();
    }

    @Override
    public void execute(SpotifyAPIService service, String s) {

    }

    @Override
    public Pagination getPagination() {
        return null;
    }
}
