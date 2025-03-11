package cn.yz.sdk.common.exception;

public class ArrayException extends BaseUncheckedException{

    public ArrayException(Throwable cause) {
        super(cause);
    }

    public ArrayException(ExceptionCode code) {
        super(code.getCode(), code.getMsg());
    }

    public String toString() {
        String message = this.getMessage();
        return "CollectionException [message=" + message + ", code=" + this.getCode() + "]";
    }
}
