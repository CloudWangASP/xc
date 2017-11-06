package com.cloud.xc.activity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cloud.xc.R;
import com.cloud.xc.view.async.AsyncExpandableListView;
import com.cloud.xc.view.async.AsyncExpandableListViewCallbacks;
import com.cloud.xc.view.async.AsyncHeaderViewHolder;
import com.cloud.xc.view.async.CollectionView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class ExpandableListActivity extends AppCompatActivity implements AsyncExpandableListViewCallbacks<String, String> {

    private AsyncExpandableListView<String, String> mAsyncExpandableListView;
    private CollectionView.Inventory<String, String> inventory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_demo);
        mAsyncExpandableListView = (AsyncExpandableListView) findViewById(R.id.asyncExpandableCollectionView);
        mAsyncExpandableListView.setCallbacks(this);
        inventory = new CollectionView.Inventory<>();

        for (int i = 0; i < 4; i++) {
            CollectionView.InventoryGroup<String, String> group = inventory.newGroup(i);
            group.setHeaderItem("查看趋势");
        }
        mAsyncExpandableListView.updateInventory(inventory);
    }


    @Override
    public void onStartLoadingGroup(int groupOrdinal) {
        new LoadDataTask(groupOrdinal, mAsyncExpandableListView).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }


    private static class LoadDataTask extends AsyncTask<Void, Void, Void> {

        private final int mGroupOrdinal;
        private WeakReference<AsyncExpandableListView<String, String>> listviewRef = null;

        public LoadDataTask(int groupOrdinal, AsyncExpandableListView<String, String> listview) {
            mGroupOrdinal = groupOrdinal;
            listviewRef = new WeakReference<>(listview);
        }

        @Override
        protected Void doInBackground(Void... params) {
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            List<String> items = new ArrayList<>();
            items.add("hello world");
            if (listviewRef.get() != null) {
                listviewRef.get().onFinishLoadingGroup(mGroupOrdinal, items);
            }
        }

    }

    @Override
    public AsyncHeaderViewHolder newCollectionHeaderView(Context context, int groupOrdinal, ViewGroup parent) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.header_row_item_async, parent, false);
        return new MyHeaderViewHolder(v, groupOrdinal, mAsyncExpandableListView);
    }

    @Override
    public RecyclerView.ViewHolder newCollectionItemView(Context context, int groupOrdinal, ViewGroup parent) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.text_row_item_async, parent, false);
        return new NewsItemHolder(v);
    }

    @Override
    public void bindCollectionHeaderView(Context context, AsyncHeaderViewHolder holder, int groupOrdinal, String headerItem) {
        MyHeaderViewHolder myHeaderViewHolder = (MyHeaderViewHolder) holder;
        myHeaderViewHolder.getTextView().setText(headerItem);
    }

    @Override
    public void bindCollectionItemView(Context context, RecyclerView.ViewHolder holder, int groupOrdinal, String item) {
        NewsItemHolder newsItemHolder = (NewsItemHolder) holder;
        newsItemHolder.getTextViewTitle().setText(item);
    }

    //列表holder
    public static class MyHeaderViewHolder extends AsyncHeaderViewHolder implements AsyncExpandableListView.OnGroupStateChangeListener {

        private TextView tip;
        private View devideLine;

        public MyHeaderViewHolder(View itemView, int groupOrdinal, AsyncExpandableListView asyncExpandableListView) {
            super(itemView, groupOrdinal, asyncExpandableListView);
            tip = (TextView) itemView.findViewById(R.id.tip);
            devideLine = itemView.findViewById(R.id.devideLine);
            if (groupOrdinal == 3) {
                devideLine.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onGroupStartExpending() {

        }

        @Override
        public void onGroupExpanded() {
            tip.setText("收起趋势");
            devideLine.setVisibility(View.INVISIBLE);
        }

        @Override
        public void onGroupCollapsed(int groupId) {
            tip.setText("查看趋势");
            if (groupId != 3) {
                devideLine.setVisibility(View.VISIBLE);
            }
        }

        public TextView getTextView() {
            return tip;
        }
    }

    //拓展holder
    public static class NewsItemHolder extends RecyclerView.ViewHolder {
        private final TextView text;

        public NewsItemHolder(View v) {
            super(v);
            text = (TextView) v.findViewById(R.id.text);
        }

        public TextView getTextViewTitle() {
            return text;
        }
    }
}
