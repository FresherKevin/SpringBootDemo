package cn.nh.kevin.demo.DTO;

import cn.nh.kevin.demo.Enum.ResultEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * 标题:
 * 描述:用于返回请求是否成功，以及相应问题
 * 版权: Kevin
 * 作者: xck
 * 时间: 2019-08-28 15:53
 */
@Getter
@Setter
public class ResultDTO {
    private ResultEnum result;
    private String Message;

    public ResultDTO(ResultEnum result, String message) {
        this.result = result;
        this.Message = message;
    }
}
