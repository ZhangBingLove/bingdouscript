package com.bingdou.dnfscript.bean;

import com.bingdou.dnfscript.tools.Global;

/**
 * 键位 - 我的(冰哥的扫把)
 */
public enum SkillForAuthor {

    SKILL_1(1902 * getYRate(), 976 * getXRate()),
    SKILL_2(1725 * getYRate(), 983 * getXRate()),
    SKILL_3(1579 * getYRate(), 981 * getXRate()),
    SKILL_4(1874 * getYRate(), 837 * getXRate()),
    SKILL_5(1689 * getYRate(), 813 * getXRate()),
    SKILL_6(1540 * getYRate(), 840 * getXRate()),
    SKILL_7(1955 * getYRate(), 677 * getXRate()),
    SKILL_8(1780 * getYRate(), 673 * getXRate()),
    SKILL_9(2029 * getYRate(), 546 * getXRate()),
    SKILL_10(1910 * getYRate(), 539 * getXRate()),
    SKILL_11(1787 * getYRate(), 543 * getXRate()),
    SKILL_12(1673 * getYRate(), 533 * getXRate());

    // 成员变量
    private int x;
    private int y;

    // 构造方法 ,赋值给成员变量
    private SkillForAuthor(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //覆盖方法  :只能使用toString方法来输出枚举变量值
    @Override
    public String toString() {
        return this.x + "_" + this.y;
    }

    /**
     * 获取X的比率
     * 参照手机的比率/
     */
    private static int getXRate() {
        return Global.SCREEN_X / Global.SCREEN_X;
    }

    private static int getYRate() {
        return Global.SCREEN_Y / Global.SCREEN_Y;
    }
}
