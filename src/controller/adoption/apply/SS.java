package controller.adoption.apply;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SS {
	public static void main(String[] args) {
		BufferedReader br = null;
		try {
			String urlstr = "http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic?bgnde=20180101&endde=20181030&pageNo=2&numOfRows=10&ServiceKey=cOxNjTFSNpe%2Bzduu3wvca3a%2F5O7nWgtqy0M%2FBQD6s6oMIMLL0bFMiFfHKhf9aCuYqMvYJ78h1GJUKZTA7z8l0g%3D%3D";

			URL url = new URL(urlstr);
			HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
			urlconnection.setRequestMethod("GET");
			br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
			String result = "";
			String line;
			while ((line = br.readLine()) != null) {
				result = result + line + "\n";
			}
			System.out.println(result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
