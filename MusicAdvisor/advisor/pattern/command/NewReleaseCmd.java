package advisor.pattern.command;

import advisor.dto.Albums;
import advisor.dto.Pagination;
import advisor.dto.Playlists;
import advisor.pattern.invoker.NewReleaseButton;
import advisor.server.SpotifyAPIService;

public class NewReleaseCmd extends Command<SpotifyAPIService> {

    private final NewReleaseButton button;
    Pagination pagination;

    public NewReleaseCmd(NewReleaseButton button) {
        this.button = button;
    }

    @Override
    public void execute(SpotifyAPIService service) {

        var response = service.getNewReleases();
        if (isError(response)) return;

        pagination = setPagination(response, Albums.class);

        var albums = getItemList(response, Albums.class);

        albums.forEach(button::printDetails);
        printPage();

    }

    @Override
    public void execute(SpotifyAPIService service, String paginationUri) {

        var response = service.getNextOrPrev(paginationUri);
        if (isError(response)) return;

        pagination = setPagination(response, Albums.class);

        var albums = getItemList(response, Albums.class);
        albums.forEach(button::printDetails);
        printPage();

    }


    @Override
    public Pagination getPagination() {
        return pagination;
    }
}


