package cn.yz.sdk.common.exception;

import java.io.Serial;

public class ArrayException extends BaseUncheckedException{

    @Serial
    private static final long serialVersionUID = 8512401285266636820L;

    public ArrayException(Throwable cause) {
        super(cause);
    }

    public ArrayException(ExceptionCode code) {
        super(code.getCode(), code.getMsg());
    }

    public String toString() {
        String message = this.getMessage();
        return STR."CollectionException [message=\{message}, code=\{this.getCode()}]";
    }
}
