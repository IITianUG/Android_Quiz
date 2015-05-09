package com.hackathon.HAC4819;

import com.hackathon.HAC4819.R;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

public class ResultFragment extends Fragment {
	
	 Button playagain;
	    SharedPreferences myprefs;
	    String name,username,sscore;
	    ListView listhall;
	    int ddlevel,dlevelcopy;
	    
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View view = inflater.inflate(R.layout.fragment_result, container, false);	
		// TODO Auto-generated method stub
        
        playagain=(Button)view.findViewById(R.id.btnplayagain);
		listhall=(ListView)view.findViewById(R.id.listhall);
		myprefs=PreferenceManager.getDefaultSharedPreferences(getActivity());
		name=myprefs.getString("name", "");
		username=myprefs.getString("username", "");
		ddlevel=myprefs.getInt("dlevel",0);
		
		
		int score = getArguments().getInt("score");
		//Intent intent=getIntent();
		//int score=intent.getIntExtra("score", 0);
		if(score>=0){
			sscore=score+"";
			  DbMethods.SaveScore(getActivity(), name, username, sscore,ddlevel);
		}
		//myprefs=PreferenceManager.getDefaultSharedPreferences(getActivity());
		//dlevelcopy=myprefs.getInt("dlevel",0);
		 Cursor cursor=DbMethods.queryHall(getActivity(),ddlevel);

			MyCAdapter bcAdapter=new MyCAdapter(getActivity(), cursor);
			 listhall.setAdapter(bcAdapter);
			playagain.setOnClickListener(new View.OnClickListener() {
			//retrieving hall of fame
		   
		   
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//Intent w = new Intent(ResultActivity.this,QuestionsActivity.class);
					//startActivity(w);
					//finish();
					
					 FragmentManager fm = getFragmentManager();
					    FragmentTransaction ft = fm.beginTransaction();
					    HomeFragment llf = new HomeFragment();
					    ft.replace(R.id.frame_container, llf);
					    ft.commit();
					
				}
			});
		 
		
				
			
        return view;
    }
}
