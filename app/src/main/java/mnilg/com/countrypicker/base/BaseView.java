package mnilg.com.countrypicker.base;

/**
 * BaseView
 * @author ligang
 * @date 16/10/10 16:56
 * @email gang.li@mengqitech.com
 * @description
 */
public interface BaseView<T> {
    void setPresenter(T presenter);
    void showError(String msg);
    void toastMsg(String msg);
    void isLoading(boolean isLoading);
}
