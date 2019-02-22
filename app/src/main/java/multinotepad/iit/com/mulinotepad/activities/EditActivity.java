package multinotepad.iit.com.mulinotepad.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import multinotepad.iit.com.mulinotepad.R;

public class EditActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolBar;
    private TextView toolBarTitle;
    private ImageButton imgSave;
    private EditText etNote;
    private EditText etTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        toolBar = findViewById(R.id.toolbar);
        toolBarTitle = findViewById(R.id.toolbar_title);
        imgSave = findViewById(R.id.img_save);
        etTitle = findViewById(R.id.etTitle);
        etNote = findViewById(R.id.etNote);

        toolBarTitle.setText("Multi Notes");

        imgSave.setOnClickListener(this);

        imgSave.setVisibility(View.VISIBLE);
        setSupportActionBar(toolBar);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.img_save) {
            Toast.makeText(EditActivity.this, "Coming Soon..", Toast.LENGTH_LONG).show();
        }
    }
}
