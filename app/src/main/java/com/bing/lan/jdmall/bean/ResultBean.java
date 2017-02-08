package com.bing.lan.jdmall.bean;

/**
 * @author 蓝兵
 * @time 2017/2/8  15:03
 */
public abstract class ResultBean<T> {

    private boolean success;
    private String errorMsg;
    private T result;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "errorMsg='" + errorMsg + '\'' +
                ", success=" + success +
                ", result=" + result +
                '}';
    }
}
