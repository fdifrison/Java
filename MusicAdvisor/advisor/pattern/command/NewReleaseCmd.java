package advisor.pattern.command;

import advisor.dto.Albums;
import advisor.dto.Pagination;
import advisor.pattern.invoker.NewReleaseButton;
import advisor.server.SpotifyAPIService;

public class NewReleaseCmd extends Command<SpotifyAPIService> {

    private final NewReleaseButton newReleaseButton;

    public NewReleaseCmd(NewReleaseButton newReleaseButton) {
        this.newReleaseButton = newReleaseButton;
    }

    @Override
    public void execute(SpotifyAPIService service) {

        var response = service.getNewReleases();
        if (isError(response)) return ;
        var albums = getItemList(response, Albums.class);

        albums.forEach(this.newReleaseButton::printAlbumDetails);


    }

    @Override
    public void execute(SpotifyAPIService service, String s) {

    }

    @Override
    public Pagination getPagination() {
        return null;
    }


}



