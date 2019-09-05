package cn.nh.kevin.demo.Rest;

import cn.nh.kevin.demo.Enum.ResultEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * 标题:http json返回对象
 * 描述:
 * 版权: Kevin
 * 作者: xck
 * 时间: 2019-09-02 11:07
 */
@Data
public class JsonResponse<T extends Object> implements Serializable {
    private static final long serialVersionUID = 1L;
    private T data;
    private ResultEnum resultEnum;
    private String errorCode;
    private String errorMessage;

    public JsonResponse(T data) {
        this.data = data;
        this.resultEnum = ResultEnum.SUCCESS;
    }

    public JsonResponse(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.resultEnum = ResultEnum.FAIL;
    }

    public static <T> JsonResponse<T> success(T data) {
        return new JsonResponse<T>(data);
    }

    public static <T> JsonResponse<T> fail(String errorCode, String errorMessage) {
        return new JsonResponse<T>(errorCode, errorMessage);
    }
}
