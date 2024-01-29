package advisor.server;

import advisor.Main;
import advisor.config.PropertyFileReader;
import advisor.utils.WebUtils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SpotifyAuthService implements IService{
    private final String CLIENT_ID = PropertyFileReader.getClientId();
    private final String CLIENT_SECRET = PropertyFileReader.getClientSecret();
    private final String STATE = String.valueOf(UUID.randomUUID());
    private final String REDIRECT_URI = "http://localhost:8080";
    private final String BASE_ENDPOINT = Main.AUTH_URL == null ? "https://accounts.spotify.com" : Main.AUTH_URL;
    private final String AUTH_END_POINT =  BASE_ENDPOINT + "/authorize?";
    private final String TOKEN_END_POINT = BASE_ENDPOINT + "/api/token?";

    private String CODE;

    private boolean logged = false;
    private JsonObject accessToken;

    HttpClient client = HttpClient.newBuilder().build();

    Map<String, String> authQueryParams;

    {
        authQueryParams = new HashMap<>();
        authQueryParams.put("response_type", "code");
        authQueryParams.put("client_id", CLIENT_ID);
        authQueryParams.put("scope", "");
        authQueryParams.put("redirect_uri", REDIRECT_URI);
        authQueryParams.put("state", STATE);
    }


    String authorizationUrl;

    {
        authorizationUrl = AUTH_END_POINT + WebUtils.buildQueryString(authQueryParams);
    }

    public HttpResponse<String> authorize() {
        HttpResponse<String> response;
        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .version(HttpClient.Version.HTTP_2)
                .uri(URI.create(authorizationUrl))
                .GET()
                .build();
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public JsonObject authenticate() {
        HttpResponse<String> response;

        var encodedString = Base64.getEncoder().encodeToString((CLIENT_ID + ":" + CLIENT_SECRET).getBytes());

        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", "Basic " + encodedString)
                .version(HttpClient.Version.HTTP_2)
                .uri(URI.create(TOKEN_END_POINT))
                .POST(HttpRequest.BodyPublishers.ofString("code=" + CODE +
                        "&redirect_uri=" + REDIRECT_URI +
                        "&grant_type=authorization_code"))
                .build();
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            var token= JsonParser.parseString(response.body()).getAsJsonObject();
            setAccessToken(token);
            return token;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void okAuth() {
        System.out.print("---SUCCESS---");
    }
    public void noAuth() {
        System.out.println("Please, provide access for application.");
    }


    public void setCODE(String CODE) {
        this.CODE = CODE;
    }

    public JsonObject getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(JsonObject accessToken) {
        this.accessToken = accessToken;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }
}
