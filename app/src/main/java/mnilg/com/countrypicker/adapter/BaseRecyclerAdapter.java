package mnilg.com.countrypicker.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * BaseRecyclerAdapter
 * @author ligang
 * @date 2016/11/10 15:24
 * @email gang.li@mengqitech.com
 * @description
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseViewHolder<T>>{
    private final List<T> list;

    public BaseRecyclerAdapter() {
        this(new ArrayList<T>());
    }

    public BaseRecyclerAdapter(T[] list) {
        this(Arrays.asList(list));
    }

    public BaseRecyclerAdapter(List<T> list) {
        this.list = list;
    }

    @Override
    public final BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder viewHolder = createBaseViewHolder(parent,viewType);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.itemView.setId(position);
        holder.setData(getItem(position));
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public T getItem(int position) {
        return list != null && position < getItemCount() && position > -1 ? list.get(position) : null;
    }

    public List<T> getList() {
        return list;
    }

    protected abstract BaseViewHolder createBaseViewHolder(ViewGroup parent, int viewType);

    public void setList(List<T> data) {
        if(data == null || data.size() <= 0)
            return;
        list.clear();
        list.addAll(data);
        notifyDataSetChanged();
    }
}
