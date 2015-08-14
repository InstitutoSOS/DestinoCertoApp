package institutosos.org.br.destinocerto.activity.detail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import institutosos.org.br.destinocerto.activity.detail.wastepackage.PackageActivity;

public abstract class Item {
    public abstract int getViewType();

    public abstract View getView(LayoutInflater inflater, View convertView, Context context);

    public void longClick(PackageActivity packageActivity) {
    }
}
