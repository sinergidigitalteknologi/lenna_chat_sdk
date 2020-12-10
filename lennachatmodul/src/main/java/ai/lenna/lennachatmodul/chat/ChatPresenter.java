package ai.lenna.lennachatmodul.chat;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.Keep;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pixplicity.easyprefs.library.Prefs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import ai.lenna.lennachatmodul.chat.model.ChatModel;
import ai.lenna.lennachatmodul.chat.model.ChatObject;
import ai.lenna.lennachatmodul.chat.model.ChatReq;
import ai.lenna.lennachatmodul.chat.model.ChatResp;
import ai.lenna.lennachatmodul.chat.model.output.ChatOutputButton;
import ai.lenna.lennachatmodul.chat.model.output.ChatOutputCarousel;
import ai.lenna.lennachatmodul.chat.model.output.ChatOutputConfirm;
import ai.lenna.lennachatmodul.chat.model.output.ChatOutputDatepickerForm;
import ai.lenna.lennachatmodul.chat.model.output.ChatOutputDonasi;
import ai.lenna.lennachatmodul.chat.model.output.ChatOutputForm;
import ai.lenna.lennachatmodul.chat.model.output.ChatOutputFormTravel;
import ai.lenna.lennachatmodul.chat.model.output.ChatOutputGrid;
import ai.lenna.lennachatmodul.chat.model.output.ChatOutputHtml;
import ai.lenna.lennachatmodul.chat.model.output.ChatOutputImage;
import ai.lenna.lennachatmodul.chat.model.output.ChatOutputMovie;
import ai.lenna.lennachatmodul.chat.model.output.ChatOutputMultiDatePicker;
import ai.lenna.lennachatmodul.chat.model.output.ChatOutputNews;
import ai.lenna.lennachatmodul.chat.model.output.ChatOutputPlanePassengerForm;
import ai.lenna.lennachatmodul.chat.model.output.ChatOutputPlaneTripDetailForm;
import ai.lenna.lennachatmodul.chat.model.output.ChatOutputSummary;
import ai.lenna.lennachatmodul.chat.model.output.ChatOutputText;
import ai.lenna.lennachatmodul.chat.model.output.ChatOutputTrainPassengerForm;
import ai.lenna.lennachatmodul.chat.model.output.ChatOutputTrainTripDetailForm;
import ai.lenna.lennachatmodul.chat.model.output.ChatOutputTravel;
import ai.lenna.lennachatmodul.chat.model.output.ChatOutputWeather;
import ai.lenna.lennachatmodul.chat.model.output.action.ChatOutputAction;
import ai.lenna.lennachatmodul.chat.model.type.ChatInput;
import ai.lenna.lennachatmodul.chat.model.type.ChatLoadingResponse;
import ai.lenna.lennachatmodul.chat.model.type.ChatResponse;
import ai.lenna.lennachatmodul.chat.model.type.ChatResponseAction;
import ai.lenna.lennachatmodul.chat.model.type.ChatResponseButton;
import ai.lenna.lennachatmodul.chat.model.type.ChatResponseCarousel;
import ai.lenna.lennachatmodul.chat.model.type.ChatResponseConfirm;
import ai.lenna.lennachatmodul.chat.model.type.ChatResponseGrid;
import ai.lenna.lennachatmodul.chat.model.type.ChatResponseHtml;
import ai.lenna.lennachatmodul.chat.model.type.ChatResponseImage;
import ai.lenna.lennachatmodul.chat.model.type.ChatResponseList;
import ai.lenna.lennachatmodul.chat.model.type.ChatResponseMovie;
import ai.lenna.lennachatmodul.chat.model.type.ChatResponseNews;
import ai.lenna.lennachatmodul.chat.model.type.ChatResponseSummary;
import ai.lenna.lennachatmodul.chat.model.type.ChatResponseTravel;
import ai.lenna.lennachatmodul.chat.model.type.ChatResponseWeather;
import ai.lenna.lennachatmodul.chat.viewholder.ChatRespOpenAppVH;
import ai.lenna.lennachatmodul.room.AppDatabase;
import ai.lenna.lennachatmodul.room.AppExecutors;
import ai.lenna.lennachatmodul.room.entity.ChatResponseEntity;
import ai.lenna.lennachatmodul.util.AesCipher;
import ai.lenna.lennachatmodul.util.Constant;
import ai.lenna.lennachatmodul.util.GenericErrorResponseBean;

