package mnilg.com.countrypicker.base;

import android.app.Application;
import android.content.Context;

/**
 * Created time:2017/9/12
 * Created by:ligang
 * Description:
 */

public class App extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
