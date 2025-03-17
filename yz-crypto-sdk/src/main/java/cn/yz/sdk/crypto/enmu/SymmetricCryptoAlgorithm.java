package cn.yz.sdk.crypto.enmu;

public enum SymmetricCryptoAlgorithm {
    AES("AES"),
    DES("DES"),
    ;

    private final String type;

    SymmetricCryptoAlgorithm(String type) {
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
