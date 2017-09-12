package mnilg.com.countrypicker.base;

/**
 * RxPresenter
 * @author ligang
 * @date 16/10/10 17:04
 * @email gang.li@mengqitech.com
 * @description 基于Rx的Presenter封装,控制订阅的生命周期
 */
public abstract class RxPresenter<T extends EmptyView> implements BasePresenter<T> {
    private T mView;

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
    }
}
