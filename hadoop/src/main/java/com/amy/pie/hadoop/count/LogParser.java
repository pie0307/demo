package com.amy.pie.hadoop.count;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Author : liuby.
 * Description :
 * Date : Created in 2018/5/10 10:59
 */
public class LogParser {
    public static final SimpleDateFormat FORMAT = new SimpleDateFormat(
            "d/MMM/yyyy:HH:mm:ss", Locale.ENGLISH);
    public static final SimpleDateFormat dateformat1 = new SimpleDateFormat(
            "yyyyMMddHHmmss");

    /**
     * 解析英文时间字符串
     *
     * @param string
     * @return
     * @throws ParseException
     */
    private Date parseDateFormat(String string) {
        Date parse = null;
        try {
            parse = FORMAT.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }

    /**
     * 解析日志的行记录
     *
     * @param line
     * @return 数组含有5个元素，分别是ip、时间、url、状态、流量
     */
    public String[] parse(String line) {
        String ip = parseIP(line);
        String time = parseTime(line);
        String url = parseURL(line);
        String status = parseStatus(line);
        String traffic = parseTraffic(line);

        return new String[]{ip, time, url, status, traffic};
    }

    private String parseTraffic(String line) {
        final String trim = line.substring(line.lastIndexOf("\"") + 1)
                .trim();
        String traffic = trim.split(" ")[1];
        return traffic;
    }

    private String parseStatus(String line) {
        final String trim = line.substring(line.lastIndexOf("\"") + 1)
                .trim();
        String status = trim.split(" ")[0];
        return status;
    }

    private String parseURL(String line) {
        final int first = line.indexOf("\"");
        final int last = line.lastIndexOf("\"");
        String url = line.substring(first + 1, last);
        return url;
    }

    private String parseTime(String line) {
        final int first = line.indexOf("[");
        final int last = line.indexOf("+0800]");
        String time = line.substring(first + 1, last).trim();
        Date date = parseDateFormat(time);
        return dateformat1.format(date);
    }

    private String parseIP(String line) {
        String ip = line.split("- -")[0].trim();
        return ip;
    }

    public static void main(String[] args) {
        LogParser logParser = new LogParser();

        String value = "10.16.15.16 - - [07/May/2018:00:00:01 +0800] \"GET /AMI/resblockInterf/focusBuildingServer!isFocusResblocksOfIds.do?resblock_ids=%5B'2412377335017995'%5D HTTP/1.0\" 200 63 0.024";
        final String[] parsed = logParser.parse(value);

        // step1.过滤掉静态资源访问请求
        if (parsed[2].startsWith("GET /static/")
                || parsed[2].startsWith("GET /uc_server")) {
            return;
        }
        // step2.过滤掉开头的指定字符串
        if (parsed[2].startsWith("GET /")) {
            parsed[2] = parsed[2].substring("GET /".length());
        } else if (parsed[2].startsWith("POST /")) {
            parsed[2] = parsed[2].substring("POST /".length());
        }
        // step3.过滤掉结尾的特定字符串
        if (parsed[2].endsWith(" HTTP/1.0")) {
            parsed[2] = parsed[2].substring(0, parsed[2].length()
                    - " HTTP/1.0".length());
        }
        if (parsed[2].contains("?")) {
            parsed[2] = parsed[2].substring(0, parsed[2].indexOf("?"));
        }
        // step4.只写入前三个记录类型项
        String ret = parsed[0] + "\t" + parsed[1] + "\t" + parsed[2];
        System.out.printf(ret);
    }

}
