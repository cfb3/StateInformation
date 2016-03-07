package edu.faytechcc.web151.stateinformation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.res.AssetManager;
import android.util.Log;

public class StateLoader {
  
	static List<String> stateNames = new LinkedList<String>();
	
	static Map<String, State> stateObjects = new HashMap<String, State>();
	
	public StateLoader(Activity a) {
		AssetManager am = a.getAssets();
		try {
			if(stateNames.isEmpty()) {
				InputStream is = am.open("FiftyStates.txt");
				InputStreamReader inputStreamReader = new InputStreamReader(is);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
				String line;
				while((line = bufferedReader.readLine()) != null)
				{
					if(!line.startsWith("#")) {
						String[] parts = line.split(",");
						stateNames.add(parts[0]);
						stateObjects.put(parts[0], new State(parts[0], parts[1], parts[2], parts[3]));
					}
				}
			}
			
		} catch (IOException e) {
			Log.e("FILE", e.getMessage());
		}		
	}
}
