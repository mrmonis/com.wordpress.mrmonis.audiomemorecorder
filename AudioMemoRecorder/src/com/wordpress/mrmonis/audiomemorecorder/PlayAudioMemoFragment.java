package com.wordpress.mrmonis.audiomemorecorder;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.actionbarsherlock.app.SherlockListFragment;

public class PlayAudioMemoFragment extends SherlockListFragment implements
		OnClickListener {

	// The adapter for the files
	private MemoArrayAdapter mAdapter;

	// The button to record a new message
	private Button mNewMemoButton;

	// All the memo files
	private File[] mMemos;

	/* An interface to communicate with the underlying activity */
	public interface PlayAudioMemoFragmentListener {
		public void onCreateMemoCLicked();
	}

	private PlayAudioMemoFragmentListener mListener;

	@Override
	public void onAttach(Activity activity) {

		// Attempt to attach
		try {
			mListener = (PlayAudioMemoFragmentListener) activity;
		} catch (ClassCastException cce) {
			throw new ClassCastException(
					"Activity must implement PlayAudioMemoFragmentListener");
		}

		// Call super method
		super.onAttach(activity);
	}

	@Override
	public void onResume() {
		super.onResume();

		// Retrieve each file from storage
		mMemos = getMemos();

		// Set to empty list if nothing returned
		if (mMemos == null) {
			mMemos = new File[0];
		}

		// Set the adapter
		mAdapter = new MemoArrayAdapter(getActivity(),
				R.layout.play_audio_memo_file, mMemos);
		setListAdapter(mAdapter);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// Inflate the view
		View view = inflater.inflate(R.layout.play_audio_memo_fragment, null);

		mNewMemoButton = (Button) view.findViewById(R.id.create_memo_button);
		mNewMemoButton.setOnClickListener(this);

		return view;
	}

	/* Retrieves each memo */
	private File[] getMemos() {
		// Retrieve the filepath of memos
		File directory = getActivity().getDir("memos", Context.MODE_PRIVATE);

		return directory.listFiles();

	}

	public class MemoArrayAdapter extends ArrayAdapter<File> {

		public MemoArrayAdapter(Context context, int textViewResourceId,
				File[] objects) {
			super(context, textViewResourceId, objects);
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.create_memo_button:
			mListener.onCreateMemoCLicked();
			break;

		default:
			break;
		}

	}

}
