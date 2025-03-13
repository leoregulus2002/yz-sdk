package cn.yz.sdk.crypto.enmu;

import java.io.Serializable;
import java.security.Provider;
import java.security.Security;

public enum GlobalBouncyCastleProvider implements Serializable {


    INSTANCE;
    private static final long serialVersionUID = 4980191558277180342L;

    private Provider provider;

    GlobalBouncyCastleProvider() {
        try {
            // 仅加载类，不执行 `new` 操作，确保类存在
            Class<?> clazz = Class.forName("org.bouncycastle.jce.provider.BouncyCastleProvider");
            Object providerInstance = clazz.getDeclaredConstructor().newInstance(); // 反射创建实例
            Security.insertProviderAt((java.security.Provider) providerInstance, 0);
            this.provider = (java.security.Provider) providerInstance;
        } catch (ReflectiveOperationException e) {
        }


    }

    public Provider getProvider() {
        return this.provider;
    }

}
