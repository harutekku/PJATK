package zad1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TravelData{
	private List<String[]> records;
	public TravelData(File dataDir){
		records=new ArrayList<>();
		File[] files=dataDir.listFiles();
		for(File file:files){
			try{
				Files.lines(file.toPath()).forEach(line->{
					String[] data=line.split("\t");
					records.add(data);
				});
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		for(String[] s:records){
			for(String t:s){
				System.out.print(t+" ");
			}
			System.out.println();
		}
	}

	public List<String> getOffersDescriptionsList(String loc, String dateFormat){
		Locale locale = Locale.forLanguageTag(loc.replace("_", "-"));
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
		NumberFormat numberFormat = NumberFormat.getInstance(locale);
		records.forEach(record->{
			StringBuilder line=new StringBuilder();
		});
		return null;
	}
}
