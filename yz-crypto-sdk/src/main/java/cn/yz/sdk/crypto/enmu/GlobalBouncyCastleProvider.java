package cn.yz.sdk.crypto.enmu;

import java.io.Serializable;
import java.security.Provider;
import java.security.Security;
import java.util.ServiceLoader;

public enum GlobalBouncyCastleProvider implements Serializable {


    INSTANCE;
    private static final long serialVersionUID = 4980191558277180342L;

    private Provider provider;

    GlobalBouncyCastleProvider() {
        try {
            ServiceLoader<Provider> providers = ServiceLoader.load(Provider.class);
            providers.stream()
                    .map(ServiceLoader.Provider::get)
                    .filter(provider -> "org.bouncycastle.jce.provider.BouncyCastleProvider".equals(provider.getProperty("Provider.id className")))
                    .findFirst()
                    .ifPresent(provider -> {
                        Security.insertProviderAt(provider, 0);
                        this.provider = provider;
                    });
        }catch (Throwable _){

        }
    }

    public Provider getProvider() {
        return this.provider;
    }

}
