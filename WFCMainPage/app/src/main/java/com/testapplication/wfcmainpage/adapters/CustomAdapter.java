package com.testapplication.wfcmainpage.adapters;


/**
 * Created by Nick Zwaans on 3-3-2015.
 * Provides a custom adapter for the facility list items, also holds a custom filter class to search through the list items.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.testapplication.wfcmainpage.R;
import com.testapplication.wfcmainpage.models.Facility;

import java.util.ArrayList;


public class CustomAdapter extends BaseAdapter implements Filterable {


    private ArrayList<Facility> mData = null;
    private ArrayList<Facility> mFilteredData = null;
    private LayoutInflater mCustomInflater;
    private FacilityFilter mFacilityFilter = new FacilityFilter();

    public CustomAdapter(Context pContext, ArrayList<Facility> pData) {
        this.mData = new ArrayList<>();
        mData.addAll(pData);
        this.mFilteredData = new ArrayList<>();
        mFilteredData.addAll(mData);
        mCustomInflater = LayoutInflater.from(pContext);
        getFilter();
    }

    public int getCount() {
        return mFilteredData.size();
    }

    public Object getItem(int pPosition) {
        return mFilteredData.get(pPosition);
    }

    @Override
    public long getItemId(int pPosition) {
        return pPosition;
    }

    static class ViewHolder {
        TextView facilityTitleRowText;
        TextView facilityInfoRowText;
        TextView facilityTelNrRowText2;
    }

    @Override
    public View getView(int pPosition, View pConvertView, ViewGroup pParent) {
        View view;

        Facility filteredResults = mFilteredData.get(pPosition);
        ViewHolder viewHolder;

        if (pConvertView == null) {
            view = mCustomInflater.inflate(R.layout.custom_row, null);
            viewHolder = new ViewHolder();
            viewHolder.facilityTitleRowText = (TextView) view.findViewById(R.id.customRowText);//facilityTitle
            viewHolder.facilityInfoRowText = (TextView) view.findViewById(R.id.mediumRowText);//facilitytext
            viewHolder.facilityTelNrRowText2 = (TextView) view.findViewById(R.id.mediumRowText2);//facility telnr
            view.setTag(viewHolder);

        } else {
            view = pConvertView;
            viewHolder = ((ViewHolder) view.getTag());
        }
        viewHolder.facilityTitleRowText.setText(filteredResults.getFacilityNaam());
        viewHolder.facilityInfoRowText.setText(filteredResults.getWebsite());
        viewHolder.facilityTelNrRowText2.setText(filteredResults.getTelefoonNummer());

        return view;

    }

    public Filter getFilter() {
        if (mFacilityFilter == null) {
            mFacilityFilter = new FacilityFilter();
        }
        return mFacilityFilter;
    }

    private class FacilityFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence pConstraint) {

            String filterString = pConstraint.toString().toLowerCase();
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
            } else {

                synchronized (this) {
                    results.values = mData;
                    results.count = mData.size();
                }
            }
            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence pConstraint, FilterResults pResults) {
            mFilteredData = (ArrayList<Facility>) pResults.values;
            notifyDataSetChanged();
        }
    }

}