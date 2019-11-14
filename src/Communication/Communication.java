package Communication;

import GUI.Client;
import com.mysql.cj.protocol.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author paulalan
 * @create 2019/11/13 20:32
 */
public class Communication implements Runnable
{
	private Client client = Client.getInstance();
	private String hostname;
	private int port;
	private Socket s;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	Question questionDetail=new Question();

	public Communication(String hostname, int port)
	{
		this.hostname = hostname;
		this.port = port;
	}

	@Override
	public void run()
	{

		try
		{
			s = new Socket(hostname, port);
			System.out.println("Socket creates successfully!");
			oos = new ObjectOutputStream(s.getOutputStream());
			System.out.println("oos creates successfully!");

			connect();

			while (s.isConnected())
			{
				ois = new ObjectInputStream(s.getInputStream());
				questionDetail=(Question) ois.readObject();
				System.out.println(questionDetail.toString());

				System.out.println("id = " + questionDetail.getId() + "\nquestion = " + questionDetail.getQuestion() +
						"\nanswer = " + questionDetail.getAnswer() + "\ntype = " + questionDetail.getType() +
						"\nanswerNum = " + questionDetail.getAnswerNum());
			}
		} catch (IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}

	}

	public void connect()
	{
		//create message class
		Question questionDetail = new Question();
		questionDetail.setFlag("acquire");
		//send
		send(questionDetail);
	}

	public void send(Question question)
	{
		try
		{
			oos.writeObject(question);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public Question getQuestion(){
		return questionDetail;
	}
}
