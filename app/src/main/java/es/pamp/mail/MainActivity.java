package es.pamp.mail;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private String destinatario;
    private String asunto;
    private String mensaje;
    private ConstraintLayout layout;
    private EditText mailT;
    private EditText mensajeT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = (ConstraintLayout)findViewById(R.id.layout);

        mailT = (EditText) findViewById(R.id.mailT);
        mensajeT = (EditText) findViewById(R.id.mensajeT);

        Button enviarBoton = (Button)findViewById(R.id.enviarBoton);
        enviarBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mailT.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+") && mailT.getText().toString().length() > 0)
                {
                    destinatario = mailT.getText().toString();
                    mensaje = mensajeT.getText().toString();
                    asunto = "EMAIL DE PRUEBA";

                    enviarMail(destinatario, asunto, mensaje);

                }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Introduzca un email válido", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }
    public void enviarMail(String destinatario, String asunto, String mensaje){

        //TODO enviar Mail
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));

        //Hace una captura de pantalla NO FUNCIONA!!!
        //layout.setDrawingCacheEnabled(true);
        //Bitmap im = layout.getDrawingCache();
        //layout.setDrawingCacheEnabled(false);

        //String path = MediaStore.Images.Media.insertImage(getContentResolver(), im, "titulo", null);
        //Uri screenshotUri = Uri.parse(path);

        emailIntent.putExtra(Intent.EXTRA_EMAIL, destinatario);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, asunto);
        emailIntent.putExtra(Intent.EXTRA_TEXT,mensaje);
        //emailIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
        emailIntent.setType("message/rfc822");

        startActivity(Intent.createChooser(emailIntent, "Email"));

    }

}
