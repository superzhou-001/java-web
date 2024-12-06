package indi.study.system.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OuterReturnResultModel implements Serializable {
    private String code;
    private String errorMessage;
    //List<ApiChannelReturnModel> content;
}
