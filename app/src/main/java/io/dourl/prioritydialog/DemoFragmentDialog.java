package io.dourl.prioritydialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import io.dourl.lib_windows.IWindow;
import io.dourl.lib_windows.OnWindowDismissListener;
import io.dourl.lib_windows.WindowHelper;


public class DemoFragmentDialog extends AppCompatDialogFragment implements IWindow {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragmemt_demo, null);

        AppCompatDialog appCompatDialog = new AppCompatDialog(getContext(), R.style.Theme_PriorityDialog);
        appCompatDialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        appCompatDialog.setCanceledOnTouchOutside(false);
        appCompatDialog.findViewById(R.id.close).setOnClickListener( v->{
            dismiss();
        });

        return appCompatDialog;
    }

    @Override
    public void onResume() {
        super.onResume();
        WindowHelper.getInstance().setActivityShow(true);
    }

    @Override
    public void onStop() {
        super.onStop();
        WindowHelper.getInstance().setActivityShow(false);
    }

    @Override
    public void onDestroy() {
        WindowHelper.getInstance().getActivityDismissListener().onDismiss();
        super.onDestroy();
    }

    @Override
    public String getWindowName() {
        return DemoFragmentDialog.class.getSimpleName();
    }

    @Override
    public void show(AppCompatActivity activity) {
        show(activity.getSupportFragmentManager(), "add");
    }

    @Override
    public boolean isShowing() {
        return WindowHelper.getInstance().isActivityShow();
    }

    @Override
    public void setOnWindowDismissListener(OnWindowDismissListener listener) {
        WindowHelper.getInstance().setActivityDismissListener(listener);
    }

}
