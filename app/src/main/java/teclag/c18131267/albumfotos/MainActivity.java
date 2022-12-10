package teclag.c18131267.albumfotos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ArrayList<Integer> imagenes = new ArrayList<>(Arrays.asList(
            R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,
            R.drawable.img5,R.drawable.img6,R.drawable.img7,R.drawable.img8,
            R.drawable.img9,R.drawable.img10,R.drawable.img11,R.drawable.img12
            ));

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
    
}