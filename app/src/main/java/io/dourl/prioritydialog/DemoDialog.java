package io.dourl.prioritydialog;
import android.content.Context;
import android.content.DialogInterface;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import io.dourl.lib_windows.IWindow;
import io.dourl.lib_windows.OnWindowDismissListener;

public class DemoDialog extends AlertDialog implements IWindow {

    private OnWindowDismissListener onWindowDismissListener;

    protected DemoDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public String getWindowName() {
        return  DemoDialog.class.getSimpleName();
    }

    @Override
    public void show(AppCompatActivity activity) {
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
            return;
        }
        show();
    }

    @Override
    public boolean isShowing() {
        return super.isShowing();
    }

    @Override
    public void setOnWindowDismissListener(OnWindowDismissListener listener) {
        onWindowDismissListener = listener;
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                listener.onDismiss();
            }
        });
    }

}