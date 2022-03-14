package com.compe.competition_demo1.service;
import com.compe.competition_demo1.cdata.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

//@ApiModel(description = "返回结果")
@Service
public class UserServiceImpt implements UserService {
    //    @ApiModelProperty("状态码 0:成功")
//    private int code;
//    @ApiModelProperty("消息")
//    private String message;
//    @ApiModelProperty("返回数据")
//    private Object data;
    @Autowired
//    private static String driver = "com.mysql.cj.jdbc.Driver";
//    private static String url = "jdbc:mysql://localhost/mytestdb?useSSL=false&useUnicode=ture&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
//    private static String user = "root", password = "123456";
//
//    public static void main(String[] args) {
//        String user_password = null;
//        String user_in= null;
////        List<User> user = LoginUser(user_in,user_password);
////        for (User user : user) {
////            System.out.println(user);
////        }
//    }

    private JdbcTemplate jdbcTemplate;

    //登录用户(输入name，password，返回相关数据)
    @Override
    public User LoginUser(String user_in, String user_password){
//        Connection conn = null;
//        ResultSet rs = null;
//        PreparedStatement ps = null;
//
//        try {
//            // 加载注册驱动
//            try {
//                Class.forName(driver);
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//
//            //2.获得链接 Connection
//            conn = DriverManager.getConnection(url, user, password);
//
            String sql = "select * from user u where u.user_name=user_in|u.user_phone=user_in";
//            ps = conn.prepareStatement(sql);
//            rs = ps.executeQuery();// 这里不需要再传入SQL语句
//            user = String.valueOf(new ArrayList<User>());
//            sql = sql.conn.createStatement();// 插入之前先在数据库里面查
//            rs = sql.sql.executeQuery(sql);//返回查询结果
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<User>(User.class));
        return null;
    }

    //注册用户(name,password,e-mail,phone,picture)
    public boolean registerUser(User user) {
        String sql="insert into User(user_id,user_name,user_password,user_email,user_phone,user_picture) values(null,?,?,null,?,null)";
        jdbcTemplate.update(sql,user.getUser_name(),user.getUser_password(),user.getUser_phone());
        return true;
    }
    //修改用户信息
    //身份
    @Override
    public boolean changeUser_identity(User user) {
        String sql="update User set user_identity = ? where user_id = ?";
        jdbcTemplate.update(sql,user.getUser_identity());
        return true;
    }
    //用户名
    public boolean changeUser_name(User user) {
        String sql="update User set user_name = ? where user_id = ?";
        jdbcTemplate.update(sql,user.getUser_name());
        return true;
    }
    //密码
    public boolean changeUser_password(User user) {
        String sql="update User set user_password = ? where user_id = ?";
        jdbcTemplate.update(sql,user.getUser_password());
        return true;
    }
    //邮箱
    public boolean changeUser_mail(User user) {
        String sql="update User set user_mail = ? where user_id = ?";
        jdbcTemplate.update(sql,user.getUser_email());
        return true;
    }
    //电话
    public boolean changeUser_phone(User user) {
        String sql="update User set user_phone = ? where user_id = ?";
        jdbcTemplate.update(sql,user.getUser_phone());
        return true;
    }
    //头像
    public boolean changeUser_picture(User user) {
        String sql="update User set user_picture = ? where user_id = ?";
        jdbcTemplate.update(sql,user.getUser_picture());
        return true;
    }
}
