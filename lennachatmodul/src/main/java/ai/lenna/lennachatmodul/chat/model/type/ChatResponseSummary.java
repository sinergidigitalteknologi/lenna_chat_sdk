package ai.lenna.lennachatmodul.chat.model.type;


import ai.lenna.lennachatmodul.chat.model.ChatObject;

public class ChatResponseSummary extends ChatObject {
    @Override
    public int getType() {
        return ChatObject.RESPONSE_SUMMARY;
    }
}
