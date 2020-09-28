package ai.lenna.lennachatmodul.chat.model;

import com.pixplicity.easyprefs.library.Prefs;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import ai.lenna.lennachatmodul.chat.ChatContract;
import ai.lenna.lennachatmodul.network.ApiBuilder;
import ai.lenna.lennachatmodul.network.ApiService;
import ai.lenna.lennachatmodul.util.Constant;
import ai.lenna.lennachatmodul.util.GenericErrorResponseBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatModel implements ChatContract.Model {

    @Override
    public void sendChatToBot(final ChatContract.Model.OnFinishedListener onFinishedListener, ChatReq chatReq) {

        ApiService service = ApiBuilder.getClient().create(ApiService.class);
        Call<ChatResp> call = service.submitChat("Bearer " + Prefs.getString("TOKEN",""), chatReq,Constant.BOT_ID);

        call.enqueue(new Callback<ChatResp>() {
            @Override
            public void onResponse(Call<ChatResp> call, Response<ChatResp> response) {

                if (response.isSuccessful()) {
                    onFinishedListener.onFinishedSuccess(response.body());

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

            @Override
            public void onFailure(Call<ChatResp> call, Throwable t) {
                onFinishedListener.onFailure();
            }
        });

    }
}
