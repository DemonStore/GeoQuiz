package com.example.demonstore.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class CheatActivity extends Activity {

    private static final String KEY_INDEX = "index";

    public static final String EXTRA_ANSWER_IS_TRUE = "com.example.demonstore.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN = "com.example.demonstore.geoquiz.answer_shown";

    private TextView mAnswerTextView;
    private Button mShowAnswerButton;

    private boolean mAnswerIsTrue;
    private boolean mAnswerIsShown = false;

    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        if (savedInstanceState != null) {
            mAnswerIsShown = savedInstanceState.getBoolean(KEY_INDEX, false);
        }
        setAnswerShownResult(mAnswerIsShown);

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mAnswerTextView = (TextView) findViewById(R.id.answer_text);

        mShowAnswerButton = (Button) findViewById(R.id.answer_button);
        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAnswerTextView.setText((mAnswerIsTrue) ? R.string.correct_toast : R.string.incorrect_toast);
                mAnswerIsShown = true;
                setAnswerShownResult(mAnswerIsShown);
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean(KEY_INDEX, mAnswerIsShown);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cheat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
