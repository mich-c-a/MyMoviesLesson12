package sg.edu.rp.c346.id21025446.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class listMovies extends AppCompatActivity {

    Button btnlv;
    ListView lvMovies;
    ArrayList<Movie> al;
    ArrayList<Movie> alNotPG13;
    CustomAdapter aa;
    public boolean filterOn = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_movies);

        lvMovies = findViewById(R.id.lvMovies);
        btnlv = findViewById(R.id.btnlv);

        al = new ArrayList<Movie>();
        alNotPG13 = new ArrayList<Movie>();

        aa = new CustomAdapter(this,R.layout.row,al);
        lvMovies.setAdapter(aa);

        DBHelper dbh = new DBHelper(listMovies.this);
        al.addAll(dbh.getAllMovies());


        lvMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Movie data = al.get(i);
                Intent intent = new Intent(listMovies.this, editMovie.class);
                intent.putExtra("data", data);
                startActivity(intent);

            }
        });

        btnlv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filterOn = !filterOn;
                if (filterOn) {
                    for (int i = 0; i < al.size(); i++) {
                        if (!al.get(i).getRating().equals("PG13")  ) {
                            alNotPG13.add(al.get(i));
                        }
                    }
                    al.removeAll(alNotPG13);
                }
                else {
                    al.clear();
                    al.addAll(dbh.getAllMovies());

                }
                aa.notifyDataSetChanged();
            }

        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        DBHelper dbh = new DBHelper(listMovies.this);
        al.clear();
        al.addAll(dbh.getAllMovies());
        aa.notifyDataSetChanged();
    }
}