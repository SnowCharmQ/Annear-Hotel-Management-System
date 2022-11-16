package sustech.hotel.common.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import sustech.hotel.exception.BaseException;

import java.io.Serializable;
import java.util.Map;

import static sustech.hotel.common.utils.Constant.OK;

@ApiModel("API通用返回数据")
public class JsonResult<E> implements Serializable {
    @ApiModelProperty("响应码")
    private Integer state;
    @ApiModelProperty("响应信息")
    private String message;
    @ApiModelProperty("响应数据")
    private E data;
    @ApiModelProperty("响应异常信息")
    private BaseException ex;

    @ApiModelProperty("响应异常信息Map")
    private Map<String, String> errors;

    public JsonResult() {
        super();
        this.state = OK;
    }

    public JsonResult(E data) {
        super();
        this.state = OK;
        this.data = data;
    }

    public JsonResult(BaseException ex) {
        super();
        this.ex = ex;
        this.state = ex.getState();
        this.message = ex.getMessage();
    }

    public JsonResult(Integer state, String message) {
        super();
        this.state = state;
        this.message = message;
    }

    public JsonResult(Integer state, String message, Map<String, String> errors) {
        super();
        this.state = state;
        this.message = message;
        this.errors = errors;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public BaseException getEx() {
        return ex;
    }

    public void setEx(BaseException ex) {
        this.ex = ex;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "state=" + state +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
