package top.glimpse.yueche;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by joyce on 16-5-21.
 */
public class YueChe {

    private Handler handler;
    public YueChe(Handler handler) {
        this.handler = handler;
    }

//    public static void main(String[] args) throws InterruptedException {
//
//        //获取验证码
//        //getVerfiyNum();
//
//        //发送请求
//        //doPost();
//        SimpleDateFormat df = new SimpleDateFormat("HH");
//        System.out.println(df.format(new Date()));
//
//
//
//        while (true) {
//
//            String time = df.format(new Date());
//            if (time.equals("21")) {
//                for(int i = 1;i <= 8;i++) {
//                    doPost(i);
//                }
//            }
//
//            Thread.sleep(1000);
//        }
//
//    }

    public void getVerfiyNum(final String idcard, final String phone) {
        //String param = "{'idcard':'520221199412263030','phone':'18463585063'}";

        String param = "{'idcard':'" + idcard + "','phone':'"+ phone +"'}";
        String result = myDoPost("http://www.lczhengda.com/index/onlineExercise.aspx/getVerfiyNum",  param);
        System.out.println(result);

        Message msg = new Message();
        msg.what = 0x123;
        msg.obj = result;
        handler.sendMessage(msg);


    }


    /**General
     *
     Request URL:http://www.lczhengda.com/index/exercise.ashx
     Request Method:POST
     Status Code:200 OK
     Remote Address:121.42.155.124:80
     */

    /**Reponse header
     *
     Cache-Control:private
     Content-Length:41
     Content-Type:text/plain; charset=utf-8
     Date:Sat, 21 May 2016 16:02:47 GMT
     Server:Microsoft-IIS/7.0
     X-AspNet-Version:4.0.30319
     X-Powered-By:ASP.NET
     */


    /**
     * Request header
     */
    //Accept:*/*
    /**
    Accept-Encoding:gzip, deflate
    Accept-Language:zh-CN,zh;q=0.8
    Connection:keep-alive
    Content-Length:690
    Content-Type:application/x-www-form-urlencoded; charset=UTF-8
    Host:www.lczhengda.com
    Origin:http://www.lczhengda.com
    Referer:http://www.lczhengda.com/index/onlineExercise.aspx
    User-Agent:Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36
    X-Requested-With:XMLHttpRequest
     */


    /**Form Data
     *
     __VIEWSTATE:/wEPDwUKMTUxNjc2MjAwNg9kFgICAw9kFgICBQ8QDxYGHg1EYXRhVGV4dEZpZWxkBQRuYW1lHg5EYXRhVmFsdWVGaWVsZAUCaWQeC18hRGF0YUJvdW5kZ2QQFQIP6K+36YCJ5oup6L2m5Z6LAkMxFQIBMAExFCsDAmdnZGRkeXJetDnZYJKEL92cBJlU9z5PfpSi2uZmy2jxA77I7As=
     __VIEWSTATEGENERATOR:D713EC1C
     __EVENTVALIDATION:/wEdAAgkt8UC6RbuE4MS5pHhEvCVyTszhigKMwh5T/qLlNAYC4VEJo+SrW5iS07jV747hZoq0Jch2ABkbvWLmhU2kjsGpvHT34apPX7p9UI59hFZ624epzfTuo1N8YTXhv8p5ACBaiDCrPCkbk84loZDu0P5AC0FssKr110GHGfDWCgI3y7v+MEl+HoGkAIMyhQempZuahsxIR51GXFyz/eMk23P
     idcard:520221199412263030     身份证号
     phone:18463585063             电话号码
     verfiyNum:1331                短信验证码
     drpModel:1                    车型（c：1）
     drpSubject:2                  科目（科目二：1,科目三：2）
     drpSchool:7                   练车地点
     drpExerciseTime:2016/05/27    约车时间
     coachtime:0                            不明白
     selectCoach:48                教练号
     timekeyId:2                   时间段
     selectStyle:1                 预约方式
     *
     *
     *
     */

