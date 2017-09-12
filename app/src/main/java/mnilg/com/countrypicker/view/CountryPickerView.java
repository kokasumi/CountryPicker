package mnilg.com.countrypicker.view;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import mnilg.com.countrypicker.R;
import mnilg.com.countrypicker.adapter.CountryPickerRecyclerAdapter;
import mnilg.com.countrypicker.base.PLRootView;
import mnilg.com.countrypicker.model.Country;
import mnilg.com.countrypicker.presenter.CountryPickerContract;
import mnilg.com.countrypicker.utills.ActivityManagers;
import mnilg.com.countrypicker.widget.ClearEditText;
import mnilg.com.countrypicker.widget.SideBar;
import mnilg.com.countrypicker.widget.expandrecyclerview.SpaceItemDecoration;
import mnilg.com.countrypicker.widget.expandrecyclerview.StickyRecyclerHeadersDecoration;
import mnilg.com.countrypicker.widget.theme.ColorTextView;

/**
 * CountryPickerView
 * @author ligang
 * @date 16/10/21 16:23
 * @email gang.li@mengqitech.com
 * @description 国家选择
 */
public class CountryPickerView extends PLRootView<CountryPickerContract.Presenter> implements CountryPickerContract.View {
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.title_name)
    ColorTextView mCtvTitle;
    @BindView(R.id.et_search)
    ClearEditText mEtSearch;
    @BindView(R.id.rv_recycler_view)
    RecyclerView mRvCountryList;
    @BindView(R.id.tv_sidebar_dialog)
    AppCompatTextView mTvSidebarDialog;
    @BindView(R.id.view_quick_search_side_bar)
    SideBar mQuickSearchSidebar;
    private CountryPickerRecyclerAdapter mAdapter;

    public CountryPickerView(Context context) {
        super(context);
    }

    public CountryPickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CountryPickerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initUI() {
        super.initUI();
        mCtvTitle.setText("选择国家");
        mAdapter = new CountryPickerRecyclerAdapter();
        mRvCountryList.setAdapter(mAdapter);
        mRvCountryList.setLayoutManager(new LinearLayoutManager(mContext));
        //添加头部字母
        final StickyRecyclerHeadersDecoration _headersDecoration = new StickyRecyclerHeadersDecoration(mAdapter);
        mRvCountryList.addItemDecoration(_headersDecoration);
        mRvCountryList.addItemDecoration(new SpaceItemDecoration(mContext,R.dimen.dividing_line));
        mAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                _headersDecoration.invalidateHeaders();
            }
        });
        mQuickSearchSidebar.setTextView(mTvSidebarDialog);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mQuickSearchSidebar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                if(mAdapter != null) {
                    int position = mAdapter.getPositionForSection(s.charAt(0));
                    if (position != -1) {
                        mRvCountryList.scrollToPosition(position);
                    }
                }
            }
        });
    }

    @Override
    public void setPresenter(CountryPickerContract.Presenter presenter) {
        if(presenter == null)
            throw new NullPointerException();
        this.mPresenter = presenter;
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void toastMsg(String msg) {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.layout_search_sidebar_recycler_view;
    }

    @OnTextChanged(value = R.id.et_search,callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void onTextChanged(Editable s) {
        if(mPresenter != null) {
            mPresenter.search(s.toString());
        }
    }

    @OnClick(R.id.rl_back)
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                exit();
                break;
        }
    }

    private void exit() {
        ActivityManagers.getAppManager().finishActivity();
    }

    @Override
    public void refreshList(List<Country> t) {
        if(t == null)
            return;
        mAdapter.getList().clear();
        mAdapter.getList().addAll(t);
        mAdapter.notifyDataSetChanged();
    }
}
