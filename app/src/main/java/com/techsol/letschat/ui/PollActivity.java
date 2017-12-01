package com.techsol.letschat.ui;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.techsol.letschat.BaseActivity;
import com.techsol.letschat.R;
import com.techsol.letschat.ui.fragments.ActivityFragmentBridge;
import com.techsol.letschat.ui.fragments.PollFragment;
import com.techsol.letschat.ui.fragments.PollImageFragment;
import com.techsol.letschat.ui.fragments.QuestionDailoge;
import com.techsol.letschat.utils.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.techsol.letschat.utils.Constants.PICK_IMAGE_REQUEST;
import static com.techsol.letschat.utils.Constants.REQUEST_IMAGE_CAPTURE;

public class PollActivity extends BaseActivity implements ActivityFragmentBridge {
    PollImageFragment pollImageFragment;
    PollFragment profileFragment;
    int pageCurrentPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll);
        bindView();
    }

    private void bindView() {
        profileFragment = new PollFragment();
        pollImageFragment = new PollImageFragment();
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(profileFragment, Constants.ScreenNames.TEXT);
        adapter.addFragment(pollImageFragment, Constants.ScreenNames.IMAGE);
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pageCurrentPosition = tab.getPosition();
                Log.i("pageCurrentPosition", "pageCurrentPosition " + pageCurrentPosition);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            pollImageFragment.setImage(imageBitmap);
        }
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                pollImageFragment.setImage(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public void selectPhotoFromGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), Constants.PICK_IMAGE_REQUEST);
    }

    @Override
    public void showMessage(String msg) {
        if (msg.equals(Constants.CAM_OPTION)) {
            selectPhotoFromGallery();
        } else if (msg.equals(Constants.GAL_OPTION)) {
            dispatchTakePictureIntent();
        } else if (msg.equals(Constants.SHOW_DIALOGUE)) {
            showDialog();
        } else if (msg.equals(Constants.Q_CANCEL)) {
            closeDialog();
        } else {
            if (pageCurrentPosition == 0) {
                profileFragment.setText(msg);
            } else if (pageCurrentPosition == 1) {
                pollImageFragment.setText(msg);
            }
            closeDialog();

        }
    }

    @Override
    public void takeSelfi(View view) {
        registerForContextMenu(view);
        view.showContextMenu();
    }

}
