package com.my.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Download {
	public Download(String path, OutputStream os) throws IOException {
		//파일 읽기
		FileInputStream fis = null;
		fis = new FileInputStream(path);
		//파일 내용 응답하기
		//PrintWriter out = response.getWriter();
//				int readValue = -1;
//				while( (readValue = fis.read()) != -1 ) {
//					os.write(readValue);
//				}
		
		byte[]bArr = new byte[1024];
		int readCnt = -1; //읽은 바이트수
		while( (readCnt = fis.read(bArr)) != -1) {
			os.write(bArr, 0, readCnt);
		}
		fis.close();
		os.close();
	}
}
