package ai.lenna.lennachatmodul.chat;

import androidx.annotation.Keep;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.AlarmClock;
import android.provider.CalendarContract;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.karan.churi.PermissionManager.PermissionManager;
import com.pixplicity.easyprefs.library.Prefs;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import ai.lenna.lennachatmodul.Chat;
import ai.lenna.lennachatmodul.R;
import ai.lenna.lennachatmodul.chat.adapter.ChatAdapter;
import ai.lenna.lennachatmodul.chat.adapter.QuickButtonAdapter;
import ai.lenna.lennachatmodul.chat.adapter.QuickButtonCallBack;
import ai.lenna.lennachatmodul.chat.airport.Airport;
import ai.lenna.lennachatmodul.chat.model.ChatLoadReq;
import ai.lenna.lennachatmodul.chat.model.ChatReq;
import ai.lenna.lennachatmodul.chat.model.ChatResp;
import ai.lenna.lennachatmodul.chat.model.bean.ChatResultBean;
import ai.lenna.lennachatmodul.chat.model.column.ChatColumnForm;
import ai.lenna.lennachatmodul.chat.model.output.ChatOutputDatepickerForm;
import ai.lenna.lennachatmodul.chat.model.output.ChatOutputDonasi;
import ai.lenna.lennachatmodul.chat.model.output.ChatOutputForm;
import ai.lenna.lennachatmodul.chat.model.output.ChatOutputFormTravel;
import ai.lenna.lennachatmodul.chat.model.output.ChatOutputMultiDatePicker;
import ai.lenna.lennachatmodul.chat.model.output.ChatOutputPlanePassengerForm;
import ai.lenna.lennachatmodul.chat.model.output.ChatOutputPlaneTripDetailForm;
import ai.lenna.lennachatmodul.chat.model.output.ChatOutputTrainPassengerForm;
import ai.lenna.lennachatmodul.chat.model.output.ChatOutputTrainTripDetailForm;
import ai.lenna.lennachatmodul.chat.model.output.action.ChatDataAction;
import ai.lenna.lennachatmodul.chat.model.output.action.ChatOutputAction;
import ai.lenna.lennachatmodul.network.ApiBuilder;
import ai.lenna.lennachatmodul.network.ApiService;
import ai.lenna.lennachatmodul.room.AppDatabase;
import ai.lenna.lennachatmodul.room.AppExecutors;
import ai.lenna.lennachatmodul.room.entity.ChatResponseEntity;
import ai.lenna.lennachatmodul.util.AesCipher;
import ai.lenna.lennachatmodul.util.Constant;
import ai.lenna.lennachatmodul.util.DialogUtils;
import ai.lenna.lennachatmodul.util.ShowAllert;
import ai.lenna.lennachatmodul.util.SpeakToTextUtils;
import ai.lenna.lennachatmodul.util.TtsUtils;
import im.delight.android.location.SimpleLocation;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static ai.lenna.lennachatmodul.network.ApiBuilder.getImageHttpClient;

@Keep
public class ChatActivity extends AppCompatActivity implements RecognitionListener, ChatContract.View, LocationListener, ChatAdapter.OnClickListener {
    private static final String INPUT = "input";
    private static final int LOCATION_REQUEST = 1355;
    private static final String tag = "ChatFragment";
    private static final String[] LOCATION_PERMS = {Manifest.permission.ACCESS_FINE_LOCATION};

    public static final String TAG = "ChatActivity";
    public static int LOGO_TITLE = R.drawable.logo_lenna_blue;

    LinearLayout llForm;
    ShowAllert showAllert;
    private AppDatabase mDb;
    private Location location;
    private Activity activity;
    private TtsUtils ttsUtils;
    PermissionManager permission;
    private Intent recognizerIntent;
    private ChatPresenter presenter;
    Button btnOkMultiDate, btnOkDate;
    private ChatActivity chatActivity;
    private LocationManager locManager;
    private SimpleLocation locationsMap;
    private ChatOutputAction outputAction;
    private SpeechRecognizer speech = null;
    DatePicker picker, pickerNow, pickerTomorrows;
    ImageView imageViewCloseMultiDate, imageViewClose;
    private BroadcastReceiver mRegistrationBroadcastReceiver;

    int page = 1;
    boolean isOnPause = false;

    private double lat;
    private double lng;
    private double acc;
    private double alt;
    private double latitude;
    private double longitude;
    private boolean is_gps_active;
    private boolean is_gps_enabled;
    private boolean isTouch = true;
    private boolean is_network_active;
    private boolean is_network_enabled;

