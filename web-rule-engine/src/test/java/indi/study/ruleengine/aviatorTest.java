package indi.study.ruleengine;

import com.googlecode.aviator.AviatorEvaluator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class aviatorTest {
    /**
     * execute()：需要传递Map格式参数
     * exec()：不需要传递Map
     * */
    @Test
    public void test1 () {
        // exec执行方式，无需传递Map格式
        String age = "18";
        System.out.println(AviatorEvaluator.exec("'His age is '+ age +'!'", age));

        // execute执行方式，需传递Map格式
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("age", "18");
        System.out.println(AviatorEvaluator.execute("'His age is '+ age +'!'", map));
    }

    @Test
    public void grayRule(){
        Map<String, Object> map = new HashMap<>();
        final Date date = new Date();
        String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS").format(date);
        map.put("mobileTail", "9");
        map.put("userId", 700);
        map.put("registerTime", dateStr);
        map.put("mobile", "15600000269");
        map.put("sex", 1);
        map.put("age", 28);
        // 手机尾号
        String expression = "(string.startsWith('9,0',mobileTail) && userId>=901 && registerTime>'2021-06-25 00:00:00')";
        Boolean flag = (Boolean) AviatorEvaluator.execute(expression, map);
        log.info("规则验证判断:{}", flag);
    }

}
