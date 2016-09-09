package activitytest.example.com.week_target;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText edit;

    private Button saveTarget;

    private Button loadTarget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit = (EditText) findViewById(R.id.edit);
        saveTarget = (Button) findViewById(R.id.save_target);
        saveTarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputText = edit.getText().toString();
                save(inputText);
            }
        });
        loadTarget = (Button) findViewById(R.id.load_target);
        loadTarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] weekTarget = load();
                Intent intent = new Intent(MainActivity.this,targets.class);
                intent.putExtra("targets", weekTarget);
                startActivity(intent);
            }
        });
    }

    public void save(String inputText)
    {
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try
        {
            out = openFileOutput("data", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);
        }catch (IOException e)
        {
            e.printStackTrace();
        }finally
        {
            try
            {
                if(writer != null)
                {
                    writer.close();
                }
            }catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public String[] load()
    {
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        String[] ss = new String[64];//用来暂存每一个target
        int count = 0;//用来记录targets的行数
        try
        {
            in = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while((line = reader.readLine()) != null)
            {
                content.append(line);
                ss[count] = line;
                count++;
            }
        }catch(IOException e)
        {
            e.printStackTrace();
        }finally
        {
            if(reader != null)
            {
                try
                {
                    reader.close();
                }catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        String[] str = new String[count];
        for(int i = 0; i < count; i++)
            str[i] = ss[i];
        return str;
    }
}