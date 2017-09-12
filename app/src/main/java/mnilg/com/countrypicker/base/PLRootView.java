package mnilg.com.countrypicker.base;

import android.content.Context;
import android.os.Looper;
import android.util.AttributeSet;

import mnilg.com.countrypicker.R;
import mnilg.com.countrypicker.widget.CustomProgressDialog;

/**
 * PLRootView
 * @author ligang
 * @date 16/10/24 16:44
 * @email gang.li@mengqitech.com
 * @description
 */
public abstract class PLRootView<T extends BasePresenter> extends RootView {
    protected T mPresenter;
    protected CustomProgressDialog mProgressDialog = null;

    public PLRootView(Context context) {
        super(context);
    }

    public PLRootView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PLRootView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        dismissProgress();
    }

    /**
     * 显示ProgressDialog
     */
    protected void showProgress() {
        if(mProgressDialog == null) {
            mProgressDialog = new CustomProgressDialog(getContext(), R.drawable.my_anim);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setCanceledOnTouchOutside(false);
        }
        if(!mProgressDialog.isShowing() && isActive()) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mProgressDialog.show();
                }
            });
        }
    }

    /**
     * 取消显示ProgressDialog
     */
    protected void dismissProgress() {
        if(mProgressDialog != null && mProgressDialog.isShowing() && isActive()) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mProgressDialog.dismiss();
                }
            });
        }
    }

    public void isLoading(boolean isLoading) {
        if(isLoading) {
            showProgress();
        }else {
            dismissProgress();
        }
    }

    protected void runOnUiThread(final Runnable runnable) {
        if(Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        }else {
            post(runnable);
        }
    }
}
