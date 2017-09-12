package mnilg.com.countrypicker.adapter;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.ViewGroup;

import java.util.Locale;

import butterknife.BindView;
import mnilg.com.countrypicker.R;
import mnilg.com.countrypicker.model.Country;
import mnilg.com.countrypicker.utills.ResourceUtil;

/**
 * CountryPickerViewHolder
 * @author ligang
 * @date 2016/11/10 15:07
 * @email gang.li@mengqitech.com
 * @description
 */
public class CountryPickerViewHolder extends BaseViewHolder<Country>{
    @BindView(R.id.iv_country_icon)
    AppCompatImageView mIvCountryIcon;
    @BindView(R.id.tv_country_name)
    AppCompatTextView mTvCountryName;
    @BindView(R.id.tv_dialog_code)
    AppCompatTextView mTvDialogCode;

    public CountryPickerViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_country_list);
    }

    @Override
    public void setData(Country data) {
        if(data != null) {
            String _drawableName = "flag_"
                    + data.getCode().toLowerCase(Locale.ENGLISH);
            mIvCountryIcon.setImageResource(ResourceUtil.getResId(_drawableName));
            mTvCountryName.setText(data.getName());
            mTvDialogCode.setText(String.valueOf(data.getDialCode()));
        }
    }
}
