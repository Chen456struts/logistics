import java.io.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {
    //列如配置到com.wbg.ssm包下  程序会自动添加dao\controller\service\entity
    final static String Package = "com.cchong.logistics";  //包名格式
    final static String database = "xiaomi";   //数据库名称
    final static String user = "root";//数据库账号
    final static String password = "123456";//数据库密码
    //---------------^^^^^^^^^^^^^配置上面四项^^^^^^^^^^^^^^^^^--------------------------
    final static String dburl = "jdbc:mariadb://localhost:3306/" + database;  //服务器地址
    final static String dbdriver = "org.mariadb.jdbc.Driver"; //驱动

    public static void main(String[] args) throws Exception {
        System.out.println("1、第一次配置");
        System.out.println("2、第二次配置");
        System.out.print("请输入配置：");
        Scanner scanner = new Scanner(System.in);
        String c = scanner.next();
        if ("1".equals(c)) {
            System.out.println("开始第一次配置");
            createStart();
            System.out.println("开始第一次配置完成");
        } else if ("2".equals(c)) {
            System.out.println("开始第二次配置");
            for (String table : TBlist()) {
                createStart(tables(table));
            }
            //创建Request
            System.out.println("创建Request");
            createFile(new File(srcPackage + "util\\Result.java"), createResult());
            System.out.println("开始第二次配置完成");
        } else {
            System.out.printf("只能输入1、2选项");
        }
    }

    //获取当前项目的路径
    public static String url = System.getProperty("user.dir");
    //包的路径
    public static String srcPackage = url + "/src/main/java/" + Package.replace(".", "/") + "/";
    private static String tablename;
    private String[] colnames; // 列名数组
    private String[] colTypes; // 列名类型数组
    private int[] colSizes; // 列名大小数组

    static void createStart() {
        System.out.println("开始配置pom.xml");
        System.out.println(configPomXml(url));
        url = url + File.separator + "src" + File.separator + "main";
        System.out.println("开始配置resources目录");
        createResources(url + File.separator + "resources");
        System.out.println("完成配置resources目录");
        System.out.println("开始配置webapp目录");
        createWebapp(url + File.separator + "webapp");
        System.out.println("完成配置webapp目录");
    }

    //***********************Resources************************

    /**
     * 创建四个配置文件
     * dbc.properties
     * log4j.properties
     * mybatis-config.xml
     * spring-web.xml
     *
     * @return
     */
    static boolean createResources(String url) {
        if (createJdbcProperties(url)) {
            System.out.println("jdbc.properties配置成功");
        } else {
            System.out.println("jdbc.properties配置失败");
        }
        if (log4jProperties(url)) {
            System.out.println("log4j.properties配置成功");
        } else {
            System.out.println("log4j.properties配置失败");
        }
        if (mybatisConfig(url)) {
            System.out.println("mybatis-config.xml配置成功");
        } else {
            System.out.println("mybatis-config.xml配置失败");
        }
        if (springWeb(url)) {
            System.out.println("spring-web.xml配置成功");
        } else {
            System.out.println("spring-web.xml配置失败");
        }
        if (generatorConfig(url)) {
            System.out.println("generatorConfig.xml配置成功");
        } else {
            System.out.println("generatorConfig.xml配置失败");
        }

        //\resources\spring
        if (springDao(url + File.separator + "spring")) {
            System.out.println("spring-dao.xml配置成功");
        } else {
            System.out.println("spring-dao.xml配置失败");
        }
        //\resources\spring
        if (springService(url + File.separator + "spring")) {
            System.out.println("spring-service.xml配置成功");
        } else {
            System.out.println("spring-service.xml配置失败");
        }

        return true;
    }

    /**
     * 创建jdbc.properties配置文件
     *
     * @param url 路径
     * @return
     */
    static boolean createJdbcProperties(String url) {
        File file = new File(url, "jdbc.properties");
        String context = "jdbc.driver=org.mariadb.jdbc.Driver\n" +
                "jdbc.url=jdbc:mariadb://localhost:3306/" + database + "\n" +
                "jdbc.username=" + user + "\n" +
                "jdbc.password=" + password + "";
        return createFile(file, context);
    }

    /**
     * 创建log4j.properties日志文件
     *
     * @param url 路径
     * @return
     */
    static boolean log4jProperties(String url) {
        File file = new File(url, "log4j.properties");

        String context = "# Global logging configuration\n" +
                "log4j.rootLogger=ERROR, ooo\n" +
                "\n" +
                "# MyBatis logging configuration...\n" +
                "log4j.logger." + Package + ".dao=DEBUG\n" +
                "\n" +
                "# 规则1,名字为 ooo，向标准输出 System.err/out\n" +
                "log4j.appender.ooo=org.apache.log4j.ConsoleAppender\n" +
                "log4j.appender.ooo.layout=org.apache.log4j.PatternLayout\n" +
                "log4j.appender.ooo.layout.ConversionPattern=%5p [%t] ~ %m%n\n";
        return createFile(file, context);
    }

    /**
     * 创建mybatis-config.xml配置文件
     *
     * @param url 路径
     * @return
     */
    static boolean mybatisConfig(String url) {
        File file = new File(url, "mybatis-config.xml");

        String context = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<!DOCTYPE configuration PUBLIC \"-//mybatis.org//DTD Config 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-config.dtd\">\n" +
                "\n" +
                "\n" +
                "<configuration>\n" +
                "    <settings>\n" +
                "        <!-- 使用jdbc的getGeneratedKeys获取数据库自增主键值 -->\n" +
                "        <setting name=\"useGeneratedKeys\" value=\"true\" />\n" +
                "        <!-- 使用列别名替换列名 默认:true -->\n" +
                "        <setting name=\"useColumnLabel\" value=\"true\" />\n" +
                "        <!-- 开启驼峰命名转换:Table {create_time} -> Entity {createTime} -->\n" +
                "        <setting name=\"mapUnderscoreToCamelCase\" value=\"true\" />\n" +
                "    </settings>\n" +
                "\n" +
                "    <plugins>\n" +
                "        <plugin interceptor=\"com.github.pagehelper.PageInterceptor\" />\n" +
                "    </plugins>\n" +
                "</configuration>";
        return createFile(file, context);
    }

    /**
     * 创建spring-web.xml配置文件
     *
     * @return
     */
    static boolean springWeb(String url) {
        File file = new File(url, "spring-web.xml");

        String context = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<beans xmlns=\"http://www.springframework.org/schema/beans\"\n" +
                "       xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "       xmlns:context=\"http://www.springframework.org/schema/context\"\n" +
                "       xmlns:mvc=\"http://www.springframework.org/schema/mvc\"\n" +
                "       xsi:schemaLocation=\"http://www.springframework.org/schema/beans\n" +
                "\thttp://www.springframework.org/schema/beans/spring-beans.xsd\n" +
                "\thttp://www.springframework.org/schema/context\n" +
                "\thttp://www.springframework.org/schema/context/spring-context.xsd\n" +
                "\thttp://www.springframework.org/schema/mvc\n" +
                "\thttp://www.springframework.org/schema/mvc/spring-mvc-3.0.xsd\">\n" +
                "    <!-- 配置SpringMVC -->\n" +
                "    <!-- 1.开启SpringMVC注解模式 -->\n" +
                "    <!-- 简化配置：\n" +
                "        (1)自动注册DefaultAnootationHandlerMapping,AnotationMethodHandlerAdapter\n" +
                "        (2)提供一些列：数据绑定，数字和日期的format @NumberFormat, @DateTimeFormat, xml,json默认读写支持\n" +
                "    -->\n" +
                "    <mvc:annotation-driven />\n" +
                "\n" +
                "    <!-- 2.静态资源默认servlet配置\n" +
                "        (1)加入对静态资源的处理：js,gif,png\n" +
                "        (2)允许使用\"/\"做整体映射\n" +
                "     -->\n" +
                "    <mvc:default-servlet-handler/>\n" +
                "\n" +
                "    <!-- 3.配置jsp 显示ViewResolver -->\n" +
                "  <bean class=\"org.springframework.web.servlet.view.InternalResourceViewResolver\">\n" +
                "        <property name=\"viewClass\" value=\"org.springframework.web.servlet.view.JstlView\" />\n" +
                "        <property name=\"prefix\" value=\"/WEB-INF/jsp/\" />\n" +
                "        <property name=\"suffix\" value=\".jsp\" />\n" +
                "    </bean>\n" +
                "    <!-- 4.扫描web相关的bean -->\n" +
                "    <context:component-scan base-package=\"" + Package + ".controller\" />\n" +
                "</beans>";
        return createFile(file, context);
    }

    /**
     * 创建spring-dao.xml配置文件
     *
     * @param url 路径
     * @return
     */
    static boolean springDao(String url) {
        File file = new File(url, "spring-dao.xml");
        String context = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<beans xmlns=\"http://www.springframework.org/schema/beans\"\n" +
                "       xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "       xmlns:tx=\"http://www.springframework.org/schema/tx\" xmlns:context=\"http://www.springframework.org/schema/context\"\n" +
                "       xsi:schemaLocation=\"http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd\">\n" +
                "\n" +
                "\n" +
                "\n" +
                "    <!-- 配置整合mybatis过程 -->\n" +
                "    <!-- 1.配置数据库相关参数properties的属性：${url} -->\n" +
                "    <context:property-placeholder location=\"classpath:jdbc.properties\" />\n" +
                "\n" +
                "    <!-- 2.数据库连接池 -->\n" +
                "    <bean id=\"dataSource\" class=\"com.mchange.v2.c3p0.ComboPooledDataSource\">\n" +
                "        <property name=\"driverClass\" value=\"${jdbc.driver}\" />\n" +
                "        <property name=\"jdbcUrl\" value=\"${jdbc.url}\" />\n" +
                "        <property name=\"user\" value=\"${jdbc.username}\" />\n" +
                "        <property name=\"password\" value=\"${jdbc.password}\" />\n" +
                "\n" +
                "        <!-- c3p0连接池的私有属性 -->\n" +
                "        <property name=\"maxPoolSize\" value=\"30\" />\n" +
                "        <property name=\"minPoolSize\" value=\"10\" />\n" +
                "        <!-- 关闭连接后不自动commit -->\n" +
                "        <property name=\"autoCommitOnClose\" value=\"false\" />\n" +
                "        <!-- 获取连接超时时间 -->\n" +
                "        <property name=\"checkoutTimeout\" value=\"10000\" />\n" +
                "        <!-- 当获取连接失败重试次数 -->\n" +
                "        <property name=\"acquireRetryAttempts\" value=\"2\" />\n" +
                "    </bean>\n" +
                "\n" +
                "    <!-- 3.配置SqlSessionFactory对象 -->\n" +
                "    <bean id=\"sqlSessionFactory\" class=\"org.mybatis.spring.SqlSessionFactoryBean\">\n" +
                "        <!-- 注入数据库连接池 -->\n" +
                "        <property name=\"dataSource\" ref=\"dataSource\" />\n" +
                "        <!-- 配置MyBaties全局配置文件:mybatis-config.xml -->\n" +
                "        <property name=\"configLocation\" value=\"classpath:mybatis-config.xml\" />\n" +
                "        <!-- 扫描entity包 使用别名 -->\n" +
                "        <property name=\"typeAliasesPackage\" value=\"" + Package + ".entity\" />\n" +
                "        <!-- 扫描sql配置文件:mapper需要的xml文件 -->\n" +
                "        <property name=\"mapperLocations\" value=\"classpath:mapper/*.xml\" />\n" +
                "    </bean>\n" +
                "\n" +
                "    <!-- 4.配置扫描Dao接口包，动态实现Dao接口，注入到spring容器中 -->\n" +
                "    <bean class=\"org.mybatis.spring.mapper.MapperScannerConfigurer\">\n" +
                "        <!-- 注入sqlSessionFactory -->\n" +
                "        <property name=\"sqlSessionFactoryBeanName\" value=\"sqlSessionFactory\" />\n" +
                "        <!-- 给出需要扫描Dao接口包 -->\n" +
                "        <property name=\"basePackage\" value=\"" + Package + ".dao\" />\n" +
                "    </bean>\n" +
                "\n" +
                "    <!--配置声明式事务管理-->\n" +
                "    <bean id=\"transactionManager\" class=\"org.springframework.jdbc.datasource.DataSourceTransactionManager\">\n" +
                "        <property name=\"dataSource\" ref=\"dataSource\" />\n" +
                "    </bean>\n" +
                "    <tx:annotation-driven proxy-target-class=\"true\" />\n" +
                "\n" +
                "</beans>";
        return createFile(file, context);
    }

    /**
     * 创建spring-service.xml配置文件
     *
     * @param url 路径
     * @return
     */
    static boolean springService(String url) {
        File file = new File(url, "spring-service.xml");
        String context = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<beans xmlns=\"http://www.springframework.org/schema/beans\"\n" +
                "       xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "       xmlns:context=\"http://www.springframework.org/schema/context\"\n" +
                "       xmlns:tx=\"http://www.springframework.org/schema/tx\" xmlns:mvc=\"http://www.springframework.org/schema/mvc\"\n" +
                "       xmlns:aop=\"http://www.springframework.org/schema/aop\"\n" +
                "       xsi:schemaLocation=\"http://www.springframework.org/schema/beans\n" +
                "\thttp://www.springframework.org/schema/beans/spring-beans.xsd\n" +
                "\thttp://www.springframework.org/schema/context\n" +
                "\thttp://www.springframework.org/schema/context/spring-context.xsd\n" +
                " http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd\">\n" +
                "    <!-- 扫描service包下所有使用注解的类型 -->\n" +
                "    <context:component-scan base-package=\"" + Package + ".service\" />\n" +
                "    <mvc:annotation-driven />\n" +
                "    <!-- 启用 aspectj 方式 AOP-->\n" +
                "    <aop:aspectj-autoproxy proxy-target-class=\"true\" />\n" +
                "</beans>";
        return createFile(file, context);
    }

    /**
     * 创建generatorConfig.xml配置文件
     *
     * @param url
     * @return
     */
    static boolean generatorConfig(String url) {
        File file = new File(url, "generatorConfig.xml");
        String context = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<!DOCTYPE generatorConfiguration\n" +
                "        PUBLIC \"-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN\"\n" +
                "        \"http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd\">\n" +
                "\n" +
                "<generatorConfiguration>\n" +
                "\n" +
                "    <context id=\"xxx\" targetRuntime=\"MyBatis3Simple\">\n" +
                "\n" +
                "\n" +
                "        <commentGenerator>\n" +
                "            <property name=\"suppressDate\" value=\"true\" />\n" +
                "        </commentGenerator>\n" +
                "        <!-- 数据库连接 -->\n" +
                "        <jdbcConnection driverClass=\"org.mariadb.jdbc.Driver\"\n" +
                "                        connectionURL=\"jdbc:mariadb://localhost/" + database + "\"\n" +
                "                        userId=\"" + user + "\" password=\"" + password + "\">\n" +
                "        </jdbcConnection>\n" +
                "\n" +
                "        <!-- Model生成规则 -->\n" +
                "        <javaModelGenerator targetPackage=\"" + Package + ".entity\" targetProject=\"src/main/java\">\n" +
                "            <property name=\"trimStrings\" value=\"true\" />\n" +
                "        </javaModelGenerator>\n" +
                "\n" +
                "        <sqlMapGenerator targetPackage=\"mapper\"  targetProject=\"src/main/resources\"/>\n" +
                "        <!-- dao 规则 -->\n" +
                "        <javaClientGenerator type=\"XMLMAPPER\" targetPackage=\"" + Package + ".dao\"  targetProject=\"src/main/java\">\n" +
                "            <property name=\"enableSubPackages\" value=\"true\" />\n" +
                "        </javaClientGenerator>\n" +
                "        <table tableName=\"%\">\n" +
                "            <generatedKey column=\"id\" sqlStatement=\"Mysql\"/>\n" +
                "        </table>\n" +
                "    </context>\n" +
                "</generatorConfiguration>";
        return createFile(file, context);
    }


    //***********************webapp************************
    static boolean createWebapp(String url) {
        if (webXml(url + File.separator + "WEB-INF")) {
            System.out.println("web.xml配置成功");
        } else {
            System.out.println("web.xml配置失败");
        }
        createCSSJSDirectory(url + File.separator);
        return true;
    }

    /**
     * 创建WEB-INF\web.xml配置文件
     *
     * @param url 路径
     * @return
     */
    static boolean webXml(String url) {
        File file = new File(url, "web.xml");

        File f = new File(url, "/jsp");
        f.mkdirs();
        String context = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<web-app xmlns=\"http://xmlns.jcp.org/xml/ns/javaee\"\n" +
                "         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "         xsi:schemaLocation=\"http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd\"\n" +
                "         version=\"4.0\">\n" +
                "\n" +
                "    <display-name>自动生成</display-name>\n" +
                "\n" +
                "    <!--解决中文乱码-->\n" +
                "    <filter>\n" +
                "        <filter-name>encodingFilter</filter-name>\n" +
                "        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>\n" +
                "        <async-supported>true</async-supported>\n" +
                "        <init-param>\n" +
                "            <param-name>encoding</param-name>\n" +
                "            <param-value>UTF-8</param-value>\n" +
                "        </init-param>\n" +
                "\n" +
                "    </filter>\n" +
                "    <filter-mapping>\n" +
                "        <filter-name>encodingFilter</filter-name>\n" +
                "        <url-pattern>/*</url-pattern>\n" +
                "    </filter-mapping>\n" +
                "\n" +
                "    <!--配置 Spring 的容器-->\n" +
                "    <context-param>\n" +
                "        <param-name>contextConfigLocation</param-name>\n" +
                "        <param-value>classpath:spring/spring-*.xml</param-value>\n" +
                "    </context-param>\n" +
                "    <listener>\n" +
                "        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>\n" +
                "    </listener>\n" +
                "\n" +
                "    <!--配置 MVC 容器-->\n" +
                "    <!--将所有的请求都交给 Spring MVC 处理-->\n" +
                "    <servlet>\n" +
                "        <servlet-name>app</servlet-name>\n" +
                "        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>\n" +
                "        <init-param>\n" +
                "            <param-name>contextConfigLocation</param-name>\n" +
                "            <param-value>classpath:spring-web.xml</param-value>\n" +
                "        </init-param>\n" +
                "    </servlet>\n" +
                "    <servlet-mapping>\n" +
                "        <servlet-name>app</servlet-name>\n" +
                "        <url-pattern>/</url-pattern>\n" +
                "    </servlet-mapping>\n" +
                "</web-app>";


        return createFile(file, context);
    }

    /**
     * 创建css和js
     *
     * @param url 路径
     */
    static boolean createCSSJSDirectory(String url) {
        File fcss = new File(url + "css");
        if (fcss.mkdirs()) {
            System.out.println("成功创建css文件夹");
        }
        File fjs = new File(url + "js");
        if (fjs.mkdirs()) {
            System.out.println("成功创建js文件夹");
        }

        return true;
    }

    /**
     * @param file    创建的文件
     * @param context 文件里面的内容
     */
    static boolean createFile(File file, String context) {
        //获取文件
        File parent = file.getParentFile();
        //如果是目录
        if (parent != null) {
            //创建目录
            parent.mkdirs();
        }
        try {
            //创建文件
            file.createNewFile();
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(file);
                fileWriter.write(context);
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                return false;
            }
        } catch (IOException e) {
            System.out.println("创建文件失败:" + e.getMessage());
        }
        return true;
    }


    //***********************pom.xml************************

    /**
     * 配置pom.xml文件
     *
     * @param url 路径
     */
    static String configPomXml(String url) {
        File file = new File(url, "pom.xml");
        InputStream inputStream = null;
        byte b[] = new byte[Integer.parseInt(String.valueOf(file.length()))];
        StringBuffer stringBuffer = null;
        try {
            inputStream = new FileInputStream(file);
            inputStream.read(b);
            inputStream.close();
            stringBuffer = new StringBuffer(new String(b));
            stringBuffer.replace(Integer.parseInt(String.valueOf(file.length())) - 10, Integer.parseInt(String.valueOf(file.length())), "");
            stringBuffer.append(pomContext());
        } catch (Exception e) {
            return "程序出错，请重试 -- pom.xml文件配置失败";
        }
        if (createFile(file, stringBuffer.toString())) {
            return "pom.xml文件配置完成";
        }
        return "pom.xml文件配置失败";
    }

    /**
     * pom.xml配置文件需要加的配置
     *
     * @return
     */
    static String pomContext() {
        return "<!--打包-->\n" +
                "    <packaging>war</packaging>\n" +
                "    <!--设置编码-->\n" +
                "    <properties>\n" +
                "        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>\n" +
                "        <maven.compiler.source>1.8</maven.compiler.source>\n" +
                "        <maven.compiler.target>1.8</maven.compiler.target>\n" +
                "        <spring.version>5.1.0.RELEASE</spring.version>\n" +
                "    </properties>\n" +
                "    <!--引入文件-->\n" +
                "    <dependencies>\n" +
                "        <!-- Spring Web MVC -->\n" +
                "        <dependency>\n" +
                "            <groupId>org.springframework</groupId>\n" +
                "            <artifactId>spring-web</artifactId>\n" +
                "            <version>${spring.version}</version>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>org.springframework</groupId>\n" +
                "            <artifactId>spring-webmvc</artifactId>\n" +
                "            <version>${spring.version}</version>\n" +
                "        </dependency>\n" +
                "\n" +
                "        <!-- servlet 系列的支持 -->\n" +
                "        <dependency>\n" +
                "            <groupId>javax</groupId>\n" +
                "            <artifactId>javaee-api</artifactId>\n" +
                "            <version>8.0</version>\n" +
                "            <scope>provided</scope>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>javax.servlet</groupId>\n" +
                "            <artifactId>jstl</artifactId>\n" +
                "            <version>1.2</version>\n" +
                "        </dependency>\n" +
                "\n" +
                "        <dependency>\n" +
                "            <groupId>com.github.pagehelper</groupId>\n" +
                "            <artifactId>pagehelper</artifactId>\n" +
                "            <version>5.1.7</version>\n" +
                "        </dependency>\n" +
                "\n" +
                "        <!-- Springframework -->\n" +
                "        <dependency>\n" +
                "            <groupId>org.springframework</groupId>\n" +
                "            <artifactId>spring-context</artifactId>\n" +
                "            <version>${spring.version}</version>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>org.springframework</groupId>\n" +
                "            <artifactId>spring-jdbc</artifactId>\n" +
                "            <version>${spring.version}</version>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>org.springframework</groupId>\n" +
                "            <artifactId>spring-aop</artifactId>\n" +
                "            <version>${spring.version}</version>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>org.aspectj</groupId>\n" +
                "            <artifactId>aspectjweaver</artifactId>\n" +
                "            <version>1.9.1</version>\n" +
                "        </dependency>\n" +
                "\n" +
                "        <!-- MyBatis -->\n" +
                "        <dependency>\n" +
                "            <groupId>org.mybatis</groupId>\n" +
                "            <artifactId>mybatis</artifactId>\n" +
                "            <version>3.4.6</version>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>org.mybatis</groupId>\n" +
                "            <artifactId>mybatis-spring</artifactId>\n" +
                "            <version>1.3.2</version>\n" +
                "        </dependency>\n" +
                "\n" +
                "        <!-- 数据库驱动以及数据库连接池-->\n" +
                "        <dependency>\n" +
                "            <groupId>org.mariadb.jdbc</groupId>\n" +
                "            <artifactId>mariadb-java-client</artifactId>\n" +
                "            <version>2.3.0</version>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>com.mchange</groupId>\n" +
                "            <artifactId>c3p0</artifactId>\n" +
                "            <version>0.9.5.2</version>\n" +
                "        </dependency>\n" +
                "\n" +
                "        <!-- 日志框架 -->\n" +
                "        <dependency>\n" +
                "            <groupId>log4j</groupId>\n" +
                "            <artifactId>log4j</artifactId>\n" +
                "            <version>1.2.17</version>\n" +
                "        </dependency>\n" +
                "\n" +
                "        <!-- 通用工具 -->\n" +
                "        <dependency>\n" +
                "            <groupId>com.fasterxml.jackson.core</groupId>\n" +
                "            <artifactId>jackson-databind</artifactId>\n" +
                "            <version>2.9.7</version>\n" +
                "        </dependency>\n" +
                "\n" +
                "        <!-- 单元测试 -->\n" +
                "        <dependency>\n" +
                "            <groupId>org.springframework</groupId>\n" +
                "            <artifactId>spring-test</artifactId>\n" +
                "            <version>${spring.version}</version>\n" +
                "            <scope>test</scope>\n" +
                "        </dependency>\n" +
                "\n" +
                "        <dependency>\n" +
                "            <groupId>junit</groupId>\n" +
                "            <artifactId>junit</artifactId>\n" +
                "            <version>4.12</version>\n" +
                "            <scope>test</scope>\n" +
                "        </dependency>\n" +
                "    </dependencies>\n" +
                "    <build>\n" +
                "        <finalName>contact</finalName>\n" +
                "        <plugins>\n" +
                "            <plugin>\n" +
                "                <groupId>org.mybatis.generator</groupId>\n" +
                "                <artifactId>mybatis-generator-maven-plugin</artifactId>\n" +
                "                <version>1.3.7</version>\n" +
                "                <dependencies>\n" +
                "                    <dependency>\n" +
                "                        <groupId>org.mariadb.jdbc</groupId>\n" +
                "                        <artifactId>mariadb-java-client</artifactId>\n" +
                "                        <version>2.3.0</version>\n" +
                "                    </dependency>\n" +
                "                </dependencies>\n" +
                "            </plugin>\n" +
                "        </plugins>\n" +
                "\n" +
                "        <pluginManagement><!-- lock down plugins versions to avoid using Maven defaults (may be moved to parent pom) -->\n" +
                "            <plugins>\n" +
                "                <plugin>\n" +
                "                    <artifactId>maven-clean-plugin</artifactId>\n" +
                "                    <version>3.0.0</version>\n" +
                "                </plugin>\n" +
                "                <!-- see http://maven.apache.org/ref/current/maven-core/default-bindings.html#Plugin_bindings_for_war_packaging -->\n" +
                "                <plugin>\n" +
                "                    <artifactId>maven-resources-plugin</artifactId>\n" +
                "                    <version>3.0.2</version>\n" +
                "                </plugin>\n" +
                "                <plugin>\n" +
                "                    <artifactId>maven-compiler-plugin</artifactId>\n" +
                "                    <version>3.7.0</version>\n" +
                "                </plugin>\n" +
                "                <plugin>\n" +
                "                    <artifactId>maven-surefire-plugin</artifactId>\n" +
                "                    <version>2.20.1</version>\n" +
                "                </plugin>\n" +
                "                <plugin>\n" +
                "                    <artifactId>maven-war-plugin</artifactId>\n" +
                "                    <version>3.2.0</version>\n" +
                "                </plugin>\n" +
                "                <plugin>\n" +
                "                    <artifactId>maven-install-plugin</artifactId>\n" +
                "                    <version>2.5.2</version>\n" +
                "                </plugin>\n" +
                "                <plugin>\n" +
                "                    <artifactId>maven-deploy-plugin</artifactId>\n" +
                "                    <version>2.8.2</version>\n" +
                "                </plugin>\n" +
                "            </plugins>\n" +
                "        </pluginManagement>\n" +
                "    </build>\n\n" +
                "</project>";
    }


    //-------------------------------------------------------

    /**
     * 获取指定数据库中包含的表 TBlist
     *
     * @return 返回所有表名(将表名放到一个集合中)
     * @throws Exception
     * @time 2017年7月14日下午5:54:52
     * @packageName com.util
     */
    public static List<String> TBlist() throws Exception {
        // 访问数据库 采用 JDBC方式
        Class.forName(dbdriver);

        Connection con = DriverManager.getConnection(dburl, user, password);

        DatabaseMetaData md = con.getMetaData();

        List<String> list = null;

        ResultSet rs = md.getTables(null, null, null, null);
        if (rs != null) {
            list = new ArrayList<String>();
        }
        while (rs.next()) {
            String tableName = rs.getString("TABLE_NAME");
            list.add(tableName);
        }
        rs = null;
        md = null;
        con = null;
        return list;
    }

    /**
     * 把输入字符串的首字母改成大写
     *
     * @param str
     * @return
     */
    private static String initcap(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }

    /**
     * 把输入字符串的首字母改成小写
     *
     * @param str
     * @return
     */
    private static String initlow(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'A' && ch[0] <= 'Z') {
            ch[0] = (char) (ch[0] + 32);
        }
        return new String(ch);
    }

    //首字母转换和下划线转换
    private static String tables(String table) {
        String[] tables = table.split("_");
        table = "";
        for (String s : tables) {
            table += initcap(s);
        }
        return table;
    }

    /**
     * 创建Dao
     */
    private static String createDao(String tableName) {
        String dao = "package " + Package + ".dao;\n" +
                "\n" +
                "import " + Package + ".entity." + tableName + ";\n" +
                "import org.springframework.stereotype.Repository;\n" +
                "\n" +
                "import java.util.List;\n" +
                "\n" +
                "@Repository\n" +
                "public interface " + tableName + "Mapper {\n" +
                "    /**\n" +
                "     * 根据主键删除操作\n" +
                "     *\n" +
                "     * @param id\n" +
                "     * @return\n" +
                "     */\n" +
                "    int deleteByPrimaryKey(int id);\n" +
                "\n" +
                "    /**\n" +
                "     * 添加操作\n" +
                "     *\n" +
                "     * @param " + initlow(tableName) + "\n" +
                "     * @return\n" +
                "     */\n" +
                "    int insert(" + tableName + " " + initlow(tableName) + ");\n" +
                "\n" +
                "    /**\n" +
                "     * 根据主键查询操作\n" +
                "     *\n" +
                "     * @param id\n" +
                "     * @return\n" +
                "     */\n" +
                "    " + tableName + " selectByPrimaryKey(int id);\n" +
                "\n" +
                "    /**\n" +
                "     * 全部查询操作\n" +
                "     *\n" +
                "     * @return\n" +
                "     */\n" +
                "    List<" + tableName + "> selectAll();\n" +
                "\n" +
                "    /**\n" +
                "     * 根据主键全部修改操作\n" +
                "     *\n" +
                "     * @param " + initlow(tableName) + "\n" +
                "     * @return\n" +
                "     */\n" +
                "    int updateByPrimaryKey(" + tableName + " " + initlow(tableName) + ");\n" +
                "\n" +
                "    /**\n" +
                "     * 查询所有数据\n" +
                "     * @return\n" +
                "     */\n" +
                "    int count();\n" +
                "}";
        return dao;
    }

    /**
     * 创建Service
     *
     * @param tableName 数据库表
     */
    private static String createService(String tableName) {
        String service = "package " + Package + ".service;\n" +
                "\n" +
                "import " + Package + ".entity." + tableName + ";\n" +
                "\n" +
                "import java.util.List;\n" +
                "\n" +
                "public interface " + tableName + "Service {\n" +
                "\n" +
                "    /**\n" +
                "     * 删除操作 根据主键\n" +
                "     *\n" +
                "     * @param id\n" +
                "     * @return\n" +
                "     */" +
                "\n" +
                "    int deleteByPrimaryKey(int id);\n" +
                "\n" +
                "    /**\n" +
                "     * 添加操作\n" +
                "     *\n" +
                "     * @param " + initlow(tableName) + "\n" +
                "     * @return\n" +
                "     */" +
                "\n" +
                "    int insert(" + tableName + " " + initlow(tableName) + ");\n" +
                "\n" +
                "    /**\n" +
                "     * 根据主键查询操作\n" +
                "     *\n" +
                "     * @param id\n" +
                "     * @return\n" +
                "     */" +
                "\n" +
                "    " + tableName + " selectByPrimaryKey(int id);\n" +
                "\n" +
                "    /**\n" +
                "     * 全部查询操作\n" +
                "     *\n" +
                "     * @return\n" +
                "     */" +
                "\n" +
                "    List<" + tableName + "> selectAll();\n" +
                "\n" +
                "    /**\n" +
                "     * 修改操作\n" +
                "     *\n" +
                "     * @param " + initlow(tableName) + "\n" +
                "     * @return\n" +
                "     */" +
                "\n" +
                "    int updateByPrimaryKey(" + tableName + " " + initlow(tableName) + ");\n" +
                "\n" +
                "    /**\n" +
                "     * 查询所有数据\n" +
                "     * @return\n" +
                "     */\n" +
                "    int count();\n" +
                "}";
        return service;
    }

    /**
     * 创建ServiceImpl
     *
     * @param tableName 数据库表
     */
    private static String createServiceImpl(String tableName) {
        String serviceImpl = "package " + Package + ".service.impl;\n" +
                "\n" +
                "import " + Package + ".dao." + tableName + "Mapper;\n" +
                "import " + Package + ".entity." + tableName + ";\n" +
                "import " + Package + ".service." + tableName + "Service;\n" +
                "import org.springframework.beans.factory.annotation.Autowired;\n" +
                "import org.springframework.stereotype.Service;\n" +
                "\n" +
                "import java.util.List;\n" +
                "\n" +
                "@Service\n" +
                "public class " + tableName + "ServiceImpl implements " + tableName + "Service {\n" +
                "\n" +
                "    @Autowired\n" +
                "    private " + tableName + "Mapper " + initlow(tableName) + "Mapper;\n" +
                "\n" +
                "    /**\n" +
                "     * 删除操作 根据主键删除\n" +
                "     *\n" +
                "     * @param id\n" +
                "     * @return\n" +
                "     */\n" +
                "    @Override\n" +
                "    public int deleteByPrimaryKey(int id) {\n" +
                "        return " + initlow(tableName) + "Mapper.deleteByPrimaryKey(id);\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * 添加操作\n" +
                "     *\n" +
                "     * @param " + initlow(tableName) + "\n" +
                "     * @return\n" +
                "     */\n" +
                "    @Override\n" +
                "    public int insert(" + tableName + " " + initlow(tableName) + ") {\n" +
                "        return " + initlow(tableName) + "Mapper.insert(" + initlow(tableName) + ");\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * 根据主键查询操作\n" +
                "     *\n" +
                "     * @param id\n" +
                "     * @return\n" +
                "     */\n" +
                "    @Override\n" +
                "    public " + tableName + " selectByPrimaryKey(int id) {\n" +
                "        return " + initlow(tableName) + "Mapper.selectByPrimaryKey(id);\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * 全部查询操作\n" +
                "     *\n" +
                "     * @return\n" +
                "     */\n" +
                "    @Override\n" +
                "    public List<" + tableName + "> selectAll() {\n" +
                "        return " + initlow(tableName) + "Mapper.selectAll();\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * 修改操作\n" +
                "     *\n" +
                "     * @param " + initlow(tableName) + "\n" +
                "     * @return\n" +
                "     */\n" +
                "    @Override\n" +
                "    public int updateByPrimaryKey(" + tableName + " " + initlow(tableName) + ") {\n" +
                "        return " + initlow(tableName) + "Mapper.updateByPrimaryKey(" + initlow(tableName) + ");\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * 查询所有数据\n" +
                "     * @return\n" +
                "     */\n" +
                "    @Override\n" +
                "    public int count() {\n" +
                "        return " + initlow(tableName) + "Mapper.count();\n" +
                "    }\n" +
                "}\n";
        return serviceImpl;
    }

    /**
     * 创建Controller
     *
     * @param tableName 数据库表
     */
    private static String createController(String tableName) {
        String controller = "package " + Package + ".controller;\n" +
                "import " + Package + ".entity." + tableName + ";\n" +
                "import " + Package + ".service." + tableName + "Service;\n" +
                "import " + Package + ".util.Result;\n" +
                "import org.springframework.beans.factory.annotation.Autowired;\n" +
                "import org.springframework.web.bind.annotation.*;\n" +
                "import java.util.List;\n" +
                "import com.github.pagehelper.PageHelper;\n" +
                "\n" +
                "@RestController\n" +
                "@RequestMapping(\"/" + initlow(tableName) + "\")\n" +
                "public class " + tableName + "Controller {\n" +
                "    @Autowired\n" +
                "    private " + tableName + "Service " + initlow(tableName) + "Service;\n" +
                "\n" +
                "    /**\n" +
                "     * 根据主键删除\n" +
                "     * 要求转入 aId\n" +
                "     *\n" +
                "     * @param id\n" +
                "     * @return\n" +
                "     */\n" +
                "    @GetMapping(\"/deleteByPrimaryKey\")\n" +
                "    public Result deleteByPrimaryKey(int id) {\n" +
                "        try {\n" +
                "\n" +
                "            return " + initlow(tableName) + "Service.deleteByPrimaryKey(id) > 0 ? new Result().successMessage(\"删除成功\") : Result.error(\"删除失败\");\n" +
                "        } catch (Exception ex) {\n" +
                "            return new Result().error(ex.getMessage());\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * 添加对象" + initlow(tableName) + "\n" +
                "     *\n" +
                "     * @param " + initlow(tableName) + "\n" +
                "     * @return\n" +
                "     */\n" +
                "    @PostMapping(\"/insert\")\n" +
                "    public Result insert(@RequestBody " + tableName + " " + initlow(tableName) + ") {\n" +
                "        try {\n" +
                "            return " + initlow(tableName) + "Service.insert(" + initlow(tableName) + ") > 0 ? new Result().successMessage(\"添加成功！\") : Result.error(\"添加失败！\");\n" +
                "        } catch (Exception ex) {\n" +
                "            return new Result().error(ex.getMessage());\n" +
                "        }\n" +
                "\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * 根据主键查找对象  最多只能返回一个对象\n" +
                "     *\n" +
                "     * @param id\n" +
                "     * @return\n" +
                "     */\n" +
                "    @GetMapping(\"/selectByPrimaryKey\")\n" +
                "    public Result selectByPrimaryKey(int id) {\n" +
                "        try {\n" +
                "            " + tableName + " " + initlow(tableName) + "1 = " + initlow(tableName) + "Service.selectByPrimaryKey(id);\n" +
                "            if (" + initlow(tableName) + "1 == null) {\n" +
                "                return new Result().successMessage(\"无数据\");\n" +
                "            } else {\n" +
                "                return new Result().success(" + initlow(tableName) + "1);\n" +
                "            }\n" +
                "        } catch (Exception ex) {\n" +
                "            return new Result().error(ex.getMessage());\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * 查询所有数据\n" +
                "     *\n" +
                "     * @return\n" +
                "     */\n" +
                "    @GetMapping(\"/selectAll\")\n" +
                "    public Result selectAll() {\n" +
                "        try {\n" +
                "            List<" + tableName + "> list = " + initlow(tableName) + "Service.selectAll();\n" +
                "            if (list == null) {\n" +
                "                return new Result().successMessage(\"无数据\");\n" +
                "            } else {\n" +
                "                return new Result().success(list);\n" +
                "            }\n" +
                "        } catch (Exception ex) {\n" +
                "            return new Result().error(ex.getMessage());\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * 根据主键修改全部字段\n" +
                "     *\n" +
                "     * @param " + initlow(tableName) + "\n" +
                "     * @return\n" +
                "     */\n" +
                "    @PostMapping(value = \"/updateByPrimaryKey\")\n" +
                "    public Result updateByPrimaryKey(@RequestBody " + tableName + " " + initlow(tableName) + ") {\n" +
                "        try {\n" +
                "            return " + initlow(tableName) + "Service.updateByPrimaryKey(" + initlow(tableName) + ") > 0 ? new Result().successMessage(\"修改成功\") : Result.error(\"修改失败\");\n" +
                "        } catch (Exception ex) {\n" +
                "            return new Result().error(ex.getMessage());\n" +
                "        }\n" +
                "    }\n" +
                "    \n" +
                "    \n" +
                "     /* 查询所有数据分页\n" +
                "     *\n" +
                "     * @return\n" +
                "     */\n" +
                "    @GetMapping(\"/selectPage\")\n" +
                "    public Result selectPage(@RequestParam(defaultValue = \"1\") int page, @RequestParam(defaultValue = \"10\") int limit) {\n" +
                "        try {\n" +
                "            PageHelper.startPage(page, limit);\n" +
                "            List<" + tableName + "> list = " + initlow(tableName) + "Service.selectAll();\n" +
                "            if (list == null) {\n" +
                "                return new Result().successMessage(\"无数据\");\n" +
                "            } else {\n" +

                "                return new Result(0, \"ok\", list, " + initlow(tableName) + "Service.count());\n" +
                "            }\n" +
                "        } catch (Exception ex) {\n" +
                "            return new Result().error(ex.getMessage());\n" +
                "        }\n" +
                "    }\n" +
                "}\n";


        return controller;
    }

    /**
     * 创建util
     */
    private static String createResult() {
        String controller = "package " + Package + ".util;\n" +
                "\n" +
                "public class Result {\n" +
                "\n" +
                " /* 根据Constants 常量 进行返回编码\n" +
                "    public static final int SUCCESS_CODE = 200;\n" +
                "    public static final String SUCCESS_MSG = \"请求成功\";\n" +
                "    public static final int EXCEPTION_CODE = 404;\n" +
                "    public static final String EXCEPTION_MSG = \"请求处理异常\";\n" +
                "    public static final int ERROR_CODE = 500;\n" +
                "    public static final String ERROR_MSG = \"请求方式有误,请检查 GET/POST\";\n" +
                "    public static final int NOT_URL_CODE = 501;\n" +
                "    public static final String NOT_URL_MSG = \"请求路径不存在\";\n" +
                "    public static final int INSUFFICIENT_AUTHORITY_CODE = 502;\n" +
                "    public static final String INSUFFICIENT_AUTHORITY_MSG = \"权限不足\";\n" +
                "    public static final int LOGON_EXPIRATION_CODE = 20011;\n" +
                "    public static final String LOGON_EXPIRATION_MSG = \"登陆已过期\";*/\n" +
                "\n" +
                "    //状态码\n" +
                "    int code;\n" +
                "    //数据\n" +
                "    Object data;\n" +
                "    //消息提示\n" +
                "    String message;\n" +
                "    //数量\n" +
                "    int count;\n" +
                "\n" +
                "\n" +
                "    public int getCount() {\n" +
                "        return count;\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "    public void setCount(int count) {\n" +
                "        this.count = count;\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "    public Result() {\n" +
                "    }\n" +
                "\n" +
                "    public Result(int code, String message) {\n" +
                "        this.code = code;\n" +
                "        this.message = message;\n" +
                "    }\n" +
                "\n" +
                "    public Result(int code, String message, Object data) {\n" +
                "        this.code = code;\n" +
                "        this.message = message;\n" +
                "        this.data = data;\n" +
                "    }\n" +
                "    public Result(int code, String message, Object data, int count) {\n" +
                "        this.code = code;\n" +
                "        this.data = data;\n" +
                "        this.message = message;\n" +
                "        this.count = count;\n" +
                "    }\n" +
                "\n" +
                "    public int getCode() {\n" +
                "        return code;\n" +
                "    }\n" +
                "\n" +
                "    public void setCode(int code) {\n" +
                "        this.code = code;\n" +
                "    }\n" +
                "\n" +
                "    public Object getData() {\n" +
                "        return data;\n" +
                "    }\n" +
                "\n" +
                "    public void setData(Object data) {\n" +
                "        this.data = data;\n" +
                "    }\n" +
                "\n" +
                "    public String getMessage() {\n" +
                "        return message;\n" +
                "    }\n" +
                "\n" +
                "    public void setMessage(String message) {\n" +
                "        this.message = message;\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "    /**\n" +
                "     * code:200\n" +
                "     * msg:请求成功\n" +
                "     *\n" +
                "     * @return\n" +
                "     */\n" +
                "    public static Result success() {\n" +
                "        return new Result(200, \"true\");\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "    /**\n" +
                "     * code:201\n" +
                "     * msg:请求失败\n" +
                "     *\n" +
                "     * @return\n" +
                "     */\n" +
                "    public static Result error() {\n" +
                "        return new Result(201, \"false\");\n" +
                "    }\n" +
                "    /**\n" +
                "     * code:201\n" +
                "     * msg:请求失败\n" +
                "     *\n" +
                "     * @return\n" +
                "     */\n" +
                "    public static Result error(String message) {\n" +
                "        return new Result(201, message);\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * successMessage\n" +
                "     * 正常返回，携带消息\n" +
                "     * code:200\n" +
                "     *\n" +
                "     * @param message 消息\n" +
                "     *                data:null\n" +
                "     *                count:0\n" +
                "     * @return\n" +
                "     */\n" +
                "    public static Result successMessage(String message) {\n" +
                "        return new Result(200, message);\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * success\n" +
                "     * 成功方法 带数据返回\n" +
                "     * code:200\n" +
                "     *\n" +
                "     * @param data  数据\n" +
                "     * @param count 总数\n" +
                "     * @return\n" +
                "     */\n" +
                "    public static Result success(Object data, int count) {\n" +
                "        return new Result(200, \"true\", data, count);\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * success\n" +
                "     * 成功方法 带数据返回\n" +
                "     * code:200\n" +
                "     * message: success\n" +
                "     *\n" +
                "     * @param data 数据\n" +
                "     *             count :0\n" +
                "     * @return\n" +
                "     */\n" +
                "    public static Result success(Object data) {\n" +
                "        return new Result(200, \"true\", data, 0);\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * error\n" +
                "     * data:null\n" +
                "     * count:0\n" +
                "     *\n" +
                "     * @param code    错误编码\n" +
                "     * @param message 错误信息\n" +
                "     * @return\n" +
                "     */\n" +
                "    public static Result error(int code, String message) {\n" +
                "        return new Result(code, message);\n" +
                "    }\n" +
                "\n" +
                "}\n";
        return controller;
    }

    /**
     * 开始创建
     *
     * @param tableName 数据库表
     */
    static void createStart(String tableName) {
        //创建Dao
        createFile(new File(srcPackage + "dao\\" + tableName + "Mapper.java"), createDao(tableName));
        //创建Service
        System.out.println("创建Service");
        createFile(new File(srcPackage + "service\\" + tableName + "Service.java"), createService(tableName));
        //创建ServiceImpl
        System.out.println("创建ServiceImpl");
        createFile(new File(srcPackage + "service\\impl\\" + tableName + "ServiceImpl.java"), createServiceImpl(tableName));
        //创建Controller
        System.out.println("创建Controller");
        createFile(new File(srcPackage + "controller\\" + tableName + "Controller.java"), createController(tableName));
    }


}