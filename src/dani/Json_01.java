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
//			DB�� �ִ� �����͸� JavaScript���·� ǥ���Ѱ�.
//			XML ���ٵ� �ξ� ���� �е���.. XML ��� �ܿ��µ� ...��..

//			JVAA �迭 : {1,2,3}
//			JS�迭 : [1,2,3]

//			JAVA ��ü :Dog d = new Dog();
//						d.getname("����");
//						d.getAge(3);
//			JS	��ü	:{name:"����,age:3}
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
			address += "&appid=5e6d6e938c64cabcecd3e680a2352ee1"; // Ű��й�ȣ
			address += "&units=metric"; // ȭ���� ���� �µ��� ��ȯ ��û
			address += "&lang=kr"; // �ѱ���κ��� ��û

			InputStream is = DaniHttpClient.download(address);
			String str = DaniHttpClient.convert(is, "UTF-8");
//			System.out.println(str);
			JSONParser jp = new JSONParser(); // �Ľ��Ϸ��� ��ü�� �ϳ� �ʿ�.
//			�迭�� [] ���ȣ ���.
//			JSONArray js = (JSONArray) jp.parse(str);	//jp.parse(str) �Ѵ��� ����ȯ
//			System.out.println(ja);

//			rorcpsms {} ���.
			JSONObject jo = (JSONObject) jp.parse(str); // jp.parse(str) �Ѵ��� ����ȯ.
//			System.out.println(jo); //����ϸ� ó�������� ��ü��ü {}�ȿ� ��� �����̵��Դ�.

//			���� ����Ϸ����� ��ü��ü {} �ȿ� sys��ü �ȿ� country �� �ִ�. ã�ư�����
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
//			System.out.println("���"+temp+"\n"+"ü���µ�"+feelsLike+"\n"+"�������"+tempMin+"\n"+"�ְ���"+tempMax+"\n"+"���"+pressure+"\n"+"����"+humidity);

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

	}
}
