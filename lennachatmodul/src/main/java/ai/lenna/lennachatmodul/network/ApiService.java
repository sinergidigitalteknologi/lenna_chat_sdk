package ai.lenna.lennachatmodul.network;


import androidx.annotation.Keep;

import ai.lenna.lennachatmodul.chat.model.ChatEncrypResp;
import ai.lenna.lennachatmodul.chat.model.ChatReq;
import ai.lenna.lennachatmodul.chat.model.ChatResp;
import ai.lenna.lennachatmodul.regist.model.RegisterReqEncrypt;
import ai.lenna.lennachatmodul.regist.model.RegisterReq;
import ai.lenna.lennachatmodul.regist.model.RegisterResp;
import ai.lenna.lennachatmodul.regist.model.RegisterRespEncrypt;
import ai.lenna.lennachatmodul.util.Constant;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

@Keep
public interface ApiService {

    String bot_id = Constant.BOT_ID;
    String BOT_CHAT = "app/public/api/"+ bot_id+"/webhook/mobile";

    @POST("backend/api/{appId}/users/register")
    Call<RegisterResp> reg(@Body RegisterReq registerReq,@Path("appId") String app_id);

    //encrypt
    @POST("app/public/api/{botId}/webhook/mobile")
    Call<ChatEncrypResp> submitChatEncrypt(@Header("Authorization") String token, @Body ChatReq req, @Path("botId") String bot_id);

    @POST("backend/api/{appId}/users/register")
    Call<RegisterRespEncrypt> regEncrypt(@Body RegisterReqEncrypt registerReqEncrypt, @Path("appId") String app_id);

}
