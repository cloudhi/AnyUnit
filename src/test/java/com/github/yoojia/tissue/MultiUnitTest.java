package com.github.yoojia.tissue;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Yoojia.Chen (yoojia.chen@gmail.com)
 * 2014-08-26
 * 多个单位
 */
public class MultiUnitTest {

    private Tissue tissue;

    @Before
    public void setUp(){
        tissue = Tissue.base("B");
        tissue.next("KB",1024)
                .next("MB",1024)
                .next("GB",1024)
                .next("TB",1024)
                .next("PB",1024);
    }

    @Test
    public void testBase(){
        String base = tissue.format(1023);
        assertThat(base,is("1023B"));
    }

    @Test
    public void testValue(){
        String value = tissue.format(1024);
        assertThat(value,is("1KB"));
    }

    @Test
    public void testValue1(){
        String value = tissue.format(10240);
        assertThat(value,is("10KB"));
    }

    @Test
    public void testValue2(){
        String value = tissue.format(1024*1024L);
        assertThat(value,is("1MB"));
    }

    @Test
    public void testValue3(){
        String value = tissue.format(1024*1024L * 3);
        assertThat(value,is("3MB"));
    }
}
