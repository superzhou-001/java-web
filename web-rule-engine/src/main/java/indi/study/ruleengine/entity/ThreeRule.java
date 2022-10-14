package indi.study.ruleengine.entity;

import org.jeasy.rules.annotation.*;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.support.composite.UnitRuleGroup;

@Rule(name = "被3整除", description = "number如果被3整除，打印：number is three")
public class ThreeRule {

    @Condition //条件判断注解：如果return true， 执行Action
    public boolean isThree(@Fact("number") int number){
        return number % 3 == 0;
    }

    @Action(order = 0) //执行方法注解
    public void threeAction(@Fact("number") int number, Facts facts){
        facts.put("three", "123213");
        System.out.println(number + " is three");
    }

    @Action(order = 1) //执行方法注解
    public void threeAction2(@Fact("number") int number){
        System.out.println(number + " is three2");
    }

    @Priority //优先级注解：return 数值越小，优先级越高
    public int getPriority(){
        return 1;
    }

    @Rule(name = "被8整除")
    public static class EightRule {

        @Condition
        public boolean isEight(@Fact("number") int number){
            return number % 8 == 0;
        }

        @Action
        public void eightAction(@Fact("number") int number){
            System.out.println(number + " is eight");
        }

        @Priority
        public int getPriority(){
            return 2;
        }
    }
    @Rule(name = "被3和8同时整除",  description = "这是一个组合规则")
    public static class ThreeEightRuleUnitGroup extends UnitRuleGroup {
        public ThreeEightRuleUnitGroup(Object... rules) {
            for (Object rule : rules) {
                addRule(rule);
            }
        }

        @Override
        public int getPriority() {
            return 0;
        }
    }
    @Rule(name = "既不被3整除也不被8整除", description = "打印number自己")
    public static class OtherRule {
        @Condition
        public boolean isOther(@Fact("number") int number){
            return number % 3 != 0 || number % 8 != 0;
        }

        @Action
        public void printSelf(@Fact("number") int number){
            System.out.println(number);
        }

        @Priority
        public int getPriority(){
            return 3;
        }
    }
}


