package org.animal.classify.service.impl;

import org.animal.classify.entity.ForwardResult;
import org.animal.classify.entity.ReverseResult;
import org.animal.classify.entity.RulesEnum;
import org.animal.classify.service.IDiscernService;
import org.animal.classify.service.IRulesEnumService;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
 * 实现 识别 方法 （解释器）
 */
public class DiscernServiceImpl implements IDiscernService {

    IRulesEnumService rulesEnumService = new RulesEnumServiceImpl();
    List<RulesEnum> rulesEnumList;

    public DiscernServiceImpl() {
        rulesEnumList = rulesEnumService.getAllRules();
    }

    @Override
    public ForwardResult discernByForward(List<String> rulesList, Function<ForwardResult, ForwardResult> onceDiscernFunciton) {
        List<ForwardResult> forwardResultList = new ArrayList<>();

        // 一级匹配 确定 二级匹配需要参数
        Set<String> tempParams = new HashSet<>();
        rulesEnumList.forEach(rulesEnum -> {
            if(rulesEnum.getConclusionToRules()){
                List<String> rulesEnumRulesList = rulesEnum.getRulesList();
                if(rulesList.containsAll(rulesEnumRulesList) || rulesList.contains(rulesEnum.getConclusion())){
                    // 输入规则 包含 一条规则 的 全部
                    tempParams.add(rulesEnum.getConclusion());
                    System.out.println("一定是" + rulesEnum.getConclusion() + "，其规则有" + Arrays.toString(rulesEnum.getRulesList().toArray()));
                }
            }
        });
        tempParams.addAll(rulesList);

        // 二级匹配 确定 结果 集
        rulesEnumList.forEach(rulesEnum -> {
            if(!rulesEnum.getConclusionToRules()) {
                ForwardResult forwardResult = new ForwardResult(new ArrayList<>(), 0D);

                List<String> rulesEnumRulesList = rulesEnum.getRulesList();
                rulesEnumRulesList.forEach(rulesEnumRulesListItem -> {
                    tempParams.forEach(i -> {
                        // 存在包含是
                        if (rulesEnumRulesListItem.contains(i) || i.contains(rulesEnumRulesListItem)) {
                            forwardResult.getRules().add(i);
                            forwardResult.setProbability(forwardResult.getProbability() + rulesEnum.getOnceShowProbability());
                        }
                    });
                });

                if (forwardResult.getProbability() != 0D){
                    forwardResult.setResult(rulesEnum.getConclusion());
                    forwardResultList.add(forwardResult);
                    // TODO 想做 的 事情
                    onceDiscernFunciton.apply(forwardResult);
                }
            }
        });

        // TODO 过滤 最大
        return forwardResultList.stream().max(Comparator.comparing(ForwardResult::getProbability)).orElse(new ForwardResult());
    }

    @Override
    public ReverseResult discernByReverse(String animalName) {
        return null;
    }
}
