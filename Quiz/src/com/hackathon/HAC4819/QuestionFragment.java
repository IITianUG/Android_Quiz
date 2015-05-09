package com.hackathon.HAC4819;



import java.util.Random;

import com.hackathon.HAC4819.R;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


	
	
	public class QuestionFragment extends Fragment {
		Bundle args;
		public String[] questions;
		public int[] answer;//={"1","2","3","4"};
		public String[] options;
		int ans=0;
		
		View view1, view2, view3, view4;
		TextView questionview, option1, option2, option3, option4, tvtimer,tvscore;
		int counter,timeleft=0;
		Button submit;
		final int startTime = 30 * 1000;
		final int interval = 1 * 100;
		CountDownTimer count;
		ProgressBar pb;
		int won = 0;
		public int ans1=1,ans2=2,ans3=3,ans4=4,currans=0,score=0;
		View.OnClickListener l;
		int color=Color.parseColor("#337777");
		    
		@Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	 
	        View view = inflater.inflate(R.layout.fragment_question, container, false);	
			// TODO Auto-generated method stub
	        
	        view1 = (View)view.findViewById(R.id.view1);
			view2 = (View) view.findViewById(R.id.view2);
			view3 = (View) view.findViewById(R.id.view3);
			view4 = (View) view.findViewById(R.id.view4);
			questionview = (TextView) view.findViewById(R.id.questionview);
	 
			option1=(TextView)view.findViewById(R.id.option1);
			option2=(TextView)view.findViewById(R.id.option2);
			option3=(TextView)view.findViewById(R.id.option3);
			option4=(TextView)view.findViewById(R.id.option4);
			
			option1.setBackgroundColor(color);
			option2.setBackgroundColor(color);
			option3.setBackgroundColor(color);
			option4.setBackgroundColor(color);
			
		
			

			submit = (Button) view.findViewById(R.id.submitb);
			view1.setBackgroundColor(Color.BLUE);
			view2.setBackgroundColor(Color.GRAY);
			view3.setBackgroundColor(Color.GRAY);
			view4.setBackgroundColor(Color.GRAY);
	    
	    	counter = 1;
			
			pb = (ProgressBar)view.findViewById(R.id.progress);
			pb.setProgress(0);

			pb.setMax(3000);
			tvscore=(TextView)view.findViewById(R.id.tvscore);
			tvtimer = (TextView)view.findViewById(R.id.tvtimer);
			tvtimer.setText("30");

			count = new MyCountDownTimer(startTime, interval);
			count.start();
			
			//setting onclick listeners for options
			l=new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					switch(v.getId()){
					case R.id.option1:currans=1;
									toast("option1");
								        break;
					case R.id.option2:currans=2;
					                toast("option2");
					                    break;
					case R.id.option3:currans=3;
					                  toast("option3");
						              break;
					case R.id.option4:currans=4;
					              toast("option1");
						              break;
					
					
					}
					
				}
			};
			
			//options click listeners
			option1.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					currans=1;
					option1.setBackgroundColor(Color.GRAY);
					option2.setBackgroundColor(color);
					option3.setBackgroundColor(color);
					option4.setBackgroundColor(color);
				}
			});
	option2.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					currans=2;
					option1.setBackgroundColor(color);
					option2.setBackgroundColor(Color.GRAY);
					option3.setBackgroundColor(color);
					option4.setBackgroundColor(color);
				}
			});
	option3.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			currans=3;
			option1.setBackgroundColor(color);
			option2.setBackgroundColor(color);
			option3.setBackgroundColor(Color.GRAY);
			option4.setBackgroundColor(color);
		}
	});
	option4.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			currans=4;
			option1.setBackgroundColor(color);
			option2.setBackgroundColor(color);
			option3.setBackgroundColor(color);
			option4.setBackgroundColor(Color.GRAY);
		}
	});
	
	
			//set question 1
			questions=getResources().getStringArray(R.array.questionseasy);
			answer=getResources().getIntArray(R.array.answereasy);
			options=getResources().getStringArray(R.array.optionseasy);
			
					
			//set question #1
			setQuestion(1);
			

			submit.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					//button background reset
					option1.setBackgroundColor(color);
					option2.setBackgroundColor(color);
					option3.setBackgroundColor(color);
					option4.setBackgroundColor(color);
					counter++;

					if (counter == 2) {
						//check first answer
						if(currans==ans){
							score+=100;
							view1.setBackgroundColor(Color.GREEN);
							
							
							
						}else{
							view1.setBackgroundColor(Color.RED);
						}
						tvscore.setText(score+"");
						currans=0;

						view2.setBackgroundColor(Color.BLUE);
						view3.setBackgroundColor(Color.GRAY);
						view4.setBackgroundColor(Color.GRAY);
						
						//set question #2
						setQuestion(2);
					} else if (counter == 3) {
						if(currans==ans){
							score+=100;
							view2.setBackgroundColor(Color.GREEN);
							
						}else{
							view2.setBackgroundColor(Color.RED);
						}
						tvscore.setText(score+"");
						currans=0;

						view3.setBackgroundColor(Color.BLUE);
						view4.setBackgroundColor(Color.GRAY);
						//set question #3
						setQuestion(3);
					} else if (counter == 4) {
						if(currans==ans){
							score+=100;
							view3.setBackgroundColor(Color.GREEN);
							
						}else{
							view3.setBackgroundColor(Color.RED);
						}
						tvscore.setText(score+"");
						currans=0;

						view4.setBackgroundColor(Color.BLUE);
						//set question #4
						setQuestion(4);
						
					} else if (counter == 5) {
						
						if(currans==ans){
							score+=100;
							view4.setBackgroundColor(Color.GREEN);
						}else{
							view4.setBackgroundColor(Color.RED);
						}
						tvscore.setText(score+"");
						currans=0;

						//get remaining seconds
						if(tvtimer.getText().toString().contentEquals("Time Over")){
								timeleft=0;
			            }else{
			            	timeleft=Integer.parseInt(tvtimer.getText().toString());
			            }
						final int initialscore=score;
					    //hide button before runnable
						submit.setVisibility(View.GONE);
						 
						//subtract time and add score
						final Handler handler = new Handler();
				         Runnable runnable = new Runnable() {
				            
				            public void run() {
				               int factor=0;
								switch(initialscore){
								  case 0:factor=0;break;
								  case 100:factor=4;break;
								  case 200:factor=6;break;
								  case 300:factor=8;break;
								  case 400:factor=10;break;
								}
								
				                timeleft--;
				                tvtimer.setText(factor+"x "+timeleft+"");
				                score=score+factor;
				                tvscore.setText(score+"");
				                if(timeleft>0){
				                	 handler.postDelayed(this, 50);  //for interval...    
				                }else{
				                	  tvscore.setText(score+"");
										won = 1;
										submit.setVisibility(View.VISIBLE);
										submit.setText("Proceed to results");
				                }
				             }
				        };
				        handler.postDelayed(runnable, 500); //for initial delay..
				        count.cancel();

					} else if (counter == 6) {
						// proceed to results
						
								//Intent callmain = new Intent(
								//		QuestionsActivity.this,
								//		ResultActivity.class);
								// TODO Auto-generated method stub
								//callmain.putExtra("score", score);
								//startActivity(callmain);
								//getActivity().finish();
						
						
						args = new Bundle();
						args.putInt("score", score);
						
						
						 FragmentManager fm = getFragmentManager();
						    FragmentTransaction ft = fm.beginTransaction();
						    ResultFragment llf = new ResultFragment();
						    llf.setArguments(args);
						    ft.replace(R.id.frame_container, llf);
						    ft.commit();
								
					}
				}
			});
			
			
			
			

				
	        return view;
	        
	        
	    }
		
		
		
			
		
		
		private void setQuestion(int i){
			Random n =new Random();
			int x=n.nextInt(4);
			int y=4*(i-1)+x;
			//Log.d("TAG",y+"");
			questionview.setText(questions[y]);
			option1.setText(options[4*(y)+0]);
			option2.setText(options[4*(y)+1]);
			option3.setText(options[4*(y)+2]);
			option4.setText(options[4*(y)+3]);
			ans=answer[y];
		}
		
	 		
     

		public class MyCountDownTimer extends CountDownTimer {

			

			public MyCountDownTimer(long millisInFuture, long countDownInterval) {
				super(millisInFuture, countDownInterval);
				// TODO Auto-generated constructor stub
			}

			

			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub

				tvtimer.setText((millisUntilFinished / 1000) + "");

				pb.setProgress(3000 - (int) millisUntilFinished / 10);

			}

			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				if (won == 0) {
					tvtimer.setText("Time Over");
					// disabling button
					counter = 5;
					view4.setBackgroundColor(Color.RED);
					view3.setBackgroundColor(Color.RED);
					view1.setBackgroundColor(Color.RED);
					view2.setBackgroundColor(Color.RED);
					submit.setText("Proceed to results");

				} else {
					// do nothing
				}

			}

		}
		
		public void toast(String msg){
			Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT);
		}

		
		
	}
	
	
	
	
	
