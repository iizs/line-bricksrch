package net.iizs.lego.linebricksrch.model;

public class Sku {
    public String currency;
    public float price;
    public String site;
    public String shopUrl;
    public String skuNumber;

    @Override
    public String toString() {
        return "Sku{" +
                "currency='" + currency + '\'' +
                ", price=" + price +
                ", site='" + site + '\'' +
                ", shopUrl='" + shopUrl + '\'' +
                ", skuNumber='" + skuNumber + '\'' +
                '}';
    }
}
