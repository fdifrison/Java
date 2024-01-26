package advisor.pattern.command;

import advisor.dto.Pagination;
import advisor.server.SpotifyAPIService;

public class LastCmd extends Command<SpotifyAPIService> {

    Command<SpotifyAPIService> lastCommand;

    public LastCmd(Command<SpotifyAPIService> lastCommand) {
        this.lastCommand = lastCommand;
    }

    @Override
    public void execute(SpotifyAPIService service) {
    }

    @Override
    public void execute(SpotifyAPIService service, String s) {
        if ("next".equals(s)) {
            ifNext(service, s);
        } else if ("prev".equals(s)) {
            ifPrev(service, s);
        }
    }

    private void ifNext(SpotifyAPIService service, String s) {

        if (getPagination().hasNext()) {
            lastCommand.execute(service, getPagination().getNext());
        } else {
            System.out.println("No more pages.");
            lastCommand.execute(service, getPagination().getCurrent());
        }
    }


    private void ifPrev(SpotifyAPIService service, String s) {
        if (getPagination().hasPrev()) {
            lastCommand.execute(service, getPagination().getPrev());
        } else {
            System.out.println("No more pages.");
            lastCommand.execute(service, getPagination().getCurrent());
        }
    }


    @Override
    public Pagination getPagination() {
        return lastCommand.getPagination();
    }


}
