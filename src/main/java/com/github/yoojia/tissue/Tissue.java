package com.github.yoojia.tissue;

import java.util.ArrayList;
import java.util.List;

public class Tissue {

    static class Section{

        final String unit;
        final long weight;
        final long base;

        Section(String unit, long weight, long base){
            this.unit = unit;
            this.weight = weight;
            this.base = base;
        }
    }

    private List<Section> sections = new ArrayList<Section>();

    private String linkChar = "";

    public static Tissue base(String unit){
        return base(unit, 1);
    }

    public static Tissue base(String unit, int weight){
        Tissue uc = new Tissue();
        uc.sections.add(new Section(unit, weight, weight));
        return uc;
    }

    public Tissue next(String unit, int weight){
        Section last = sections.get(sections.size() - 1 );
        sections.add(new Section(unit, last.weight * weight, weight));
        return this;
    }

    public Tissue next(String unit){
        Section last = sections.get(sections.size() - 1 );
        sections.add(new Section(unit, last.weight * last.base, last.base));
        return this;
    }

    public Tissue linkChar(String linkChar){
        this.linkChar = linkChar;
        return this;
    }

    public String format(double value){
        final int max = sections.size() - 1;
        StringBuilder msg = new StringBuilder();
        for (int i = max; i>=0; --i){
            Section sec = sections.get(i);
            double dRS = value / sec.weight;
            int iRS = (int)dRS;
            if (iRS <= 0) continue;

            msg.append(iRS);
            msg.append(sec.unit);
            if (i != 0) {
                msg.append(linkChar);
            }

            value -= iRS * sec.weight;
        }
        return msg.toString();
    }

}
