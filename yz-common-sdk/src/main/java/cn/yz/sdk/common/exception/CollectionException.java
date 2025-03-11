package cn.yz.sdk.common.exception;

public class CollectionException extends BaseUncheckedException{

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
