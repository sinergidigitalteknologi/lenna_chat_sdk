package ai.lenna.lennachatmodul.chat;


import android.content.Context;

import java.util.ArrayList;

import ai.lenna.lennachatmodul.chat.model.ChatObject;
import ai.lenna.lennachatmodul.chat.model.ChatReq;
import ai.lenna.lennachatmodul.chat.model.ChatResp;
import ai.lenna.lennachatmodul.chat.model.bean.ChatResultBean;
import ai.lenna.lennachatmodul.chat.model.output.ChatOutputDataMuzaki;
import ai.lenna.lennachatmodul.chat.model.output.ChatOutputDatepickerForm;
import ai.lenna.lennachatmodul.chat.model.output.ChatOutputDonasi;
import ai.lenna.lennachatmodul.chat.model.output.ChatOutputForm;
import ai.lenna.lennachatmodul.chat.model.output.ChatOutputFormTravel;
import ai.lenna.lennachatmodul.chat.model.output.ChatOutputMultiDatePicker;
import ai.lenna.lennachatmodul.chat.model.output.ChatOutputPlanePassengerForm;
import ai.lenna.lennachatmodul.chat.model.output.ChatOutputPlaneTripDetailForm;
import ai.lenna.lennachatmodul.chat.model.output.ChatOutputTrainPassengerForm;
import ai.lenna.lennachatmodul.chat.model.output.ChatOutputTrainTripDetailForm;
import ai.lenna.lennachatmodul.chat.model.output.ChatOutputZakatFitrah;
import ai.lenna.lennachatmodul.chat.model.output.ChatOutputZakatMaal;
import ai.lenna.lennachatmodul.chat.model.output.ChatOutputZakatProfesi;
import ai.lenna.lennachatmodul.chat.model.output.action.ChatOutputAction;
import ai.lenna.lennachatmodul.util.GenericErrorResponseBean;

public interface ChatContract {

    interface Model {
        interface OnFinishedListener {
            void onFinishedSuccess(ChatResp chatResp);
            void onFinishedFail(GenericErrorResponseBean genericErrorResponseBean);
            void onFailure();
        }

        void sendChatToBot(OnFinishedListener onFinishedListener, ChatReq chatReq);
    }

    interface View {

        void notifyAdapterObjectAdded(int position);
        void scrollChatDown();
        void notifyAdapterObjectRemove(int position);
        void speakOut(String speakText);
        void inputChat(String text);

        void inputChatZakat(String text);
        void setTextChat(String textChat);
        void showErrorMessage();
        void showBottomSheetDialog(ChatOutputForm chatOutputForm);
        void showBottomSheetTravelDialog(ChatOutputFormTravel chatOutputFormTravel);
        void showBottomSheetDonasiiDialog(ChatOutputDonasi chatOutputDonasi);
        void showBottomSheetTrainPassengerForm(ChatOutputTrainPassengerForm chatOutputTrainPassengerForm);
        void showBottomSheetTrainTripDetailrForm(ChatOutputTrainTripDetailForm chatOutputTrainTripDetailForm);
        void showBottomSheetPlaneTripDetailrForm(ChatOutputPlaneTripDetailForm chatOutputPlaneTripDetailForm);
        void showBottomSheetPlanePassengerForm(ChatOutputPlanePassengerForm chatOutputPlanePassengerForm);
        void showBottomSheetDatePickerForm(ChatOutputDatepickerForm chatOutputDatepickerForm);
        void showBottomSheetMultipleDatePicker(ChatOutputMultiDatePicker chatOutputMultiDatePicker);




        void actionOpenApp(ChatOutputAction chatOutputAction, String subType);
        void quickButton(ChatResultBean chatResultBean);

        Context getContext();
    }

    interface Activity {
        void attachViewActivity(ChatContract.View view);
    }
    interface Adapter {
        void attachViewAdapter(ChatContract.View view);
    }

    interface Presenter {

        void attachView(ChatContract.View view);
        void removeItem();

        void addLoading();
        void onEditTextActionDone(String inputText);
        void onLiveChat(String json);
        void loadChatHistory(String json);
        ArrayList<ChatObject> getChatObjects();
        void requestDataFromServer(ChatReq req);
    }
}
