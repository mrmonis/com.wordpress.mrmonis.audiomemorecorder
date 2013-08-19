package com.wordpress.mrmonis.audiomemorecorder;

import java.io.IOException;

import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
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

	// Name of the recording
	private String mFileName;

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

		mFileName = Environment.getExternalStorageDirectory().getAbsolutePath()
				+ "/memo1.mp4";

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
		mRecorder = new MediaRecorder();
		mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
		mRecorder.setOutputFile(mFileName);
		mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_WB);

		try {
			mRecorder.prepare();
		} catch (IOException e) {
			Log.e(TAG, "Could not prepare recorder");
		}

		mRecorder.start();
		mIsRecording = true;

	}

	private void stopRecording() {
		mRecorder.stop();
		mRecorder.release();
		mRecorder = null;
		mIsRecording = false;
	}

}
