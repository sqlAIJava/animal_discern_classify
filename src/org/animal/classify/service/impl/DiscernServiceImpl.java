package org.animal.classify.service.impl;

import org.animal.classify.entity.ForwardResult;
import org.animal.classify.entity.ReverseResult;
import org.animal.classify.entity.RulesEnum;
import org.animal.classify.service.IDiscernService;
import org.animal.classify.service.IRulesEnumService;

import javax.print.attribute.standard.Finishings;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
 * 实现 识别 方法 （解释器）
 */
public class DiscernServiceImpl implements IDiscernService {

    // 用于递归
    private static LinkedHashSet<String> ret = new LinkedHashSet<>();
    private LinkedHashSet<String> advancedResult = new LinkedHashSet<>();

    // 初始化规格
    IRulesEnumService rulesEnumService = new RulesEnumServiceImpl();
    static List<RulesEnum> rulesEnumList;

    public DiscernServiceImpl() {
        rulesEnumList = rulesEnumService.getAllRules();
    }

    @Override
    public ForwardResult discernByForward(List<String> rulesList, Function<ForwardResult, ForwardResult> onceDiscernFunciton) {
        List<ForwardResult> forwardResultList = new ArrayList<>();

        List<String> tempPrintln = new ArrayList<>();
        // 一级匹配 确定 二级匹配需要参数
        Set<String> tempParams = new HashSet<>();
        rulesEnumList.forEach(rulesEnum -> {
            if(rulesEnum.getConclusionToRules()){
                List<String> rulesEnumRulesList = rulesEnum.getRulesList();
                if(rulesList.containsAll(rulesEnumRulesList) || rulesList.contains(rulesEnum.getConclusion())){
                    // 输入规则 包含 一条规则 的 全部
                    tempParams.add(rulesEnum.getConclusion());

                    if(tempPrintln.stream().noneMatch(i -> i.equals(rulesEnum.getConclusion()))){
                        tempPrintln.add(rulesEnum.getConclusion());

                        Set<String> printlnList = new HashSet<>();
                        rulesEnumList.stream().filter( i -> i.getConclusion().equals(rulesEnum.getConclusion()))
                                .collect(Collectors.toList())
                                .forEach( i ->{
                                    printlnList.addAll(i.getRulesList());
                                });

                        System.out.println("一定是" + rulesEnum.getConclusion() + "，其规则有" + Arrays.toString(printlnList.toArray()));
                    }
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
    public ReverseResult discernByReverse(String animalName, Function<Map<String, Object>, String> function) {
        ReverseResult reverseResult = new ReverseResult(ret);

        // 向下查找方法
        findFloorRules(animalName, function);

        return reverseResult;
    }

    /**
     * 递归方法设计， 查找更下一层 规则
     */
    private void findFloorRules(String request, Function<Map<String, Object>, String> function){
        // 该保存 ， 打印时 可以 去除 第一个
        ret.add(request);

        if(rulesEnumList.stream().anyMatch(t -> t.getConclusion().equals(request) )) {

            // 能找到 结果为 结论的 , 递归 下一个 是重点
            LinkedHashSet<String> strings = new LinkedHashSet<>();
            rulesEnumList.stream()
                    .filter(t -> t.getConclusion().equals(request))
                    .forEach(i -> strings.addAll(i.getRulesList()));

            // 目前 该高层 可 保存 也可 不保存；；；
            if (!advancedResult.contains(request)) {
                // 为了打印 封装 参数  顶点 + 规则
                Map<String, Object> funMap = new HashMap<>();
                funMap.put("vertex", request);
                funMap.put("rule", strings);
                advancedResult.add(function.apply(funMap));
            }

            for (String temp : strings) {
                findFloorRules(temp, function);
            }

        }
    }

}
