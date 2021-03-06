package ai.lenna.lennachatmodul.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Keep;
import com.google.gson.Gson;
import com.pixplicity.easyprefs.library.Prefs;
import java.util.ArrayList;
import ai.lenna.lennachatmodul.Chat;
import ai.lenna.lennachatmodul.chat.ChatActivity;
import ai.lenna.lennachatmodul.network.ApiBuilder;
import ai.lenna.lennachatmodul.network.ApiService;
import ai.lenna.lennachatmodul.regist.RegisterContract;
import ai.lenna.lennachatmodul.regist.model.RegisterReqEncrypt;
import ai.lenna.lennachatmodul.regist.model.RegisterReq;
import ai.lenna.lennachatmodul.regist.model.RegisterResp;
import ai.lenna.lennachatmodul.regist.model.RegisterRespEncrypt;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Keep
public class RequestToken implements RegisterContract.View {
    public static RegisterReq registerReq;
    @Keep
    public static void request(Context context){
        //

        if(Prefs.getString("TOKEN","").equals("")){
            ApiService service = ApiBuilder.getClient().create(ApiService.class);
            RegisterReq req = new RegisterReq();
            RegisterReqEncrypt registerRedEncrypt = new RegisterReqEncrypt();
            req.setName(registerReq.getName());
            req.setNickname(registerReq.getNickname());
            req.setEmail(registerReq.getEmail());
            req.setPhone(registerReq.getPhone());
            req.setClient(registerReq.getClient());
            ArrayList<String> array_item = new ArrayList<>();
            array_item.add("goers");
            req.setInterests(registerReq.getInterests());
            req.setPassword(registerReq.getPassword());
            req.setFcm_token(registerReq.getFcm_token());


            try {
                String data = AesCipher.encrypt(Constant.REG_KEY, req.toString());
                registerRedEncrypt.setData(data);

                Call<RegisterResp> call = service.reg(req, Constant.APP_ID);
                call.enqueue(new Callback<RegisterResp>() {
                    @Override
                    public void onResponse(Call<RegisterResp> call, Response<RegisterResp> response) {
                        if (response.isSuccessful()) {

                                Chat.setToken(response.body().getAccessToken());
                                Chat.setUserId(response.body().getData().getId());
                                Intent intent = new Intent(context, ChatActivity.class);
                                intent.setAction(Intent.ACTION_VIEW);
                                ((Activity)context).startActivity(intent);

                        }else{
                            Toast.makeText(context,String.valueOf(response),Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterResp> call, Throwable t) {
                        Toast.makeText(context,t.toString(),Toast.LENGTH_LONG).show();
                        Prefs.putString("TOKEN","");
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }


        }else{
            Intent intent = new Intent(context, ChatActivity.class);
            intent.setAction(Intent.ACTION_VIEW);
            ((Activity)context).startActivity(intent);
        }

    }

    @Override
    public void showDialogSukses(RegisterResp registerResp) {

    }

    @Override
    public void showDialogGagal(String message) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onResponseFailure(Throwable throwable) {

    }
}
