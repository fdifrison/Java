package advisor.server;

import advisor.Main;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SpotifyAPIService implements IService {

    private final String API_ENDPOINT = Main.API_URL == null ? "https://api.spotify.com/v1/browse" : Main.API_URL + "/v1/browse";
    private JsonObject Token;
    private String selectedPlaylist;
    HttpClient client = HttpClient.newBuilder().build();
    HttpResponse<String> response;

    public JsonObject getNewReleases() {
        return getJsonObject("/new-releases", Main.ELEMENT_PER_PAGE);
    }

    public JsonObject getFeaturedPlaylists() {
        return getJsonObject("/featured-playlists", Main.ELEMENT_PER_PAGE);
    }

    public JsonObject getCategories(Integer pageSize) {
        return getJsonObject("/categories",pageSize);
    }

    public JsonObject getPlaylist(String categoryId) {
        return getJsonObject("/categories/" + categoryId + "/playlists", Main.ELEMENT_PER_PAGE);
    }

    private JsonObject getJsonObject(String apiPath, Integer pageSize) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .header("Authorization", "Bearer " + Token.get("access_token").getAsString())
                    .uri(URI.create(API_ENDPOINT + apiPath + "?limit=" + pageSize))
                    .GET()
                    .build();

            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return JsonParser.parseString(response.body()).getAsJsonObject();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public JsonObject getNextOrPrev(String uri) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .header("Authorization", "Bearer " + Token.get("access_token").getAsString())
                    .uri(URI.create(uri))
                    .GET()
                    .build();

            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return JsonParser.parseString(response.body()).getAsJsonObject();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public JsonObject getToken() {
        return Token;
    }

    public void setToken(JsonObject token) {
        Token = token;
    }

    public String getSelectedPlaylist() {
        return selectedPlaylist;
    }

    public void setSelectedPlaylist(String selectedPlaylist) {
        this.selectedPlaylist = selectedPlaylist;
    }
}
