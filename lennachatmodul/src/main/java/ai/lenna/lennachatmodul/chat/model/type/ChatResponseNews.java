package ai.lenna.lennachatmodul.chat.model.type;

import androidx.annotation.Keep;

import ai.lenna.lennachatmodul.chat.model.ChatObject;

@Keep
public class ChatResponseNews extends ChatObject {
    @Override
    public int getType() {
        return ChatObject.RESPONSE_NEWS;
    }
}
