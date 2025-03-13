package cn.yz.sdk.common.exception;

import cn.yz.sdk.common.util.ExceptionUtil;
import cn.yz.sdk.common.util.StrUtil;

public class CryptoException extends RuntimeException{

    public CryptoException(Throwable e) {
        super(ExceptionUtil.getMessage(e), e);
    }

    public CryptoException(String message) {
        super(message);
    }

    public CryptoException(String messageTemplate, Object... params) {
        super(StrUtil.format(messageTemplate, params));
    }

    public CryptoException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public CryptoException(String message, Throwable throwable, boolean enableSuppression, boolean writableStackTrace) {
        super(message, throwable, enableSuppression, writableStackTrace);
    }

    public CryptoException(Throwable throwable, String messageTemplate, Object... params) {
        super(StrUtil.format(messageTemplate, params), throwable);
    }
}
