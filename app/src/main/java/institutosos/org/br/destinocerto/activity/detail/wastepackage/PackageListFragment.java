package institutosos.org.br.destinocerto.activity.detail.wastepackage;

import android.app.ListFragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import institutosos.org.br.destinocerto.R;
import institutosos.org.br.destinocerto.activity.detail.InfoItem;
import institutosos.org.br.destinocerto.activity.detail.Item;
import institutosos.org.br.destinocerto.activity.detail.InfoItemAdapter;
import institutosos.org.br.destinocerto.activity.detail.InfoItemHeader;
import institutosos.org.br.destinocerto.model.Location;
import institutosos.org.br.destinocerto.model.WastePackage;

public class PackageListFragment extends ListFragment {

    private List<Item> _items;
    private InfoItemAdapter _adapter;

    public void setPackage(WastePackage wastePackage) {
        Resources res = getResources();

        _items.clear();

        _items.add(new InfoItemHeader(res.getString(R.string.package_information)));
        _items.add(new InfoItem(res.getString(R.string.package_weight), wastePackage.getWeight() + " kg"));

        _items.add(new InfoItemHeader(res.getString(R.string.package_site)));
        _items.add(new InfoItem(res.getString(R.string.package_site_name), wastePackage.getCurrentLocation().getSite().getName()));
        _items.add(new InfoItem(res.getString(R.string.package_site_address), wastePackage.getCurrentLocation().getSite().getAddress(), InfoItem.TYPES.MAP));

        _items.add(new InfoItemHeader(res.getString(R.string.package_location_history)));

        // TODO use localized format
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        for (Location l : wastePackage.getLocationHistory()) {
            Log.d("fragment", l.getTimestamp().toString());
            _items.add(new InfoItem(format.format(l.getTimestamp()), l.getSite().getName()));
        }

        _adapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        _items = new ArrayList<>();
        _adapter = new InfoItemAdapter(getActivity(), _items);

        return inflater.inflate(R.layout.fragment_item_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getListView().setLongClickable(true);
        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                _items.get(position).longClick((PackageActivity) getActivity());
                return true;
            }
        });

        setListAdapter(_adapter);
    }
}
