package com.testapplication.wfcmainpage.adapters;


/**
 * Created by Nick Zwaans on 3-3-2015.
 * Provides a custom adapter for the facility list items, also holds a custom filter class to search through the list items.
 */

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.testapplication.wfcmainpage.R;
import com.testapplication.wfcmainpage.activity.MainActivity;
import com.testapplication.wfcmainpage.models.Facility;

import java.util.ArrayList;


public class CustomAdapter extends BaseAdapter implements Filterable {

    /**
     * @param mData provides an arraylist for the database items.
     * @param mFilteredData provides an arraylist for the items after they are filtered.
     * @param mCustomInflater provides a layoutinflater for the output of the filtered items.
     * @param mFacilityFilter provides a custom filter to make our search function work.
     */

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
        TextView facilityTelNrRowText;
        ImageView image1;
        ImageView image2;
        ImageView image3;
    }

    /**
     * Provides a ViewHolder keeps references to children views to avoid unnecessary calls to findViewById() on each row.
     * When convertView is not null, we can reuse it directly, there is no need to reinflate it.
     * We only inflate a new View when the convertView supplied by ListView is null.
     * viewHolder provides a ViewHolder and store references to the two children views we want to bind data to.
     *
     * @param pPosition gets the index of the position.
     * @return view with searched items
     */

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
            viewHolder.facilityTelNrRowText = (TextView) view.findViewById(R.id.mediumRowText2);//facility telnr
            viewHolder.image1 = (ImageView) view.findViewById(R.id.womenswear);
            viewHolder.image2 = (ImageView) view.findViewById(R.id.menswear);
            viewHolder.image3 = (ImageView) view.findViewById(R.id.childrenswear);
            view.setTag(viewHolder);

        } else {
            view = pConvertView;
            viewHolder = ((ViewHolder) view.getTag());
        }


        viewHolder.image1.setVisibility(View.GONE);
        viewHolder.image2.setVisibility(View.GONE);
        viewHolder.image3.setVisibility(View.GONE);
//
//        int[] modeIcons = new int[]{R.drawable.womenswear, R.drawable.menswear, R.drawable.childrenswear};
//        ArrayList<Integer> modeIconArray = new ArrayList<>();
//
//
//        for (int i = 0; i <= modeIcons.length-1; i++) {
//            if (!filteredResults.isLeeg(i)) {
//                modeIconArray.add(modeIcons[i]);
//                System.out.println(i);
//            }
//
//        }
//
//
//            for (int y = 0; y < modeIconArray.size(); y++) {
//                if (checkEmptyImage1(viewHolder)) {
//                    viewHolder.image1.setVisibility(View.VISIBLE);
//                    viewHolder.image1.setImageResource(modeIconArray.get(y));
//                } else if (checkEmptyImage2(viewHolder)) {
//                    viewHolder.image2.setVisibility(View.VISIBLE);
//                    viewHolder.image2.setImageResource(modeIconArray.get(y));
//                } else  {
//                    viewHolder.image3.setVisibility(View.VISIBLE);
//                    viewHolder.image3.setImageResource(modeIconArray.get(y));
//                }
//            }
        if(!filteredResults.isLeeg(0)){
                    viewHolder.image1.setVisibility(View.VISIBLE);
                    viewHolder.image1.setImageResource(R.drawable.womenswear);
        }
        if(!filteredResults.isLeeg(1)){
                    viewHolder.image2.setVisibility(View.VISIBLE);
                    viewHolder.image2.setImageResource(R.drawable.menswear);}
        if(!filteredResults.isLeeg(2)){
            viewHolder.image3.setVisibility(View.VISIBLE);
            viewHolder.image3.setImageResource(R.drawable.childrenswear);}


            viewHolder.facilityTitleRowText.setText(filteredResults.getFacilityNaam());
            viewHolder.facilityInfoRowText.setText(filteredResults.getWebsite());
            viewHolder.facilityTelNrRowText.setText(filteredResults.getTelefoonNummer());

            return view;

        }


    public boolean checkEmptyImage1(ViewHolder holder){
        if(holder.image1.getDrawable()==null){
            return true;
        }
        else return false;
    }
    public boolean checkEmptyImage2(ViewHolder holder){
        if(holder.image2.getDrawable()==null){
            return true;
        }
        else return false;
    }
    public boolean checkEmptyImage3(ViewHolder holder){
        if(holder.image3.getDrawable()==null){
            return true;
        }
        else return false;
    }



    public Filter getFilter() {
        if (mFacilityFilter == null) {
            mFacilityFilter = new FacilityFilter();
        }
        return mFacilityFilter;
    }

    /**
     * Provides a custom filter that searches per letter and not for whole words.
     */

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