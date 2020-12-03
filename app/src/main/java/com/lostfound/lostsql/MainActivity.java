package com.lostfound.lostsql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity<CLasses, classes> extends AppCompatActivity {


    private TextView textView;
    private static String ip="192.168.0.18";
    private static String port="1433";
    private static String Classes="net.sourceforge.jtds.jdbc.Driver";
    private static String database="lostDatabase";
    private static String username="lost";
    private static String password="lost";
    private static String url= "jdbc:jtds:sqlserver://"+ip+":"+port+"/"+database;

    private Connection connection= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.textView);

        StrictMode.ThreadPolicy policy= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            Class.forName(Classes);
            try {
                connection= DriverManager.getConnection(url, username, password);
                textView.setText("The Results are");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                textView.setText("Could not fetch results :(");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            textView.setText("Please try again");
        }



    }

    public void sqlList(View view){
        if(connection!=null){

            Statement statement= null;
            try {
                statement = connection.createStatement();
                ResultSet resultSet= statement.executeQuery("select * from lost_table");
                //iterate through the result set
                while(resultSet.next()){
                    textView.setText(resultSet.getString(4));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();

            }

            else{
                textView.setText("Connection is null");
            }
        }

    }
}