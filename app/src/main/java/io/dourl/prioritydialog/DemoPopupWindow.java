package io.dourl.prioritydialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;
import io.dourl.lib_windows.IWindow;
import io.dourl.lib_windows.OnWindowDismissListener;


public class DemoPopupWindow extends PopupWindow implements IWindow {

    public DemoPopupWindow(Context context) {
        super(context);
        setHeight(600);
        setWidth(800);
        setOutsideTouchable(true);
        setFocusable(true);
        View contentView = LayoutInflater.from(context).inflate(R.layout.popup_demo, null, false);
        setContentView(contentView);
        contentView.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    @Override
    public String getWindowName() {
        return DemoPopupWindow.class.getSimpleName();
    }

    @Override
    public void show(AppCompatActivity activity) {
        showAtLocation(activity.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    @Override
    public boolean isShowing() {
        return super.isShowing();
    }

    @Override
    public void setOnWindowDismissListener(OnWindowDismissListener listener) {
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                listener.onDismiss();
            }
        });
    }
}
