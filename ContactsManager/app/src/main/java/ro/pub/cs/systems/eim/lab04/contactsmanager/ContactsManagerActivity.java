package ro.pub.cs.systems.eim.lab04.contactsmanager;

import android.content.ContentValues;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class ContactsManagerActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_manager);

        findViewById(R.id.hide_show_btn).setOnClickListener(this);
        findViewById(R.id.save_btn).setOnClickListener(this);
        findViewById(R.id.cancel_btn).setOnClickListener(this);

    }

    public void onClick(View view) {
        if(view instanceof Button) {
            Button btn = (Button) view;

            switch(btn.getId()) {
                case R.id.hide_show_btn:
                    onShowHideButtonClicked(btn);
                    break;

                case R.id.cancel_btn:
                    onCancelButtonClicked(btn);
                    break;

                case R.id.save_btn:
                    onSaveButtonClicked(btn);
                    break;
            }
        }
    }

    private void onShowHideButtonClicked(Button btn) {
        LinearLayout additional_container = (LinearLayout) findViewById(R.id.additional_container);
        String btn_text = btn.getText().toString().toLowerCase();
        if(btn_text.contains("show")) {
            btn_text = "HIDE ADDITIONAL FIELDS";
            additional_container.setVisibility(LinearLayout.VISIBLE);
        } else if(btn_text.contains("hide")) {
            btn_text = "SHOW ADDITIONAL FIELDS";
            additional_container.setVisibility(LinearLayout.GONE);
        }
        btn.setText(btn_text);
    }

    private void onCancelButtonClicked(Button btn) {
        finish();
    }

    private void onSaveButtonClicked(Button btn) {
        String name = ((EditText)findViewById(R.id.name_text)).getText().toString();
        String phone = ((EditText)findViewById(R.id.phone_text)).getText().toString();
        String email = ((EditText)findViewById(R.id.email_text)).getText().toString();
        String address = ((EditText)findViewById(R.id.address_text)).getText().toString();
        String jobTitle = ((EditText)findViewById(R.id.job_title_text)).getText().toString();
        String company = ((EditText)findViewById(R.id.company_text)).getText().toString();
        String website = ((EditText)findViewById(R.id.website_text)).getText().toString();
        String im = ((EditText)findViewById(R.id.im_text)).getText().toString();

        Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
        intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);

        if (!name.isEmpty()) {
            intent.putExtra(ContactsContract.Intents.Insert.NAME, name);
        }
        if (!phone.isEmpty()) {
            intent.putExtra(ContactsContract.Intents.Insert.PHONE, phone);
        }
        if (!email.isEmpty()) {
            intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email);
        }
        if (!address.isEmpty()) {
            intent.putExtra(ContactsContract.Intents.Insert.POSTAL, address);
        }
        if (!jobTitle.isEmpty()) {
            intent.putExtra(ContactsContract.Intents.Insert.JOB_TITLE, jobTitle);
        }
        if (!company.isEmpty()) {
            intent.putExtra(ContactsContract.Intents.Insert.COMPANY, company);
        }

        ArrayList<ContentValues> contactData = new ArrayList<ContentValues>();
        if (website != null) {
            ContentValues websiteRow = new ContentValues();
            websiteRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE);
            websiteRow.put(ContactsContract.CommonDataKinds.Website.URL, website);
            contactData.add(websiteRow);
        }
        if (im != null) {
            ContentValues imRow = new ContentValues();
            imRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE);
            imRow.put(ContactsContract.CommonDataKinds.Im.DATA, im);
            contactData.add(imRow);
        }
        intent.putParcelableArrayListExtra(ContactsContract.Intents.Insert.DATA, contactData);
        startActivity(intent);
    }
}
