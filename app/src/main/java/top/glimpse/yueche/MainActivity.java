package top.glimpse.yueche;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, AdapterView.OnItemSelectedListener {

    private EditText idcard;
    private EditText phonenum;
    private EditText verfiyNum;
//    private TextView date;

    private Button sendvnum;
    private Button start;

    private TextView output;

    private CheckBox cb1;
    private CheckBox cb2;
    private CheckBox cb3;
    private CheckBox cb4;
    private CheckBox cb5;
    private CheckBox cb6;
    private CheckBox cb7;
    private CheckBox cb8;

    private LinearLayout select_coach;
    private Spinner select_coach_spinner;
    private RelativeLayout select_time;

    List<Integer> checked = new ArrayList<Integer>();
    List<Integer> coach = new ArrayList<Integer>();
    List<String> coachName = new ArrayList<String>();


    private String sidcard;
    private String sphone;
    private String sverfiyNum;
    private String sdate;

    private int coachid;


    private Context context = this;

    private String soutput = "";
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            String result = (String) msg.obj;

            switch (msg.what) {
                case 0x123:
                    Toast.makeText(context, result, Toast.LENGTH_LONG).show();
                    output.append("\n" + result);
                    break;
                default:
                    break;
            }
        }
    };
    YueChe yueChe = new YueChe(handler);

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    private boolean isTime = true;

    private ImageView time_btn;
    private ImageView coach_btn;
    private ImageView notice_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

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
        coachName.add("张乐勇");
        coachName.add("邴柱");
        coachName.add("白培超");
        coachName.add("郭梅伟");
        coachName.add("李彦青");
        coachName.add("刘振武");
        coachName.add("杨兆涛");
        coachName.add("王勇");
        coachName.add("张乐波");
        coachName.add("张士进");
        coachName.add("胡士超");
        coachName.add("付东争");
        coachName.add("杨建峰");
        coachName.add("董良");
        coachName.add("万令岩");
        coachName.add("吕合锋");
        coachName.add("张长百");

        coach.add((Integer)3);
        coach.add((Integer)8);
        coach.add((Integer)9);
        coach.add((Integer)12);
        coach.add((Integer)13);
        coach.add((Integer)14);
        coach.add((Integer)15);
        coach.add((Integer)16);
        coach.add((Integer)18);
        coach.add((Integer)19);
        coach.add((Integer)20);
        coach.add((Integer)21);
        coach.add((Integer)62);
        coach.add((Integer)73);
        coach.add((Integer)75);
        coach.add((Integer)76);
        coach.add((Integer)99);

        idcard = (EditText) findViewById(R.id.idcard);
        phonenum = (EditText) findViewById(R.id.phonenum);
        verfiyNum = (EditText) findViewById(R.id.verfiyNum);

        output = (TextView) findViewById(R.id.output);
        output.setMovementMethod(ScrollingMovementMethod.getInstance());
