package cn.yz.sdk.common.exception;

public enum ExceptionCode {
    SUCCESS(0, "成功"),
    SYSTEM_BUSY(-1, "系统繁忙~请稍后再试"),
    SYSTEM_TIMEOUT(-2, "系统维护中~请稍后再试"),
    VALID_ASSERT(-3,"断言验证异常"),
    NOT_NULL(-4,"参数为null异常"),
    ;

    private final int code;
    private String msg;

    ExceptionCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public ExceptionCode build(String msg, Object... param) {
        this.msg = String.format(msg, param);
        return this;
    }

    public ExceptionCode param(Object... param) {
        this.msg = String.format(this.msg, param);
        return this;
    }
}
