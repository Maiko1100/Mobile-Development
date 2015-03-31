package com.testapplication.wfcmainpage;


/**
 * Created by Nick Zwaans on 3-3-2015.
 * Deze class wordt toegepast op alle items in de list om de sFacility array te converteren naar een list.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;


public class CustomAdapter extends BaseAdapter implements Filterable {

    private Facility mData[] = null;
    private Facility mFilteredData[] = null;
    private LayoutInflater customInflater;
    private FacilityFilter facilityFilter = new FacilityFilter();


    public CustomAdapter(Context context, Facility[] pData) {
        this.mData=pData;
        this.mFilteredData=pData;
        customInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return mFilteredData.length;
    }

    public Object getItem(int position){
        return mFilteredData[position];
    }

    public long getItemId(int position){
        return position;
    }

    static class ViewHolder{
        TextView customRowText;
        TextView mediumRowText;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        ViewHolder holder;
        if (view==null){
            view = customInflater.inflate(R.layout.custom_row, parent, false);

            holder = new ViewHolder();

            holder.customRowText = (TextView)view.findViewById(R.id.customRowText);
            holder.mediumRowText = (TextView)view.findViewById(R.id.mediumRowText);

            view.setTag(holder);
        }else{
            holder = (ViewHolder)view.getTag();
        }

        Facility facility = mData[position];
        holder.customRowText.setText(facility.title);
        holder.mediumRowText.setText(facility.info);
        return view;
    }

    public Filter getFilter(){
        return facilityFilter;
    }

    private class FacilityFilter extends Filter{
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();
            FilterResults results = new FilterResults();

            final Facility LIST[] = mData;

            int count = LIST.length;
            final Facility NLIST[] = new Facility[count];

            Facility filterableString;

            for (int i = 0; i < count; i++){
                filterableString = LIST[i];
                if (filterableString.toString().toLowerCase().contains(filterString)){
                    NLIST = new Facility[]{
                            new Facility(filterableString.getTitle(), filterableString.getInfo())
                    };
                }
            }
            results.values= NLIST;
            results.count = NLIST.length;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mFilteredData = (Facility[]) results.values;
            notifyDataSetChanged();
        }
    }

}
