package edu.faytechcc.web151.stateinformation;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class StateListFragment extends ListFragment {
	OnStateSelectedListener mCallback;
	
	// The container Activity must implement this interface so this frag can deliver messages
	// to the activity that the list has been clicked.
	public interface OnStateSelectedListener {
		public void onStateSelected(int position);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		int layout = android.R.layout.simple_list_item_activated_1;
		
		// Create an array adapter for the list view, using the Ipsum headlines array
		setListAdapter(
				new ArrayAdapter<String>(getActivity(), layout, StateLoader.stateNames));
	}
	
	@Override 
	public void onStart() {
		super.onStart();
		
        // When in two-pane layout, set the listview to highlight the selected list item
        // (We do this during onStart because at the point the listview is available.)		
		if(getFragmentManager().findFragmentById(R.id.state_fragment) != null) {
			getListView().setChoiceMode((ListView.CHOICE_MODE_SINGLE));
		}
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception.		
		if(activity instanceof OnStateSelectedListener) {
			mCallback = (OnStateSelectedListener) activity;
		}
		else {
			throw new IllegalArgumentException("Activity must implement OnStateSelectedListener.");
		}
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// Notify the parent activity of selected item
		mCallback.onStateSelected(position);
		
		// Set the item as checked to be highlighted when in two-pane layout
		getListView().setItemChecked(position, true);
	}
}
 