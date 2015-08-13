package institutosos.org.br.destinocerto.activity.detail;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import institutosos.org.br.destinocerto.R;
import institutosos.org.br.destinocerto.model.Cooperative;
import institutosos.org.br.destinocerto.model.WastePackage;

public class PackageActivity extends ListActivity {

    public static final String BARCODE = "BARCODE";

    @Bind(R.id.package_material)
    TextView packageMaterial;

    @Bind(R.id.package_cooperative)
    TextView packageCooperative;

    private List<Item> _items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package);
        ButterKnife.bind(this);

        getListView().setLongClickable(true);
        getListView().setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                _items.get(position).longClick(PackageActivity.this);
                return true;
            }
        });

        Intent intent = getIntent();
        String barcode = intent.getStringExtra(BARCODE);

        Cooperative cooperative = new Cooperative();
        cooperative.setName("Test Cooperative");
        cooperative.setAddress("Example Street 15, 12345 São Paulo");
        WastePackage p = new WastePackage();
        p.setBarcode(barcode);
        p.setMaterial(WastePackage.EMaterial.PLASTIC);
        p.setWeight(5.5);
        p.setImageUrl("http://i.imgur.com/uuHbEvf.jpg");
        p.setCooperative(cooperative);

        setDetails(p);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_package, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setDetails(WastePackage p) {

        packageMaterial.setText(p.getMaterial().toString());
        packageCooperative.setText(p.getCooperative().getName());

        _items = new ArrayList<>();
        _items.add(new PackageHeader("Package information"));
        _items.add(new PackageCard("Barcode", p.getBarcode()));
        _items.add(new PackageCard("Weight", p.getWeight() + ""));

        _items.add(new PackageHeader("Cooperative"));
        _items.add(new PackageCard("Name", p.getCooperative().getName()));
        _items.add(new PackageCard("Address", p.getCooperative().getAddress(), PackageCard.TYPES.MAP));

        // TODO list those things:
        // * picture

        PackageCardAdapter adapter = new PackageCardAdapter(this, _items);
        setListAdapter(adapter);
    }
}
