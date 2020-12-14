package ai.lenna.lennachatmodul;

import androidx.annotation.Keep;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;

import ai.lenna.lennachatmodul.LoginOrRegister.lennaLogin.LoginLennaReq;
import ai.lenna.lennachatmodul.LoginOrRegister.lennaLogin.LoginLennaResp;
import ai.lenna.lennachatmodul.LoginOrRegister.regist.model.RegisterLennaReq;
import ai.lenna.lennachatmodul.LoginOrRegister.regist.model.RegisterLennaReqEncrypt;
import ai.lenna.lennachatmodul.LoginOrRegister.regist.model.RegisterLennaResp;
import ai.lenna.lennachatmodul.chat.ChatActivity;
import ai.lenna.lennachatmodul.network.ApiBuilder;
import ai.lenna.lennachatmodul.network.ApiService;
import ai.lenna.lennachatmodul.util.Constant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Keep
public class LoginOrRegisterActivity extends AppCompatActivity {

    RegisterLennaReq registerLennaReq;

    Button btnErrorRegisterOrLogin;
    LinearLayout llFailedRegisterOrLogin, llLoadRegisterOrLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_or_register);

        Chat.setContext(LoginOrRegisterActivity.this);

        btnErrorRegisterOrLogin = (Button) findViewById(R.id.btn_error_register_or_login);
        llLoadRegisterOrLogin = (LinearLayout) findViewById(R.id.ll_load_register_or_login);
        llFailedRegisterOrLogin = (LinearLayout) findViewById(R.id.ll_failed_register_or_login);

        btnErrorRegisterOrLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requstRegister(
                        LoginOrRegisterActivity.this, registerLennaReq,
                        llLoadRegisterOrLogin, llFailedRegisterOrLogin);
            }
        });

