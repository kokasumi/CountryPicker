package mnilg.com.countrypicker.utills;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * ScreenUtil
 * @author ligang
 * @date 2016/11/16 17:35
 * @email gang.li@mengqitech.com
 * @description
 */
public class ScreenUtil {
    /**
     * dpתpx
     * @param ctx
     * @param dpValue
     * @return
     */
    public static int dip2px(Context ctx, float dpValue) {
        final float scale = ctx.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    /**
     * pxתdp
     * @param ctx
     * @param pxValue
     * @return
     */
    public static int px2dip(Context ctx, float pxValue) {
        final float scale = ctx.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * sp2px
     * @param ctx
     * @param sp
     * @return
     */
    public static float sp2px(Context ctx,int sp) {
        float fontScale = ctx.getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * fontScale + 0.5f);
    }

    /**
     * screenWidth
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * screenHeight
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    /**
     * 获取当前设备DensityDpi
     * @param context
     * @return
     */
    public static int getDensityDpi(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        return metrics.densityDpi;
    }
}
