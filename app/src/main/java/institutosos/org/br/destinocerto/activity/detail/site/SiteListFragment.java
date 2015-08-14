package institutosos.org.br.destinocerto.activity.detail.site;

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
import institutosos.org.br.destinocerto.activity.detail.wastepackage.PackageActivity;
import institutosos.org.br.destinocerto.activity.detail.InfoItem;
import institutosos.org.br.destinocerto.activity.detail.InfoItemAdapter;
import institutosos.org.br.destinocerto.activity.detail.InfoItemHeader;
import institutosos.org.br.destinocerto.model.Site;

public class SiteListFragment extends ListFragment {

    private List<Item> _items;
    private InfoItemAdapter _adapter;

    public void setSite(Site site) {
        _items.add(new InfoItemHeader(getResources().getString(R.string.site_information)));
        _items.add(new InfoItem(getResources().getString(R.string.site_address), site.getAddress()));

        // TODO add some summary numbers
        /*for(Location l : wastePackage.getLocationHistory()){
            _items.add(new InfoItem(l.getTimestamp(), l.getSite().getName()));
        }*/

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
