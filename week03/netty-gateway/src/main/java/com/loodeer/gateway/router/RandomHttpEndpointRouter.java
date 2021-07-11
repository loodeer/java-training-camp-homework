package com.loodeer.gateway.router;

import java.util.List;
import java.util.Random;

/**
 * @author luzuheng
 * @since 2021-07-11 17:27
 */
public class RandomHttpEndpointRouter implements HttpEndpointRouter {
    @Override public String route(List<String> urls) {
        int size = urls.size();
        Random random = new Random(System.currentTimeMillis());
        return urls.get(random.nextInt(size));
    }
}
