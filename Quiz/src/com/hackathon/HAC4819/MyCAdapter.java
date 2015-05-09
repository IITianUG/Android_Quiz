package com.hackathon.HAC4819;

import com.hackathon.HAC4819.R;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class MyCAdapter extends CursorAdapter {
	static public String TBLUsers="table_users";
	static public String COLId="_id";
	static public String COLName="name";
	static public String COLUsername="username";
	static public String COLPassword="password";
	
	static public String TBLHall="halloffame";
	
	static public String COLScoresEasy="scoreseasy";
	static public String COLScoresMed="scoresmed";
	static public String COLScoresHard="scoreshard";
	static public String COLStatus="status";
	static public String COLTime="time";

	public MyCAdapter(Context context, Cursor c) {
		super(context, c);
		
				
		// TODO Auto-generated constructor stub
	}

	@Override
	public void bindView(View view, Context arg1, Cursor cursor) {
		// TODO Auto-generated method stub
		String nm=cursor.getString(cursor.getColumnIndex(COLName));
		String username=cursor.getString(cursor.getColumnIndex(COLUsername));
		String sc1=cursor.getString(cursor.getColumnIndex(COLScoresEasy));
		String sc2=cursor.getString(cursor.getColumnIndex(COLScoresMed));
		String sc3=cursor.getString(cursor.getColumnIndex(COLScoresHard));
		
		
		TextView name=(TextView)view.findViewById(R.id.name);
		TextView score1=(TextView)view.findViewById(R.id.score1);
		TextView score2=(TextView)view.findViewById(R.id.score2);
		TextView score3=(TextView)view.findViewById(R.id.score3);
		name.setText(nm+" ( "+username+" )");
		score1.setText(sc1);
		score2.setText(sc2);
		score3.setText(sc3);
		
	}

	@Override
	public View newView(Context arg0, Cursor arg1, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflator=LayoutInflater.from(parent.getContext());
		View view=inflator.inflate(R.layout.list_layout, parent, false);
		
		return view;
	}

}
