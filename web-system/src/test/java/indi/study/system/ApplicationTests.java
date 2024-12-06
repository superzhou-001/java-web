package indi.study.system;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.*;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import indi.study.system.entity.OuterReturnResultModel;
import indi.study.system.entity.Student;
import indi.study.system.entity.Users;
import indi.study.system.test.vo.SunyurResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@SpringBootTest
@Slf4j
class ApplicationTests {
    @Autowired
    DataSource dataSource;
    @Test
    void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());
        //获得连接
        Connection connection =   dataSource.getConnection();
        System.out.println(connection);
        DruidDataSource druidDataSource = (DruidDataSource) dataSource;
        log.debug("druidDataSource 数据源最大连接数：" + druidDataSource.getMaxActive());
        log.debug("druidDataSource 数据源初始化连接数：" + druidDataSource.getInitialSize());
        //关闭连接
        connection.close();
    }


    /**
     *
     * CollectionUtils.isNotEmpty(proItemInParamList)
     * */
    @Test
    public void test1 () {
        List<Student> studentList = new ArrayList<>();
        Users user = new Users();
        user.setId(12);
        user.setAge(23);
        user.setName("22");
        Student student = new Student();
        student.setId("12");
        student.setSname("34");
        student.setSage(3);
        studentList.add(student);
        user.setStudentList(studentList);
        System.out.println(JSON.toJSONString(user));
        List<Student> studentList1 = user.getStudentList();
        if (CollectionUtils.isNotEmpty(studentList1)) {
            if (studentList1.get(0).getSage() != null && studentList1.get(0).getSage() == 3 ) {
                System.out.println("aaaaa");
            } else {
                System.out.println("bbbbb");
            }
        } else {
            System.out.println("0000000");
        }
        if (user.getName().equals("22")) {
            System.out.println("=====");
        } else {
            System.out.println("-----");
        }
        String userStr = "{\"age\":23,\"name\":\"22\",\"studentList\":[{\"id\":\"12\",\"sage\":3,\"sname\":\"34\"}]}";
        Users user1 = JSON.parseObject(userStr, Users.class);

    }

    public static void main(String[] args) {
//       List<Integer> numbers = Arrays.asList(null, 1, 2, 3, 4, 5);
        // 筛选大于等于3的元素
//        List<Integer> filteredNumbers = numbers.stream()
//                .filter(num -> num == 3 || num == 4)
//                .collect(Collectors.toList());
//        Integer num1 = 2;
//        Integer num3 = 2;
//        System.out.println(num1 == 2);
//        System.out.println(num1 == num3);
//        System.out.println(num3.equals(num1));
//
//        // 校验items是否存在待发货状态,及出库失败的item
//        List<Integer> flag = numbers.stream().filter(num -> num == null || num == 2 || num == 3).collect(Collectors.toList());
//        System.out.println("----:" + JSON.toJSONString(flag));
        //System.out.println("过滤后的结果为：" + filteredNumbers);
/*        List<Long> filmOrderItemIdListCollect = new ArrayList<>();
        filmOrderItemIdListCollect.add(8514616L);
        Map<Long, Long> orderItemIdMap = new HashMap<>();
        orderItemIdMap.put(8514616L, 8513973L);
        filmOrderItemIdListCollect.forEach(orderItemId -> {

        });
        // 如存在原始订单行id则替换
        for (Long orderItemId : filmOrderItemIdListCollect) {
            Long originalItemId = orderItemIdMap.getOrDefault(orderItemId, null);
            if (originalItemId != null) {
                filmOrderItemIdListCollect.remove(orderItemId);
                filmOrderItemIdListCollect.add(originalItemId);
            }
        }
        System.out.println("-----:"+ JSON.toJSONString(filmOrderItemIdListCollect));

        long milliseconds = 1706168700000L; // 获取当前时间的毫秒数

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(new Date(milliseconds));

        System.out.println("时间："+dateStr);*/

//        try {
//            DateFormat simpleFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//
//            System.out.println("时间："+ simpleFormatter.parse("2024-03-01 00:00:00"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }



      /*  Users user = new Users();
        user.setId(12);
        user.setAge(23);
        SunyurResult result1 = new SunyurResult();
        result1.setCode("00000");
        result1.setContent("123123");
        SunyurResult result = new SunyurResult();
        TypeReference<SunyurResult<Integer>> typeReference = new TypeReference<SunyurResult<Integer>>() {};
        result = JSON.parseObject(JSON.toJSONString(result1), typeReference);
        System.out.println(JSON.toJSONString(result));*/


//        Map<String, Date> finalWaybillDoneMap = new HashMap<>();
//        System.out.println("-------"+ finalWaybillDoneMap.getOrDefault("123", null));

//        List<Users> usersList1 = new ArrayList<>();
//        Users user = new Users();
//        user.setId(12);
//        user.setAge(22);
//        Users user1 = new Users();
//        user1.setId(13);
//        user1.setAge(23);
//        Users user2 = new Users();
//        user2.setId(14);
//        user2.setAge(24);
//        Users user3 = new Users();
//        user3.setId(15);
//        user3.setAge(25);
//        usersList1.add(user);
//        usersList1.add(user1);
//        usersList1.add(user2);
//        usersList1.add(user3);
//        System.out.println("----"+ usersList1.size());
//        List<Users> usersList2 = new ArrayList<>();
//        Users user4 = new Users();
//        user4.setId(12);
//        Users user5 = new Users();
//        user5.setId(15);
//        Users user6 = new Users();
//        user6.setId(13);
//        usersList2.add(user4);
//        usersList2.add(user5);
//
//        System.out.println("----"+ usersList2.size());
//        List<Users> newUserList = new ArrayList<>();
//
//        newUserList.addAll(usersList1.stream()
//                .filter(item -> usersList2.stream()
//                        .noneMatch(record -> item.getId() == record.getId()))
//                .collect(Collectors.toList()));
//
//        System.out.println("---"+JSON.toJSONString(newUserList));
//
//        Set<Integer> recordIds = usersList2.stream()
//                .map(Users::getId)
//                .collect(Collectors.toSet());
//        List<Users> newUserList2 = new ArrayList<>();
//
//        newUserList2.addAll(usersList1.stream()
//                .filter(item -> !recordIds.contains(item.getId()))
//                .collect(Collectors.toList()));
//        System.out.println("+++"+JSON.toJSONString(newUserList));


//        Map<String, Object> mainData = new HashMap<>();
//        mainData.put("编号", "RPC00012");
//        mainData.put("项目名称", "测试项目");
//        mainData.put("填表日期", "2024-04-24");
//        mainData.put("预计采购金额", "2800");
//        mainData.put("项目概况", "这是一个测试单");
//        mainData.put("需求部门", "这是一个测试部门");
//        mainData.put("采购模式", "直采");
//        List<Map<String, Object>> subDataList = new ArrayList<>();
//        AtomicInteger num = new AtomicInteger(1);
//        Map<String, Object> subData1 = new HashMap<>();
//        subData1.put("cf序号", 1);
//        subData1.put("cf需求项目名称", "飞机");
//        subData1.put("技术参数", "num1100");
//        subData1.put("cf需求数量", "2");
//        subData1.put("参考单价", "20");
//        subDataList.add(subData1);
//        Map<String, Object> subData2 = new HashMap<>();
//        subData2.put("cf序号", 2);
//        subData2.put("cf需求项目名称", "坦克");
//        subData2.put("技术参数", "num2200");
//        subData2.put("cf需求数量", "2");
//        subData2.put("参考单价", "30");
//        subDataList.add(subData2);
//        mainData.put("sub", subDataList);
//        String result = JSON.toJSONString(mainData);
//        System.out.println(result);
//
//
//        Map<String, Object> zyMainData = new HashMap<>();
//        zyMainData.put("申请人", "bnb");
//        zyMainData.put("申请日期", String.format("%tF", System.currentTimeMillis()));
//        zyMainData.put("合计", "800");
//        zyMainData.put("申请部门", "运营部");
//        List<Map<String, Object>> zySubDataList = new ArrayList<>();
//        AtomicInteger zynum = new AtomicInteger(1);
//        Map<String, Object> zysubData1 = new HashMap<>();
//        zysubData1.put("序号", zynum.getAndIncrement());
//        zysubData1.put("名称", "M416");
//        zysubData1.put("规格", "mm110");
//        zysubData1.put("数量", "10");
//        zysubData1.put("预估金额", "33");
//        zySubDataList.add(zysubData1);
//        Map<String, Object> zysubData2 = new HashMap<>();
//        zysubData2.put("序号", zynum.getAndIncrement());
//        zysubData2.put("名称", "AK47");
//        zysubData2.put("规格", "kk180");
//        zysubData2.put("数量", "30");
//        zysubData2.put("预估金额", "44");
//        zySubDataList.add(zysubData2);
//        zyMainData.put("sub", zySubDataList);
//        String zyresult = JSON.toJSONString(zyMainData);
//        System.out.println(zyresult);
//
//        Map<String, Object> qtMainData = new HashMap<>();
//        qtMainData.put("编号", "PRCCT007");
//        qtMainData.put("项目名称", "测");
//        qtMainData.put("填表日期", String.format("%tF", System.currentTimeMillis()));
//        qtMainData.put("x经办人", "yyb");
//        qtMainData.put("预计采购金额", "28");
//        qtMainData.put("项目概况", "ccccbbbyyyy");
//        qtMainData.put("需求部门", "cccyt");
//        qtMainData.put("采购模式", "竞价");
//        String qtresult = JSON.toJSONString(qtMainData);
//        System.out.println(qtresult);
//
//        BigDecimal original = new BigDecimal("123");
//        BigDecimal rounded = original.setScale(2, RoundingMode.HALF_DOWN);
//        System.out.println(rounded);


//        String responseJSON = "{\"approveMemberId\":0,\"finishedFlag\":0,\"state\":0}";
//
//        Object approveMemberId = JSONPath.read(responseJSON, "approveMemberId");
//        Integer finishedFlag = (Integer) JSONPath.read(responseJSON, "finishedFlag");
//        Integer state = (Integer) JSONPath.read(responseJSON, "state");
//        if(!approveMemberId.equals(0) && state.equals(1)) {
//            System.out.println("状态："+ 1);
//        } else {
//            if(approveMemberId.equals(0) && state.equals(1) && finishedFlag.equals(3)) {
//                System.out.println("状态："+ 4);
//            }
//            if(approveMemberId.equals(0) && state.equals(0) && finishedFlag.equals(0)) {
//                System.out.println("状态："+ 2);
//            }
//            if(approveMemberId.equals(0) && state.equals(1) && finishedFlag.equals(0)){
//                System.out.println("状态："+ 0);
//            }
//        }


//        List<Users> usersList1 = new ArrayList<>();
//        Users user = new Users();
//        user.setId(12);
//        user.setAge(22);
//        Users user1 = new Users();
//        user1.setId(13);
//        user1.setAge(23);
//        Users user4 = new Users();
//        user4.setId(133);
//        user4.setAge(23);
//        usersList1.add(user);
//        usersList1.add(user1);
//        usersList1.add(user4);
//
//        List<Users> usersList2 = new ArrayList<>();
//        Users user2 = new Users();
//        user2.setId(12);
//        user2.setAge(22);
//        Users user3 = new Users();
//        user3.setId(13);
//        user3.setAge(23);
//        Users user5 = new Users();
//        user5.setId(133);
//        user5.setAge(23);
//        usersList2.add(user3);
//        usersList2.add(user2);
//        usersList2.add(user5);
//
//        String userList1 = "[{\"adjustType\":1,\"adjust\":1,\"scope\":[{\"label\":\"全部协议\",\"value\":0}],\"type\":2,\"dimension\":1,\"marketPriceRuleId\":32,\"rulesValues\":[{\"taxPoint\":0.05,\"maxNum\":\"100\",\"taxCode\":\"VAT5\",\"value\":\"10\",\"minNum\":0},{\"taxPoint\":0.05,\"maxNum\":\"200\",\"taxCode\":\"VAT5\",\"value\":\"20\",\"minNum\":\"100\"},{\"taxPoint\":0.05,\"maxNum\":\"300\",\"taxCode\":\"VAT5\",\"value\":\"30\",\"minNum\":\"200\"}]}]";
//        //Map<String, Object> beforeFormData = JSONObject.parseObject(userList1, Map.class);
//        List<Map<String, Object>> list = JSONObject.parseObject(userList1, new TypeReference<List<Map<String, Object>>>() {
//        });
//        System.out.println("-------");


//        if (isEqual(usersList1, usersList2)) {
//            System.out.println("---: true");
//        } else {
//            System.out.println("---: false");
//        }

//          List<Long> id1 = new ArrayList<>();
//            id1.add(123L);
//            id1.add(124L);
//            id1.add(125L);
//            id1.add(126L);
//            id1.add(127L);
//
//        List<Long> id2 = new ArrayList<>();
//        id2.add(123L);
//        id2.add(124L);
//        id2.add(126L);
//        id2.add(127L);
//        System.out.println("-------:" + id1.containsAll(id2));





//        // 按照1000分组
//        List<Integer> groups = splitNumber(0, 20000);
//        System.out.println(groups);
    }


    public static List<Integer> splitNumber(int number, int groupSize) {
        List<Integer> groups = new ArrayList<>();
        int remaining = number;
        while (remaining > 0) {
            int group = Math.min(remaining, groupSize);
            groups.add(group);
            remaining -= group;
        }
        return groups;
    }



    public static boolean isEqual(List<Users> list1, List<Users> list2) {
        if (list1.size() != list2.size()) {
            // 两个集合的大小不相等，不一样
            return false;
        }

        Set<Users> set1 = new HashSet<>(list1);
        Set<Users> set2 = new HashSet<>(list2);

        return set1.equals(set2);
    }

}
