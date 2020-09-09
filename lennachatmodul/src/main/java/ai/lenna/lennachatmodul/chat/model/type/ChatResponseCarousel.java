package ai.lenna.lennachatmodul.chat.model.type;


import ai.lenna.lennachatmodul.chat.model.ChatObject;

public class ChatResponseCarousel extends ChatObject {
    @Override
    public int getType() {
        return ChatObject.RESPONSE_CAROUSEL;
    }
}
