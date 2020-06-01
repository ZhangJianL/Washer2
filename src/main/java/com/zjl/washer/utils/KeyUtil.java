package com.zjl.washer.utils;

import java.util.Random;

public class KeyUtil {
    /*
    * 生成的主键，用于orderId：由时间和随机数生成
    * */
    public static synchronized String genUniqueKey(){
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