//        Intent i = getIntent();
        Intent intent= getIntent();
        Bundle bundle = intent.getExtras();
        String stringJsonRegister = "";
        if (bundle != null) {
            stringJsonRegister = bundle.getString("DATA_REQ_REGISTER");
            registerLennaReq = new Gson().fromJson(stringJsonRegister,
                    RegisterLennaReq.class);

            requstRegister(
                    LoginOrRegisterActivity.this, registerLennaReq,
                    llLoadRegisterOrLogin, llFailedRegisterOrLogin);
        }

    }

    private static void requstRegister(Context context,
                                       RegisterLennaReq registerLennaReq,
                                       LinearLayout llLoadRegisterOrLogin,
                                       LinearLayout llFailedRegisterOrLogin) {

        if(Prefs.getString("TOKEN_LOGIN","").equals("")){
            ApiService service = ApiBuilder.getClient().create(ApiService.class);
            RegisterLennaReq req = new RegisterLennaReq();
            RegisterLennaReqEncrypt registerRedEncrypt = new RegisterLennaReqEncrypt();
            req.setName(registerLennaReq.getName());
            req.setNickname(registerLennaReq.getNickname());
            req.setEmail(registerLennaReq.getEmail());
            req.setPhone(registerLennaReq.getPhone());
            req.setClient(registerLennaReq.getClient());
            ArrayList<String> array_item = new ArrayList<>();
            array_item.add("lenna");
            req.setInterests(registerLennaReq.getInterests());
            req.setPassword(registerLennaReq.getPassword());
            req.setFcm_token(registerLennaReq.getFcm_token());
            req.setSales_force_id((registerLennaReq.getSales_force_id()));


            Log.d("RegisterReq_123", String.valueOf(req));
            try {
//                String data = AesCipher.encrypt(Constant.REG_KEY, req.toString());
//                registerRedEncrypt.setData(data);
////                Enc
//                Call<RegisterLennaRespEncrypt> call = service.regEncrypt(registerRedEncrypt, Constant.APP_ID);
//                call.enqueue(new Callback<RegisterLennaRespEncrypt>() {
//                    @Override
//                    public void onResponse(Call<RegisterLennaRespEncrypt> call, Response<RegisterLennaRespEncrypt> response) {
//                        if (response.isSuccessful()) {
//                            try{
//                                String res = AesCipher.decrypt(Constant.REG_KEY,response.body().getData());
//                                RegisterLennaResp  chatResp = new RegisterLennaResp();
//                                Gson gson = new Gson();
//                                chatResp = gson.fromJson(res,RegisterLennaResp.class);
//                                Chat.setToken(chatResp.getAccessToken());
//                                Chat.setUserId(chatResp.getData().getId());
//                                Intent intent = new Intent(context, ChatActivity.class);
//                                intent.setAction(Intent.ACTION_VIEW);
//                                ((Activity)context).startActivity(intent);
//                            }catch (Exception e){
//                                Toast.makeText(context, "Sorry somethink wrong",Toast.LENGTH_LONG).show();
//                            }
//                        }else{
//                            Toast.makeText(context,String.valueOf(response),Toast.LENGTH_LONG).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<RegisterLennaRespEncrypt> call, Throwable t) {
//                        Toast.makeText(context,t.toString(),Toast.LENGTH_LONG).show();
//                        Prefs.putString("TOKEN","");
//                    }
//                });

                // Not enc
                Call<RegisterLennaResp> call = service.reg(req, Constant.APP_ID);
                call.enqueue(new Callback<RegisterLennaResp>() {
                    @Override
                    public void onResponse(Call<RegisterLennaResp> call, Response<RegisterLennaResp> response) {
                        Log.d("hai_hai", new Gson().toJson(response.body()));
                        if (response.isSuccessful()) {
                            if (response.body().getSuccess()) {
                                Chat.setToken(response.body().getAccessToken());
                                Chat.setUserId(response.body().getData().getId());
                                Intent intent = new Intent(context, ChatActivity.class);
                                intent.setAction(Intent.ACTION_VIEW);
                                ((Activity)context).startActivity(intent);
                            } else {
                                int codeError = response.body().getError().getCode();
                                if (codeError == 5000) {
                                    loginLenna(context, registerLennaReq,
                                            llLoadRegisterOrLogin, llFailedRegisterOrLogin);
                                } else {
                                    Prefs.putString("TOKEN_LOGIN","");
                                    requstRegister(context, registerLennaReq,
                                            llLoadRegisterOrLogin, llFailedRegisterOrLogin);
                                }
                            }
                        } else {
                            Prefs.putString("TOKEN_LOGIN","");
                            llFailedRegisterOrLogin.setVisibility(View.VISIBLE);
                            llLoadRegisterOrLogin.setVisibility(View.GONE);
                        }
                    }
                    @Override
                    public void onFailure(Call<RegisterLennaResp> call, Throwable t) {
//                        Toast.makeText(context,t.toString(),Toast.LENGTH_LONG).show();
                        Prefs.putString("TOKEN_LOGIN","");
                        llFailedRegisterOrLogin.setVisibility(View.VISIBLE);
                        llLoadRegisterOrLogin.setVisibility(View.GONE);
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

    private static void loginLenna(Context context,
                                   RegisterLennaReq registerLennaReq,
                                   LinearLayout llLoadRegisterOrLogin,
                                   LinearLayout llFailedRegisterOrLogin) {

        LoginLennaReq loginReq = new LoginLennaReq();

        loginReq.setClient("android");
        loginReq.setEmail(registerLennaReq.getEmail());
        loginReq.setFcmToken(registerLennaReq.getFcm_token());
        loginReq.setPassword(registerLennaReq.getPassword());

        ApiService serviceLogin = ApiBuilder.getClient().create(ApiService.class);
        Call<LoginLennaResp> callLogin = serviceLogin.loginLenna(loginReq, Constant.APP_ID);
        callLogin.enqueue(new Callback<LoginLennaResp>() {
            @Override
            public void onResponse(Call<LoginLennaResp> callLogin, Response<LoginLennaResp> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSuccess()) {
                        Chat.setToken(response.body().getAccessToken());
                        Chat.setUserId(response.body().getData().getId());
                        Intent intent = new Intent(context, ChatActivity.class);
                        intent.setAction(Intent.ACTION_VIEW);
                        ((Activity)context).startActivity(intent);
                    } else {
                        Prefs.putString("TOKEN_LOGIN","");
                        llFailedRegisterOrLogin.setVisibility(View.VISIBLE);
                        llLoadRegisterOrLogin.setVisibility(View.GONE);
                    }
                } else {
                    Prefs.putString("TOKEN_LOGIN","");
                    llFailedRegisterOrLogin.setVisibility(View.VISIBLE);
                    llLoadRegisterOrLogin.setVisibility(View.GONE);
                }
            }
            @Override
            public void onFailure(Call<LoginLennaResp> callLogin, Throwable t) {
                Prefs.putString("TOKEN_LOGIN","");
                llFailedRegisterOrLogin.setVisibility(View.VISIBLE);
                llLoadRegisterOrLogin.setVisibility(View.GONE);
            }
        });
    }
}
