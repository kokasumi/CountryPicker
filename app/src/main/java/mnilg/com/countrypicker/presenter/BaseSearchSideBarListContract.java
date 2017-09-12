package mnilg.com.countrypicker.presenter;


import mnilg.com.countrypicker.base.BasePresenter;
import mnilg.com.countrypicker.base.BaseView;

/**
 * BaseSearchSideBarListContract
 * @author ligang
 * @date 2017/3/14 15:51
 * @email gang.li@mengqitech.com
 * @description
 */
public interface BaseSearchSideBarListContract {
    interface View<P extends Presenter> extends BaseView<P> {

    }
    interface Presenter extends BasePresenter {
        void prepareData();
        void search(String keywords);
    }
}
