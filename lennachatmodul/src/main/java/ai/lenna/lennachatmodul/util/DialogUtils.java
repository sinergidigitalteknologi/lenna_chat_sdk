package ai.lenna.lennachatmodul.util;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.github.javiersantos.materialstyleddialogs.enums.Style;

import ai.lenna.lennachatmodul.R;

public class DialogUtils {

    public DialogUtils() {
    }

    //untuk Alert title dynamic
    public static void showDialogAlertNoAction(Context context, String alertText) {
        new MaterialDialog.Builder(context)
                .iconRes(R.drawable.ic_dialog_alert)
                .limitIconToDefaultSize() // limits the displayed icon size to 48dp
                .title(alertText)
                .titleColor(context.getResources().getColor(R.color.red_notification))
                .contentColor(context.getResources().getColor(R.color.red_notification))
                .positiveText("OKE")

                .canceledOnTouchOutside(false)
                .show();
    }



    public static void showDialogAlertGagal(Context context, String alertTitle, String alertDesc) {
        new MaterialStyledDialog.Builder(context)
                .setTitle(alertTitle)
                .setStyle(Style.HEADER_WITH_ICON)
                .setDescription(alertDesc)
                .setPositiveText("OK")
                .setIcon(R.drawable.ic_dialog_alert)
                .setHeaderColor(R.color.red_notification)
                .setCancelable(false)
                .show();
    }

    public static void showDialogAlertFailed(Context context, String alertTitle, String alertDesc) {
        new MaterialStyledDialog.Builder(context)
                .setTitle(alertTitle)
                .setStyle(Style.HEADER_WITH_ICON)
                .setDescription(alertDesc)
                .setPositiveText("OK")
                .setIcon(R.drawable.ic_dialog_false)
                .setHeaderColor(R.color.red_notification)
                .setCancelable(false)
                .show();
    }


    public static void showDialogAlertNoActionNew(Context context, String alertTitle, String alertDesc) {
        new MaterialStyledDialog.Builder(context)
                .setTitle(alertTitle)
                .setStyle(Style.HEADER_WITH_ICON)
                .setDescription(alertDesc)
                .setPositiveText("OK")
                .setIcon(R.drawable.ic_dialog_alert)
                .setHeaderColor(R.color.red_notification)
                .show();
    }

    //Untuk Alert title dan content dynamic
    public static void showDialogAlertNoAction(Context context, String title, String content) {
        new MaterialDialog.Builder(context)
                .iconRes(R.drawable.ic_dialog_alert)
                .limitIconToDefaultSize() // limits the displayed icon size to 48dp
                .title(title)
                .titleColor(context.getResources().getColor(R.color.red_notification))
                .contentColor(context.getResources().getColor(R.color.red_notification))
                .content(content)
                .canceledOnTouchOutside(false)
                .positiveText("OKE")
                .show();
    }
}
