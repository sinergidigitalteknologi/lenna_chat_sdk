package ai.lenna.lennachatmodul.network;


import androidx.annotation.Keep;

import java.util.ArrayList;

import ai.lenna.lennachatmodul.LoginOrRegister.lennaLogin.LoginLennaReq;
import ai.lenna.lennachatmodul.LoginOrRegister.lennaLogin.LoginLennaResp;
import ai.lenna.lennachatmodul.chat.model.ChatEncrypResp;
import ai.lenna.lennachatmodul.chat.model.ChatLoadReq;
import ai.lenna.lennachatmodul.chat.model.ChatReq;
import ai.lenna.lennachatmodul.chat.model.ChatResp;
import ai.lenna.lennachatmodul.LoginOrRegister.regist.model.RegisterLennaReqEncrypt;
import ai.lenna.lennachatmodul.LoginOrRegister.regist.model.RegisterLennaReq;
import ai.lenna.lennachatmodul.LoginOrRegister.regist.model.RegisterLennaResp;
import ai.lenna.lennachatmodul.LoginOrRegister.regist.model.RegisterLennaRespEncrypt;
import ai.lenna.lennachatmodul.chatRoom.model.RoomResolveResp;
import ai.lenna.lennachatmodul.util.Constant;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

@Keep
public interface ApiService {

    String bot_id = Constant.BOT_ID;
    String BOT_CHAT = "app/public/api/"+ bot_id+"/webhook/mobile";

    @POST("backend/api/{appId}/users/register")
    Call<RegisterLennaResp> reg(@Body RegisterLennaReq registerReq, @Path("appId") String app_id);

    @POST("backend/api/{appId}/auth/login")
    Call<LoginLennaResp> loginLenna(@Body LoginLennaReq loginReq, @Path("appId") String app_id);

    //encrypt
    @POST("app/public/api/{botId}/webhook/mobile")
    Call<ChatEncrypResp> submitChatEncrypt(@Header("Authorization") String token, @Body ChatReq req, @Path("botId") String bot_id);

    @POST("app/public/api/webhook/digipos/resolve-livechat")
    Call<RoomResolveResp> funResolveChat(@Header("userId") String userId);

    @POST("app/public/api/{botId}/message/get-more-message-digipos")
    Call<ArrayList<ChatResp>> getChatList(@Body ChatLoadReq chatLoadReq, @Path("botId") String bot_id, @Query("page") String page, @Query("per_page") String per_page);

    @POST("backend/api/{appId}/users/register")
    Call<RegisterLennaRespEncrypt> regEncrypt(@Body RegisterLennaReqEncrypt registerReqEncrypt, @Path("appId") String app_id);

}
