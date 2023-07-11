package org.tattour.server.global.util;

import java.util.concurrent.ThreadLocalRandom;
import org.springframework.stereotype.Component;

@Component
public class RandomNumberGenerator {
    public int generateRandomNumber(){
        return ThreadLocalRandom.current().nextInt(100000, 1000000);
    }
}
