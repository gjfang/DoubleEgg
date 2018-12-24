package com.example.dell.fder;


import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private BottomNavigationView bottomNavigationView;
    private BottomNavigationView mBottomNavigationView;
//    private ViewPageAdapter viewPagerAdapter;
//    private ViewPager viewPager;
//    private MenuItem menuItem;
    public TextView nameOfNow;
    private  int  fragNum;
    private Fragment [] mFragments=new Fragment[4];

    private Moments[] fruits = {new Moments("Apple", R.drawable.apple), new Moments("Banana", R.drawable.banana),
            new Moments("Orange", R.drawable.orange), new Moments("Watermelon", R.drawable.watermelon),
            new Moments("Pear", R.drawable.pear), new Moments("Grape", R.drawable.grape),
            new Moments("Pineapple", R.drawable.pineapple), new Moments("Strawberry", R.drawable.strawberry),
            new Moments("Cherry", R.drawable.cherry), new Moments("Mango", R.drawable.mango)};

    private List<Moments> fruitList = new ArrayList<>();

    private MomentsAdapter adapter;


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

    private void initMoments() {
        fruitList.clear();
        for (int i = 0; i < 50; i++) {
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
    }








    private void initView() {
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        //mBottomNavigationView.getMaxItemCount()

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

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        switch (id){
            case R.id.navigation_home:
                fragment = mFragments[0];
                nameOfNow.setText(" 首 页");
                fragNum=0;
                invalidateOptionsMenu();
                break;
            case R.id.navigation_dashboard:
                fragment = mFragments[1];
                fragNum=1;
                nameOfNow.setText(" 广 场");
                invalidateOptionsMenu();
                initMoments();
                break;

            case R.id.navigation_friends:
                fragment = mFragments[2];
                fragNum=2;
                nameOfNow.setText("联系人");
                invalidateOptionsMenu();
                initMoments();
                break;
            case R.id.navigation_notifications:
                fragment = mFragments[3];

                fragNum=3;
                nameOfNow.setText(" 消 息");
                invalidateOptionsMenu();
initMoments();

                break;
        }
        if(fragment!=null) {//用来替换，把之前的主页面的布局换掉
           getSupportFragmentManager().beginTransaction().replace(R.id.home_container,fragment).commit();
//           getSupportFragmentManager().beginTransaction().replace(R.id.drawer_layout,fragment).commit();

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
