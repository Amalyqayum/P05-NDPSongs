package sg.edu.rp.c346.p05_ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowSong extends AppCompatActivity {
    ArrayList<Song> al;
    ArrayAdapter aa;
    ListView lv;
    Button btnAllStar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_song);

        btnAllStar = findViewById(R.id.btnAllStar);

        lv = findViewById(R.id.lv);
        al = new ArrayList<Song>();
        DBHelper db = new DBHelper(ShowSong.this);
        al = db.getAllNotes();
        aa = new CustomAdapter(this, R.layout.row, al);
        lv.setAdapter(aa);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Song song = al.get(position);
                Intent i = new Intent(ShowSong.this,
                        ModifySong.class);
                i.putExtra("song", song);
                startActivityForResult(i, 9);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 9) {
            DBHelper db = new DBHelper(ShowSong.this);
            al = new ArrayList<Song>();
            al = db.getAllNotes();
            aa = new CustomAdapter(this, R.layout.row, al);
            lv.setAdapter(aa);
        }
    }
}
