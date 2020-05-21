package sg.edu.rp.c346.p05_ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etTitle, etName, etYear;
    RadioGroup rg;
    Button btnInsert, btnShow;
    ArrayList<Song> al;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = findViewById(R.id.etTitle);
        etName = findViewById(R.id.etName);
        etYear = findViewById(R.id.etYear);
        rg = findViewById(R.id.rg);
        btnInsert = findViewById(R.id.btnInsert);
        btnShow = findViewById(R.id.btnShow);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int check = rg.getCheckedRadioButtonId();
                RadioButton rbSelected = findViewById(check);
                String data = etTitle.getText().toString();
                String data1 = etName.getText().toString();
                int data2 = Integer.parseInt(etYear.getText().toString());
                int data3 = Integer.parseInt(rbSelected.getText().toString());
                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_id = dbh.insertSong(data, data1, data2, data3);
                dbh.close();

                if (inserted_id != -1) {
                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this,
                        ShowSong.class);
                startActivity(i);
            }
        });
    }

}

