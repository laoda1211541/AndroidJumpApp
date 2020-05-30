package com.jump.pojo;

/**
 * @Author Saraph1nes
 * @Date 2020/5/21 14:29
 * @Version 1.0
 */
public class User {
    private int uid;
    private String uname;
    private String pwd;
    private String score;

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", pwd='" + pwd + '\'' +
                ", score='" + score + '\'' +
                '}';
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public User() {
    }

    public User(int uid, String uname, String pwd, String score) {
        this.uid = uid;
        this.uname = uname;
        this.pwd = pwd;
        this.score = score;
    }
}
