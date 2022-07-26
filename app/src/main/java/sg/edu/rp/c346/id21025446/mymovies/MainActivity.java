package sg.edu.rp.c346.id21025446.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etTitle;
    EditText etGenre;
    EditText etYear;
    Button btnInsert;
    Button btnSL;
    Spinner spnRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = findViewById(R.id.etTitle);
        etGenre = findViewById(R.id.etGenre);
        etYear = findViewById(R.id.etYear);
        btnInsert = findViewById(R.id.btninsrt);
        btnSL = findViewById(R.id.btnSL);
        spnRating = findViewById(R.id.spinnerRating);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = etTitle.getText().toString();
                String genre = etGenre.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());
                String rating = spnRating.getSelectedItem().toString();

                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_id = dbh.insertMovie(title, genre, year, rating);

                if (inserted_id != -1){
                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(MainActivity.this, "Insert unsuccessful",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(MainActivity.this);
                Intent intent = new Intent(MainActivity.this, listMovies.class);
                startActivity(intent);
            }
        });
    }
}