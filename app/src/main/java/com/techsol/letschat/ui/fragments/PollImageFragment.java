package com.techsol.letschat.ui.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.techsol.letschat.R;
import com.techsol.letschat.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PollImageFragment extends Fragment {

    @BindView(R.id.cam_id)
    ImageView camImage;

    @BindView(R.id.gal_id)
    ImageView galleryImage;

    @BindView(R.id.option_1)
    ImageView optionOne;

    @BindView(R.id.option_2)
    ImageView optionTwo;

    @BindView(R.id.option_3)
    ImageView optionThree;

    @BindView(R.id.option_4)
    ImageView optionFour;
    @BindView(R.id.q_id)
    TextView questionView;
    @BindView(R.id.done_id)
    Button createPollBtn;

    boolean optionOneFlag;
    boolean optionTwoFlag;
    boolean optionThreeFlag;
    boolean optionFourFlag;



    private ActivityFragmentBridge activityFragmentBridge;
    List<Bitmap> bitmaps = new ArrayList<>();

    public PollImageFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pollimagelayout, container, false);
        ButterKnife.bind(this, view);
        questionView.setPaintFlags(questionView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activityFragmentBridge = (ActivityFragmentBridge) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activityFragmentBridge = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }


    private void makeItVisible(TextView view, String text) {
        view.setText(text);
        view.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.cam_id)
    public void onCamClick()
    {
        activityFragmentBridge.showMessage(Constants.CAM_OPTION);
    }

    @OnClick(R.id.gal_id)
    public void onGalClick()
    {
        activityFragmentBridge.showMessage(Constants.GAL_OPTION);
    }

    @OnClick(R.id.custom_id)
    public void onCustomClick()
    {

    }
    @OnClick(R.id.option_1)
    public void optionOneClick()
    {
        optionOneFlag = true;
        activityFragmentBridge.takeSelfi(optionOne);
    }
    @OnClick(R.id.option_2)
    public void optionTwoClick()
    {
        optionTwoFlag = true;
        activityFragmentBridge.takeSelfi(optionTwo);
    }
    @OnClick(R.id.option_3)
    public void optionThreeClick()
    {
        optionThreeFlag = true;
        activityFragmentBridge.takeSelfi(optionThree);
    }
    @OnClick(R.id.option_4)
    public void optionFourClick()
    {
        optionFourFlag = true;
        activityFragmentBridge.takeSelfi(optionFour);
    }

    @OnClick(R.id.q_id)
    public void onQuestionClick()
    {
        activityFragmentBridge.showMessage(Constants.SHOW_DIALOGUE);
    }

    public void setImage(Bitmap bitmap) {

        if (optionOne.getTag() == null || optionOneFlag) {
            optionOne.setImageBitmap(bitmap);
            optionOne.setTag("tag");
            optionOneFlag = false;
          //  bitmaps.add(bitmap);
          //  collage.createCollageBitmaps(bitmaps);
            return;
        }

        if (optionTwo.getTag() == null || optionTwoFlag) {
            optionTwo.setImageBitmap(bitmap);
            optionTwo.setTag("tag");
            createPollVisibility(true);
            optionTwoFlag = false;
          //  bitmaps.add(bitmap);
            //collage.createCollageBitmaps(bitmaps);
            return;
        }

        if (optionThree.getTag() == null || optionThreeFlag) {
            optionThree.setImageBitmap(bitmap);
            optionThree.setTag("tag");
            optionThreeFlag = false;
          //  bitmaps.add(bitmap);
           // collage.createCollageBitmaps(bitmaps);
            return;
        }

        if (optionFour.getTag() == null || optionFourFlag) {
            optionFour.setImageBitmap(bitmap);
            optionFour.setTag("tag");
            optionFourFlag = false;
           // bitmaps.add(bitmap);
            //collage.createCollageBitmaps(bitmaps);
           // collage.setFixedCollage(false);
            return;
        }
    }

    public void setText(String message)
    {
        questionView.setText(message);
    }

    private void createPollVisibility(boolean flag)
    {
        if(flag) {
            createPollBtn.setVisibility(View.VISIBLE);
            return;
        }
        createPollBtn.setVisibility(View.INVISIBLE);
    }
}
