package com.wordpress.mrmonis.audiomemorecorder;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;

public class RecordAudioActivity extends SherlockFragmentActivity {

	private FragmentManager mManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_record_audio);

		mManager = getSupportFragmentManager();
		swapFragment(new RecordAudioMemoFragment());
	}

	@Override
	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.record_audio, menu);
		return true;
	}
	
	/* Swap fragments */
	public void swapFragment(SherlockFragment fragment) {
		FragmentTransaction transaction = mManager.beginTransaction();
		transaction.replace(R.id.main_fragment, fragment);
		transaction.commit();
	}

}
