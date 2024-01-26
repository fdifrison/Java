package advisor.pattern.command;

import advisor.Main;
import advisor.dto.Pagination;
import advisor.dto.Playlists;
import advisor.pattern.invoker.FeatureButton;
import advisor.server.SpotifyAPIService;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

public class FeaturedCmd extends Command<SpotifyAPIService> {

    private final FeatureButton button;

    Pagination pagination;

    public FeaturedCmd(FeatureButton button) {
        this.button = button;
    }

    @Override
    public void execute(SpotifyAPIService service) {

        var response = service.getFeaturedPlaylists();
        if (isError(response)) return;

        pagination = setPagination(response, Playlists.class);

        var playlists = getItemList(response, Playlists.class);

        playlists.forEach(button::printPlaylistDetails);
        System.out.printf("---PAGE %d OF %d---%n", pagination.getCurrPage() + 1, pagination.getPages());

    }

    @Override
    public void execute(SpotifyAPIService service, String paginationUri) {

        var response = service.getNextOrPrev(paginationUri);
        if (isError(response)) return;

        pagination = setPagination(response, Playlists.class);

        var playlists = getItemList(response, Playlists.class);
        playlists.forEach(button::printPlaylistDetails);
        System.out.printf("---PAGE %d OF %d---%n", pagination.getCurrPage() + 1, pagination.getPages());

    }


    @Override
    public Pagination getPagination() {
        return pagination;
    }
}
