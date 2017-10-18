package net.iizs.lego.linebricksrch.model;

import java.util.List;

public class ItemSearchResult {
    public String item_number;
    public List<Item> items;

    @Override
    public String toString() {
        return "ItemSearchResult{" +
                "item_number='" + item_number + '\'' +
                ", items=" + items +
                '}';
    }
}