    public void doPostCoach(String idcard, String phone, String verfiyNum,
                             String date,int coachid, int timekey) {

        BufferedInputStream inputStream;
        String result = "";
        PrintWriter out = null;
        String url = "http://www.lczhengda.com/index/exercise.ashx";
        String params = "__VIEWSTATE=/wEPDwUKMTUxNjc2MjAwNg9kFgICAw9kFgICBQ8QDxYGHg1EYXRhVGV4dEZpZWxkBQRuYW1lHg5EYXRhVmFsdWVGaWVsZAUCaWQeC18hRGF0YUJvdW5kZ2QQFQIP6K+36YCJ5oup6L2m5Z6LAkMxFQIBMAExFCsDAmdnZGRkeXJetDnZYJKEL92cBJlU9z5PfpSi2uZmy2jxA77I7As=&" +
                "__VIEWSTATEGENERATOR=D713EC1C&" +
                "__EVENTVALIDATION=/wEdAAgkt8UC6RbuE4MS5pHhEvCVyTszhigKMwh5T/qLlNAYC4VEJo+SrW5iS07jV747hZoq0Jch2ABkbvWLmhU2kjsGpvHT34apPX7p9UI59hFZ624epzfTuo1N8YTXhv8p5ACBaiDCrPCkbk84loZDu0P5AC0FssKr110GHGfDWCgI3y7v+MEl+HoGkAIMyhQempZuahsxIR51GXFyz/eMk23P&" +
                "idcard="+idcard+"&" +
                "phone="+phone+"&" +
                "verfiyNum="+verfiyNum+"&" +
                "drpModel=1&" +
                "drpSubject=1&" +
                "drpSchool=1&" +
                "drpExerciseTime="+date+"&" +
                "coachtime=0&" +
                "selectCoach="+coachid+"&" +
                "timekeyId="+ timekey +"&" +
                "selectStyle=1";
        try {
            URL realUrl = new URL(url);
            URLConnection urlConnection = realUrl.openConnection();

            urlConnection.setRequestProperty("Accept", "*/*");
            urlConnection.setRequestProperty("Accept-Encoding", "gzip, deflate");
            urlConnection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
            urlConnection.setRequestProperty("Connection", "Keep-Alive");
            //urlConnection.setRequestProperty("Content-Length", "690");
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            urlConnection.setRequestProperty("user-agent",
                    "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36" +
                            "    X-Requested-With:XMLHttpRequest");

            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            out = new PrintWriter(urlConnection.getOutputStream());
            out.print(params);
            out.flush();

            inputStream = new BufferedInputStream(urlConnection.getInputStream());

            byte[] buffer = new byte[1024];
            int bytesRead;
            while((bytesRead = inputStream.read(buffer)) != -1)
            {
                result += new String(buffer, 0, bytesRead);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result);
        Message msg = new Message();
        msg.what = 0x123;
        msg.obj = result;
        handler.sendMessage(msg);

    }




    // Accept:*/*
    /**
     *
    Accept-Encoding:gzip, deflate
    Accept-Language:zh-CN,zh;q=0.8
    Connection:keep-alive
    Content-Length:670
    Content-Type:application/x-www-form-urlencoded; charset=UTF-8
    Host:www.lczhengda.com
    Origin:http://www.lczhengda.com
    Referer:http://www.lczhengda.com/index/onlineExercise.aspx
    User-Agent:Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36
    X-Requested-With:XMLHttpRequest
     *
     */

    /**
     *
     __VIEWSTATE:/wEPDwUKMTUxNjc2MjAwNg9kFgICAw9kFgICBQ8QDxYGHg1EYXRhVGV4dEZpZWxkBQRuYW1lHg5EYXRhVmFsdWVGaWVsZAUCaWQeC18hRGF0YUJvdW5kZ2QQFQIP6K+36YCJ5oup6L2m5Z6LAkMxFQIBMAExFCsDAmdnZGRkeXJetDnZYJKEL92cBJlU9z5PfpSi2uZmy2jxA77I7As=
     __VIEWSTATEGENERATOR:D713EC1C
     __EVENTVALIDATION:/wEdAAgkt8UC6RbuE4MS5pHhEvCVyTszhigKMwh5T/qLlNAYC4VEJo+SrW5iS07jV747hZoq0Jch2ABkbvWLmhU2kjsGpvHT34apPX7p9UI59hFZ624epzfTuo1N8YTXhv8p5ACBaiDCrPCkbk84loZDu0P5AC0FssKr110GHGfDWCgI3y7v+MEl+HoGkAIMyhQempZuahsxIR51GXFyz/eMk23P
     idcard:520221199412263030      身份证
     phone:18463585063              电话
     verfiyNum:                     验证码
     drpModel:1                     车型（c：1）
     drpSubject:2                   科目（科目二：1,科目三：2）
     drpSchool:7                    地点
     drpExerciseTime:2016/05/31     日期
     coachtime:1                    时间段
     selectCoach:0                  选择方式
     coachId:58                     教练号
     */

    /**
     * 教练号：
     * 张乐勇  3
     * 邴柱   8
     * 白培超 9
     * 郭梅伟 12
     * 李彦青 13
     * 刘振武 14
     * 杨兆涛 15
     * 王勇   16
     * 张乐波 18
     * 张士进 19
     * 胡士超 20
     * 付东争 21
     * 杨建峰 62
     * 董良   73
     * 万令岩 75
     * 吕合锋 76
     * 张长百 99
     */
    public void doPostTime(String idcard, String phone, String verfiyNum,
                                  String date, int coachtime, int coachId) {
        BufferedInputStream inputStream;
        String result = "";
        PrintWriter out = null;
        String url = "http://www.lczhengda.com/index/exercise.ashx";
        final String params = "__VIEWSTATE=/wEPDwUKMTUxNjc2MjAwNg9kFgICAw9kFgICBQ8QDxYGHg1EYXRhVGV4dEZpZWxkBQRuYW1lHg5EYXRhVmFsdWVGaWVsZAUCaWQeC18hRGF0YUJvdW5kZ2QQFQIP6K+36YCJ5oup6L2m5Z6LAkMxFQIBMAExFCsDAmdnZGRkeXJetDnZYJKEL92cBJlU9z5PfpSi2uZmy2jxA77I7As=&" +
                "__VIEWSTATEGENERATOR=D713EC1C&" +
                "__EVENTVALIDATION=/wEdAAgkt8UC6RbuE4MS5pHhEvCVyTszhigKMwh5T/qLlNAYC4VEJo+SrW5iS07jV747hZoq0Jch2ABkbvWLmhU2kjsGpvHT34apPX7p9UI59hFZ624epzfTuo1N8YTXhv8p5ACBaiDCrPCkbk84loZDu0P5AC0FssKr110GHGfDWCgI3y7v+MEl+HoGkAIMyhQempZuahsxIR51GXFyz/eMk23P&" +
                "idcard="+ idcard +"&" +
                "phone="+ phone +"&" +
                "verfiyNum="+ verfiyNum +"&" +
                "drpModel=1&" +
                "drpSubject=1&" +
                "drpSchool=1&" +
                "drpExerciseTime=2016/05/31&" +
                "coachtime="+ coachtime +"&" +
                "selectCoach=0&" +
                "coachId=" + coachId;
        try {
            URL realUrl = new URL(url);
            URLConnection urlConnection = realUrl.openConnection();

            urlConnection.setRequestProperty("Accept", "*/*");
            urlConnection.setRequestProperty("Accept-Encoding", "gzip, deflate");
            urlConnection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
            urlConnection.setRequestProperty("Connection", "keep-alive");
            //urlConnection.setRequestProperty("Content-Length", "674");
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            urlConnection.setRequestProperty("user-agent",
                    "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");

            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            out = new PrintWriter(urlConnection.getOutputStream());
            out.print(params);
            out.flush();

            inputStream = new BufferedInputStream(urlConnection.getInputStream());

            byte[] buffer = new byte[1024];
            int bytesRead;
            while((bytesRead = inputStream.read(buffer)) != -1)
            {
                result += new String(buffer, 0, bytesRead);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result);
        Message msg = new Message();
        msg.what = 0x123;
        msg.obj = result;
        handler.sendMessage(msg);

    }




    public String myDoPost(String url, String params) {

        BufferedInputStream inputStream;
        String result = "";
        PrintWriter out = null;

        try {
            URL realUrl = new URL(url);
            URLConnection urlConnection = realUrl.openConnection();

            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("accept", "*/*");
            urlConnection.setRequestProperty("connection", "Keep-Alive");
            urlConnection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");

            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            out = new PrintWriter(urlConnection.getOutputStream());
            out.print(params);
            out.flush();
            System.out.println("");

            inputStream = new BufferedInputStream(urlConnection.getInputStream());

            byte[] buffer = new byte[1024];
            int bytesRead;
            while((bytesRead = inputStream.read(buffer)) != -1)
            {
                result += new String(buffer, 0, bytesRead);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;

    }

}
