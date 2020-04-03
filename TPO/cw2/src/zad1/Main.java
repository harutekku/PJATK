/**
 * @author Pawłowicz Jakub S18688
 */

package zad1;


import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;

import javax.swing.*;
import java.awt.*;

public class Main{
	public static void main(String[] args){
		Service s=new Service("Poland");
		String weatherJson=s.getWeather("Warsaw");
		Double rate1=s.getRateFor("USD");
		Double rate2=s.getNBPRate();

		JFrame jFrame=new JFrame("App");
		JPanel jPanel1=new JPanel(), jPanel2=new JPanel(new GridLayout(3,1));
		JTextArea jTextArea1=new JTextArea("Weather: "+weatherJson), jTextArea2=new JTextArea("Rate: "+rate1.toString()), jTextArea3=new JTextArea("NBP rate:"+rate2.toString());
		jTextArea1.setEditable(false);
		jTextArea2.setEditable(false);
		jTextArea3.setEditable(false);
		JFXPanel jfxPanel=new JFXPanel();

		jPanel2.add(jTextArea1,0);
		jPanel2.add(jTextArea2,1);
		jPanel2.add(jTextArea3,2);
		jPanel1.add(jPanel2);
		jPanel1.add(jfxPanel);
		jFrame.add(jPanel1);

		Platform.runLater(()->{
			WebView webView=new WebView();
			webView.getEngine().load("https://wikipedia.org/wiki/"+s.city);
			jfxPanel.setScene(new Scene(webView));
		});

		jFrame.setSize(1000,800);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
	}
}
