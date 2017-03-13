package ru.jahom.simple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import ru.jahom.assetfont.AssetFontAlertDialogBuilder;
import ru.jahom.assetfont.AssetFontHelper;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        findViewById(R.id.button_alert).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_alert:
                    showAlert();
                break;
        }
    }

    private void showAlert() {
        new AssetFontAlertDialogBuilder(this)
                .setTypeFace(AssetFontHelper.get().getTypeface(this, R.string.font_great_vibes_regilar))
                .setMessage("This is test dialog")
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }

}
