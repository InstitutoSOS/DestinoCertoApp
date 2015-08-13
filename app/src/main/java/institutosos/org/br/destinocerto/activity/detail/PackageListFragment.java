package institutosos.org.br.destinocerto.activity.detail;

import android.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

import institutosos.org.br.destinocerto.model.WastePackage;

public class PackageListFragment extends ListFragment {

    private List<Item> _items;

    public PackageListFragment() {
    }

    public void setPackage(WastePackage wastePackage) {
        _items.add(new PackageHeader("Package information"));
        _items.add(new PackageCard("Barcode", wastePackage.getBarcode()));
        _items.add(new PackageCard("Weight", wastePackage.getWeight() + ""));

        _items.add(new PackageHeader("Site"));
        _items.add(new PackageCard("Name", wastePackage.getSite().getName()));
        _items.add(new PackageCard("Address", wastePackage.getSite().getAddress(), PackageCard.TYPES.MAP));
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      //  getListView().setLongClickable(true);
      //  getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
      //      @Override
      //      public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
      //          //_items.get(position).longClick(PackageActivity.this);
      //          return true;
      //      }
      //  });

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        _items = new ArrayList<>();
        PackageCardAdapter adapter = new PackageCardAdapter(getActivity(), _items);
        setListAdapter(adapter);

        super.onActivityCreated(savedInstanceState);
    }
}
