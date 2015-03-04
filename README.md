#AnyUnit - A powerful java unit converter

单位转换

```java

    final AnyUnit anyUnit = AnyUnit.base("B");
    anyUnit.next("KB",1024)
        .next("MB")
        .next("GB")
        .next("TB")
        .next("PB");
        
    String msg = tissue.format(1023);

```
        
    
