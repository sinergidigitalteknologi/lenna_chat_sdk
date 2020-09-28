package ai.lenna.lennachatmodul.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;

import ai.lenna.lennachatmodul.R;

public class ShowAllert {
    public static Activity activityShowAllert;
    public void allertWarning(Context context, String title, String text){
        new MaterialStyledDialog.Builder(context)
                .setTitle(title)
                .setDescription(text)
                .setIcon(R.drawable.ic_report_problem_black_50dp)
                .setHeaderColor(R.color.dialog_header_gagal)
                .setPositiveText("OK")
                .show();
    }
    public void allertSucses(Context context, Activity activity,String title, String text){
        new MaterialStyledDialog.Builder(context)
                .setTitle(title)
                .setDescription(text)
                .setIcon(R.drawable.ic_check_circle_black_40dp)
                .setHeaderColor(R.color.green_notification)
                .setPositiveText("OK")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if(activity != null){
                            activity.onBackPressed();
                        }else {

                        }

                    }
                })
                .show();
    }
    public void allertSucses(Context context, Intent intent, String title, String text){
        new MaterialStyledDialog.Builder(context)
                .setTitle(title)
                .setDescription(text)
                .setIcon(R.drawable.ic_check_circle_black_40dp)
                .setHeaderColor(R.color.green_notification)
                .setPositiveText("OK")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if(intent != null){
                            context.startActivity(intent);
                        }else {

                        }

                    }
                })
                .show();
    }

    public void allertSucses(Context context,String title, String text){
        new MaterialStyledDialog.Builder(context)
                .setTitle(title)
                .setDescription(text)
                .setIcon(R.drawable.ic_check_circle_black_40dp)
                .setHeaderColor(R.color.green_notification)
                .setPositiveText("OK")
                .show();
    }
    public void allertValidation(Context context,String title, String text){
        new MaterialStyledDialog.Builder(context)
                .setTitle(title)
                .setDescription(text)
                .setIcon(R.drawable.ic_report_problem_black_50dp)
                .setHeaderColor(R.color.dialog_header_gagal)
                .setPositiveText("Ya")
                .setNegativeText("Tidak")
                .show();
    }
    public void allertOptions(Context context,Activity activity,Activity activity2,String title, String text){
        new MaterialStyledDialog.Builder(context)
                .setTitle(title)
                .setDescription(text)
                .setIcon(R.drawable.ic_question_answer_black_24dp)
                .setHeaderColor(R.color.dialog_header_gagal)
                .setPositiveText("Buat Password")
                .setNegativeText("Ubah Password")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Intent intent  = new Intent(context,activity.getClass());
                        context.startActivity(intent);
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Intent intent  = new Intent(context,activity2.getClass());
                        context.startActivity(intent);
                    }
                })
                .show();
    }
}
