package com.app.mycontactlist.common.utils;
import com.app.mycontactlist.BuildConfig;
import com.app.mycontactlist.R;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class DialogUtil {

   static ProgressDialog progressBar;
    private static final String TAG = "DialogUtil";

    public static AlertDialog dialogWithNoTitle(Context ctx, View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        AlertDialog dialog = builder.create();
        //dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setView(view);
        return  dialog;
    }

    public static ProgressDialog getProgressDialog(Context ctx, String msg) {
        ProgressDialog dialog = new ProgressDialog(ctx);
        dialog.setMessage(msg);
        dialog.setTitle(R.string.app_name);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        return dialog;
    }
    public static void displayAlert(Context ctx, String title, String msg,
                                    int positiveBtnId, DialogInterface.OnClickListener positiveBtnListener) {
        try {
            if (ctx != null && title != null && msg != null)
                new AlertDialog.Builder(ctx).setTitle(title).setMessage(msg)
                        .setCancelable(false)
                        .setPositiveButton(positiveBtnId, positiveBtnListener)
                        .create().show();
        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }
    }

    public static void displayAlert(Context ctx, String title, String msg,
                                    int positiveBtnId, int cancelBtnId,
                                    DialogInterface.OnClickListener positiveBtnListener, DialogInterface.OnClickListener negBtnListener) {
        try {
            if (ctx != null && title != null && msg != null)
                new AlertDialog.Builder(ctx).setTitle(title).setMessage(msg)
                        .setCancelable(false)
                        .setPositiveButton(positiveBtnId, positiveBtnListener)
                        .setNegativeButton(cancelBtnId, negBtnListener)
                        .create().show();

        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays no network alert dialog. Msg - You are not connected to the
     * internet. Please check your internet connection. title - app name
     *
     * @param ctx
     */
    public static void showNoNetworkAlert(Context ctx) {
        try {
            new android.app.AlertDialog.Builder(ctx).setTitle(R.string.app_name).setMessage(R.string.no_internet)
                    .setPositiveButton(R.string.ok, null).create().show();
        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }
    }

    public static void showNoNetworkAlertForClose(final Context ctx) {
        try {
            new android.app.AlertDialog.Builder(ctx).setTitle(R.string.app_name).setMessage(R.string.no_internet)
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ((Activity) ctx).finish();
                        }
                    }).create().show();
        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }
    }

    public static void showDebugToast(Context ctx, String message) {
        if (BuildConfig.DEBUG)
            Toast.makeText(ctx, message, Toast.LENGTH_LONG).show();
    }

    public static void dismissKeyboard(Context ctx, EditText editText) {
        final InputMethodManager imm = (InputMethodManager) ctx.getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    public static void dismissKeyboardImg(Context ctx, ImageView imageView) {
        final InputMethodManager imm = (InputMethodManager) ctx.getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(imageView.getWindowToken(), 0);
    }

    public static void showProgress(Context mContext,String msg){
        progressBar = new ProgressDialog(mContext);
        progressBar.setCancelable(false);
        progressBar.setMessage(msg);
        progressBar.show();
    }

    public static void cancelPrgoress(){
        progressBar.cancel();
    }




}
