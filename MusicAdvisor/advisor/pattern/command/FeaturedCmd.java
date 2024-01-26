package advisor.pattern.command;

import advisor.dto.Playlists;
import advisor.pattern.invoker.FeatureButton;
import advisor.server.SpotifyAPIService;

public class FeaturedCmd extends Command<SpotifyAPIService> {

    private final FeatureButton button;

    public FeaturedCmd(FeatureButton button) {
        this.button = button;
    }

    @Override
    public void execute(SpotifyAPIService service) {

        var response = service.getFeaturedPlaylists();
        if (isError(response)) return ;
        var playlists = getItemList(response, Playlists.class);

        playlists.forEach(button::printPlaylistDetails);

    }
}
