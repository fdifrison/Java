package advisor.pattern.invoker;


import advisor.dto.Categories;

public class CategoryButton  {

    public void printCategoryDetails(Categories categories) {
        System.out.println(categories.getName());
    }
}
