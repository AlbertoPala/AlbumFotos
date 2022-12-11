package teclag.c18131267.albumfotos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final int CODIGO_CAPTURA_FOTO = 123;
    private  Uri UriFOTO;
    private String ruta;

    ArrayList<Integer> imagenes = new ArrayList<>(Arrays.asList(
            R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,
            R.drawable.img5,R.drawable.img6,R.drawable.img7,R.drawable.img8,
            R.drawable.img9,R.drawable.img10,R.drawable.img11,R.drawable.img12
            ));

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = findViewById(R.id.miGridView);
        gridView.setAdapter(new ImageAdaptor(imagenes,this));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                int item_pos = imagenes.get(position);

                ShowDialogBox(item_pos);
            }
        });
    }
    public void ShowDialogBox(int item_pos){
        Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.custom_dialog_box);

        TextView image_name = dialog.findViewById(R.id.txt_Image_name);
        ImageView image = dialog.findViewById(R.id.img);
        Button btn_full = dialog.findViewById(R.id.btn_full);
        Button btn_cerrar = dialog.findViewById(R.id.btn_cerrar);

        String titulo = getResources().getResourceName(item_pos);

        int index = titulo.indexOf("/");
        String name = titulo.substring(index+1,titulo.length());
        image_name.setText(name);

        image.setImageResource(item_pos);
        btn_cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btn_full.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FullViewActivity.class);
                intent.putExtra("img_id", item_pos);
                startActivity(intent);
            }
        });
        dialog.show();
    }

    public void onClick(View view) {
        Snackbar.make(view, "Foto", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }


    public boolean onOptionsItemSelected(MenuItem item){

        int id = item.getItemId();

        if (id == R.id.id_AcercaDe){
            ruta = "android.resource://" + this.getPackageName() + "/" + R.raw.gansito;
        } else if (id == R.id.id_Navidad){
            ruta = "android.resource://" + this.getPackageName() + "/" + R.raw.navidad;
        }
        lanzarVideoActiviy();

        return super.onOptionsItemSelected(item);
    }

    private void  lanzarVideoActiviy () {
        Intent intent = new Intent ( this, VideoActivity.class ) ;
        intent.putExtra ( "rutaVideo", ruta );
        startActivity ( intent );
    }

    //Metodo
    public void btnCapturaSimpleClick ( View v )
    {
        // VAMOS A DARLE NOMBRE A LOS ARCHIVOS- QUE SE GUARDE CON LA FECHA DONDE LA SE TOMO LA FOTOGRAFIA
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyymmddHHmmss" );//(20221117843)
        String nombreFotografiafecha = simpleDateFormat.format( new Date() );
        String archivoFoto = "Foto" + nombreFotografiafecha + ".jpg";

        File fileFoto = new File( Environment.getExternalStorageDirectory().getAbsolutePath() +
                File.separator + "DCIM" +File.separator + archivoFoto );

        //UriFOTO = FileProvider.getUriForFile( this , BuildConfig.APPLICATION_ID + ".provider", fileFoto );
        UriFOTO = Uri.fromFile( fileFoto );

        //CREAMOS AL INTENT QUE LLAMARA A LA APP DE DE LA CAMARA
        Intent intent = new Intent( MediaStore.ACTION_IMAGE_CAPTURE );
        intent.putExtra( MediaStore.EXTRA_OUTPUT, UriFOTO );
    }
}