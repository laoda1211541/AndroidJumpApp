package net.yxlyz.jumpapp;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
/**
 * @Author Saraph1nes
 * @Date 2020/5/21 14:30
 * @Version 1.0
 */
public class LoginActivity extends AppCompatActivity {

    public static User CURRENT_USER;
    private EditText loginAccount_etext;
    private EditText loginPassword_etext;
    String uname = "" , pwd= "";
    User user;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            user = (User) msg.obj;
            if(user != null){
                SPSave.savaUserInfo(LoginActivity.this,user.getUname(),user.getPwd());
                //显示意图，通过构造方法Intent(当前Activity对象（this），目标Activity对象)实现
                Intent intent = new Intent(LoginActivity.this,WelcomeActivity.class);
                //Intent提供了一系列重载的intent.putExtra(String name,String value)方法，通过该方法可以将传递的数据暂存带Intent中
                intent.putExtra("user",user);
                startActivityForResult(intent,103);
                //存储到全局User
                LoginActivity.CURRENT_USER = user;
                Toast.makeText(LoginActivity.this,"登录成功", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(LoginActivity.this,"用户名或密码错误!", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginAccount_etext = findViewById(R.id.uname);
        loginPassword_etext = findViewById(R.id.pwd);
        Map<String ,String> userInfo = SPSave.getUserInfo(this);
        loginAccount_etext.setText(userInfo.get("uname"));
        loginPassword_etext.setText(userInfo.get("pwd"));
    }

    public  void  register(View view){
        System.out.println("按钮点击");
        uname = loginAccount_etext.getText().toString().trim();
        pwd = loginPassword_etext.getText().toString().trim();
        //判断是否为空
        if (!uname.equals("")&&!pwd.equals("")){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    HttpURLConnection httpURLConnection = null;
                    try {
                        URL url = new URL(Global.url +"register");
                        httpURLConnection = (HttpURLConnection) url.openConnection();
                        httpURLConnection.setRequestMethod("POST");
                        httpURLConnection.setReadTimeout(10000);
                        httpURLConnection.setConnectTimeout(10000);
                        httpURLConnection.setDoOutput(true);
                        String userdata = "uname="+uname+"&pwd="+pwd;
                        OutputStream out = httpURLConnection.getOutputStream();
                        out.write(userdata.getBytes());
                        out.flush();
                        out.close();
                        System.out.println("httpURLConnection.getResponseCode()====="+httpURLConnection.getResponseCode());
                        if(httpURLConnection.getResponseCode() == 200){

                            InputStream in = httpURLConnection.getInputStream();
                            byte[]  buffer = new byte[1024];
                            int len = 0;
                            String str = "";
                            while ((len = in.read(buffer)) != -1){
                                str += new String(buffer,0,len);
                            }
                            in.close();
                            System.out.println("str========="+str);
                            if (str.equals("true")){
                                Looper.prepare();
                                Toast.makeText(LoginActivity.this,"注册成功", Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            }else {
                                Looper.prepare();
                                Toast.makeText(LoginActivity.this,"注册失败,未连接或账号已存在", Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            }
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
        }else {
            Toast.makeText(this,"请输入用户名和密码", Toast.LENGTH_SHORT).show();
        }
    }

    public  void  login(View view){
        uname = loginAccount_etext.getText().toString().trim();
        pwd = loginPassword_etext.getText().toString().trim();
        //判断是否为空
        if (!uname.equals("")&&!pwd.equals("")){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    HttpURLConnection httpURLConnection = null;
                    try {
                        URL url = new URL(Global.url +"login");
                        httpURLConnection = (HttpURLConnection) url.openConnection();
                        httpURLConnection.setRequestMethod("POST");
                        httpURLConnection.setReadTimeout(10000);
                        httpURLConnection.setConnectTimeout(10000);
                        httpURLConnection.setDoOutput(true);
                        String userdata = "uname="+uname+"&pwd="+pwd;
                        OutputStream out = httpURLConnection.getOutputStream();
                        out.write(userdata.getBytes());
                        out.flush();
                        out.close();
                        System.out.println("httpURLConnection.getResponseCode()====="+httpURLConnection.getResponseCode());
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
                            User user = gson.fromJson(str,User.class);
                            Message message =handler.obtainMessage();
                            message.obj = user;
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
        }else {
            Toast.makeText(this,"请输入用户名和密码", Toast.LENGTH_SHORT).show();
        }
    }




    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("requestcode",requestCode +"");
        Log.i("resultcode",resultCode +"");
        //清空pwd
        loginPassword_etext.setText("");
    }


}
