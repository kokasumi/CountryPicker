package mnilg.com.countrypicker.base;

import android.support.v4.app.FragmentManager;

/**
 * BasePresenter
 * @author ligang
 * @date 16/10/10 16:56
 * @email gang.li@mengqitech.com
 * @description MVP模式中基类Presenter
 */
public interface BasePresenter<T extends EmptyView> {
    void attachView(T view);

    void detachView();

    FragmentManager getFragmentManager();
}
