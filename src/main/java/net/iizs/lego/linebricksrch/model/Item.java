package net.iizs.lego.linebricksrch.model;

import java.util.List;

public class Item {
    public String productId;
    public String title;
    public int pieceCount;
    public List<String> image;
    public String datetimeUpdated;
    public String productCode;
    public List<Sku> skus;

    @Override
    public String toString() {
        return "Item{" +
                "productId='" + productId + '\'' +
                ", title='" + title + '\'' +
                ", pieceCount=" + pieceCount +
                ", image=" + image +
                ", datetimeUpdated='" + datetimeUpdated + '\'' +
                ", productCode='" + productCode + '\'' +
                ", skus=" + skus +
                '}';
    }
}
