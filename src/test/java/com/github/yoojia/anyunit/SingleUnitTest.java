package com.github.yoojia.anyunit;

/**
 * Created by Yoojia.Chen
 * yoojia.chen@gmail.com
 * 2014-08-31
 */
public class SingleUnitTest {

    private AnyUnit mAnyUnit;

//    @Before
//    public void setUp(){
//        anyunit = Tissue.first("MB", 1024 * 1024);
//    }
//
//    @Test
//    public void test(){
//        String rs = anyunit.format(10240055);
//
//    }

    public static void main(String[] args) {
        AnyUnit t = AnyUnit.first("MB", 1000 * 1000);
        t.precision(2);
        System.out.println(">> " + t.format(1000*32477));
        System.out.println(">> " + t.format(0));
    }
}
