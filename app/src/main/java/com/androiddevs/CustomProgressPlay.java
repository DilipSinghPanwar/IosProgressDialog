package com.androiddevs;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

public class CustomProgressPlay extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void showSimpleIndeterminate(View v) {
    	TimeConsumingTask t = new TimeConsumingTask();
    	t.execute();
    }
    
    
    public class TimeConsumingTask extends AsyncTask<Void, String, Void> implements OnCancelListener {
    	CustomProgress mCustomProgress;

    	@Override
    	protected void onPreExecute() {
        	mCustomProgress = CustomProgress.show(CustomProgressPlay.this,"Connecting", true,true,this);
    		super.onPreExecute();
    	}
    	
		@Override
		protected Void doInBackground(Void... params) {
			try {
				publishProgress("Connecting");
				Thread.sleep(2000);
				publishProgress("Downloading");
				Thread.sleep(5000);
				publishProgress("Done");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
		}
    	
		@Override
		protected void onProgressUpdate(String... values) {
			mCustomProgress.setMessage(values[0]);
			super.onProgressUpdate(values);
		}
		
		@Override
		protected void onPostExecute(Void result) {
			mCustomProgress.dismiss();
			super.onPostExecute(result);
		}

		@Override
		public void onCancel(DialogInterface dialog) {
			this.cancel(true);
			mCustomProgress.dismiss();
		}		
    }
    
}
