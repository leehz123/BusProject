package park.frame;

import java.awt.Color;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import an.userinfo.Info_MainFrame;
import hong.BeforeButton;
import hong.SaveInfo;
import hong.selectseat.SelectSeatMainFrame;
import lee.RoundedButton;
import park.button.BeforePayButton;
import park.button.HomeButton;
import park.button.PayButton;
import park.combobox.SelectPayWayBox;
import park.database.LoadRVID;
import park.database.SaveDB;
import park.event.BeforeButtonEvent;
import park.event.HomeButtonEvent;
import park.event.PayButtonEvent;
import park.label.RouteInfoLabel;
import park.panel.HomeBeforeBtnPanel;
import park.panel.RouteInfoPanel;
import park.panel.SelectSeatAgePanel;


public class Pay extends JFrame{
	// ���̾ƿ� �η� ����� ���������� flow, table, label, combobox, button 
	static SaveInfo user;
	
	//static HomeBeforeBtnPanel panel1 ; // Ȩ��ư, ������ư�� ���� �ǳ� (�÷ο췹�̾ƿ�)
	private HomeButton homeBtn;
	private BeforePayButton beforeBtn;
	private SelectSeatAgePanel seatPanel ; // �¼��� ���ɴ� �� �޺��ڽ� ����ִ� �ǳ�
	//private PayButton payBtn ; // �����ϱ� ��ư
	private RoundedButton payBtn ; // �����ϱ� ��ư
	private SelectPayWayBox payWayBox ; // ���� ��� ���� �޺��ڽ�
	private RouteInfoLabel route; // �༱�� ���� 
	private RouteInfoPanel routePanel ; // �༱�� ���� ǥ ���·� ������ �г�
	private ImageIcon background = new ImageIcon("image/mp������1.png");
	private JLabel label = new JLabel();
	
	
	public void thisClose() {
		dispose();
	}
	
	
	public Pay(SaveInfo user) {
		super("���� ȭ��");
		user.setSeatId(); // �ؽ��ʿ� �ִ� �¼��̸� ��̸���Ʈ�� �ֱ�
		user.setSeatNames();
		this.user = user;
		
		// ���� ���� �����ϱ����ؼ� �� �Ű������� �޾ƿ� user�� �ʱ�ȭ ����
		homeBtn = new HomeButton(new ImageIcon("image/home2.png"));
		beforeBtn = new BeforePayButton(new ImageIcon("image/before1.png"));
		//panel1 = new HomeBeforeBtnPanel(user);
		route = new RouteInfoLabel();

		routePanel = new RouteInfoPanel(user);
		seatPanel = new SelectSeatAgePanel(user); // �г� �ʱ�ȭ ���ָ鼭 user ���� ��� �����ؾ��ؼ� �Ű������� �Ѱ��ش�
		payWayBox = new SelectPayWayBox(user);
		payBtn = new RoundedButton("�����ϱ�");
		payBtn.setBounds(190,500,90,40);
		

		
		
		// �׼ǵ�
		homeBtn.addActionListener(new HomeButtonEvent(this));
		beforeBtn.addActionListener(new BeforeButtonEvent(this));
		payBtn.addActionListener(new PayButtonEvent(this));

		
		label.setIcon(background);
		label.setSize(500,600);
		add(label);
	
		//label.add(panel1); // Ȩ��ư, ������ư �ǳ�
		label.add(homeBtn);
		label.add(beforeBtn);
		label.add(route); // �༱�� ����
		label.add(routePanel); // �༱�� ���� ǥ ǥ���� �ǳ�
		label.add(seatPanel); // �¼��� ���� ���� �޺��ڽ�
		label.add(payWayBox); // ���� ���� �� �޺� �ڽ�
		label.add(payBtn); // �����ϱ� ��ư
	

		getContentPane().setBackground(Color.white);
		setLayout(null);
		setResizable(false);
		setSize(500,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void homeButtonEvent() {
		String str = "���� ȭ������ ���ư��ðڽ��ϱ�?";
		int ok =JOptionPane.showConfirmDialog(null, str, "���� ȭ��", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
		if(ok==JOptionPane.OK_OPTION) { // ok�� ������
			user.remove_bs_map(user.getSeatId(), user.getSeatNames());
			user.setTotalCharge();
			user.setPayWay(null);
			dispose();
			new Info_MainFrame(user);
		}
	}
	
	public void beforeButtonEvent() {
		user.remove_bs_map(user.getSeatId(), user.getSeatNames());
		user.setTotalCharge();
		user.setPayWay(null);
		dispose();
		new SelectSeatMainFrame(user);
	}
	
	public void payButtonEvent() {
		try {
			//if((!user.getPayWay().equals(null))&&user.isTotalChargeCheck()) {
			if((user.getdcBySeat().size() == user.getSeatNameBySeatId().size())&&(!user.getPayWay().equals(null))) {
				String str =String.format("���� �ݾ� : %d\n���� ���� : %s\n���� �Ͻðڽ��ϱ�?", (int)(user.getTotalCharge()), user.getPayWay());
				int ok =JOptionPane.showConfirmDialog(null, str, "���� Ȯ��", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if(ok==JOptionPane.OK_OPTION) { // ok�� ������
					new SaveDB(user); // ��� �����Ѵ�
					new LoadRVID(user); // ��� ����� ���Ź�ȣ�� �ν��Ͻ��� ������
					dispose();
					new PayInfo(user); // ���� ��ư ������ ���� ȭ������ �Ѿ��
					System.out.println("db���� ����");
				}
				else { // ok �̿��� ���� ������
					user.setTotalCharge(); // ���� �˾����� ����� ���̹Ƿ� �ѿ���� �ʱ�ȭ ���ش�
				}
			}else {
				String str ="�ùٸ��� ���� ���� �� ���� ����Դϴ�.";
				JOptionPane.showMessageDialog(null, str, "���� �޽���", JOptionPane.PLAIN_MESSAGE);
			}
		}catch(NullPointerException e1){
			String str ="�ùٸ��� ���� ���� �� ���� ����Դϴ�.";
			JOptionPane.showMessageDialog(null, str, "���� �޽���", JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	
	public static void main(String[] args) {
		new Pay(new SaveInfo());
	}

}
