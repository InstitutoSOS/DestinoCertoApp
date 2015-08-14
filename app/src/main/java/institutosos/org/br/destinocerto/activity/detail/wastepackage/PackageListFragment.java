package institutosos.org.br.destinocerto.activity.detail.wastepackage;

import android.app.ListFragment;
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
        _items.add(new PackageHeader("Package information"));
        _items.add(new PackageCard("Barcode", wastePackage.getBarcode()));
        _items.add(new PackageCard("Weight", wastePackage.getWeight() + " kg"));

        _items.add(new PackageHeader("Site"));
        _items.add(new PackageCard("Name", wastePackage.getCurrentLocation().getSite().getName()));
        _items.add(new PackageCard("Address", wastePackage.getCurrentLocation().getSite().getAddress(), PackageCard.TYPES.MAP));

        _items.add(new PackageHeader("Location History"));

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
