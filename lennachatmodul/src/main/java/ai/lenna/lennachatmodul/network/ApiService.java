package ai.lenna.lennachatmodul.network;


import ai.lenna.lennachatmodul.chat.model.ChatReq;
import ai.lenna.lennachatmodul.chat.model.ChatResp;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {

    String BOT_CHAT = "app/public/api/PdRgRe/webhook/mobile";

    @POST(BOT_CHAT)
    Call<ChatResp> submitChat(@Header("Authorization") String token, @Body ChatReq req);

}
