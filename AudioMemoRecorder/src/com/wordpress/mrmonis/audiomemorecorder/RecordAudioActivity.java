package com.wordpress.mrmonis.audiomemorecorder;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.wordpress.mrmonis.audiomemorecorder.PlayAudioMemoFragment.PlayAudioMemoFragmentListener;

public class RecordAudioActivity extends SherlockFragmentActivity implements
		PlayAudioMemoFragmentListener {

	private RecordAudioMemoFragment mRecordFrag;
	private PlayAudioMemoFragment mPlayFrag;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_record_audio);
		
		// Create fragments
		mPlayFrag = new PlayAudioMemoFragment();
		mRecordFrag = new RecordAudioMemoFragment();
		
		// Start with the play fragment
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.add(R.id.main_fragment, mPlayFrag).commit();
	}

	@Override
	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.record_audio, menu);
		return true;
	}

	@Override
	public void onCreateMemoCLicked() {
		// Swap fragments
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.main_fragment, mRecordFrag)
				.addToBackStack(null).commit();

	}
}
