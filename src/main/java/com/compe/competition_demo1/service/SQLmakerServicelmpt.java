package com.compe.competition_demo1.service;

import com.compe.competition_demo1.service.SQLmakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class SQLmakerServicelmpt implements SQLmakerService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void createall() throws SQLException {
        //user,项目管理员
        for (int i = 0; i < 200; i++) {
            // 数据库语句
            StringBuilder sql1 = new StringBuilder(
                    "insert into user(user_identity,user_name,user_password,user_email,user_phone,user_num,user_picture) values('");
            String name1 = randomChar(3);
            sql1.append(randomNumStr(0,0)).append("','")
                    .append(name1).append("','")     // 用户名
                    .append(randomNumStr(100000, 999999)).append("','")           //密码
                    .append(randomEmail(8)).append("','")                 //email
                    .append(getTel()).append("','")               //电话
                    .append(randomNumStr(11111111, 99999999)).append("','")       //学号
                    .append("null").append("');\n");
            jdbcTemplate.update(String.valueOf(sql1));
        }


        //registration
        for (int i = 0; i < 20; i++) {
            // 数据库语句
            StringBuilder sql2 = new StringBuilder(
                    "insert into competition(com_date,com_mainname,com_manager,com_subname,com_level,com_major,com_category,com_information,sign_up_start,sign_up_end,preliminary_start,preliminary_end,repecharge_start,repecharge_end,finals_start,finals_end,attachment1,attachment2,attachment3,com_status,com_schedule,sign_num,award1,award2,award3,award_other,com_check) values('");
            String name2 = randomChar(5);
            String name3 = randomChar(10);
            sql2.append(randomDate(2020,2022)).append("','")     //发布时间
                    .append(name2).append("','")                 // 竞赛主标题
                    .append(identity()).append("','")   //负责人id
                    .append(name2).append("','")                 // 竞赛副标题
                    .append(comlevel()).append("','")            //竞赛级别
                    .append(commajor()).append("','")            //专业
                    .append(comlcate()).append("','")            //竞赛类别
                    .append(name3).append("','")                 //竞赛信息
                    .append(randomDate(2015,2015)).append("','")//报名开始
                    .append(randomDate(2016,2016)).append("','")//报名结束
                    .append(randomDate(2017,2017)).append("','")//初赛开始
                    .append(randomDate(2018,2018)).append("','")//初赛结束
                    .append(randomDate(2019,2019)).append("','")//复赛开始
                    .append(randomDate(2020,2020)).append("','")//复赛结束
                    .append(randomDate(2021,2021)).append("','")//决赛开始
                    .append(randomDate(2022,2022)).append("','")//决赛结束
                    .append("null").append("','").append("null").append("','").append("null").append("','")  //附件123
                    .append(randomNumStr(0,2)).append("','")//竞赛状态
                    .append(randomNumStr(0,1)).append("','")//奖项进度
                    .append(randomNumStr(50,100)).append("','")//赛事报名
                    .append(randomNumStr(1,5)).append("','")//一等奖
                    .append(randomNumStr(1,10)).append("','")//二
                    .append(randomNumStr(1,20)).append("','")//三
                    .append(randomNumStr(1,50)).append("','")//其他
                    .append(randomNumStr(0,2))//审核结果
                    .append("');\n");
            jdbcTemplate.update(String.valueOf(sql2));
        }

        //registration_management表
        for (int i = 0; i < 200; i++) {
            // 数据库语句
            StringBuilder sql3 = new StringBuilder(
                    "insert into registration_management(user_id,com_id,declaration,opening_report,interim_report,closing_report,award,team_name,date) values('");
            String name1 = randomChar(5);
            sql3.append(identity()).append("','")   //user_id
                    .append(randomNumStr(1,20)).append("','")   //com_id
                    .append("null").append("','")//申报书
                    .append("null").append("','")//开题
                    .append("null").append("','")//中期
                    .append("null").append("','")//结题
                    .append(randomNumStr(0,4)).append("','") //获奖
                    .append(name1).append("','")     // 队伍名
                    .append(randomDate(2020,2022))       //报名时间
                    .append("');\n");
            jdbcTemplate.update(String.valueOf(sql3));
        }

        //news
        for (int i = 0; i < 50; i++) {
            // 数据库语句
            StringBuilder sql4 = new StringBuilder(
                    "insert into news(title,essay,author,date,news_check) values('");
            String name1 = randomChar(5);
            String name2 = randomChar(50);
            sql4.append(name1).append("','")     // 新闻标题
                    .append(name2).append("','")     // 新闻正文
                    .append(identity()).append("','")      //作者id
                    .append(randomDate(2020,2022)).append("','")       //发布日期
                    .append(randomNumStr(0,2)).append("');\n");             //审核结果
            jdbcTemplate.update(String.valueOf(sql4));
        }

        //announcement
        for (int i = 0; i < 50; i++) {
            // 数据库语句
            StringBuilder sql5 = new StringBuilder(
                    "insert into announcement(author,date,essay,title,inform_check) values('");
            String name1 = randomChar(5);
            String name2 = randomChar(50);
            sql5.append(identity()).append("','")      //公告发布者id
                    .append(randomDate(2020,2022)).append("','")       //发布日期
                    .append(name2).append("','")     // 公告正文
                    .append(name1).append("','")     // 公告标题
                    .append(randomNumStr(0,2)).append("');\n");             //审核结果
            jdbcTemplate.update(String.valueOf(sql5));
        }
    }

    @Override
    public void deleteall() throws SQLException {
        String sql6 = "delete from user";
        jdbcTemplate.update(sql6);
        String sql7 = "delete from competition";
        jdbcTemplate.update(sql7);
        String sql8 = "delete from registration_management";
        jdbcTemplate.update(sql8);
        String sql9 = "delete from news";
        jdbcTemplate.update(sql9);
        String sql10 = "delete from announcement";
        jdbcTemplate.update(sql10);
        String sql11 = "alter table user user_id =0";
        jdbcTemplate.update(sql11);
        String sql12 = "alter table competition com_id =0";
        jdbcTemplate.update(sql12);
        String sql13 = "alter table registration_management registration_id =0";
        jdbcTemplate.update(sql13);
        String sql14 = "alter table news news_id =0";
        jdbcTemplate.update(sql14);
        String sql15 = "alter table announcement inform_id =0";
        jdbcTemplate.update(sql15);
    }

    /**
     * 获取指定长度随机简体中文
     *
     * @param len int
     * @return String
     */
    public static String randomChar(int len) {
        String ret = "";
        for (int i = 0; i < len; i++) {
            String str = null;
            int hightPos, lowPos; // 定义高低位
            Random random = new Random();
            hightPos = (176 + Math.abs(random.nextInt(39))); //获取高位值
            lowPos = (161 + Math.abs(random.nextInt(93))); //获取低位值
            byte[] b = new byte[2];
            b[0] = (Integer.valueOf(hightPos).byteValue());
            b[1] = (Integer.valueOf(lowPos).byteValue());
            try {
                str = new String(b, "GBk"); //转成中文
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
            ret += str;
        }
        return ret;
    }

    /**
     * 随机生成邮箱
     *
     * @param length 邮箱地址长度
     */
    private static String randomEmail(int length) {
        return randomAz(length) + "@qq.com";
    }

    /**
     * 随机生成电话
     */
    private static String[] telFirst = "134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");

    private static String getTel() {
        int index = randomNum(0, telFirst.length - 1);
        String first = telFirst[index];
        String second = String.valueOf(randomNum(1, 888) + 10000).substring(1);
        String thrid = String.valueOf(randomNum(1, 9100) + 10000).substring(1);
        return first + second + thrid;
    }


    /**
     * 随机生成大小写字母字符串
     *
     * @param length 字符串长度
     */
    private static String randomAz(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (new Random().nextInt(2) == 1) {
                sb.append((char) randomNum(65, 90));    // 大写字母65-90   97-122小写
            } else {
                sb.append((char) randomNum(97, 122));    // 大写字母65-90   97-122小写
            }
        }
        return sb.toString();
    }


    /**
     * 随机生成数字字符串
     *
     * @param min 最小值
     * @param max 最大值
     */
    private static String randomNumStr(int min, int max) {
        return String.valueOf(randomNum(min, max));
    }

    /**
     * 随机生成数字
     *
     * @param min 最小值
     * @param max 最大值
     */
    private static int randomNum(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }

    /**
     * 随机生成发布日期字符串
     *
     * @param min 最小年份
     * @param max 最大年份
     */
    private static String randomDate(int min, int max) {
        return randomNumStr(min, max) + "-" + randomNumStr(1, 12) + "-" + randomNumStr(1, 12) + " " + randomNumStr(0, 24) + "-" + randomNumStr(0, 60) + "-" + randomNumStr(0, 60);
    }

    /**
     * 循环生成竞赛级别
     *
     * @return
     */
    private static String comlevel() {
        String[] str1 = {"A类", "B类", "C类", "D类","E类"};
        int i = new Random().nextInt(4);
        if (i == 1)
            return str1[i];
        else if (i == 2)
            return str1[i];
        else if (i == 3)
            return str1[i];
        else if (i == 4)
            return str1[i];
        return str1[i];
    }

    /**
     * 循环生成专业
     */
    private static String commajor() {
        String[] str2 = {"计算机科学与计数", "物联网", "信息安全计数"};
        int i = new Random().nextInt(3);
        if (i == 1)
            return str2[i];
        else if (i == 2)
            return str2[i];
        return str2[i];
    }

    /**
     * 循环生成竞赛类别
     */
    private static String comlcate() {
        String[] str3 = {"体育类", "艺术类", "科技类", "电子类"};
        int i = new Random().nextInt(4);
        if (i == 1)
            return str3[i];
        else if (i == 2)
            return str3[i];
        else if (i == 3)
            return str3[i];
        return str3[i];
    }

    /**
     * 利用identity查询user_id,对应name
     * @return
     */
    private String identity(){
        String sql1 = "select user_id from user where user_identity = 1";
        List<Map<String,Object>> list = jdbcTemplate.queryForList(sql1);
        int id = jdbcTemplate.update(sql1);
        String userid = null;
        for(int i = 0;i<id;i++)
            userid = String.valueOf(list.get(i));
        return userid;
    }
}
