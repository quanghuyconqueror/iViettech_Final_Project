package com.example.iviettech_final_project;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;

public class RegisterDialog extends Dialog {

	public RegisterDialog(Context context) {
		super(context);
		setTitle("Guest Sign On");
		setContentView(R.layout.register_dialog);
		
		Button submitButton = (Button) findViewById(R.id.bt_register_ok);
		Button cancelButton = (Button) findViewById(R.id.bt_register_cancel);
		
		cancelButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				dismiss();		
			}
		});
		
		submitButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				dismiss();
				
			}
		});
	}

}
