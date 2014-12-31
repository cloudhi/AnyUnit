package com.github.yoojia.tissue;

import java.util.ArrayList;
import java.util.List;

public class Tissue {

    private static class Section{

        final String unitChar;
        final long radix;
        final long preRadix;

        Section(String unitChar, long radix, long preRadix){
            this.unitChar = unitChar;
            this.radix = radix;
            this.preRadix = preRadix;
        }
    }

    private List<Section> sections = new ArrayList<Section>();

    /**
     * 各级单位的连接符
     */
    private String linkChar = "";

    /**
     * 单个单位表示时，数值精度位数
     */
    private int precision = 3;

    /**
     * 单个单位时，强制要求显示精度
     */
    private boolean enforcePrecision = false;

    /**
     * 第一个单位，其基数为1.
     * @param unit 单位名称
     * @return Tissue对象
     */
    public static Tissue first(String unit){
        return first(unit, 1);
    }

    /**
     * 第一个单位，并设定基数。
     * @param unit 单位名称
     * @param radix 基数
     * @return Tissue对象
     */
    public static Tissue first(String unit, int radix){
        Tissue item = new Tissue();
        item.sections.add(new Section(unit, radix, radix));
        return item;
    }

    /**
     * 设定下一个单位及其基数。
     * @param unit 单位名称
     * @param radix 与上一级的基数
     * @return Tissue对象
     */
    public Tissue next(String unit, int radix){
        Section last = sections.get(sections.size() - 1);
        sections.add(new Section(unit, last.radix * radix, radix));
        return this;
    }

    /**
     * 设定下一个单位，其基数与上一级相同。
     * @param unit 单位名称
     * @return Tissue对象
     */
    public Tissue next(String unit){
        Section last = sections.get(sections.size() - 1);
        sections.add(new Section(unit, last.radix * last.preRadix, last.preRadix));
        return this;
    }

    /**
     * 设定各级单位之间的连接符
     * @param linkChar 连接符
     * @return Tissue对象
     */
    public Tissue linkChar(String linkChar){
        this.linkChar = linkChar;
        return this;
    }

    /**
     * 设定单个单位的精度
     * @param precision 精度小数位数
     * @return Tissue对象
     */
    public Tissue precision(int precision){
        this.precision = precision;
        return this;
    }

    /**
     * 强制要求数值精度，在单个单位时生效
     * @param enforce
     */
    public Tissue enforcePrecision(boolean enforce){
        this.enforcePrecision = enforce;
        return this;
    }

    /**
     * 格式化数值
     * @param value 数值
     * @return 格式化的数值
     */
    public String format(double value){
        final int max = sections.size() - 1;
        StringBuilder msg = new StringBuilder();
        // Single
        if (max == 0){
            Section sec = sections.get(max);
            double result = value / sec.radix;
            long intResult = (long)result;
            if (result == intResult && !enforcePrecision){
                // 不需要显示精度数值
                msg.append(intResult);
            }else{
                msg.append(String.format("%." + precision + "f", result));
            }
            msg.append(sec.unitChar);
        }else{
            for (int i = max; i>=0; --i){
                Section sec = sections.get(i);
                double result = value / sec.radix;
                int intResult = (int)result;
                if (intResult <= 0){
                    continue;
                }else{
                    msg.append(intResult);
                }
                msg.append(sec.unitChar);
                if (i != 0) {
                    msg.append(linkChar);
                }
                value -= intResult * sec.radix;
            }
        }
        return msg.toString();
    }

}
