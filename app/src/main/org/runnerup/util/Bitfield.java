

package org.runnerup.util;


public class Bitfield {

    public static boolean test(long flags, int bit) {
        long val = (1 << bit);
        return (flags & val) == val;
    }

    public static long set(long flags, int bit, boolean value) {
        if (value)
            return set(flags, bit);
        else
            return clear(flags, bit);
    }

    private static long set(long flags, int bit) {
        long val = (1 << bit);
        return flags | val;
    }

    private static long clear(long flags, int bit) {
        long val = (1 << bit);
        return flags & (~val);
    }
}
