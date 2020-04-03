/**
 * @author Pawłowicz Jakub S18688
 */

package zad1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Currency;
import java.util.Locale;

import org.json.JSONObject;

public class Service{
	public String code, currency, city;

	public Service(String country){
		Locale.setDefault(Locale.ENGLISH);
		try{
			Locale locale=Arrays.stream(Locale.getISOCountries()).map((name)->new Locale("",name))
					.filter((loc)->loc.getDisplayCountry().equals(country)).findFirst().get();
			this.code=locale.getCountry();
			this.currency=Currency.getInstance(locale).toString();
		}catch(Exception e){
			System.err.println("Bad Country Name");
			System.exit(1);
		}
	}

	public String getWeather(String city){
		try{
			this.city=city;
			String all=JsonToString(new URL("https://api.openweathermap.org/data/2.5/weather?q="+city+","+this.code+"&appid=c19989cd8c67fcc28b5812fc46ffce4e"));
			String description=new JSONObject(all).getJSONArray("weather").getJSONObject(0).getString("description");
			String temperature=String.valueOf(new JSONObject(all).getJSONObject("main").getDouble("temp")-272.15);
			return "Description: "+description+", Temperature: "+temperature;
		}catch(IOException e){
			System.err.println("Bad city");
		}
		return null;
	}

	public Double getRateFor(String currency){
		if(this.currency.equals(currency)) return 1.0;
		try{
			String all=JsonToString(new URL("https://api.exchangeratesapi.io/latest?base="+currency+"&symbols="+this.currency));
			return new JSONObject(all).getJSONObject("rates").getDouble(this.currency);
		}catch(IOException e){
			System.err.println("Bad currency");
		}
		return 0.0;
	}

	public Double getNBPRate(){
		if(this.code.toLowerCase().equals("pl")) return 1.0;
		try{
			String all=JsonToString(new URL("http://api.nbp.pl/api/exchangerates/rates/a/"+this.currency+"/?format=json"));
			return new JSONObject(all).getJSONArray("rates").getJSONObject(0).getDouble("mid");
		}catch(IOException e){
			System.err.println(e);
		}
		return 0.0;
	}

	String JsonToString(URL url) throws IOException{
		StringBuilder result=new StringBuilder();
		BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(url.openStream(),StandardCharsets.UTF_8));
		String line;
		while((line=bufferedReader.readLine())!=null) result.append(line);
		return result.toString();
	}
}
