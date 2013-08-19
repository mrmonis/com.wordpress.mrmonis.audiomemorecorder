package com.wordpress.mrmonis.audiomemorecorder;

import java.io.File;
import java.io.IOException;

import android.content.Context;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.actionbarsherlock.app.SherlockFragment;

/**
 * Records an audio memo.
 * 
 * @author Benjamin
 * 
 */
public class RecordAudioMemoFragment extends SherlockFragment implements
		OnClickListener {

	private static final String TAG = "RecordAudioMemoFragment";

	// Used for recording
	private MediaRecorder mRecorder;

	// Flag for recording state
	private boolean mIsRecording;

	// Controls recording
	private Button mRecordButton;

	/* Called when the activities view is created. Attach XML elements to code */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.record_audio_memo_fragment, null);

		mRecordButton = (Button) view
				.findViewById(R.id.record_audio_memo_button);

		mRecordButton.setOnClickListener(this);

		return view;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.record_audio_memo_button:
			if (mIsRecording) {
				stopRecording();
			} else {
				startRecording();
			}
			break;

		default:
			break;
		}

	}

	private void startRecording() {
		// Get the current time and date
		Time now = new Time(Time.getCurrentTimezone());
		now.setToNow();
		String time = String.valueOf(now.year) + now.month + now.monthDay + now.hour
				+ now.minute + now.second;

		// Set directory and filename
		File dir = getActivity().getDir("memos", Context.MODE_PRIVATE);
		File memoFile = new File(dir, time + ".mp4");

		// Set up the recorder
		mRecorder = new MediaRecorder();
		mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
		mRecorder.setOutputFile(memoFile.getAbsolutePath());
		mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_WB);

		try {
			mRecorder.prepare();
		} catch (IOException e) {
			Log.e(TAG, "Could not prepare recorder");
		}

		// Change the buttons text
		mRecordButton.setText("Stop Recording");

		// Start recording
		mRecorder.start();
		mIsRecording = true;

	}

	private void stopRecording() {
		// Change record button text
		mRecordButton.setText("Record");

		// Stop recording
		mRecorder.stop();
		mRecorder.release();
		mRecorder = null;
		mIsRecording = false;
	}

}
