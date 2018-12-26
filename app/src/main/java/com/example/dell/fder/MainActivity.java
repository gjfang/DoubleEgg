package com.example.dell.fder;


import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.fder.Adapter.FriendAdapter;
import com.example.dell.fder.Adapter.MessageAdapter;
import com.example.dell.fder.Adapter.MomentsAdapter;
import com.example.dell.fder.Adapter.SquareAdapter;
import com.example.dell.fder.ObjectClass.Friend;
import com.example.dell.fder.ObjectClass.Message;
import com.example.dell.fder.ObjectClass.Moments;
import com.example.dell.fder.ObjectClass.Square;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private BottomNavigationView mBottomNavigationView;
    public TextView nameOfNow;
    private  int  fragNum;
    private Fragment [] mFragments=new Fragment[4];

    private Moments[] fruits = {new Moments("震惊", R.drawable.bg_tic,"2018/12/27"), new Moments("复旦学子", R.drawable.main_1,"2018/12/27"),
            new Moments("竟然", R.drawable.cab,"2018/12/27"), new Moments("如此这般", R.drawable.comment,"2018/12/27"),
            new Moments("凑字", R.drawable.hotel,"2018/12/27"), new Moments("待定", R.drawable.main_2,"2018/12/27"),
            new Moments("新闻", R.drawable.main_3,"2018/12/27"), new Moments("大新闻", R.drawable.main_5,"2018/12/27"),
            new Moments("移动", R.drawable.main_4,"2018/12/27"), new Moments("安卓", R.drawable.main_8,"2018/12/27")};

    private List<Moments> fruitList = new ArrayList<>();

    private MomentsAdapter adapter;

    private List<Message> messageList=new ArrayList<>();
    private MessageAdapter messageAdapter;


    private Message[] messages = {
            new Message(R.drawable.apple,"Apple","I'm an apple,and you? ","14:10"),
            new Message(R.drawable.banana,"Banana","I'm a banana, and you?","14:11"),
            new Message(R.drawable.grape,"Grape","I'm a grape, and you?","14:11"),
            new Message(R.drawable.girl,"Girl","I'm a girl, and you?","14:12"),
            new Message(R.drawable.pear,"Pear","I'm a pear, and you?","14:12"),
            new Message(R.drawable.orange,"Orange","I'm an orange, and you?","14:13"),
            new Message(R.drawable.mango,"Mango","I'm a Mango, and you?","14:14"),

    };


    private List<Friend> friendList=new ArrayList<>();
    private FriendAdapter friendAdapter;


    private Friend [] friends={
            new Friend("Apple","I'm an apple. ",R.drawable.apple),
            new Friend("Orange","I'm an orange",R.drawable.orange),
            new Friend("Banana","I'm a banana",R.drawable.banana),
            new Friend("Grape","I'm a grape",R.drawable.grape),
            new Friend("Pear","I'm a pear",R.drawable.pear),
            new Friend("Girl","I'm a girl",R.drawable.girl),
            new Friend("Nicolas","I'm Nicolas",R.drawable.nicolas_cage)


    };


    private List<Square> squareList=new ArrayList<>();
    private SquareAdapter squareAdapter;

    private Square[] squares={
            new Square(new Friend("Lin","xiao  lin shi wo",R.drawable.girl),"12/25","林冲，是小说《水浒传》中的人物。原为东京八十万禁军枪棒教头，生得豹头环眼、燕颔虎须，身长八尺，人称豹子头，又唤“小张飞”。善使枪棒，重情义，武艺盖世，有万夫不当之勇。 "),
            new Square(new Friend("Fang","xiao  fang shi wo",R.drawable.nicolas_cage),"12/25","林冲，是小说《水浒传》中的人物。原为东京八十万禁军枪棒教头，生得豹头环眼、燕颔虎须，身长八尺，人称豹子头，又唤“小张飞”。善使枪棒，重情义，武艺盖世，有万夫不当之勇。 "),
            new Square(new Friend("Luo","xiao  luo shi wo",R.drawable.apple),"12/26","林冲，是小说《水浒传》中的人物。原为东京八十万禁军枪棒教头，生得豹头环眼、燕颔虎须，身长八尺，人称豹子头，又唤“小张飞”。善使枪棒，重情义，武艺盖世，有万夫不当之勇。 "),
            new Square(new Friend("Zhu","xiao  zhu shi wo",R.drawable.cat),"12/25","林冲，是小说《水浒传》中的人物。原为东京八十万禁军枪棒教头，生得豹头环眼、燕颔虎须，身长八尺，人称豹子头，又唤“小张飞”。善使枪棒，重情义，武艺盖世，有万夫不当之勇。 "),
            new Square(new Friend("Han","xiao  han shi wo",R.drawable.cherry),"12/27","林冲，是小说《水浒传》中的人物。原为东京八十万禁军枪棒教头，生得豹头环眼、燕颔虎须，身长八尺，人称豹子头，又唤“小张飞”。善使枪棒，重情义，武艺盖世，有万夫不当之勇。 "),
            new Square(new Friend("SUn","xiao  sun shi wo",R.drawable.eyes),"12/23","林冲，是小说《水浒传》中的人物。原为东京八十万禁军枪棒教头，生得豹头环眼、燕颔虎须，身长八尺，人称豹子头，又唤“小张飞”。善使枪棒，重情义，武艺盖世，有万夫不当之勇。 "),
            new Square(new Friend("Wang","xiao  wnag shi wo",R.drawable.grape),"12/23","林冲，是小说《水浒传》中的人物。原为东京八十万禁军枪棒教头，生得豹头环眼、燕颔虎须，身长八尺，人称豹子头，又唤“小张飞”。善使枪棒，重情义，武艺盖世，有万夫不当之勇。 "),
            new Square(new Friend("Chen","xiao  chen shi wo",R.drawable.banana),"12/21","林冲，是小说《水浒传》中的人物。原为东京八十万禁军枪棒教头，生得豹头环眼、燕颔虎须，身长八尺，人称豹子头，又唤“小张飞”。善使枪棒，重情义，武艺盖世，有万夫不当之勇。 "),
            new Square(new Friend("qin","xiao  qin shi wo",R.drawable.orange),"12/23","林冲，是小说《水浒传》中的人物。原为东京八十万禁军枪棒教头，生得豹头环眼、燕颔虎须，身长八尺，人称豹子头，又唤“小张飞”。善使枪棒，重情义，武艺盖世，有万夫不当之勇。 "),
            new Square(new Friend("zhao","xiao  zhao shi wo",R.drawable.pear),"12/23","林冲，是小说《水浒传》中的人物。原为东京八十万禁军枪棒教头，生得豹头环眼、燕颔虎须，身长八尺，人称豹子头，又唤“小张飞”。善使枪棒，重情义，武艺盖世，有万夫不当之勇。 "),


    };
    ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameOfNow=(TextView)findViewById(R.id.nameOfNow);
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        NavigationView navView=(NavigationView)findViewById(R.id.nav_view);

        mFragments[0]=MainPageFragment.newInstance("首页");
        mFragments[1]=SqureFragment.newInstance("发现");
        mFragments[2]=ContactFragment.newInstance("联系人");
        mFragments[3]=MessageFragment.newInstance("消息");
        fragNum=0;
          initView();
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        navView.setCheckedItem(R.id.nav_call);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                mDrawerLayout.closeDrawers();
                return true;
            }
        });
        initMoments();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 1);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MomentsAdapter(fruitList);
        recyclerView.setAdapter(adapter);


    }

    private void refreshMoments() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initMoments();
                        adapter.notifyDataSetChanged();
                        //swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void initMessage() {
        messageList.clear();
        for (int i = 0; i < 20; i++) {
            Random random = new Random();
            int index = random.nextInt(messages.length);
            messageList.add(messages[index]);
        }
    }

    private void initMoments() {
        fruitList.clear();
        for (int i = 0; i < 20; i++) {
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
    }

    private void initFriends() {
       friendList.clear();
        for (int i = 0; i < 20; i++) {
            Random random = new Random();
            int index = random.nextInt(friends.length);
            friendList.add(friends[index]);
        }
    }

    private void initSquares() {
        squareList.clear();
        for (int i = 0; i < 20; i++) {
            Random random = new Random();
            int index = random.nextInt(squares.length);
            squareList.add(squares[index]);
        }
    }
    private void initView() {
        //负责四个主界面的函数，初始化功能
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                onTabItemSelected(item.getItemId());
                return true;
            }
        });

        // 由于第一次进来没有回调onNavigationItemSelected，因此需要手动调用一下切换状态的方法
        onTabItemSelected(R.id.navigation_home);
    }

    private void onTabItemSelected(int id){
        Fragment fragment = null;
        MenuItem item=(MenuItem)findViewById(R.id.name_fragment);
        GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 1);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        switch (id){
            case R.id.navigation_home:
                fragment = mFragments[0];
                nameOfNow.setText(" 首 页");
                fragNum=0;
                invalidateOptionsMenu();

                initMoments();
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                recyclerView.setLayoutManager(layoutManager);
                adapter = new MomentsAdapter(fruitList);
                recyclerView.setAdapter(adapter);
                break;
            case R.id.navigation_dashboard:
                fragment = mFragments[1];
                fragNum=1;
                nameOfNow.setText("旦圈");
                invalidateOptionsMenu();
               initSquares();

                RecyclerView recyclerView3=(RecyclerView)findViewById(R.id.recycler_view);
                squareAdapter=new SquareAdapter(squareList);
                recyclerView3.setLayoutManager(layoutManager);
                recyclerView3.setAdapter(squareAdapter);


                break;

            case R.id.navigation_friends:
                fragment = mFragments[2];
                fragNum=2;
                nameOfNow.setText("联系人");
                invalidateOptionsMenu();
             initFriends();
             RecyclerView recyclerView2=(RecyclerView)findViewById(R.id.recycler_view);
             friendAdapter=new FriendAdapter(friendList);
             recyclerView2.setLayoutManager(layoutManager);
             recyclerView2.setAdapter(friendAdapter);
                break;


            case R.id.navigation_notifications:
                fragment = mFragments[3];

                fragNum=3;
                nameOfNow.setText(" 消 息");
                invalidateOptionsMenu();


                initMessage();
                RecyclerView recyclerView1=(RecyclerView)findViewById(R.id.recycler_view);
                messageAdapter=new MessageAdapter(messageList);
                recyclerView1.setLayoutManager(layoutManager);
                recyclerView1.setAdapter(messageAdapter);
                break;
        }
        if(fragment!=null) {//用来替换，把之前的主页面的布局换掉
           getSupportFragmentManager().beginTransaction().replace(R.id.home_container,fragment).commit();

        }
    }









    public boolean onCreateOptionsMenu(Menu menu) {
        //顶部标题栏创建
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

//         动态设置ToolBar状态,控制某些按钮的显隐性——右上角按钮
        switch (fragNum) {
            case 0:
//          menu.findItem(R.id.name_fragment).setTitle("Main Page");
                menu.findItem(R.id.backup).setVisible(true);
                menu.findItem(R.id.delete).setVisible(false);
                menu.findItem(R.id.settings).setVisible(false);
                break;
            case 1:
//                menu.findItem(R.id.name_fragment).setTitle("Square ");
                menu.findItem(R.id.backup).setVisible(false);
                menu.findItem(R.id.delete).setVisible(false);
                menu.findItem(R.id.settings).setVisible(false);
                break;
            case 2:
//                menu.findItem(R.id.name_fragment).setTitle("Contact");
                menu.findItem(R.id.backup).setVisible(false);
                menu.findItem(R.id.delete).setVisible(true);
                menu.findItem(R.id.settings).setVisible(false);
                break;
            case 3:
//                menu.findItem(R.id.name_fragment).setTitle("Message ");
                menu.findItem(R.id.backup).setVisible(false);
                menu.findItem(R.id.delete).setVisible(false);
                menu.findItem(R.id.settings).setVisible(true);
                break;
                default:
                    break;
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //顶部标题栏中按钮点击事件，待完善
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.backup:
                Toast.makeText(this, "You clicked Backup", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this, "You clicked Settings", Toast.LENGTH_SHORT).show();
                break;

            default:
        }
        return true;
    }
}
