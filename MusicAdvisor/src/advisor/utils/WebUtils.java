package advisor.utils;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class WebUtils {

    public static Map<String, String> parseQueryToParams(String query) {

        Map<String, String> result = new HashMap<>();
        if (query == null) {
            return result;
        }

        int last = 0;
        int next;
        int l = query.length();

        while (last < l) {
            next = query.indexOf('&', last);
            if (next == -1) {
                next = l;
            }

            if (next > last) {
                int eqPos = query.indexOf('=', last);
                if (eqPos < 0 || eqPos > next) {
                    result.put(URLDecoder.decode(query.substring(last, next), StandardCharsets.UTF_8), "");
                } else {
                    result.put(URLDecoder.decode(query.substring(last, eqPos), StandardCharsets.UTF_8),
                            URLDecoder.decode(query.substring(eqPos + 1, next), StandardCharsets.UTF_8));
                }
            }
            last = next + 1;
        }
        return result;
    }

    public static String buildQueryString(Map<String, String> params) {
        return params.entrySet().stream()
                .map(entry -> entry.getKey() + "=" +
                        entry.getValue())
                .collect(Collectors.joining("&"));
    }

}
