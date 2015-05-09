package com.hackathon.HAC4819;

import com.hackathon.HAC4819.R;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class HomeFragment extends Fragment{
	
	SharedPreferences myprefs;
	TextView welcome;
	Button logout,startquiz,halloffame;
	SharedPreferences.Editor editor;
	
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        
        //logout=(Button)view.findViewById(R.id.btnlogout);
        welcome=(TextView)view.findViewById(R.id.textView1);
		startquiz=(Button)view.findViewById(R.id.btnstart);
		halloffame=(Button)view.findViewById(R.id.halloffame);
		myprefs=PreferenceManager.getDefaultSharedPreferences(getActivity());
	    String guestname = myprefs.getString("name", "Guest");
		welcome.setText("Welcome "+ guestname + '!');
		
		halloffame.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Intent callhall=new Intent(getActivity(),ResultActivity.class);
				//startActivityForResult(callhall, 1);
				
				//QuestionFragment ldf = new QuestionFragment();
				//Bundle args = new Bundle();
				//args.putString("YourKey", "YourValue");
				//ldf.setArguments(args);
				
				/*Difficultycopy qf = new Difficultycopy();
				FragmentManager fragmentManager = getFragmentManager();
				fragmentManager.beginTransaction()
						.replace(R.id.frame_container, qf).commit();*/
				
				FragmentManager fm = getFragmentManager();
				    FragmentTransaction ft = fm.beginTransaction();
				    ResultFragment llf = new ResultFragment();
				    Bundle args = new Bundle();
					args.putInt("score", -1);
					llf.setArguments(args);
				    ft.replace(R.id.frame_container, llf);
				    ft.commit();

			}
		});
		
		/*logout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				editor=myprefs.edit();
				editor.putInt("loggedin", 0);
				editor.putString("username", "");
				editor.putString("name", "");
				editor.commit();
				//Toast.makeText(getApplicationContext(), "login called", Toast.LENGTH_SHORT).show();
				Intent calllogin=new Intent(getActivity(),LoginActivity.class);
				startActivity(calllogin);
				getActivity().finish();
				
			}
			
		});
		*/
		
		startquiz.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Intent quiz = new Intent(getActivity(),QuestionsActivity.class);
				//startActivity(quiz);
				//getActivity().finish();
				
				//FragmentManager fm = getFragmentManager();
			    //FragmentTransaction ft = fm.beginTransaction();
			    Difficulty qf = new Difficulty();
			    //ft.replace(R.id.frame_container, llf);
			    //ft.commit();
			   
			    
			    FragmentManager fragmentManager = getFragmentManager();
				fragmentManager.beginTransaction()
						.replace(R.id.frame_container, qf).commit();

				
			}

		});
			
     		
		// TODO Auto-generated method stub
				
			
        return view;
        
      
        
        
        
        
        
        
    }
}
