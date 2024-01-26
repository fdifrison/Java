package advisor.pattern.invoker;


import advisor.dto.Playlists;

public class FeatureButton {

    public void printPlaylistDetails(Playlists playlists) {
        System.out.println(playlists.getName());
        System.out.println(playlists.getUrls().getUrl());
        System.out.println();
    }
}
