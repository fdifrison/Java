package advisor.pattern.command;

import advisor.dto.Categories;
import advisor.dto.Pagination;
import advisor.pattern.invoker.CategoryButton;
import advisor.server.SpotifyAPIService;

import java.util.List;

public class CategoryCmd extends Command<SpotifyAPIService> {

    private final CategoryButton button;

    public CategoryCmd(CategoryButton button) {
        this.button = button;
    }

    @Override
    public void execute(SpotifyAPIService service) {

        var response = service.getCategories();

        if (isError(response)) return ;

        List<Categories> categories = getItemList(response, Categories.class);

        categories.forEach(button::printCategoryDetails);

    }

    @Override
    public void execute(SpotifyAPIService service, String s) {

    }

    @Override
    public Pagination getPagination() {
        return null;
    }
}
