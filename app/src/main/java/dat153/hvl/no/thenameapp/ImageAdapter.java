package dat153.hvl.no.thenameapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;


/**
 *
 * @see <a href="https://developer.android.com/guide/topics/ui/layout/gridview.html">developer.android.com/.../</a>
 */
class ImageAdapter extends BaseAdapter {
    private int image_size = 250;
    private int padding_size = 8;

    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null; //should return the item
    }

    public long getItemId(int position) {
        return 0; //should return the row id of the item
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(image_size, image_size));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(padding_size, padding_size, padding_size, padding_size);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.rabbit1, R.drawable.rabbit2,
            R.drawable.rabbit3, R.drawable.rabbit4,
            R.drawable.rabbit5, R.drawable.rabbit6,
            R.drawable.rabbit7, R.drawable.rabbit8,
            R.drawable.rabbit9, R.drawable.rabbit10,
            R.drawable.rabbit11, R.drawable.rabbit12,
            R.drawable.rabbit13
    };
}