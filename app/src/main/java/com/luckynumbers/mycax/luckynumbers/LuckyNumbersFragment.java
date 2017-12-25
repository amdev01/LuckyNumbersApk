package com.luckynumbers.mycax.luckynumbers;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hanks.htextview.base.AnimationListener;
import com.hanks.htextview.base.HTextView;

public class LuckyNumbersFragment extends Fragment implements
        View.OnClickListener {


    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("luckynumbers");
    }

    private EditText mFirstName;
    private EditText mLastName;
    private HTextView mOutputText;
    private ImageButton mButton_Calculate;
    private ImageButton mButton_Reset;
    private DataBHelper database;

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    private boolean isAlpha(String name) {
        return name.matches("[a-zA-Z]+");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_luckynumbers,
                container, false);
        mFirstName = (EditText) view.findViewById(R.id.firstName);
        mLastName = (EditText) view.findViewById(R.id.lastName);
        mOutputText = (HTextView) view.findViewById(R.id.output_text);
        mOutputText.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationEnd(HTextView hTextView) {
                //noop
            }
        });

        mButton_Calculate = (ImageButton) view.findViewById(R.id.imageButtonCalculate);
        mButton_Calculate.setOnClickListener(this);
        mButton_Reset = (ImageButton) view.findViewById(R.id.imageButtonReset);
        mButton_Reset.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.imageButtonCalculate) {
            if (isEmpty(mFirstName) && isEmpty(mLastName) || isEmpty(mFirstName) || isEmpty(mLastName)) {
                Snackbar.make(v, "Input fields cannot be empty", Snackbar.LENGTH_SHORT).show();

            } else if (isAlpha(mFirstName.getText().toString()) && isAlpha(mLastName.getText().toString())) {
                String result = Calculate(mFirstName.getText().toString(), mLastName.getText().toString());
                mOutputText.animateText(result);
                database = new DataBHelper(getActivity());
                database.saveToDB(mFirstName.getText().toString(), mLastName.getText().toString(), result);
                database.close();

            } else {
                Snackbar.make(v, "Input fields can only contain letters!", Snackbar.LENGTH_SHORT).show();
            }
        } else if (v.getId() == R.id.imageButtonReset) {
            mFirstName.getText().clear();
            mLastName.getText().clear();
            mOutputText.animateText("");
        }
    }

    public native String Calculate(String jFirstName, String jLastName);

}
