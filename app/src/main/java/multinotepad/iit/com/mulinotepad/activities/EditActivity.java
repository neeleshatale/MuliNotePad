package multinotepad.iit.com.mulinotepad.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import multinotepad.iit.com.mulinotepad.R;
import multinotepad.iit.com.mulinotepad.models.Note;
import multinotepad.iit.com.mulinotepad.utility.CommonFunction;
import multinotepad.iit.com.mulinotepad.utility.OnClickListener;

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
    public void onBackPressed() {
        if (!etTitle.getText().toString().trim().isEmpty()) {
            showAlert();
        } else
            finish();
    }

    private void showAlert() {
        String noteTitle = "'" + etTitle.getText().toString().trim() + "'";
        CommonFunction.getInstance().showAlertDialog(EditActivity.this, "Your note is not saved! Save note " + noteTitle, "YES", "NO", new OnClickListener() {
            @Override
            public void OnPositiveButtonClick() {
                saveNote();
            }

            @Override
            public void OnNegativeButtonClick() {
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.img_save) {
            if (etTitle.getText() != null && etTitle.getText().toString().trim().isEmpty()) {
                Toast.makeText(EditActivity.this, "Un-titled activity was not saved.", Toast.LENGTH_LONG).show();
                finish();
            } else {
                saveNote();
            }
        }
    }

    private void saveNote() {
        //Sun, Feb 10, 12:41 AM
        SimpleDateFormat sdf = new SimpleDateFormat("E,MMM d, hh:mm a");
        String lastSavedDate = sdf.format(new Date());
        Note note = new Note(etTitle.getText().toString().trim(), lastSavedDate, etNote.getText().toString().trim());
        Intent returnIntent = new Intent();
        returnIntent.putExtra("NEW_NOTE", note);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}
