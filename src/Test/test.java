package Test;

import Communication.Question;

import java.io.*;
import java.net.Socket;

/**
 * @author paulalan
 * @create 2019/11/9 22:48
 */
public class test
{
	public static void main(String[] args) throws IOException, ClassNotFoundException
	{
		ObjectInputStream ois;
//		PrintWriter pw;
		ObjectOutputStream oos;
		Question questionDetail=new Question();

		Socket socket = new Socket("localhost", 11111);
//		pw=new PrintWriter(socket.getOutputStream());
//		bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//		pw.write("acquire");

		oos=new ObjectOutputStream(socket.getOutputStream());
		ois = new ObjectInputStream(socket.getInputStream());
		questionDetail.setFlag("acquire");
		oos.writeObject(questionDetail);

		Question question = (Question) ois.readObject();

		System.out.println("id = " + question.getId() + "\nquestion = " + question.getQuestion() +
				"\nanswer = " + question.getAnswer() + "\ntype = " + question.getType() +
				"\nanswerNum = " + question.getAnswerNum());
//		pw.close();
		oos.close();
		ois.close();

	}
}
