package indi.study.ruleengine;

import com.alibaba.fastjson.JSON;
import indi.study.ruleengine.entity.Person;
import indi.study.ruleengine.entity.ThreeRule;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.rules.api.*;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.mvel.MVELRule;
import org.junit.jupiter.api.Test;

@Slf4j
public class ThreeEightRuleLauncher {

    @Test
    public void pojoRule() {
        /**
         * 创建规则执行引擎
         * 注意: skipOnFirstAppliedRule意思是，只要匹配到第一条规则就跳过后面规则匹配
         * skipOnFirstAppliedRule：当一个规则成功应用时，跳过余下的规则。
         * skipOnFirstFailedRule：当一个规则失败时，跳过余下的规则。
         * skipOnFirstNonTriggeredRule：当一个规则未触发时，跳过余下的规则。
         * rulePriorityThreshold：当优先级超过指定的阈值时，跳过余下的规则。
         */
        RulesEngineParameters parameters = new
                RulesEngineParameters().skipOnFirstAppliedRule(true);
        /**
         * DefaultRulesEngine：根据规则的自然顺序(默认为优先级)应用规则。
         * InferenceRulesEngine：在已知的事实上不断地应用规则，直到没有更多的规则可用。
         * */
        RulesEngine rulesEngine = new DefaultRulesEngine(parameters);
        //创建规则
        Rules rules = new Rules();
        rules.register(new ThreeRule());
        rules.register(new ThreeRule.EightRule());
        // 组合规则同时满足条件都打印
        rules.register(new ThreeRule.ThreeEightRuleUnitGroup(new ThreeRule.EightRule(), new ThreeRule()));
        rules.register(new ThreeRule.OtherRule());
        Facts facts = new Facts();
        facts.put("number", 3);
        //执行规则
        rulesEngine.fire(rules, facts);
        System.out.println("--------------");
        /*for (int i=1 ; i<=50 ; i++){
            //规则因素，对应的name，要和规则里面的@Fact 一致
            facts.put("number", i);
            //执行规则
            rulesEngine.fire(rules, facts);
            System.out.println();
        }*/
    }

    @Test
    public void mvelRule() {
        //基于MVEL表达式的编程模型
        //创建一个Person实例(Fact)
        Person tom = new Person("Tom", 10);
        Facts facts = new Facts();
        facts.put("person", tom);
        //创建规则1
        Rule ageRule = new MVELRule()
                .name("age rule")
                .description("Check if person's age is > 18 and marks the person as adult")
                .priority(1)
                .when("(person.age > 18 || person.age == 10) && person.isAdult() == false")
                .then("person.setAdult(true);");
        Rules rules = new Rules();
        rules.register(ageRule);
        //创建规则执行引擎，并执行规则
        RulesEngine rulesEngine = new DefaultRulesEngine();
        System.out.println("Tom: Hi! can I have some Vodka please?");
        rulesEngine.fire(rules, facts);
        System.out.println(JSON.toJSONString(tom));
    }
}
