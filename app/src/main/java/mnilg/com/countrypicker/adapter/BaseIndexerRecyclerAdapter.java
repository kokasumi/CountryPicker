package mnilg.com.countrypicker.adapter;

import android.support.v7.widget.AppCompatTextView;
import android.view.ViewGroup;
import android.widget.SectionIndexer;

import mnilg.com.countrypicker.R;
import mnilg.com.countrypicker.widget.expandrecyclerview.StickyRecyclerHeadersAdapter;

/**
 * BaseIndexerRecyclerAdapter
 * @author ligang
 * @date 2016/11/10 16:56
 * @email gang.li@mengqitech.com
 * @description
 */
public abstract class BaseIndexerRecyclerAdapter<T> extends BaseRecyclerAdapter<T> implements SectionIndexer,StickyRecyclerHeadersAdapter<BaseViewHolder<String>> {
    @Override
    public Object[] getSections() {
        return new Object[0];
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        for (int i = 0; i < getItemCount(); i++) {
            String sortStr = getSortLetter(i);
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == sectionIndex) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getSectionForPosition(int position) {
        return getSortLetter(position).charAt(0);
    }

    @Override
    public long getHeaderId(int position) {
        return getSectionForPosition(position);
    }

    @Override
    public BaseViewHolder<String> onCreateHeaderViewHolder(ViewGroup parent) {
        return new BaseViewHolder<String>(parent, R.layout.layout_sort_letter_header) {};
    }

    @Override
    public void onBindHeaderViewHolder(BaseViewHolder<String> holder, int position) {
        AppCompatTextView _textView = (AppCompatTextView) holder.itemView;
        String _sortLetter = String.valueOf(getSortLetter(position).charAt(0));
        _textView.setText(_sortLetter);
    }

    protected abstract String getSortLetter(int position);
}
