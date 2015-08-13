package institutosos.org.br.destinocerto.activity.detail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

public class PackageCardAdapter extends ArrayAdapter<Item> {
    private LayoutInflater _inflater;
    private Context _context;

    public enum RowType {
        HEADER_ITEM, LIST_ITEM
    }

    public PackageCardAdapter(Context context, List<Item> items) {
        super(context, 0, items);
        _inflater = LayoutInflater.from(context);
        _context = context;
    }

    @Override
    public int getViewTypeCount() {
        return RowType.values().length;

    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getViewType();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getItem(position).getView(_inflater, convertView, _context);
    }
}
