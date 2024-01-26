package advisor.dto;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Albums {
    private List<Artist> artists;
    @SerializedName("name")
    private String title;
    @SerializedName("external_urls")
    private JsonObject urls;

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ExtUrls getUrls() {
        return new Gson().fromJson(urls, ExtUrls.class);
    }

    public void setUrls(JsonObject urls) {
        this.urls = urls;
    }
}
