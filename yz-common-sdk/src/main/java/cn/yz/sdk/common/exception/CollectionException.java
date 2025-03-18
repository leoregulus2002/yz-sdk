package cn.yz.sdk.common.exception;

import java.io.Serial;

public class CollectionException extends BaseUncheckedException{

    @Serial
    private static final long serialVersionUID = -1395241257733829085L;

    public CollectionException(Throwable cause) {
        super(cause);
    }

    public CollectionException(ExceptionCode code) {
        super(code.getCode(), code.getMsg());
    }

    public String toString() {
        String message = this.getMessage();
        return "CollectionException [message=" + message + ", code=" + this.getCode() + "]";
    }
}
