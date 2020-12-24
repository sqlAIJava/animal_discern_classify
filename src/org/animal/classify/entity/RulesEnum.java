package org.animal.classify.entity;

import java.util.Arrays;
import java.util.List;

/**
 * 规则 定义 类似 存储部分
 * 概率计算的是 （单个出翔个数/单个同规则个数）*（总共同级规则个数/总共全部规则个数）
 */
public enum RulesEnum {
    // 一级规则知识库 规则 全符合 才 100% 为 某种动物
    PuRuAnimalOne(Arrays.asList("体温恒定"), "哺乳动物", true, 0D),
    PuRuAnimalTwo(Arrays.asList("胎生"), "哺乳动物", true, 0D),
    ShiRouAnimalOne(Arrays.asList("哺乳动物", "吃肉"), "食肉动物", true, 0D),
    ShiRouAnimalTwo(Arrays.asList("哺乳动物", "四肢强劲", "牙齿尖锐"), "食肉动物", true, 0D),
    ShiRouAnimalThree(Arrays.asList("吃肉"), "食肉动物", true, 0D),
    NiaoAnimalOne(Arrays.asList("有羽毛"), "鸟", true, 0D),
    NiaoAnimalTwo(Arrays.asList("会飞"), "鸟", true, 0D),
    YouTiAnimal(Arrays.asList("有蹄子", "哺乳动物"), "有蹄类动物", true, 0D),

    // 二级规则知识库 规则 平均概率 划分
    Lang(Arrays.asList("食肉动物", "灰黄色毛发", "灰黄色", "鼻子突出"), "狼", false, 0D),
    BeiJiXiong(Arrays.asList("食肉动物", "白色", "会游泳"), "北极熊", false, 0D),
    HuLi(Arrays.asList("食肉动物", "红色", "长尾巴"), "狐狸", false, 0D),
    JinQianBao(Arrays.asList("食肉动物", "哺乳动物", "黄褐色", "黑色条纹", "暗斑点"), "金钱豹", false, 0D),
    LaoHu(Arrays.asList("食肉动物", "哺乳动物", "黄褐色", "黑色条纹"), "老虎", false, 0D),
    ChangJinLu(Arrays.asList("有蹄类动物", "长脖子", "长腿", "暗斑点"), "长颈鹿", false, 0D),
    TuoNiao(Arrays.asList("鸟", "长脖子", "长腿", "黑白色", "不飞"), "鸵鸟", false, 0D),
    QiE(Arrays.asList("鸟", "会游泳", "不飞", "黑白色"), "企鹅", false, 0D);

    private List<String> rulesList;
    private String conclusion;
    private Boolean isConclusionToRules;
    private Double onceShowProbability;

    RulesEnum(List<String> rulesList, String conclusion, Boolean isConclusionToRules, Double onceShowProbability) {
        this.rulesList = rulesList;
        this.conclusion = conclusion;
        this.isConclusionToRules = isConclusionToRules;
        this.onceShowProbability = onceShowProbability;
    }

    public static Boolean checkForWardOrReverse(String request){
        return Arrays.asList(RulesEnum.values()).stream().noneMatch(i -> i.getConclusion().equals(request));
    }

    RulesEnum() {
    }

    public List<String> getRulesList() {
        return rulesList;
    }

    public void setRulesList(List<String> rulesList) {
        this.rulesList = rulesList;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public Boolean getConclusionToRules() {
        return isConclusionToRules;
    }

    public void setConclusionToRules(Boolean conclusionToRules) {
        isConclusionToRules = conclusionToRules;
    }

    public Double getOnceShowProbability() {
        return onceShowProbability;
    }

    public void setOnceShowProbability(Double onceShowProbability) {
        this.onceShowProbability = onceShowProbability;
    }
}
