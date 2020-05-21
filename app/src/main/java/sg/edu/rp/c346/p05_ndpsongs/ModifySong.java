package sg.edu.rp.c346.p05_ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ModifySong extends AppCompatActivity {

    Song song;
    EditText editId, editTitle, editSingers, editYear;
    Button btnUpdate, btnDelete, btnCancel;
    RadioGroup rg1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_song);
        editId = findViewById(R.id.editId);
        editTitle = findViewById(R.id.editTitle);
        editSingers = findViewById(R.id.editSingers);
        editYear = findViewById(R.id.editYear);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);
        rg1 = findViewById(R.id.rg1);

        Intent i = getIntent();
        song = (Song) i.getSerializableExtra("song");

        editId.setText(song.getId() + "");
        editYear.setText(song.getYear() + "");
        editSingers.setText(song.getSingers());
        editTitle.setText(song.getTitle());


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int checking = rg1.getCheckedRadioButtonId();
                RadioButton rbSelected = findViewById(checking);
                int stars = Integer.parseInt(rbSelected.getText().toString());
                DBHelper dbh = new DBHelper(ModifySong.this);
                song.setYear(Integer.parseInt(editYear.getText().toString()));
                song.setSingers(editSingers.getText().toString());
                song.setTitle(editTitle.getText().toString());
                song.setStars(stars);

                dbh.updateSong(song);
                dbh.close();
                Intent i = new Intent();
                setResult(RESULT_OK, i);
                finish();
            }
        });

    }
}
