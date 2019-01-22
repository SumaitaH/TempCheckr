package edu.cuny.qc.cs.tempcheckr.activities;

import android.app.Activity;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.HttpResponse;
import com.testfairy.TestFairy;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import edu.cuny.qc.cs.tempcheckr.R;
import edu.cuny.qc.cs.tempcheckr.validateInput;
import android.widget.TextView.OnEditorActionListener;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Button webViewButton;
    private String[] CITIES;
    String webURL = new String();
    private WebView webView;
    //static AutoCompleteTextView data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TestFairy.begin(this, "e5d100c4be41c08c6dfefaeb6cf210fd3033e9c3");

        webView = (WebView) findViewById(R.id.wb);
        webView.setWebViewClient(new WebViewClient());
        //webView.loadUrl("http://10.0.2.2:8080/Servlet_SumaitaH");

        button = (Button) findViewById(R.id.button);
        //data = (AutoCompleteTextView) findViewById(R.id.citySearch);
        webViewButton = (Button) findViewById(R.id.webview_button);
        webViewButton.setOnClickListener(new View.OnClickListener() {
            //final CharSequence inputTextChar = textView.getText();
            //final String inputTextString = inputTextChar.toString();

            @Override
            public void onClick(View view) {
                //sends data to localhost
                openActivity3();

            }
        });



        Resources res = getResources();
        CITIES = res.getStringArray(R.array.list_of_cities);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, CITIES);
        final AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.citySearch);
        textView.setAdapter(adapter);


////        //EditText editText = (EditText) findViewById(R.id.citySearch);
        textView.setOnEditorActionListener(new OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_DONE) {
                    validateAndOpenActivity(textView);
                    handled = true;
                }

                return handled;
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            //final CharSequence inputTextChar = textView.getText();
            //final String inputTextString = inputTextChar.toString();

            @Override
            public void onClick(View view) {

                validateAndOpenActivity(textView);

            }
        });

        textView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });


    }
    public void validateAndOpenActivity(AutoCompleteTextView textViewer){

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-dd-mm");
        String strDate = mdformat.format(calendar.getTime());

        if(!validateInput.hasText(textViewer)){
            Toast.makeText(getBaseContext(), "Please enter a location" , Toast.LENGTH_SHORT ).show();
        }
        else{
            if(Character.isLetter(textViewer.getText().charAt(0))){
                if(validateInput.isCity(textViewer, true)){
                    String loc = textViewer.getText().toString();
                    setStringURL(loc);
                    webView.loadUrl("http://10.0.2.2:8080/Servlet_SumaitaH/FileUploadServlet?date=" + "2018-12-21" + "&location=" + loc);
                    openActivity2();
                }
            }
            else if(Character.isDigit(textViewer.getText().charAt(0)) || textViewer.length()==5){
                if(validateInput.isZipCode(textViewer, true)){
                    String loc = textViewer.getText().toString();
                    webView.loadUrl("http://10.0.2.2:8080/Servlet_SumaitaH/FileUploadServlet?date=" + "2018-12-21" + "&location=" + loc);
                    setStringURL(loc);
                    openActivity2();
                }
            }

        }
    }

    public void setStringURL(String location){
        webURL = "https://weather.cit.api.here.com/weather/1.0/report.json?product=forecast_7days_simple&name=" + location + "&app_id=83joEwu57v5M6pSLpP2E&app_code=vEsuM73ubIJT3KDfXE9_bQ";
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void openActivity2(){
        Intent intent = new Intent(this, Activity2.class);
        intent.putExtra("URL",webURL);
        startActivity(intent);
    }
    public void openActivity3(){
        Intent intent = new Intent(this, Activity3.class);
        startActivity(intent);
    }
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