@Keep
public class ChatPresenter implements ChatContract.Presenter, ChatContract.Model.OnFinishedListener {

    private static final String TOKEN_BARRER = "bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjIyODAyIiwiZmlyc3RuYW1lIjoiQWd1bmciLCJsYXN0bmFtZSI6IlNhbnRvc28iLCJuaWNrbmFtZSI6IkFndW5nIiwiZW1haWwiOiJqb2VhZ3VuZzQ1NkBnbWFpbC5jb20iLCJwaG9uZSI6IjA4Nzg4MjE1NjIxMiIsImpvaW5kYXRlIjoiMjAxOC0wOS0xNCAyMDoxMjozNCIsInBhc3N3b3JkIjoiMjVmOWU3OTQzMjNiNDUzODg1ZjUxODFmMWI2MjRkMGIiLCJwaWN0dXJlIjpudWxsLCJzdGF0dXMiOiIyIiwidXNlcm9iamVjdCI6bnVsbCwidmVyaWZpY2F0aW9uX2tleSI6IjA1NThlNDRjNjQxNTI0M2QxMDNhYzM1ZThhYjMyNjVhIiwidmVyaWZpY2F0aW9uX2NvZGUiOm51bGwsImFwcF92ZXJzaW9uIjoiMCIsImJvdGlkIjoiMSIsInZlcmlmaWNhdGlvbl9lbWFpbCI6IjAiLCJ2ZXJpZmljYXRpb25fc21zIjoiMCIsImJpcnRoX2RhdGUiOiIxOTk3LTAzLTIxIiwiZ2VuZGVyIjoiTSJ9.91Vpz-DS4VKekFm-6mKpQrlbASPC8aIic72pkLbKijA";
    private static final String TYPE_TEXT = "text";
    private static final String TYPE_LOADING = "loading";
    private static final String TYPE_CROUSEL = "carousel";
    private static final String TYPE_MOVIE = "movie";
    private static final String TYPE_IMAGE = "image";
    private static final String TYPE_HTML = "html";
    private static final String TYPE_GRID = "grid";
    private static final String TYPE_LIST = "list";
    private static final String TYPE_FORM = "form";
    private static final String TYPE_FORM_TRAVEL = "formTravel";
    private static final String TYPE_CONFIRM = "confirm";
    private static final String TYPE_BUTTON = "button";
    private static final String TYPE_SUMMARY = "summary";
    private static final String TYPE_NEWS = "newsList";
    private static final String TYPE_TRAVEL = "ticketList";
    private static final String TYPE_WEATHER = "weather";
    private static final String TYPE_ACTION = "action";
    private static final String TYPE_FORM_DONASI = "donationForm";
    private static final String TYPE_FORM_DETIL_PENUMPANG_KERETA = "trainPassengerForm";
    private static final String TYPE_FORM_TRIP_DETIL_FORM = "trainTripDetailForm";
    private static final String TYPE_FORM_DETIL_PENUMPANG_PESAWAT = "flightPassengerForm";
    private static final String TYPE_FORM_TRIP_DETIL_FORM_PESAWAT = "flightTripDetailForm";
    private static final String TYPE_FORM_DATE_PICKER_FORM = "datePicker";
    private static final String TYPE_FORM_MULTIPLE_DATE_PICKER = "multiDatePicker";
    private static final String TYPE_SHARE_IMAGE = "shareImage";

    int objectPosition = 0;
    private Context context;
    private ArrayList<ChatObject> chatObjects;
    private ChatContract.View view;
    private ChatContract.Model model;
    private AppDatabase mDb;


    @Keep
    public ChatPresenter(ChatContract.View view) {
        // Create the ArrayList for the chat objects
        this.view = view;
        this.chatObjects = new ArrayList<>();
        this.model = new ChatModel();
    }

    @Keep
    @Override
    public void attachView(ChatContract.View view) {
        this.view = view;
        context = view.getContext();
    }

    @Keep
    @Override
    public void removeItem() {
        chatObjects.remove(chatObjects.size() - 1);
        view.notifyAdapterObjectRemove(chatObjects.size());
    }

    @Keep
    @Override
    public void addLoading() {
        ChatLoadingResponse loadingResponse = new ChatLoadingResponse();
        loadingResponse.setText("");
        this.chatObjects.add(loadingResponse);
        view.notifyAdapterObjectAdded(chatObjects.size() - 1);
        view.scrollChatDown();
    }

    @Keep
    @Override
    public ArrayList<ChatObject> getChatObjects() {
        return this.chatObjects;
    }


