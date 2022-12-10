package teclag.c18131267.albumfotos;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.List;

public class ImageAdaptor extends BaseAdapter {

    private List<Integer> mThumbId;
    private Context mContext;

    public ImageAdaptor(List<Integer> mThumbId, Context mContext) {
        this.mThumbId = mThumbId;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mThumbId.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return mThumbId.get(i);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = (ImageView) convertView;

        if (imageView == null){
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(350,450));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        }
        imageView.setImageResource(mThumbId.get(position));

        return imageView;
    }

}
