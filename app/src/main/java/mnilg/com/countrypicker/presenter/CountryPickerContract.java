package mnilg.com.countrypicker.presenter;

import java.util.List;

import mnilg.com.countrypicker.model.Country;

/**
 * CountryPickerContract
 * @author ligang
 * @date 16/10/21 15:46
 * @email gang.li@mengqitech.com
 * @description 选择国家
 */
public interface CountryPickerContract {
    interface View extends BaseSearchSideBarListContract.View<Presenter>{
        void refreshList(List<Country> t);
    }
    interface Presenter extends BaseSearchSideBarListContract.Presenter{
    }
}
