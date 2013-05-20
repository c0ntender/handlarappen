package com.alexjenny.handlarappen;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.api.services.shoppinglist.model.Category;

/**
 * The Main Activity.
 * 
 * This activity starts up the RegisterActivity immediately, which communicates
 * with your App Engine backend using Cloud Endpoints. It also receives push
 * notifications from backend via Google Cloud Messaging (GCM).
 * 
 * Check out RegisterActivity.java for more details.
 */
public class MainActivity extends Activity {

	private Button mAdd;
	private Button mRemove;
	private Button mList;
	private EditText mEditText;
	private OnClickListener mOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.add_category: {
				final String category = mEditText.getText().toString();
				mEditText.setText("");
				if (!TextUtils.isEmpty(category)) {
					new Thread(new Runnable() {

						@Override
						public void run() {

							Category c = new Category();
							c.setName(category);
							String result = new HandlarAppenBackend()
									.addCategory(c) ? "Added " + category
									: "Failed to add" + category;
							showToast(result);
						}

					}).start();
				}
			}
				break;
			case R.id.remove_category: {
				final String category = mEditText.getText().toString();
				mEditText.setText("");
				if (!TextUtils.isEmpty(category)) {
					new Thread(new Runnable() {
						
						@Override
						public void run() {

							Category c = new Category();
							c.setName(category);
							String result = new HandlarAppenBackend()
									.removeCategory(c) ? "Removed " + category
									: "Failed to remove" + category;
							showToast(result);
						}

					}).start();
				}
			}

				break;
			case R.id.list_category:
				new Thread(new Runnable() {

					@Override
					public void run() {
						List<Category> categories = new HandlarAppenBackend()
								.getCategories();
						StringBuilder toastMsg = new StringBuilder(
								"Categories:\n");
						for (Category c : categories) {
							toastMsg.append(c.getName()+"\n");
						}
						showToast(toastMsg.toString());
					}

				}).start();

				break;

			}

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mAdd = (Button) findViewById(R.id.add_category);
		mRemove = (Button) findViewById(R.id.remove_category);
		mList = (Button) findViewById(R.id.list_category);
		mAdd.setOnClickListener(mOnClickListener);
		mRemove.setOnClickListener(mOnClickListener);
		mList.setOnClickListener(mOnClickListener);
		mEditText = (EditText) findViewById(R.id.editText);

		// Start up RegisterActivity right away
		// Intent intent = new Intent(this, RegisterActivity.class);
		// startActivity(intent);
		// Since this is just a wrapper to start the main activity,
		// finish it after launching RegisterActivity
		// finish();
	}

	private void showToast(final String toastMsg) {
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				Toast.makeText(MainActivity.this, toastMsg, Toast.LENGTH_LONG)
						.show();

			}
		});

	}
}
