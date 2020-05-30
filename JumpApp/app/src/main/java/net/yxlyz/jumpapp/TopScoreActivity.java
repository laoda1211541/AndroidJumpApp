package net.yxlyz.jumpapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
/**
 * @Author Saraph1nes
 * @Date 2020/5/21 14:30
 * @Version 1.0
 */
public class TopScoreActivity extends AppCompatActivity {


    ListView lv;
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            List<User> list= (List<User>) msg.obj;
            if(list != null){
                MyAdapter myAdapter = new MyAdapter(list);
                lv.setAdapter((ListAdapter) myAdapter);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);
        lv = findViewById(R.id.ranklv);
        getAll();
    }

    void getAll() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection httpURLConnection = null;
                try {
                    URL url = new URL(Global.url +"queryScore");
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setReadTimeout(10000);
                    httpURLConnection.setConnectTimeout(10000);
                    httpURLConnection.setDoOutput(true);

                    if(httpURLConnection.getResponseCode() == 200){
                        InputStream in = httpURLConnection.getInputStream();
                        byte[]  buffer = new byte[1024];
                        int len = 0;
                        String str = "";
                        while ((len = in.read(buffer)) != -1){
                            str += new String(buffer,0,len);
                        }
                        in.close();
                        Gson gson = new Gson();
                        Type listType = new TypeToken<List<User>>(){}.getType();
                        List<User> list = gson.fromJson(str,listType);

                        Message message =handler.obtainMessage();
                        message.obj = list;
                        handler.sendMessage(message);

                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                }
            }
        }).start();
    }

    public class MyAdapter extends BaseAdapter {
        List<User> list;
        ListView listview;

        public MyAdapter(List<User> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (listview == null) {
                listview = (ListView) viewGroup;
            }
            ViewHolder holder = null;
            if (view == null) {
                view=View.inflate(TopScoreActivity.this,R.layout.layout,null);
                holder =  new ViewHolder();
                holder.tv_name = view.findViewById(R.id.tv_name);
                holder.tv_score = view.findViewById(R.id.tv_score);
                viewGroup.setTag(holder);
            } else {
                holder = (ViewHolder) viewGroup.getTag();
            }
            User user = list.get(i);
            holder.tv_name.setText(user.getUname());
            holder.tv_score.setText(user.getScore());
            return view;
        }
        class ViewHolder {
            TextView tv_score;
            TextView tv_name;
        }
    }
}
