package mnilg.com.countrypicker.utills;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import mnilg.com.countrypicker.R;
import mnilg.com.countrypicker.base.App;
import mnilg.com.countrypicker.model.Country;

/**
 * @author Li Gang
 *         Date:31/03/2016 16:00
 *         Email:gang.li@mengqitech.com
 */
public class CountryPickerHelper {
    private static final CountryPickerHelper ourInstance = new CountryPickerHelper();
    private static List<Country> allCountriesList;

    public static CountryPickerHelper getInstance() {
        return ourInstance;
    }

    private CountryPickerHelper() {
    }

    public static List<Country> getAllCountries() {
        if (allCountriesList == null) {
            try {
                allCountriesList = new ArrayList<>();
                String allCountriesCode = readEncodedJsonString(App.getContext());
                JSONArray countrArray = new JSONArray(allCountriesCode);
                for (int i = 0; i < countrArray.length(); i++) {
                    JSONObject jsonObject = countrArray.getJSONObject(i);
                    String countryName = jsonObject.getString("name");
                    String countryDialCode = jsonObject.getString("dial_code");
                    String countryCode = jsonObject.getString("code");
                    Country country = new Country(countryCode,countryName,countryDialCode);
                    allCountriesList.add(country);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return allCountriesList;
    }

    private static String readEncodedJsonString(Context context)
            throws java.io.IOException {
        String base64 = context.getResources().getString(R.string.countries_code);
        byte[] data = Base64.decode(base64, Base64.DEFAULT);
        return new String(data, "UTF-8");
    }

    /**
     * 根据dialogCode获取相应的Country变量
     * @param dialogCode
     * @return
     */
    public static Country getCountry(String dialogCode) {
        if(TextUtils.isEmpty(dialogCode)) {
            return null;
        }
        if(allCountriesList == null) {
            getAllCountries();
        }
        for(Country country : allCountriesList) {
            if(dialogCode.equals(country.getDialCode())) {
                return country;
            }
        }
        return null;
    }

    /**
     * 根据Code查找相应的Country变量
     * @param code
     * @return
     */
    public static Country getCountryByCode(String code) {
        if(TextUtils.isEmpty(code)) {
            return null;
        }
        if(allCountriesList == null) {
            getAllCountries();
        }
        for(Country country : allCountriesList) {
            if(code.equals(country.getCode())) {
                return country;
            }
        }
        return null;
    }
}
