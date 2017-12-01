package com.techsol.letschat.ui;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.iid.FirebaseInstanceId;
import com.techsol.letschat.BaseActivity;
import com.techsol.letschat.MyApplication;
import com.techsol.letschat.R;
import com.techsol.letschat.events.GlobalBus;
import com.techsol.letschat.ui.fragments.ActivityFragmentBridge;
import com.techsol.letschat.ui.fragments.FeedFragment;
import com.techsol.letschat.ui.fragments.ProfileFragment;
import com.techsol.letschat.ui.fragments.UserListFragment;
import com.techsol.letschat.utils.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;

import static com.techsol.letschat.utils.Constants.PICK_IMAGE_REQUEST;
import static com.techsol.letschat.utils.Constants.REQUEST_IMAGE_CAPTURE;

public class TabActivity extends BaseActivity implements ActivityFragmentBridge {

    ProfileFragment profileFragment;

//    @Inject
//    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_main_activity);
        bindView();
       // ((MyApplication) getApplication()).getNetComponent().inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        GlobalBus.getBus().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        GlobalBus.getBus().unregister(this);
    }

    private void bindView() {
        profileFragment = new ProfileFragment();
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new UserListFragment(), Constants.ScreenNames.USER_LIST);
        adapter.addFragment(new FeedFragment(), Constants.ScreenNames.FEED);
        adapter.addFragment(profileFragment, Constants.ScreenNames.PROFILE);
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tab, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public void showMessage(String msg) {
        // showToast(msg);
        if (msg.equals(Constants.FETCH_USERS)) {
            showProgressDialog();
        }
        if (msg.equals(Constants.FETCH_USERS_COMPLETED)) {
            hideProgressDialog();
        }
        //showToast(msg);
    }

    @Override
    public void takeSelfi(View view) {
        registerForContextMenu(view);
        view.showContextMenu();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            profileFragment.setProfilePic(imageBitmap);
        }
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                profileFragment.setProfilePic(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

//    public void dispatchTakePictureIntent() {
//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
//        }
//    }
//
//    public void selectPhotoFromGallery() {
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent, "Select Picture"), Constants.PICK_IMAGE_REQUEST);
//    }
//
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.selfi_context_menu, menu);
//    }
//
//    public boolean onContextItemSelected(MenuItem item) {
//        //find out which menu item was pressed
//        switch (item.getItemId()) {
//            case R.id.option1:
//                doOptionOne();
//                return true;
//            case R.id.option2:
//                doOptionTwo();
//                return true;
//            default:
//                return false;
//        }
//    }
//
//    private void doOptionOne() {
//        selectPhotoFromGallery();
//    }
//
//    private void doOptionTwo() {
//        dispatchTakePictureIntent();
//    }
//

}
