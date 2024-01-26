package advisor.pattern.command;

import advisor.dto.ResponseError;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.List;

public abstract class Command<S> {
    S service;

    public abstract void execute(S service);

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

}