//        date = (TextView) findViewById(R.id.date);
//
//        date.setText("练车日期:" + getNewDay());

        sendvnum = (Button) findViewById(R.id.sendvnum);
        start = (Button) findViewById(R.id.start);
        sendvnum.setOnClickListener(this);
        start.setOnClickListener(this);

        time_btn = (ImageView) findViewById(R.id.time);
        coach_btn = (ImageView) findViewById(R.id.coach);
        notice_btn = (ImageView) findViewById(R.id.what);
        time_btn.setOnClickListener(this);
        coach_btn.setOnClickListener(this);
        notice_btn.setOnClickListener(this);

        cb1 = (CheckBox) findViewById(R.id.one);
        cb2 = (CheckBox) findViewById(R.id.two);
        cb3 = (CheckBox) findViewById(R.id.three);
        cb4 = (CheckBox) findViewById(R.id.four);
        cb5 = (CheckBox) findViewById(R.id.five);
        cb6 = (CheckBox) findViewById(R.id.six);
        cb7 = (CheckBox) findViewById(R.id.seven);
        cb8 = (CheckBox) findViewById(R.id.eight);
        cb1.setOnCheckedChangeListener(this);
        cb2.setOnCheckedChangeListener(this);
        cb3.setOnCheckedChangeListener(this);
        cb4.setOnCheckedChangeListener(this);
        cb5.setOnCheckedChangeListener(this);
        cb6.setOnCheckedChangeListener(this);
        cb7.setOnCheckedChangeListener(this);
        cb8.setOnCheckedChangeListener(this);

        select_time = (RelativeLayout) findViewById(R.id.select_time);
        select_coach = (LinearLayout) findViewById(R.id.select_coach);

        select_coach_spinner = (Spinner) findViewById(R.id.select_coach_spinner);
        select_coach_spinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> coachAdapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, coachName);
        select_coach_spinner.setAdapter(coachAdapter);

        preferences = getSharedPreferences("yueche", MODE_PRIVATE);
        editor = preferences.edit();

        sidcard = preferences.getString("idcard", null);
        sphone = preferences.getString("phonenum", null);
        idcard.setText(sidcard);
        phonenum.setText(sphone);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.time:
                isTime = true;
                select_coach.setVisibility(View.GONE);
                select_time.setVisibility(View.VISIBLE);
                Log.i("tag", "time");
                break;
            case R.id.coach:
                isTime = false;
                select_coach.setVisibility(View.VISIBLE);
                select_time.setVisibility(View.GONE);
                Log.i("tag", "coach");

                break;
            case R.id.what:
                Intent intent = new Intent(context, NoticeActivity.class);
                startActivity(intent);
                break;
            case R.id.sendvnum:

                sidcard = idcard.getText().toString();
                sphone = phonenum.getText().toString();

                editor.putString("idcard", sidcard);
                editor.putString("phonenum", sphone);
                editor.commit();

                new Thread(){
                    public void run() {
                        yueChe.getVerfiyNum(sidcard, sphone);
                    }
                }.start();
                break;
            case R.id.start:


                sidcard = idcard.getText().toString();
                sphone = phonenum.getText().toString();
                sverfiyNum = verfiyNum.getText().toString();

                editor.putString("idcard", sidcard);
                editor.putString("phonenum", sphone);
                editor.commit();

                final SimpleDateFormat df = new SimpleDateFormat("HH:mm");
                System.out.println(df.format(new Date()));


                if (isTime) {
                    new Thread(){
                        public void run() {


                            while (true) {

//                                String time = df.format(new Date());
//                                if (time.equals("21:00")) {
                                    for (int i : checked) {//循环时间段
                                        for (int j : coach) {//循环教练
                                            yueChe.doPostTime(sidcard, sphone,sverfiyNum, getNewDay(),
                                                    i,j);
                                        }
                                    }
//                                }

                                try {
                                    Thread.sleep(500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }

                        }
                    }.start();
                }
                else {

                    new Thread(){
                        public void run() {


                            while (true) {

//                                String time = df.format(new Date());
//                                if (time.equals("21:00")) {
                                    for (int i = 1 ; i <= 8;i++) {//循环时间段
                                            yueChe.doPostCoach(sidcard, sphone,sverfiyNum, getNewDay(),
                                                    coachid, i);

                                    }
//                                }

                                try {
                                    Thread.sleep(500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }

                        }
                    }.start();


                }


                break;
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        switch (buttonView.getId()) {
            case R.id.one:
                if (isChecked) {
                    checked.add(1);
                }
                else {
                    checked.remove((Integer) 1);
                }
                break;
             case R.id.two:
                 if (isChecked) {
                     checked.add(2);
                 }
                 else {
                     checked.remove((Integer) 2);
                 }
                break;
             case R.id.three:
                 if (isChecked) {
                     checked.add(3);
                 }
                 else {
                     checked.remove((Integer) 3);
                 }
                break;
             case R.id.four:
                 if (isChecked) {
                     checked.add(4);
                 }
                 else {
                     checked.remove((Integer) 4);
                 }
                break;
             case R.id.five:
                 if (isChecked) {
                     checked.add(5);
                 }
                 else {
                     checked.remove((Integer) 5);
                 }
                break;
             case R.id.six:
                 if (isChecked) {
                     checked.add(6);
                 }
                 else {
                     checked.remove((Integer) 6);
                 }
                break;
             case R.id.seven:
                 if (isChecked) {
                     checked.add(7);
                 }
                 else {
                     checked.remove((Integer) 7);
                 }
                break;
             case R.id.eight:
                 if (isChecked) {
                     checked.add(8);
                 }
                 else {
                     checked.remove((Integer) 8);
                 }
                break;

        }

        Toast.makeText(context, checked.toString(), Toast.LENGTH_SHORT).show();
    }

    private String getNewDay() {
        String newday = "";
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        int inputDayOfYear = cal.get(Calendar.DAY_OF_YEAR);
        cal.set(Calendar.DAY_OF_YEAR , inputDayOfYear+6);

        newday = df.format(cal.getTime());
        System.out.println(newday);
        return newday;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        coachid = coach.get((int)id);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
