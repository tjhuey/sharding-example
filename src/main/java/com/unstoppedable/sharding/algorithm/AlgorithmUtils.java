package com.unstoppedable.sharding.algorithm;

public class AlgorithmUtils {

    public static int getTableIndex(int month) {
        if (month >=1 && month <= 3) {
            return 0;
        }
        if (month >=4 && month <= 6) {
            return 1;
        }
        if (month >=7 && month <= 9) {
            return 2;
        }
        if (month >=10 && month <= 12) {
            return 3;
        }
        throw new IllegalArgumentException("month must from 1 to 12");
    }
}
