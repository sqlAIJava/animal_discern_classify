package org.animal.classify.service;

import org.animal.classify.Entity.ForwardResult;
import org.animal.classify.Entity.ReverseResult;

import java.util.List;

/*
 * @Author sql
 * @Date 2020/12/23 23:19
 * @Desc 定义识别接口
 */
public interface IDiscernService {


    ForwardResult discernByForward(List<String> rulesList);

    ReverseResult discernByReverse(String animalName);

}
