package org.animal.classify.mock;

import org.animal.classify.entity.ForwardResult;
import org.animal.classify.entity.ReverseResult;
import org.animal.classify.service.IDiscernService;
import org.animal.classify.service.impl.DiscernServiceImpl;

import java.util.*;

public class TestMock {

    static IDiscernService discernService = new DiscernServiceImpl();

    public static void main(String[] args) {

//        // TEST ONE
//        List<String> request = Arrays.asList("会游泳", "有羽毛", "长脖子", "长腿");
//        ForwardResult forwardResult = discernService.discernByForward(request, i -> {
//            System.out.println(i.toString());
//            return i;
//        });
//        System.out.println("===最合适的答案===" + forwardResult.toString());

//        // TEST TWO
//        ReverseResult reverseResult = discernService.discernByReverse("长颈鹿", i -> {
//            System.out.println("【" + i.get("vertex") + "】递归" + "找到规则" + i.get("rule"));
//            return (String) i.get("vertex");
//        });
//        System.out.println("===最终的答案===" + reverseResult.getRules());

    }

}
