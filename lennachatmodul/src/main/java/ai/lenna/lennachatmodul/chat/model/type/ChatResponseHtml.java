package ai.lenna.lennachatmodul.chat.model.type;

import ai.lenna.lennachatmodul.chat.model.ChatObject;

public class ChatResponseHtml extends ChatObject {
    @Override
    public int getType() {
        return ChatObject.RESPONSE_HTML;
    }
}
