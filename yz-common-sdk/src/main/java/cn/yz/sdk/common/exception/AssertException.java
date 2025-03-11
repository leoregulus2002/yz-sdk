package cn.yz.sdk.common.exception;

public class AssertException extends BaseUncheckedException{
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
