package indi.study.system.entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "用户实体")
public class Users {
   @ApiModelProperty("主键id")
   private Integer id;
   @ApiModelProperty("名称")
   private String name;
   @ApiModelProperty("年龄")
   private Integer age;
}
