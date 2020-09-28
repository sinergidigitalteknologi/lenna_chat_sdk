package ai.lenna.lennachatmodul.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

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

public class RequestToken implements RegisterContract.View {
    public static RegisterReq registerReq;
    public static RegisterPresenter registerPresenter ;

    public static void request(Context context){
        Log.d("regisrequestt" , String.valueOf( registerReq.getEmail()));
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
                            Toast.makeText(context, String.valueOf(e),Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(context,String.valueOf(response),Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<RegisterResp> call, Throwable t) {
                    Toast.makeText(context,String.valueOf(t),Toast.LENGTH_LONG).show();

                }
            });
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
