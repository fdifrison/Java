package advisor.pattern.command;

import advisor.dto.Categories;
import advisor.dto.Pagination;
import advisor.dto.Playlists;
import advisor.pattern.invoker.PlaylistButton;
import advisor.server.SpotifyAPIService;

public class PlaylistCmd extends Command<SpotifyAPIService> {
    private final PlaylistButton button;
    Pagination pagination;
    public PlaylistCmd(PlaylistButton button) {
        this.button = button;
    }

    @Override
    public void execute(SpotifyAPIService service) {

        var response = service.getCategories(100);
        if (isError(response)) return ;
        var categories = getItemList(response, Categories.class);

        var categoryId = categories.stream()
                .filter(cat -> cat.getName().equals(service.getSelectedPlaylist()))
                .map(Categories::getId)
                .findAny()
                .orElse(null);

        if (categoryId == null) {
            System.out.println("Unknown category name.");
            return;
        }

        response = service.getPlaylist(categoryId);
        if (isError(response)) return ;
        var playlists = getItemList(response, Playlists.class);
        pagination = setPagination(response, Playlists.class);

        playlists.forEach(button::printDetails);
        printPage();
    }

    @Override
    public void execute(SpotifyAPIService service, String paginationUri) {

        var response = service.getNextOrPrev(paginationUri);
        if (isError(response)) return;

        pagination = setPagination(response, Playlists.class);

        var playlists = getItemList(response, Playlists.class);
        playlists.forEach(button::printDetails);
        printPage();

    }


    @Override
    public Pagination getPagination() {
        return pagination;
    }

}
