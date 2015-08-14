package institutosos.org.br.destinocerto.activity.detail.wastepackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import institutosos.org.br.destinocerto.R;
import institutosos.org.br.destinocerto.activity.detail.Item;

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
