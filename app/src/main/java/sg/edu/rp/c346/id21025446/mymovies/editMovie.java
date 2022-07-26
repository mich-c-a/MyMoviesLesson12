package sg.edu.rp.c346.id21025446.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class editMovie extends AppCompatActivity {

    EditText etTitle;
    EditText etGenre;
    EditText etYear;
    Spinner spnRate;
    Button btnUpdate, btnDelete, btnCancel;
    Movie data;
    String spnSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie);
        etTitle = findViewById(R.id.etEdtTitle);
        etGenre = findViewById(R.id.etEdtGenre);
        etYear = findViewById(R.id.etEdtYear);
        spnRate = findViewById(R.id.spnEdtRate);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        Intent i = getIntent();
        data = (Movie) i.getSerializableExtra("data");

        etTitle.setText(data.getTitle());
        etGenre.setText(data.getGenre());
        etYear.setText(String.valueOf(data.getYear()));

        for (int x = 0; x <  spnRate.getChildCount(); x++){
            Spinner selectedBtn = (Spinner) spnRate.getChildAt(x);
            if(selectedBtn.toString() == data.getRating()){
                spnRate.setSelection(selectedBtn.getId());
            }
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(editMovie.this);
                data.setTitle(etTitle.getText().toString());
                data.setGenre(etGenre.getText().toString());
                data.setYear(Integer.parseInt(etYear.getText().toString()));
                spnSelected = spnRate.getSelectedItem().toString();
                data.setRating(spnSelected);
                dbh.updateMovie(data);
                dbh.close();
                Intent intent = new Intent(editMovie.this, listMovies.class);
                startActivity(intent);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(editMovie.this);
                dbh.deleteMovie(data.getId());
                Intent intent = new Intent(editMovie.this, listMovies.class);
                startActivity(intent);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(editMovie.this, listMovies.class);
                startActivity(intent);
            }
        });
    }
}