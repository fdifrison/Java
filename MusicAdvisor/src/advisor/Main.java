package advisor;


public class Main {

    //DOCUMENTATION
    // https://developer.spotify.com/documentation/web-api

    public static String AUTH_URL = null;
    public static String API_URL = null;

    public static int ELEMENT_PER_PAGE = 5;

    public static void main(String[] args) {

        if (args.length == 2 && args[0].equals("-access")) {
            AUTH_URL = args[1];
        } else if (args.length == 2 && args[0].equals("-resource")) {
            API_URL = args[1];
        } else if (args.length == 4) {
            AUTH_URL = args[1];
            API_URL = args[3];
        } else if (args.length == 6){
            AUTH_URL = args[1];
            API_URL = args[3];
            ELEMENT_PER_PAGE = Integer.parseInt(args[5]);
        }

        UI ui = new UI();
        ui.run();
    }
}
