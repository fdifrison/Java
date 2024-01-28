package advisor.pattern.command;

import advisor.Main;
import advisor.dto.Pagination;
import advisor.dto.ResponseError;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.List;

public abstract class Command<S> {
    S service;


    public abstract void execute(S service);

    public abstract void execute(S service, String s);

    public boolean isError(JsonObject response) {
        try {
            ResponseError error = new Gson().fromJson(response.get("error"), ResponseError.class);
            if (error == null) return false;
            System.out.println(error.getMessage());
            return true;
        } catch (JsonSyntaxException ignored) {
        }
        return false;
    }

    public <T> List<T> getItemList(JsonObject response, Class<? extends T> T) {

        var items = response.get(T.getSimpleName().toLowerCase()).getAsJsonObject().get("items").getAsJsonArray();

        List<T> itemList = new ArrayList<>();
        for (JsonElement item : items) itemList.add(new Gson().fromJson(item, T));
        return itemList;
    }

    public <T> Pagination setPagination(JsonObject response, Class<? extends T> T) {
        var root = response.get(T.getSimpleName().toLowerCase()).getAsJsonObject();
        var curr = root.get("href").getAsString();
        var next = root.get("next") == JsonNull.INSTANCE ? null : root.get("next").getAsString();
        var prev = root.get("previous") == JsonNull.INSTANCE ? null : root.get("previous").getAsString();
        var currPage = root.get("offset").getAsInt() / Main.ELEMENT_PER_PAGE;
        var pages = (int) Math.ceil(root.get("total").getAsDouble() / Main.ELEMENT_PER_PAGE);
        return new Pagination(curr, next, prev, currPage, pages);
    }

    public void printPage() {
        System.out.printf("---PAGE %d OF %d---%n", getPagination().getCurrPage() + 1, getPagination().getPages());
    }


    public abstract Pagination getPagination();

}
