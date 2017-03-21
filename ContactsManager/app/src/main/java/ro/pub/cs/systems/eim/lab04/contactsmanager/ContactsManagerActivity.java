package ro.pub.cs.systems.eim.lab04.contactsmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class ContactsManagerActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_manager);

        Button hide_show_button = (Button) findViewById(R.id.hide_show_btn);
        hide_show_button.setOnClickListener(this);

    }

    public void onClick(View view) {
        if(view instanceof Button) {
            Button hide_show_button = (Button) view;
            LinearLayout additional_container = (LinearLayout) findViewById(R.id.additional_container);
            String btn_text = hide_show_button.getText().toString().toLowerCase();
            if(btn_text.contains("show")) {
                btn_text = "HIDE ADDITIONAL FIELDS";
                additional_container.setVisibility(LinearLayout.VISIBLE);
            } else if(btn_text.contains("hide")) {
                btn_text = "SHOW ADDITIONAL FIELDS";
                additional_container.setVisibility(LinearLayout.INVISIBLE);
            }
            hide_show_button.setText(btn_text);
        }
    }
}
