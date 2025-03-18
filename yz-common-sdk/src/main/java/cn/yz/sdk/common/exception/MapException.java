package cn.yz.sdk.common.exception;

import java.io.Serial;

public class MapException extends BaseUncheckedException{

    @Serial
    private static final long serialVersionUID = -2519469674662543855L;

    public MapException(Throwable cause) {
        super(cause);
    }
    public MapException(ExceptionCode code) {
        super(code.getCode(), code.getMsg());
    }

}
