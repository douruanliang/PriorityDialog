package io.dourl.lib_windows;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 窗口约定规则
 */
public interface IWindow {

    String getWindowName();

    /**
     * 弹窗展示
     */
    void show(AppCompatActivity activity);

    /**
     * 弹窗关闭
     */
    void dismiss();

    /**
     * 是否在显示
     *
     * @return
     */
    boolean isShowing();

    /**
     * 设置窗口关闭监听
     */
    void setOnWindowDismissListener( OnWindowDismissListener listener);

}
