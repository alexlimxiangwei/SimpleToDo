package sg.edu.rp.c346.simpletodo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText et;
    Button btnAdd;
    Button btnDel;
    Button btnClear;
    ListView lv;
    Spinner spinner;

    ArrayList<String> alTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.etTask);
        btnAdd = findViewById(R.id.btnAdd);
        btnDel = findViewById(R.id.btnDelete);
        btnClear = findViewById(R.id.btnClear);
        lv = findViewById(R.id.lvTasks);
        spinner = findViewById(R.id.spinner);

        btnDel.setEnabled(false);

        alTasks = new ArrayList<>();

        final ArrayAdapter<String> aaTasks = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,alTasks);
        lv.setAdapter(aaTasks);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alTasks.add(et.getText().toString());
                aaTasks.notifyDataSetChanged();
                et.setText("");
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alTasks.clear();
                et.setText("");
                aaTasks.notifyDataSetChanged();
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(alTasks.isEmpty()){
                    Toast.makeText(MainActivity.this, "You don't have any tasks to delete", Toast.LENGTH_SHORT).show();
                }
                else {
                    try {
                        int pos = Integer.parseInt(et.getText().toString());
                        if (alTasks.size() > pos) {
                            alTasks.remove(pos);
                            aaTasks.notifyDataSetChanged();
                            et.setText("");
                        } else {
                            Toast.makeText(MainActivity.this, "Item index does not exist", Toast.LENGTH_SHORT).show();
                        }
                    } catch (NumberFormatException e) {
                        Toast.makeText(MainActivity.this, "Please enter an integer", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        btnAdd.setEnabled(true);
                        btnDel.setEnabled(false);
                        et.setText("");
                        et.setHint("Type in a new task here");
                        break;
                    case 1:
                        btnAdd.setEnabled(false);
                        btnDel.setEnabled(true);
                        et.setText("");
                        et.setHint("Type in the index of the task to be removed");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}
