package com.itdr.utils;

public class PathUtil {
    public static String getPath(String path){
        String s = path.replace(".","/");
        String[] str = s.split("/");
        return str[1];
    }
}
