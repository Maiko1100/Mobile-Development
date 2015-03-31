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
	//private Facility mFilteredData[] = null;
	private LayoutInflater customInflater;
	private FacilityFilter facilityFilter = new FacilityFilter();


	public CustomAdapter(Context context, ArrayList<Facility> pData) {
		this.mData=pData;
		this.mFilteredData=pData;
		customInflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return mFilteredData.size();
	}

	public Object getItem(int position){
		return mFilteredData.get(position);
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

		Facility facility = mData.get(position);
		holder.customRowText.setText(facility.getFacilityNaam());
		holder.mediumRowText.setText(facility.getWebsite());
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

			final ArrayList<Facility> LIST = mData;

			int count = LIST.size();
			final ArrayList<Facility> NLIST = new ArrayList<>();

			Facility filterableString;

			for (int i = 0; i < count; i++){
				filterableString = LIST.get(i);
				if (filterableString.getFacilityNaam().toLowerCase().contains(filterString)){
					NLIST.add(filterableString);
				}
			}
			results.values= NLIST;
			results.count = NLIST.size();
			return results;
		}

		@Override
		protected void publishResults(CharSequence constraint, FilterResults results) {
			mFilteredData = (ArrayList<Facility>) results.values;
			notifyDataSetChanged();
		}
	}

}