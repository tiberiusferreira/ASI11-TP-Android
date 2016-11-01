package com.excilys.formation.battleships;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

import com.excilys.formation.battleships.android.ui.BattleShipsApplication;

import battleships.formation.excilys.com.battleships.R;

public class PlayerNameActivity extends AppCompatActivity {
    EditText name;
    String final_name;
    private TextView.OnEditorActionListener mEditorListener = new CustomTVListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.NameField);
        name.setOnEditorActionListener(mEditorListener);
    }


    private class CustomTVListener implements TextView.OnEditorActionListener {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                final_name = v.getText().toString();
                BattleShipsApplication.getGame().init(final_name);

                return true;
            }
        }
}



