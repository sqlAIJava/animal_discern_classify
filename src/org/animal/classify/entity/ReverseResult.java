package org.animal.classify.entity;

import java.util.LinkedHashSet;

/**
 * 逆向结果
 */
public class ReverseResult {

    private LinkedHashSet<String> rules;

    public LinkedHashSet<String> getRules() {
        return rules;
    }

    public void setRules(LinkedHashSet<String> rules) {
        this.rules = rules;
    }

    public ReverseResult() {
    }

    public ReverseResult(LinkedHashSet<String> rules) {
        this.rules = rules;
    }
}
