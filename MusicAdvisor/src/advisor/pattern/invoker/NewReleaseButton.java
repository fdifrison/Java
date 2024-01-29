package advisor.pattern.invoker;


import advisor.dto.Albums;

public class NewReleaseButton  {

    public void printDetails(Albums a) {
        System.out.println(a.getTitle());
        System.out.println(a.getArtists());
        System.out.println(a.getUrls().getUrl());
        System.out.println();
    }
}
