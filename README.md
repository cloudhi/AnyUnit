#AnyUnit - A powerful java unit converter

可以多级单位输入的数值单位转换工具类

## Gradle dependencies

已推送到JCenter

> compile 'com.github.yoojia:AnyUnit:1.1'

## 最基本的使用方式

```java

    final AnyUnit anyUnit = AnyUnit.base("B");
    anyUnit.next("KB",1024)
        .next("MB")
        .next("GB")
        .next("TB")
        .next("PB");
        
    String msg = tissue.format(1023);

```
## 其它使用例子

```java

    AnyUnit u = AnyUnit.first("元", 1);
    u.setLinkChar("-");
    u.setPrecision(3);
    u.next("万", 10000).next("千万", 1000).next("亿", 10);
    System.out.println(u.format(1));
    System.out.println(u.format(10));
    System.out.println(u.format(100));
    System.out.println(u.format(1000));
    System.out.println(u.format(10000));
    System.out.println(u.format(100000));
    System.out.println(u.format(1000000));
    System.out.println(u.format(10000000));
    System.out.println(u.format(100000000));
    System.out.println(u.format(200000000));
    System.out.println(u.format(203040000));
    System.out.println(u.format(203040500));

```
输入结果为：

    1元
    10元
    100元
    1000元
    1万
    10万
    100万
    1千万
    1亿
    2亿
    2亿-304万
    2亿-304万-500元

        
    
