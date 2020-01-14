package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
 
public class server {
	// ������صĲ���,�˿�,�洢Socket���ӵļ���,ServerSocket����
	// �Լ��̳߳�
	private static final int PORT = 9090;//����˿�
	private List<Socket> mList = new ArrayList<Socket>();
	private ServerSocket server = null;
	private ExecutorService myExecutorService = null;
 
	public static void main(String[] args) {
		new server();
	}
 
	public server() {
		try {
			server = new ServerSocket(PORT);
			// �����̳߳�
			myExecutorService = Executors.newCachedThreadPool();
			System.out.println("�����������...\n");
			Socket client = null;
			while (true) {
				client = server.accept();
				mList.add(client);
				myExecutorService.execute(new Service(client));
			}
 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
	class Service implements Runnable {
		private Socket socket;
		private BufferedReader in = null;
		private String msg =null;
 
		public Service(Socket socket) {
			this.socket = socket;
			try {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				msg = "�û�:" + this.socket.getInetAddress() + "~������������," + "��ǰ��������:" + mList.size();
				this.sendmsg();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
 
		public void run() {
			try {
				while (true) {
					if ((msg = in.readLine()) != null) {
						if (msg.equals("bye")) {
							System.out.println("-------------------");
							mList.remove(socket);
							in.close();
							msg = "�û�:" + socket.getInetAddress() + "�˳�:" + "��ǰ��������:" + mList.size();
							socket.close();
							this.sendmsg();
							break;
						} else {
							msg = socket.getInetAddress() + "   ˵: " + msg;
							this.sendmsg();
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
 
		// Ϊ�����Ϸ���˵�ÿ���ͻ��˷�����Ϣ
		public void sendmsg() {
			System.out.println(msg);
			int num = mList.size();
			for (int index = 0; index < num; index++) {
				Socket mSocket = mList.get(index);
				PrintWriter pout = null;
				try {
					pout = new PrintWriter(
							new BufferedWriter(new OutputStreamWriter(mSocket.getOutputStream(), "UTF-8")), true);
					pout.println(msg);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
 
	}
}
