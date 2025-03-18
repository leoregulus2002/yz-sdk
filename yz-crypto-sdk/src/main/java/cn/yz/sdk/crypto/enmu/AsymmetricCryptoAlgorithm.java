package cn.yz.sdk.crypto.enmu;

public enum AsymmetricCryptoAlgorithm {
    RSA("RSA"),
    ECIES("ECIES"),
    SM2("SM2"),
    ElGamal("ElGamal"),
    ;

    private final String type;

    AsymmetricCryptoAlgorithm(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type;
    }
}
