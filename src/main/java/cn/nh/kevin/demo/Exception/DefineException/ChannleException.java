package cn.nh.kevin.demo.Exception.DefineException;

import lombok.Data;

/**
 * 标题: 渠道校验异常
 * 描述:
 * 版权: Kevin
 * 作者: xck
 * 时间: 2019-08-30 15:13
 */
@Data
public class ChannleException extends Exception{

    private String errorcode;

    public ChannleException(String message, String errorcode) {
        super(message);
        this.errorcode = errorcode;
    }
}
