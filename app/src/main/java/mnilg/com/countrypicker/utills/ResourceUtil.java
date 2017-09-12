package mnilg.com.countrypicker.utills;

import android.util.Log;

import java.lang.reflect.Field;

import mnilg.com.countrypicker.R;

/**
 * ResourceUtil
 * @author ligang
 * @date 16/10/21 16:20
 * @email gang.li@mengqitech.com
 * @description 资源Util
 */
public class ResourceUtil {
    /**
     * 获取资源id
     * @param drawableName
     * @return
     */
    public static int getResId(String drawableName) {
        try {
            Class<R.drawable> res = R.drawable.class;
            Field field = res.getField(drawableName);
            int drawableId = field.getInt(null);
            return drawableId;
        } catch (Exception e) {
            Log.e("CountryCodePicker", "Failure to get drawable id.", e);
        }
        return -1;
    }
}