    ImageView closeNoAktifGS;
    private ChatAdapter chatAdapter;
    Button btn_batal, btn_pengaturan, btnErrorLoad;
    ConstraintLayout layout_chatbox2;
    private QuickButtonAdapter quickButtonAdapter;
    private RecyclerView rvChatList, rvQuickButton;
    private LinearLayout llLoadChatList, llFailedLoadChatList;

    Spinner spinnerAsalPesawat, spinnerTujuanPesawat, spinnerDewasaPesawat, spinnerAnakPesawat, spinnerBayiPesawat;

    EditText etJumlahDonasi, etNamaDonasi, etEmailDonasi, etNoKtp;

    //detil form penumpang kereta
    DatePickerDialog.OnDateSetListener mDataSetListenerKepulangan;Spinner spLokasiPerjKereta, spTujuanPerjKereta, spLokasiDewasaPerjkereta, spLokasiAnakPerjkereta, spLokasiBayiPerjkereta;

    private String current = "";
    String choose = "Uang & Emas";
    private int statusLoading = 0;
    String choosePlaneTitle = "Tuan";
    boolean clickBottomNavigationChat = true;

    String asalStasiun, tujuanStasiun, tglBerangkat, tglPulang;
    String asalBandara, tujuanBandara, tglBerangkatPesawatVar, tglPulangPesawatVar;

    ImageView ivActionMic,img_icon_chat;
    ImageView ivImageViewEnter;
    EditText etSendMessage;

    public static Picasso mPicasso;

    ChatLoadReq chatLoadReq;

    @Keep
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Constant.IS_CHAT_LENNA_ACTIVE = true;

        OkHttpClient client = getImageHttpClient();
        mPicasso = new Picasso.Builder(ChatActivity.this)
                .downloader(new OkHttp3Downloader(client))
                .build();

        ivActionMic = findViewById(R.id.action_mic);
        ivImageViewEnter = findViewById(R.id.imageViewEnter);
        etSendMessage = findViewById(R.id.et_send_message);
        img_icon_chat = findViewById(R.id.img_icon_chat);
        // click listener
        ivActionMic.setOnClickListener(micClicked);
        ivImageViewEnter.setOnClickListener(imgEnterClicked);
        //set logo title
        img_icon_chat.setImageResource(Prefs.getInt("LOGO_TITLE",LOGO_TITLE));
        activity = this.activity;
        showAllert = new ShowAllert();
        permission = new PermissionManager() {};
        clickBottomNavigationChat = true;
        presenter = new ChatPresenter(this);
        locationsMap = new SimpleLocation(this);
        mDb = AppDatabase.getInstance(getApplicationContext());
        permission.checkAndRequestPermissions(ChatActivity.this);

        chatLoadReq = new ChatLoadReq();

        if (!locationsMap.hasLocationEnabled()) {
            final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
            View sheetView = getLayoutInflater().inflate(R.layout.bottom_sheet_noaktif_gps, null);
            bottomSheetDialog.setCanceledOnTouchOutside(false);
            btn_batal = sheetView.findViewById(R.id.button_batal);
            btn_pengaturan = sheetView.findViewById(R.id.button_pengaturan);
            closeNoAktifGS = sheetView.findViewById(R.id.image_no_aktifgps_dismiss);
            btn_pengaturan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SimpleLocation.openSettings(ChatActivity.this);
                    bottomSheetDialog.dismiss();
                }
            });
            btn_batal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bottomSheetDialog.dismiss();
                }
            });
            closeNoAktifGS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bottomSheetDialog.dismiss();
                }
            });
            bottomSheetDialog.setContentView(sheetView);
            bottomSheetDialog.show();
        }

        llLoadChatList = findViewById(R.id.ll_load_chat_list);
        llFailedLoadChatList = findViewById(R.id.ll_failed_load_chat_list);

        containLoadListChat();

        btnErrorLoad = (Button) findViewById(R.id.btn_error_load) ;
        btnErrorLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llLoadChatList.setVisibility(View.VISIBLE);
                llFailedLoadChatList.setVisibility(View.GONE);
                llFailedLoadChatList.setVisibility(View.GONE);
                containLoadListChat();
            }
        });

        rvChatList = (RecyclerView) findViewById(R.id.rv_chat);
        rvQuickButton = (RecyclerView) findViewById(R.id.rv_chat_quick);
        layout_chatbox2 = (ConstraintLayout) findViewById(R.id.layout_chatbox);

        rvQuickButton.setVisibility(View.GONE);


        speech = SpeechRecognizer.createSpeechRecognizer(ChatActivity.this);
        speech.setRecognitionListener(this);
        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        SpeakToTextUtils.setIntentSpeakToText(recognizerIntent, ChatActivity.this);

        ttsUtils = new TtsUtils(ChatActivity.this, new Locale("id", "ID"), "com.google.android.tts");

        is_gps_active = true;
        is_network_active = true;
        is_gps_enabled = false;
        is_network_enabled = false;
        locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, LOCATION_PERMS, LOCATION_REQUEST);
            Log.d(tag, "Permission ACCESS_FINE_LOCATION Has Been Granted");
        } else {
            locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000L, 500.0f, locationListener);
            location = locManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
        etSendMessage.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int keyCode, KeyEvent keyevent) {
                //If the keyevent is a key-down event on the "enter" button
                if ((keyevent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    inputChat(etSendMessage.getText().toString());
                    InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(etSendMessage.getWindowToken(),
                            InputMethodManager.RESULT_UNCHANGED_SHOWN);
                    etSendMessage.setText("");
                    return true;
                }
                return false;
            }
        });

        this.chatActivity = new ChatActivity();

        presenter.attachView(this);

        this.chatAdapter = new ChatAdapter(this, presenter.getChatObjects(), this, outputAction);

        rvChatList.setAdapter(chatAdapter);
        rvChatList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvChatList.setItemAnimator(new DefaultItemAnimator());
        getLonLat();
