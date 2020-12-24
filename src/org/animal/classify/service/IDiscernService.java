package org.animal.classify.service;

import org.animal.classify.entity.ForwardResult;
import org.animal.classify.entity.ReverseResult;

import java.util.List;
import java.util.function.Function;

/*
 * 定义识别接口
 */
public interface IDiscernService {


    ForwardResult discernByForward(List<String> rulesList, Function<ForwardResult, ForwardResult> onceDiscernFunciton);

    ReverseResult discernByReverse(String animalName);

}
