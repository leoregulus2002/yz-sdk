package cn.yz.sdk.crypto.enmu;

public enum SummaryAlgorithm {
    SHA1("SHA-1"),
    SHA256("SHA-256"),
    SHA384("SHA-384"),
    SHA512("SHA-512"),
    MD2("MD2"),
    MD5("MD5"),
    SM3("SM3"),
    ;

    private final String type;

    SummaryAlgorithm(String type) {
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
