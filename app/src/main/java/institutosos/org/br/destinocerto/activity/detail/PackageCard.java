package institutosos.org.br.destinocerto.activity.detail;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import institutosos.org.br.destinocerto.R;

/**
 * Created by rehans on 13-Aug-15.
 */
public class PackageCard extends Item {
    public enum TYPES {
        MAP, EMAIL, PHONE
    };

    private TYPES _type;
    private final String str1;
    private final String str2;
    private String _actionText;

    public PackageCard(String text1, String text2) {
        this.str1 = text1;
        this.str2 = text2;
    }

    public PackageCard(String text1, String text2, TYPES type) {
        this(text1, text2);
        _type = type;
    }

    public PackageCard(String text1, String text2, TYPES type, String actionText) {
        this(text1, text2, type);
        _actionText = actionText;
    }

    @Override
    public int getViewType() {
        return PackageCardAdapter.RowType.LIST_ITEM.ordinal();
    }

    @Override
    public View getView(LayoutInflater inflater, View convertView, final Context context) {
        View view;
        if (convertView == null) {
            view = inflater.inflate(R.layout.package_detail_card, null);
        } else {
            view = convertView;
        }

        TextView text1 = (TextView) view.findViewById(R.id.list_content1);
        TextView text2 = (TextView) view.findViewById(R.id.list_content2);
        text1.setText(str1);

        if (_type == TYPES.PHONE) {
            text2.setText(formatPhoneNumber(str2));
        } else {
            text2.setText(str2);
        }

        return view;
    }

    private String formatPhoneNumber(String number) {
        String searchPattern = "(.{3})(.{2})(.{3})(.{2})(.{2})";
        return number.replaceAll(searchPattern, "$1 $2 $3 $4 $5");
    }

    @Override
    public void click(PackageActivity context) {
        if (_type == null || str2.length() <= 0) {
            return;
        }
        String action = _actionText != null && _actionText.length() > 0 ? _actionText : str2;
        switch (_type) {
            case MAP:
                Intent mapIntent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("geo:?q=" + action));
                context.startActivity(mapIntent);
                break;
            case EMAIL:
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("message/rfc822");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { action });
                context.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                break;
            case PHONE:
                Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + action));
                context.startActivity(dialIntent);
                break;
            default:

        }
    }

    @Override
    public void longClick(PackageActivity context) {
        super.longClick(context);
        if (str2.length() <= 0) {
            return;
        }

        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(str1, str2);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(context, "Copied " + str1 + " into clipboard", Toast.LENGTH_SHORT).show();
    }
}
