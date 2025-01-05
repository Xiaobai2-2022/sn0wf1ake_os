/**
 * Provides the classes to process Random Requests
 *
 * @since 0.0.6
 */
package com.sn0wf1ake.backend.utils;

import java.util.Random;

public class SFRandomUtils {

    private Random rnd;

    public SFRandomUtils(long seed) {
        rnd = new Random(seed);
    }

    // Generate a random hex string with given seed of given length
    public String genHexStr(int length) {

        final int BOUND = 16;

        String str = "";

        for(int i = 0; i < length; ++i) {

            int randomChar = rnd.nextInt(BOUND);

            if(randomChar >= 0 && randomChar <= 9) {
                str += randomChar;
            } else {
                str += (char) ('a' + (randomChar - 10));
            }

        }

        return str;

    }

}
