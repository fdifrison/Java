package advisor.server;

import advisor.config.PropertyFileReader;
import advisor.utils.WebUtils;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.CountDownLatch;

public class HTTPClientServer {

    private static HttpServer server;

    private final CountDownLatch latch = new CountDownLatch(1);

    public String CODE;

    public HTTPClientServer() throws IOException {
    }

    public void listen() throws IOException {
        server = HttpServer.create();
        server.bind(new InetSocketAddress(PropertyFileReader.getServerPort()), 0);
        server.start();
    }

    public synchronized void start() throws IOException {

        //TODO should also control the return STATE against the SENDED one in the get request

        server.createContext("/",
                exchange -> {
                    String query = exchange.getRequestURI().getQuery();
                    if (query == null) {
                        query = "test";
                    }
                    String serverAnswerContent;
                    byte[] bytes;
                    var params = WebUtils.parseQueryToParams(query);
                    if (params.containsKey("code")) {
                        serverAnswerContent = "Got the code. Return back to your program.";
                        CODE = params.get("code");
                        bytes = serverAnswerContent.getBytes();
                        exchange.sendResponseHeaders(200, bytes.length);
                    } else {
                        serverAnswerContent = "Authorization code not found. Try again.";
                        bytes = serverAnswerContent.getBytes();
                        exchange.sendResponseHeaders(200, bytes.length);
                    }
                    exchange.getResponseBody().write(bytes);
                    exchange.getResponseBody().close();

                    latch.countDown();
                }
        );
    }

    public synchronized void stop() {
        server.stop(1);
    }

    public void awaitResponse() throws InterruptedException {
        latch.await();
    }

    public String getCODE() {
        return CODE;
    }
}



