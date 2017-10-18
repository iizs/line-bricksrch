package net.iizs.lego.linebricksrch.model;

import java.util.List;

public class ItemSearchResult {
    public String itemNumber;
    public List<Item> items;

    @Override
    public String toString() {
        return "ItemSearchResult{" +
                "itemNumber='" + itemNumber + '\'' +
                ", items=" + items +
                '}';
    }
}
