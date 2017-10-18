package net.iizs.lego.linebricksrch.model;

public class Sku {
    public String currency;
    public float price;
    public String site;
    public String shop_url;
    public String sku_number;

    @Override
    public String toString() {
        return "Sku{" +
                "currency='" + currency + '\'' +
                ", price=" + price +
                ", site='" + site + '\'' +
                ", shop_url='" + shop_url + '\'' +
                ", sku_number='" + sku_number + '\'' +
                '}';
    }
}
