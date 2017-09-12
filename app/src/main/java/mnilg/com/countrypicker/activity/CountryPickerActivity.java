package mnilg.com.countrypicker.activity;

import butterknife.BindView;
import mnilg.com.countrypicker.R;
import mnilg.com.countrypicker.base.PLBaseActivity;
import mnilg.com.countrypicker.presenter.CountryPickerContract;
import mnilg.com.countrypicker.presenter.CountryPickerPresenter;
import mnilg.com.countrypicker.view.CountryPickerView;

/**
 * CountryPickerActivity
 * @author ligang
 * @date 16/10/21 16:56
 * @email gang.li@mengqitech.com
 * @description 选择国家
 */
public class CountryPickerActivity extends PLBaseActivity<CountryPickerContract.Presenter> {
    @BindView(R.id.view_country_picker)
    CountryPickerView mCountryPickerView;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_country_picker;
    }

    @Override
    protected void fetchData() {
        super.fetchData();
        mPresenter.prepareData();
    }

    @Override
    protected CountryPickerContract.Presenter createPresenter() {
        return new CountryPickerPresenter(getSupportFragmentManager(),mCountryPickerView);
    }
}
