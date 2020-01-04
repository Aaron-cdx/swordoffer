package com.duanxi.interview;

/**
 * @author caoduanxi
 * @Date 2020/1/2 11:09
 * Tag标签类
 */
public enum TagEnum {
    A(1,"A"),
    B(2,"B"),
    AB(3,"AB");

    private int numer;
    private String tag;

    TagEnum(int numer, String tag) {
        this.numer = numer;
        this.tag = tag;
    }

    public int getNumer() {
        return numer;
    }

    public void setNumer(int numer) {
        this.numer = numer;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public static String getNumTag(int number) {
        for (TagEnum value : values()) {
            if (value.numer == number) {
                return value.tag;
            }
        }
        return null;
    }
}
