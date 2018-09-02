package com.example.sivaram.sqlite;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper mydb;
    private EditText name,sub,mark;
    private Button btn,btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb=new DatabaseHelper(this);
        name=findViewById(R.id.editText);
        sub=findViewById(R.id.editText2);
        mark=findViewById(R.id.editText3);
        btn=findViewById(R.id.button);
        btn2=findViewById(R.id.button1);
        Add();
        view();
    }
    public void Add(){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String s1 = name.getText().toString();
                final String s2 = sub.getText().toString();
                final String s3 = mark.getText().toString();
                if (TextUtils.isEmpty(s1)) {
                    name.setError("Please enter name");
                    name.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(s2)) {
                    sub.setError("Please enter subject");
                    sub.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(s3)) {
                    mark.setError("Please enter mark");
                    mark.requestFocus();
                    return;
                }
              boolean isinserted= mydb.insert(name.getText().toString(),sub.getText().toString(), Integer.parseInt(mark.getText().toString()));
              //  Log.e("Error",sub.getText().toString());
              if(isinserted){
                  Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
              }
              else {
                  Toast.makeText(MainActivity.this,"Data Not Inserted",Toast.LENGTH_LONG).show();

              }
            }
        });
    }
    public void view(){
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res =mydb.getData();
                if(res.getCount()==0){
                    show("Error","Nothing Found");
                    return;
                }
                StringBuffer stringBuffer =new StringBuffer();
                while (res.moveToNext()){
                    stringBuffer.append("Id :"+res.getString(0)+"\n");
                    stringBuffer.append("NAME :"+res.getString(1)+"\n");
                    stringBuffer.append("SUBJECT :"+res.getString(2)+"\n");
                    stringBuffer.append("MARK :"+res.getString(3)+"\n");

                }
                show("Data",stringBuffer.toString());
            }
        });
    }
    public void show(String title,String Message){
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();


    }
}
