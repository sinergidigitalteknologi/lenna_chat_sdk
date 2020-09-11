package ai.lenna.lennachatmodul.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;

import ai.lenna.lennachatmodul.Chat;
import ai.lenna.lennachatmodul.chat.ChatActivity;
import ai.lenna.lennachatmodul.network.ApiBuilder;
import ai.lenna.lennachatmodul.network.ApiService;
import ai.lenna.lennachatmodul.regist.RegisterContract;
import ai.lenna.lennachatmodul.regist.RegisterPresenter;
import ai.lenna.lennachatmodul.regist.model.RegisterReq;
import ai.lenna.lennachatmodul.regist.model.RegisterResp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestToken {

    public static void request(Context context){
        String password = "" ;
        RegisterReq registerReq = new RegisterReq();
        registerReq.setName(Constant.USER_NAME);
        registerReq.setNickname(Constant.USER_NAME);
        registerReq.setEmail(Prefs.getString("id_user","") + "@goersapp.com");
        registerReq.setPhone("123456789");
        registerReq.setClient("android");
        ArrayList<String> array_item = new ArrayList<>();
        array_item.add("goers");
        registerReq.setInterests(array_item);
        try {
            String scrt_keky = AesCipher.decrypt("lennachatsdk",Constant.SECRET_KEY);
            password = AesCipher.encrypt(scrt_keky, Prefs.getString("id_user",""));
        } catch (Exception e) {
            e.printStackTrace();
        }
        registerReq.setPassword(password);
        Log.d("TOKEN=" , Prefs.getString("TOKEN",""));
        if(Prefs.getString("TOKEN","").equals("")){
            ApiService service = ApiBuilder.getClient().create(ApiService.class);
            Call<RegisterResp> call = service.reg(registerReq, Constant.APP_ID);
            call.enqueue(new Callback<RegisterResp>() {
                @Override
                public void onResponse(Call<RegisterResp> call, Response<RegisterResp> response) {
                    if (response.isSuccessful()) {
                        try{
                            Chat.setToken(response.body().getAccessToken());
                            Chat.setUserId(response.body().getData().getId());
                            Intent intent = new Intent(context, ChatActivity.class);
                            intent.setAction(Intent.ACTION_VIEW);
                            ((Activity)context).startActivity(intent);
                        }catch (Exception e){

                        }
                    }else{

                    }
                }

                @Override
                public void onFailure(Call<RegisterResp> call, Throwable t) {

                }
            });
        }else{
            Intent intent = new Intent(context, ChatActivity.class);
            intent.setAction(Intent.ACTION_VIEW);
            ((Activity)context).startActivity(intent);
        }

    }
}
