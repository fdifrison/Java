package advisor.pattern.command;

import advisor.dto.Categories;
import advisor.dto.Pagination;
import advisor.dto.Playlists;
import advisor.pattern.invoker.CategoryButton;
import advisor.server.SpotifyAPIService;

import java.util.List;

public class CategoryCmd extends Command<SpotifyAPIService> {

    private final CategoryButton button;
    Pagination pagination;

    public CategoryCmd(CategoryButton button) {
        this.button = button;
    }

    @Override
    public void execute(SpotifyAPIService service) {

        var response = service.getCategories();
        if (isError(response)) return ;

        pagination = setPagination(response, Categories.class);

        var categories = getItemList(response, Categories.class);

        categories.forEach(button::printDetails);
        printPage();

    }


    @Override
    public void execute(SpotifyAPIService service, String paginationUri) {

        var response = service.getNextOrPrev(paginationUri);
        if (isError(response)) return;

        pagination = setPagination(response, Categories.class);

        var categories = getItemList(response, Categories.class);
        categories.forEach(button::printDetails);
        printPage();

    }


    @Override
    public Pagination getPagination() {
        return pagination;
    }
}