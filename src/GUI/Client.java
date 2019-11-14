package GUI;

import Communication.Communication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

/**
 * @author paulalan
 * @create 2019/11/10 18:07
 */
public class Client
{
	private static Client instance;
	private Communication communication;

	public Client()
	{
		instance = this;
	}

	public static Client getInstance()
	{
		return instance;
	}

	public void setCommunication(Communication communication){
		this.communication = communication;
	}

	public static void main(String[] args)
	{
		Communication communication=new Communication("localhost", 11111);
		new Thread(communication).start();

		JFrame client = new JFrame("Client");
		client.setLayout(new BorderLayout());
		client.setVisible(true);
		client.setSize(900, 600);
		client.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout());
		client.add(buttonPanel, BorderLayout.SOUTH);

		JTextArea questionArea = new JTextArea();
		questionArea.setText(communication.getQuestion().getQuestion());
		questionArea.setEditable(false);


		JButton refresh = new JButton("Refersh");
		refresh.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				super.mouseClicked(e);
			}
		});

		client.add(refresh, BorderLayout.NORTH);
		client.add(questionArea, BorderLayout.CENTER);
	}


}
