package io.dourl.prioritydialog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;

import io.dourl.lib_windows.IWindow;
import io.dourl.lib_windows.WindowPriority;
import io.dourl.lib_windows.WindowTaskManager;
import io.dourl.lib_windows.WindowType;
import io.dourl.lib_windows.WindowWrapper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buildDialog();
    }

    /**
     * 模拟 启动时间 有大量对话框弹出 ，按优先级顺序弹出需求
     */
    private void buildDialog() {
        //告警第二
        IWindow demoDialog = getDialogWindow();
        WindowTaskManager.getInstance().addWindow(new WindowWrapper.Builder()
                .priority(WindowPriority.ALERT_PRIORITY)
                .windowType(WindowType.DIALOG)
                .window(demoDialog)
                .setWindowName(demoDialog.getWindowName())
                .setCanShow(true)
                .build());

       // 广告第三
        IWindow popupWindow = getPopupWindow();
        WindowTaskManager.getInstance().addWindow(new WindowWrapper.Builder()
                .priority(WindowPriority.AD_PRIORITY)
                .windowType(WindowType.DIALOG)
                .window(popupWindow)
                .setWindowName(popupWindow.getWindowName())
                .setCanShow(true)
                .build());

        //升级优先级最高
        IWindow upWindow = getFragmentWindow();
        WindowTaskManager.getInstance().addWindow(new WindowWrapper.Builder()
                .priority(WindowPriority.UPDATE_PRIORITY)
                .windowType(WindowType.DIALOGFRAGMENT)
                .window(upWindow)
                .setWindowName(upWindow.getWindowName())
                .setCanShow(true)
                .build());


        WindowTaskManager.getInstance().continueShow(this);


    }

    private IWindow getDialogWindow() {
        // Dialog
        DemoDialog demoDialog = new DemoDialog(this);
        demoDialog.setTitle("告警对话框");
        demoDialog.setMessage("Dialog Window");
        demoDialog.setCancelable(false);
        demoDialog.setButton(DialogInterface.BUTTON_POSITIVE, "关闭", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                demoDialog.dismiss();
            }
        });
        return demoDialog;
    }


    private IWindow getPopupWindow() {
        // PopupWindow
        DemoPopupWindow popupWindow = new DemoPopupWindow(this);
        return popupWindow;
    }

    private IWindow getFragmentWindow() {
        // FragmentDialog
        DemoFragmentDialog demoActivity = new DemoFragmentDialog();
        return demoActivity;
    }

    @Override
    protected void onDestroy() {
        WindowTaskManager.getInstance().clear();
        super.onDestroy();
    }
}