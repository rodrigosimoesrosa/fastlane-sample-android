package br.com.mirabilis.gptestefast;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button btnOk;
    private EditText editName;
    private EditText editRG;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editRG = (EditText) findViewById(R.id.editRG);
        editName = (EditText) findViewById(R.id.editName);

        btnOk = (Button) findViewById(R.id.btnOk);
        btnOk.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnOk:
                doSomething();
                break;
        }
    }

    private void doSomething() {
        if(!editName.getText().toString().isEmpty() && !editRG.getText().toString().isEmpty()){
            dialog = ProgressDialog.show(MainActivity.this,"Processando","Aguarde ...");
            new SomethingTask(editName.getText().toString(), editRG.getText().toString()).execute();
        }
    }

    private class SomethingTask extends AsyncTask<Void,Void,String>{

        private String name;
        private String rg;

        public SomethingTask(String name, String rg) {
            this.rg = rg;
            this.name = name;
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Nome : " + name + " RG : " + rg;
        }

        @Override
        protected void onPostExecute(String s) {
            dialog.dismiss();

            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).setTitle("Resultado").setMessage(s).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    editRG.setText("");
                    editName.setText("");
                    editName.requestFocus();
                }
            }).create();

            alertDialog.show();
        }
    }
}
