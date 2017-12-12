package com.work.util;

import org.apache.commons.httpclient.methods.PostMethod;

public class EncodePostMethod extends PostMethod {


	@Override
	public String getRequestCharSet() {
		// TODO Auto-generated method stub
		return "UTF-8";
	}

	public EncodePostMethod(String url){
		super(url);
	}

}
