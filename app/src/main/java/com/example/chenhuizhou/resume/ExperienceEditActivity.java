package com.example.chenhuizhou.resume;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.chenhuizhou.resume.model.Experience;
import com.example.chenhuizhou.resume.util.DateUtils;

import java.util.Arrays;

/**
 * Created by chenhuizhou on 8/27/16.
 */
public class ExperienceEditActivity extends AppCompatActivity {

    public static final String KEY_EXPERIENCE = "experience";

    private Experience experience;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience_edit);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        experience = getIntent().getParcelableExtra(KEY_EXPERIENCE);
        if (experience != null) {
            setupUI();
        }

        setTitle(experience == null ? "New Experience" : "Edit Experience");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.ic_save:
                saveAndExit();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupUI() {
        ((EditText) findViewById(R.id.experience_edit_company))
                .setText(experience.company);
        ((EditText) findViewById(R.id.experience_edit_title))
                .setText(experience.title);
        ((EditText) findViewById(R.id.experience_edit_start_date))
                .setText(DateUtils.dateToString(experience.startDate));
        ((EditText) findViewById(R.id.experience_edit_end_date))
                .setText(DateUtils.dateToString(experience.endDate));
        ((EditText) findViewById(R.id.experience_edit_details))
                .setText(TextUtils.join("\n", experience.details));
    }

    private void saveAndExit() {
        if (experience == null) {
            experience = new Experience();
        }
        experience.company = ((EditText) findViewById(R.id.experience_edit_company)).getText().toString();
        experience.title = ((EditText) findViewById(R.id.experience_edit_title)).getText().toString();
        experience.startDate = DateUtils.stringToDate(((EditText) findViewById(R.id.experience_edit_start_date)).getText().toString());
        experience.endDate = DateUtils.stringToDate(((EditText) findViewById(R.id.experience_edit_end_date)).getText().toString());
        experience.details = Arrays.asList(TextUtils.split(((EditText) findViewById(R.id.experience_edit_details)).getText().toString(), "\n"));

        Intent resultIntent = new Intent();
        resultIntent.putExtra(KEY_EXPERIENCE, experience);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}

