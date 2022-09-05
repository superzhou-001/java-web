package indi.study.system.common.utils;

import indi.study.system.common.bean.JsonResult;
import indi.study.system.common.bean.APage;

import java.util.List;

public class ResultUtil {

    public static <T> JsonResult<T> success(T data) {
        JsonResult<T> result = new JsonResult<>();
        result.setStatus(200);
        result.setMessage("success");
        result.setData(data);
        return result;
    }

    public static <T> JsonResult<T> success(T data, String message) {
        JsonResult<T> result = new JsonResult<>();
        result.setStatus(200);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    // 分页数据成功返回
    public static <T> JsonResult<APage<T>> success(T data, long pageCount, int pageNo, int pageSize) {
        JsonResult<APage<T>> result = new JsonResult<APage<T>>();
        APage<T> page = new APage<T>();
        page.setPageNo(pageNo);
        page.setCount(pageCount);
        page.setPageSize(pageSize);
        page.setList(data);
        result.setStatus(200);
        result.setMessage("success");
        result.setData(page);
        return result;
    }

    public static JsonResult error(Integer code, String message) {
        JsonResult<?> result = new JsonResult<Object>();
        result.setStatus(code);
        result.setMessage(message);
        return result;
    }

    public static JsonResult error(Integer code, String message, List list) {
        JsonResult result = new JsonResult();
        result.setStatus(code);
        result.setMessage(message);
        result.setData(list);
        return result;
    }
}
