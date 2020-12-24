package org.animal.classify.entity;

import java.util.List;

/**
 * 逆向结果
 */
public class ReverseResult {

    private List<String> rules;

    public ReverseResult() {
    }

    public ReverseResult(List<String> rules) {
        this.rules = rules;
    }

    public List<String> getRules() {
        return rules;
    }

    public void setRules(List<String> rules) {
        this.rules = rules;
    }
}
