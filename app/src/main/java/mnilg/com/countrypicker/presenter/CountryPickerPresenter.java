package mnilg.com.countrypicker.presenter;

import android.support.v4.app.FragmentManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import mnilg.com.countrypicker.base.PLRxPresenter;
import mnilg.com.countrypicker.model.Country;
import mnilg.com.countrypicker.utills.CountryPickerHelper;
import mnilg.com.countrypicker.utills.StringUtils;
import mnilg.com.countrypicker.view.CountryPickerView;

/**
 * CountryPickerPresenter
 * @author ligang
 * @date 16/10/21 16:27
 * @email gang.li@mengqitech.com
 * @description 选择国家
 */
public class CountryPickerPresenter extends PLRxPresenter<CountryPickerView> implements CountryPickerContract.Presenter,Comparator<Country> {
    private List<Country> allCountryList;
    private List<Country> searchedCountryList;

    public CountryPickerPresenter(FragmentManager fragmentManager, CountryPickerView view) {
        super(fragmentManager,view);
    }

    @Override
    public void prepareData() {
        allCountryList = CountryPickerHelper.getAllCountries();
        Collections.sort(allCountryList,this);
        searchedCountryList = new ArrayList<>();
        searchedCountryList.addAll(allCountryList);
        if(view != null) {
            view.refreshList(searchedCountryList);
        }
    }

    @Override
    public void search(String keywords) {
        searchedCountryList.clear();
        for (Country country : allCountryList) {
            if (country.getName().toLowerCase()
                    .contains(keywords.toLowerCase()) ||
                    StringUtils.getPinYin(country.getName()).toLowerCase().contains(keywords.toLowerCase())) {
                searchedCountryList.add(country);
            }
        }
        if(view != null) {
            view.refreshList(searchedCountryList);
        }
    }

    @Override
    public int compare(Country o1, Country o2) {
        int _compareInt = o1.getSortLetter().compareTo(o2.getSortLetter());
        if(_compareInt == 0) {
            _compareInt = o1.getName().compareTo(o2.getName());
        }
        return _compareInt;
    }
}
