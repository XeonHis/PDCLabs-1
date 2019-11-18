package GUI;

import Communication.Communication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author paulalan
 * @create 2019/11/10 18:07
 */
public class Client
{
	private static Client instance;
	private Communication communication;
	private static Map<Integer, String> nameRelateAnswer = new HashMap<>()
	{{
		put(0, "A");
		put(1, "B");
		put(2, "C");
		put(3, "D");
		put(4, "E");
		put(5, "F");
		put(6, "G");
	}};

	public Client()
	{
		instance = this;
	}

	public static Client getInstance()
	{
		return instance;
	}

	public void setCommunication(Communication communication)
	{
		this.communication = communication;
	}

	public static void main(String[] args)
	{
		Communication communication = new Communication("localhost", 11111);
		new Thread(communication).start();

		JFrame client = new JFrame("Client");
		client.setLayout(new BorderLayout());
		client.setVisible(true);
		client.setSize(900, 600);
		client.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		JLabel timeShow = new JLabel();


		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout());

		JTextArea questionArea = new JTextArea();
		generateQuestion(communication, buttonPanel, questionArea);
//		generateQuestion(communication, buttonPanel, questionArea);

//		JButton refresh=new JButton("Refresh");
//		refresh.addMouseListener(new MouseAdapter()
//		{
//			@Override
//			public void mouseClicked(MouseEvent e)
//			{
//				communication.acquireQuestion();
//				generateQuestion(communication, buttonPanel, questionArea);
//			}
//		});
//		client.add(refresh,BorderLayout.NORTH);
		client.add(buttonPanel, BorderLayout.SOUTH);
		client.add(questionArea, BorderLayout.CENTER);
	}

	private static void generateQuestion(Communication communication, JPanel buttonPanel, JTextArea questionArea)
	{
		questionArea.setText(communication.getQuestion().getQuestion());
//		questionArea.setEditable(false);

		for (int i = 0; i < communication.getQuestion().getAnswerNum(); i++)
		{
			buttonPanel.add(new JButton(nameRelateAnswer.get(i)));
		}
	}


}
