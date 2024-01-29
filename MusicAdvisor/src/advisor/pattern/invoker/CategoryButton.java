package advisor.pattern.invoker;


import advisor.dto.Categories;

public class CategoryButton  {

    public void printDetails(Categories categories) {
        System.out.println(categories.getName());
    }
}
