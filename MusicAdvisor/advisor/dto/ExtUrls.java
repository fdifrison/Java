package advisor.dto;

import com.google.gson.annotations.SerializedName;

public class ExtUrls {
    @SerializedName("spotify")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
