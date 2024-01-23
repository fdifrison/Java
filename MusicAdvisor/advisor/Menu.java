package advisor;

public class Menu {

    public static void release() {
        var text = """
                ---NEW RELEASES---
                Mountains [Sia, Diplo, Labrinth]
                Runaway [Lil Peep]
                The Greatest Show [Panic! At The Disco]
                All Out Life [Slipknot]
                """;
        System.out.print(text);
    }


    public static void featured() {
        var text = """
                ---FEATURED---
                Mellow Morning
                Wake Up and Smell the Coffee
                Monday Motivation
                Songs to Sing in the Shower
                """;
        System.out.println(text);
    }

    public static void categories() {
        var text = """
                ---CATEGORIES---
                Top Lists
                Pop
                Mood
                Latin
                """;
        System.out.println(text);
    }

    public static void playlist() {
        var text = """
                ---MOOD PLAYLISTS---
                Walk Like A Badass
                Rage Beats
                Arab Mood Booster
                Sunday Stroll
                """;
        System.out.println(text);
    }

    public static void goodbye() {
        var text = """
                ---GOODBYE!---
                """;
        System.out.println(text);
    }

    public static void auth() {
        var text = """
                https://accounts.spotify.com/authorize?client_id=a19ee7dbfda443b2a8150c9101bfd645&redirect_uri=http://localhost:8080&response_type=code
                ---SUCCESS---
                """;
        System.out.print(text);
    }

    public static void noAuth() {
        System.out.println("Please, provide access for application.");
    }


}
