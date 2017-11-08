package com.cloud.xc.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cloud.xc.R;
import com.cloud.xc.model.MyAppInfo;
import com.cloud.xc.utils.ApkTool;

import java.util.ArrayList;
import java.util.List;

public class DeviceInfoActivity extends AppCompatActivity {
    private Handler mHandler = new Handler();
    private List<MyAppInfo> appInfos;
    private ProgressBar progress;
    private ListView lv_app_list;
    private AppAdapter mAppAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_info);
        lv_app_list = (ListView) findViewById(R.id.lv_app_list);
        progress = (ProgressBar) findViewById(R.id.progress);
        mAppAdapter = new AppAdapter();
        lv_app_list.setAdapter(mAppAdapter);
        progress.setVisibility(View.VISIBLE);
        initAppList();
    }


    private void initAppList() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                //扫描得到APP列表
                appInfos = ApkTool.scanLocalInstallAppList(DeviceInfoActivity.this.getPackageManager());
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mAppAdapter.setData(appInfos);
                        progress.setVisibility(View.GONE);
                    }
                });
            }
        }.start();
    }

    class AppAdapter extends BaseAdapter {

        List<MyAppInfo> myAppInfos = new ArrayList<MyAppInfo>();

        public void setData(List<MyAppInfo> myAppInfos) {
            this.myAppInfos = myAppInfos;
            notifyDataSetChanged();
        }

        public List<MyAppInfo> getData() {
            return myAppInfos;
        }

        @Override
        public int getCount() {
            if (myAppInfos != null && myAppInfos.size() > 0) {
                return myAppInfos.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            if (myAppInfos != null && myAppInfos.size() > 0) {
                return myAppInfos.get(position);
            }
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder mViewHolder;
            MyAppInfo myAppInfo = myAppInfos.get(position);
            if (convertView == null) {
                mViewHolder = new ViewHolder();
                convertView = LayoutInflater.from(getBaseContext()).inflate(R.layout.item_app_info, null);
                mViewHolder.iv_app_icon = (ImageView) convertView.findViewById(R.id.iv_app_icon);
                mViewHolder.tx_app_name = (TextView) convertView.findViewById(R.id.tv_app_name);
                convertView.setTag(mViewHolder);
            } else {
                mViewHolder = (ViewHolder) convertView.getTag();
            }
            mViewHolder.iv_app_icon.setImageDrawable(myAppInfo.getImage());
            mViewHolder.tx_app_name.setText(myAppInfo.getAppName());
            return convertView;
        }

        class ViewHolder {

            ImageView iv_app_icon;
            TextView tx_app_name;
        }
    }
}
