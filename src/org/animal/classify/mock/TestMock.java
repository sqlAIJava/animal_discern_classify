package org.animal.classify.mock;

import org.animal.classify.entity.ForwardResult;
import org.animal.classify.service.IDiscernService;
import org.animal.classify.service.impl.DiscernServiceImpl;

import java.util.*;

public class TestMock {

    static IDiscernService discernService = new DiscernServiceImpl();

    public static void main(String[] args) {
        List<String> request = Arrays.asList("鸟");
        ForwardResult forwardResult = discernService.discernByForward(request, i -> {
            System.out.println(i.toString());
            return i;
        });
        System.out.println("===最合适的答案===" + forwardResult.toString());

    }

}
