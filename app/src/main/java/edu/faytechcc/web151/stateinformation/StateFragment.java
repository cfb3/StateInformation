package edu.faytechcc.web151.stateinformation;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class StateFragment extends Fragment{
	final static String ARG_POSITION = "position";
	int mCurrentPosition = -1;
	
	@Override
	public View onCreateView(LayoutInflater inflator, ViewGroup container, Bundle savedInstance) {
		
        // If activity recreated (such as from screen rotate), restore
        // the previous article selection set by onSaveInstanceState().
        // This is primarily necessary when in the two-pane layout.		
		if(savedInstance != null) {
			mCurrentPosition = savedInstance.getInt(ARG_POSITION);
		}
		
		return inflator.inflate(R.layout.state, container, false);
	}
	
	@Override
	public void onStart() {
		super.onStart();
		
        // During startup, check if there are arguments passed to the fragment.
        // onStart is a good place to do this because the layout has already been
        // applied to the fragment at this point so we can safely call the method
        // below that sets the State information.		
		Bundle args = getArguments();
		if(args != null) {
			updateStateView(args.getInt(ARG_POSITION));
		}
		else if(mCurrentPosition != -1) {
			updateStateView(mCurrentPosition);
		}
		else {
			updateStateView(0); //just display the first state...
		}
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		// Save the current article selection in case we need to recreate the fragment
		outState.putInt(ARG_POSITION, mCurrentPosition);
		
	}
	
	public void updateStateView(int pos) {
		
		final State myState = StateLoader.stateObjects.get(StateLoader.stateNames.get(pos));
		
		ImageView image = (ImageView)getActivity().findViewById(R.id.flagView);
		String stateName = myState.getName().toLowerCase().trim().replace(' ', '_');
		
		int id = this.getResources().getIdentifier(stateName, "drawable", getActivity().getPackageName());
		
		image.setImageResource(id);
		
		TextView name = (TextView)getActivity().findViewById(R.id.name);
		name.setText(myState.getName());
		
		TextView captial = (TextView)getActivity().findViewById(R.id.capital);
		captial.setText(myState.getCapital());
		
		TextView bird = (TextView)getActivity().findViewById(R.id.bird);
		bird.setText(myState.getBird());
		
		Button webButton = (Button)getActivity().findViewById(R.id.url);
		webButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(myState.getUrl())));
			}
		});
	}
}
