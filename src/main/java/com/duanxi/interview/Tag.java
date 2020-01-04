package com.duanxi.interview;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caoduanxi
 * @Date 2020/1/2 10:46 OPPO面试 2020-01-02
 * <p>
 * 使用面向对象的方式实现
 * 遍历100-200所有的元素，如果元素%3=0，则打上标签A，如果%5=0，打上标签B
 * 如果%3&&%5==0 打上标签AB
 */
public class Tag {
    private List<NumberTag> getNumberTag(int start, int end) {
        List<NumberTag> list = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            NumberTag tag = new NumberTag();
            if (i % 15 == 0) {
                tag.setNumber(i);
                tag.setTag(TagEnum.AB.getTag());
                list.add(tag);
                continue;
            }
            if (i % 3 == 0) {
                tag.setNumber(i);
                tag.setTag(TagEnum.A.getTag());
                list.add(tag);
            }
            if (i % 5 == 0) {
                tag.setNumber(i);
                tag.setTag(TagEnum.B.getTag());
                list.add(tag);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        Tag tag = new Tag();
        List<NumberTag> numberTag = tag.getNumberTag(100, 200);
        for (NumberTag num : numberTag) {
            System.out.println("number:" + num.getNumber() + ",tag:" + num.getTag());
        }
    }
}

class NumberTag {
    private int number;
    private String tag;

    public NumberTag() {
    }

    public NumberTag(int number, String tag) {
        this.number = number;
        this.tag = tag;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}