//        AppExecutors.getInstance().diskIO().execute(new Runnable() {
//            @Override
//            public void run() {
//                List<ChatResponseEntity> chatResponseEntities;
//                chatResponseEntities = mDb.chatResponseDao().getAll();
//                Log.d("message.getStringExtra", new Gson().toJson(chatResponseEntities));
//                ChatActivity.this.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        for (int i = 0; i < chatResponseEntities.size(); i++){
//                            presenter.loadChatHistory(chatResponseEntities.get(i).getChatHistory());
//                        }
//                    }
//                });
//            }
//        });
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.e(tag, "onReceive");
                String message = intent.getStringExtra("message");
                presenter.onLiveChat(message);
                LocalBroadcastManager.getInstance(ChatActivity.this).unregisterReceiver(mRegistrationBroadcastReceiver);
                LocalBroadcastManager.getInstance(ChatActivity.this).registerReceiver(mRegistrationBroadcastReceiver,
                        new IntentFilter(Constant.PUSH_NOTIFICATION));
            }
        };


//        firtsMessage(Constant.GMESSAGE);
    }

    public void containLoadListChat() {
        chatLoadReq.setUserId(Prefs.getString("USER_ID",""));
        ChatLoadReq inChatLoadReq = new ChatLoadReq();
        inChatLoadReq.setUserId(chatLoadReq.getUserId());

        loadListChat(inChatLoadReq);
    }

    @Keep
    private void loadListChat(ChatLoadReq chatLoadReq) {

        presenter.removeAllItem();

        ApiService service = ApiBuilder.getClient().create(ApiService.class);
//        Call<ChatResp> call = service.submitChat("Bearer " + Prefs.getString("TOKEN",""), chatReq,Constant.BOT_ID);
//        Call<List<Chat>> call = service.getChatList(chatLoadReq, Constant.BOT_ID, "1");

        Call<ArrayList<ChatResp>> call = service.getChatList(chatLoadReq, Constant.BOT_ID, String.valueOf(page), "1000");
        call.enqueue(new Callback<ArrayList<ChatResp>>() {
            @Override
            public void onResponse(Call<ArrayList<ChatResp>> call, Response<ArrayList<ChatResp>> response) {

                if (response.isSuccessful()) {
                    llLoadChatList.setVisibility(View.GONE);
                    llFailedLoadChatList.setVisibility(View.GONE);
                    List<ChatResp> list = response.body();

                    if (list.size() > 0) {
                        try {
                            Collections.reverse(list);
                            list.remove(0);
                            Gson gson = new Gson();
                            Log.d("List_chat", gson.toJson(list));
                            if (list.size() != 0) {
                                for (int i = 0; i < list.size(); i++) {
                                    presenter.loadChatHistory(new Gson().toJson(list.get(i)));
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        firtsMessage(Constant.GMESSAGE);
                    }
                } else {
                    llLoadChatList.setVisibility(View.GONE);
                    llFailedLoadChatList.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onFailure(Call<ArrayList<ChatResp>> call, Throwable t) {
                llLoadChatList.setVisibility(View.GONE);
                llFailedLoadChatList.setVisibility(View.VISIBLE);
            }
        });
    }

    @Keep
    private  void firtsMessage(String message){
        if (!message.equals("")){

            try {
                final ChatReq req = new ChatReq(Prefs.getString("USER_ID",""),message,String.valueOf(Constant.LAT),String.valueOf(Constant.LON),"android");
                req.setUserId(AesCipher.encrypt(Constant.APP_KEY, Prefs.getString("USER_ID","")));
                req.setQuery(AesCipher.encrypt(Constant.APP_KEY,message));
                req.setLat(AesCipher.encrypt(Constant.APP_KEY,String.valueOf(latitude)));
                req.setLon(AesCipher.encrypt(Constant.APP_KEY,String.valueOf(longitude)));
                req.setChannel(AesCipher.encrypt(Constant.APP_KEY,"android"));

//                AppExecutors.getInstance().diskIO().execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        ChatResponseEntity chatResponseEntity = new ChatResponseEntity();
//                        chatResponseEntity.setChatHistory(message);
//                        mDb.chatResponseDao().insertAll(chatResponseEntity);
//                    }
//                });
//                presenter.onEditTextActionDone(req.getQuery()); //viky
                statusLoading = 1;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        presenter.requestDataFromServer(req);
                        statusLoading = 0;
                    }
                }, 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Keep
    @Override
    public void mainCours(String text) {
        if (!text.equals("")){
            try {
                final ChatReq req = new ChatReq(Prefs.getString("USER_ID",""),text,String.valueOf(Constant.LAT),String.valueOf(Constant.LON),"android");
                req.setUserId(AesCipher.encrypt(Constant.APP_KEY,Prefs.getString("USER_ID","")));
                req.setQuery(AesCipher.encrypt(Constant.APP_KEY,text));
                req.setLat(AesCipher.encrypt(Constant.APP_KEY,String.valueOf(latitude)));
                req.setLon(AesCipher.encrypt(Constant.APP_KEY,String.valueOf(longitude)));
                req.setChannel(AesCipher.encrypt(Constant.APP_KEY,"android"));
//                AppExecutors.getInstance().diskIO().execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        ChatResponseEntity chatResponseEntity = new ChatResponseEntity();
//                        chatResponseEntity.setChatHistory(text);
//                        mDb.chatResponseDao().insertAll(chatResponseEntity);
//                    }
//                });
//            insertToDatabase(req.getQuery());
//             presenter.onEditTextActionDone(req.getQuery()); //viky
                statusLoading = 1;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        presenter.requestDataFromServer(req);
                        statusLoading = 0;
                    }
                }, 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Keep
    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = ChatActivity.this.getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
        }
        return false;
    }

    @Keep
    private void getLonLat() {
//        latitude = -6.175801;
//        longitude = 106.879632;
        latitude = locationsMap.getLatitude();
        longitude = locationsMap.getLongitude();
        Constant.LAT = latitude;
        Constant.LON = longitude;
    }

    @Keep
    private void updateWithNewLocation(Location location) {
        if (location != null) {
            lat = location.getLatitude();
            lng = location.getLongitude();
            acc = location.getAccuracy();
            alt = location.getAltitude();
        }
    }

    @Keep
    private final LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            updateWithNewLocation(location);
        }
        public void onProviderDisabled(String provider) {
            updateWithNewLocation(null);
        }
        public void onProviderEnabled(String provider) {}
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    };

    @Keep
    public void createAlarm(String message, int hour, int minutes) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                .putExtra(AlarmClock.EXTRA_MINUTES, minutes);
        if (intent.resolveActivity(ChatActivity.this.getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @Keep
    public void addEvent(String title, long begin, long endin) {

        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setType("vnd.android.cursor.item/event")
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.Events.TITLE, title)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, begin)
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endin);
        if (intent.resolveActivity(ChatActivity.this.getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public static String getMonthName(int month) {
        String[] monthNames = {"Jan", "Feb", "Mar", "Apr", "Mei", "Jun", "Jul", "Agt", "Sept", "Oct", "Nov", "Dec"};
        return monthNames[month];
    }

    public static String getMonthNameKepulangan(int month) {
        String[] monthNamesKepulangan = {"Jan", "Feb", "Mar", "Apr", "Mei", "Jun", "Jul", "Agt", "Sept", "Oct", "Nov", "Dec"};
        return monthNamesKepulangan[month];
    }

    private void initSpinnerAsalBandara(final ArrayList<Airport> listFormAsalBandara) {
        final List<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("Pilih Bandara Asal");
        for (int i = 0; i < listFormAsalBandara.size(); i++) {
            spinnerArray.add(listFormAsalBandara.get(i).getLabel());
        }
        SpinnerAdapter spinnerAdapter = new ArrayAdapter<>(ChatActivity.this, R.layout.s_spinner, spinnerArray);
        spinnerAsalPesawat.setAdapter(spinnerAdapter);
        spinnerAsalPesawat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (!spinnerAsalPesawat.getSelectedItem().toString().equals("Pilih Bandara Asal")) {
                    listFormAsalBandara.get(position).getLabel();
                    asalBandara = listFormAsalBandara.get(position - 1).getLabel();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });


    }

    @Keep
    public void showInputMethod() {
        InputMethodManager imm = (InputMethodManager) ChatActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    @Keep
    private void setEditTextAttributes(EditText editText) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(convertDpToPixel(16),
                convertDpToPixel(16),
                convertDpToPixel(16),
                0);
        editText.setLayoutParams(params);
    }

    @Keep
    private int convertDpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return Math.round(px);
    }

    @Keep
    public void installTTSApplication() {
        Intent marketIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + "com.google.android.tts"));
        marketIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_MULTIPLE_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(marketIntent);
    }

    // change click listerner
    @Keep
    private View.OnClickListener micClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (isTouch) {
                isTouch = false;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ivActionMic.setImageDrawable(getResources().getDrawable(R.drawable.ic_bt_mic_blue_listening, getApplicationContext().getTheme()));
                } else {
                    ivActionMic.setImageDrawable(getResources().getDrawable(R.drawable.ic_bt_mic_blue_listening));
                }
                speech.startListening(recognizerIntent);
            } else {
                isTouch = true;
                speech.stopListening();
            }
        }
    };

    private View.OnClickListener imgEnterClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!TextUtils.isEmpty(etSendMessage.getText()) && !etSendMessage.getText().toString().trim().isEmpty()) {
                inputChat(etSendMessage.getText().toString());
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(etSendMessage.getWindowToken(),
                        InputMethodManager.RESULT_UNCHANGED_SHOWN);
                etSendMessage.setText("");
            }
        }
    };


    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "ON RESUME");
        clickBottomNavigationChat = true;
        boolean isAppInstalled = appInstalledOrNot("com.google.android.tts");
        if (isAppInstalled) {
            if (!ttsUtils.getDefaultEngineTts().equalsIgnoreCase("com.google.android.tts")) {
//                showWarning("3", "Warning", "warning2", "Silahkan Switch Setting ke Google Text to Speech", "", "Batal", "Ok");
                ttsUtils = new TtsUtils(ChatActivity.this, new Locale("id", "ID"), "com.google.android.tts");
            }
        } else {
            installTTSApplication();
        }

        if (isOnPause) {
            containLoadListChat();
        }

        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        if (Constant.IS_CHAT_LENNA_ACTIVE) {
            LocalBroadcastManager.getInstance(ChatActivity.this).registerReceiver(mRegistrationBroadcastReceiver,
                    new IntentFilter(Constant.PUSH_NOTIFICATION));
        }

    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "ON DESTROY");
        if (ttsUtils != null && ttsUtils.isSpeaking()) {
            ttsUtils.stop();
        }
        if (ttsUtils != null) {
            ttsUtils.shutdown();
        }
        ttsUtils = null;
        super.onDestroy();
    }

    @Override
    public void onLocationChanged(Location location) {}

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}

    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onProviderDisabled(String provider) {}

    @Override
    public void onReadyForSpeech(Bundle bundle) {
        Log.i(TAG, "onReadyForSpeech");
        speakOut("");
    }

    @Override
    public void onBeginningOfSpeech() {
        Log.i(TAG, "onBeginningOfSpeech");
    }

    @Override
    public void onRmsChanged(float v) {
        Log.i(TAG, "onRmsChanged: " + v);
    }

    @Override
    public void onBufferReceived(byte[] bytes) {
        Log.i(TAG, "onBufferReceived: " + bytes);
    }

    @Override
    public void onEndOfSpeech() {
        Log.i(TAG, "onEndOfSpeech");
    }

    @Override
    public void onError(int error) {
        String errorMessage = SpeakToTextUtils.getErrorText(error);
        Log.d(TAG, "FAILED " + errorMessage);
        if (errorMessage.equals(SpeechRecognizer.ERROR_RECOGNIZER_BUSY)) {
            isTouch = false;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ivActionMic.setImageDrawable(getResources().getDrawable(R.drawable.ic_bt_mic_blue_active, getApplicationContext().getTheme()));
            etSendMessage.setText("");
        } else {
            ivActionMic.setImageDrawable(getResources().getDrawable(R.drawable.ic_bt_mic_blue_active));
            etSendMessage.setText("");
        }
    }

    @Override
    public void onResults(Bundle results) {
        Log.i(TAG, "onResults");
        isTouch = true;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (!TextUtils.isEmpty(etSendMessage.getText())) {
                inputChat(etSendMessage.getText().toString());
                etSendMessage.setText("");
            }
            ivActionMic.setImageDrawable(getResources().getDrawable(R.drawable.ic_bt_mic_blue_active, getApplicationContext().getTheme()));
        } else {
            if (!TextUtils.isEmpty(etSendMessage.getText())) {
                inputChat(etSendMessage.getText().toString());
                etSendMessage.setText("");
            }
            ivActionMic.setImageDrawable(getResources().getDrawable(R.drawable.ic_bt_mic_blue_active));
        }
    }

    @Override
    public void onPartialResults(Bundle partialResults) {
        Log.i(TAG, "onPartialResults");
        ArrayList<String> matches = partialResults
                .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        StringBuilder text = new StringBuilder();
        for (String result : matches)
            text.append(result);
        etSendMessage.setText(text.toString());
    }

    @Override
    public void onEvent(int eventType, Bundle params) {
        Log.i(TAG, "onEvent");
    }

    @Override
    public void notifyAdapterObjectAdded(int position) {
        this.chatAdapter.notifyItemInserted(position);
        scrollChatDown();
    }

    @Override
    protected void onPause() {
        super.onPause();
        isOnPause = true;
    }

    @Override
    public void scrollChatDown() {
        this.rvChatList.scrollToPosition(presenter.getChatObjects().size() - 1);
    }

    @Override
    public void notifyAdapterObjectRemove(int position) {
        this.chatAdapter.notifyItemRemoved(position);
    }

    @Override
    public void notifyAdapterRemove(int pos, int size) {
        this.chatAdapter.notifyItemRangeRemoved(0, size);
    }

    @Override
    public void speakOut(String speakText) {
        if (ttsUtils != null) {
            ttsUtils.speak(speakText);
        }
    }

    @Keep
    @Override
    public void inputChat(String text) {
        rvQuickButton.setVisibility(View.GONE);
        String user_id = Prefs.getString("USER_ID","");
        Log.d("D/OK", "ini user id "+ Prefs.getString("USER_ID",""));

        if (!TextUtils.isEmpty(text)) {
            if (statusLoading == 1) {
                presenter.removeItem();
            }
            try {
                ChatReq req = new ChatReq(user_id,text,String.valueOf(Constant.LAT),String.valueOf(Constant.LON),"android");
                req.setUserId(AesCipher.encrypt(Constant.APP_KEY,user_id));
                req.setQuery(AesCipher.encrypt(Constant.APP_KEY,text));
                req.setLat(AesCipher.encrypt(Constant.APP_KEY,String.valueOf(Constant.LAT)));
                req.setLon(AesCipher.encrypt(Constant.APP_KEY,String.valueOf(Constant.LON)));
                req.setChannel(AesCipher.encrypt(Constant.APP_KEY,"android"));
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        ChatResponseEntity chatResponseEntity = new ChatResponseEntity();
                        chatResponseEntity.setChatHistory(text);
                        mDb.chatResponseDao().insertAll(chatResponseEntity);
                    }
                });
                presenter.onEditTextActionDone(text, "", "");
                String a = req.getUserId();
                statusLoading = 1;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        presenter.requestDataFromServer(req);
                        statusLoading = 0;
                    }
                }, 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Keep
    @Override
    public void inputChatZakat(String text) {
        rvQuickButton.setVisibility(View.GONE);
        if (!TextUtils.isEmpty(text)) {
            if (statusLoading == 1) {
                presenter.removeItem();
            }
            try {
                final ChatReq req = new ChatReq(Prefs.getString("USER_ID",""),text,String.valueOf(Constant.LAT),String.valueOf(Constant.LON),"android");
                req.setUserId(AesCipher.encrypt(Constant.APP_KEY,Prefs.getString("USER_ID","")));
                req.setQuery(AesCipher.encrypt(Constant.APP_KEY,text));
                req.setLat(AesCipher.encrypt(Constant.APP_KEY,String.valueOf(latitude)));
                req.setLon(AesCipher.encrypt(Constant.APP_KEY,String.valueOf(longitude)));
                req.setChannel(AesCipher.encrypt(Constant.APP_KEY,"android"));
//              insertToDatabase(req.getQuery());
                presenter.removeItem();
                statusLoading = 1;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        presenter.requestDataFromServer(req);
                        statusLoading = 0;
                    }
                }, 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Keep
    @Override
    public void setTextChat(String textChat) {
        showInputMethod();
        etSendMessage.setText(textChat);
        etSendMessage.setSelection(etSendMessage.getText().length());
    }

    @Keep
    @Override
    public void showErrorMessage() {
        Toast.makeText(ChatActivity.this, R.string.communication_error, Toast.LENGTH_SHORT).show();
    }

    @Keep
    @Override
    public void showBottomSheetDialog(ChatOutputForm chatOutputForm) {
        BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(ChatActivity.this);
        View sheetView = ChatActivity.this.getLayoutInflater().inflate(R.layout.bottomsheet_dialog, null);
        LinearLayout linearLayout = sheetView.findViewById(R.id.linear_layout);
        ArrayList<ChatColumnForm> columnForms = chatOutputForm.getColumns();
        llForm = new LinearLayout(ChatActivity.this);
        llForm.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(llForm);
        for (int i = 0; i < columnForms.size(); i++) {
            switch (columnForms.get(i).getType()) {
                case INPUT:
                    EditText editText = new EditText(ChatActivity.this);
                    editText.setHint(columnForms.get(i).getLabel());
                    editText.setBackground(getResources().getDrawable(R.drawable.background_edittext_login));
                    editText.setHintTextColor(getResources().getColor(R.color.manatee));
                    if (columnForms.get(i).getSubType().equals("password")) {
                        editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                    editText.setPadding(16, 16, 16, 16);
                    setEditTextAttributes(editText);
                    llForm.addView(editText);
                    break;
            }
        }
        mBottomSheetDialog.setContentView(sheetView);
        mBottomSheetDialog.show();
    }

    @Override
    public void showBottomSheetTravelDialog(ChatOutputFormTravel chatOutputFormTravel) {}

    @Override
    public void showBottomSheetDonasiiDialog(ChatOutputDonasi chatOutputDonasi) {

    }

    @Override
    public void showBottomSheetTrainPassengerForm(ChatOutputTrainPassengerForm chatOutputTrainPassengerForm) {

    }

    @Override
    public void showBottomSheetTrainTripDetailrForm(ChatOutputTrainTripDetailForm chatOutputTrainTripDetailForm) {

    }

    @Override
    public void showBottomSheetPlaneTripDetailrForm(ChatOutputPlaneTripDetailForm chatOutputPlaneTripDetailForm) {

    }

    @Override
    public void showBottomSheetPlanePassengerForm(ChatOutputPlanePassengerForm chatOutputPlanePassengerForm) {

    }

    @Override
    public void showBottomSheetDatePickerForm(ChatOutputDatepickerForm chatOutputDatepickerForm) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(ChatActivity.this, R.style.AppBottomSheetDialogTheme);

        View sheetView = getLayoutInflater().inflate(R.layout.bottomsheet_datepicker, null);

        picker = sheetView.findViewById(R.id.datePicker);

        btnOkDate = sheetView.findViewById(R.id.btn_ok);
        imageViewClose = sheetView.findViewById(R.id.imageViewClose);

        imageViewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputChat("batal");
                bottomSheetDialog.dismiss();
            }
        });

        btnOkDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tanggal = String.valueOf(picker.getDayOfMonth());
                String bulan = String.valueOf(picker.getDayOfMonth() + 1);
                String tahun = String.valueOf(picker.getYear());

                String hasil = tanggal + "/" + bulan + "/" + tahun;
                inputChat(hasil);
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.setContentView(sheetView);
        bottomSheetDialog.show();
    }

    @Override
    public void showBottomSheetMultipleDatePicker(ChatOutputMultiDatePicker chatOutputMultiDatePicker) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(ChatActivity.this, R.style.AppBottomSheetDialogTheme);

        View sheetView = getLayoutInflater().inflate(R.layout.bottomsheet_multi_datepicker, null);

        pickerNow = sheetView.findViewById(R.id.datePickerNow);
        pickerTomorrows = sheetView.findViewById(R.id.datePickerTomorrow);

        btnOkMultiDate = sheetView.findViewById(R.id.btn_ok_multi_date);
        imageViewCloseMultiDate = sheetView.findViewById(R.id.imageViewClose);

        imageViewCloseMultiDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputChat("batal");
                bottomSheetDialog.dismiss();
            }
        });

        btnOkMultiDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tanggalNow = String.valueOf(pickerNow.getDayOfMonth());
                String bulanNow = String.valueOf(pickerNow.getDayOfMonth() + 1);
                String tahunNow = String.valueOf(pickerNow.getYear());

                String tanggalTomorrow = String.valueOf(pickerTomorrows.getDayOfMonth());
                String bulanTomorrow = String.valueOf(pickerTomorrows.getDayOfMonth() + 1);
                String tahunTomorrow = String.valueOf(pickerTomorrows.getYear());

                String hasil = tanggalNow + "/" + bulanNow + "/" + tahunNow + " Sampai " + tanggalTomorrow + "/" + bulanTomorrow + "/" + tahunTomorrow;
                inputChat(hasil);
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.setContentView(sheetView);
        bottomSheetDialog.show();
    }

    @Keep
    @Override
    public void actionOpenApp(ChatOutputAction chatOutputAction, String subType) {
        switch (subType) {
            case "openApp":
                ChatDataAction chatDataAction = chatOutputAction.getData();
                String packageName = chatDataAction.getPackageName();
                boolean isAppInstalled = appInstalledOrNot(packageName);
                if (isAppInstalled) {
                    Intent LaunchIntent = Objects.requireNonNull(ChatActivity.this).getPackageManager()
                            .getLaunchIntentForPackage(packageName);
                    if (LaunchIntent != null){
                        startActivity(LaunchIntent);
                    } else {
                        DialogUtils.showDialogAlertNoActionNew(ChatActivity.this, "Perhatian..", "Aplikasi tidak terinstall di Smartphone Kamu");
                    }
                } else {
                    DialogUtils.showDialogAlertNoActionNew(ChatActivity.this, "Perhatian..", "Aplikasi tidak terinstall di Smartphone Kamu");
                }
                break;
            case "makeCall":
                Intent intentCall = new Intent(Intent.ACTION_DIAL);
                intentCall.setData(Uri.parse("tel:" + chatOutputAction.getData().getPhoneNumber()));
                if (intentCall.resolveActivity(ChatActivity.this.getPackageManager()) != null) {
                    startActivity(intentCall);
                }
                break;
            case "composeSMS":
                Intent intentSms = new Intent(Intent.ACTION_SENDTO);
                intentSms.setData(Uri.parse("smsto:" + chatOutputAction.getData().getRecipient()));  // This ensures only SMS apps respond
                intentSms.putExtra("sms_body", chatOutputAction.getData().getMessage());
                if (intentSms.resolveActivity(ChatActivity.this.getPackageManager()) != null) {
                    startActivity(intentSms);
                }
                break;
            case "composeMail":
                String mailto = "mailto:" + chatOutputAction.getData().getEmailAddress() +
                        "?cc=" + chatOutputAction.getData().getCc() +
                        "&subject=" + Uri.encode(chatOutputAction.getData().getSubject()) +
                        "&body=" + Uri.encode(chatOutputAction.getData().getMessage());
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse(mailto));
                try {
                    startActivity(emailIntent);
                } catch (ActivityNotFoundException e) {
                    //TODO: Handle case where no email app is available
                    DialogUtils.showDialogAlertNoActionNew(ChatActivity.this, "Perhatian..", "Kamu tidak mempunyai aplikasi pengiriman pesan elektronik");
                }
                break;
            case "setAlarm":
                String alrmMessage = chatOutputAction.getData().getAlarmMessage();
                int hour = Integer.parseInt(chatOutputAction.getData().getHours());
                int minutes = Integer.parseInt(chatOutputAction.getData().getMinutes());
                createAlarm(alrmMessage, hour, minutes);
                break;
            case "setSchedule":
                Calendar cal = Calendar.getInstance();
                long startTime = cal.getTimeInMillis();
                long endTime = cal.getTimeInMillis() + 60 * 60 * 1000;
                addEvent(chatOutputAction.getData().getReminderTitle(), startTime, endTime);
                break;
        }
    }

    @Keep
    @Override
    public void quickButton(ChatResultBean chatResultBean) {
        if (chatResultBean.getQuickbutton() == null || chatResultBean.getQuickbutton().size() == 0) {
            rvQuickButton.setVisibility(View.GONE);
        } else {
            rvQuickButton.setVisibility(View.VISIBLE);
            this.quickButtonAdapter = new QuickButtonAdapter(ChatActivity.this, chatResultBean.getQuickbutton(), new QuickButtonCallBack() {
                @Override
                public void onRowClick(String textChat) {
                    inputChat(textChat);
                }
            });
            rvQuickButton.setLayoutManager(new LinearLayoutManager(ChatActivity.this, LinearLayoutManager.HORIZONTAL, false));
            rvQuickButton.setAdapter(this.quickButtonAdapter);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Constant.IS_CHAT_LENNA_ACTIVE = false;
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public void onCarouselClick(String buttonName, String payload) {
    }

    public void setImageLogo(int resource){
        img_icon_chat.setImageResource(resource);
    }
}
