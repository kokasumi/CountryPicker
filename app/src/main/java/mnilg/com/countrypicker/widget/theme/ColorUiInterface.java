package mnilg.com.countrypicker.widget.theme;

import android.content.res.Resources;
import android.view.View;

/**
 * 换肤接口
 * Created by chengli on 15/6/8.
 */
interface ColorUiInterface {


    View getView();

    void setTheme(Resources.Theme themeId);
}
