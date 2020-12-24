package org.animal.classify.entity;

import java.util.List;

/**
 * 正向返回结果
 */
public class ForwardResult {
    private List<String> rules;
    private String result;
    private Double probability;

    public ForwardResult(List<String> rules, Double probability) {
        this.rules = rules;
        this.probability = probability;
    }

    public ForwardResult() {
    }

    public List<String> getRules() {
        return rules;
    }

    public void setRules(List<String> rules) {
        this.rules = rules;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Double getProbability() {
        return probability;
    }

    public void setProbability(Double probability) {
        this.probability = probability;
    }

    @Override
    public String toString() {
        return "ForwardResult{" +
                "rules=" + rules +
                ", result='" + result + '\'' +
                ", probability='" + probability + '\'' +
                '}';
    }
}
