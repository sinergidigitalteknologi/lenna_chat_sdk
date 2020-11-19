package ai.lenna.lennachatmodul.chat.model.type;


import androidx.annotation.Keep;

import ai.lenna.lennachatmodul.chat.model.ChatObject;

@Keep
public class ChatResponse extends ChatObject {
    @Override
    public int getType() {
        return ChatObject.RESPONSE_OBJECT;
    }
}
