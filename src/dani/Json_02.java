package dani;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.kim.http.client.DaniHttpClient;

public class Json_02 {
public static void main(String[] args) {
		
		try {
			
			Scanner k = new Scanner(System.in);
			System.out.println("City : ");
			String cityname = k.next();
			cityname = URLEncoder.encode(cityname,"UTF-8");
			
			String address = "https://api.openweathermap.org/data/2.5/weather";
			address += "?q=" + cityname;
			address += "&appid=5e6d6e938c64cabcecd3e680a2352ee1";  //키비밀번호
			address += "&units=metric";		//화씨를 섭씨 온도로 변환 요청
			address	+= "&lang=kr";			//한국어로변경 요청
			
			InputStream is = DaniHttpClient.download(address);
			String str = DaniHttpClient.convert(is, "UTF-8");
//			System.out.println(str);
			
			JSONParser jp = new JSONParser(); // 파싱하려면 객체가 하나 필요.
			JSONObject jo = (JSONObject) jp.parse(str);  //jp.parse(str) 한다음 형변환.
		
			JSONObject ob = (JSONObject) jo.get("main");
			double temp = (double) ob.get("temp");
			System.out.println(temp);
			double feelsLike = (double) ob.get("feels_like");
			System.out.println(feelsLike);;;
		
	} catch (Exception e) {
		// TODO: handle exception
	}
		
	}
}
