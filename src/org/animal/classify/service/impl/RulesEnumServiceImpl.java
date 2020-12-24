package org.animal.classify.service.impl;

import org.animal.classify.entity.RulesEnum;
import org.animal.classify.service.IRulesEnumService;

import java.util.Arrays;
import java.util.List;

/**
 * 实现规则操作类
 */
public class RulesEnumServiceImpl implements IRulesEnumService {

    private RulesEnum[] rulesEnums;

    @Override
    public List<RulesEnum> getAllRules() {

        if(rulesEnums != null)
            return Arrays.asList(rulesEnums);

        rulesEnums = RulesEnum.values();

        // 计算总量
        Double allHasRules = 0D;
        Double isFalesCount = (double) Arrays.stream(rulesEnums).filter(i -> !i.getConclusionToRules()).count();
        for (RulesEnum rulesEnum : rulesEnums) {
            if(!rulesEnum.getConclusionToRules()){
                allHasRules += rulesEnum.getRulesList().size();
            }
        }

        // 计算概率
        Double alineRules = 1D / isFalesCount;
        for (RulesEnum rulesEnum : rulesEnums) {
            if(!rulesEnum.getConclusionToRules()){
                Double temp = (1D/(double) rulesEnum.getRulesList().size()) * alineRules;
                rulesEnum.setOnceShowProbability(temp);
            }else {
                // 结论规则
                rulesEnum.setOnceShowProbability(1D);
            }
        }

        return Arrays.asList(rulesEnums);
    }
}
