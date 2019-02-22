package multinotepad.iit.com.mulinotepad.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import multinotepad.iit.com.mulinotepad.R;

public class EditActivity extends AppCompatActivity {

    private Toolbar toolBar;
    private TextView toolBarTitle;
    private ImageButton imgSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        toolBar = findViewById(R.id.toolbar);
        toolBarTitle = findViewById(R.id.toolbar_title);
        imgSave = findViewById(R.id.img_save);
        toolBarTitle.setText("Multi Notes");

        imgSave.setVisibility(View.VISIBLE);
        setSupportActionBar(toolBar);
    }
}
