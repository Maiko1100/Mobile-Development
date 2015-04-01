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

import java.util.ArrayList;


public class CustomAdapter extends BaseAdapter implements Filterable {

	private ArrayList<Facility> mData = null;
	private ArrayList<Facility> mFilteredData = null;
	private LayoutInflater customInflater;
	private FacilityFilter facilityFilter = new FacilityFilter();
    private CustomAdapter adapter;


	public CustomAdapter(Context context, ArrayList<Facility> pData) {
		this.mData= new ArrayList<>();
        mData.addAll(pData);
		this.mFilteredData=new ArrayList<>();
        mFilteredData.addAll(mData);
		customInflater = LayoutInflater.from(context);
        getFilter();
	}

	public int getCount() {
		return mFilteredData.size();
	}

	public Object getItem(int position){
		return mFilteredData.get(position);
	}

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder{
		TextView customRowText;
		TextView mediumRowText;
        TextView mediumRowText2;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        Facility filteredResults = mFilteredData.get(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            view = customInflater.inflate(R.layout.custom_row, null);
            viewHolder = new ViewHolder();
            viewHolder.customRowText = (TextView) view.findViewById(R.id.customRowText);//facilityTitle
            viewHolder.mediumRowText = (TextView) view.findViewById(R.id.mediumRowText);//facilitytext
            viewHolder.mediumRowText2 = (TextView) view.findViewById(R.id.mediumRowText2);//facility telnr
            view.setTag(viewHolder);

        } else {
            view = convertView;
            viewHolder = ((ViewHolder) view.getTag());
        }
        viewHolder.customRowText.setText(filteredResults.getFacilityNaam());
        viewHolder.mediumRowText.setText(filteredResults.getWebsite());
		viewHolder.mediumRowText2.setText(filteredResults.getTelefoonNummer());

        return view;

    }

	public Filter getFilter(){
        if (facilityFilter == null){
            facilityFilter = new FacilityFilter();
        }
		return facilityFilter;
	}

	private class FacilityFilter extends Filter{
		@Override
		protected FilterResults performFiltering(CharSequence constraint) {

			String filterString = constraint.toString().toLowerCase();
			FilterResults results = new FilterResults();
            if (filterString != null && filterString.length() > 0) {


                final ArrayList<Facility> NLIST = new ArrayList<>();

                Facility filterableString;

                for (int i = 0; i < mData.size(); i++) {
                    filterableString = mData.get(i);
                    if (filterableString.getFacilityNaam().toLowerCase().contains(filterString)) {
                        NLIST.add(filterableString);
                    }
                }
                results.values = NLIST;
                results.count = NLIST.size();
            }else {

                synchronized (this){
                    results.values = mData;
                    results.count = mData.size();
                }
            }
            return results;
		}

        @SuppressWarnings("unchecked")
		@Override
		protected void publishResults(CharSequence constraint, FilterResults results) {
			mFilteredData = (ArrayList<Facility>) results.values;
			notifyDataSetChanged();
		}
	}

}