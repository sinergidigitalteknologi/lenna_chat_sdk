package ai.lenna.lennachatmodul.chatRoom;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.pixplicity.easyprefs.library.Prefs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import ai.lenna.lennachatmodul.Chat;
import ai.lenna.lennachatmodul.LoginOrRegister.lennaLogin.LoginLennaReq;
import ai.lenna.lennachatmodul.LoginOrRegister.lennaLogin.LoginLennaResp;
import ai.lenna.lennachatmodul.LoginOrRegister.regist.model.RegisterLennaReq;
import ai.lenna.lennachatmodul.LoginOrRegister.regist.model.RegisterLennaResp;
import ai.lenna.lennachatmodul.R;
import ai.lenna.lennachatmodul.chat.ChatActivity;
import ai.lenna.lennachatmodul.chat.model.ChatLoadReq;
import ai.lenna.lennachatmodul.chat.model.ChatResp;
import ai.lenna.lennachatmodul.network.ApiBuilder;
import ai.lenna.lennachatmodul.network.ApiService;
import ai.lenna.lennachatmodul.util.AesCipher;
import ai.lenna.lennachatmodul.util.Constant;
import ai.lenna.lennachatmodul.util.Encryp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Keep
public class ChatRoomLennaFragment extends Fragment {

    Button btnRegisterOrLoginTry;
    ImageView ivImgMsgBot, ivImgMsgAgent;
    LinearLayout containInbox, llContainBot,
            llContainAgent, llRegisterOrLoginErr,
            llRegisterOrLoginLoading;

    TextView tvNameMsgBot, tvNameMsgAgent,
            tvLastMsgBot, tvLastMsgAgent,
            tvTimeMsgBot, tvTimeMsgAgent;

    public ChatLoadReq chatLoadReq;
    RegisterLennaReq registerLennaReq;

