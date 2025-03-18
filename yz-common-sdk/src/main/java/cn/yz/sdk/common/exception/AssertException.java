package cn.yz.sdk.common.exception;

import java.io.Serial;

public class AssertException extends BaseUncheckedException{
    @Serial
    private static final long serialVersionUID = 656951990294962562L;

    public AssertException(Throwable cause) {
        super(cause);
    }

    public AssertException(String message) {
        super(ExceptionCode.VALID_ASSERT.getCode(), message);
    }

    public AssertException(String message, Throwable cause) {
        super(ExceptionCode.VALID_ASSERT.getCode(), message, cause);
    }

    public AssertException(final String format, Object... args) {
        super(ExceptionCode.VALID_ASSERT.getCode(), format, args);
    }

    public String toString() {
        String message = this.getMessage();
        return "AssertException [message=" + message + ", code=" + this.getCode() + "]";
    }
}
