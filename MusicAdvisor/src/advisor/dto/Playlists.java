package advisor.dto;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class Playlists {

    private String name;

    @SerializedName("external_urls")
    private JsonObject urls;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ExtUrls getUrls() {
        return new Gson().fromJson(urls, ExtUrls.class);
    }

    public void setUrls(JsonObject urls) {
        this.urls = urls;
    }
}
