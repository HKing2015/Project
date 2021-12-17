package com.huangjin.testio;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.ForkJoinPool;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.activation.MimetypesFileTypeMap;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;

import com.google.common.base.Charsets;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.huangjin.domain.Aaa;
import com.huangjin.domain.AaaCopy;
import com.huangjin.domain.Bbb;
import com.huangjin.domain.City;
import com.huangjin.domain.Daughter;
import com.huangjin.domain.Father;
import com.huangjin.domain.Son;
import com.huangjin.domain.User;
import com.huangjin.helper.SpringContextHolder;
import com.huangjin.state.DistributeContext;
import com.huangjin.state.DistributeStateConfigFinish;
import com.huangjin.util.ConvertUtil;
import com.huangjin.util.DateUtil;
import com.huangjin.util.DownUtils;
import com.huangjin.util.IOUtils;
import com.huangjin.util.JsonConvertUtil;
import com.huangjin.util.Md5Util;
import com.huangjin.util.RSAUtils;
import com.huangjin.util.RsaUtil4Aliyun;
import com.huangjin.util.TimeUtil;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.quartz.CronExpression;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import static java.math.BigDecimal.ROUND_DOWN;
import static java.util.regex.Pattern.compile;

/**
 * Created by huang on 2016-11-8.
 */
