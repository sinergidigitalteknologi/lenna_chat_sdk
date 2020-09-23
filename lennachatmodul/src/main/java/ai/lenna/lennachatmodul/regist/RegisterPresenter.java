package ai.lenna.lennachatmodul.regist;


import ai.lenna.lennachatmodul.regist.model.RegisterModel;
import ai.lenna.lennachatmodul.regist.model.RegisterReq;
import ai.lenna.lennachatmodul.regist.model.RegisterResp;
import ai.lenna.lennachatmodul.util.GenericErrorResponseBean;

public class RegisterPresenter implements RegisterContract.Presenter, RegisterContract.Model.OnFinishedListener {
    private RegisterContract.View view;
    private RegisterContract.Model model;

    public RegisterPresenter(RegisterContract.View view) {
        this.view = view;
        this.model = new RegisterModel();
    }

    @Override
    public void onFinishedSuccess(RegisterResp registerResp) {
        if (view != null){
            view.hideProgress();
        }
        view.showDialogSukses(registerResp);
    }

    @Override
    public void onFinishedFail(GenericErrorResponseBean responseBean) {
        if (view != null){
            view.hideProgress();
        }
        view.showDialogGagal(responseBean.getMessage());
    }

    @Override
    public void onFailure(Throwable t) {
        if(view != null){
            view.hideProgress();
        }
        view.showDialogGagal(t.getMessage());
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void requestDataFromServer(RegisterReq registerReq) {
        if (view != null){
            view.showProgress();
        }
        model.insertDataRegister(this, registerReq);
    }
}
