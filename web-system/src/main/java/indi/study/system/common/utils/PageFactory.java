package indi.study.system.common.utils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.util.StringUtils;

import java.util.Map;

public class PageFactory {
    public static Page getPage(Map<String, String> map) {
        Page page = null;
        Integer offset = 0;
        Integer limit = 10;
        if (!StringUtils.isEmpty(map.get("offset"))) {
            offset = Integer.valueOf(map.get("offset"));
        }
        if (!StringUtils.isEmpty(map.get("limit"))) {
            limit = Integer.valueOf(map.get("limit"));
        }
        if (limit == -1) {
            page = PageHelper.startPage(offset / limit + 1, 0);
        } else {
            page = PageHelper.startPage(offset / limit + 1, limit);
        }
        return page;
    }
}
