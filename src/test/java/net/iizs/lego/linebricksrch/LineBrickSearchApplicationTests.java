package net.iizs.lego.linebricksrch;

import net.iizs.lego.linebricksrch.model.Item;
import net.iizs.lego.linebricksrch.model.ItemSearchResult;
import net.iizs.lego.linebricksrch.service.BrickSearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LineBrickSearchApplicationTests {

	@Autowired
	private BrickSearchService brickSearchService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void brickSearchService() throws IOException {
		Call<ItemSearchResult> call = brickSearchService.getByItemNumber("10242");
		Response<ItemSearchResult> response = call.execute();

        ItemSearchResult result = response.body();

        assertEquals("10242", result.itemNumber);
        assertEquals(1, result.items.size());
        Item item = result.items.get(0);
        assertEquals(1077, item.pieceCount);
        assertEquals("MINI Cooper", item.title);
        assertEquals("10242", item.productCode);

        assertTrue("item.sku.size() > 0", item.skus.size() > 0);

	}


}
