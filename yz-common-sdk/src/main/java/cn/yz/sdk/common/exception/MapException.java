package cn.yz.sdk.common.exception;

public class MapException extends BaseUncheckedException{

    public MapException(Throwable cause) {
        super(cause);
    }
    public MapException(ExceptionCode code) {
        super(code.getCode(), code.getMsg());
    }

}
