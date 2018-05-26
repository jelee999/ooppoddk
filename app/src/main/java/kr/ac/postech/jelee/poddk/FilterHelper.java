
/*package kr.ac.postech.jelee.poddk;

import android.widget.Filter;

import java.util.ArrayList;



public class FilterHelper extends Filter {
    static ArrayList<Person> currentList;
    static StudentAdapter adapter;

    public static FilterHelper newInstance(ArrayList<Person> currentList, StudentAdapter adapter) {
        FilterHelper.adapter = adapter;
        FilterHelper.currentList = currentList;
        return new FilterHelper();
    }


    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {
        FilterResults filterResults = new FilterResults();

        if(charSequence != null && charSequence.length()>0){
            //change to upper
            charSequence=charSequence.toString().toUpperCase();

            //hold filters we find
            ArrayList<Person> foundFilters = new ArrayList<Person>();

            String studentName;

            //iterate current list
            for(int j=0;j<currentList.size();j++)
            {
                studentName=currentList.get(j).getName();

                //search
                if(studentName.toUpperCase().contains(charSequence))
                {
                    foundFilters.add(currentList.get(j));
                }
            }

            filterResults.count=foundFilters.size();
            filterResults.values=foundFilters;

        }
        else {
            filterResults.count=currentList.size();
            filterResults.values=currentList;
        }

        return filterResults;
    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        adapter.setmStudentList((ArrayList<Person>) filterResults.values);
        adapter.notifyDataSetChanged();
    }
}
*/