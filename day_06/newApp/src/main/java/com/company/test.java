package com.company;
import org.apache.commons.lang3.StringUtils;

public class test {
    public static void main(String[] arg){
        boolean rs = StringUtils.isNumeric("Hello Maven");
        System.out.println(rs);
    }
}
