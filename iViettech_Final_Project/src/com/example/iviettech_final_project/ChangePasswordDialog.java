package com.example.iviettech_final_project;

import android.app.Dialog;
import android.content.Context;

public class ChangePasswordDialog extends Dialog {
	Context context;
	public ChangePasswordDialog(Context context) {
		super(context);
		this.context = context;
		setTitle("Change Password");
		setContentView(R.layout.change_password);
	}

}
