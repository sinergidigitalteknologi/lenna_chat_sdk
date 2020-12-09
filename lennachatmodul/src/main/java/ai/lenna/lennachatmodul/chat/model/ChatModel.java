package ai.lenna.lennachatmodul.chat.model;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Keep;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pixplicity.easyprefs.library.Prefs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import ai.lenna.lennachatmodul.chat.ChatContract;
import ai.lenna.lennachatmodul.chat.model.bean.ChatResultBean;
import ai.lenna.lennachatmodul.network.ApiBuilder;
import ai.lenna.lennachatmodul.network.ApiService;
import ai.lenna.lennachatmodul.util.AesCipher;
import ai.lenna.lennachatmodul.util.Constant;
import ai.lenna.lennachatmodul.util.GenericErrorResponseBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Keep
public class ChatModel implements ChatContract.Model {

    @Keep
    @Override
    public void sendChatToBot(final ChatContract.Model.OnFinishedListener onFinishedListener, ChatReq chatReq) {

        ApiService service = ApiBuilder.getClient().create(ApiService.class);
//        Call<ChatResp> call = service.submitChat("Bearer " + Prefs.getString("TOKEN",""), chatReq,Constant.BOT_ID);
        Call<ChatEncrypResp> call = service.submitChatEncrypt("Bearer " + Prefs.getString("TOKEN",""), chatReq,Constant.BOT_ID);

        call.enqueue(new Callback<ChatEncrypResp>() {
            @Keep
            @Override
            public void onResponse(Call<ChatEncrypResp> call, Response<ChatEncrypResp> response) {
                if (response.isSuccessful()) {
                    try {
                        String data = AesCipher.decrypt(Constant.APP_KEY, response.body().getEncryption());
                        JSONObject jsonObject = new JSONObject(data);
                        ChatResp  chatResp = new ChatResp();
                        Gson gson = new Gson();
                        chatResp = gson.fromJson(data,ChatResp.class);

//                        Log.d("jsonObject1234", new Gson().toJson(customChatResp(chatResp)));
//                        onFinishedListener.onFinishedSuccess(customChatResp(chatResp));
                        onFinishedListener.onFinishedSuccess(chatResp);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    GenericErrorResponseBean errorResponse = new GenericErrorResponseBean();
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        JSONObject jsonerror = jsonObject.getJSONObject("error");
                        errorResponse.setCode(jsonerror.getInt("code"));
                        errorResponse.setMessage(jsonerror.getString("message"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    onFinishedListener.onFinishedFail(errorResponse);
                }
            }

            @Keep
            @Override
            public void onFailure(Call<ChatEncrypResp> call, Throwable t) {
                onFinishedListener.onFailure();
            }
        });

    }

    public ChatResp customChatResp(ChatResp dataChatResp) {

        // Set data ChatOutput
        ArrayList<JsonObject> arrayOutput = new ArrayList<>();
        for (int i=0; i<dataChatResp.getResult().getOutput().size(); i++) {
            JsonObject containJsonObject = dataChatResp.getResult().getOutput().get(i);
            containJsonObject.addProperty("user_type", "bot");
            containJsonObject.addProperty("date_msg", dataChatResp.getDate());
            containJsonObject.addProperty("time_msg", dataChatResp.getTime());
            arrayOutput.add(containJsonObject);
        }


        // Set data ChatResultBean
        ChatResultBean customChatResult = new ChatResultBean();

        customChatResult.setOutput(arrayOutput);
        customChatResult.setIntents(dataChatResp.getResult().getIntents());
        customChatResult.setIntentsid(dataChatResp.getResult().getIntentsid());
        customChatResult.setQuickbutton(dataChatResp.getResult().getQuickbutton());


        // Set data ChatResp
        ChatResp customChatResp = new ChatResp();

        customChatResp.setResult(customChatResult);
        customChatResp.setDate(dataChatResp.getDate());
        customChatResp.setTime(dataChatResp.getTime());
        customChatResp.setStatuscode(dataChatResp.getStatuscode());
        customChatResp.setResolvequery(dataChatResp.getResolvequery());

        return customChatResp;
//
//        return customChatResp;
    }
}
