package dani;

//https://openweathermap.org/api

import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.kim.http.client.DaniHttpClient;

// JSON (JavaScript Object Notation)
//			DB에 있는 데이터를 JavaScript형태로 표현한것.
//			XML 보다도 훨씬 좋다 압도적.. XML 밤새 외웠는데 ...하..

//			JVAA 배열 : {1,2,3}
//			JS배열 : [1,2,3]

//			JAVA 객체 :Dog d = new Dog();
//						d.getname("마루");
//						d.getAge(3);
//			JS	객체	:{name:"마루,age:3}
public class Json_01 {
	public static void main(String[] args) {

		// 5e6d6e938c64cabcecd3e680a2352ee1
		// &units=metric&lang=kr

		// https://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
		try {

			Scanner k = new Scanner(System.in);
			System.out.println("City : ");
			String cityname = k.next();
			cityname = URLEncoder.encode(cityname, "UTF-8");

			String address = "https://api.openweathermap.org/data/2.5/weather";
			address += "?q=" + cityname;
			address += "&appid=5e6d6e938c64cabcecd3e680a2352ee1"; // 키비밀번호
			address += "&units=metric"; // 화씨를 섭씨 온도로 변환 요청
			address += "&lang=kr"; // 한국어로변경 요청

			InputStream is = DaniHttpClient.download(address);
			String str = DaniHttpClient.convert(is, "UTF-8");
//			System.out.println(str);
			JSONParser jp = new JSONParser(); // 파싱하려면 객체가 하나 필요.
//			배열은 [] 대괄호 사용.
//			JSONArray js = (JSONArray) jp.parse(str);	//jp.parse(str) 한다음 형변환
//			System.out.println(ja);

//			rorcpsms {} 사용.
			JSONObject jo = (JSONObject) jp.parse(str); // jp.parse(str) 한다음 형변환.
//			System.out.println(jo); //출력하면 처음만나는 전체객체 {}안에 모든 내용이들어왔다.

//			나라를 출력하려고보니 전체객체 {} 안에 sys객체 안에 country 가 있다. 찾아가보자
//			JSONObject sys = (JSONObject) jo.get("sys");
//			String country = (String) sys.get("country");
//			System.out.println(country);

//			JSONArray ja2 = (JSONArray) jo.get("weather");
//			JSONObject jo2 = (JSONObject) ja2.get(0);
//			String description = (String) jo2.get("description");
//			System.out.println(description);

			JSONObject ob = (JSONObject) jo.get("main");
			double temp = (double) ob.get("temp");
			System.out.println(temp);
			double feelsLike = (double) ob.get("feels_like");
			System.out.println(feelsLike);
			double tempMin = (double) ob.get("temp_min");
			System.out.println(tempMin);
			double tempMax = (double) ob.get("temp_max");
			System.out.println(tempMax);
			long pressure = (long) ob.get("pressure");
			System.out.println(pressure);
			long humidity = (long) ob.get("humidity");
			System.out.println(humidity);
//			System.out.println("기온"+temp+"\n"+"체감온도"+feelsLike+"\n"+"최저기온"+tempMin+"\n"+"최고기온"+tempMax+"\n"+"기압"+pressure+"\n"+"습도"+humidity);

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

	}
}
