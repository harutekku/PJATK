package zad1;

import javax.swing.*;
import javax.naming.*;
import javax.jms.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

public class Program extends JFrame{
	public static void main(String[] args){
		Program program1=new Program("topic1","user1");
		Program program2=new Program("topic1","user2");
	}

	public Program(String admTopicName,String userName){
		try{
			Hashtable<String,String> env=new Hashtable<>();
			env.put(Context.INITIAL_CONTEXT_FACTORY,"org.exolab.jms.jndi.InitialContextFactory");
			env.put(Context.PROVIDER_URL,"tcp://localhost:3035");
			env.put(Context.SECURITY_PRINCIPAL,"admin");
			env.put(Context.SECURITY_CREDENTIALS,"openjds");
			Context ctx=new InitialContext(env);
			ConnectionFactory factory=(ConnectionFactory)ctx.lookup("ConnectionFactory");
			Connection con=factory.createConnection();
			Session ses=con.createSession(false,Session.AUTO_ACKNOWLEDGE);
			Topic topic=(Topic)ctx.lookup(admTopicName);
			MessageConsumer messageConsumer=ses.createDurableSubscriber(topic,userName);
			JTextArea textArea=new JTextArea();
			textArea.setFocusable(false);
			messageConsumer.setMessageListener(message->{
						try{
							textArea.append(((TextMessage)message).getText()+"\n");
						}catch(JMSException e){
							e.printStackTrace();
						}
					}
			);
			JTextField input=new JTextField();
			MessageProducer messageProducer=ses.createProducer(topic);
			TextMessage msg=ses.createTextMessage();
			input.addActionListener(e->{
				try{
					msg.setText(new SimpleDateFormat("HH:mm:ss").format(new Date())+" - "+userName+": "+input.getText());
					messageProducer.send(msg);
					input.setText("");
				}catch(JMSException ignore){
				}
			});
			addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e){
					try{
						con.close();
					}catch(Exception ignored){
					}
					dispose();
					System.exit(0);
				}
			});
			add(new JPanel(){
				{
					setLayout(new BorderLayout());
					add(new JScrollPane(textArea),BorderLayout.CENTER);
					add(input,BorderLayout.SOUTH);
					pack();
				}
			});
			setTitle(userName);
			pack();
			setSize(300,200);
			setVisible(true);
			con.start();
		}catch(Exception exc){
			exc.printStackTrace();
			System.exit(1);
		}
	}
}
