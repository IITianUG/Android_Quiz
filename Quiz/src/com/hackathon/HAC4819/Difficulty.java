package com.hackathon.HAC4819;

import com.hackathon.HAC4819.R;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class Difficulty
  extends Fragment
{
  Button easy;
  SharedPreferences.Editor editor;
  Button hard;
  Intent ieasy;
  Intent ihard;
  Intent imed;
  Button medium;
  SharedPreferences myprefs;
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    View localView = paramLayoutInflater.inflate(R.layout.activity_difficulty, paramViewGroup, false);
    this.easy = ((Button)localView.findViewById(2131296257));
    this.medium = ((Button)localView.findViewById(2131296258));
    this.hard = ((Button)localView.findViewById(2131296259));
    this.easy.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Difficulty.this.myprefs = PreferenceManager.getDefaultSharedPreferences(Difficulty.this.getActivity());
        Difficulty.this.editor = Difficulty.this.myprefs.edit();
        Difficulty.this.editor.putInt("dlevel", 1);
        Difficulty.this.editor.commit();
        QuestionFragment localQuestionFragment = new QuestionFragment();
        Difficulty.this.getFragmentManager().beginTransaction().replace(2131296293, localQuestionFragment).commit();
      }
    });
    this.medium.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Difficulty.this.myprefs = PreferenceManager.getDefaultSharedPreferences(Difficulty.this.getActivity());
        Difficulty.this.editor = Difficulty.this.myprefs.edit();
        Difficulty.this.editor.putInt("dlevel", 2);
        Difficulty.this.editor.commit();
        QuestionFragmentMed localQuestionFragmentMed = new QuestionFragmentMed();
        Difficulty.this.getFragmentManager().beginTransaction().replace(2131296293, localQuestionFragmentMed).commit();
      }
    });
    this.hard.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Difficulty.this.myprefs = PreferenceManager.getDefaultSharedPreferences(Difficulty.this.getActivity());
        Difficulty.this.editor = Difficulty.this.myprefs.edit();
        Difficulty.this.editor.putInt("dlevel", 3);
        Difficulty.this.editor.commit();
        QuestionFragmentHard localQuestionFragmentHard = new QuestionFragmentHard();
        Difficulty.this.getFragmentManager().beginTransaction().replace(2131296293, localQuestionFragmentHard).commit();
      }
    });
    return localView;
  }
}
