package net.iizs.lego.linebricksrch;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.action.*;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.*;
import com.linecorp.bot.model.message.template.ButtonsTemplate;
import com.linecorp.bot.model.response.BotApiResponse;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import net.iizs.lego.linebricksrch.exception.BrickItemNotFoundException;
import net.iizs.lego.linebricksrch.model.Item;
import net.iizs.lego.linebricksrch.model.ItemSearchResult;
import net.iizs.lego.linebricksrch.service.BrickSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
@LineMessageHandler
public class LineBrickSearchApplication extends SpringBootServletInitializer {

    @Autowired
    public BrickSearchService brickSearchService;

    @Autowired
    private LineMessagingClient lineMessagingClient;

	public static void main(String[] args) {
		SpringApplication.run(LineBrickSearchApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(LineBrickSearchApplication.class);
	}

	@EventMapping
	public void handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
		System.out.println("event: " + event);

        ArrayList<Message> replyMessages = new ArrayList<>();

        String itemNumber = event.getMessage().getText().trim();

        try {

            Integer.parseInt(itemNumber);
            ItemSearchResult searchResult = getItemSearchResultByItemNumber(itemNumber);

            if (searchResult.items.size() == 0) {
                throw new BrickItemNotFoundException(itemNumber);
            }

            Item i = searchResult.items.get(0);
            replyMessages.add(new TextMessage(
                    String.format("%1$s: %2$s (%3$d pcs)",
                            itemNumber,
                            i.title,
                            i.pieceCount
                    )
            ));

            if ( i.skus.size() != 0 ) {
                replyMessages.add(new TextMessage( i.skus.get(0).shopUrl ));
            }

            replyMessages.add(new ImageMessage(
                    String.format("https://sh-s7-live-s.legocdn.com/is/image/LEGO/%1$s?$leaf2$", itemNumber),
                    String.format("https://sh-s7-live-s.legocdn.com/is/image/LEGO/%1$s?$leaf2$", itemNumber)
            ));
        } catch ( BrickItemNotFoundException e ) {
            // TODO: make string to constant
            replyMessages.add(new TextMessage(String.format("%1$s에 대한 정보를 찾을 수 없습니다.", e.getMessage() )));
        } catch( NumberFormatException e ) {
            if ( itemNumber.charAt(0) == '/' ) {
                replyMessages.addAll(handleCommand( event.getReplyToken(), itemNumber ));
            } else {
                // TODO: make string to constant
                replyMessages.add(new TextMessage("숫자를 입력해주세요."));
            }
        } catch ( IOException e ) {
            // TODO: make string to constant
            replyMessages.add(new TextMessage("나중에 다시 시도해주세요."));
        }

        // line allows maximum 1 ~ 5 messages at once
		if ( replyMessages.size() > 0 && replyMessages.size() <= 5 ) {
            reply(event.getReplyToken(), replyMessages);
        }
	}

	@EventMapping
	public void handleDefaultMessageEvent(Event event) {
		System.out.println("event: " + event);
	}

    private List<Message> handleCommand(String replyToken, String command) {
        String[] args = command.split(" ");
        String cmd = args[0];

        if ( cmd.startsWith("/sticker") ) {
            if ( args.length < 3 ) {
                return Collections.singletonList(new TextMessage("Usage: /sticker pkgId stickerId"));
            }
            return Collections.singletonList(new StickerMessage(args[1], args[2]));
        } else if ( cmd.startsWith("/button")) {
            Action[] actions = {
                    new MessageAction("msg action", "text"),
                    new URIAction("uri action", "http://iizs.net"),
                    new DatetimePickerAction("datetime action", "param=data", "date"),
                    new PostbackAction("postback action", "postback", "Postback sent")
            };
            return Collections.singletonList(new TemplateMessage(
                    "Buttons Template",
                    new ButtonsTemplate(null,"title01", "msg01", Arrays.asList(actions))
            ));

        }

	    return Collections.singletonList(new TextMessage("알 수 없는 명령어입니다."));
    }

	private ItemSearchResult getItemSearchResultByItemNumber(String itemNumber) throws IOException {
        Call<ItemSearchResult> call = brickSearchService.getByItemNumber(itemNumber);
        Response<ItemSearchResult> response = call.execute();

        return response.body();
	}

	// TODO null check required for parameters
    private void reply(String replyToken, Message message) {
        reply(replyToken, Collections.singletonList(message));
    }

    // TODO null check required for parameters
    private void reply(String replyToken, List<Message> messages) {
        try {
            BotApiResponse apiResponse = lineMessagingClient
                    .replyMessage(new ReplyMessage(replyToken, messages))
                    .get();
            //log.info("Sent messages: {}", apiResponse);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}
