package cn.yz.sdk.common.exception;

import cn.yz.sdk.common.util.StrUtil;

import java.io.Serial;

public class BaseUncheckedException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 4955410990126902125L;
    private String message;
    private int code;

    public BaseUncheckedException(Throwable cause) {
        super(cause);
    }

    public BaseUncheckedException(final int code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public BaseUncheckedException(final int code, final String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BaseUncheckedException(final int code, final String message, Throwable cause) {
        super(cause);
        this.code = code;
        this.message = message;
    }

    public BaseUncheckedException(final int code, final String format, Object... args) {
        super(StrUtil.contains(format, "{}") ? StrUtil.format(format, args) : String.format(format, args));
        this.code = code;
        this.message = StrUtil.contains(format, "{}") ? StrUtil.format(format, args) : String.format(format, args);
    }

    public String getMessage() {
        return this.message;
    }

    public int getCode() {
        return this.code;
    }
}
