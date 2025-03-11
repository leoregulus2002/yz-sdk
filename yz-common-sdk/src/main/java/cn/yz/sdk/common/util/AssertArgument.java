package cn.yz.sdk.common.util;

import cn.yz.sdk.common.exception.AssertException;

import java.util.function.Supplier;

public class AssertArgument {
    public AssertArgument() {
    }

    public static <X extends Throwable> void isTrue(boolean expression, Supplier<? extends X> supplier) throws X{
        if (!expression){
            throw supplier.get();
        }
    }

    public static void isTrue(boolean expression,String message,Object... params){
        isTrue(expression,() ->{
            throw new AssertException(StrUtil.format(message,params));

        });
    }

    public static <X extends Throwable> void isFalse(boolean expression, Supplier<? extends X> supplier) throws X{
        if (expression){
            throw supplier.get();
        }
    }

    public static void isFalse(boolean expression,String message,Object... params){
        isFalse(expression,() ->{
            throw new AssertException(StrUtil.format(message,params));
        });
    }

    public static <E,T extends Iterable<E> ,X extends Throwable> void isEmpty(T collection, Supplier<? extends X> supplier) throws X {
        if (!CollectionUtil.isEmpty(collection)){
            throw supplier.get();
        }
    }

    public static <E,T extends Iterable<E>> void isEmpty(T collection,String message,Object... params) {
        isEmpty(collection,()->{
            throw new AssertException(StrUtil.format(message,params));
        });
    }
}
