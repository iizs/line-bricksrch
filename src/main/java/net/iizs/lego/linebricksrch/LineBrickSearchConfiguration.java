package net.iizs.lego.linebricksrch;

import net.iizs.lego.linebricksrch.service.BrickSearchService;
import net.iizs.lego.linebricksrch.service.BrickSearchServiceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LineBrickSearchConfiguration {
    @Bean
    public BrickSearchService getBrickSearchService() {
        return BrickSearchServiceBuilder.create().build();
    }
}
