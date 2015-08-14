package institutosos.org.br.destinocerto.activity.detail.wastepackage;

import android.app.ListFragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

import institutosos.org.br.destinocerto.R;
import institutosos.org.br.destinocerto.activity.detail.Item;
import institutosos.org.br.destinocerto.model.Location;
import institutosos.org.br.destinocerto.model.WastePackage;

public class PackageListFragment extends ListFragment {

    private List<Item> _items;
    private PackageCardAdapter _adapter;

    public void setPackage(WastePackage wastePackage) {
        Resources res =getResources();

        _items.add(new PackageHeader(res.getString(R.string.package_information)));
        _items.add(new PackageCard(res.getString(R.string.package_barcode), wastePackage.getBarcode()));
        _items.add(new PackageCard(res.getString(R.string.package_weight), wastePackage.getWeight() + " kg"));

        _items.add(new PackageHeader(res.getString(R.string.package_site)));
        _items.add(new PackageCard(res.getString(R.string.package_site_name), wastePackage.getCurrentLocation().getSite().getName()));
        _items.add(new PackageCard(res.getString(R.string.package_site_address), wastePackage.getCurrentLocation().getSite().getAddress(), PackageCard.TYPES.MAP));

        _items.add(new PackageHeader(res.getString(R.string.package_location_history)));

        for(Location l : wastePackage.getLocationHistory()){
            _items.add(new PackageCard(l.getTimestamp(), l.getSite().getName()));
        }

        _adapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        _items = new ArrayList<>();
        _adapter = new PackageCardAdapter(getActivity(), _items);

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
