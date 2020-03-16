package zad1;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.StandardOpenOption.*;

public class Futil extends SimpleFileVisitor<Path>{
	public FileChannel in, out;
	public Charset inCharset=Charset.forName("CP1250"),
			outCharset=Charset.forName("UTF-8");

	public static void processDir(String dirName,String resultFileName){
		try{
			Path path=Paths.get(dirName);
			Futil futil=new Futil();
			futil.out=FileChannel.open(Paths.get(resultFileName),CREATE,TRUNCATE_EXISTING,WRITE);
			Files.walkFileTree(path,futil);
			futil.out.close();

		}catch(IOException e){
			e.printStackTrace();
		}
	}

	@Override
	public FileVisitResult visitFile(Path file,BasicFileAttributes attributes){
		try{
			in=FileChannel.open(file,StandardOpenOption.READ);
			ByteBuffer byteBuffer = ByteBuffer.allocate((int)in.size());
			in.read(byteBuffer);
			byteBuffer.flip();
			CharBuffer cbuf = inCharset.decode(byteBuffer);
			byteBuffer = outCharset.encode(cbuf);
			out.write(byteBuffer);
			in.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		return FileVisitResult.CONTINUE;
	}
}
