package com.fykj.scaffold.support.utils;

import java.util.UUID;

/**
 * Created by ace on 2017/9/27.
 */
public class UUIDUtils {

    /**
     * 生成8位UUId
     *
     * @return
     */
    public static String generateShortUuid() {
        StringBuilder stringBuilder = new StringBuilder();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            stringBuilder.append(chars()[x % 0x3E]);
        }
        return stringBuilder.toString();

    }

    /**
     * 生成32位UUID
     *
     * @return
     */
    public static String generateUuid() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replace("-", "");
    }

    private static String[] chars() {
        return new String[]{"a", "b", "c", "d", "e", "f",
                "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
                "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
                "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
                "W", "X", "Y", "Z"};
    }

    private UUIDUtils() {
        throw new IllegalStateException("Utilily calss");
    }
}