    @Keep
    @Override
    public void onEditTextActionDone(String inputText) {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm");
        String strDate = mdformat.format(calendar.getTime());

        // Create new input object
        ChatInput inputObject = new ChatInput();
        inputObject.setText(inputText);
        inputObject.setTime(strDate);


        // Add it to the list and tell the adapter we added something
        this.chatObjects.add(inputObject);
        view.notifyAdapterObjectAdded(chatObjects.size() - 1);

        // Also scroll down if we aren't at the bottom already
        view.scrollChatDown();

    }

    @Keep
    @Override
    public void onLiveChat(String json) {
        try {
            responseTypeLiveChat(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Keep
    @Override
    public void loadChatHistory(String json) {
        try {
            responseTypeHistoryChat(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Keep
    @Override
    public void requestDataFromServer(ChatReq req) {
        if (view != null) {
            addLoading();
        }
        model.sendChatToBot(this, req);
    }

    @Keep
    private void responseTypeHistoryChat(String json) throws JSONException {
        JSONObject obj = null;
        JSONArray output = null;

        try {
            obj = new JSONObject(json);
            output = new JSONArray(obj.getJSONObject("result").getJSONArray("output").toString());


        } catch (Throwable t) {
            Log.e("My JSON", "Could not parse malformed JSON: \"" + json + "\"");
            onEditTextActionDone(json);
        }

        if (output != null){
            for (int i = 0; i < output.length(); i++){
                String type = null;
                String subtype = null;
                JSONObject object = output.getJSONObject(i);
                String jsonObj = object.toString();

                type = object.getString("type");
                if (type.equals("action")) {
                    subtype = object.getString("subType");
                }
                mapType(type, jsonObj, subtype, "history");
            }
        }
    }

    @Keep
    private void responseTypeLiveChat(String json) throws JSONException {
        JSONArray obj = null;

        try {
            obj = new JSONArray(json);
            Log.d("My JSON", obj.toString());
        } catch (Throwable t) {
            Log.e("My JSON", "Could not parse malformed JSON: \"" + json + "\"");
        }


        for (int i = 0; i < obj.length(); i++){
            String type = null;
            String subtype = null;
            JSONObject object = obj.getJSONObject(i);
            String jsonObj = object.toString();

            type = object.getString("type");
            if (type.equals("action")) {
                subtype = object.getString("subType");
            }
            mapType(type, jsonObj, subtype, "live_chat");
        }
    }

    @Keep
    private void responseType(ChatResp chatResp) {

        for (int i = 0; i < chatResp.getResult().getOutput().size(); i++) {
            String type = null;
            String subtype = null;

            type = chatResp.getResult().getOutput().get(i).get("type").getAsString();

            if (type.equals("action")) {
                subtype = chatResp.getResult().getOutput().get(i).get("subType").getAsString();
            }

            String json = String.valueOf(chatResp.getResult().getOutput().get(i));

            mapType(type, json, subtype, "api");
        }

    }

    @Keep
    private void mapType(String type, String json, String subtype, String sourceType){
        switch (type) {
            case TYPE_TEXT:
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm");
                String strDate = mdformat.format(calendar.getTime());
                ChatResponse chatResponse = new ChatResponse();
                ChatOutputText outputText =
                        new Gson().fromJson(json,
                                ChatOutputText.class);
                String ori_text_response = outputText.getText();
                String arr[] = ori_text_response.split(" ", 2);

                String text_response = outputText.getText().replace(Constant.KEY_FALLBACK,"");
                chatResponse.setText(text_response);
                chatResponse.setTime(strDate);
//                chatResponse.setDate(chatResp.getDate());
                chatObjects.add(chatResponse);
                view.notifyAdapterObjectAdded(chatObjects.size() - 1);
                view.scrollChatDown();

                if (!sourceType.equals("history")){
                    speakOut(outputText.getSpeech().replace(Constant.KEY_FALLBACK,""));
                    if(arr[0].equals(Constant.KEY_FALLBACK)){
                        mainCours();
                    }
                }

                break;
            case TYPE_CROUSEL:
                ChatResponseCarousel chatResponseCarousel = new ChatResponseCarousel();
                ChatOutputCarousel outputCarousel =
                        new Gson().fromJson(json, ChatOutputCarousel.class);
                chatResponseCarousel.setChatColumnCarousels(outputCarousel.getColumns());
                chatObjects.add(chatResponseCarousel);
                view.notifyAdapterObjectAdded(chatObjects.size() - 1);
                view.scrollChatDown();
                if (!sourceType.equals("history")){
                    speakOut(outputCarousel.getSpeech());
                }
                break;
            case TYPE_NEWS:
                ChatResponseNews chatResponseNews = new ChatResponseNews();
                ChatOutputNews chatOutputNews =
                        new Gson().fromJson(json, ChatOutputNews.class);
                chatResponseNews.setChatColumnNews(chatOutputNews.getColumns());
                chatObjects.add(chatResponseNews);
                view.notifyAdapterObjectAdded(chatObjects.size() - 1);
                view.scrollChatDown();

                if (!sourceType.equals("history")){
                    speakOut(chatOutputNews.getSpeech());
                }
                break;
            case TYPE_MOVIE:
                ChatResponseMovie chatResponseMovie = new ChatResponseMovie();
                ChatOutputMovie outputMovie = new Gson().fromJson(json, ChatOutputMovie.class);
                chatResponseMovie.setChatColumnMovies(outputMovie.getColumns());
                chatObjects.add(chatResponseMovie);
                view.notifyAdapterObjectAdded(chatObjects.size() - 1);
                view.scrollChatDown();
                if (!sourceType.equals("history")){
                    speakOut(outputMovie.getSpeech());
                }
                break;
            case TYPE_IMAGE:
                ChatResponseImage chatResponseImage = new ChatResponseImage();
                ChatOutputImage outputImage = new Gson().fromJson(json, ChatOutputImage.class);
                chatResponseImage.setImage_original_url(outputImage.getOriginalContentUrl());
                chatResponseImage.setImage_preview_url(outputImage.getPreviewImageUrl());
                chatObjects.add(chatResponseImage);
                view.notifyAdapterObjectAdded(chatObjects.size() - 1);
                view.scrollChatDown();
                if (!sourceType.equals("history")){
                    speakOut(outputImage.getSpeech());
                }
                break;


            case TYPE_HTML:
                ChatResponseHtml chatResponseHtml = new ChatResponseHtml();
                ChatOutputHtml outputHtml = new Gson().fromJson(json, ChatOutputHtml.class);
                chatResponseHtml.setHtml(outputHtml.getHtml());
                chatObjects.add(chatResponseHtml);
                view.notifyAdapterObjectAdded(chatObjects.size() - 1);
                view.scrollChatDown();
                if (!sourceType.equals("history")){
                    speakOut(outputHtml.getSpeech());
                }
                view.scrollChatDown();
                break;
            case TYPE_LIST:
                ChatResponseList chatResponseList = new ChatResponseList();
                ChatOutputGrid outputList = new Gson().fromJson(json, ChatOutputGrid.class);
                chatResponseList.setImageUrlGrid(outputList.getImageUrl());
                chatResponseList.setSubTitleGrid(outputList.getSubTitle());
                chatResponseList.setChatColumnGrids(outputList.getColumns());
                chatObjects.add(chatResponseList);
                view.notifyAdapterObjectAdded(chatObjects.size() - 1);
                view.scrollChatDown();
                if (!sourceType.equals("history")){
                    speakOut(outputList.getSpeech());
                }
                break;
            case TYPE_GRID:
                ChatResponseGrid chatResponseGrid = new ChatResponseGrid();
                ChatOutputGrid outputGrid = new Gson().fromJson(json, ChatOutputGrid.class);
                chatResponseGrid.setImageUrlGrid(outputGrid.getImageUrl());
                chatResponseGrid.setSubTitleGrid(outputGrid.getSubTitle());
                chatResponseGrid.setChatColumnGrids(outputGrid.getColumns());
                chatObjects.add(chatResponseGrid);
                view.notifyAdapterObjectAdded(chatObjects.size() - 1);
                view.scrollChatDown();
                if (!sourceType.equals("history")){
                    speakOut(outputGrid.getSpeech());
                }
                break;
            case TYPE_FORM:
                ChatOutputForm chatOutputForm = new Gson().fromJson(json, ChatOutputForm.class);
                view.showBottomSheetDialog(chatOutputForm);
                break;
            case TYPE_CONFIRM:
                ChatResponseConfirm chatResponseConfirm = new ChatResponseConfirm();
                ChatOutputConfirm chatOutputConfirm = new Gson().fromJson(json, ChatOutputConfirm.class);
                chatResponseConfirm.setTextTitleConfirm(chatOutputConfirm.getText());
                chatObjects.add(chatResponseConfirm);
                view.notifyAdapterObjectAdded(chatObjects.size() - 1);
                view.scrollChatDown();
                break;
            case TYPE_BUTTON:
                ChatResponseButton chatResponseButton = new ChatResponseButton();
                ChatOutputButton chatOutputButton = new Gson().fromJson(json, ChatOutputButton.class);
                chatResponseButton.setTextTitleButton(chatOutputButton.getText());
                chatResponseButton.setChatActionButtons(chatOutputButton.getActions());
                chatObjects.add(chatResponseButton);
                view.notifyAdapterObjectAdded(chatObjects.size() - 1);
                view.scrollChatDown();
                break;
            case TYPE_SUMMARY:
                ChatResponseSummary chatResponseSummary = new ChatResponseSummary();
                ChatOutputSummary chatOutputSummary = new Gson().fromJson(json, ChatOutputSummary.class);
                chatResponseSummary.setChatColumnSummaries(chatOutputSummary.getColumns());
                chatResponseSummary.setImageUrl(chatOutputSummary.getImageUrl());
                chatObjects.add(chatResponseSummary);
                view.notifyAdapterObjectAdded(chatObjects.size() - 1);
                view.scrollChatDown();
                break;
            case TYPE_TRAVEL:
                ChatResponseTravel chatResponseTravel = new ChatResponseTravel();
                ChatOutputTravel outputOutputTravel = new Gson().fromJson(json, ChatOutputTravel.class);
                chatResponseTravel.setChatColumnAirlines(outputOutputTravel.getColumns());
                chatObjects.add(chatResponseTravel);
                view.notifyAdapterObjectAdded(chatObjects.size() - 1);
                view.scrollChatDown();
                break;
            case TYPE_FORM_TRAVEL:
                ChatOutputFormTravel chatOutputTravel = new Gson().fromJson(json, ChatOutputFormTravel.class);
                view.showBottomSheetTravelDialog(chatOutputTravel);
                break;
            case TYPE_WEATHER:
                ChatResponseWeather chatResponseWeather = new ChatResponseWeather();
                ChatOutputWeather chatOutputWeather = new Gson().fromJson(json, ChatOutputWeather.class);
                chatResponseWeather.setChatColumnWeathers(chatOutputWeather.getColumns());
                chatResponseWeather.setArea(chatOutputWeather.getArea());
                chatResponseWeather.setCountry(chatOutputWeather.getCountry());
                chatResponseWeather.setCountryCode(chatOutputWeather.getCountryCode());
                chatObjects.add(chatResponseWeather);
                view.notifyAdapterObjectAdded(chatObjects.size() - 1);
                view.scrollChatDown();
                break;

            case TYPE_ACTION:
                if (subtype.equals("openApp")) {
                    ChatResponseAction chatResponseAction = new ChatResponseAction();
                    ChatOutputAction outputOpenApp = new Gson().fromJson(json, ChatOutputAction.class);
                    chatResponseAction.setImageAction(outputOpenApp.getData().getIconUrl());
                    chatResponseAction.setTextAction(outputOpenApp.getData().getName());
                    chatResponseAction.setPackageAction(outputOpenApp.getData().getPackageName());
                    chatResponseAction.setSubTypeAction(subtype);
                    chatResponseAction.setChatOutputAction(outputOpenApp);

                    chatObjects.add(chatResponseAction);
                    view.notifyAdapterObjectAdded(chatObjects.size() -1 );
                    view.scrollChatDown();
                    if (!sourceType.equals("history")){
                        view.actionOpenApp(outputOpenApp, subtype);
                    }
                } else {
                    if (!sourceType.equals("history")){
                        ChatOutputAction outputOpenApp = new Gson().fromJson(json, ChatOutputAction.class);
                        view.actionOpenApp(outputOpenApp, subtype);
                    }
                }
                break;
            case TYPE_FORM_DONASI:
                if (!sourceType.equals("history")){
                    ChatOutputDonasi chatOutputDonasi = new Gson().fromJson(json, ChatOutputDonasi.class);
                    view.showBottomSheetDonasiiDialog(chatOutputDonasi);
                }

                break;
            case TYPE_FORM_DETIL_PENUMPANG_KERETA:
                if (!sourceType.equals("history")){
                    ChatOutputTrainPassengerForm chatOutputTrainPassengerForm = new Gson().fromJson(json, ChatOutputTrainPassengerForm.class);
                    view.showBottomSheetTrainPassengerForm(chatOutputTrainPassengerForm);
                }

                break;
            case TYPE_FORM_TRIP_DETIL_FORM:
                if (!sourceType.equals("history")){
                    ChatOutputTrainTripDetailForm chatOutputTrainTripDetailForm = new Gson().fromJson(json, ChatOutputTrainTripDetailForm.class);
                    view.showBottomSheetTrainTripDetailrForm(chatOutputTrainTripDetailForm);
                }

                break;

            case TYPE_FORM_DETIL_PENUMPANG_PESAWAT:
                if (!sourceType.equals("history")){
                    ChatOutputPlanePassengerForm chatOutputPlanePassengerForm = new Gson().fromJson(json, ChatOutputPlanePassengerForm.class);
                    view.showBottomSheetPlanePassengerForm(chatOutputPlanePassengerForm);
                }

                break;
            case TYPE_FORM_TRIP_DETIL_FORM_PESAWAT:
                if (!sourceType.equals("history")){
                    ChatOutputPlaneTripDetailForm chatOutputPlaneTripDetailForm = new Gson().fromJson(json, ChatOutputPlaneTripDetailForm.class);
                    view.showBottomSheetPlaneTripDetailrForm(chatOutputPlaneTripDetailForm);
                }

                break;
            case TYPE_FORM_DATE_PICKER_FORM:
                if (!sourceType.equals("history")){
                    ChatOutputDatepickerForm chatOutputDatepickerForm = new Gson().fromJson(json, ChatOutputDatepickerForm.class);
                    view.showBottomSheetDatePickerForm(chatOutputDatepickerForm);
                }

                break;
            case TYPE_FORM_MULTIPLE_DATE_PICKER:
                if (!sourceType.equals("history")){
                    ChatOutputMultiDatePicker chatOutputMultiDatePicker = new Gson().fromJson(json, ChatOutputMultiDatePicker.class);
                    view.showBottomSheetMultipleDatePicker(chatOutputMultiDatePicker);
                }
                break;
        }
    }
    @Keep
    @Override
    public void onFinishedSuccess(ChatResp chatResp) {
        Log.d("RESULT_CHAT_SAVE_DB", converterJsonToString(chatResp));
        if (view != null) {
            removeItem();
        }

        if (chatResp.getResult().getOutput() != null) {
            responseType(chatResp);
            mDb = AppDatabase.getInstance(context);
            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    ChatResponseEntity chatResponseEntity = new ChatResponseEntity();
                    chatResponseEntity.setChatHistory(converterJsonToString((chatResp)));
                    mDb.chatResponseDao().insertAll(chatResponseEntity);
                }
            });

            view.quickButton(chatResp.getResult());
        }

    }

    @Keep
    private String converterJsonToString(ChatResp chatResp){
        Gson gson = new GsonBuilder().create();
        return gson.toJson(chatResp);
    }

    @Keep
    @Override
    public void onFinishedFail(GenericErrorResponseBean genericErrorResponseBean) {
        if (view != null) {
            removeItem();
        }
        ChatResponse chatResponse = new ChatResponse();
        chatResponse.setText("Maaf sepertinya ada kesalahan");
        chatObjects.add(chatResponse);
        view.notifyAdapterObjectAdded(chatObjects.size() - 1);
        view.scrollChatDown();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        view.speakOut(chatResponse.getText());
    }

    @Keep
    @Override
    public void onFailure() {
        if (view != null) {
            removeItem();
        }
        ChatResponse chatResponse = new ChatResponse();
        chatResponse.setText("Maaf sepertinya terjadi kesalahan");
        chatObjects.add(chatResponse);
        view.notifyAdapterObjectAdded(chatObjects.size() - 1);
        view.scrollChatDown();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        view.speakOut(chatResponse.getText());
    }

    @Keep
    private void speakOut(String speech){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(speech==null){
        }else {
            if(speech.equals("")){
            }else {
                view.speakOut(speech);
            }
        }

    }

    @Keep
    private void mainCours(){
        final ChatReq req = new ChatReq(Prefs.getString("USER_ID",""),Constant.REQUEST_MENU_FALLBACK,String.valueOf(Constant.LAT),String.valueOf(Constant.LON),"android");
        try {
            req.setUserId( Prefs.getString("USER_ID",""));
            req.setQuery(Constant.REQUEST_MENU_FALLBACK);
            req.setLat( String.valueOf(Constant.LAT));
            req.setLon( String.valueOf(Constant.LON));
            req.setChannel("android");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    requestDataFromServer(req);
                }
            }, 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
