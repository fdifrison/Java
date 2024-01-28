package advisor.pattern.invoker;


import advisor.dto.Playlists;

public class PlaylistButton {

    public void printDetails(Playlists playlists) {
        System.out.println(playlists.getName());
        System.out.println(playlists.getUrls().getUrl());
        System.out.println();
    }

}
