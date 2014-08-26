#Tissue

单位转换

    Tissue tissue = Tissue.base("B");
    tissue.next("KB",1024)
        .next("MB")
        .next("GB")
        .next("TB")
        .next("PB");
        
    String msg = tissue.format(1023);
        
    
