package activitytest.example.com.week_target;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Ctrump on 9/9/2016.
 */
public class targets extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.targets);
        Intent intent = getIntent();
        final String[] tgs = intent.getStringArrayExtra("targets");//用来存每一个target；定义成final类型是因为之后的函数中要使用tgs[]
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(targets.this, android.R.layout.simple_list_item_activated_1, tgs);//构建适配器对象[1]<-手动脚注
        ListView listView = (ListView) findViewById(R.id.targets);
        listView.setAdapter(adapter);
        //点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //点击某项item后弹出提示框：“是否已完成本周任务？”
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(targets.this);
                final View v = view;
                dialog.setTitle("提示");
                dialog.setMessage("是否已完成本周“" + tgs[position] + "”任务？");
                //若已完成本周任务则改变当前item背景颜色，表示已完成
                dialog.setPositiveButton("YES!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        v.setBackgroundColor(Color.parseColor("#F5F5DC"));
                        Toast.makeText(targets.this, "Congratulations!", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
            }
        });
    }

}