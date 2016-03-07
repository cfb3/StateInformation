package edu.faytechcc.web151.stateinformation;

import edu.faytechcc.web151.stateinformation.StateListFragment.OnStateSelectedListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends FragmentActivity
	implements OnStateSelectedListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//loading the data from the file into the data structures in StateLoader
		StateLoader loader = new StateLoader(this);
		setContentView(R.layout.states_list);
		
        // Check whether the activity is using the layout version with
        // the fragment_container FrameLayout. If so, we must add the first fragment
		if (findViewById(R.id.fragment_container) != null){
            
			// However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
			if(savedInstanceState != null) {
				return; 
			}
			
			// Create an instance of StateListFragment
			Fragment firstFragment = new StateListFragment();
			
            // In case this activity was started with special instructions from an Intent,
            // pass the Intent's extras to the fragment as arguments			
			firstFragment.setArguments(getIntent().getExtras());
			
			//Add the fragment to the 'fragment container' FrameLayout
			getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, firstFragment).commit();
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onStateSelected(int position) {
		Toast.makeText(this, "Item: " + StateLoader.stateNames.get(position), Toast.LENGTH_LONG).show();
		
        // The user selected the the state at position in the States list.

        // Capture the Sate fragment from the activity layout
		StateFragment stateFrag =
				(StateFragment)getSupportFragmentManager().findFragmentById(R.id.state_fragment);
		
		if(stateFrag != null)
		{
            // If StateFrag is available, we're in two-pane layout...

            // Call a method in the StateFragment to update its content	with the new state info		
			stateFrag.updateStateView(position);
		}
		else
		{
            // If the frag is not available, we're in the one-pane layout and must swap frags...

            // Create fragment and give it an argument for the selected state			
			StateFragment newFrag = new StateFragment();
			
			//bundle up the position of the selected state
			Bundle args = new Bundle();
			args.putInt(StateFragment.ARG_POSITION, position);
			newFrag.setArguments(args);
			FragmentTransaction transaction = 
					getSupportFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
			transaction.replace(R.id.fragment_container, newFrag);
			transaction.addToBackStack(null);
			
			
			//do it!
			transaction.commit();
		}
	}

}