    public ChatRoomLennaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_chat_room, container, false);

        containInbox = mView.findViewById(R.id.contain_inbox);
        ivImgMsgBot = mView.findViewById(R.id.iv_img_msg_bot);
        llContainBot = mView.findViewById(R.id.ll_contain_bot);
        tvNameMsgBot = mView.findViewById(R.id.tv_name_msg_bot);
        tvLastMsgBot = mView.findViewById(R.id.tv_last_msg_bot);
        tvTimeMsgBot = mView.findViewById(R.id.tv_time_msg_bot);
        ivImgMsgAgent = mView.findViewById(R.id.iv_img_msg_agent);
        llContainAgent = mView.findViewById(R.id.ll_contain_agent);
        tvLastMsgAgent = mView.findViewById(R.id.tv_last_msg_agent);
        tvNameMsgAgent = mView.findViewById(R.id.tv_name_msg_agent);
        tvTimeMsgAgent = mView.findViewById(R.id.tv_time_msg_agent);
        llRegisterOrLoginErr = mView.findViewById(R.id.ll_register_or_login_err);
        btnRegisterOrLoginTry = mView.findViewById(R.id.btn_register_or_login_try);
        llRegisterOrLoginLoading = mView.findViewById(R.id.ll_register_or_login_loading);

        chatLoadReq = new ChatLoadReq();
        registerLennaReq = new RegisterLennaReq();

        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnRegisterOrLoginTry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setVisibilityInbox(View.GONE, View.GONE, View.VISIBLE);
                funAuthenticationLenna();
            }
        });

        llContainBot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Chat.setGreetingMessage("hai");
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                startActivity(intent);
            }
        });

        llContainAgent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Chat.setGreetingMessage("live");
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                startActivity(intent);
            }
        });

        setVisibilityInbox(View.GONE, View.GONE, View.VISIBLE);
        funAuthenticationLenna();
    }

    @Override
    public void onResume() {
        super.onResume();
        setVisibilityInbox(View.GONE, View.GONE, View.VISIBLE);
        funListChatHistory();
    }

    public void funListChatHistory() {
        String dataUserId = Prefs.getString("USER_ID","");
        Log.d("dataUserId", dataUserId);
        if (!dataUserId.equals("")) {
            chatLoadReq.setUserId(dataUserId);
            loadListChat();
        }

    }

    @Keep
    private void loadListChat() {

        ApiService service = ApiBuilder.getClient().create(ApiService.class);

        Call<ArrayList<ChatResp>> call = service.getChatList(chatLoadReq, Constant.BOT_ID, String.valueOf(1), "1000");
        call.enqueue(new Callback<ArrayList<ChatResp>>() {
            @Override
            public void onResponse(Call<ArrayList<ChatResp>> call, Response<ArrayList<ChatResp>> response) {

                if (response.isSuccessful()) {
                    List<ChatResp> list = response.body();
                    if (list.size() > 0) {

                        Collections.reverse(list);
                        List<ChatResp> filteredListBot = new ArrayList<>();
                        List<ChatResp> filteredListAgent = new ArrayList<>();

                        Calendar calendar = Calendar.getInstance();
                        SimpleDateFormat sdfDateFormat = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
                        String strDate = sdfDateFormat.format(calendar.getTime());

                        for (ChatResp item : list) {
                            if (item.getUsertype().equals("bot")) {
                                filteredListBot.add(item);
                            }
                            if (item.getUsertype().equals("user_platform")) {
                                filteredListAgent.add(item);
                            }
                        }

                        if (filteredListBot.size() > 0) {

                            ArrayList<JsonObject> dataBot = filteredListBot.get(filteredListBot.size()-1).getResult().getOutput();
                            JsonObject jsonDataBot = dataBot.get(dataBot.size()-1);

                            if (strDate.equals(filteredListBot.get(filteredListBot.size()-1).getDate())) {
                                tvTimeMsgBot.setText(filteredListBot.get(filteredListBot.size()-1).getTime());
                            } else {
                                tvTimeMsgBot.setText(filteredListBot.get(filteredListBot.size()-1).getDate());
                            }

                            if (jsonDataBot.get("type").getAsString().equals("text")) {
                                tvLastMsgBot.setText(jsonDataBot.get("text").getAsString());
                            } else {
                                tvLastMsgBot.setText(jsonDataBot.get("type").getAsString());
                            }

                        } else {
                            tvTimeMsgBot.setText("-");
                            tvLastMsgBot.setText(". . .");
                        }

                        if (filteredListAgent.size() > 0) {

                            ArrayList<JsonObject> dataAgent = filteredListAgent.get(filteredListAgent.size()-1).getResult().getOutput();
                            JsonObject jsonDataAgent = dataAgent.get(dataAgent.size()-1);

                            if (strDate.equals(filteredListAgent.get(filteredListAgent.size()-1).getDate())) {
                                tvTimeMsgAgent.setText(filteredListAgent.get(filteredListAgent.size()-1).getTime());
                            } else {
                                tvTimeMsgAgent.setText(filteredListAgent.get(filteredListAgent.size()-1).getDate());
                            }

                            if (jsonDataAgent.get("type").getAsString().equals("text")) {
                                tvLastMsgAgent.setText(jsonDataAgent.get("text").getAsString());
                            } else {
                                tvLastMsgAgent.setText(jsonDataAgent.get("type").getAsString());
                            }

                        } else {
                            tvTimeMsgAgent.setText("-");
                            tvLastMsgAgent.setText(". . .");
                        }

                    } else {
                        tvLastMsgBot.setText(". . .");
                        tvLastMsgAgent.setText(". . .");

                        tvTimeMsgBot.setText("-");
                        tvTimeMsgAgent.setText("-");
                    }

                    tvNameMsgBot.setText(Constant.NAME_BOT_MSG);
                    tvNameMsgAgent.setText(Constant.NAME_AGENT_MSG);
                    ivImgMsgBot.setImageBitmap(Constant.ICON_BOT_LENNA_BITMAP);
                    ivImgMsgAgent.setImageBitmap(Constant.ICON_AGENT_LENNA_BITMAP);
//                    ivImgMsgBot.setImageResource(Prefs.getInt("ICON_BOT_LENNA", 0));
//                    ivImgMsgAgent.setImageResource(Prefs.getInt("ICON_AGENT_LENNA", 0));
                    setVisibilityInbox(View.VISIBLE, View.GONE, View.GONE);
                } else {
                    setVisibilityInbox(View.GONE, View.VISIBLE, View.GONE);
                }
            }
            @Override
            public void onFailure(Call<ArrayList<ChatResp>> call, Throwable t) {
                setVisibilityInbox(View.GONE, View.VISIBLE, View.GONE);
            }
        });
    }

    public void funAuthenticationLenna() {
        Encryp encryp  = new Encryp();
        encryp.dc();
        String password = "" ;

        registerLennaReq.setName(Constant.USER_NAME);
        registerLennaReq.setSales_force_id(Constant.SALEFORCEID);
        registerLennaReq.setNickname(Constant.USER_NAME);
        registerLennaReq.setEmail(Constant.EMAIL);
        registerLennaReq.setPhone(Constant.PHONE);
        registerLennaReq.setClient("android");
        registerLennaReq.setFcm_token(Constant.FCM_TOKEN_LOGIN);
        ArrayList<String> array_item = new ArrayList<>();
        array_item.add("lenna");
        registerLennaReq.setInterests(array_item);

        try {
            password = AesCipher.encrypt(Constant.SECRET_KEY, Constant.EMAIL);
            registerLennaReq.setPassword(password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        requstRegister();
    }

    private void requstRegister() {

        if(Prefs.getString("TOKEN_LOGIN","").equals("")){
            ApiService service = ApiBuilder.getClient().create(ApiService.class);


            Log.d("RegisterReq_123", String.valueOf(registerLennaReq));
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
                Call<RegisterLennaResp> call = service.reg(registerLennaReq, Constant.APP_ID);
                call.enqueue(new Callback<RegisterLennaResp>() {
                    @Override
                    public void onResponse(Call<RegisterLennaResp> call, Response<RegisterLennaResp> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getSuccess()) {
                                Log.d("account_register", new Gson().toJson(response.body()));
                                Chat.setToken(response.body().getAccessToken());
                                Chat.setUserId(response.body().getData().getId());
                                funListChatHistory();
//                                setVisibilityInbox(View.VISIBLE, View.GONE, View.GONE);
                            } else {
                                int codeError = response.body().getError().getCode();
                                if (codeError == 5000) {
                                    loginLenna();
                                } else {
                                    Prefs.putString("TOKEN_LOGIN","");
                                    requstRegister();
                                }
                            }
                        } else {
                            Prefs.putString("TOKEN_LOGIN","");
                            setVisibilityInbox(View.GONE, View.VISIBLE, View.GONE);
                        }
                    }
                    @Override
                    public void onFailure(Call<RegisterLennaResp> call, Throwable t) {
//                        Toast.makeText(context,t.toString(),Toast.LENGTH_LONG).show();
                        Prefs.putString("TOKEN_LOGIN","");
                        setVisibilityInbox(View.GONE, View.VISIBLE, View.GONE);
                    }
                });
            } catch (Exception e) {
//                e.printStackTrace();
                Prefs.putString("TOKEN_LOGIN","");
                setVisibilityInbox(View.GONE, View.VISIBLE, View.GONE);
            }
        }else{
            Log.d("Login_token : ", (Prefs.getString("TOKEN_LOGIN", "")));
            Chat.setToken(Prefs.getString("TOKEN_LOGIN", ""));
            Chat.setUserId(Prefs.getString("USER_ID", ""));
            funListChatHistory();
        }
    }

    private void loginLenna() {

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
                        Log.d("account_login", new Gson().toJson(response.body()));
                        Chat.setToken(response.body().getAccessToken());
                        Chat.setUserId(response.body().getData().getId());
                        funListChatHistory();
                    } else {
                        Prefs.putString("TOKEN_LOGIN","");
                        setVisibilityInbox(View.GONE, View.VISIBLE, View.GONE);
                    }
                } else {
                    Prefs.putString("TOKEN_LOGIN","");
                    setVisibilityInbox(View.GONE, View.VISIBLE, View.GONE);
                }
            }
            @Override
            public void onFailure(Call<LoginLennaResp> callLogin, Throwable t) {
                Prefs.putString("TOKEN_LOGIN","");
                setVisibilityInbox(View.GONE, View.VISIBLE, View.GONE);
            }
        });
    }

    public void setVisibilityInbox(int vContainInbox,
                                   int vLlRegisterOrLoginErr,
                                   int vLlRegisterOrLoginLoading) {
        containInbox.setVisibility(vContainInbox);
        llRegisterOrLoginErr.setVisibility(vLlRegisterOrLoginErr);
        llRegisterOrLoginLoading.setVisibility(vLlRegisterOrLoginLoading);
    }
}