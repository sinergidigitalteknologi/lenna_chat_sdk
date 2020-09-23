package ai.lenna.lennachatmodul.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
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
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
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
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.karan.churi.PermissionManager.PermissionManager;
import com.pixplicity.easyprefs.library.Prefs;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import ai.lenna.lennachatmodul.R;
import ai.lenna.lennachatmodul.chat.adapter.ChatAdapter;
import ai.lenna.lennachatmodul.chat.adapter.QuickButtonAdapter;
import ai.lenna.lennachatmodul.chat.adapter.QuickButtonCallBack;
import ai.lenna.lennachatmodul.chat.airport.Airport;
import ai.lenna.lennachatmodul.chat.model.ChatReq;
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
import ai.lenna.lennachatmodul.chat.model.station.ChatStationTrainTripDetailForm;
import ai.lenna.lennachatmodul.chat.modelzakat.ZakatDonasi;
import ai.lenna.lennachatmodul.room.AppDatabase;
import ai.lenna.lennachatmodul.room.AppExecutors;
import ai.lenna.lennachatmodul.room.entity.ChatResponseEntity;
import ai.lenna.lennachatmodul.util.Constant;
import ai.lenna.lennachatmodul.util.DialogUtils;
import ai.lenna.lennachatmodul.util.ShowAllert;
import ai.lenna.lennachatmodul.util.SpeakToTextUtils;
import ai.lenna.lennachatmodul.util.TtsUtils;
import im.delight.android.location.SimpleLocation;

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
    Button btn_batal, btn_pengaturan;
    ConstraintLayout layout_chatbox2;
    private QuickButtonAdapter quickButtonAdapter;
    private RecyclerView rvChatList, rvQuickButton;



    //zakat maal
    EditText etUangUE, etEmasUE, etUangU, etEmasE;


    //Penumpang pesawat
    Button prosesPenumpangPesawat;
    ImageView imageCloseFormPlane;
    ImageView imageViewCloseDetilPlane;
    TextView tvTuanPlane, tvNyonyaPlane;

    Spinner spinnerPassportCountrey, spinnerKewarganegaraan;
    DatePickerDialog.OnDateSetListener mDataSetListenerTglLahirPenumpang;
    EditText et_plane_passenger_name, et_date_plane_passenger, et_identity_number, et_no_passpord, et_dateExpired;


    //detil form detil trip pesawat
    Button btnCariTiketPesawat;
    TextView tvPerjalananPesawat, tvPulangPergiPesawat;
    LinearLayout llKepulanganPesawat, llKeberangkatanPesawat;
    EditText etKeberangkatan_pesawat, etKepulangan_pesawat;
    Spinner spinnerAsalPesawat, spinnerTujuanPesawat, spinnerDewasaPesawat, spinnerAnakPesawat, spinnerBayiPesawat;


    ImageView imageViewCloseDetilPesawat;
    DatePickerDialog.OnDateSetListener mDataSetListenerKepulanganPesawat;
    DatePickerDialog.OnDateSetListener mDataSetListenerKeberangkatanPesawat;


    ImageView imgCloseProfesi;
    ImageView image_view_close;
    Button btnKirim, btnBatal, btnKirimDataMuzaki;
    EditText etJumlahDonasi, etNamaDonasi, etEmailDonasi, etNoKtp;
    EditText etPenghasilanPerbulan, etProfesiNik, etProfesiNama, etProfesiEmail;


    //form detil penumpang kereta
    TextView tvTuan, tvNyonya;
    Button tambahPenumpang, prosesPenumpang;
    ImageView imageCloseFormKereta, imageViewCloseDetil;
    EditText etNamaPemesan, etEmailPemesan, etNoHpPemesan;
    String tglLahirPenumpang, tglLahirPenumpangPesawat, tglExpired;
    EditText etNamaPenumpang, etTglLahirPenumpang, etNoKtpPenumpang, etNoTelpPenumpang;
    DatePickerDialog.OnDateSetListener mDataSetListenerTglLahirPenumpangPesawat, mDataSetListenerDateExpired;


    //detil form penumpang kereta
    Button btnCariTiketKereta;
    TextView tvPerjalanan, tvPulangPergi;
    LinearLayout llKepulangan, llKeberangkatan;
    DatePickerDialog.OnDateSetListener mDataSetListener;
    EditText etKeberangkatan_perjkereta, etKepulangan_perjkereta;
    DatePickerDialog.OnDateSetListener mDataSetListenerKepulangan;Spinner spLokasiPerjKereta, spTujuanPerjKereta, spLokasiDewasaPerjkereta, spLokasiAnakPerjkereta, spLokasiBayiPerjkereta;

    String chooseTitel;
    String dewasa, anak, bayi;
    private String current = "";
    String choose = "Uang & Emas";
    private int statusLoading = 0;
    String choosePlaneTitle = "Tuan";
    String negara, stringPasspordCountrey;
    boolean clickBottomNavigationChat = true;
    String choosePerjalanan = "1x Perjalanan";
    String tglPulangPesawat, tglBerangkatPesawat;
    String dewasaPesawat, anakPesawat, bayiPesawat;
    String choosePerjalananPesawat = "1x Perjalanan";
    String asalStasiun, tujuanStasiun, tglBerangkat, tglPulang;
    String asalBandara, tujuanBandara, tglBerangkatPesawatVar, tglPulangPesawatVar;

    ImageView ivActionMic,img_icon_chat;
    ImageView ivImageViewEnter;
    EditText etSendMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ivActionMic = findViewById(R.id.action_mic);
        ivImageViewEnter = findViewById(R.id.imageViewEnter);
        etSendMessage = findViewById(R.id.et_send_message);
        img_icon_chat = findViewById(R.id.img_icon_chat);
        // click listener
        ivActionMic.setOnClickListener(micClicked);
        ivImageViewEnter.setOnClickListener(imgEnterClicked);
        //set logo title
        img_icon_chat.setImageResource(LOGO_TITLE);
        activity = this.activity;
        showAllert = new ShowAllert();
        permission = new PermissionManager() {};
        clickBottomNavigationChat = true;
        presenter = new ChatPresenter(this);
        locationsMap = new SimpleLocation(this);
        mDb = AppDatabase.getInstance(getApplicationContext());
        permission.checkAndRequestPermissions(ChatActivity.this);

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
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                List<ChatResponseEntity> chatResponseEntities;
                chatResponseEntities = mDb.chatResponseDao().getAll();
                ChatActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < chatResponseEntities.size(); i++){
                            presenter.loadChatHistory(chatResponseEntities.get(i).getChatHistory());
                        }
                    }
                });
            }
        });
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.e(tag, "onReceive");
                String message = intent.getStringExtra("message");
                presenter.onLiveChat(message);
            }
        };
        firtsMessage(Constant.GMESSAGE);
    }

    private  void firtsMessage(String message){
        if (!message.equals("")){
            final ChatReq req = new ChatReq();
            req.setUserId(Prefs.getString("USER_ID",""));
            req.setQuery(message);
            req.setLat(String.valueOf(latitude));
            req.setLon(String.valueOf(longitude));
            req.setChannel("android");
            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    ChatResponseEntity chatResponseEntity = new ChatResponseEntity();
                    chatResponseEntity.setChatHistory(req.getQuery());
                    mDb.chatResponseDao().insertAll(chatResponseEntity);
                }
            });
