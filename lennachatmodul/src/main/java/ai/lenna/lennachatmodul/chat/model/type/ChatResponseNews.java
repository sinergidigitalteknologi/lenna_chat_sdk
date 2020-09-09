package ai.lenna.lennachatmodul.chat.model.type;

import ai.lenna.lennachatmodul.chat.model.ChatObject;

public class ChatResponseNews extends ChatObject {
    @Override
    public int getType() {
        return ChatObject.RESPONSE_NEWS;
    }
}
