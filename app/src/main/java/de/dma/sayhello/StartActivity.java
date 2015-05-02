package de.dma.sayhello;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class StartActivity extends Activity implements TextToSpeech.OnInitListener {

    private TextToSpeech tts;
    private String text;
    private boolean language = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onButtonClick(View v) {
        TextView textView = (TextView) findViewById(R.id.textView);
        Button button = (Button) findViewById(R.id.button);

        if (textView.getText() == getString(R.string.hello_world_english)) {
            textView.setText(getString(R.string.hello_world_german));
            button.setText(getString(R.string.button_german));
        }
        else {
            textView.setText(getString(R.string.hello_world_english));
            button.setText(getString(R.string.button_english));
        }
    }

    public void onReadButtonClick(View v) {
        EditText editText = (EditText) findViewById(R.id.editText);

        if (editText.getText().length() > 0)
            text = editText.getText().toString();
        else {
            Context context = getApplicationContext();
            CharSequence text = "Please enter a text to be read first!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        tts = new TextToSpeech(this, this);
    }

    public void onRadioClick(View v) {
        if (v.getId() == R.id.radioButton)
            language = true;
        else
            language = false;
    }

    @Override
    public void onInit(int status) {
        if (language)
            tts.setLanguage(Locale.GERMAN);
        else
            tts.setLanguage(Locale.ENGLISH);

        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "Test");
    }
}
