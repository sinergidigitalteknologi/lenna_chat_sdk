package ai.lenna.lennachatmodul.chat.model.type;

import ai.lenna.lennachatmodul.chat.model.ChatObject;

public class ChatResponseMovie extends ChatObject {
    @Override
    public int getType() {
        return ChatObject.RESPONSE_MOVIE;
    }
}
