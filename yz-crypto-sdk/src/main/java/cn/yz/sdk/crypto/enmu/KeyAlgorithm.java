package cn.yz.sdk.crypto.enmu;

public enum KeyAlgorithm {

    RSA("RSA"),
    ECIES("ECIES"),
    SM2("SM2"),
    ElGamal("ElGamal"),
    AES("AES"),
    DES("DES"),
    RC4("RC4"),
    Blowfish("Blowfish"),
    DESede("DESede"),
    SM4("SM4"),
    RC2("RC2"),
    ECDSA("ECDSA"),
    ;

    private final String type;

    KeyAlgorithm(String type) {
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
