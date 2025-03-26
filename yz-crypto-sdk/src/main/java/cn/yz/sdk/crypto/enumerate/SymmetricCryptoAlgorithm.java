package cn.yz.sdk.crypto.enumerate;

public enum SymmetricCryptoAlgorithm {
    AES("AES"),
    DES("DES"),
    RC4("RC4"),
    Blowfish("Blowfish"),
    DESede("DESede"),
    SM4("SM4"),
    RC2("RC2"),
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
