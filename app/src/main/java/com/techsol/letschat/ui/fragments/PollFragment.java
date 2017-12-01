package com.techsol.letschat.ui.fragments;

import android.content.Context;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.techsol.letschat.R;
import com.techsol.letschat.utils.Constants;
import com.techsol.letschat.utils.Validation;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PollFragment extends Fragment {

    @BindView(R.id.done_id)
    Button doneView;
    @BindView(R.id.more_id)
    TextView moreView;

    @BindView(R.id.option_1)
    TextView optionOne;
    @BindView(R.id.option_2)
    TextView optionTwo;
    @BindView(R.id.option_3)
    TextView optionThree;
    @BindView(R.id.option_4)
    TextView optionFour;

    @BindView(R.id.text_enter_id)
    EditText editText;
    @BindView(R.id.edit_text_layout_id)
    RelativeLayout editLayView;
    @BindView(R.id.q_id)
    TextView questionId;


    private ActivityFragmentBridge mListener;

    public PollFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_poll, container, false);
        ButterKnife.bind(this, view);
        questionId.setPaintFlags(questionId.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (ActivityFragmentBridge)getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    @OnClick(R.id.done_id)
    public void onDoneClick() {

    }

    @OnClick(R.id.more_id)
    public void onMoreClick() {
        //Validation.showToast(getActivity(), "Enter Valid String");
        if (Validation.isEmpty(editText)) {
            Validation.showToast(getActivity(), "Enter Valid String");
            return;
        }
        setPollText(editText.getText().toString());
    }

    private void setPollText(String text) {
        if (Validation.isEmpty(optionOne)) {
            makeItVisible(optionOne, "1) "+text);
            return;
        }
        if (Validation.isEmpty(optionTwo)) {
            makeItVisible(optionTwo, "2) "+text);
            return;
        }
        doneView.setVisibility(View.VISIBLE);
        if (Validation.isEmpty(optionThree)) {
            makeItVisible(optionThree, "3) "+text);
            return;
        }
        if (Validation.isEmpty(optionFour)) {
            makeItVisible(optionFour, "4) "+text);
            editLayView.setVisibility(View.GONE);
            return;
        }
    }

    private void makeItVisible(TextView view, String text) {
        view.setText(text);
        view.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.q_id)
    public void onTextQClick()
    {
        mListener.showMessage(Constants.SHOW_DIALOGUE);
    }
    public void setText(String message)
    {
        questionId.setText(message);
    }
}
