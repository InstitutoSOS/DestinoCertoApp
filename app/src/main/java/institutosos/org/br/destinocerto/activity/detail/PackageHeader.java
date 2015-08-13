package institutosos.org.br.destinocerto.activity.detail;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import institutosos.org.br.destinocerto.R;

public class PackageHeader extends Item {
    private final String name;

    public PackageHeader(String name) {
        this.name = name;
    }

    @Override
    public int getViewType() {
        return PackageCardAdapter.RowType.HEADER_ITEM.ordinal();
    }

    @Override
    public View getView(LayoutInflater inflater, View convertView, Context context) {
        View view;
        if (convertView == null) {
            view = inflater.inflate(R.layout.package_header, null);
        } else {
            view = convertView;
        }

        TextView text = (TextView) view.findViewById(R.id.separator);
        text.setText(name);

        return view;
    }
}
