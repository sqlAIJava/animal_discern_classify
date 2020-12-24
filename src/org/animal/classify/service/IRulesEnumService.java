package org.animal.classify.service;

import org.animal.classify.entity.RulesEnum;

import java.util.List;

/**
 * 规则 知识库 操作类
 */
public interface IRulesEnumService {

    // 要用规则，从此接口拿
    List<RulesEnum> getAllRules();

}
