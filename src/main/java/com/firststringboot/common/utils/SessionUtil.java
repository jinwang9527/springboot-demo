package com.firststringboot.common.utils;

import java.util.HashMap;

public class SessionUtil {

    //K:用户id值 V:对应的cookie值
    private static HashMap<Integer, String> map;

    static {
        map = new HashMap<>();
    }

    public static void putSession(Integer key, String value) {
        map.put(key, value);
    }

    public static String getSession(Integer key) {
        return map.get(key);
    }

    public static boolean containsKey(Integer key) {
        return map.containsKey(key);
    }

    public static boolean containsValue(String value) {
        return map.containsValue(value);
    }

    public static Integer getKeyByValue(String value) {
        for (Integer key : map.keySet()) {
            if (map.get(key).equals(value)) {
                return key;
            }
        }
        return -1;
    }

    public static void removeSession(Integer key) {
        map.remove(key);
    }


}
