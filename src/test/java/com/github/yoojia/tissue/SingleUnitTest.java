package com.github.yoojia.tissue;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by Yoojia.Chen
 * yoojia.chen@gmail.com
 * 2014-08-31
 */
public class SingleUnitTest {

    private Tissue tissue;

//    @Before
//    public void setUp(){
//        tissue = Tissue.first("MB", 1024 * 1024);
//    }
//
//    @Test
//    public void test(){
//        String rs = tissue.format(10240055);
//
//    }

    public static void main(String[] args) {
        Tissue t = Tissue.first("MB", 1000*1000);
        t.precision(2);
        System.out.println(">> " + t.format(1000*32477));
    }
}
