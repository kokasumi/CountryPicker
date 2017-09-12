package mnilg.com.countrypicker.base;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;


import butterknife.ButterKnife;
import butterknife.Unbinder;
import mnilg.com.countrypicker.R;
import mnilg.com.countrypicker.utills.ScreenUtil;
import mnilg.com.countrypicker.widget.theme.ColorRelativeLayout;

/**
 * BaseActivity
 * @author ligang
 * @date 16/10/8 18:15
 * @email gang.li@mengqitech.com
 * @description 项目基类Activity
 */
public abstract class BaseActivity<T extends BasePresenter> extends FragmentActivity implements EmptyView {
    boolean isConnection = false;
    protected T mPresenter;
    private Unbinder unbinder;
    private boolean mIsSoftKeyboardShowing;
    protected int screenHeight;
    protected int screenWidth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTranslucentStatus(true);
        setContentView(getLayoutResource());
        unbinder = ButterKnife.bind(this);
        intentValue(savedInstanceState);
        mPresenter = createPresenter();
        if (mPresenter != null)
            mPresenter.attachView(this);
        initView();
        initEvent();
        fetchData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        setTitleHeight(getRootView(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachView();
        if (unbinder != null)
            unbinder.unbind();
    }

    protected abstract int getLayoutResource();

    protected abstract T createPresenter();

    protected void intentValue(Bundle savedInstanceState) {

    }

    protected void initView() {

    }

    protected void initEvent() {

    }

    /**
     * 获取数据
     */
    protected void fetchData() {

    }

    /**
     * 设置沉浸式
     *
     * @param on
     */
    private void setTranslucentStatus(boolean on) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if (on) {
                winParams.flags |= bits;
            } else {
                winParams.flags &= ~bits;
            }
            win.setAttributes(winParams);
        }
    }

    private void setTitleHeight(View view) {
        if (view != null) {
            ColorRelativeLayout title = (ColorRelativeLayout) view.findViewById(R.id.title);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                if (title != null) {
                    ViewGroup.LayoutParams lp = title.getLayoutParams();
                    lp.height = ScreenUtil.dip2px(this, 48);
                    title.setLayoutParams(lp);
                    title.setPadding(0, 0, 0, 0);
                }
            }
        }
    }

    private View getRootView(Activity context) {
        return ((ViewGroup) context.findViewById(android.R.id.content)).getChildAt(0);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            if (mIsSoftKeyboardShowing) {
                InputMethodManager loInputMgr = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                try {
                    loInputMgr.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }catch (NullPointerException e) {

                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        return getWindow().superDispatchTouchEvent(ev) || onTouchEvent(ev);
    }
}
