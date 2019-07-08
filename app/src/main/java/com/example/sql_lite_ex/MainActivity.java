package com.example.sql_lite_ex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DB_sqlite db = new DB_sqlite(this);

    EditText name,email,id;
    ListView lst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.et_name);
        email=findViewById(R.id.etemail);
        lst = findViewById(R.id.listview);
        id = findViewById(R.id.et_id);

        Show_Data();
    }

    public void btn_add_data(View view) {

        String Name = name.getText().toString();
        String Email = email.getText().toString();
        Boolean result = db.Insert_Data(Name, Email);

        if (result == true) {

        Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
            name.setText("");
            email.setText("");
            Show_Data();
        }
    else
        {
            Toast.makeText(this, "Opees!!!", Toast.LENGTH_SHORT).show();

        }
    }


    public void Show_Data(){
        ArrayList<String> listdata = db.getallrecord();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listdata);
        lst.setAdapter(arrayAdapter);

    }

    public void btn_update(View view) {

        String Name = name.getText().toString();
        String Email = email.getText().toString();
        String Id = id.getText().toString();
        Boolean result = db.updatedata(Id,Name, Email);

        if (result == true) {

            Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
            name.setText("");
            email.setText("");
            id.setText("");
            Show_Data();
        }
        else
        {
            Toast.makeText(this, "Opees!!!", Toast.LENGTH_SHORT).show();

        }

    }

    public void btn_delete(View view) {

        String Id = id.getText().toString();
        Integer result = db.Delete(Id);
        if(result > 0){

            Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
        Show_Data();

        }
    else {
            Toast.makeText(this, "Opees!!!", Toast.LENGTH_SHORT).show();
        }
    }
}
