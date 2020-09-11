package ai.lenna.lennachatmodul.chat.model.type;


import ai.lenna.lennachatmodul.chat.model.ChatObject;

public class ChatInput extends ChatObject {

    @Override
    public int getType() {
        return ChatObject.INPUT_OBJECT;
    }
}