@Slf4j
@Component
public class TestTest {
    public static void main(String[] args) {
        try {
            BufferedInputStream in = new BufferedInputStream(System.in);

            byte[] bytes = new byte[10];
            int bytesReads = in.read(bytes);

            while(bytesReads != -1) {
                String str = new String(bytes);
                if(str.substring(0, 4).equals("exit")) {
                    System.exit(0);
                } else {
                    System.out.println(str.substring(0, bytesReads));
                }

                bytesReads = in.read(bytes);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testFileReader() {
        FileReader f = null;
        try {
            StringBuffer str = new StringBuffer();
            char[] buf = new char[1024];
            f = new FileReader("C:\\Users\\huang\\Desktop\\sdf.txt");
            while(f.read(buf) > 0) {
                str.append(buf);
            }
            str.toString();
            System.out.println(str);
            f.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }



    @Test
    public void testFilter() {
        PrintStream ps = null;
        try {
            FileOutputStream fos = new FileOutputStream("C:\\Users\\huang\\Desktop\\sdf.txt", true);
            ps = new PrintStream(fos);
            ps.println("123");
            ps.println(new TestTest());
            ps.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testFileWriter() {
        try {
            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter("C:\\Users\\huang\\Desktop\\sdf.txt", true);
            writer.write("321");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBasic() throws IOException {
        File file = new File("C:\\Users\\huang\\Desktop\\sdf.txt");
        File tmpFile = new File("C:\\Users\\huang\\Desktop\\ddd.txt");
        FileUtils.copyFile(file, tmpFile); //生成临时文件

        System.out.println(file.getName());
    }


    @Test
    public void testTime() {
        Calendar calendarStart = Calendar.getInstance();
        calendarStart.set(2018, 2, 1, 1, 1, 1);
        long start = calendarStart.getTimeInMillis();

        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.set(2018, 3, 1, 1, 1, 1);
        long end = calendarEnd.getTimeInMillis();

        System.out.println(start);
        System.out.println(end);
    }

    @Test
    public void testJoin() {
        System.out.println(Joiner.on(",").join(Lists.newArrayList(0)) + ",0");
    }

    @Test
    public void test() {
        Map<Long, Long> testMap = Maps.newHashMap();
        testMap.put(1L, 2L);
        testMap.put(3L, 4l);
        List<Long> editGoodsIds = Lists.newArrayList();
        for(Map.Entry<Long, Long> entry : testMap.entrySet()) {
            editGoodsIds.add(entry.getValue());
        }
        System.out.println(editGoodsIds);
    }

    @Test
    public void testStream() {
        Date now = new Date();

        List<User> userList = Lists.newArrayList();
        User user1 = new User("1day", "1", DateUtil.getNDaysBefore(1));
        User user2 = new User("6day", "2", DateUtil.getNDaysBefore(6));
        User user3 = new User("3day", "3", DateUtil.getNDaysBefore(3));
        User user4 = new User("5day", "4", DateUtil.getNDaysBefore(5));
        User user5 = new User("4day", "5", DateUtil.getNDaysBefore(4));
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        userList.add(user5);
        Map<String, List<User>> userMap = userList.stream().collect(Collectors.groupingBy(User::getUsername));
        System.out.println(userMap);

        List<String> passwordList = Lists.newArrayList();
        passwordList.addAll(userList.stream().map(User::getPassword).collect(Collectors.toList()));
        System.out.println(passwordList);


        userList = userList.stream().sorted(Comparator.comparing(User::getPassword).reversed()).collect(Collectors.toList());
        for(User user : userList) {
            System.out.println(user.getUsername() + "---" + user.getPassword());
        }


        userList = userList.stream().sorted(Comparator.comparing(User::getDate)).collect(Collectors.toList());
        for(User user : userList) {
            System.out.println(user.getUsername());
        }

    }

    @Test
    public void testJsoup() {
        String html = "<!DOCTYPE html>\n" +
                "<html lang=\"zh-CN\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "    <title></title>\n" +
                "</head>\n" +
                "    <body>\n" +
                "        <div>\n" +
                "            <form name=\"itemForm\" id=\"itemForm\" method=\"post\" action=\"/uploadAndSaveImg\" enctype=\"multipart/form-data\">\n" +
                "                <input name=\"imgFile\" type=\"file\" multiple=\"multiple\">\n" +
                "                <input type=\"submit\" value=\"确定\">\n" +
                "                <p>\n" +
                "                    <video src=\"/i/movie.ogg\" controls=\"controls\">\n" +
                "                        your browser does not support the video tag\n" +
                "                    </video>\n" +
                "\n" +
                "                    <video src=\"/i/movie.hehe\" controls=\"controls\">\n" +
                "                        your browser does not support the video tag\n" +
                "                    </video>\n" +
                "                </p>\n" +
                "            </form>\n" +
                "        </div>\n" +
                "    </body>\n" +
                "</html>";


        String html2 = "<br/>\n" +
                "<div data-filter=\"allStyle\"></div>\n" +
                "<div>\n" +
                "  <img src=\"//pop.nosdn.127.net/4aefd357-d205-48eb-8a02-052547b3b965\" width=\"100%\">\n" +
                "  <br/>\n" +
                "  <br/></div>\n" +
                "<img src=\"http://pop.nosdn.127.net/7ef74df4-cfb0-4539-9618-2af2f954229d\" style=\"width: 100%; height: 100%;\">\n" +
                "<img src=\"http://pop.nosdn.127.net/ab9954f8-989a-4a29-a0cf-5790df9e7c40\" style=\"width: 100%; height: 100%;\">\n" +
                "<img src=\"http://pop.nosdn.127.net/f893c0cb-1e06-4a3b-9dfe-09d64d4b39a8\" style=\"width: 100%; height: 100%;\">\n" +
                "<img src=\"http://pop.nosdn.127.net/e647144a-a907-4689-b170-c9b4382fe02f\" style=\"width: 100%; height: 100%;\">\n" +
                "<img src=\"http://pop.nosdn.127.net/1fc300e1-46b3-4b01-b0cb-72a40b270a2d\" style=\"width: 100%; height: 100%;\">\n" +
                "<img src=\"http://pop.nosdn.127.net/cfb241f1-1d78-4a0e-8b16-b89d10347724\" style=\"width: 100%; height: 100%;\">\n" +
                "<img src=\"http://pop.nosdn.127.net/5374a5b8-742a-40dd-bcfc-79ec4d6d19e3\" style=\"width: 100%; height: 100%;\">\n" +
                "<img src=\"http://pop.nosdn.127.net/49845161-d484-4fe8-8afc-53f0fd4506e2\" style=\"width: 100%; height: 100%;\">\n" +
                "<img src=\"http://pop.nosdn.127.net/fa80e9d3-dfc1-4f46-8225-5feaa6fc1e03\" style=\"width: 100%; height: 100%;\">\n" +
                "<img src=\"http://pop.nosdn.127.net/3aa29ec1-1c5a-4bee-bcaa-89e85bcc36ab\" style=\"width: 100%; height: 100%;\">\n" +
                "<img src=\"http://pop.nosdn.127.net/586b3aaa-cea2-4565-b441-8e4d306520cc\" style=\"width: 100%; height: 100%;\">\n" +
                "<img src=\"http://pop.nosdn.127.net/462c464a-9e34-4303-a672-a479fb97549d\" style=\"width: 100%; height: 100%;\">\n" +
                "<img src=\"http://pop.nosdn.127.net/82746253-e696-4b78-aaa1-921c9ba25b9e\" style=\"width: 100%; height: 100%;\">\n" +
                "<img src=\"http://pop.nosdn.127.net/7c2ce377-7e75-49c1-a075-534b488537d9\" style=\"width: 100%; height: 100%;\">\n" +
                "<img src=\"http://pop.nosdn.127.net/ccca320d-a34b-4faf-89ef-13400e24ce98\" style=\"width: 100%; height: 100%;\">\n" +
                "<img src=\"http://pop.nosdn.127.net/9f610706-fa38-4c97-b986-2e2d987ab970\" style=\"width: 100%; height: 100%;\">\n" +
                "<img src=\"http://pop.nosdn.127.net/a18f8927-fc2a-4481-91e6-e4547ca6a012\" style=\"width: 100%; height: 100%;\">\n" +
                "<img src=\"http://pop.nosdn.127.net/5920b790-3c3f-456b-9743-9bb6f9c516dc\" style=\"width: 100%; height: 100%;\">\n" +
                "<img src=\"http://pop.nosdn.127.net/bc25d43c-2770-41b0-aba0-627b6deb28ef\" style=\"width: 100%; height: 100%;\">\n" +
                "<img src=\"http://pop.nosdn.127.net/d39094be-2663-4ead-aaad-19495e058fcc\" style=\"width: 100%; height: 100%;\">\n" +
                "<img src=\"http://pop.nosdn.127.net/c2be0ff9-903a-4407-97a5-dde40a5e7a12\">";

        Document doc = Jsoup.parse(html2);
        Elements imgElements = doc.select("img[src]");
        for(Element element : imgElements) {
            String src = element.attr("src");
            if(src.equals("http://pop.nosdn.127.net/7ef74df4-cfb0-4539-9618-2af2f954229d")) {
                element.remove();
            }

            if(src.equals("http://pop.nosdn.127.net/c2be0ff9-903a-4407-97a5-dde40a5e7a12")) {
                element.attr("src", "aaa");
            }
        }
        System.out.println(doc.toString());
    }


    @Test
    public void test17() {
        try {
            String filePath = this.getClass().getResource("/offshelf.txt").getPath();
            List<String> lines = IOUtils.readFile(filePath);
            for(String str : lines) {
                System.out.println(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test
    public void test18() {
        String filePath = this.getClass().getResource("/").getPath();
        System.out.println(filePath);
    }

    @Test
    public void test19() {
        PrintStream psError = null;
        PrintStream psInfo = null;

        try {
            String sourcesPath = this.getClass().getResource("/").getPath();

            FileOutputStream fosError = new FileOutputStream(sourcesPath + "errorLog.log", true); //错误日志文件
            FileOutputStream fosInfo = new FileOutputStream(sourcesPath + "runLog.log", true); //日志文件

            psError = new PrintStream(fosError);
            psInfo = new PrintStream(fosInfo);

            psError.println("123");
            psInfo.println("123");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            psError.close();
            psInfo.close();
        }
    }

    @Test
    public void test20() {
        User use = new User();
        System.out.println(use.getId() == 0);
    }

    @Test
    public void test21() {
        List<Integer> list1 = Lists.newArrayList(1,2,3,4,5);

        List<Integer> list2 = Lists.newArrayList(1,2,3,4,5);

        list1.removeAll(list2);

        System.out.println(list1);
        System.out.println(list2);
    }

    @Test
    public void test22() {
        List<User> users = Lists.newArrayList();
        User user1 = new User("1", "2");
        User user2 = new User("3", "4");
        users.add(user1);
        users.add(user2);

        Map<String, User> userMap = Maps.uniqueIndex(users, new Function<User, String>() {
            @Override
            public String apply(User user) {
                return user.getUsername();
            }
        });

        for(Map.Entry<String, User> entry : userMap.entrySet()) {
            System.out.println(entry.getKey());
        }
    }

    @Test
    public void test23() {
        String a = "1";
        String b = "2";
        Set<String> aaa = Sets.newHashSet();
        System.out.println(aaa.add(a));
        System.out.println(aaa.add(b));
        System.out.println(aaa.add(a));
    }

    @Test
    public void test24() {
        long timeMills = System.currentTimeMillis();
        System.out.println(String.valueOf(timeMills));
        System.out.println(RandomUtils.nextInt(1, 1000));

    }

    @Test
    public void test25() {
        String s1 = new String("aaa");
        String s2 = "aaa";
        System.out.println(s1 == s2);    // false

        s1 = new String("bbb").intern();
        s2 = "bbb";
        System.out.println(s1 == s2);    // true

        s1 = "ccc";
        s2 = "ccc";
        System.out.println(s1 == s2);    // true

        s1 = new String("ddd").intern();
        s2 = new String("ddd").intern();
        System.out.println(s1 == s2);    // true

        s1 = "ab" + "cd";
        s2 = "abcd";
        System.out.println(s1 == s2);    // true

        String temp = "hh";
        s1 = "a" + temp;
        // 如果调用s1.intern 则最终返回true
        s2 = "ahh";
        System.out.println(s1 == s2);    // false

        temp = "hh".intern();
        s1 = "a" + temp;
        s2 = "ahh";
        System.out.println(s1 == s2);    // false

        temp = "hh".intern();
        s1 = ("a" + temp).intern();
        s2 = "ahh";
        System.out.println(s1 == s2);    // true

        s1 = new String("1");    // 同时会生成堆中的对象 以及常量池中1的对象，但是此时s1是指向堆中的对象的
        s1.intern();            // 常量池中的已经存在
        s2 = "1";
        System.out.println(s1 == s2);    // false

        String s3 = new String("1") + new String("1");    // 此时生成了四个对象 常量池中的"1" + 2个堆中的"1" + s3指向的堆中的对象（注此时常量池不会生成"11"）
        s3.intern();    // jdk1.7之后，常量池不仅仅可以存储对象，还可以存储对象的引用，会直接将s3的地址存储在常量池
        String s4 = "11";    // jdk1.7之后，常量池中的地址其实就是s3的地址
        System.out.println(s3 == s4); // jdk1.7之前false， jdk1.7之后true

        s3 = new String("2") + new String("2");
        s4 = "22";        // 常量池中不存在22，所以会新开辟一个存储22对象的常量池地址
        s3.intern();    // 常量池22的地址和s3的地址不同
        System.out.println(s3 == s4); // false

        // 对于什么时候会在常量池存储字符串对象，我想我们可以基本得出结论: 1. 显示调用String的intern方法的时候; 2. 直接声明字符串字面常量的时候，例如: String a = "aaa";
        // 3. 字符串直接常量相加的时候，例如: String c = "aa" + "bb";  其中的aa/bb只要有任何一个不是字符串字面常量形式，都不会在常量池生成"aabb". 且此时jvm做了优化，不会同时生成"aa"和"bb"在字符串常量池中
    }

    @Test
    public void test26() {
        String str = "huangjin111";
        for (int i = str.length(); --i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                System.out.println(false);
            }
        }
    }

    @Test
    public void test27() {
        Set<Integer> sets = Sets.newHashSet(1,2,3,4,5,6,7,8,9,0);

        List<List<Integer>> list = Lists.partition(Lists.newArrayList(sets), 10);

        System.out.println(list);

    }


    @Test
    public void test28() {
        List<User> users = Lists.newArrayList();
        User user1 = new User("huangjin", "1");
        User user2 = new User("huangjin", "2");
        User user3 = new User("huangjin", "3");
        User user4 = new User("lmf", "4");
        User user5 = new User("lmf", "5");
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);

        for(User user : users) {
            user.setUsername("hehe");
        }

        for(User user : users) {
            System.out.println(user.toString());
        }
    }

    @Test
    public void test29() {
        Map<String, Integer> map = Maps.newHashMap();
        map.put("1-1", 1);
        map.put("1-2", 1);
        map.put("2-1", 2);
        map.put("3-1", 3);

        List<Integer> list = Lists.newArrayList(map.values());
        Set<Integer> set = Sets.newHashSet(map.values());
        System.out.println(list);
        System.out.println(set);
    }


    @Test
    public void test31() {
        Object ddd = 2018435.0;

        System.out.println(Math.round(Double.parseDouble(ddd.toString())));
    }

    @Test
    public void test33() {
        String url = "/inperoid/query/**";
        String matchUrl = "/inperoid/query/";

        AntPathMatcher pathMatcher = new AntPathMatcher();
        System.out.println(pathMatcher.match(url, matchUrl));
    }

    @Test
    public void test35() {
        int threads = Math.min(Runtime.getRuntime().availableProcessors() * 2, 10);
        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println(threads);
    }

    @Test
    public void test37() throws IOException {
        List<String> list = IOUtils.readFile("C:\\Users\\huang\\Downloads\\bbb.sql");
        FileWriter fw = new FileWriter("C:\\Users\\huang\\Downloads\\ddd.sql", true);
        BufferedWriter bw = new BufferedWriter(fw);
        for(String str : list) {
            String goodsId = str.substring(0, str.indexOf("\t"));
            String title = str.substring(str.indexOf("\t") + 1);

            String udpateSql = "update tb_goods_label_edit set invoice_title = '" + title + "' where edit_goods_id = " + goodsId + ";";
            bw.write(udpateSql);
            bw.newLine();
        }

        bw.close();
        fw.close();
    }

    @Test
    public void test337() throws IOException {
        List<String> list = IOUtils.readFile("C:\\Users\\huang\\Downloads\\qqqqq.sql");
        FileWriter fw = new FileWriter("C:\\Users\\huang\\Downloads\\updateForbidRule.sql", true);
        BufferedWriter bw = new BufferedWriter(fw);
        for(String str : list) {
            String id = str.substring(0, str.indexOf("\t"));
            String businessType = str.substring(str.lastIndexOf("\t") + 1);

            String udpateSql = "update tb_forbid_rule set business_type = " + businessType + " where id = " + id + ";";
            bw.write(udpateSql);
            bw.newLine();
        }

        bw.close();
        fw.close();
    }

    @Test
    public void test339() throws IOException {
        List<String> list = IOUtils.readFile("/Users/huangjin/Downloads/aaaaa.txt");
        Set<String> set = Sets.newHashSet();
        set.addAll(list);

        for(String setStr : set) {
            System.out.println(setStr);
        }
    }

    /**
     * Lists.newArrayList如果参数是null, 则会生成一个包含null的list, 而不是一个空list
     */
    @Test
    public void test39() {
        Map<Integer, Long> map = Maps.newHashMap();
        long l = map.get(123);

        List list = Lists.newArrayList(l);
        List list2 = Arrays.asList(l);
        System.out.println(list);
    }

    /**
     * new一个对象, 不会new里面的对象
     */
    @Test
    public void test41() {
        User use = new User();
        System.out.println(use);
        System.out.println(use.isMan());
    }

    /**
     * 计算Date类型时间差(天数)
     * @throws ParseException
     */
    @Test
    public void test43() throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = df.parse("2018-06-06 11:30:24");
        Long offShelfDays = (new Date().getTime() - time.getTime()) / (1000 * 60 * 60 * 24);
        System.out.println(offShelfDays);

    }


    @Test
    public void test44() {
        boolean result = TimeUtil.isValidDate("2019-01-19 12:00:00", "yyyy-MM-dd HH:mm:ss");
        System.out.println(result);
    }

    @Test
    public void test45() {
        String img = "http://r1.ykimg.com/050B00005BD17EEEADA7B2063E038B0D";
        if(img.indexOf(".com/") != -1) {

            Integer index = img.indexOf(".com/") + 5;

            String imgUrl = img.substring(0, index);
            System.out.println(imgUrl);
            String imgId = img.substring(index);
            System.out.println(imgId);

            if(imgId.startsWith("050")) {
                imgId = "050C" + imgId.substring(4);
            } else if(imgId.startsWith("054")) {
                imgId = "0541" + imgId.substring(4);
            }

            String replaceImg = imgUrl + imgId;
            System.out.println(replaceImg);
        }
    }

    @Test
    public void test47() throws IOException {
        List<String> list = IOUtils.readFile("C:\\Users\\huang\\Downloads\\1.txt");
        FileWriter fw = new FileWriter("C:\\Users\\huang\\Downloads\\2.sql", true);
        BufferedWriter bw = new BufferedWriter(fw);

        String sql = "insert into tb_forbid_rule (id, supplier_id, brand_id, category_id, edit_goods_id, forbid_reason, status, type, operator) values ";
        bw.write(sql);
        bw.newLine();
        for(String str : list) {
            String insert = "(seq," + str + ",";
            insert = insert + "0,0,0,'贸易模式不合规人工禁发',3,1,'operator'),";
            bw.write(insert);
            bw.newLine();
        }
        bw.close();
        fw.close();
    }

    @Test
    public void test49() {
        String str = "1,2,3,4,5";

        List<String> strList = Arrays.asList(str);

        System.out.println(strList);
    }

    @Test
    public void test51() {
        String str = "我是中国人";
        System.out.println(str.length());

        String str2 = "111abc";
        System.out.println(str2.length());

        String str3 = "  sd sd d  ";
        System.out.println("|" + str3.trim() + "|");
    }

    @Test
    public void test53() {
        User user = new User();
        System.out.println(user.getId());
        System.out.println(user.getMoney());

    }


    @Test
    public void test55() {
        Object obj = null;
        String str = (String)obj;
        System.out.println(str);
    }

    @Test
    public void test57() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 0);
        System.out.println(cal.getTime());

    }

    @Test
    public void test59() throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = df.parse("2018-06-06 11:11:24");

        Calendar c1 = Calendar.getInstance();
        c1.setTime(time);

        Calendar now = Calendar.getInstance(); //当前时间
        Integer timeDiffHour = now.get(Calendar.HOUR_OF_DAY) - c1.get(Calendar.HOUR_OF_DAY);
        Integer timeDiffMinute = now.get(Calendar.MINUTE) - c1.get(Calendar.MINUTE);
        System.out.println(timeDiffHour);
        System.out.println(timeDiffMinute);
    }

    @Test
    public void test65() throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = sdf.parse("2019-07-26 00:00:00");
        System.out.println(now.getTime());

        String value0 = (new Timestamp(1558593599000L)).toString();
        String value1 = (new Timestamp(1565076007000L)).toString();
        String value2 = (new Timestamp(1565076002000L)).toString();
        System.out.println(value0);
        System.out.println(value1);
        System.out.println(value2);

        System.out.println(new Date((long)Integer.MAX_VALUE*1000));

        System.out.println(Integer.MAX_VALUE);

        System.out.println((long)Integer.MAX_VALUE*1000);
    }


    @Test
    public void test67() throws IOException {
        BufferedWriter goodsIdsWriter = new BufferedWriter(new FileWriter(new File("C:\\Users\\huang\\Downloads\\hehehehehe.txt")));

        List<Long> list = Lists.newArrayList();
        for(Long i = 1L; i < 100000; i++) {
            list.add(i);
        }

        for (Long item : list) {
            if(item % 1000 == 0) {
                System.out.println("currently @ " + item);
            }

            goodsIdsWriter.write(item + "");
            goodsIdsWriter.write(System.getProperty("line.separator"));
        }
        goodsIdsWriter.close();
    }


    @Test
    public void test69() {
        System.out.print(1 + System.getProperty("line.separator") + 2);
    }

    @Test
    public void test71() {
        System.out.println(this.getClass().getClassLoader().getResource("").getPath());
    }

    @Test
    public void test73() {
        Pattern p = Pattern.compile("(满.*减)|(满.*折)|(件.*折)|(半价)");
        Matcher m = p.matcher("折叠椅"); //要验证的字符串
        System.out.println(m.find());
    }

    @Test
    public void test77() {
        BigDecimal ChinaGDP = new BigDecimal(13.00);
        BigDecimal USAGDP = new BigDecimal(20.00);

        int year = 2019;
        for(int i = 1; i < 33; i++) {
            ChinaGDP = ChinaGDP.multiply(BigDecimal.valueOf(1.06)).setScale(2, ROUND_DOWN);
            USAGDP = USAGDP.multiply(BigDecimal.valueOf(1.02)).setScale(2, ROUND_DOWN);
            System.out.println(year + "---China:" + ChinaGDP + "---USA:" + USAGDP);
            year++;
        }
    }

    @Test
    public void test79() {

        String str ="中华人民共(和国)，简称(中国)。";

        Matcher mat = Pattern.compile("(?<=\\()(\\S+)(?=\\))").matcher(str);
        while(mat.find()){
            System.out.println(mat.group());
        }

        String result = str.substring(str.lastIndexOf("(") + 1, str.lastIndexOf(")"));
        System.out.println(result);
    }

    @Test
    public void test81() {
        Integer x = new Integer(123);
        Integer y = new Integer(123);
        System.out.println(x == y);    // false
        System.out.println(x.equals(y)); //true
        Integer z = Integer.valueOf(123);
        Integer k = Integer.valueOf(123);
        System.out.println(z == k);   // true

        Integer a = 1111;
        Integer b = 1111;
        System.out.println(a == b);
    }

    @Test
    public void test87() throws IOException {
        IOUtils.clearInfoForFile("C:\\Users\\huang\\Desktop\\qqqqq.sql");

        File file = new File("C:\\Users\\huang\\Desktop\\qqqqq.sql");
        FileOutputStream fosInfo = new FileOutputStream(file, true); //日志文件
        PrintStream psInfo = new PrintStream(fosInfo);
        psInfo.println(100);
        psInfo.println(300);
        psInfo.close();
    }

    @Test
    public void test89() {
        Map<Integer, List<Long>> map = Maps.newHashMap();
        List<Long> list = map.get(1);

        for(int i = 0; i < 3; i++) {
            if(CollectionUtils.isEmpty(list)) {
                list = Lists.newArrayList(2L);
                map.put(1, list);
            } else {
                list.add(3L);
            }
        }

        System.out.println(map.get(1));
    }

    @Test
    public void test91() {
        List<Integer> list = Lists.newArrayList(1,2,3);
        list.removeAll(null);
        System.out.println(list);
    }

    @Test
    public void test93() {
        List<Integer> list = Lists.newArrayList(1,2,3,4,5,6,7,8,9);
        List<Integer> list2 = Lists.newArrayList(1,2,3);
        Iterator<Integer> iterator = list2.iterator();

        while(iterator.hasNext()) {
            Integer i = iterator.next();
            if(i == 3) {
                list.remove(i);
            }
        }
        System.out.println(list);
    }

    @Test
    public void test95() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");

        String dateStr = "2020年05月01日";

        try {
            Date date = sdf.parse(dateStr);

            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test97() {
        String url = "https://pop.nosdn.127.net/6d4f9342-101b-4c74-b135-f967f686c048";
        if(url.startsWith("http:")) {
            url = url.substring(5);
        } else if(url.startsWith("https:")) {
            url = url.substring(6);
        }
        System.out.println(url);
    }

    @Test
    public void test99() throws IOException {
        final File file = new File("C:\\Users\\huang\\Desktop\\ddd.txt");
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(file);
            inputStream.close();
            System.out.println(file.getName());
            new Thread(() -> {
                try {
                    Thread.sleep(5000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
            System.out.println("end");
        }
    }

    @Test
    public void test101() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        User user = new User();
        user.setDate(new Date());
        user.setId(1);
        user.setUsername("huangjin");
        Map<String, Object> param = BeanUtils.describe(user);

        for(Map.Entry<String, Object> entry : param.entrySet()) {
            System.out.println(entry.getKey() + "---" + entry.getValue());
        }
    }


    @Test
    public void test103() {
        String str = "huangjin(1919191)";
        String code = str.substring(str.lastIndexOf("(") + 1, str.lastIndexOf(")"));
        System.out.println(code);
    }

    @Test
    public void test105() {
        Set<String> set = Sets.newHashSet();
        set.add("1");
        set.add("2");
        set.add("3");

        System.out.println(StringUtils.join(set.toArray(), ","));
    }

    @Test
    public void test107() {
        String strFormat = "贵司【%s】%s公司，在考拉平台申请的“%s”品牌新建申请，已被审核拒绝；审核拒绝原因为：%s；请及时前往https://partner.kaola.com/ 登陆进入信息与权限-品牌资质管理菜单跟进处理；";

        String str = String.format(strFormat, 1, "2", "3", null);
        System.out.println(str);
    }


    @Test
    public void test109() {

        String url = "https://dianliuegg.youku.com/share/video/XNDI1MzIwMzgwOA==?routeType=FAXIAN&sharekey=710ca9597e0bb9c7ffb975cd104cb5ca7&refer=pgy_bd.yl151641_00003050_000000_iUvEzm_19071800";
        if (url.startsWith("http")) {

            if(url.contains("dianliu")) {
                Pattern p = compile("/([a-zA-Z0-9]*==)");
                Matcher m = p.matcher(url);
                if (m.find()) {
                    String urlVid = m.group(1);
                    if (StringUtils.isNotEmpty(urlVid)) {
                        System.out.println(urlVid);
                    }
                }
            }
        }
    }

    @Test
    public void test111() {
        Aaa aaa = new Aaa();
        System.out.println(aaa.getClass());

        Type type = aaa.getClass().getGenericSuperclass();
        System.out.println(type);

        ParameterizedType pt = (ParameterizedType) type;
        Type[] ts = pt.getActualTypeArguments();
        Class c = (Class) ts[0];
        System.out.println(c);
    }

    @Test
    public void test113() {

        List<String> lst = new ArrayList<String>();
        lst.add("aaa");
        lst.add("bbb");
        lst.add("ccc");
        lst.add("ddd");
        lst.add("eee");
        lst.add("fff");
        Iterator<String> iterator = lst.iterator();

        StringBuilder sb = new StringBuilder();
        //iterator.hasNext()如果存在元素的话返回true
        while(iterator.hasNext()) {
            //iterator.next()返回迭代的下一个元素

            sb.append(iterator.next());
            if(iterator.hasNext()) {
                sb.append(",");
            }
        }
        System.out.println(sb);
    }


    @Test
    public void test115() {
        String searchStr = "hehe";
        String str = "notin(video_source, \"" + searchStr + "\")";
        System.out.println(str);

        Map<String, String> map = Maps.newHashMap();

        String str1 = map.get(null);
        System.out.println(str1);

        Map<String, String> map2 = Maps.newConcurrentMap();
        String str2 = map2.get(null);
        System.out.println(str2);
    }


    @Test
    public void test119() {

        Date date = new Date();// 取时间
        Calendar calendar = new GregorianCalendar();

        calendar.setTime(date);

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        System.out.println(calendar.getTime());

    }

    @Test
    public void test121() {
        String str = "111.3";
        Double dou = Double.parseDouble(str);
        Long l = dou.longValue();
        System.out.println(l);


        String str1 = "hehexixixlalalal";
        System.out.println(str1.contains("hehe"));

    }

    @Test
    public void test123() {
        String str = "CCTV,天津卫视,CCTV-1,CCTV-2,CCTV-3,CCTV-4,CCTV-5,CCTV-6,CCTV-7,CCTV-8,CCTV-13";

        System.out.println(str.indexOf("CCTV-1"));

    }

    @Test
    public void test127() {
        Integer count = 10000;
        this.count(count);
        System.out.println(count);

        User user = new User();
        user.setId(1);
        this.setUser(user);
        System.out.println(user.getId());
    }


    private void count(Integer count) {
        count++;
    }

    private void setUser(User user) {
        user.setId(2);
    }

    @Test
    public void test129() throws Exception {
        List<String> list = IOUtils.readFile("/Users/huangjin/Documents/newToken.txt");
        FileWriter fw = new FileWriter("/Users/huangjin/Documents/newTokenUpdate.sql", true);
        BufferedWriter bw = new BufferedWriter(fw);

        for(String str : list) {

            String[] splitStr = str.split(",");
            if(splitStr.length != 2) {
                throw new Exception("长度不合法");
            }

            String uid = splitStr[0];
            String token = splitStr[1];

            String sql = "update t_pgy_userinfo_channel set access_token = '" + token + "' where channel = 'WEIBO' and uid = '" + uid + "'";
            bw.write(sql);
            bw.newLine();
        }
        bw.close();
        fw.close();
    }


    @Test
    public void test130() throws Exception {
        List<String> list = IOUtils.readFile("/Users/huangjin/Downloads/titleurl.txt");

        for(String str : list) {
            String[] splitStr = str.split(",");
            if(splitStr.length != 2) {
                throw new Exception("长度不合法");
            }

            String title = splitStr[0];
            String thumburl = splitStr[1];

            title = title.replace("\"", "");
            //System.out.println(title);

            thumburl = thumburl.replace("\"", "");
            //System.out.println(thumburl);

            String imgCode = thumburl.substring(thumburl.lastIndexOf("/") + 1) + ".jpg";
            //System.out.println(imgCode);

            List<File> files = IOUtils.getFiles("/Users/huangjin/Downloads/thumb");
            for(File file : files) {
                String fileName = file.getName();

                if(imgCode.equals(fileName)) {
                    IOUtils.fixFileName(file.toString(), title);
                }
            }
        }

    }


    @Test
    public void test131() {
        List<String> list = null;

        for(String str : list) {
            System.out.println(str);
        }
    }


    @Test
    public void test133() {
        File file = new File("/Users/huangjin/Documents/1416eZ4YPugjZh5R-VSVideo-Merged.mp4");
        String contentType = new MimetypesFileTypeMap().getContentType(file);

        System.out.println(contentType);
    }

    @Test
    public void test135() {
        Date date = new Date();

        String dateTime = DateUtil.formatDate(date, DateUtil.DATE_TIME_FULL2);

        System.out.println(dateTime);
    }

    @Test
    public void test137() {
        String weiboText = "#未来演技派#@辣目洋子 转型做演员！着实期待！之前看过一些短片就觉得小姐姐演技不错！果然，期待作为演员洋子未来的发展！http://t.cn/Ai1NcvKU";

        Pattern topicTagPattern = Pattern.compile("#([^#]{1,40})#");

        Set<String> topicList = new LinkedHashSet<>();

        Matcher matcher = topicTagPattern.matcher(weiboText);
        while (matcher.find()){
            String topicName = matcher.group(1);
            topicList.add(topicName);
        }
        System.out.println(topicList);
    }

    @Test
    public void test139() {
        User user = new User();
        user.setUsername("huangjin");

        this.setUserName(user);
        System.out.println(user.getUsername());
    }

    private void setUserName(User user) {
        user.setUsername("fangfang");
    }


    @Test
    public void test141() {
        //开始时间
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 30);

        Integer startMonth = calendar.get(Calendar.MONTH) + 1;
        Integer startDate = calendar.get(Calendar.DATE);
        String startTimeStr = startMonth + "月" + startDate + "日";

        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 3);
        Integer endMonth = calendar.get(Calendar.MONTH) + 1;
        Integer endDate = calendar.get(Calendar.DATE) + 1;
        String endTimeStr = endMonth + "月" + endDate + "日";

        String winDesc = startTimeStr + "-" + endTimeStr;

        System.out.println(winDesc);
    }


    @Test
    public void test143() {
        StringBuilder sb = new StringBuilder();
        sb.append("ing");
        sb.append("");

        System.out.println(sb.length());
    }

    @Test
    public void test145() {
        String rule = "[{\"ruleCode\":\"hehe\",\"ruleInfo\":{\"start_time\":111,\"end_time\":222}},"
            + "{\"ruleCode\":\"xixi\",\"ruleInfo\":{\"dayExp\":111,\"peopleExp\":222}}]";

        List<JSONObject> list = JSON.parseArray(rule, JSONObject.class);

        for(JSONObject jsonObject : list) {

            for(Entry entry : jsonObject.entrySet()) {
                System.out.println(entry.getKey());
                System.out.println(entry.getValue());
            }
        }
    }

    @Test
    public void test147() {
        ForkJoinPool pool = ForkJoinPool.commonPool();

        System.out.println(pool.getPoolSize());
    }

    @Test
    public void test149() throws IOException {
        List<String> list = IOUtils.readFile("/Users/huangjin/Downloads/test.json");
        for(String item : list) {
            if(!item.startsWith("{")) {
                continue;
            }
            JSONObject jsonObject = JSONObject.parseObject(item);
            System.out.println(item);
        }
    }

    @Test
    public void test151() throws IOException {

        String item = "{\"genYkPasswordParam\":\"{\\\"targetUrl\\\":\\\"https://v.youku"
            + ".com/v_show/id_XMzQwNjg1MjMyNA==.html?sharefrom=iphone&sharekey=8af3837814a1efff60bc2be76beadf6f9\\\","
            + "\\\"sourceType\\\":\\\"activity\\\",\\\"title\\\":\\\"烈火如歌\\\",\\\"picUrl\\\":\\\"http://vthumb.ykimg"
            + ".com/0541010159A8419F8B324C89F57D3319\\\",\\\"bizId\\\":\\\"common\\\","
            + "\\\"videoId\\\":\\\"XMzAwMDYxMTM0NA==\\\",\\\"watchCount\\\":\\\"100000\\\","
            + "\\\"btnName\\\":\\\"立即观看\\\",\\\"openAppName\\\":\\\"优酷\\\","
            + "\\\"extendInfo\\\":\\\"{\\\\\\\"cookie\\\\\\\":\\\\\\\"1;url=ddddd;name=value\\\\\\\", "
            + "\\\\\\\"taskId\\\\\\\": \\\\\\\"demoTask\\\\\\\", \\\\\\\"kk2\\\\\\\": \\\\\\\"vv2\\\\\\\", "
            + "\\\\\\\"kk3\\\\\\\": \\\\\\\"vv3\\\\\\\"}\\\"}\",\"clientInfo\":\"{\\\"userId\\\":0,\\\"ip\\\":\\\"127"
            + ".0.0.1\\\",\\\"appKey\\\":\\\"123\\\",\\\"ttid\\\":\\\"\\\",\\\"appVersion\\\":\\\"\\\","
            + "\\\"platform\\\":\\\"\\\",\\\"appName\\\":\\\"aplatform\\\",\\\"email\\\":\\\"zixuan.yfz@taobao"
            + ".com\\\"}\"}";
        JSONObject jsonObject = JSONObject.parseObject(item);
        System.out.println(jsonObject);

    }

    @Test
    public void test153() throws IOException {
        Long l = 3210873l;
        Double d = l.doubleValue();

        System.out.println(d/(1000*1000));
        }

        @Test
        public void test155() {
        String str = "成功. retMsg={\"data\":{\"description\":\"\",\"error_code\":0,"
            + "\"item_id\":\"@9VwVhfbHX54gbHCuNN0xQs780mztOvmAPZR1oQKlKVEaZvf460zdRmYqig357zEBK+218yZD2CKkhoXn4WpTyg"
            + "==\"},\"extra\":{\"logid\":\"202002181715590100140471940412AEBC\",\"now\":1582017359979}}";

        String str1 = str.substring(str.indexOf("retMsg=") + 7);

        System.out.println(str1);
    }

    @Test
    public void test157() {
        String json = "{\"data\":{\"description\":\"\",\"error_code\":0,\"list\":[{\"cover\":\"https://p9-dy.byteimg"
            + ".com/img/tos-cn-p-0015/defd31dd368a468da5a9b05af25e91e7_1581846673~c5_300x400"
            + ".jpeg?from=2563711402_large\",\"create_time\":1581846667,\"is_reviewed\":true,\"is_top\":false,"
            + "\"item_id\":\"@9VwVhfbHX54gbHCuNN0xQs780mzqNP+LPpZyqAqjL1QUbvL760zdRmYqig357zEBEu9pgEGtvxzC4CdxD1E7Wg"
            + "==\",\"share_url\":\"https://www.iesdouyin"
            + ".com/share/video"
            +
            "/QDlWd1ZoZmJIWDU0Z2JIQ3VOTjB4UXM3ODBtenFOUCtMUHBaeXFBcWpMMVFVYnZMNzYwemRSbVlxaWczNTd6RUJFdTlwZ0VHdHZ4ekM0Q2R4RDFFN1dnPT0=/?region=CN&mid=6793964200273300231&u_code=gh7me18g&titleType=title\",\"statistics\":{\"comment_count\":19,\"digg_count\":419,\"download_count\":0,\"forward_count\":2,\"play_count\":23640,\"share_count\":3},\"title\":\"#王牌对王牌 TFBOYS变身呆萌波斯猫，老阿姨们尖叫不已\"},{\"cover\":\"https://p3-dy.byteimg.com/img/tos-cn-p-0015/d15da1624e784a9cac2fdc82db303fa9_1581309908~c5_300x400.jpeg?from=2563711402_large\",\"create_time\":1581309903,\"is_reviewed\":true,\"is_top\":false,\"item_id\":\"@9VwVhfbHX54gbHCuNN0xQs780mzoO/+GOpNzrA+uKlcaafL660zdRmYqig357zEB5Hqiu6ESE+3J8HTUgIQ/CQ==\",\"share_url\":\"https://www.iesdouyin.com/share/video/QDlWd1ZoZmJIWDU0Z2JIQ3VOTjB4UXM3ODBtem9PLytHT3BOenJBK3VLbGNhYWZMNjYwemRSbVlxaWczNTd6RUI1SHFpdTZFU0UrM0o4SFRVZ0lRL0NRPT0=/?region=CN&mid=6791659247018101517&u_code=gh7me18g&titleType=title\",\"statistics\":{\"comment_count\":165,\"digg_count\":3719,\"download_count\":38,\"forward_count\":2,\"play_count\":184075,\"share_count\":26},\"title\":\"钟丽缇隔空大喊要给张伦硕生宝宝，弱弱的问一句这么大岁数还能生吗？\"}]},\"extra\":{\"logid\":\"2020021813140301000806508523D6F4E1\",\"now\":1582002844078}}";

        ReadContext readContext = JsonPath.parse(json);

        Integer errorCode = JsonConvertUtil.getJsonPathInt(readContext.read("$.data.error_code"));
        System.out.println(errorCode);

        int size = readContext.read("$.data.list.length()");
        System.out.println(size);

        String itemId = readContext.read("$.data.list[" + 1 + "].item_id");
        System.out.println(itemId);

        Integer commentCount = JsonConvertUtil.getJsonPathInt(readContext.read("$.data.list[" + 1 + "].statistics.comment_count"));
        System.out.println(commentCount);
    }


    @Test
    public void test159() {
        String json = "[{\"ruleCode\":\"MAX_EXP\",\"ruleInfo\":{\"userType\":\"alipayId\",\"dayMaxExp\":1,"
            + "\"peopleMaxExp\":10000}},{\"ruleCode\":\"MEGA_PEOPLE\",\"ruleInfo\":{\"userType\":\"alipayId\","
            + "\"megaPeopleIds\":\"41634\"}},{\"ruleCode\":\"NOT_LOGIN\",\"ruleInfo\":{\"userType\":\"ytid\"}},"
            + "{\"ruleCode\":\"CLIENT_TYPE\",\"ruleInfo\":{\"client\":\"ANDROID\"}}]";

        JSONArray jsonArray = JSONArray.parseArray(json);

        System.out.println(jsonArray);

        List<JSONObject> ruleDetail = Lists.newArrayList();
        if(!jsonArray.isEmpty()) {
            for(Object object : jsonArray) {
                ruleDetail.add((JSONObject)object);
            }
        }

        System.out.println(ruleDetail);
    }

    @Test
    public void test161() {
        String str = "12313213";
        System.out.println(NumberUtils.isDigits(str));

        String str1 = "12313213aa";
        System.out.println(NumberUtils.isDigits(str1));
    }

    @Test
    public void test163() throws ParseException {
        String cron = "* * 10-15 * * ? ";
        System.out.println(filterWithCronTime(cron));//true，我当前时间为15:36，
        System.out.println(filterWithCronTime(cron));//false，我当前时间为15:36，
    }

    /**
     * 校验在当前时间是否满足时间规则表达式
     * @param cron
     * @return
     * @throws ParseException
     */
    private  Boolean filterWithCronTime(String cron) throws ParseException {
        if(StringUtils.isBlank(cron)){
            return false;
        }
        CronExpression exp = new CronExpression(cron);
        Boolean inCron = exp.isSatisfiedBy(new Date());
        return inCron;
    }


    @Test
    public void test165() throws ParseException {
        String timeStr = "2019-07-01 15:06:23";
        String df = "yyyy-MM";
        SimpleDateFormat formatter = new SimpleDateFormat(df);

        Date date = formatter.parse(timeStr);

        System.out.println(date);

    }


    @Test
    public void test167() {

        String cdnUrl1 = "https://api.youku.com/videos/player/file?data=WE5UQXpORFExTkRNMk5BPT18MnwxfDEwMDgxNDd8MAO0O0OO0O0O";

        String filePrefix = "/Users/huangjin/Downloads/";

        //文件
        File file = null;


        file = DownUtils.createFile(filePrefix, "testFile03");
        DownUtils.download(cdnUrl1, file);
        //this.fileDelete(filePrefix + "testFile01");
        //file.delete();

    }



    @Test
    public void test169() {

        try {
            System.out.println(100/0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("-------");
            System.out.println(JSON.toJSONString(e.getStackTrace()));
            System.out.println("-------");
            System.out.println(JSON.toJSONString(e.getCause()));
            System.out.println("-------");
            System.out.println(e.getLocalizedMessage());
            System.out.println("-------");
            System.out.println(JSON.toJSONString(e.getSuppressed()));
        }
    }

    @Test
    public void test171() {

        String URL_ACCOUNT_FANS_DATA = "https://baijiahao.baidu.com/builder/author/statistic/getFansBasicInfo?start=%s&end=%s&sort=asc&is_page=0&b_app_id=%s&c_app_id=%s&show_type=chart";
        String url = String.format(URL_ACCOUNT_FANS_DATA, "aaa", "bbb", "ccc", "ddd");
        System.out.println(url);
    }


    @Test
    public void test173() {
        byte bb = 1;
        Byte b = bb;

        System.out.println(b == 1);
    }

    @Test
    public void test175()
        throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException,
        InvocationTargetException {

        Class<?> stateClass = Class.forName("com.huangjin.state.DistributeStateConfigFinish");
        //Object stateObj = stateClass.newInstance();
        //DistributeState state = (DistributeState)stateObj;


        //DistributeStateConfigFinish distributeStateConfigFinish =
        //    (DistributeStateConfigFinish) ApplicationContextGetBeanHelper.getBean("DistributeStateConfigFinish");

        SpringContextHolder springContextHolder = new SpringContextHolder();
        DistributeStateConfigFinish distributeStateConfigFinish = springContextHolder.getObj("distributeStateConfigFinish");
        System.out.println(distributeStateConfigFinish.getClass().getName());

        Method stateMethod = stateClass.getDeclaredMethod("save", DistributeContext.class);
        stateMethod.setAccessible(true);

        DistributeContext context = new DistributeContext("ConfigFinish", "save");
        context.setParam("lalal");

        stateMethod.invoke(distributeStateConfigFinish, context);

    }


    @Test
    public void test177()
        throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException,
        InvocationTargetException {

        DistributeContext context = new DistributeContext("ConfigFinish", "save");
        context.setParam("lalal");
        context.invoke();
    }


    @Test
    public void test181() {

        Aaa aaa = new Aaa();
        aaa.setCity(new City());

        Bbb bbb = aaa;

        AaaCopy aaaCopy = new AaaCopy();
        org.springframework.beans.BeanUtils.copyProperties(bbb, aaaCopy);

        System.out.println(JSON.toJSONString(aaaCopy));
    }


    @Test
    public void test183() {

        Son son = new Son();
        son.setsParam("sss");

        Father father = son;
        System.out.println(father instanceof Son);

        Daughter daughter = new Daughter();
        father = daughter;
        System.out.println(father instanceof Son);

        this.printprint((Son)father);

    }


    @Test
    public void test185() {

       String str = "2324231";
        System.out.println(str.hashCode());

    }

    @Test
    public void test187() {
        String str = "1,2,3,4,5,6,7";
        Iterable<String> split = Splitter.on(',').trimResults().omitEmptyStrings().split(str);
        System.out.println(split);
    }

    @Test
    public void test189() {
        String s1 = "{\"bg_pic\":\"https://gw.alicdn.com/tfs/TB1w.KXklFR4u4jSZFPXXanzFXa-339-83.png\",\"red_title\":\"0.3元/天\",\"action_type\":\"JUMP_TO_VIP_GUIDE\",\"link\":\"youku://payment/halfscreen?params={\\\"activityCode\\\":\\\"crm_nk5z\\\",\\\"biz\\\":\\\"default\\\",\\\"pageKey\\\":\\\"vip.trade.order.render.default\\\",\\\"attributes\\\":\\\"{\\\\\\\"crm_params\\\\\\\":{\\\\\\\\\\\\\\\"touch_point_code\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\"alipay_miniapp_home_banner\\\\\\\\\\\\\\\"}}\\\",\\\"products\\\":[{\\\"productId\\\":\\\"128\\\",\\\"promotions\\\":[{\\\"activityId\\\":\\\"10743\\\",\\\"receivingId\\\":\\\"0\\\"}],\\\"quantity\\\":1,\\\"skuId\\\":\\\"3\\\"}]}&crmCode=alipay_miniapp_home_banner&en_scm=20140732.0.0.crm_20140732-manual-142001_181001_612032_26-100051_100200_5088_1_808_197_1600936177754_3d6a16f5f0784e468b18e895f22d9104-syt_HALFSTANDARDRENDER&en_spm=null\",\"icon_pic\":\"https://gw.alicdn.com/tfs/TB12rqgU.z1gK0jSZLeXXb9kVXa-90-83.png\",\"tips\":\"超值\",\"highlight\":0,\"payType\":\"\",\"bubble\":0,\"cashierPageKey\":\"vip.trade.order.render.default\",\"product_id\":\"128\",\"activity_id\":\"10743\",\"product_sku_id\":\"128_3\",\"render_status\":1,\"crm_params\":\"{\\\"touch_point_code\\\":\\\"alipay_miniapp_home_banner\\\"}\",\"scm\":\"20140732.0.0.crm_20140732-manual-142001_181001_612032_26-100051_100200_5088_1_808_197_1600936177754_3d6a16f5f0784e468b18e895f22d9104-syt_HALFSTANDARDRENDER\",\"link_style\":1,\"sub_title\":\"超值价\",\"goods_id\":\"100051\",\"sku_id\":\"3\",\"activity_code\":\"crm_nk5z\",\"crm_d\":\"20140732-manual-142001_181001_612032_26-100051_100200_5088_1_808_197_1600936177754_3d6a16f5f0784e468b18e895f22d9104-syt_HALFSTANDARDRENDER\",\"black_title\":\"年卡低至\",\"act_title\":\"立即GO\",\"banner_type\":\"1\"}";

        JSONObject json = JSON.parseObject(s1);
        System.out.println(json);

        String s2 = StringEscapeUtils.unescapeJava(s1);
        String s3 = StringEscapeUtils.unescapeJava(s2);
        String s4 = StringEscapeUtils.unescapeJava(s3);
        String s5 = StringEscapeUtils.unescapeJava(s4);
        String s6 = StringEscapeUtils.unescapeJava(s5);
        //JSONObject obj1 = JSON.parseObject(s1);
        //JSONObject obj2 = JSON.parseObject(s2);
        //JSONObject obj3 = JSON.parseObject(s3);
        //JSONObject obj4 = JSON.parseObject(s4);
        //JSONObject obj5 = JSON.parseObject(s5);
        //JSONObject obj6 = JSON.parseObject(s6);
        System.out.println(s2);


        String aaa = "{&quot;funds&quot;:&quot;ILN032&quot;,&quot;meet_zf_rules_list&quot;:&quot;ILN032&quot;}";

        System.out.println(org.apache.commons.lang.StringEscapeUtils.unescapeHtml(aaa));
    }

    /**
     * 替换url里的某个参数
     * @throws MalformedURLException
     */
    @Test
    public void test191() throws MalformedURLException {
        String urlStr = "https://t.youku.com/app/ykvip_rax/yk-vip-cashier-plato/pages/index?wh_weex=true&sceneType=simpleScreen&h5params=%7B%22activityCode%22%3A%22crm_nk7z%22%2C%22biz%22%3A%22default%22%2C%22pageKey%22%3A%22vip.trade.order.render.default%22%2C%22attributes%22%3A%22%7B%5C%22crm_params%5C%22%3A%7B%5C%22touch_point_code%5C%22%3A%5C%22alipay_miniapp_home_banner%5C%22%7D%7D%22%2C%22products%22%3A%5B%7B%22productId%22%3A%22128%22%2C%22promotions%22%3A%5B%7B%22activityId%22%3A%2210745%22%2C%22receivingId%22%3A%220%22%7D%5D%2C%22quantity%22%3A1%2C%22skuId%22%3A%223%22%7D%5D%7D&hideNavigatorBar=true&crmCode=alipay_miniapp_home_banner&en_scm=20140732.0.0.crm_20140732-manual-142001_181001_612001_29-100053_100200_5088_1_808_197_1602571514298_251fce7305b84b7fb4a11fc3f6bd9564-syt_HALFSTANDARDRENDER&en_spm=null";
        URL url = new URL(urlStr);
        String params = url.getQuery();
        if(StringUtils.isNotEmpty(params)) {
            List<String> paramList = Lists.newArrayList(params.split("&"));

            if(CollectionUtils.isNotEmpty(paramList)) {
                for(String kv : paramList) {
                    if(kv.contains("h5params")) {
                        String[] kvs = kv.split("=");
                        if(kvs.length == 2) {
                            String h5param = kvs[1];

                            String h5paramDecode = URLDecoder.decode(h5param);

                            JSONObject h5paramJson = JSON.parseObject(h5paramDecode);

                            String tags = h5paramJson.getString("tags");
                            if(StringUtils.isNotEmpty(tags)) {
                                tags = tags + ",twopd_crm_distribute";
                            } else {
                                tags = "twopd_crm_distribute";
                            }
                            h5paramJson.put("tags", tags);

                            String h5paramEncode = URLEncoder.encode(h5paramJson.toJSONString());

                            urlStr = urlStr.replace(h5param, h5paramEncode);
                        }
                    }
                }
            }

            System.out.println(urlStr);
        }
    }


    @Test
    public void test193() {
        String str = "\\u8bf7\\u6c42\\u8fc7\\u4e8e\\u9891\\u7e41|abc";
        List<String> baijiaMsgList = Lists.newArrayList(str.split("\\|"));
        System.out.println(baijiaMsgList);
    }

    @Test
    public void test195() throws MalformedURLException {
        String urlStr = "https://t.youku.com/yep/page/m/2020shuang11zhuhuichnag?hee=huangjin";
        URL url = new URL(urlStr);

        System.out.println(url.getPath());
    }

    @Test
    public void test199() {
        List<Integer> list1 = Lists.newArrayList();
        list1.add(1);
        list1.add(2);
        list1.add(3);

        List<Integer> list2 = Lists.newArrayList();
        list2.addAll(list1);

        System.out.println(list2);
        for(int i = 0; i < list1.size(); i++) {
            int num = (int) (Math.random() * (list2.size()));
            System.out.println(num);
            list2.remove(num);
            System.out.println(list2);

        }
    }

    @Test
    public void test201() {
        Date today = new Date();

        Date before1day = DateUtil.getNDaysBefore(1);
        System.out.println(before1day);

        System.out.println(DateUtil.getEndOfDate(before1day));


        String str = "2021-05-17 14:36:36";

        Date date = DateUtil.parseDate(str, DateUtil.DATE_TIME_SHORT);

        System.out.println(date);


    }

    @Test
    public void test203() {
        String jsonStr = "{\n"
            + "    \"code\": \"100000\",\n"
            + "    \"msg\": \"success\",\n"
            + "    \"data\": {\n"
            + "        \"total\": 688,\n"
            + "        \"list\": [\n"
            + "            {\n"
            + "                \"screen_name\": \"搞笑幽默大湿兄\",\n"
            + "                \"avatar_large\": \"https://tvax1.sinaimg.cn/crop.102.138.310.310"
            + ".180/0070xrHoly8fmf3sfxfogj30dw0fjaam.jpg?KID=imgbed,tva&Expires=1603725556&ssig=EEBwCOfj6q\",\n"
            + "                \"verified\": true,\n"
            + "                \"verified_type\": 0,\n"
            + "                \"verified_type_ext\": 0,\n"
            + "                \"uid\": \"6420901126\",\n"
            + "                \"fans\": \"376\",\n"
            + "                \"addfans\": \"0\",\n"
            + "                \"post\": \"0\",\n"
            + "                \"read\": \"0\",\n"
            + "                \"video\": \"0\",\n"
            + "                \"videoplay\": \"1\",\n"
            + "                \"beinter\": \"0\"\n"
            + "            },\n"
            + "            {\n"
            + "                \"screen_name\": \"神级热剧大放送\",\n"
            + "                \"avatar_large\": \"https://tvax2.sinaimg.cn/crop.19.21.260.260"
            + ".180/00706Lkfly8fniytufdt7j308c08cwei.jpg?KID=imgbed,tva&Expires=1603725556&ssig=N/Y+8l/k4+\",\n"
            + "                \"verified\": true,\n"
            + "                \"verified_type\": 0,\n"
            + "                \"verified_type_ext\": 0,\n"
            + "                \"uid\": \"6414541715\",\n"
            + "                \"fans\": \"337\",\n"
            + "                \"addfans\": \"-1\",\n"
            + "                \"post\": \"0\",\n"
            + "                \"read\": \"0\",\n"
            + "                \"video\": \"0\",\n"
            + "                \"videoplay\": \"5\",\n"
            + "                \"beinter\": \"0\"\n"
            + "            },\n"
            + "            {\n"
            + "                \"screen_name\": \"喜剧电影范\",\n"
            + "                \"avatar_large\": \"https://tvax3.sinaimg.cn/crop.65.5.296.296"
            + ".180/0070ynlXly8fqj7hn8dasj30b408ymxf.jpg?KID=imgbed,tva&Expires=1603725556&ssig=BCXdKGyccP\",\n"
            + "                \"verified\": true,\n"
            + "                \"verified_type\": 0,\n"
            + "                \"verified_type_ext\": 0,\n"
            + "                \"uid\": \"6421122749\",\n"
            + "                \"fans\": \"287\",\n"
            + "                \"addfans\": \"0\",\n"
            + "                \"post\": \"0\",\n"
            + "                \"read\": \"0\",\n"
            + "                \"video\": \"0\",\n"
            + "                \"videoplay\": \"12\",\n"
            + "                \"beinter\": \"0\"\n"
            + "            },\n"
            + "            {\n"
            + "                \"screen_name\": \"冒险电影范\",\n"
            + "                \"avatar_large\": \"https://tvax3.sinaimg.cn/crop.35.136.751.751"
            + ".180/00706LtUly8fqyak8dqntj30m80vpn0o.jpg?KID=imgbed,tva&Expires=1603725556&ssig=+uZUOynalc\",\n"
            + "                \"verified\": true,\n"
            + "                \"verified_type\": 0,\n"
            + "                \"verified_type_ext\": 0,\n"
            + "                \"uid\": \"6414542314\",\n"
            + "                \"fans\": \"249\",\n"
            + "                \"addfans\": \"0\",\n"
            + "                \"post\": \"0\",\n"
            + "                \"read\": \"0\",\n"
            + "                \"video\": \"0\",\n"
            + "                \"videoplay\": \"3\",\n"
            + "                \"beinter\": \"0\"\n"
            + "            },\n"
            + "            {\n"
            + "                \"screen_name\": \"明星们的最佳损友\",\n"
            + "                \"avatar_large\": \"https://tvax2.sinaimg.cn/crop.268.0.335.335"
            + ".180/0070xrlbly8fmgg6yd1r9j30gr0aq0uw.jpg?KID=imgbed,tva&Expires=1603725556&ssig=Jn4qj493Ii\",\n"
            + "                \"verified\": true,\n"
            + "                \"verified_type\": 0,\n"
            + "                \"verified_type_ext\": 0,\n"
            + "                \"uid\": \"6420899749\",\n"
            + "                \"fans\": \"243\",\n"
            + "                \"addfans\": \"0\",\n"
            + "                \"post\": \"0\",\n"
            + "                \"read\": \"0\",\n"
            + "                \"video\": \"0\",\n"
            + "                \"videoplay\": \"2\",\n"
            + "                \"beinter\": \"0\"\n"
            + "            },\n"
            + "            {\n"
            + "                \"screen_name\": \"可爱的萌宠集中营\",\n"
            + "                \"avatar_large\": \"https://tvax1.sinaimg.cn/crop.127.102.244.244"
            + ".180/0070xrkPly8fnw9vcj3kgj30c80c8jrl.jpg?KID=imgbed,tva&Expires=1603725556&ssig=2oY483Lrrm\",\n"
            + "                \"verified\": true,\n"
            + "                \"verified_type\": 0,\n"
            + "                \"verified_type_ext\": 0,\n"
            + "                \"uid\": \"6420899727\",\n"
            + "                \"fans\": \"185\",\n"
            + "                \"addfans\": \"0\",\n"
            + "                \"post\": \"0\",\n"
            + "                \"read\": \"0\",\n"
            + "                \"video\": \"0\",\n"
            + "                \"videoplay\": \"0\",\n"
            + "                \"beinter\": \"0\"\n"
            + "            },\n"
            + "            {\n"
            + "                \"screen_name\": \"清江娱乐\",\n"
            + "                \"avatar_large\": \"https://tvax1.sinaimg.cn/crop.35.23.380.380"
            + ".180/0070ynoqly8fts1l51pctj30c80c7t9b.jpg?KID=imgbed,tva&Expires=1603725556&ssig=ZBFaQ5mKoY\",\n"
            + "                \"verified\": false,\n"
            + "                \"verified_type\": -1,\n"
            + "                \"verified_type_ext\": null,\n"
            + "                \"uid\": \"6421122902\",\n"
            + "                \"fans\": \"175\",\n"
            + "                \"addfans\": \"0\",\n"
            + "                \"post\": \"0\",\n"
            + "                \"read\": \"0\",\n"
            + "                \"video\": \"0\",\n"
            + "                \"videoplay\": \"0\",\n"
            + "                \"beinter\": \"0\"\n"
            + "            },\n"
            + "            {\n"
            + "                \"screen_name\": \"湄公娱\",\n"
            + "                \"avatar_large\": \"https://tvax1.sinaimg.cn/crop.207.0.394.394"
            + ".180/00706Lvaly8fuw9xzo3loj30j60ay74s.jpg?KID=imgbed,tva&Expires=1603725556&ssig=OI6l3DOju3\",\n"
            + "                \"verified\": false,\n"
            + "                \"verified_type\": -1,\n"
            + "                \"verified_type_ext\": null,\n"
            + "                \"uid\": \"6414542392\",\n"
            + "                \"fans\": \"144\",\n"
            + "                \"addfans\": \"0\",\n"
            + "                \"post\": \"0\",\n"
            + "                \"read\": \"0\",\n"
            + "                \"video\": \"0\",\n"
            + "                \"videoplay\": \"0\",\n"
            + "                \"beinter\": \"0\"\n"
            + "            },\n"
            + "            {\n"
            + "                \"screen_name\": \"今天你青春了吗\",\n"
            + "                \"avatar_large\": \"https://tvax4.sinaimg.cn/crop.14.0.385.385"
            + ".180/0070xrkZly8fmg8rl4mntj30b50apjuy.jpg?KID=imgbed,tva&Expires=1603725556&ssig=RvFfEnXZm0\",\n"
            + "                \"verified\": true,\n"
            + "                \"verified_type\": 0,\n"
            + "                \"verified_type_ext\": 0,\n"
            + "                \"uid\": \"6420899737\",\n"
            + "                \"fans\": \"129\",\n"
            + "                \"addfans\": \"0\",\n"
            + "                \"post\": \"0\",\n"
            + "                \"read\": \"0\",\n"
            + "                \"video\": \"0\",\n"
            + "                \"videoplay\": \"6\",\n"
            + "                \"beinter\": \"0\"\n"
            + "            },\n"
            + "            {\n"
            + "                \"screen_name\": \"今天你是公主吗\",\n"
            + "                \"avatar_large\": \"https://tvax4.sinaimg.cn/crop.50.0.441.441"
            + ".180/0070ymUUly8fmgfau4gcmj30dw0hemxi.jpg?KID=imgbed,tva&Expires=1603725556&ssig=bvI8frk45J\",\n"
            + "                \"verified\": true,\n"
            + "                \"verified_type\": 0,\n"
            + "                \"verified_type_ext\": 0,\n"
            + "                \"uid\": \"6421121072\",\n"
            + "                \"fans\": \"115\",\n"
            + "                \"addfans\": \"0\",\n"
            + "                \"post\": \"0\",\n"
            + "                \"read\": \"0\",\n"
            + "                \"video\": \"0\",\n"
            + "                \"videoplay\": \"3\",\n"
            + "                \"beinter\": \"0\"\n"
            + "            },\n"
            + "            {\n"
            + "                \"screen_name\": \"时尚变成蝴蝶飞走了\",\n"
            + "                \"avatar_large\": \"https://tvax4.sinaimg.cn/crop.4.3.131.131"
            + ".180/0070xrkGly8fqer5gmkm7j304c03rgm4.jpg?KID=imgbed,tva&Expires=1603725556&ssig=x33KWcsblY\",\n"
            + "                \"verified\": true,\n"
            + "                \"verified_type\": 0,\n"
            + "                \"verified_type_ext\": 0,\n"
            + "                \"uid\": \"6420899718\",\n"
            + "                \"fans\": \"111\",\n"
            + "                \"addfans\": \"0\",\n"
            + "                \"post\": \"0\",\n"
            + "                \"read\": \"0\",\n"
            + "                \"video\": \"0\",\n"
            + "                \"videoplay\": \"0\",\n"
            + "                \"beinter\": \"0\"\n"
            + "            },\n"
            + "            {\n"
            + "                \"screen_name\": \"搞笑娱乐头条君\",\n"
            + "                \"avatar_large\": \"https://tvax1.sinaimg.cn/crop.0.0.300.300"
            + ".180/0070ynhGly8fmf9xhvctsj308c08caa9.jpg?KID=imgbed,tva&Expires=1603725556&ssig=cLQXCWVDpJ\",\n"
            + "                \"verified\": true,\n"
            + "                \"verified_type\": 0,\n"
            + "                \"verified_type_ext\": 0,\n"
            + "                \"uid\": \"6421122484\",\n"
            + "                \"fans\": \"84\",\n"
            + "                \"addfans\": \"0\",\n"
            + "                \"post\": \"0\",\n"
            + "                \"read\": \"0\",\n"
            + "                \"video\": \"0\",\n"
            + "                \"videoplay\": \"0\",\n"
            + "                \"beinter\": \"0\"\n"
            + "            },\n"
            + "            {\n"
            + "                \"screen_name\": \"洞察老司机\",\n"
            + "                \"avatar_large\": \"https://tvax1.sinaimg.cn/crop.480.0.1200.1200"
            + ".180/0070ymUFly8fpw5kccxqxj31hc0xcwil.jpg?KID=imgbed,tva&Expires=1603725556&ssig=6vaQEhlBTW\",\n"
            + "                \"verified\": true,\n"
            + "                \"verified_type\": 0,\n"
            + "                \"verified_type_ext\": 0,\n"
            + "                \"uid\": \"6421121057\",\n"
            + "                \"fans\": \"84\",\n"
            + "                \"addfans\": \"0\",\n"
            + "                \"post\": \"0\",\n"
            + "                \"read\": \"0\",\n"
            + "                \"video\": \"0\",\n"
            + "                \"videoplay\": \"4\",\n"
            + "                \"beinter\": \"0\"\n"
            + "            },\n"
            + "            {\n"
            + "                \"screen_name\": \"影视圈小钢炮\",\n"
            + "                \"avatar_large\": \"https://tvax3.sinaimg.cn/crop.8.3.591.591"
            + ".180/0070ynhgly8fmf9i270vhj30go0h83z0.jpg?KID=imgbed,tva&Expires=1603725556&ssig=S6ZzGSEuXW\",\n"
            + "                \"verified\": true,\n"
            + "                \"verified_type\": 0,\n"
            + "                \"verified_type_ext\": 0,\n"
            + "                \"uid\": \"6421122458\",\n"
            + "                \"fans\": \"78\",\n"
            + "                \"addfans\": \"0\",\n"
            + "                \"post\": \"0\",\n"
            + "                \"read\": \"0\",\n"
            + "                \"video\": \"0\",\n"
            + "                \"videoplay\": \"1\",\n"
            + "                \"beinter\": \"0\"\n"
            + "            },\n"
            + "            {\n"
            + "                \"screen_name\": \"轩辕五百年\",\n"
            + "                \"avatar_large\": \"https://tvax1.sinaimg.cn/crop.69.0.266.266"
            + ".180/0070xrkKly8fmganicvjgj30dc0dcmy6.jpg?KID=imgbed,tva&Expires=1603725556&ssig=hQpA+IZj9M\",\n"
            + "                \"verified\": true,\n"
            + "                \"verified_type\": 0,\n"
            + "                \"verified_type_ext\": 0,\n"
            + "                \"uid\": \"6420899722\",\n"
            + "                \"fans\": \"54\",\n"
            + "                \"addfans\": \"0\",\n"
            + "                \"post\": \"0\",\n"
            + "                \"read\": \"0\",\n"
            + "                \"video\": \"0\",\n"
            + "                \"videoplay\": \"1\",\n"
            + "                \"beinter\": \"0\"\n"
            + "            },\n"
            + "            {\n"
            + "                \"screen_name\": null,\n"
            + "                \"avatar_large\": null,\n"
            + "                \"verified\": null,\n"
            + "                \"verified_type\": null,\n"
            + "                \"verified_type_ext\": null,\n"
            + "                \"uid\": \"6420901080\",\n"
            + "                \"fans\": \"0\",\n"
            + "                \"addfans\": \"0\",\n"
            + "                \"post\": \"0\",\n"
            + "                \"read\": \"0\",\n"
            + "                \"video\": \"0\",\n"
            + "                \"videoplay\": \"0\",\n"
            + "                \"beinter\": \"0\"\n"
            + "            },\n"
            + "            {\n"
            + "                \"screen_name\": null,\n"
            + "                \"avatar_large\": null,\n"
            + "                \"verified\": null,\n"
            + "                \"verified_type\": null,\n"
            + "                \"verified_type_ext\": null,\n"
            + "                \"uid\": \"6414541560\",\n"
            + "                \"fans\": \"0\",\n"
            + "                \"addfans\": \"0\",\n"
            + "                \"post\": \"0\",\n"
            + "                \"read\": \"0\",\n"
            + "                \"video\": \"0\",\n"
            + "                \"videoplay\": \"0\",\n"
            + "                \"beinter\": \"0\"\n"
            + "            },\n"
            + "            {\n"
            + "                \"screen_name\": null,\n"
            + "                \"avatar_large\": null,\n"
            + "                \"verified\": null,\n"
            + "                \"verified_type\": null,\n"
            + "                \"verified_type_ext\": null,\n"
            + "                \"uid\": \"6414242559\",\n"
            + "                \"fans\": \"0\",\n"
            + "                \"addfans\": \"0\",\n"
            + "                \"post\": \"0\",\n"
            + "                \"read\": \"0\",\n"
            + "                \"video\": \"0\",\n"
            + "                \"videoplay\": \"0\",\n"
            + "                \"beinter\": \"0\"\n"
            + "            },\n"
            + "            {\n"
            + "                \"screen_name\": null,\n"
            + "                \"avatar_large\": null,\n"
            + "                \"verified\": null,\n"
            + "                \"verified_type\": null,\n"
            + "                \"verified_type_ext\": null,\n"
            + "                \"uid\": \"6400788304\",\n"
            + "                \"fans\": \"0\",\n"
            + "                \"addfans\": \"0\",\n"
            + "                \"post\": \"0\",\n"
            + "                \"read\": \"0\",\n"
            + "                \"video\": \"0\",\n"
            + "                \"videoplay\": \"0\",\n"
            + "                \"beinter\": \"0\"\n"
            + "            },\n"
            + "            {\n"
            + "                \"screen_name\": null,\n"
            + "                \"avatar_large\": null,\n"
            + "                \"verified\": null,\n"
            + "                \"verified_type\": null,\n"
            + "                \"verified_type_ext\": null,\n"
            + "                \"uid\": \"6400788281\",\n"
            + "                \"fans\": \"0\",\n"
            + "                \"addfans\": \"0\",\n"
            + "                \"post\": \"0\",\n"
            + "                \"read\": \"0\",\n"
            + "                \"video\": \"0\",\n"
            + "                \"videoplay\": \"0\",\n"
            + "                \"beinter\": \"0\"\n"
            + "            },\n"
            + "            {\n"
            + "                \"screen_name\": null,\n"
            + "                \"avatar_large\": null,\n"
            + "                \"verified\": null,\n"
            + "                \"verified_type\": null,\n"
            + "                \"verified_type_ext\": null,\n"
            + "                \"uid\": \"6421122409\",\n"
            + "                \"fans\": \"0\",\n"
            + "                \"addfans\": \"0\",\n"
            + "                \"post\": \"0\",\n"
            + "                \"read\": \"0\",\n"
            + "                \"video\": \"0\",\n"
            + "                \"videoplay\": \"0\",\n"
            + "                \"beinter\": \"0\"\n"
            + "            },\n"
            + "            {\n"
            + "                \"screen_name\": null,\n"
            + "                \"avatar_large\": null,\n"
            + "                \"verified\": null,\n"
            + "                \"verified_type\": null,\n"
            + "                \"verified_type_ext\": null,\n"
            + "                \"uid\": \"6420901025\",\n"
            + "                \"fans\": \"0\",\n"
            + "                \"addfans\": \"0\",\n"
            + "                \"post\": \"0\",\n"
            + "                \"read\": \"0\",\n"
            + "                \"video\": \"0\",\n"
            + "                \"videoplay\": \"0\",\n"
            + "                \"beinter\": \"0\"\n"
            + "            },\n"
            + "            {\n"
            + "                \"screen_name\": null,\n"
            + "                \"avatar_large\": null,\n"
            + "                \"verified\": null,\n"
            + "                \"verified_type\": null,\n"
            + "                \"verified_type_ext\": null,\n"
            + "                \"uid\": \"6420899991\",\n"
            + "                \"fans\": \"0\",\n"
            + "                \"addfans\": \"0\",\n"
            + "                \"post\": \"0\",\n"
            + "                \"read\": \"0\",\n"
            + "                \"video\": \"0\",\n"
            + "                \"videoplay\": \"0\",\n"
            + "                \"beinter\": \"0\"\n"
            + "            },\n"
            + "            {\n"
            + "                \"screen_name\": null,\n"
            + "                \"avatar_large\": null,\n"
            + "                \"verified\": null,\n"
            + "                \"verified_type\": null,\n"
            + "                \"verified_type_ext\": null,\n"
            + "                \"uid\": \"6414243155\",\n"
            + "                \"fans\": \"0\",\n"
            + "                \"addfans\": \"0\",\n"
            + "                \"post\": \"0\",\n"
            + "                \"read\": \"0\",\n"
            + "                \"video\": \"0\",\n"
            + "                \"videoplay\": \"0\",\n"
            + "                \"beinter\": \"0\"\n"
            + "            },\n"
            + "            {\n"
            + "                \"screen_name\": null,\n"
            + "                \"avatar_large\": null,\n"
            + "                \"verified\": null,\n"
            + "                \"verified_type\": null,\n"
            + "                \"verified_type_ext\": null,\n"
            + "                \"uid\": \"6401085711\",\n"
            + "                \"fans\": \"0\",\n"
            + "                \"addfans\": \"0\",\n"
            + "                \"post\": \"0\",\n"
            + "                \"read\": \"0\",\n"
            + "                \"video\": \"0\",\n"
            + "                \"videoplay\": \"0\",\n"
            + "                \"beinter\": \"0\"\n"
            + "            },\n"
            + "            {\n"
            + "                \"screen_name\": null,\n"
            + "                \"avatar_large\": null,\n"
            + "                \"verified\": null,\n"
            + "                \"verified_type\": null,\n"
            + "                \"verified_type_ext\": null,\n"
            + "                \"uid\": \"6401085652\",\n"
            + "                \"fans\": \"0\",\n"
            + "                \"addfans\": \"0\",\n"
            + "                \"post\": \"0\",\n"
            + "                \"read\": \"0\",\n"
            + "                \"video\": \"0\",\n"
            + "                \"videoplay\": \"0\",\n"
            + "                \"beinter\": \"0\"\n"
            + "            },\n"
            + "            {\n"
            + "                \"screen_name\": null,\n"
            + "                \"avatar_large\": null,\n"
            + "                \"verified\": null,\n"
            + "                \"verified_type\": null,\n"
            + "                \"verified_type_ext\": null,\n"
            + "                \"uid\": \"6401085609\",\n"
            + "                \"fans\": \"0\",\n"
            + "                \"addfans\": \"0\",\n"
            + "                \"post\": \"0\",\n"
            + "                \"read\": \"0\",\n"
            + "                \"video\": \"0\",\n"
            + "                \"videoplay\": \"0\",\n"
            + "                \"beinter\": \"0\"\n"
            + "            },\n"
            + "            {\n"
            + "                \"screen_name\": null,\n"
            + "                \"avatar_large\": null,\n"
            + "                \"verified\": null,\n"
            + "                \"verified_type\": null,\n"
            + "                \"verified_type_ext\": null,\n"
            + "                \"uid\": \"6400788314\",\n"
            + "                \"fans\": \"0\",\n"
            + "                \"addfans\": \"0\",\n"
            + "                \"post\": \"0\",\n"
            + "                \"read\": \"0\",\n"
            + "                \"video\": \"0\",\n"
            + "                \"videoplay\": \"0\",\n"
            + "                \"beinter\": \"0\"\n"
            + "            },\n"
            + "            {\n"
            + "                \"screen_name\": null,\n"
            + "                \"avatar_large\": null,\n"
            + "                \"verified\": null,\n"
            + "                \"verified_type\": null,\n"
            + "                \"verified_type_ext\": null,\n"
            + "                \"uid\": \"6400788273\",\n"
            + "                \"fans\": \"0\",\n"
            + "                \"addfans\": \"0\",\n"
            + "                \"post\": \"0\",\n"
            + "                \"read\": \"0\",\n"
            + "                \"video\": \"0\",\n"
            + "                \"videoplay\": \"0\",\n"
            + "                \"beinter\": \"0\"\n"
            + "            },\n"
            + "            {\n"
            + "                \"screen_name\": null,\n"
            + "                \"avatar_large\": null,\n"
            + "                \"verified\": null,\n"
            + "                \"verified_type\": null,\n"
            + "                \"verified_type_ext\": null,\n"
            + "                \"uid\": \"6391433510\",\n"
            + "                \"fans\": \"0\",\n"
            + "                \"addfans\": \"0\",\n"
            + "                \"post\": \"0\",\n"
            + "                \"read\": \"0\",\n"
            + "                \"video\": \"0\",\n"
            + "                \"videoplay\": \"0\",\n"
            + "                \"beinter\": \"0\"\n"
            + "            },\n"
            + "            {\n"
            + "                \"screen_name\": null,\n"
            + "                \"avatar_large\": null,\n"
            + "                \"verified\": null,\n"
            + "                \"verified_type\": null,\n"
            + "                \"verified_type_ext\": null,\n"
            + "                \"uid\": \"6391154789\",\n"
            + "                \"fans\": \"0\",\n"
            + "                \"addfans\": \"0\",\n"
            + "                \"post\": \"0\",\n"
            + "                \"read\": \"0\",\n"
            + "                \"video\": \"0\",\n"
            + "                \"videoplay\": \"0\",\n"
            + "                \"beinter\": \"0\"\n"
            + "            },\n"
            + "            {\n"
            + "                \"screen_name\": null,\n"
            + "                \"avatar_large\": null,\n"
            + "                \"verified\": null,\n"
            + "                \"verified_type\": null,\n"
            + "                \"verified_type_ext\": null,\n"
            + "                \"uid\": \"6420901457\",\n"
            + "                \"fans\": \"0\",\n"
            + "                \"addfans\": \"0\",\n"
            + "                \"post\": \"0\",\n"
            + "                \"read\": \"0\",\n"
            + "                \"video\": \"0\",\n"
            + "                \"videoplay\": \"0\",\n"
            + "                \"beinter\": \"0\"\n"
            + "            },\n"
            + "            {\n"
            + "                \"screen_name\": null,\n"
            + "                \"avatar_large\": null,\n"
            + "                \"verified\": null,\n"
            + "                \"verified_type\": null,\n"
            + "                \"verified_type_ext\": null,\n"
            + "                \"uid\": \"6420899987\",\n"
            + "                \"fans\": \"0\",\n"
            + "                \"addfans\": \"0\",\n"
            + "                \"post\": \"0\",\n"
            + "                \"read\": \"0\",\n"
            + "                \"video\": \"0\",\n"
            + "                \"videoplay\": \"0\",\n"
            + "                \"beinter\": \"0\"\n"
            + "            },\n"
            + "            {\n"
            + "                \"screen_name\": null,\n"
            + "                \"avatar_large\": null,\n"
            + "                \"verified\": null,\n"
            + "                \"verified_type\": null,\n"
            + "                \"verified_type_ext\": null,\n"
            + "                \"uid\": \"6414242568\",\n"
            + "                \"fans\": \"0\",\n"
            + "                \"addfans\": \"0\",\n"
            + "                \"post\": \"0\",\n"
            + "                \"read\": \"0\",\n"
            + "                \"video\": \"0\",\n"
            + "                \"videoplay\": \"0\",\n"
            + "                \"beinter\": \"0\"\n"
            + "            },\n"
            + "            {\n"
            + "                \"screen_name\": null,\n"
            + "                \"avatar_large\": null,\n"
            + "                \"verified\": null,\n"
            + "                \"verified_type\": null,\n"
            + "                \"verified_type_ext\": null,\n"
            + "                \"uid\": \"6421121787\",\n"
            + "                \"fans\": \"0\",\n"
            + "                \"addfans\": \"0\",\n"
            + "                \"post\": \"0\",\n"
            + "                \"read\": \"0\",\n"
            + "                \"video\": \"0\",\n"
            + "                \"videoplay\": \"0\",\n"
            + "                \"beinter\": \"0\"\n"
            + "            },\n"
            + "            {\n"
            + "                \"screen_name\": null,\n"
            + "                \"avatar_large\": null,\n"
            + "                \"verified\": null,\n"
            + "                \"verified_type\": null,\n"
            + "                \"verified_type_ext\": null,\n"
            + "                \"uid\": \"6420899708\",\n"
            + "                \"fans\": \"0\",\n"
            + "                \"addfans\": \"0\",\n"
            + "                \"post\": \"0\",\n"
            + "                \"read\": \"0\",\n"
            + "                \"video\": \"0\",\n"
            + "                \"videoplay\": \"0\",\n"
            + "                \"beinter\": \"0\"\n"
            + "            },\n"
            + "            {\n"
            + "                \"screen_name\": null,\n"
            + "                \"avatar_large\": null,\n"
            + "                \"verified\": null,\n"
            + "                \"verified_type\": null,\n"
            + "                \"verified_type_ext\": null,\n"
            + "                \"uid\": \"6414242802\",\n"
            + "                \"fans\": \"0\",\n"
            + "                \"addfans\": \"0\",\n"
            + "                \"post\": \"0\",\n"
            + "                \"read\": \"0\",\n"
            + "                \"video\": \"0\",\n"
            + "                \"videoplay\": \"0\",\n"
            + "                \"beinter\": \"0\"\n"
            + "            },\n"
            + "            {\n"
            + "                \"screen_name\": null,\n"
            + "                \"avatar_large\": null,\n"
            + "                \"verified\": null,\n"
            + "                \"verified_type\": null,\n"
            + "                \"verified_type_ext\": null,\n"
            + "                \"uid\": \"6391154824\",\n"
            + "                \"fans\": \"0\",\n"
            + "                \"addfans\": \"0\",\n"
            + "                \"post\": \"0\",\n"
            + "                \"read\": \"0\",\n"
            + "                \"video\": \"0\",\n"
            + "                \"videoplay\": \"0\",\n"
            + "                \"beinter\": \"0\"\n"
            + "            }\n"
            + "        ],\n"
            + "        \"date\": \"2020-10-25\"\n"
            + "    }\n"
            + "}";

        ReadContext readContext = JsonPath.parse(jsonStr);

        int listSize = readContext.read("$.data.list.length()");

        System.out.println(listSize);

        for(int i = 0; i < listSize; i++) {

            String prefix = "$.data.list[" + i + "].";
            String uid = ConvertUtil.readJsonPath(readContext, prefix, "uid", String.class);

            System.out.println(uid);
        }
    }


    @Test
    public void test205() {

        String resp = "{\"code\":\"100000\",\"msg\":\"success\",\"data\":{\"fans\":{\"show\":\"1318457\","
            + "\"yAxis\":[1307361,1307433,1307503,1307606,1307668,1307717,1307793,1308009,1308271,1308731,1309044,"
            + "1309245,1309929,1310746,1311971,1312395,1312815,1313519,1314082,1314616,1314743,1314972,1315524,"
            + "1315765,1316006,1316156,1316770,1317170,1317950,1318347,1318457]},\"post\":{\"show\":181,\"yAxis\":[5,"
            + "5,10,6,6,5,5,5,5,6,5,5,6,7,6,7,7,6,8,5,5,7,6,5,5,6,5,5,6,5,6]},\"read\":{\"show\":5738991,"
            + "\"yAxis\":[108616,92754,82412,70183,159587,59994,56086,61914,52691,52044,78598,241073,161922,306003,"
            + "284241,325715,145527,230451,289089,369655,802535,93569,303408,103769,226023,96167,190365,187004,"
            + "221469,121505,164622]},\"beinter\":{\"show\":6615,\"yAxis\":[198,51,83,65,84,54,47,39,40,21,65,33,150,"
            + "743,2730,511,440,252,102,25,50,84,147,32,76,21,118,33,41,55,225]},\"video\":{\"show\":3,\"yAxis\":[0,"
            + "0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]},\"videoplay\":{\"show\":15356,"
            + "\"yAxis\":[405,397,2560,825,801,633,591,490,374,443,445,344,1219,341,298,300,365,368,286,331,268,305,"
            + "301,345,274,305,299,262,273,208,700]},\"fentiao\":{\"show\":\"0.00\",\"yAxis\":[\"0.00\",\"0.00\",\"0"
            + ".00\",\"0.00\",\"0.00\",\"0.00\",\"0.00\",\"0.00\",\"0.00\",\"0.00\",\"0.00\",\"0.00\",\"0.00\",\"0"
            + ".00\",\"0.00\",\"0.00\",\"0.00\",\"0.00\",\"0.00\",\"0.00\",\"0.00\",\"0.00\",\"0.00\",\"0.00\",\"0"
            + ".00\",\"0.00\",\"0.00\",\"0.00\",\"0.00\",\"0.00\",\"0.00\"]},\"officialrepost\":{\"show\":0,"
            + "\"yAxis\":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]},"
            + "\"videostream\":{\"show\":0,\"yAxis\":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"
            + "0]},\"xAxis\":[\"10月1日\",\"10月2日\",\"10月3日\",\"10月4日\",\"10月5日\",\"10月6日\",\"10月7日\",\"10月8日\","
            + "\"10月9日\",\"10月10日\",\"10月11日\",\"10月12日\",\"10月13日\",\"10月14日\",\"10月15日\",\"10月16日\",\"10月17日\","
            + "\"10月18日\",\"10月19日\",\"10月20日\",\"10月21日\",\"10月22日\",\"10月23日\",\"10月24日\",\"10月25日\",\"10月26日\","
            + "\"10月27日\",\"10月28日\",\"10月29日\",\"10月30日\",\"10月31日\"],\"yesterday\":{\"fans\":1321051,\"post\":6,"
            + "\"read\":78325,\"beinter\":244,\"video\":0,\"videoplay\":290,\"fentiao\":0,\"officialrepost\":0,"
            + "\"videostream\":0},\"total\":\"31\"}}";

        ReadContext readContext = JsonPath.parse(resp);

        String rowTime = ConvertUtil.readJsonPath(readContext,"$.data.xAxis","", String.class);
        List<String> list = JSON.parseArray(rowTime, String.class);

        for(String item : list) {
            System.out.println(item);
        }

        String play = ConvertUtil.readJsonPath(readContext,"$.data.videoplay.yAxis","", String.class);
        List<Integer> playList = JSON.parseArray(play, Integer.class);

        for(Integer item : playList) {
            System.out.println(item);
        }
    }


    @Test
    public void test207() throws ParseException {
        String date = "2020年10月1日";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");

        Date d = formatter.parse(date);
        System.out.println(DateUtil.formatDate(d));
    }

    @Test
    public void test209() throws ParseException {

        Date date = new Date((long)1606197813*1000);
        System.out.println(date);
    }

    @Test
    public void test211() throws ParseException {

        String str = "abc";
        ReadContext readContext = JsonPath.parse(str);

        Object uidobj = readContext.read("$.media.id");
        System.out.println(uidobj);


        String str2 = "测试公司";
        System.out.println(str2.contains("测试"));

    }

    @Test
    public void test213() {
        System.out.println(Math.floorDiv(1920, 600));

        System.out.println(new Byte("1"));
    }


    @Test
    public void test215() {
        List<Long> ids = Lists.newArrayList();
        ids.add(1L);
        ids.add(2L);
        ids.add(3L);

        ids = ids.parallelStream().filter(item -> item == 1L).collect(Collectors.toList());

        System.out.println(ids);
    }

    private static String CHANNEL = "BAIDU_BAIJIAHAO";

    @Test
    public void test217() {

        String str = "电影,动画片,分类：影视,小猪佩奇过大年,明星： 方青卓,专辑：节日";

        List<String> tags = Lists.newArrayList(str.split(","));

        for(String tag : tags) {
            if(tag.contains(":")) {

                String[] tagSplit = tag.split(":");
                String type = tagSplit[0].trim();
                String value = tagSplit[1].trim();

                if("分类".equals(type)) {
                    System.out.println(value);
                } else if("专辑".equals(type)) {
                    System.out.println(value);
                } else if("明星".equals(type)) {
                    System.out.println(value);
                }

            } else if(tag.contains("：")) {

                String[] tagSplit = tag.split("：");
                String type = tagSplit[0].trim();
                String value = tagSplit[1].trim();

                if("分类".equals(type)) {
                    System.out.println(value);
                } else if("专辑".equals(type)) {
                    System.out.println(value);
                } else if("明星".equals(type)) {
                    System.out.println(value);
                }
            }

        }
    }


    @Test
    public void test219() throws Exception {

        String privateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCnYS80E7HNKnJLM77CApGzpoKujvJYwKeklbzOPbE8yOg48JP_ghEvCzt1Oeyw2Kqa3zT5KnFCTMAbUR9h270DnwY19Cn80q5li1nAw8w6njuc-zfxwAOTXAYVIqsK1WZDP0EJj6z3CpxtH7D5tNz_pOjnGSG9A_t2uEhclSTadplsM6V-fIKv4lnOpGwJeI23v9a7M7gMyxnUZHuPtkXHvBfYrC6Md1CXPLFovHTRrYnLodk9mUsutaW1u21mu7PQlf8HK0rzLPliVdAw8AbboQK1TzFnxS7cqprxiQIKpJDgZBGp2W1jZior5qrNvpfaJ3CAP9PJtXKJO73MfytBAgMBAAECggEAaeu9VGZWMTB4TZC8WcgIbjikIFUDlhisviGn7pfpzj6Rfl74OUwVFcE06jUyzKMAK6uuuTMbo0owk9jdVC8bSOxcoM4XuA-XH1l0_eCqIDo8HVZ5C7poSNuGWd-rf9qVsV6ZCLTsTxVe8kuI0iybYgf4_y3PRO79Nh7eZABZ5wj5YUYy24R8ibQdaelPZSCQy91yLvae7eB0mPcvI6yCry2c4SZDv-qdJVfaGbSzHn6YRYaTofEUeQuUPYLfWJjl7oFLuKdo4IvD9Oxj2G3cDpesE53jzFy4vikGi87i4Ze1iGagq_Fh0_g4QcVlcD8oTLLxJdGa9KQnb4ru9AsOVQKBgQDjRuiz0suqGcJkbVqkOZbmbmvqHWB-Qw0pja0nF7lDRkGUsueMWnqFafNqIS7Hd-SZA1TzzRYkqfALVCqvJmnMIkuYmDGRIAtfL2Mjhe01cnmfhP5t3vRIMDu8bJB7M_s7FGvV1l45xdXv831BuLBaEm0anc5nJvLcTy7f-7atcwKBgQC8iGqaWoFlbwd27RbJOQ7kwfJm0ib3Eg8OoYSL7M8DzdB8NGipPky8XNpe94Ns5oSSwl6OKrNxatPnRhV5tPBncmVGThFs_MMYaGOTDW4iJyakgaDoT01GIoyH4wC-llnJcpaOfLxxNFTAjO4tdmIIQGJLm8YBA24QTlGwXCmXewKBgFHQrORO9dH-A-RzGTYVJSU-isp0WNAbAxn6TmLMP3zgRatp28DCsWhlg6Ko31Ye6sPKV0KMiXQZWLg7TEJQ6bT6g4wi_XTovC8_t8iJ5aJf2zh5f729jOKPunFSA5DWXZIe_9KyoKjbdvAyDt0hxxoll_EE7dBkTPjR6GXpDxHRAoGATvU26DQVUxzf3G_JBjcORMHxOhyzUvBOPAtrDor73BT7RrlQR-kejBi7H-C7OwWD6k7a-OFrS4m4-3f_kHw13q0rOvmBFMr7DiLR6QlL4aPR7D2Z3RExRLk9b7-jVS50pgiGg2A8L5ECGa4sJgAZaGlGgXHYyB3en5edYKYIiTcCgYBWZbpZyaRdgMof6zicbCP17s4jLz-LFs-WIJYTNdla5K4aayGDryQgwwYm3xntmtO5cXHn8q1GUjpak3yTVReZiRDe6y6qIDcyeyrabg8Ycj2WLNVVXBOGH5OKaMqbszPKCuhCuvdO-yXGRg4WRM4_ezwx71g9DMAtzV5ipLX_jg";
        String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAp2EvNBOxzSpySzO+wgKRs6aCro7yWMCnpJW8zj2xPMjoOPCT/4IRLws7dTnssNiqmt80+SpxQkzAG1EfYdu9A58GNfQp/NKuZYtZwMPMOp47nPs38cADk1wGFSKrCtVmQz9BCY+s9wqcbR+w+bTc/6To5xkhvQP7drhIXJUk2naZbDOlfnyCr+JZzqRsCXiNt7/WuzO4DMsZ1GR7j7ZFx7wX2KwujHdQlzyxaLx00a2Jy6HZPZlLLrWltbttZruz0JX/BytK8yz5YlXQMPAG26ECtU8xZ8Uu3Kqa8YkCCqSQ4GQRqdltY2YqK+aqzb6X2idwgD/TybVyiTu9zH8rQQIDAQAB";


        Map<String, Object> params = Maps.newHashMap();
        params.put("mbrId", 1394530416);
        params.put("signType", "SHA256WithRSA");
        params.put("timeStamp", 54321);

        String sign = RsaUtil4Aliyun.sign(privateKey, "SHA256WithRSA", params);
        System.out.println(sign);

        String encode = URLEncoder.encode(sign);
        System.out.println(encode);

        params.put(RsaUtil4Aliyun.KEY_4_SIGN_LOCAL, sign);
        boolean verify = RsaUtil4Aliyun.verify(publicKey, "SHA256WithRSA", params);

        System.out.println(verify);

    }

    @Test
    public void test221() throws Exception {
        String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmP/8IT4d8yPjYVzIgNoWh0SQPH8CZAxNKCgkB0n6rmMDgC79wlVgWfeuI9Xh6S77tRumi4w6vY8nNsMYxtKHaOFxVHHyQULLbIfX21DL/wprly1EXjw3L+d7BVEiAf+dAyTJKOM+wdcAhDsUzyiPINUhyFFWcAoIVdc9jgwgof6PJ9rCiY+MTxCAJawR4jxmr5cUtv+lKeV35xLTGcOivXczjy/vDw5JpQ8dvKJesgb0bYugyuU6Yz3soT81YKsYSnlsuZn9lSE7P3g7lSS5Fmy0BcKWG4Hbp7llIu9p0ldrtCXzYeX1It1T6BWTgsxCsXS0fvdvEMhIDGHhZIg8pwIDAQAB";


        Map<String, Object> params = Maps.newHashMap();
        params.put("uid", 12345);
        params.put("signType", "SHA256WithRSA");
        params.put("timeStamp", "1611582757234");
        params.put("scene", "YOUKU");

        String sign = "LRAoR33d%2BD6vHSafBthc3HeoG4pUUeGGAVSYP1GWSKfYIw7rzAJDaActPBvaiJjqAWT5K8gQJXfLZJmkI3xQyXRbdaSQUN3aF0574xMRaeO00eVC1cZRTDc6A6PwDahinE%2FV%2BSqX1%2BdBEFQeAsxIInEAWDUsI77zX73f4ddYt6GmxVkTaJOCY9fb%2Fwn%2BUCVM3bO5%2Fq2JzecHxBUtpHbSAPeuUPaJ90PHN2Mo5OL7nNB0Xs5%2BhwgyFagPRln%2FrZtxAmK4Pp9b7vOBtcm46FCxtSYxIEQUVpKLEeXJKAHG2HEA3EMpwUgJjRktymlgI56%2Fmx3eEKPC0xhzJ%2B%2F520b1QQ%3D%3D";
        String decode = URLDecoder.decode(sign, Charsets.UTF_8.displayName());
        params.put(RsaUtil4Aliyun.KEY_4_SIGN_LOCAL, decode);
        boolean verify = RsaUtil4Aliyun.verify(publicKey, "SHA256WithRSA", params);

        System.out.println(verify);

    }

    @Test
    public void test223() {
        Date cutDate = DateUtil.convertStringToDate(DateUtil.DATE_TIME_SHORT, "2020-01-01 00:00:00");

        System.out.println(cutDate);

        Date date = new Date("Sun Feb 07 09:18:00 +0800 2021");
        System.out.println(date);
    }


    @Test
    public void test225() throws MalformedURLException, URISyntaxException {
        String url = "https://test2.api.looklook.cn/open-api/vip/v1/recharge";

        String path = new URL(url).getPath();
        path = path.substring(path.indexOf("/", 2));
        System.out.println(path);


    }

    @Test
    public void test227() {
        String str = "{\n"
            + "  \"code\": \"0000\",\n"
            + "  \"msg\": \"sccuess\",\n"
            + "  \"biz_encrypt\": \"\",\n"
            + "  \"biz_content\": \"{\\\"RetCode\\\":\\\"0000\\\",\\\"RetMsg\\\":\\\"查询成功！\\\","
            + "\\\"Info\\\":\\\"{\\\\\\\"acc\\\\\\\":\\\\\\\"6230500120064244203\\\\\\\","
            + "\\\\\\\"acq_trace\\\\\\\":\\\\\\\"f854765488f34d69b467fd493b9b0600\\\\\\\","
            + "\\\\\\\"accTime\\\\\\\":\\\\\\\"\n"
            + "2021-05-17 14:36:36\\\\\\\"}\\\"}\",\n"
            + "  \"sign\": \"ts2LbLuxpg2/bXlw3Mk4CYmwN2QiM6qRYmG/1E25Yy0/Y2ZdNzYu3TnSm7wDrJab8UrTyTSLiN7FQFngDE8RbO5d"
            + "+LGVyFCsZP/DCTPNUakQHmhLjjMFwbG0bZgC9Wu9o3JZf/Y6eNYgGaOAthVBPPLQ4nWcE7F/ZbtE11NS4QfWu04Zid7Z5ITPfF\n"
            + "9PZXkb7WEG9dLWGg6IEm/Z4Wg4ZLLGdNWzu1PaBtVJWcgoIPFLUUxemU44ZKRMIQSiJkfcn9W8YcdE5ot68OBvUGp+NvG2FV"
            + "+Xn6ieSKvaVuHQmYWndSMItQ8gUXGZRky0Dn99vpHZN+tWrCAApIJ1PjeemQ==\",\n"
            + "  \"responseid\": \"0202105171437042958\"\n"
            + "}";

        System.out.println(str);
        System.out.println(str.replaceAll("[\r\n]", ""));
    }

    @Test
    public void test229() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("AAA", "AAA");
        jsonObject.put("BBB", Lists.newArrayList("B"));

        System.out.println(jsonObject);
    }

    @Test
    public void test231() {
        String str = "mrp_right_log_0040";

        str = StringUtils.substringBeforeLast(str, "_");
        System.out.println(str);
    }


    @Test
    public void test233() {
        String str = "mrp_right_log_insert";

        System.out.println(str.substring(str.lastIndexOf("_") + 1));

        System.out.println(str.substring(0, str.lastIndexOf("_")));

    }

    @Test
    public void test235() {
        List<Father> list = Lists.newArrayList();

        Son son = new Son();
        son.setsParam("s");
        son.setfParam("f");
        list.add(son);

        Father father = new Father();
        father.setfParam("ff");
        list.add(father);

        System.out.println(JSON.toJSONString(list));
    }

    @Test
    public void test237() {

        String str = "{\"customRight\":\"2\",\"屈臣氏月卡领取\":\"0\"}";

        JSONObject jsonObject = JSON.parseObject(str);

        Map<String, Object> innerMap = jsonObject.getInnerMap();
        for(Entry<String, Object> entry : innerMap.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }

    @Test
    public void test239() {
        String str = "✨✨❦&#95;九命悬鸭\uD80C\uDC80☠ะ　";

        str = str.replaceAll("[^\\u0000-\\uFFFF]", "");

        System.out.println(str);
    }


    @Test
    public void test241() throws Exception {
        System.out.println(this.getSignature("abc", "abc", "HmacMD5"));
    }


    @Test
    public void test243() throws Exception {
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCoDzUQmblQWiTDBz6TCKSgNID9YhucEEEmn5KYYOaHCeOTDgyIEnoobU9OkJWmVI8Tn+bzisOMobZ7RUH5kxynfTyuejG9wO/6B8NjQLlRika5ADtc2c0wMgL0dFKZZKyxduW1PPxVWXf9qNK30YIeheZDXSPH3iVOx8tktac/awIDAQAB";

        String data = "{\"activityId\":\"201609292169470\",\"orderNo\":\"RAS20293029239020230230\","
            + "sign=\"906216147adb1d5f0bd 042205f73ad5de4f165764899e2577d6a339eeb145575\",\"timestamp\":\"2020-10-20 "
            + "00:00:00\"}";

        System.out.println(RSAUtils.encryptPart(RSAUtils.loadPublicKeyByStr(publicKey), data));
    }

    @Test
    public void test245() {
        String orderId = "orderId";
        String itemId = "itemId";
        String identityId = "identityId";

        //虚拟码（实际无用处）
        String vcode = identityId + "_" + orderId + "yk";
        String vcodePass = identityId + "_" + System.currentTimeMillis() + "yk";

        //构建请求参数
        JSONObject reqJson = new JSONObject();
        reqJson.put("version", "2.0");
        reqJson.put("source", "1");
        reqJson.put("identity_id", identityId);

        JSONObject data = new JSONObject();
        data.put("itemId", itemId);
        data.put("orderId", orderId);

        JSONObject virtualCode = new JSONObject();
        virtualCode.put("vcode", vcode);
        virtualCode.put("vcodePass", vcodePass);
        JSONArray virtualCodes = new JSONArray(Lists.newArrayList(virtualCode));
        data.put("virtualCodes", virtualCodes);

        reqJson.put("data", data);

        String reqJsonStr = JSON.toJSONString(reqJson);

        System.out.println(reqJsonStr);
    }

    @Test
    public void test247() {
        String secretKey = "fde7tfR0CZxXtMZOUsbtCjE3iWyL7a1Jb1RmU9StTsWecuZS36Zcn0ojVdDqdmXF";

        String paramStr = "{\"source\":\"1\",\"version\":\"2.0\",\"identity_id\":\"CMV14301020\","
            + "\"data\":{\"discount\":\"\",\"finalFee\":\"135.0\",\"itemId\":\"11406760\",\"orderId\":\"A16110114\","
            + "\"phone\":\"17826807543\",\"price\":\"130.0\",\"quantity\":\"1\",\"title\":\"测试ql\"}}";


        String setCodeStr = "{\"data\":{\"virtualCodes\":[{\"vcodePass\":\"CMV14301020_1638856920586yk\","
            + "\"vcode\":\"CMV14301020_A16110114yk\"}],\"itemId\":\"11406760\",\"orderId\":\"A16110114\"},"
            + "\"identity_id\":\"CMV14301020\",\"source\":\"1\",\"version\":\"2.0\"}";

        //加密
        char[] arrayCh = paramStr.toCharArray();
        Arrays.sort(arrayCh);
        String sortParamStr = new String(arrayCh);
        System.out.println(sortParamStr);


        String md5Str = secretKey + sortParamStr;
        String sign = StringUtils.lowerCase(Md5Util.MD5(md5Str));
        System.out.println(sign);


        String reqStr = sign + paramStr;
        String base64Req = new String(Base64.encodeBase64(reqStr.getBytes()));
        System.out.println(base64Req);



        //解密
        base64Req =
            "NDFhOGQzNjA4ZDUxNGVjOTdjNjA5YzdkZjM3NWY4MjV7ImRhdGEiOnsiY2hhbm5lbE9yZGVySWQiOiI1MDIxMTIwNjIyMTI1NDQwNDkzOSIsImNoYW5uZWxTdWJPcmRlcklkIjoiMjAyMTEyMDYyMjE2MjYzN\n"
            +
                "DgyMjgiLCJmaW5hbEZlZSI6IjYyLjU1IiwiaXRlbUlkIjoiMTEzNzg5NDMiLCJvcmRlcklkIjoiVjIxMTIwNjI0MDMxNDkwIiwicGhvbmUiOiIxMzgzOTM1NTM1MiIsInByaWNlIjoiNjIuNTUiLCJxdWFudGl0eSI6IjEiLCJ0aXRsZSI6IuS8mOmFt+mFt+WWtV\n"
            + "ZJUOS8muWRmO+8iOWto+WNoe"
                + "+8iSJ9LCJpZGVudGl0eV9pZCI6IkNNVjE0MzAxOTM5Iiwic291cmNlIjoiMSIsInZlcnNpb24iOiIyLjAifQ==";
        String reqDecode = new String(Base64.decodeBase64(base64Req.getBytes()));
        System.out.println(reqDecode);

        String signCheck = reqDecode.substring(0, reqDecode.indexOf("{"));
        System.out.println(signCheck);
        String paramCheck = reqDecode.substring(reqDecode.indexOf("{"));
        System.out.println(paramCheck);

        char[] arrayCh2 = paramCheck.toCharArray();
        Arrays.sort(arrayCh2);
        String sortParamStr2 = new String(arrayCh2);

        String md5Str2 = secretKey + sortParamStr2;
        String sign2 = StringUtils.lowerCase(Md5Util.MD5(md5Str2));

        System.out.println(sign2.equals(signCheck));

        JSONObject param = JSON.parseObject(paramCheck);
        String paramData = param.getString("data");

        Map<String, String> paramMap = JSON.parseObject(paramData, Map.class);

        System.out.println(paramMap);

    }

    @Test
    public void test249() {

        String paramCheck = "{\"source\":\"1\",\"version\":\"2.0\",\"identity_id\":\"CMV12345678\","
            + "\"data\":{\"discount\":\"\",\"finalFee\":\"135.0\",\"itemId\":\"1000280\",\"orderId\":\"V16110114687522\","
            + "\"phone\":\"13700000000\",\"price\":\"130.0\",\"quantity\":\"1\",\"title\":\"测试\"}}";
        JSONObject param = JSON.parseObject(paramCheck);
        String paramData = param.getString("data");
        Map<String, String> paramMap = JSON.parseObject(paramData, Map.class);

        if(StringUtils.isEmpty(paramMap.get("phone"))) {
            System.out.println(1);
        }
        if(StringUtils.isEmpty(paramMap.get("orderId"))) {
            System.out.println(2);
        }
        if(StringUtils.isEmpty(paramMap.get("itemId"))) {
            System.out.println(3);
        }


        //身份id
        String identityId = param.getString("identity_id");
        if(StringUtils.isEmpty(identityId)) {
            System.out.println(4);
        }
        paramMap.put("identityId", identityId);
        //三方活动id
        String thirdActivityId = "abc";
        if(StringUtils.isEmpty(thirdActivityId)) {
            System.out.println(5);
        }

        paramMap.put("thirdActivityId", thirdActivityId);

        String str = "{\"@type\":\"com.alidme.xtrade.client.message.TradeOrderMessage\","
            + "\"attributes\":{\"attrMap\":{\"@type\":\"java.util.LinkedHashMap\",\"delivery_type\":\"3\"}},"
            + "\"code\":null,\"id\":\"com.youku.ott.app\",\"nodeType\":\"DELIVERY_SUCCESS\",\"oneId\":325576788,"
            + "\"order\":{\"attributes\":{\"attrMap\":{\"@type\":\"java.util.LinkedHashMap\",\"hi\":\"phone:BRAVIA 4K "
            + "UR2\",\"bonus\":\"2400\",\"cibn_subject_id\":\"92\",\"delivery_relate_benefits\":\"[]\","
            + "\"channel\":\"tv@7\",\"vmp_code\":\"buy_h5\",\"pid\":\"52f8ca2b4982124b\",\"can_refund\":\"true\","
            + "\"order_promotion_fob\":\"false\",\"merchant_id\":\"20171227ZH02015004\","
            + "\"valid_duration\":\"2678400000\",\"tid\":\"212c950716392317463314261e0fe0\",\"en_spm\":\"detail.vipbuy\","
            + "\"source_ip\":\"115.199.194.142\",\"upgrade_from_package_set_code\":\"14\","
            + "\"from_middle_ground\":\"true\",\"upgrade_to_package_set_code\":\"13\",\"cycle_buy_type\":\"0\","
            + "\"scenario\":\"vipup\",\"delivery_type\":\"3\",\"client\":\"null:null\",\"id\":\"com.youku.ott.app\","
            + "\"scm\":\"20140732.0.0.crm_20140732-manual-999_1_0_0_212c950716392317463314261e0fe0"
            + "-100929_100257_10445_4_2064_385_1639231746341_77f06030699140bab39ec432d9307fdb-none\",\"brand\":\"优酷\","
            + "\"device_id\":\"92AEA922F05D7387E115AD4932E16F05\",\"os\":\"null:null\","
            + "\"crm_touch_point_code\":\"ott_cashier_multi_components_up\",\"crm_goods_id\":\"100929\","
            + "\"receivings\":\"[{\\\"activityId\\\":29705,\\\"name\\\":\\\"【正式-dp专用-升级酷喵】5元档-月卡（5元）\\\","
            + "\\\"receivingId\\\":29705}]\",\"sku_id\":\"11937527\",\"pay_channel_code\":\"17\",\"env\":\"production\","
            + "\"delivery_target\":\"rights\",\"tags\":\"1006,1011,ykvip-index,weex,plato,full-screen\","
            + "\"spu_key\":\"up_diamond\",\"license\":\"7\",\"spm\":\"a2h07.13758154_NEWUPGRADERENDER_KMVIPUP\","
            + "\"category_key\":\"vip_upgrade\",\"product_type\":\"VIP会员\",\"app_key\":\"24679788\","
            + "\"pay_latest_time\":\"300000\",\"force_retry\":\"false\","
            + "\"order_sequence\":\"439fe061cdd442358f899d17313d7099\",\"channel_tags\":\"{}\"}},\"bizType\":0,"
            + "\"confirmFee\":500,\"deliverState\":1,\"detail\":true,\"failReason\":null,\"gmtCreate\":1639231753000,"
            + "\"gmtModified\":1639231759000,\"id\":62519050380239,\"offerId\":3534773607,\"oneId\":325576788,"
            + "\"outId\":\"2677037579\",\"parentId\":62519050380239,\"payPrice\":500,\"payState\":3,"
            + "\"payTime\":1639231759000,\"productId\":1536,\"productName\":\"升级酷喵VIP-1个月\","
            + "\"productPicUrl\":\"https://img.alicdn.com/imgextra/i2/O1CN01GNHkQl27ez8S0VJF1_!!6000000007823-2-tps-192"
            + "-192.png\",\"quantity\":1,\"refundState\":9,\"sellerId\":135,\"state\":1,\"tagPrice\":2900,"
            + "\"unitPrice\":2900},\"orderDelivery\":{\"attributes\":{\"attrMap\":{\"@type\":\"java.util"
            + ".LinkedHashMap\"}},\"confirmTime\":null,\"deliveryState\":2,\"deliveryTime\":1639231760215,"
            + "\"deliveryType\":3,\"gmtCreate\":1639231759000,\"gmtModified\":1639231759000,\"id\":4806997258,"
            + "\"offerId\":3534773607,\"offerState\":1,\"oneId\":325576788,\"operator\":null,\"orderId\":62519050380239,"
            + "\"outBizId\":null,\"sellerId\":135,\"state\":1},\"orderId\":62519050380239,"
            + "\"payOrder\":{\"attributes\":{\"attrMap\":{\"@type\":\"java.util.LinkedHashMap\","
            + "\"pay_channel_out_code\":\"00600110\","
            + "\"pay_channel_res"
            +
            "\":\"aHR0cHM6Ly9vcGVuYXBpLmFsaXBheS5jb20vZ2F0ZXdheS5kbz9hbGlwYXlfc2RrPWFsaXBheS1zZGstamF2YS1keW5hbWljVmVyc2lvbk5vJmFwcF9pZD0yMDE3MTIxMTAwNTU3OTYxJmJpel9jb250ZW50PSU3QiUyMm91dF90cmFkZV9ubyUyMiUzQSUyMjIwMjExMjExUDUwNzc4NjAzNzklMjIlMkMlMjJ0b3RhbF9hbW91bnQlMjIlM0ElMjI1LjAwJTIyJTJDJTIyc3ViamVjdCUyMiUzQSUyMiVFNSU4RCU4NyVFNyVCQSVBNyVFOSU4NSVCNyVFNSU5NiVCNVZJUC0xJUU0JUI4JUFBJUU2JTlDJTg4JTIyJTJDJTIycHJvZHVjdF9jb2RlJTIyJTNBJTIyUVVJQ0tfV0FQX1BBWSUyMiU3RCZjaGFyc2V0PVVURi04JmZvcm1hdD1qc29uJm1ldGhvZD1hbGlwYXkudHJhZGUud2FwLnBheSZub3RpZnlfdXJsPWh0dHBzJTNBJTJGJTJGZ2F0ZXdheS55b3VrdS5jb20lMkZiYW5rJTJGYWxpcGF5X3dhcHBheV92Ml9ub3RpZnkuaHRtJnJldHVybl91cmw9aHR0cHMlM0ElMkYlMkZnYXRld2F5LnlvdWt1LmNvbSUyRmJhbmslMkZhbGlwYXlfd2FwcGF5X3YyX2NhbGxfYmFjay5odG0mc2lnbj1GMjRSaWNXTkVnZ2MlMkZ6a1liWk1GZHBzbnY4M2drUExCWmFsWGFMMDZJclNSZXNZTk5jZm1hNU1KVlFpc0ZUOVJtVjJuM3kxQTkzJTJGcUh5YlMwVXZ6cVBNbWhkNGhyTklvVmdsVXU3VWxRMWNMUk9iT2tpbXhPTTZ4dEEzYm95d3FIUThPUmZTT3ZuRWN6THZQUFVjVXRueDFNaXdnYzlyZGEzWWlRZ0ZrYmRHdzgwUjJ1JTJGc2pLJTJGWldlODJFdjFFY0tJNzRrYkxyWW5qOXE0WEdnVzhDWlA0ZzN4SHB4WE4ybHJNV29EbDRIeUJzWmx0U05mRzllJTJGandsYjJkRlBHd2FWc3pPQVdLbnV1aUFUTG54WmhEVno0OHU3U2RtZmRDSSUyQmZiZUNDMHN3WlNlYzlaRG01NjhpcEVuU3hQYXFGTVVqYlNDS0kwdmlGWCUyQmhPMjkxa0tiQSUzRCUzRCZzaWduX3R5cGU9UlNBMiZ0aW1lc3RhbXA9MjAyMS0xMi0xMSsyMiUzQTA5JTNBMTMmdmVyc2lvbj0xLjA=\",\"bank_note\":\"{\\\"p\\\":\\\"2088221779019471\\\",\\\"ver\\\":\\\"2.0\\\",\\\"alipay\\\":{\\\"receipt_amount\\\":\\\"5.00\\\",\\\"point_amount\\\":\\\"0.00\\\",\\\"buyer_pay_amount\\\":\\\"5.00\\\",\\\"invoice_amount\\\":\\\"5.00\\\",\\\"fund_bill_list\\\":[{\\\"amount\\\":\\\"5.00\\\",\\\"fundChannel\\\":\\\"ALIPAYACCOUNT\\\"}]},\\\"scenario\\\":\\\"directPay\\\",\\\"goodsId\\\":\\\"1536_11937527\\\",\\\"buyer_info\\\":{\\\"buyer_account\\\":\\\"781***@qq.com\\\",\\\"buyer_id\\\":\\\"2088002932407139\\\"},\\\"appId\\\":\\\"2017121100557961\\\",\\\"action\\\":\\\"REDIRECT_PAY\\\",\\\"appkey\\\":\\\"24679788\\\",\\\"merchantOrderId\\\":\\\"62519050380239\\\",\\\"xqueue_flag\\\":\\\"1\\\"}\",\"cycle_buy_type\":\"0\",\"bank_order_sn\":\"2021121122001407131436051395\",\"pay_type\":\"BANK\",\"pay_extra_key\":\"pay_expires_date\",\"channel_tags\":\"{}\"}},\"gmtCreate\":1639231754000,\"gmtModified\":1639231759000,\"id\":7042971036,\"oneId\":325576788,\"orderId\":62519050380239,\"payChannel\":17,\"payId\":\"202112112209JY215135\",\"payPrice\":500,\"payState\":3,\"payTime\":1639231759000,\"sellerId\":135,\"state\":1,\"totalPrice\":2900},\"refundOrder\":null,\"scenario\":\"rights\",\"success\":true,\"userData\":{\"@type\":\"java.util.LinkedHashMap\"},\"yace\":null}";
    }

    /**
     * 移动权益平台
     */
    @Test
    public void test253() {
        String param = "{\"contractRoot\":{\"head\":{\"channelCode\":\"201911260277\",\"apiId\":\"200045\","
            + "\"transactionId\":\"1808341c-bc0f-457f-954d-fe625dea92157183\",\"reqTime\":\"20211203110116710\","
            + "\"sign\":\"e2869d2a067f83f9b35a487aa08f8c5d\",\"version\":\"1.0\"},"
            + "\"body\":{\"createTime\":\"20211203110117000\",\"orderItemId\":\"916282940337745920\",\"price\":0,"
            + "\"quantity\":1,\"rightsId\":\"201911260277\",\"serverNum\":\"13396819820\",\"skuCode\":\"20211110801876\","
            + "\"skuId\":\"60008725\",\"skuName\":\"优酷随心看合约包首月\"}}}";

        ReadContext readContext = JsonPath.parse(param);
        String signCheck = ConvertUtil.getJsonPathString(readContext.read("$.contractRoot.head.sign"));

        JSONObject root = JSON.parseObject(param, Feature.OrderedField);
        JSONObject contractRoot = root.getJSONObject("contractRoot");
        JSONObject head = contractRoot.getJSONObject("head");


        //计算签名，MD5(transactionId + reqTime + PRIVATEKEY+【body内容】)
        String privateKey = "C917A049A6F39A03";
        String transactionId = head.getString("transactionId");
        String reqTime = head.getString("reqTime");
        String md5Str = transactionId + reqTime + privateKey + contractRoot.getString("body");
        String sign = Md5Util.MD5(md5Str);

        System.out.println(sign);

    }

    @Test
    public void test255() {

        JSONObject result = new JSONObject();
        Map<String, Object> contractRootMap = Maps.newHashMap();
        result.put("contractRoot", contractRootMap);

        JSONObject bodyMap = new JSONObject();
        contractRootMap.put("body", bodyMap);

        String param = "{\"contractRoot\":{\"head\":{\"apiId\":\"200047\",\"channelCode\":\"202101101409\","
            + "\"reqTime\":\"20211116170329269\",\"sign\":\"83572166e884fec38bf5dd3b90b0324d\","
            + "\"transactionId\":\"d6589e87-4acc-4291-9eca-0222ec9f48695011\",\"version\":\"1.0\"},"
            + "\"body\":{\"createTime\":\"20211116170329269\",\"orderItemId\":\"910213499666255872\",\"price\":0,"
            + "\"quantity\":1,\"rightsId\":\"202101101409\",\"serverNum\":\"13396819820\",\"skuCode\":\"20211101510193\","
            + "\"skuId\":\"0410315670-1\",\"skuName\":\"优酷随心看合约包首月\"}}}";

        JSONObject root = JSON.parseObject(param);
        JSONObject contractRoot = root.getJSONObject("contractRoot");
        JSONObject head = contractRoot.getJSONObject("head");
        head.remove("channelCode");
        head.remove("apiId");

        contractRootMap.put("head", head);

        JSONObject params = contractRoot.getJSONObject("body");
        params.putAll(head);

        System.out.println(result);
    }

    @Test
    public void test257() {

        String sign = StringUtils.lowerCase(Md5Util.MD5("S011582A010099fc2138a59cd4d96b3bb15fdbd922ec5" + "f6381f234be34f5389fe8702572e28ce"));

        System.out.println(sign);

        String str = "-30";
        System.out.println(Integer.parseInt(str));

        System.out.println(DateUtil.addMins(new Date(), -180));

        String val = "{\"youku_alipay_zfb1212_H5\":\"ngfe_tag__7b2lg8hglo\"}";
        Map<String, String> double11TaskFromMap = JSON.parseObject(val, Map.class);

        System.out.println(double11TaskFromMap);
    }



        /**
         * 生成签名数据_HmacSHA1加密
         *
         * @param data 待加密的数据
         * @param key  加密使用的key
         * @throws NoSuchAlgorithmException
         */
    public String getSignature(String data, String key, String cryptoType) throws Exception {

        byte[] keyBytes = key.getBytes();
        // 根据给定的字节数组构造一个密钥。
        SecretKeySpec signingKey = new SecretKeySpec(keyBytes, cryptoType);
        Mac mac = Mac.getInstance(cryptoType);
        mac.init(signingKey);

        byte[] rawHmac = mac.doFinal(data.getBytes());

        String hexBytes = byte2hex(rawHmac);
        return hexBytes;
    }

    private String byte2hex(final byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            // 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式。
            stmp = (Integer.toHexString(b[n] & 0xFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs;
    }

    private void printprint(Son son) {
        System.out.println(son.getsParam());
    }

    /**DistributeOperateService
     * 删除文件
     * @param fileUrl
     * @return
     */
    private boolean fileDelete(String fileUrl) {
        if (StringUtils.isEmpty(fileUrl)) {
            return false;
        } else {
            File f = new File(fileUrl);
            if (f.isDirectory()) {
                return false;
            } else if (!f.exists()) {
                return false;
            } else {
                return f.delete();
            }
        }
    }

    private static int toHash(String key) {
        int arraySize = 11113; // 数组大小一般取质数
        int hashCode = 0;
        for (int i = 0; i < key.length(); i++) { // 从字符串的左边开始计算
            int letterValue = key.charAt(i) - 96;// 将获取到的字符串转换成数字，比如a的码值是97，则97-96=1
            // 就代表a的值，同理b=2；
            hashCode = ((hashCode << 5) + letterValue) % arraySize;// 防止编码溢出，对每步结果都进行取模运算
        }
        return hashCode;
    }


    private static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        //MD5加密后bytes长度16转换成32位16进制字符串
        for (int i = 0; i < bytes.length; i++) {
            /**
             * 在32位的电脑中数字都是以32格式存放的，如果是一个byte(8位)类型的数字，
             * 他的高24位里面都是随机数字，低8位才是实际的数据。
             * java.lang.Integer.toHexString() 方法的参数是int(32位)类型.
             * 如果输入一个byte(8位)类型的数字，这个方法会把这个数字的高24为也看作有效位，
             * 这就必然导致错误，使用& 0XFF操作，可以把高24位置0以避免这样错误.
             *
             * 0xFF = 1111 1111　 低8位为1，高位都为0
             * 故 &0xFF 可将数字的高位都置为0，低8位不变
             *
             * */
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }


    /**
     * 快手
     */
    @Test
    public void test251() {

        String param = "{\"appkey\":\"ks675258470929942760\",\"param\":{\"account\":\"13695849923\","
            + "\"accountType\":1,\"expireTime\":1637580742687,\"ksOrderId\":\"2132600015760298\","
            + "\"orderId\":\"22746616298\",\"price\":1990,\"relItemId\":\"3773074855180\",\"relSkuId\":\"0\","
            + "\"sellerId\":\"1317594180\"},\"sign\":\"acb4fd8c360aa5bcf7484558bf2ddbef\",\"signMethod\":\"MD5\","
            + "\"timestamp\":1637566345613,\"version\":\"1.0\"}";

        JSONObject jsonObject = JSONObject.parseObject(param, Feature.OrderedField);

        Map<String, String> params = Maps.newHashMap();
        if (jsonObject != null) {
            for (String key : jsonObject.keySet()) {
                params.put(key, jsonObject.getString(key));
            }
        }

        System.out.println(this.sign(params));

    }



    // 加签方法
    private String sign(Map<String, String> requestParamMap) {

        String param = this.getSignParam(requestParamMap);
        String signSecret = "c14d7c93e7c083d1b26bae4a8d05e7ee";
        String inputStr = param + "&signSecret=" + signSecret;
        return DigestUtils.md5Hex(inputStr);
    }

    private String getSignParam(Map<String, String> requestParamMap) {

        String appkey = checkAndGetParam(requestParamMap, "appkey");

        String version = requestParamMap.get("version").toString();
        String signMethod = requestParamMap.get("signMethod").toString();
        String timestamp = requestParamMap.get("timestamp").toString();
        String param = requestParamMap.get("param").toString();
        String access_token = requestParamMap.get("access_token");
        String method = requestParamMap.get("method");

        Map<String, String> signMap = Maps.newHashMap();
        //必传参数
        signMap.put("appkey", appkey);

        //可选参数
        if (signMethod != null) {
            signMap.put("signMethod", signMethod);
        }
        if (version != null) {
            signMap.put("version", version);
        }
        if (timestamp != null) {
            signMap.put("timestamp", timestamp);
        }
        if (param != null) {
            signMap.put("param", param);
        }
        if (access_token != null) {
            signMap.put("access_token", access_token);
        }
        if (method != null) {
            signMap.put("method", method);
        }
        return sortAndJoin(signMap);
    }

    private String checkAndGetParam(Map<String, String> paramMap, String paramKey) {
        String value = paramMap.get(paramKey).toString();
        if (org.apache.commons.lang.StringUtils.isBlank(value)) {
            throw new IllegalArgumentException(paramKey + " not exist");
        }
        return value;
    }

    // 排序
    private String sortAndJoin(Map<String, String> params) {
        TreeMap<String, String> paramsTreeMap = Maps.newTreeMap();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (entry.getValue() == null) {
                continue;
            }
            paramsTreeMap.put(entry.getKey(), entry.getValue());
        }
        String signCalc = "";
        for (Map.Entry<String, String> entry : paramsTreeMap.entrySet()) {
            signCalc = String.format("%s%s=%s&", signCalc, entry.getKey(), entry.getValue());
        }
        if (signCalc.length() > 0) {
            signCalc = signCalc.substring(0, signCalc.length() - 1);
        }
        return signCalc;
    }
}
