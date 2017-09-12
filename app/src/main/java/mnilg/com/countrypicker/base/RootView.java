package mnilg.com.countrypicker.base;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * RootView
 * @author ligang
 * @date 16/10/10 17:03
 * @email gang.li@mengqitech.com
 * @description 基类View
 */
public abstract class RootView extends LinearLayout {
    /**
     * 是否被销毁
     */
    private boolean mActive;
    protected Context mContext;
    private Unbinder unbinder;

    protected RootView(Context context) {
        super(context);
        init();
    }

    protected RootView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    protected RootView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mContext = getContext();
        inflate(mContext, getLayoutResource(), this);
        unbinder = ButterKnife.bind(this);
        initUI();
        initEvent();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mActive = true;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mActive = false;
        unbinder.unbind();
        mContext = null;
    }

    public boolean isActive() {
        return mActive;
    }

    protected void initUI() {
    }

    protected void initEvent() {

    }

    protected abstract int getLayoutResource();
}
