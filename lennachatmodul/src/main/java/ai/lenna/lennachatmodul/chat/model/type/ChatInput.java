package ai.lenna.lennachatmodul.chat.model.type;


import androidx.annotation.Keep;

import ai.lenna.lennachatmodul.chat.model.ChatObject;

@Keep
public class ChatInput extends ChatObject {

    @Override
    public int getType() {
        return ChatObject.INPUT_OBJECT;
    }
}
