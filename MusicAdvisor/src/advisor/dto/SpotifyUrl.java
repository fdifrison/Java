package advisor.dto;

import com.google.gson.JsonPrimitive;
import com.google.gson.annotations.SerializedName;

public class SpotifyUrl {
    @SerializedName("spotify")
    private JsonPrimitive url;

    public JsonPrimitive getUrl() {
        return url;
    }

    public void setUrl(JsonPrimitive url) {
        this.url = url;
    }
}
