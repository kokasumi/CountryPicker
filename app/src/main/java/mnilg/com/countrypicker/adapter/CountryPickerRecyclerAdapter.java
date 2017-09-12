package mnilg.com.countrypicker.adapter;

import android.view.ViewGroup;

import mnilg.com.countrypicker.model.Country;


/**
 * CountryPickerRecyclerAdapter
 * @author ligang
 * @date 2016/11/10 16:53
 * @email gang.li@mengqitech.com
 * @description
 */
public class CountryPickerRecyclerAdapter extends BaseIndexerRecyclerAdapter<Country> {
    @Override
    public BaseViewHolder createBaseViewHolder(ViewGroup parent, int viewType) {
        return new CountryPickerViewHolder(parent);
    }

    @Override
    protected String getSortLetter(int position) {
        return getItem(position).getSortLetter();
    }
}
