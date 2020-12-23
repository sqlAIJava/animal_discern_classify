package org.animal.classify.service.impl;

import org.animal.classify.Entity.ForwardResult;
import org.animal.classify.Entity.ReverseResult;
import org.animal.classify.service.IDiscernService;

import java.util.List;

/*
 * @Author sql
 * @Date 2020/12/23 23:21
 * @Desc 实现 识别 方法 （解释器）
 */
public class DiscernServiceImpl implements IDiscernService {
    @Override
    public ForwardResult discernByForward(List<String> rulesList) {
        return null;
    }

    @Override
    public ReverseResult discernByReverse(String animalName) {
        return null;
    }
}
