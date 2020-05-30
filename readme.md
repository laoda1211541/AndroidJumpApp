# 安卓App跳一跳小游戏（服务器实现登录）

> 学校课设需要完成一个App，我决定仿写跳一跳，由于测试时间少，可能有少许bug，不过不影响正常游戏

> 开发环境：
>
> win10
>
> tomcat9.0.31
>
> mysql8.0.17
>
> idea2019.3
>
> AndroidStdio 3.6.1

> 测试环境：
>
> android 10

### 一、建数据库

数据库（jumpapp），建表（User），字段名如下，sql文件导入也行

![image-20200530142015186](https://github.com/Saraph1nes/AndroidJumpApp/blob/master/img/image-20200530142015186.png)

### 二、导入jumpAppServer

进idea导入jumpAppServer，使用web+maven，然后配置tomcat

### 三、修改db.properties

修改db.properties，账户密码改为你自己的（注意：MySQL8以上的driver包名要加上cj，如图）

![image-20200530142225164](https://github.com/Saraph1nes/AndroidJumpApp/blob/master/img/image-20200530142225164.png)

### 四、查看DBConfig类下文件路径有无问题

查看DBConfig类下文件路径有无问题，配置好了可以单元测试一下

![image-20200530142641201](https://github.com/Saraph1nes/AndroidJumpApp/blob/master/img/image-20200530142641201.png)

### 五、运行服务器

出现如图界面和地址即服务器配置完毕

![image-20200530142428686](https://github.com/Saraph1nes/AndroidJumpApp/blob/master/img/image-20200530142428686.png)

### 六、导入安卓jumpApp

在Global中修改你自己的ip，cmd下

```
ipconfig -all
```

![image-20200530142921662](https://github.com/Saraph1nes/AndroidJumpApp/blob/master/img/image-20200530142921662.png)

### 七、运行项目

### 八、演示图,视频在里面

![image-20200530145958765](https://github.com/Saraph1nes/AndroidJumpApp/blob/master/img/image-20200530145958765.png)

![image-20200530150014801](https://github.com/Saraph1nes/AndroidJumpApp/blob/master/img/image-20200530150014801.png)

![image-20200530150030215](https://github.com/Saraph1nes/AndroidJumpApp/blob/master/img/image-20200530150030215.png)

![image-20200530150039355](https://github.com/Saraph1nes/AndroidJumpApp/blob/master/img/image-20200530150039355.png)

![image-20200530150055346](https://github.com/Saraph1nes/AndroidJumpApp/blob/master/img/image-20200530150055346.png)
