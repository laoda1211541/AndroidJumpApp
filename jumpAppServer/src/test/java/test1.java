import com.alibaba.fastjson.JSON;
import com.jump.dao.impl.UserDaoImpl;
import com.jump.pojo.User;
import com.jump.service.UserService;
import com.jump.service.impl.UserServiceImpl;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Author Saraph1nes
 * @Date 2020/5/21 14:43
 * @Version 1.0
 */
public class test1 {
    public static void main(String[] args) {
//        UserDaoImpl userDao = new UserDaoImpl();
//        List<User> users = userDao.queryAllUserScore();
//        users.sort(new Comparator<User>() {
//            @Override
//            public int compare(User o1, User o2) {
//                Integer i1 = Integer.valueOf(o1.getScore());
//                Integer i2 = Integer.valueOf(o2.getScore());
//                return i2.compareTo(i1);
//            }
//        });
//        for (User user : users) {
//            System.out.println("姓名:"+user.getUname()+"\t分数:"+user.getScore());
//            System.out.println("------------------------------");
//        }



        UserDaoImpl userDao = new UserDaoImpl();
        boolean test1 = userDao.addScore("test1555", "12");
        System.out.println(test1);
    }


}
