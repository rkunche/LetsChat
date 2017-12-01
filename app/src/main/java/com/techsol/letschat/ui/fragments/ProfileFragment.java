package com.techsol.letschat.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.techsol.letschat.R;
import com.techsol.letschat.ui.PollActivity;
import com.techsol.letschat.ui.TabActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileFragment extends Fragment {

    @BindView(R.id.profile_img_id)
    ImageView imageView;

    @BindView(R.id.name_text_id)
    TextView nameView;

    @BindView(R.id.create_poll_id)
    TextView pollView;

    ActivityFragmentBridge activityFragmentBridge;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_profile, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activityFragmentBridge = (TabActivity)getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activityFragmentBridge = null;
    }

    @OnClick(R.id.profile_img_id)
    public void onProfileClick()
    {
        activityFragmentBridge.takeSelfi(imageView);
    }

    @OnClick(R.id.profile_img_id)
    public void nameChangeClick()
    {

    }

    @OnClick(R.id.create_poll_id)
    public void createPoll()
    {
        Intent intent = new Intent(getActivity(), PollActivity.class);
        startActivity(intent);
    }

    public void setProfilePic(Bitmap imageBitmap)
    {
        imageView.setImageBitmap(imageBitmap);
    }

}
