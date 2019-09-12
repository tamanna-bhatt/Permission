package Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import Models.AppList;
import e.permission.R;

import static android.support.v7.widget.RecyclerView.*;

public class AppAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List<AppList> listStorage;

    public AppAdapter(Context context, List<AppList> customizedListView) {
        layoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listStorage = customizedListView;
    }

    @Override
    public int getCount() {
        return listStorage.size();
    }

    @Override
    public Object getItem(int position) {
        return listStorage.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder listViewHolder;
        if(convertView == null){
            listViewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.item_list_all_app, parent, false);

            listViewHolder.appName = (TextView)convertView.findViewById(R.id.appName);
            listViewHolder.appPackage = (TextView)convertView.findViewById(R.id.appPackage);
            listViewHolder.icon_Image = (ImageView)convertView.findViewById(R.id.icon_Image);
            convertView.setTag(listViewHolder);
        }else{
            listViewHolder = (ViewHolder)convertView.getTag();
        }
        listViewHolder.appName.setText(listStorage.get(position).getAppName());
        listViewHolder.appPackage.setText(listStorage.get(position).getAppPackage());
        listViewHolder.icon_Image.setImageDrawable(listStorage.get(position).getIcon_Image());

        return convertView;
    }

    static class ViewHolder{

        TextView appName,appPackage;
        ImageView icon_Image;
    }
}
