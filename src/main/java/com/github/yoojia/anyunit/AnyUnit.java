package com.github.yoojia.anyunit;

import java.util.ArrayList;
import java.util.List;

public class AnyUnit {

    private static class Section{
        /**
         * 计量单位名
         */
        final String unitName;
        final long radix;
        final long preRadix;

        Section(String unitChar, long radix, long preRadix){
            this.unitName = unitChar;
            this.radix = radix;
            this.preRadix = preRadix;
        }
    }

    private final List<Section> mSections = new ArrayList<Section>();

    /**
     * 各级单位的连接符
     */
    private String mLinkChar = "";

    /**
     * 单个单位表示时，数值精度位数
     */
    private int mPrecision = 3;

    /**
     * 单个单位时，强制要求显示精度
     */
    private boolean mEnforcePrecision = false;

    /**
     * 第一个单位，其基数为1.
     * @param unit 单位名称
     * @return Tissue对象
     */
    public static AnyUnit first(String unit){
        return first(unit, 1);
    }

    /**
     * 第一个单位，并设定基数。
     * @param unit 单位名称
     * @param radix 基数
     * @return Tissue对象
     */
    public static AnyUnit first(String unit, int radix){
        AnyUnit item = new AnyUnit();
        item.mSections.add(new Section(unit, radix, radix));
        return item;
    }

    /**
     * 设定下一个单位及其基数。
     * @param unit 单位名称
     * @param radix 与上一级的基数
     * @return Tissue对象
     */
    public AnyUnit next(String unit, int radix){
        Section last = mSections.get(mSections.size() - 1);
        mSections.add(new Section(unit, last.radix * radix, radix));
        return this;
    }

    /**
     * 设定下一个单位，其基数与上一级相同。
     * @param unit 单位名称
     * @return Tissue对象
     */
    public AnyUnit next(String unit){
        Section last = mSections.get(mSections.size() - 1);
        mSections.add(new Section(unit, last.radix * last.preRadix, last.preRadix));
        return this;
    }

    /**
     * 设定各级单位之间的连接符
     * @param linkChar 连接符
     * @return Tissue对象
     */
    public AnyUnit linkChar(String linkChar){
        this.mLinkChar = linkChar;
        return this;
    }

    /**
     * 设定单个单位的精度
     * @param precision 精度小数位数
     * @return Tissue对象
     */
    public AnyUnit precision(int precision){
        this.mPrecision = precision;
        return this;
    }

    /**
     * 强制要求数值精度，在单个单位时生效
     * @param enforce
     */
    public AnyUnit enforcePrecision(boolean enforce){
        this.mEnforcePrecision = enforce;
        return this;
    }

    /**
     * 格式化数值
     * @param value 数值
     * @return 格式化的数值
     */
    public String format(double value){
        final int max = mSections.size() - 1;
        final StringBuilder msg = new StringBuilder();
        // Single
        if (max == 0){
            final Section sec = mSections.get(max);
            double result = value / sec.radix;
            long intResult = (long)result;
            if (result == intResult && !mEnforcePrecision){
                // 不需要显示精度数值
                msg.append(intResult);
            }else{
                msg.append(String.format("%." + mPrecision + "f", result));
            }
            msg.append(sec.unitName);
        }else{
            for (int i = max; i>=0; --i){
                Section sec = mSections.get(i);
                double result = value / sec.radix;
                int intResult = (int)result;
                if (intResult <= 0){
                    continue;
                }else{
                    msg.append(intResult);
                }
                msg.append(sec.unitName);
                if (i != 0) {
                    msg.append(mLinkChar);
                }
                value -= intResult * sec.radix;
            }
        }
        return msg.toString();
    }

}
