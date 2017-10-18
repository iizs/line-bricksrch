package net.iizs.lego.linebricksrch;

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

@RunWith(SpringRunner.class)
@SpringBootTest
public class LineBrickSearchApplicationTests {

	@Autowired
	BrickSearchService brickSearchService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void brickSearchService() throws IOException {
		Call<ItemSearchResult> call = brickSearchService.getByItemNumber("10242");
		Response<ItemSearchResult> response = call.execute();

		System.out.println(response.body().toString());

	}


}
