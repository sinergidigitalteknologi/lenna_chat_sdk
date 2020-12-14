package ai.lenna.lennachatmodul.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Keep;
import com.google.gson.Gson;
import com.pixplicity.easyprefs.library.Prefs;

import java.io.Serializable;
import java.util.ArrayList;
import ai.lenna.lennachatmodul.Chat;
import ai.lenna.lennachatmodul.LoginOrRegisterActivity;
import ai.lenna.lennachatmodul.chat.ChatActivity;
import ai.lenna.lennachatmodul.LoginOrRegister.lennaLogin.LoginLennaReq;
import ai.lenna.lennachatmodul.LoginOrRegister.lennaLogin.LoginLennaResp;
import ai.lenna.lennachatmodul.network.ApiBuilder;
import ai.lenna.lennachatmodul.network.ApiService;
import ai.lenna.lennachatmodul.LoginOrRegister.regist.model.RegisterLennaReqEncrypt;
import ai.lenna.lennachatmodul.LoginOrRegister.regist.model.RegisterLennaReq;
import ai.lenna.lennachatmodul.LoginOrRegister.regist.model.RegisterLennaResp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Keep
public class RequestToken {
    public static RegisterLennaReq registerReq;
    @Keep
    public static void request(Context context){
        //

        Intent intent = new Intent(context, LoginOrRegisterActivity.class);
        intent.putExtra("DATA_REQ_REGISTER", new Gson().toJson(registerReq));
        intent.setAction(Intent.ACTION_VIEW);
        ((Activity)context).startActivity(intent);

//        if(Prefs.getString("TOKEN","").equals("")){
//            ApiService service = ApiBuilder.getClient().create(ApiService.class);
//            RegisterLennaReq req = new RegisterLennaReq();
//            RegisterLennaReqEncrypt registerRedEncrypt = new RegisterLennaReqEncrypt();
//            req.setName(registerReq.getName());
//            req.setNickname(registerReq.getNickname());
//            req.setEmail(registerReq.getEmail());
//            req.setPhone(registerReq.getPhone());
//            req.setClient(registerReq.getClient());
//            ArrayList<String> array_item = new ArrayList<>();
//            array_item.add("lenna");
//            req.setInterests(registerReq.getInterests());
//            req.setPassword(registerReq.getPassword());
//            req.setFcm_token(registerReq.getFcm_token());
//            req.setSales_force_id((registerReq.getSales_force_id()));
//
//
//            Log.d("RegisterReq_123", String.valueOf(req));
//            try {
////                String data = AesCipher.encrypt(Constant.REG_KEY, req.toString());
////                registerRedEncrypt.setData(data);
//////                Enc
////                Call<RegisterLennaRespEncrypt> call = service.regEncrypt(registerRedEncrypt, Constant.APP_ID);
////                call.enqueue(new Callback<RegisterLennaRespEncrypt>() {
////                    @Override
////                    public void onResponse(Call<RegisterLennaRespEncrypt> call, Response<RegisterLennaRespEncrypt> response) {
////                        if (response.isSuccessful()) {
////                            try{
////                                String res = AesCipher.decrypt(Constant.REG_KEY,response.body().getData());
////                                RegisterLennaResp  chatResp = new RegisterLennaResp();
////                                Gson gson = new Gson();
////                                chatResp = gson.fromJson(res,RegisterLennaResp.class);
////                                Chat.setToken(chatResp.getAccessToken());
////                                Chat.setUserId(chatResp.getData().getId());
////                                Intent intent = new Intent(context, ChatActivity.class);
////                                intent.setAction(Intent.ACTION_VIEW);
////                                ((Activity)context).startActivity(intent);
////                            }catch (Exception e){
////                                Toast.makeText(context, "Sorry somethink wrong",Toast.LENGTH_LONG).show();
////                            }
////                        }else{
////                            Toast.makeText(context,String.valueOf(response),Toast.LENGTH_LONG).show();
////                        }
////                    }
////
////                    @Override
////                    public void onFailure(Call<RegisterLennaRespEncrypt> call, Throwable t) {
////                        Toast.makeText(context,t.toString(),Toast.LENGTH_LONG).show();
////                        Prefs.putString("TOKEN","");
////                    }
////                });
//
//                // Not enc
//                Call<RegisterLennaResp> call = service.reg(req, Constant.APP_ID);
//                call.enqueue(new Callback<RegisterLennaResp>() {
//                    @Override
//                    public void onResponse(Call<RegisterLennaResp> call, Response<RegisterLennaResp> response) {
//                        Log.d("hai_hai", new Gson().toJson(response.body()));
//                        if (response.isSuccessful()) {
//
//                            if (response.body().getSuccess()) {
//                                Chat.setToken(response.body().getAccessToken());
//                                Chat.setUserId(response.body().getData().getId());
//                                Intent intent = new Intent(context, ChatActivity.class);
//                                intent.setAction(Intent.ACTION_VIEW);
//                                ((Activity)context).startActivity(intent);
//                            } else {
//                                if (response.body().getError().getCode() == 5000) {
//                                    loginLenna(context);
//                                    Log.d("jsonResp", response.body().getError().getMessage());
//                                } else {
//                                    Prefs.putString("TOKEN","");
//                                    request(context);
//                                }
//                            }
//                        } else {
//                            Prefs.putString("TOKEN","");
//                            request(context);
//                        }
//                    }
//                    @Override
//                    public void onFailure(Call<RegisterLennaResp> call, Throwable t) {
////                        Toast.makeText(context,t.toString(),Toast.LENGTH_LONG).show();
//                        Prefs.putString("TOKEN","");
//                        request(context);
//                    }
//                });
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }else{
//            Intent intent = new Intent(context, ChatActivity.class);
//            intent.setAction(Intent.ACTION_VIEW);
//            ((Activity)context).startActivity(intent);
//        }
    }

//    private static void loginLenna(Context context) {
//        LoginLennaReq loginReq = new LoginLennaReq();
//
//        loginReq.setClient("android");
//        loginReq.setEmail(registerReq.getEmail());
//        loginReq.setFcmToken(registerReq.getFcm_token());
//        loginReq.setPassword(registerReq.getPassword());
//
//        ApiService service = ApiBuilder.getClient().create(ApiService.class);
//        Call<LoginLennaResp> callLogin = service.loginLenna(loginReq, Constant.APP_ID);
//        callLogin.enqueue(new Callback<LoginLennaResp>() {
//            @Override
//            public void onResponse(Call<LoginLennaResp> callLogin, Response<LoginLennaResp> response) {
//                if (response.isSuccessful()) {
//                    if (response.body().getSuccess()) {
//                        Chat.setToken(response.body().getAccessToken());
//                        Chat.setUserId(response.body().getData().getId());
//                        Intent intent = new Intent(context, ChatActivity.class);
//                        intent.setAction(Intent.ACTION_VIEW);
//                        ((Activity)context).startActivity(intent);
//                    } else {
//                        Prefs.putString("TOKEN","");
//                        request(context);
//                    }
//                } else {
//                    Prefs.putString("TOKEN","");
//                    request(context);
//                }
//            }
//            @Override
//            public void onFailure(Call<LoginLennaResp> callLogin, Throwable t) {
//                Prefs.putString("TOKEN","");
//                request(context);
//            }
//        });
//    }
}
