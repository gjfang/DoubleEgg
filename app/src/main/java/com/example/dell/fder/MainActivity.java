package com.example.dell.fder;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
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
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.example.dell.fder.Adapter.FriendAdapter;
import com.example.dell.fder.Adapter.MessageAdapter;
import com.example.dell.fder.Adapter.MomentsAdapter;
import com.example.dell.fder.Adapter.SquareAdapter;
import com.example.dell.fder.Fragment.ContactFragment;
import com.example.dell.fder.Fragment.MainPageFragment;
import com.example.dell.fder.Fragment.MessageFragment;
import com.example.dell.fder.Fragment.SqureFragment;
import com.example.dell.fder.ObjectClass.Friend;
import com.example.dell.fder.ObjectClass.Message;
import com.example.dell.fder.ObjectClass.Moments;
import com.example.dell.fder.ObjectClass.Square;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private BottomNavigationView mBottomNavigationView;
    public TextView nameOfNow;
    private  int  fragNum;
    private Fragment [] mFragments=new Fragment[4];

    private Moments[] fruits = {
            new Moments("上海复旦大学教育发展基金会第三届理事会第十次会议召开", R.drawable.bg_tic,"2018/12/27","12月18日上午，上海复旦大学教育发展基金会理事会第三届第十次会议（以下简称“上海基金会”）在北京召开。复旦大学党委书记、基金会理事长焦扬，校长、基金会副理事长许宁生，校党委副书记、基金会副理事长兼秘书长许征，以及第三届理事会理事袁天凡、陈仲儿、汪新芽、鲁育宗、苟燕楠、杨增国出席会议,卢志强、梁信军、谢仕荣、翟立、张志勇五位理事派代表出席会议。校党委副书记、基金会监事长袁正宏列席会议。基金会副秘书长、投资委员会部分委员及秘书处工作人员参加会议。"), new Moments("相融相合 相得益彰 第六届上医文化论坛举行", R.drawable.main_1,"2018/12/27","许宁生肯定了投资委员会今年以来的工作成效，希望基金会秘书处不断提高工作水平,取得更大发展。他指出，基金会要在国际交流、人才引育、培育英才等方面不断积累资源，为学校建设成为中国特色世界一流大学提供必要的支持。"),
            new Moments("“庆祝改革开放40周年”理论研讨会举行", R.drawable.cab,"2018/12/27","焦扬在讲话中向各位理事、投资委员会委员对基金会各项工作的鼎力支持与无私奉献表示感谢，对基金会秘书处过去一年来的工作成效表示肯定。她指出，教育发展基金会对学校事业发展有重要支持作用，下一步基金会要着力强化战略布局，提升服务能力；着力加强筹资能力，提升投资效益；着力加强内部管理，提升治理水平，为学校“双一流”建设提供更加有力支持。"), new Moments("决胜全面建成小康社会：理论·实践·战略中国大学智库论坛2018年年会在复旦大学举行", R.drawable.comment,"2018/12/27","此外，复旦大学教育发展基金会（海外）（以下简称“海外基金会”）董事会会议同期于12月18日下午在北京召开。董事会主席许征、董事袁天凡、陈仲儿、杨增国出席会议，陈耀璋、谢仕荣两位董事派代表出席。与会董事一致通过了海外基金会2018年工作报告和财务报告，原则同意2019年基金会日常预算，并就海外基金会如何发挥地域优势，做好2019年筹资和拓展工作展开了讨论。"),
            new Moments("深化医教协同 探索综合性大学医学教育管理新体制教育部、国家卫健委、上海市共建托管复旦大学上海医学院及其直属附属医院", R.drawable.hotel,"2018/12/27","焦扬在讲话中深情回顾了40年来复旦师生为改革开放和社会主义现代化建设作出的贡献。她说，改革开放40年是复旦大学历史上发展最好最快的时期。40年来，复旦师生在党中央的坚强领导下，始终坚持改革创新、敢为人先、开放发展、追求卓越，始终与党和国家发展同向同行，与时代大潮同向同行。在全校师生团结奋斗下，学校乘着改革开放的春风大踏步前进，各项事业蓬勃发展，综合实力显著提升，学校面貌发生历史性变化。复旦的发展和巨变是全国高等教育大发展、大跨越的缩影，是改革开放以来中华大地历史巨变、伟大飞跃的缩影。"), new Moments("谷超豪院士铜像在江湾校区揭幕", R.drawable.main_2,"2018/12/27","焦扬强调，站在新的历史起点上，学校要深入学习贯彻习近平总书记在庆祝改革开放40周年大会上的重要讲话精神，坚定改革开放再出发的信心和决心。要坚持马克思主义指导地位，不断推进基于实践基础上的理论创新；要深化“三全育人”改革，培养更多担当民族复兴大任的时代新人；要坚持全面深化改革，实现学校治理体系和治理能力现代化；要践行“四个服务”，勇担国家使命、再立时代新功；要实施全球发展战略，拓展学校对外开放新格局；要加强党的全面领导，不断提高党的建设质量。"),
            new Moments("第四届复旦科技创新论坛举行杰出数学家英格丽·多贝西斩获“复旦-中植科学奖”", R.drawable.main_4,"2018/12/27","尹汉宁作为特邀嘉宾作了《改革开放与“三个解放”》的专题报告。复旦大学文科资深教授、历史学系教师姜义华，复旦大学文科资深教授、中文系教师陈思和，新闻学院院长、人民日报社原副总编辑米博华，哲学学院教授、长江学者吴晓明，经济学院院长、长江学者张军，中国研究院院长张维为，国际关系与公共事务学院执行院长苏长和，马克思主义学院常务副院长李冉，分别结合各自的学科和改革开放的历史进程，谈了学习习近平总书记在庆祝改革开放40周年大会上讲话精神的体会"), new Moments("部校共建复旦大学马克思主义学院第三次工作推进会举行", R.drawable.main_8,"2018/12/27","陈宝生指出，三方共建托管复旦大学上海医学院及其6家附属医院，是全面贯彻十九大精神、落实全国教育大会精神、深化教育综合改革和医药卫生体制改革的重要举措，体现了三方合力推动教育事业尤其是医学教育事业的真情厚意、真金白银、真枪实弹、真抓实干。他对学校未来办学提出四点希望：一是加强领导,坚持正确办学方向，确保高校始终成为培养社会主义事业建设者和接班人的坚强阵地")
    };

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
            new Friend("Nicolas","I'm Nicolas",R.drawable.nicolas_cage),
            new Friend("Ball","I'm a floating ball",R.drawable.main_8),
            new Friend("NewTrip","I'm a new trip!",R.drawable.newtrip),
            new Friend("TripCreate","I'm a trip create",R.drawable.tripcreate)


    };


    private List<Square> squareList=new ArrayList<>();
    private SquareAdapter squareAdapter;

    private Square[] squares={
            new Square(friends[0],"12/25","林冲，是小说《水浒传》中的人物。原为东京八十万禁军枪棒教头，生得豹头环眼、燕颔虎须，身长八尺，人称豹子头，又唤“小张飞”。善使枪棒，重情义，武艺盖世，有万夫不当之勇。 ",R.drawable.photo1),
            new Square(friends[1],"12/25","这个世界上有很多奇怪的人，奇怪的事，也有很多无法用科学解释的东西。而你要做的，就是控制自己的贪欲。因为，一旦欲望之门打开，你不知道自己面对的到底是什么，也不懂自己即将失去的，会不会是自己的性命。 ",R.drawable.community),
            new Square(friends[2],"12/26","旅行是一场美丽的失踪，失踪是一场美丽的旅行。不是为了让别人能把我暂时忘记，而是为了让别人能把我想起。 ",R.drawable.photo2),
            new Square(friends[3],"12/25","有一种旅行，叫单车旅行。它没有奢侈准备，只是需要垮上后座的勇气和一颗想走即走的心，就算是一辆永久单车也能让你的梦想走很远。 ",R.drawable.photo3),
            new Square(friends[4],"12/27","俗世不过名利场，红尘不过风月所。有谁能真正抛得开名利的诱惑?又有谁能躲得过情感的牵绊?千帆过尽，多少人还在轻吟着昨日的那阕旧词;转身陌路，多少人还在念念不忘那一场水月镜花.",R.drawable.picture_01),
            new Square(friends[5],"12/23","一个人的阅历，全部写在眼睛里，我的眼神从清亮到沉浊，所经历的不过是一场又一场的伤害和一次又一次的离别。 ",R.drawable.btn_inrecommend),
            new Square(friends[6],"12/23","一盏灯，一卷经，句句梵唱伴钟鼓。年年岁岁花相似，岁岁年年人不同，前世因果今轮回，蜡炬成灰泪已尽。花已开过，花已落过，并不悔。前世情，今生缘，冤冤相报何时了?一声嘘唏，两声长叹!梦里落花知多少。",R.drawable.bg_tic),
            new Square(friends[7],"12/21","你会不会想过，有一天突然惊醒，发现自己在高一的课堂上睡着了。现在经历的所有其实只是一场梦。阳光照的你脸皱成一团，你告诉同桌你做了一个好长的梦，同桌笑你白痴，让你好好听课。你发现，现世安稳，岁月静好，一切都充满着希望。 ",R.drawable.newtrip),
            new Square(friends[8],"12/23","如今的我就是，你不理我，我也不会多说，你讽刺我，我就一笑而过，你若爱我，我便爱你更多。长大后才发现，“如约而至”是个多么美好的词，等的辛苦，却从不辜负。 ",R.drawable.ps),
            new Square(friends[9],"12/23","你看，大家为了追求幸福，排成长长一队，然而，幸福快车从身旁呼啸而过，只卷来尘土旋飞。每天弹奏的曲调单调乏味，生活的旋律只有一个累，面对喧嚣再也感觉不到生活的美，快乐仿佛长着翅膀远飞。 ",R.drawable.tiketitem),


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
        for (int i = 0; i < messages.length; i++) {
            messageList.add(messages[i]);
        }
    }

    private void initMoments() {
        fruitList.clear();
        for (int i = 0; i < fruits.length; i++) {

            fruitList.add(fruits[i]);
        }
    }

    private void initFriends() {
       friendList.clear();
        for (int i = 0; i < friends.length; i++) {
            friendList.add(friends[i]);
        }
    }

    private void initSquares() {
        squareList.clear();
        for (int i = 0; i < squares.length; i++) {
            squareList.add(squares[i]);
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
                menu.findItem(R.id.add_new_square).setVisible(false);
                menu.findItem(R.id.delete).setVisible(false);
                menu.findItem(R.id.settings).setVisible(false);
                break;
            case 1:
//                menu.findItem(R.id.name_fragment).setTitle("Square ");
                menu.findItem(R.id.add_new_square).setVisible(true);
                menu.findItem(R.id.delete).setVisible(false);
                menu.findItem(R.id.settings).setVisible(false);
                break;
            case 2:
//                menu.findItem(R.id.name_fragment).setTitle("Contact");
                menu.findItem(R.id.add_new_square).setVisible(false);
                menu.findItem(R.id.delete).setVisible(false);
                menu.findItem(R.id.settings).setVisible(false);
                break;
            case 3:
//                menu.findItem(R.id.name_fragment).setTitle("Message ");
                menu.findItem(R.id.add_new_square).setVisible(false);
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
            case R.id.add_new_square:
                startActivity(new Intent(MainActivity.this,NewSquareActivity.class));
                break;
            case R.id.settings:
                Toast.makeText(this, "You clicked Settings", Toast.LENGTH_SHORT).show();
                break;

            default:
        }
        return true;
    }

    public void changeImage(View view){
        ((ImageView)view).setImageResource(R.mipmap.icon_praise_selected);
    }
}
