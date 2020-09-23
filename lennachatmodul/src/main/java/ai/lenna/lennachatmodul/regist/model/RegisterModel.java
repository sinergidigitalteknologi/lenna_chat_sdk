package ai.lenna.lennachatmodul.regist.model;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import ai.lenna.lennachatmodul.network.ApiBuilder;
import ai.lenna.lennachatmodul.network.ApiService;
import ai.lenna.lennachatmodul.regist.RegisterContract;
import ai.lenna.lennachatmodul.util.Constant;
import ai.lenna.lennachatmodul.util.GenericErrorResponseBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterModel  implements RegisterContract.Model {

    @Override
    public void insertDataRegister(OnFinishedListener onFinishedListener, RegisterReq registerReq) {

        ApiService service = ApiBuilder.getClient().create(ApiService.class);

        Call<RegisterResp> call = service.reg(registerReq, Constant.APP_ID);
        call.enqueue(new Callback<RegisterResp>() {
            @Override
            public void onResponse(Call<RegisterResp> call, Response<RegisterResp> response) {
                if (response.isSuccessful()) {
                    if(response.body().getStatus() == 500){
                        GenericErrorResponseBean errorResponse = new GenericErrorResponseBean();
//                        try {
//                            JSONObject jsonObject = new JSONObject(response.body().getError());
//                            JSONObject jsonerror = jsonObject.getJSONObject("error");
//                            errorResponse.setCode(jsonerror.getInt("code"));
//                            errorResponse.setMessage(jsonerror.getString("message"));
                            errorResponse.setCode(5000);
                            errorResponse.setMessage(response.body().getError().getMessage());
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
                        onFinishedListener.onFinishedFail(errorResponse);
                    }else {
                        onFinishedListener.onFinishedSuccess(response.body());
                    }
                } else {
                    GenericErrorResponseBean errorResponse = new GenericErrorResponseBean();
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        JSONObject jsonerror = jsonObject.getJSONObject("error");

                        errorResponse.setCode(jsonerror.getInt("code"));
                        errorResponse.setMessage(jsonerror.getString("message"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    onFinishedListener.onFinishedFail(errorResponse);
                }
            }

            @Override
            public void onFailure(Call<RegisterResp> call, Throwable t) {
                onFinishedListener.onFailure(t);

            }
        });


    }
}