//            insertToDatabase(req.getQuery());
            presenter.onEditTextActionDone(req.getQuery());
            statusLoading = 1;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    presenter.requestDataFromServer(req);
                    statusLoading = 0;
                }
            }, 1000);
        }

    }

    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = ChatActivity.this.getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
        }
        return false;
    }

    private void getLonLat() {
        latitude = locationsMap.getLatitude();
        longitude = locationsMap.getLongitude();
    }

    private void updateWithNewLocation(Location location) {
        if (location != null) {
            lat = location.getLatitude();
            lng = location.getLongitude();
            acc = location.getAccuracy();
            alt = location.getAltitude();
        }
    }

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

    public void createAlarm(String message, int hour, int minutes) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                .putExtra(AlarmClock.EXTRA_MINUTES, minutes);
        if (intent.resolveActivity(ChatActivity.this.getPackageManager()) != null) {
            startActivity(intent);
        }
    }

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

    private void initSpinnerTujuanBandara(final ArrayList<Airport> listFormTujuanBandara) {
        final List<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("Pilih Bandara Tujuan");
        for (int i = 0; i < listFormTujuanBandara.size(); i++) {
            spinnerArray.add(listFormTujuanBandara.get(i).getLabel());
        }
        SpinnerAdapter spinnerAdapter = new ArrayAdapter<>(ChatActivity.this, R.layout.s_spinner, spinnerArray);
        spinnerTujuanPesawat.setAdapter(spinnerAdapter);
        spinnerTujuanPesawat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!spinnerTujuanPesawat.getSelectedItem().toString().equals("Pilih Bandara Tujuan")) {
                    listFormTujuanBandara.get(position).getLabel();
                    tujuanBandara = listFormTujuanBandara.get(position - 1).getLabel();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

    }

    private void initSpinnerAsalStasiun(final ArrayList<ChatStationTrainTripDetailForm> listFormAsalStasiun) {
        final List<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("Pilih Stasiun Asal");
        for (int i = 0; i < listFormAsalStasiun.size(); i++) {
            spinnerArray.add(listFormAsalStasiun.get(i).getLabel());
        }
        SpinnerAdapter spinnerAdapter = new ArrayAdapter<>(ChatActivity.this, R.layout.s_spinner, spinnerArray);
        spLokasiPerjKereta.setAdapter(spinnerAdapter);
        spLokasiPerjKereta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (!spLokasiPerjKereta.getSelectedItem().toString().equals("Pilih Stasiun Asal")) {
                    listFormAsalStasiun.get(position).getLabel();
                    asalStasiun = listFormAsalStasiun.get(position - 1).getLabel();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });


    }

    private void initSpinnerTujuanStasiun(final ArrayList<ChatStationTrainTripDetailForm> listFormTujuanStasiun) {
        final List<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("Pilih Stasiun Tujuan");
        for (int i = 0; i < listFormTujuanStasiun.size(); i++) {
            spinnerArray.add(listFormTujuanStasiun.get(i).getLabel());
        }
        SpinnerAdapter spinnerAdapter = new ArrayAdapter<>(ChatActivity.this, R.layout.s_spinner, spinnerArray);
        spTujuanPerjKereta.setAdapter(spinnerAdapter);
        spTujuanPerjKereta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!spTujuanPerjKereta.getSelectedItem().toString().equals("Pilih Stasiun Tujuan")) {
                    listFormTujuanStasiun.get(position).getLabel();
                    tujuanStasiun = listFormTujuanStasiun.get(position - 1).getLabel();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

    }

    public class TextWatcherDonasi implements TextWatcher {
        private View view;
        private TextWatcherDonasi(View view) {
            this.view = view;
        }
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
        public void afterTextChanged(Editable editable) {
            etJumlahDonasi.removeTextChangedListener(this);
            try {
                String originalString = editable.toString();
                Long longval;
                if (originalString.contains(",")) {
                    originalString = originalString.replaceAll(",", "");
                }
                longval = Long.parseLong(originalString);
                DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                formatter.applyPattern("#,###,###,###");
                String formattedString = formatter.format(longval);
                etJumlahDonasi.setText(formattedString);
                etJumlahDonasi.setSelection(etJumlahDonasi.getText().length());
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
            etJumlahDonasi.addTextChangedListener(this);
        }
    }

    public class MyTextWatcher implements TextWatcher {
        private View view;
        private MyTextWatcher(View view) {
            this.view = view;
        }
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
        public void afterTextChanged(Editable editable) {
            etUangUE.removeTextChangedListener(this);
            try {
                String originalString = editable.toString();
                Long longval;
                if (originalString.contains(",")) {
                    originalString = originalString.replaceAll(",", "");
                }
                longval = Long.parseLong(originalString);
                DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                formatter.applyPattern("#,###,###,###");
                String formattedString = formatter.format(longval);
                etUangUE.setText(formattedString);
                etUangUE.setSelection(etUangUE.getText().length());
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
            etUangUE.addTextChangedListener(this);
        }
    }

    public class TextWatcherUang implements TextWatcher {
        private View view;
        private TextWatcherUang(View view) {
            this.view = view;
        }
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
        public void afterTextChanged(Editable editable) {
            etUangU.removeTextChangedListener(this);
            try {
                String originalString = editable.toString();

                Long longval;
                if (originalString.contains(",")) {
                    originalString = originalString.replaceAll(",", "");
                }
                longval = Long.parseLong(originalString);
                DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                formatter.applyPattern("#,###,###,###");
                String formattedString = formatter.format(longval);
                etUangU.setText(formattedString);
                etUangU.setSelection(etUangU.getText().length());
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
            etUangU.addTextChangedListener(this);
        }
    }

    public class TextWatcherEmasE implements TextWatcher {
        private View view;
        private TextWatcherEmasE(View view) {
            this.view = view;
        }
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
        public void afterTextChanged(Editable editable) {
            etEmasE.removeTextChangedListener(this);
            try {
                String originalString = editable.toString();
                Long longval;
                if (originalString.contains(",")) {
                    originalString = originalString.replaceAll(",", "");
                }
                longval = Long.parseLong(originalString);
                DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                formatter.applyPattern("#,###,###,###");
                String formattedString = formatter.format(longval);
                etEmasE.setText(formattedString);
                etEmasE.setSelection(etEmasE.getText().length());
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
            etEmasE.addTextChangedListener(this);
        }
    }

    public class TextWatcherEmasUE implements TextWatcher {
        private View view;
        private TextWatcherEmasUE(View view) {
            this.view = view;
        }
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
        public void afterTextChanged(Editable editable) {
            etEmasUE.removeTextChangedListener(this);
            try {
                String originalString = editable.toString();
                Long longval;
                if (originalString.contains(",")) {
                    originalString = originalString.replaceAll(",", "");
                }
                longval = Long.parseLong(originalString);
                DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                formatter.applyPattern("#,###,###,###");
                String formattedString = formatter.format(longval);
                etEmasUE.setText(formattedString);
                etEmasUE.setSelection(etEmasUE.getText().length());
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
            etEmasUE.addTextChangedListener(this);
        }
    }

    public void showInputMethod() {
        InputMethodManager imm = (InputMethodManager) ChatActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

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

    private int convertDpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return Math.round(px);
    }

    public void installTTSApplication() {
        Intent marketIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + "com.google.android.tts"));
        marketIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_MULTIPLE_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(marketIntent);
    }

    // change click listerner
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



//    @OnClick(R.id.action_mic)
//    public void speakToText() {
//        if (isTouch) {
//            isTouch = false;
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                ivActionMic.setImageDrawable(getResources().getDrawable(R.drawable.ic_bt_mic_blue_listening, getApplicationContext().getTheme()));
//            } else {
//                ivActionMic.setImageDrawable(getResources().getDrawable(R.drawable.ic_bt_mic_blue_listening));
//            }
//            speech.startListening(recognizerIntent);
//        } else {
//            isTouch = true;
//            speech.stopListening();
//        }
//    }

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
    public void scrollChatDown() {
        this.rvChatList.scrollToPosition(presenter.getChatObjects().size() - 1);
    }

    @Override
    public void notifyAdapterObjectRemove(int position) {
        this.chatAdapter.notifyItemRemoved(position);
    }

    @Override
    public void speakOut(String speakText) {
        if (ttsUtils != null) {
            ttsUtils.speak(speakText);
        }
    }

    @Override
    public void inputChat(String text) {
        rvQuickButton.setVisibility(View.GONE);
        if (!TextUtils.isEmpty(text)) {
            if (statusLoading == 1) {
                presenter.removeItem();
            }
            final ChatReq req = new ChatReq();
            req.setUserId(Prefs.getString("USER_ID",""));
            req.setQuery(text);
            req.setLat(String.valueOf(latitude));
            req.setLon(String.valueOf(longitude));
            req.setChannel("android");
            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    ChatResponseEntity chatResponseEntity = new ChatResponseEntity();
                    chatResponseEntity.setChatHistory(req.getQuery());
                    mDb.chatResponseDao().insertAll(chatResponseEntity);
                }
            });
//            insertToDatabase(req.getQuery());
            presenter.onEditTextActionDone(req.getQuery());
            statusLoading = 1;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    presenter.requestDataFromServer(req);
                    statusLoading = 0;
                }
            }, 1000);
        }
    }

    @Override
    public void inputChatZakat(String text) {
        rvQuickButton.setVisibility(View.GONE);
        if (!TextUtils.isEmpty(text)) {
            if (statusLoading == 1) {
                presenter.removeItem();
            }
            final ChatReq req = new ChatReq();
            req.setUserId(Prefs.getString("USER_ID",""));
            req.setQuery(text);
            req.setLat(String.valueOf(latitude));
            req.setLon(String.valueOf(longitude));
            req.setChannel("android");
//            insertToDatabase(req.getQuery());
            presenter.removeItem();
            statusLoading = 1;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    presenter.requestDataFromServer(req);
                    statusLoading = 0;
                }
            }, 1000);
        }
    }

    @Override
    public void setTextChat(String textChat) {
        showInputMethod();
        etSendMessage.setText(textChat);
        etSendMessage.setSelection(etSendMessage.getText().length());
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(ChatActivity.this, R.string.communication_error, Toast.LENGTH_SHORT).show();
    }

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
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(ChatActivity.this, R.style.AppBottomSheetDialogTheme);

        View sheetView = getLayoutInflater().inflate(R.layout.bottomsheet_form_donasi, null);

        etJumlahDonasi = sheetView.findViewById(R.id.et_jumlah_donasi);
        etNamaDonasi = sheetView.findViewById(R.id.et_nama_donasi);
        etEmailDonasi = sheetView.findViewById(R.id.et_email_donasi);
        etNoKtp = sheetView.findViewById(R.id.et_no_ktp);
        image_view_close = sheetView.findViewById(R.id.imageViewClose);


        etJumlahDonasi.addTextChangedListener(new TextWatcherDonasi(etJumlahDonasi));

        btnBatal = sheetView.findViewById(R.id.button_batal);

        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputChat("batal");
                bottomSheetDialog.dismiss();
            }
        });
        image_view_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputChat("batal");
                bottomSheetDialog.dismiss();
            }
        });

        btnKirim = sheetView.findViewById(R.id.button_kirim);
        btnKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //step 1 ini pojo
                ZakatDonasi versionObject = new ZakatDonasi();

                String jumlahDonasi = etJumlahDonasi.getText().toString();
                String namaDonasi = etNamaDonasi.getText().toString();
                String emailDonasi = etEmailDonasi.getText().toString();
                String noKTP = etNoKtp.getText().toString();

                String email = etEmailDonasi.getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String emailPatternId = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.+[a-z]+";

                String ktp = "^" +
                        "(?=.*[0-9])" +         //at least 1 digit
                        ".{16,}" +               //at least 4 characters
                        "$";

                if (TextUtils.isEmpty(etJumlahDonasi.getText().toString())) {
                    Toast.makeText(ChatActivity.this, "Isi Jumlah terlebih dahulu", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(etNamaDonasi.getText().toString())) {
                    Toast.makeText(ChatActivity.this, "Isi Nama terlebih dahulu", Toast.LENGTH_SHORT).show();
                }  else if (TextUtils.isEmpty(etEmailDonasi.getText().toString())) {
                    Toast.makeText(ChatActivity.this, "Isi Email terlebih dahulu ", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(etEmailDonasi.getText().toString())) {
                    Toast.makeText(ChatActivity.this, "Isi Email terlebih dahulu ", Toast.LENGTH_SHORT).show();
                } else if (!email.matches(emailPattern) && !email.matches(emailPatternId)) {
                    Toast.makeText(ChatActivity.this, "Email Kamu Salah ", Toast.LENGTH_SHORT).show();
                } else {

                    versionObject.setNama(namaDonasi);
                    versionObject.setNoktp(noKTP);
                    versionObject.setEmail(emailDonasi);
                    versionObject.setAmount(jumlahDonasi);

                    //step 2 ubah pojo ke JSON OBJECT
                    Gson gson = new GsonBuilder().create();
                    String json = gson.toJson(versionObject);
                    inputChatZakat(json);

                    bottomSheetDialog.dismiss();
                }
            }
        });
        etEmailDonasi.setText(Constant.EMAIL);
        bottomSheetDialog.setContentView(sheetView);
        bottomSheetDialog.show();
    }

    @Override
    public void showBottomSheetTrainPassengerForm(ChatOutputTrainPassengerForm chatOutputTrainPassengerForm) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(ChatActivity.this, R.style.AppBottomSheetDialogTheme);

        View sheetView = getLayoutInflater().inflate(R.layout.bottomsheet_form_detilpassengers_kereta, null);

        tvTuan = sheetView.findViewById(R.id.text_view_tuan);
        tvNyonya = sheetView.findViewById(R.id.text_view_nyonya);
        imageCloseFormKereta = sheetView.findViewById(R.id.imageViewClose);
        etNamaPenumpang = sheetView.findViewById(R.id.et_namapnmpg_kereta);
        etTglLahirPenumpang = sheetView.findViewById(R.id.et_tgl_lahir);
        etNoKtpPenumpang = sheetView.findViewById(R.id.et_no_ktp);
        etNoTelpPenumpang = sheetView.findViewById(R.id.et_no_telp);

        etNamaPemesan = sheetView.findViewById(R.id.et_namapemesan);
        etEmailPemesan = sheetView.findViewById(R.id.et_email);
        etNoHpPemesan = sheetView.findViewById(R.id.et_no_handphone);

        prosesPenumpang = sheetView.findViewById(R.id.button_proses);

        imageCloseFormKereta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputChat("batal");
                bottomSheetDialog.dismiss();
            }
        });

        prosesPenumpang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noKtpPenumpang1 = etNoKtpPenumpang.getText().toString();

                String email = etEmailPemesan.getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String emailPatternId = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.+[a-z]+";

                String noKtp = "^" +
                        "(?=.*[0-9])" +         //at least 1 digit
                        ".{16,}" +               //at least 4 characters
                        "$";

                if (TextUtils.isEmpty(etNamaPenumpang.getText().toString())) {
                    Toast.makeText(ChatActivity.this, "Isi Nama terlebih dahulu", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(etTglLahirPenumpang.getText().toString())) {
                    Toast.makeText(ChatActivity.this, "Isi Tanggal Lahir", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(etNoKtpPenumpang.getText().toString())) {
                    Toast.makeText(ChatActivity.this, "Isi No Ktp ", Toast.LENGTH_SHORT).show();
                } else if (!noKtpPenumpang1.matches(noKtp)) {
                    Toast.makeText(ChatActivity.this, "Nomor harus 16 digit", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(etNoTelpPenumpang.getText().toString())) {
                    Toast.makeText(ChatActivity.this, "Isi No Telpon", Toast.LENGTH_SHORT).show();
                } else if (etNoTelpPenumpang.length() > 13) {
                    Toast.makeText(ChatActivity.this, "Maaf No Telpon Anda Salah", Toast.LENGTH_SHORT).show();
                } else if (etNoTelpPenumpang.length() < 10) {
                    Toast.makeText(ChatActivity.this, "Maaf No Telpon Anda Salah", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(etNamaPemesan.getText().toString())) {
                    Toast.makeText(ChatActivity.this, "Isi Nama Pemesan", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(etEmailPemesan.getText().toString())) {
                    Toast.makeText(ChatActivity.this, "Isi Email Pemesan", Toast.LENGTH_SHORT).show();
                } else if (!email.matches(emailPattern) && !email.matches(emailPatternId)) {
                    Toast.makeText(ChatActivity.this, "Email Kamu Salah ", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(etNoHpPemesan.getText().toString())) {
                    Toast.makeText(ChatActivity.this, "Isi No Handphone", Toast.LENGTH_SHORT).show();
                } else if (etNoHpPemesan.length() > 13) {
                    Toast.makeText(ChatActivity.this, "Maaf No Telpon Anda Salah", Toast.LENGTH_SHORT).show();
                } else if (etNoHpPemesan.length() < 10) {
                    Toast.makeText(ChatActivity.this, "Maaf No Telpon Anda Salah", Toast.LENGTH_SHORT).show();
                } else {
                    String titel = chooseTitel;
                    String namaPenumpang = etNamaPenumpang.getText().toString();
                    String tglLahir = tglLahirPenumpang;
                    String noKtpPenumpang = etNoKtpPenumpang.getText().toString();
                    String noTelpPenumapang = etNoTelpPenumpang.getText().toString();

                    String namaPemesan = etNamaPemesan.getText().toString();
                    String emailPemesan = etEmailPemesan.getText().toString();
                    String noHpPemesan = etNoHpPemesan.getText().toString();

                    String hasil = titel + "/" + namaPenumpang + "/" + tglLahir + "/" + noKtpPenumpang + "/" + noTelpPenumapang + "/"
                            + namaPemesan + "/" + emailPemesan + "/" + noHpPemesan;
                    inputChat(hasil);
                    bottomSheetDialog.dismiss();
                }
            }
        });

        etTglLahirPenumpang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        ChatActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog, mDataSetListenerTglLahirPenumpang,
                        year, month, day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
            }
        });

        mDataSetListenerTglLahirPenumpang = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String monthName = getMonthName(month);
                String date = dayOfMonth + " " + monthName + " " + year;
                etTglLahirPenumpang.setText(date);
                tglLahirPenumpang = date;
            }
        };

        tvTuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chooseTitel = "Tuan";
                tvTuan.setBackground(ContextCompat.getDrawable(ChatActivity.this, R.drawable.background_button_primary_rounded_register));
                tvTuan.setTextColor(ChatActivity.this.getResources().getColor(R.color.colorFilm));

                tvNyonya.setBackground(ContextCompat.getDrawable(ChatActivity.this, R.drawable.background_button_transfer_rounded_register));
                tvNyonya.setTextColor(ChatActivity.this.getResources().getColor(R.color.tundora));
            }
        });
        tvNyonya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chooseTitel = "Nyonya";

                tvTuan.setBackground(ContextCompat.getDrawable(ChatActivity.this, R.drawable.background_button_transfer_rounded_register));
                tvTuan.setTextColor(ChatActivity.this.getResources().getColor(R.color.tundora));
                tvNyonya.setBackground(ContextCompat.getDrawable(ChatActivity.this, R.drawable.background_button_primary_rounded_register));
                tvNyonya.setTextColor(ChatActivity.this.getResources().getColor(R.color.colorFilm));
            }
        });
        bottomSheetDialog.setContentView(sheetView);
        bottomSheetDialog.show();
    }

    @Override
    public void showBottomSheetTrainTripDetailrForm(ChatOutputTrainTripDetailForm chatOutputTrainTripDetailForm) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(ChatActivity.this, R.style.AppBottomSheetDialogTheme);

        View sheetView = getLayoutInflater().inflate(R.layout.bottomsheet_traintripdetailform, null);

        spLokasiPerjKereta = sheetView.findViewById(R.id.spLokasi_perjkereta);
        spTujuanPerjKereta = sheetView.findViewById(R.id.spTujuan_perjkereta);
        spLokasiDewasaPerjkereta = sheetView.findViewById(R.id.spLokasiDewasa_perjkereta);
        spLokasiAnakPerjkereta = sheetView.findViewById(R.id.spLokasiAnak_perjkereta);
        spLokasiBayiPerjkereta = sheetView.findViewById(R.id.spLokasiBayi_perjkereta);
        etKeberangkatan_perjkereta = sheetView.findViewById(R.id.editTextKeberangkatan_perjkereta);
        etKepulangan_perjkereta = sheetView.findViewById(R.id.editTextKepulangan_perjkereta);
        btnCariTiketKereta = sheetView.findViewById(R.id.button_cari_tiketkereta);

        imageViewCloseDetil = sheetView.findViewById(R.id.imageViewClose);

        llKeberangkatan = sheetView.findViewById(R.id.llKeberangkatan);
        llKepulangan = sheetView.findViewById(R.id.llKepulangan);

        tvPerjalanan = sheetView.findViewById(R.id.textViewPerjalanan);
        tvPulangPergi = sheetView.findViewById(R.id.textViewPulangPergi);

        imageViewCloseDetil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputChat("batal");
                bottomSheetDialog.dismiss();
            }
        });


        btnCariTiketKereta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (spLokasiPerjKereta.getSelectedItem().toString().equals("Pilih Stasiun Asal")) {
                    Toast.makeText(ChatActivity.this, "Pilih Asal Stasiun", Toast.LENGTH_SHORT).show();
                } else if (spTujuanPerjKereta.getSelectedItem().toString().equals("Pilih Stasiun Tujuan")) {
                    Toast.makeText(ChatActivity.this, "Pilih Tujuan Stasiun", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(etKeberangkatan_perjkereta.getText().toString())) {
                    Toast.makeText(ChatActivity.this, "Pilih Tanggal Berangkat", Toast.LENGTH_SHORT).show();
                } else {

                    String perjalanan = choosePerjalanan;
                    String asal = asalStasiun;
                    String tujuan = tujuanStasiun;
                    String berangkat = tglBerangkat;
                    String pulang = tglPulang;

                    String spDewasa = dewasa;
                    String spAnak = anak;
                    String spBayi = bayi;

                    if (perjalanan.equals("1x Perjalanan")) {
                        String hasil = perjalanan + "/" + asal + "/" + tujuan + "/" + berangkat + "/" + spDewasa + "/" + spAnak + "/" + spBayi;
                        inputChat(hasil);
                    } else if (perjalanan.equals("Pulang Pergi")) {
                        String hasilpp = perjalanan + "/" + asal + "/" + tujuan + "/" + berangkat + "/" + pulang + "/" + spDewasa + "/" + spAnak + "/" + spBayi;
                        inputChat(hasilpp);
                    }
                    bottomSheetDialog.dismiss();
                }


            }
        });

        final List<String> spinnerArrayDewasa = new ArrayList<String>();
        spinnerArrayDewasa.add("0");
        spinnerArrayDewasa.add("1");
        spinnerArrayDewasa.add("2");
        spinnerArrayDewasa.add("3");

        SpinnerAdapter spinnerAdapterDewasa = new ArrayAdapter<>(ChatActivity.this, R.layout.s_jenkel, spinnerArrayDewasa);
        spLokasiDewasaPerjkereta.setAdapter(spinnerAdapterDewasa);

        spLokasiDewasaPerjkereta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dewasa = parent.getItemAtPosition(position).toString();
                if (dewasa.equals("0")) {
                    dewasa = "0";
                } else if (dewasa.equals("1")) {
                    dewasa = "1";
                } else if (dewasa.equals("2")) {
                    dewasa = "2";
                } else if (dewasa.equals("3")) {
                    dewasa = "3";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        final List<String> spinnerArrayAnak = new ArrayList<String>();
        spinnerArrayAnak.add("0");
        spinnerArrayAnak.add("1");
        spinnerArrayAnak.add("2");
        spinnerArrayAnak.add("3");

        SpinnerAdapter spinnerAdapterAnak = new ArrayAdapter<>(ChatActivity.this, R.layout.s_jenkel, spinnerArrayAnak);
        spLokasiAnakPerjkereta.setAdapter(spinnerAdapterAnak);

        spLokasiAnakPerjkereta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                anak = parent.getItemAtPosition(position).toString();

                if (anak.equals("0")) {
                    anak = "0";
                } else if (anak.equals("1")) {
                    anak = "1";
                } else if (anak.equals("2")) {
                    anak = "2";
                } else if (anak.equals("3")) {
                    anak = "3";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        final List<String> spinnerArrayBayi = new ArrayList<String>();
        spinnerArrayBayi.add("0");
        spinnerArrayBayi.add("1");
        spinnerArrayBayi.add("2");
        spinnerArrayBayi.add("3");

        SpinnerAdapter spinnerAdapterBayi = new ArrayAdapter<>(ChatActivity.this, R.layout.s_jenkel, spinnerArrayAnak);
        spLokasiBayiPerjkereta.setAdapter(spinnerAdapterBayi);

        spLokasiBayiPerjkereta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                bayi = parent.getItemAtPosition(position).toString();
                if (bayi.equals("0")) {
                    bayi = "0";
                } else if (bayi.equals("1")) {
                    bayi = "1";
                } else if (bayi.equals("2")) {
                    bayi = "2";
                } else if (bayi.equals("3")) {
                    bayi = "3";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });


        tvPerjalanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                choosePerjalanan = "1x Perjalanan";

                llKeberangkatan.setVisibility(View.VISIBLE);
                llKepulangan.setVisibility(View.GONE);

                tvPerjalanan.setBackground(ContextCompat.getDrawable(ChatActivity.this, R.drawable.background_button_primary_rounded_register));
                tvPerjalanan.setTextColor(ChatActivity.this.getResources().getColor(R.color.colorFilm));

                tvPulangPergi.setBackground(ContextCompat.getDrawable(ChatActivity.this, R.drawable.background_button_transfer_rounded_register));
                tvPulangPergi.setTextColor(ChatActivity.this.getResources().getColor(R.color.tundora));
            }
        });

        tvPulangPergi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                choosePerjalanan = "Pulang Pergi";

                llKeberangkatan.setVisibility(View.VISIBLE);
                llKepulangan.setVisibility(View.VISIBLE);

                tvPerjalanan.setBackground(ContextCompat.getDrawable(ChatActivity.this, R.drawable.background_button_transfer_rounded_register));
                tvPerjalanan.setTextColor(ChatActivity.this.getResources().getColor(R.color.tundora));
                tvPulangPergi.setBackground(ContextCompat.getDrawable(ChatActivity.this, R.drawable.background_button_primary_rounded_register));
                tvPulangPergi.setTextColor(ChatActivity.this.getResources().getColor(R.color.colorFilm));
            }
        });

        etKeberangkatan_perjkereta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        ChatActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog, mDataSetListener,
                        year, month, day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            }
        });


        mDataSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                String monthName = getMonthName(month);

                String date = dayOfMonth + " " + monthName + " " + year;

                etKeberangkatan_perjkereta.setText(date);

                tglBerangkat = date;


            }
        };

        etKepulangan_perjkereta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int monthKepulangan = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog dialog = new DatePickerDialog(
                        ChatActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog, mDataSetListenerKepulangan,
                        year, monthKepulangan, day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            }
        });

        mDataSetListenerKepulangan = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String monthName = getMonthNameKepulangan(month);
                String dateKepulangan = dayOfMonth + " " + monthName + " " + year;

                etKepulangan_perjkereta.setText(dateKepulangan);
                tglPulang = dateKepulangan;
            }
        };

        initSpinnerAsalStasiun(chatOutputTrainTripDetailForm.getData().getStations());
        initSpinnerTujuanStasiun(chatOutputTrainTripDetailForm.getData().getStations());

        bottomSheetDialog.setContentView(sheetView);

        bottomSheetDialog.show();
    }

    @Override
    public void showBottomSheetPlaneTripDetailrForm(ChatOutputPlaneTripDetailForm chatOutputPlaneTripDetailForm) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(ChatActivity.this, R.style.AppBottomSheetDialogTheme);

        View sheetView = getLayoutInflater().inflate(R.layout.bottomsheet_travel, null);

        spinnerAsalPesawat = sheetView.findViewById(R.id.spLokasiAsalPesawat);
        spinnerTujuanPesawat = sheetView.findViewById(R.id.spLokasiTujuanPesawat);
        spinnerDewasaPesawat = sheetView.findViewById(R.id.spDewasaPesawat);
        spinnerAnakPesawat = sheetView.findViewById(R.id.spAnakPesawat);
        spinnerBayiPesawat = sheetView.findViewById(R.id.spBayiPesawat);
        etKeberangkatan_pesawat = sheetView.findViewById(R.id.editTextKeberangkatanPesawat);
        etKepulangan_pesawat = sheetView.findViewById(R.id.editTextKepulanganPesawat);
        btnCariTiketPesawat = sheetView.findViewById(R.id.button_cari_tiketpesawat);

        imageViewCloseDetilPesawat = sheetView.findViewById(R.id.imageViewClosePesawat);

        llKeberangkatanPesawat = sheetView.findViewById(R.id.llKeberangkatanPesawat);
        llKepulanganPesawat = sheetView.findViewById(R.id.llKepulanganPesawat);

        tvPerjalananPesawat = sheetView.findViewById(R.id.textViewPerjalananPesawat);
        tvPulangPergiPesawat = sheetView.findViewById(R.id.textViewPulangPergiPesawat);

        imageViewCloseDetilPesawat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputChat("batal");
                bottomSheetDialog.dismiss();
            }
        });

        btnCariTiketPesawat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spinnerAsalPesawat.getSelectedItem().toString().equals("Pilih Bandara Asal")) {
                    Toast.makeText(ChatActivity.this, "Pilih Asal Bandara", Toast.LENGTH_SHORT).show();
                } else if (spinnerTujuanPesawat.getSelectedItem().toString().equals("Pilih Bandara Tujuan")) {
                    Toast.makeText(ChatActivity.this, "Pilih Bandara Stasiun", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(etKeberangkatan_pesawat.getText().toString())) {
                    Toast.makeText(ChatActivity.this, "Pilih Tanggal Berangkat", Toast.LENGTH_SHORT).show();
                } else {
                    String perjalananPesawat = choosePerjalananPesawat;
                    String asal = asalBandara;
                    String tujuan = tujuanBandara;
                    String berangkat = tglBerangkatPesawat;
                    String pulang = tglPulangPesawat;

                    String spDewasa = dewasaPesawat;
                    String spAnak = anakPesawat;
                    String spBayi = bayiPesawat;

                    //sampai disini

                    if (perjalananPesawat.equals("1x Perjalanan")) {
                        String hasil = perjalananPesawat + "/" + asal + "/" + tujuan + "/" + berangkat + "/" + spDewasa + "/" + spAnak + "/" + spBayi;
                        inputChat(hasil);
                    } else if (perjalananPesawat.equals("Pulang Pergi")) {
                        String hasilpp = perjalananPesawat + "/" + asal + "/" + tujuan + "/" + berangkat + "/" + pulang + "/" + spDewasa + "/" + spAnak + "/" + spBayi;
                        inputChat(hasilpp);
                    }
                    bottomSheetDialog.dismiss();
                }
            }
        });

        final List<String> spinnerArrayDewasa = new ArrayList<String>();
        spinnerArrayDewasa.add("0");
        spinnerArrayDewasa.add("1");
        spinnerArrayDewasa.add("2");
        spinnerArrayDewasa.add("3");

        SpinnerAdapter spinnerAdapterDewasa = new ArrayAdapter<>(ChatActivity.this, R.layout.s_jenkel, spinnerArrayDewasa);
        spinnerDewasaPesawat.setAdapter(spinnerAdapterDewasa);
        spinnerDewasaPesawat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dewasaPesawat = parent.getItemAtPosition(position).toString();
                if (dewasaPesawat.equals("0")) {
                    dewasaPesawat = "0";
                } else if (dewasaPesawat.equals("1")) {
                    dewasaPesawat = "1";
                } else if (dewasaPesawat.equals("2")) {
                    dewasaPesawat = "2";
                } else if (dewasaPesawat.equals("3")) {
                    dewasaPesawat = "3";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        final List<String> spinnerArrayAnak = new ArrayList<String>();
        spinnerArrayAnak.add("0");
        spinnerArrayAnak.add("1");
        spinnerArrayAnak.add("2");
        spinnerArrayAnak.add("3");

        SpinnerAdapter spinnerAdapterAnak = new ArrayAdapter<>(ChatActivity.this, R.layout.s_jenkel, spinnerArrayAnak);
        spinnerAnakPesawat.setAdapter(spinnerAdapterAnak);

        spinnerAnakPesawat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                anakPesawat = parent.getItemAtPosition(position).toString();
                if (anakPesawat.equals("0")) {
                    anakPesawat = "0";
                } else if (anakPesawat.equals("1")) {
                    anakPesawat = "1";
                } else if (anakPesawat.equals("2")) {
                    anakPesawat = "2";
                } else if (anakPesawat.equals("3")) {
                    anakPesawat = "3";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        final List<String> spinnerArrayBayi = new ArrayList<String>();
        spinnerArrayBayi.add("0");
        spinnerArrayBayi.add("1");
        spinnerArrayBayi.add("2");
        spinnerArrayBayi.add("3");

        SpinnerAdapter spinnerAdapterBayi = new ArrayAdapter<>(ChatActivity.this, R.layout.s_jenkel, spinnerArrayAnak);
        spinnerBayiPesawat.setAdapter(spinnerAdapterBayi);
        spinnerBayiPesawat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bayiPesawat = parent.getItemAtPosition(position).toString();
                if (bayiPesawat.equals("0")) {
                    bayiPesawat = "0";
                } else if (bayiPesawat.equals("1")) {
                    bayiPesawat = "1";
                } else if (bayiPesawat.equals("2")) {
                    bayiPesawat = "2";

                } else if (bayiPesawat.equals("3")) {
                    bayiPesawat = "3";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        tvPerjalananPesawat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePerjalananPesawat = "1x Perjalanan";

                llKeberangkatanPesawat.setVisibility(View.VISIBLE);
                llKepulanganPesawat.setVisibility(View.GONE);

                tvPerjalananPesawat.setBackground(ContextCompat.getDrawable(ChatActivity.this, R.drawable.background_button_primary_rounded_register));
                tvPerjalananPesawat.setTextColor(ChatActivity.this.getResources().getColor(R.color.colorFilm));

                tvPulangPergiPesawat.setBackground(ContextCompat.getDrawable(ChatActivity.this, R.drawable.background_button_transfer_rounded_register));
                tvPulangPergiPesawat.setTextColor(ChatActivity.this.getResources().getColor(R.color.tundora));
            }
        });

        tvPulangPergiPesawat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                choosePerjalananPesawat = "Pulang Pergi";

                llKeberangkatanPesawat.setVisibility(View.VISIBLE);
                llKepulanganPesawat.setVisibility(View.VISIBLE);

                tvPerjalananPesawat.setBackground(ContextCompat.getDrawable(ChatActivity.this, R.drawable.background_button_transfer_rounded_register));
                tvPerjalananPesawat.setTextColor(ChatActivity.this.getResources().getColor(R.color.tundora));
                tvPulangPergiPesawat.setBackground(ContextCompat.getDrawable(ChatActivity.this, R.drawable.background_button_primary_rounded_register));
                tvPulangPergiPesawat.setTextColor(ChatActivity.this.getResources().getColor(R.color.colorFilm));
            }
        });

        etKeberangkatan_pesawat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        ChatActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog, mDataSetListenerKeberangkatanPesawat,
                        year, month, day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            }
        });

        mDataSetListenerKeberangkatanPesawat = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String monthName = getMonthName(month);
                String date = dayOfMonth + " " + monthName + " " + year;
                etKeberangkatan_pesawat.setText(date);
                tglBerangkatPesawat = date;
            }
        };

        etKepulangan_pesawat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int monthKepulangan = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        ChatActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog, mDataSetListenerKepulanganPesawat,
                        year, monthKepulangan, day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            }
        });

        mDataSetListenerKepulanganPesawat = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String monthName = getMonthNameKepulangan(month);
                String dateKepulangan = dayOfMonth + " " + monthName + " " + year;
                etKepulangan_pesawat.setText(dateKepulangan);
                tglPulangPesawat = dateKepulangan;
            }
        };

        initSpinnerAsalBandara(chatOutputPlaneTripDetailForm.getData().getAirports());
        initSpinnerTujuanBandara(chatOutputPlaneTripDetailForm.getData().getAirports());

        bottomSheetDialog.setContentView(sheetView);
        bottomSheetDialog.show();
    }

    @Override
    public void showBottomSheetPlanePassengerForm(ChatOutputPlanePassengerForm chatOutputPlanePassengerForm) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(ChatActivity.this, R.style.AppBottomSheetDialogTheme);
        View sheetView = getLayoutInflater().inflate(R.layout.bottomsheet_form_detilpassengers_pesawat, null);

        tvTuanPlane = sheetView.findViewById(R.id.text_view_tuan);
        tvNyonyaPlane = sheetView.findViewById(R.id.text_view_nyonya);
        imageCloseFormPlane = sheetView.findViewById(R.id.imageViewClose);
        et_plane_passenger_name = sheetView.findViewById(R.id.et_plane_passenger_name);
        et_date_plane_passenger = sheetView.findViewById(R.id.et_date_plane_passenger);
        et_identity_number = sheetView.findViewById(R.id.et_identity_number);
        et_no_passpord = sheetView.findViewById(R.id.et_no_passpord);
        spinnerPassportCountrey = sheetView.findViewById(R.id.spPassportCountrey);
        spinnerKewarganegaraan = sheetView.findViewById(R.id.spKewarganegaraan);
        et_dateExpired = sheetView.findViewById(R.id.et_expired);
        prosesPenumpangPesawat = sheetView.findViewById(R.id.button_proses_plane);
        imageViewCloseDetilPlane = sheetView.findViewById(R.id.imageViewClosePlane);
        imageViewCloseDetilPlane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputChat("batal");
                bottomSheetDialog.dismiss();
            }
        });

        et_date_plane_passenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        ChatActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog, mDataSetListenerTglLahirPenumpangPesawat,
                        year, month, day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
            }
        });

        mDataSetListenerTglLahirPenumpangPesawat = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                String monthName = getMonthName(month);
                String date = dayOfMonth + " " + monthName + " " + year;
                et_date_plane_passenger.setText(date);
                tglLahirPenumpangPesawat = date;

            }
        };

        et_dateExpired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        ChatActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog, mDataSetListenerDateExpired,
                        year, month, day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            }
        });

        mDataSetListenerDateExpired = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String monthName = getMonthName(month);
                String date = dayOfMonth + " " + monthName + " " + year;
                et_dateExpired.setText(date);
                tglExpired = date;
            }
        };

        final List<String> spCountrey = new ArrayList<String>();
        spCountrey.add("Indonesia");
        spCountrey.add("Amerika");
        spCountrey.add("Kanada");

        SpinnerAdapter spinnerAdapterCountrey = new ArrayAdapter<>(ChatActivity.this, R.layout.s_jenkel, spCountrey);
        spinnerKewarganegaraan.setAdapter(spinnerAdapterCountrey);

        spinnerKewarganegaraan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                negara = parent.getItemAtPosition(position).toString();
                if (negara.equals("Indonesia")) {
                    negara = "Indonesia";
                } else if (negara.equals("Amerika")) {
                    negara = "Amerika";
                } else if (negara.equals("Kanada")) {
                    negara = "Kanada";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        final List<String> passpordCountrey = new ArrayList<String>();
        passpordCountrey.add("Indonesia");
        passpordCountrey.add("Amerika");
        passpordCountrey.add("Kanada");

        SpinnerAdapter spinnerAdapterPasspordCountrey = new ArrayAdapter<>(ChatActivity.this, R.layout.s_jenkel, passpordCountrey);
        spinnerPassportCountrey.setAdapter(spinnerAdapterCountrey);
        spinnerPassportCountrey.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stringPasspordCountrey = parent.getItemAtPosition(position).toString();
                if (stringPasspordCountrey.equals("Indonesia")) {
                    stringPasspordCountrey = "Indonesia";
                } else if (stringPasspordCountrey.equals("Amerika")) {
                    stringPasspordCountrey = "Amerika";
                } else if (stringPasspordCountrey.equals("Kanada")) {
                    stringPasspordCountrey = "Kanada";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        tvTuanPlane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePlaneTitle = "Tuan";
                tvTuanPlane.setBackground(ContextCompat.getDrawable(ChatActivity.this, R.drawable.background_button_primary_rounded_register));
                tvTuanPlane.setTextColor(ChatActivity.this.getResources().getColor(R.color.colorFilm));
                tvNyonyaPlane.setBackground(ContextCompat.getDrawable(ChatActivity.this, R.drawable.background_button_transfer_rounded_register));
                tvNyonyaPlane.setTextColor(ChatActivity.this.getResources().getColor(R.color.tundora));
            }
        });

        tvNyonyaPlane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePlaneTitle = "Nyonya";
                tvTuanPlane.setBackground(ContextCompat.getDrawable(ChatActivity.this, R.drawable.background_button_transfer_rounded_register));
                tvTuanPlane.setTextColor(ChatActivity.this.getResources().getColor(R.color.tundora));
                tvNyonyaPlane.setBackground(ContextCompat.getDrawable(ChatActivity.this, R.drawable.background_button_primary_rounded_register));
                tvNyonyaPlane.setTextColor(ChatActivity.this.getResources().getColor(R.color.colorFilm));
            }
        });

        prosesPenumpangPesawat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String noPasspordObj = et_no_passpord.getText().toString();
                String noKtp = et_identity_number.getText().toString();
                String noKtpPesawat = et_date_plane_passenger.getText().toString();
                String noKtpPenumpang = "^" +
                        "(?=.*[0-9])" +         //at least 1 digit
                        ".{16,}" +               //at least 4 characters
                        "$";

                String noPasspordVal = "^" +
                        "(?=.*[0-9])" +         //at least 1 digit
                        ".{16,}" +               //at least 4 characters
                        "$";

                if (TextUtils.isEmpty(et_plane_passenger_name.getText().toString())) {
                    Toast.makeText(ChatActivity.this, "Isi Nama terlebih dahulu", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(et_date_plane_passenger.getText().toString())) {
                    Toast.makeText(ChatActivity.this, "Isi Tanggal Lahir", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(et_identity_number.getText().toString())) {
                    Toast.makeText(ChatActivity.this, "Isi No Ktp ", Toast.LENGTH_SHORT).show();
                } else if (!noKtp.matches(noKtpPenumpang)) {
                    Toast.makeText(ChatActivity.this, "Nomor harus 16 digit", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(et_no_passpord.getText().toString())) {
                    Toast.makeText(ChatActivity.this, "Isi Nomor Passpord terlebih dahulu", Toast.LENGTH_SHORT).show();
                } else if (!noPasspordObj.matches(noPasspordVal)) {
                    Toast.makeText(ChatActivity.this, "Nomor Passpord harus 16 digit", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(et_dateExpired.getText().toString())) {
                    Toast.makeText(ChatActivity.this, "Isi Tanggal Berlaku", Toast.LENGTH_SHORT).show();
                } else {
                    String title = choosePlaneTitle;
                    String namaPenumpangPesawat = et_plane_passenger_name.getText().toString();
                    String date = tglLahirPenumpangPesawat;
                    String spNegara = negara;
                    String spPasspordCountrey = stringPasspordCountrey;
                    String dateExpired = tglExpired;
                    String noKtpPenumpangPesawat = et_identity_number.getText().toString();
                    String noPasspord = et_no_passpord.getText().toString();

                    if (title.equals("Tuan")) {
                        String hasil = title + "/" + namaPenumpangPesawat + "/" + date + "/" + spNegara + "/" + noKtpPenumpangPesawat + "/"
                                + noPasspord + "/" + spPasspordCountrey + "/" + dateExpired;
                        inputChat(hasil);
                        bottomSheetDialog.dismiss();
                    }
                }
            }
        });
        bottomSheetDialog.setContentView(sheetView);
        bottomSheetDialog.show();
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
