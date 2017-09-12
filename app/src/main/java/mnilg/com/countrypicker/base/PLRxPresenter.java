package mnilg.com.countrypicker.base;

import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentManager;


/**
 * PLRxPresenter
 * @author ligang
 * @date 16/10/24 16:56
 * @email gang.li@mengqitech.com
 * @description
 */
public class PLRxPresenter<V extends BaseView> extends RxPresenter {
    protected final V view;
    protected FragmentManager fragmentManager;
    private Handler uiHandler;

    public PLRxPresenter(FragmentManager fragmentManager, V view) {
        this.view = view;
        this.fragmentManager = fragmentManager;
        view.setPresenter(this);
    }

    @Override
    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }

    /**
     * 运行在UI线程
     * @param runnable
     */
    protected void runOnUiThread(Runnable runnable) {
        if(uiHandler == null) {
            uiHandler = new Handler(Looper.getMainLooper());
        }
        uiHandler.post(runnable);
    }
}
