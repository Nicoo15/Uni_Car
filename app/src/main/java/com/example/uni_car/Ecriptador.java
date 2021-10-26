package com.example.uni_car;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Ecriptador {

	@RequiresApi(api = Build.VERSION_CODES.O)
	public static String hasearPwd(String pass) {
		byte[] pwd = pass.getBytes();
		String pwdhash = "";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(pwd);
			byte[] resumen = md.digest();
			String mensaje = new String(resumen);
			pwdhash = Base64.getEncoder().encodeToString(resumen);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pwdhash;
	}

}
