package ai.lenna.lennachatmodul.regist;


import ai.lenna.lennachatmodul.regist.model.RegisterReq;
import ai.lenna.lennachatmodul.regist.model.RegisterResp;
import ai.lenna.lennachatmodul.util.GenericErrorResponseBean;

public interface RegisterContract {

    interface Model {

        interface OnFinishedListener {
            void onFinishedSuccess(RegisterResp registerResp);
            void onFinishedFail(GenericErrorResponseBean responseBean);
            void onFailure(Throwable t);
        }

        void insertDataRegister(OnFinishedListener onFinishedListener, RegisterReq registerReq);

    }

    interface View {

        void showDialogSukses(RegisterResp registerResp);

        void showDialogGagal(String message);

        void showProgress();

        void hideProgress();

        void onResponseFailure(Throwable throwable);

    }

    interface Presenter {

        void onDestroy();

        void requestDataFromServer(RegisterReq registerReq);

    }
}
