package com.zxk175.ssm_note.conf.constant;

/**
 * Created by zxk175 on 16/12/19.
 */
public class DBConstant {
    //public static final String JNDINAME = "jndi";
    public static final String URL = "jdbc:mysql://localhost:3306/table?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "123456";
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    //初始化连接大小
    public static final int INITIALSIZE = 0;
    //连接池最大使用连接数量
    public static final int MAXACTIVE = 20;
    //获取连接最大等待时间
    public static final long MAXWAIT = 60000;
    //连接池最小空闲
    public static final int MINIDLE = 1;
    /**
     * 用来检测连接是否有效的sql，要求是一个查询语句。
     * 如果validationQuery为null，testOnBorrow、testOnReturn、
     * testWhileIdle都不会其作用
     */
    public static final String VALIDATIONQUERY = "select 1";
    public static final boolean TESTONBORROW = false;
    public static final boolean TESTONRETURN = false;
    public static final boolean TESTWHILEIDLE = true;
    //配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
    public static final long TIMEBETWEENEVICTIONRUNSMILLIS = 60000;
    //配置一个连接在池中最小生存的时间，单位是毫秒
    public static final long MINEVICTABLEIDLETIMEMILLIS = 25200000;
    //打开removeAbandoned功能
    public static final boolean REMOVEABANDONED = true;
    //1800秒，也就是30分钟
    public static final int REMOVEABANDONEDTIMEOUT = 1800;
    //关闭abanded连接时输出错误日志
    public static final boolean LOGABANDONED = true;
}
