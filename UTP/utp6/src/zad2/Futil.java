package zad2;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Futil{
	public static void processDir(String dirName,String resultFileName){


		try{
			FileOutputStream fos=new FileOutputStream(resultFileName);
			BufferedWriter out=new BufferedWriter(new OutputStreamWriter(fos,"UTF8"));

			Path path=Paths.get(dirName);
			Files.walkFileTree(path,new SimpleFileVisitor<Path>(){
				@Override
				public FileVisitResult visitFile(Path file,BasicFileAttributes attrs) throws IOException{
					FileInputStream fis=new FileInputStream(file.toFile());
					BufferedReader in=new BufferedReader(new InputStreamReader(fis,"Cp1250"));
					String line;
					while ((line = in.readLine()) != null) {
						out.write(line);
						out.newLine();
					}
					in.close();
					return FileVisitResult.CONTINUE;
				}
			});

			out.close();
		}catch(IOException e){
			System.err.println(e);
			System.exit(1);
		}
	}
}
