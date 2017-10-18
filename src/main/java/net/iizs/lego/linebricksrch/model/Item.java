package net.iizs.lego.linebricksrch.model;

import java.util.List;

public class Item {
    public String product_id;
    public String title;
    public int piece_count;
    public List<String> image;
    public String datetime_updated;
    public String product_code;
    public List<Sku> skus;

    @Override
    public String toString() {
        return "Item{" +
                "product_id='" + product_id + '\'' +
                ", title='" + title + '\'' +
                ", piece_count=" + piece_count +
                ", image=" + image +
                ", datetime_updated='" + datetime_updated + '\'' +
                ", product_code='" + product_code + '\'' +
                ", skus=" + skus +
                '}';
    }
}
