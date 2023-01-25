package hong.selectroute;

import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import an.OjdbcConnection;
import an.userinfo.Info_MainFrame;
import hong.BackGroundLabel;
import hong.BeforeButton;
import hong.NextButton;
import hong.SaveInfo;
import hong.selectroute.event.BeforeButtonEvent;
import hong.selectroute.event.DayComboBoxEvent;
import hong.selectroute.event.HomeButtonEvent;
import hong.selectroute.event.MonthComboBoxEvent;
import hong.selectroute.event.NextButtonEvent;
import hong.selectroute.event.SelectArrivalButtonEvent;
import hong.selectroute.event.SelectBusButtonEvent;
import hong.selectroute.event.SelectStartingButtonEvent;
import hong.selectroute.event.SerchButtonEvent;
import hong.selectroute.event.ShowArriveTerminalEvent;
import hong.selectroute.event.ShowStartingTerminalEvent;
import hong.selectroute.event.YearComboBoxEvent;
import hong.selectseat.SelectSeatMainFrame;
import lee.RoundedButton;

public class SelectRouteMainFrame extends JFrame {
	
	SaveInfo saveInfo;
	
	HomeButton homeButton = new HomeButton();				// Ȩ ��ư
	StartingPointButton startingPointButton = new StartingPointButton();	// ��� ��ư
	ArrivalButton arrivalButton = new ArrivalButton(); 	// ���� ��ư
	SerchButton serchButton = new SerchButton(); // ��ȸ�ϱ� ��ư
	NextButton nextButton = new NextButton();	// ���� ��ư
	BeforeButton beforeButton = new BeforeButton();
	
	YearComboBox yearBox = new YearComboBox();
	MonthComboBox monBox = new MonthComboBox();				// �� ���� �޺��ڽ�
	DayComboBox dayBox = new DayComboBox();					// �� ���� �޺��ڽ�
	
	SelectStartingButtonEvent startingBtnEvent = new SelectStartingButtonEvent(this); // ����� ���� ��ư �̺�Ʈ
	SelectArrivalButtonEvent arrivalBtnEvent = new SelectArrivalButtonEvent(this); // ������ ���� ��ư �̺�Ʈ
	HomeButtonEvent homeBtnEvent = new HomeButtonEvent(this); // Ȩ ��ư �̺�Ʈ
	ShowStartingTerminalEvent showStratingBtnEvent = new ShowStartingTerminalEvent(this); // ������� �����ִ� �̺�Ʈ.
	ShowArriveTerminalEvent showArrivalBtnEvent = new ShowArriveTerminalEvent(this); // �������� �����ִ� �̺�Ʈ.
	YearComboBoxEvent yearBoxEvent = new YearComboBoxEvent(this);
	MonthComboBoxEvent monthBoxEvent = new MonthComboBoxEvent(this); // �� ���� �޺��ڽ� �̺�Ʈ
	DayComboBoxEvent dayBoxEvent = new DayComboBoxEvent(this); // �� ���� �޺��ڽ� �̺�Ʈ
	SerchButtonEvent serchBtnEvent = new SerchButtonEvent(this); // ��ȸ�ϱ� ��ư �̺�Ʈ
	SelectBusButtonEvent selectBusBtnEvent = new SelectBusButtonEvent(this); // ���� �����ؼ� �����ϱ� ��ư �̺�Ʈ
	NextButtonEvent nextBtnEvent = new NextButtonEvent(this);	// ���� ��ư �̺�Ʈ
	BeforeButtonEvent beforeBtnEvent = new BeforeButtonEvent(this);
	private static int thisYear = Calendar.getInstance(Locale.KOREA).get(Calendar.YEAR);
	private static int thisMonth = (Calendar.getInstance(Locale.KOREA).get(Calendar.MONTH) + 1);
	private static int today = Calendar.getInstance(Locale.KOREA).get(Calendar.DATE);
	
	SelectSeatMainFrame seatMainFrame; // �¼����� ������
	SelectTerminalMainFrame stmFrame;	// �͹̳� ���� ������
	SelectBusFrame busFrame;	// ���� ���� ������
	
	private JLabel label = new JLabel();
	private ImageIcon background = new ImageIcon("image/mp������.png");
	
	private String startingPoint;
	private String arrivalPoint;
	private int year;
	private int month;
	private int day;
	private int rt_id;
	private int bi_id;
	private String bi_time;
	private Integer price;
	
	// �޿� ���� day���� �� ���� �޺��ڽ��� �߰��ϴ� �޼���
	public void addDays(int month) {
		dayBox.addItems(month);
	}
	
	// �� ���� �޺��ڽ��� ����ִ� �޼���
	public void resetDays() {
		dayBox.reset();
	}
		
	// ������� �����ϸ� ��ư �̸��� �������(ex:������) �ٲ��ش�
	public void departFrom(String starting) {
		startingPointButton.setTextStpBtn(starting);
	}
		
	// ������� �����ϸ� ������� �Է¹޴´�.
	public void setStartingPoint(String startingPoint) {
		this.startingPoint = startingPoint;
	}
	
	// �������� �����ϸ� ��ư �̸��� �������(ex:�λ�) �ٲ��ش�
	public void arriveAt(String destination) {
		arrivalButton.setTextArvBtn(destination);
		
	}
		
	// �������� �����ϸ� �������� �Է¹޴´�.
	public void setArrivalPoint(String arrivalPoint) {
		this.arrivalPoint = arrivalPoint;
	}
	
	// ����� ��ư�� ������ �͹̳ε��� �����ִ� �޼���. ������ �͹̳� ��ư�� �׼��� �߰����ش�.
	public void showStartingTerminals() {
		
		System.out.println(saveInfo.get_user_id());
		stmFrame = new SelectTerminalMainFrame();
		for (JButton btn : stmFrame.getAllBtns()) {
			btn.addActionListener(startingBtnEvent);
		}
	}
	
	// ������ ��ư�� ������ �͹̳ε��� �����ִ� �޼���. ������ �͹̳� ��ư�� �׼��� �߰����ش�.
	public void showArriveTerminals() {
		//System.out.println(saveInfo.get_user_id());
		stmFrame = new SelectTerminalMainFrame();
		for (JButton btn : stmFrame.getAllBtns()) {
			btn.addActionListener(arrivalBtnEvent);
		}
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	
	// �� �Է� �ޱ�
	public void setMonth(int month) {
		this.month = month;
	}
		
	// �� �Է� �ޱ�
	public void setDay(int day) {
		this.day = day;
	}
		
	// ������� �������� �����ϸ� �� â�� �ݾ��ش�.
	public void stmFlameClose() {
		stmFrame.dispose();
	}
	
	// �뼱, ��¥�� �´� �������� ������ �����ִ� �޼���
	public void showBus() {
		busFrame = new SelectBusFrame();
		busFrame.showBusInfo(rt_id, year, month, day);
		busFrame.getCanReserve();
		busFrame.addInfo();
		
		for (JButton btn : busFrame.getAllBtns()) {
			btn.getName();
			btn.addActionListener(selectBusBtnEvent);
		}
	}
	
	public void setBusID(int bi_id) {
		this.bi_id = bi_id;
	}
	
	// ���� ���� ������ ���ֱ�
	public void busFrameClose() {
		busFrame.dispose();
	}

	public boolean checkMonth() {	
		
		if(thisMonth + 1 <= 12) {
			if ((thisMonth == month && today < day) || (thisMonth + 1 == month && day <= today)) {
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "���� ������ �� �� �ı����� ������ �����մϴ�!", "��¥ Ȯ��", JOptionPane.PLAIN_MESSAGE);
				return false;
			}
		} else if (thisMonth + 1 == 13) {
			if ((thisYear == year && thisMonth == month && today < day) || (thisYear + 1 == year && month == 1 && day <= today)) {
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "���� ������ �� �� �ı����� ������ �����մϴ�!", "��¥ Ȯ��", JOptionPane.PLAIN_MESSAGE);
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(null, "���� ������ �� �� �ı����� ������ �����մϴ�!", "��¥ Ȯ��", JOptionPane.PLAIN_MESSAGE);
			return false;
		}
	}
	
	public boolean checkDate() {
		if(month == 0 || day == 0) {
			JOptionPane.showMessageDialog(null, "��� ��¥�� �������ּ���!!", "��¥ Ȯ��", JOptionPane.PLAIN_MESSAGE);
			return false;
		} else {
			return true;
		}
	}
	
	public boolean checkRoute() {
		if(startingPoint == null || arrivalPoint == null) {
			JOptionPane.showMessageDialog(null, "������� �������� �������ּ���!!", "������ Ȯ��", JOptionPane.PLAIN_MESSAGE);
			return false;
		} else {
			return true;
		}
	}
	
	
	// �¼� ���� �������� ������ �޼���
	public void showSeatFrame() {
		seatMainFrame = new SelectSeatMainFrame(saveInfo);	
	}
	
	public void before() {
		new Info_MainFrame(saveInfo);
	}
	
	// �� ������ ���ֱ�
	public void thisFrameClose() {
		this.dispose();
	}
	
	public void aaa() {
		String str = String.format("%d�� %d�� %s�� ����ϴ� ������ �����ϼ̽��ϴ�", month, day, bi_time);
		JOptionPane.showMessageDialog(null, str, "���� Ȯ��", JOptionPane.PLAIN_MESSAGE);
	}
	
	public void save() {
		saveInfo.set_bi_id(bi_id);
		saveInfo.set_rt_id(rt_id); 
		saveInfo.set_depart_from(startingPoint);
		saveInfo.set_arrive_at(arrivalPoint);
		saveInfo.set_date(year, month, day);
		saveInfo.set_time(bi_time);
		saveInfo.set_price(price);
	}
	
	
	// ������ SaveInfo saveInfo
	public SelectRouteMainFrame(SaveInfo saveInfo) {
		super("���� ���� �ý���");
		
		this.saveInfo = saveInfo;
		
		// FlowLayout���� ���� / gap 10 , 30
		//setLayout(new FlowLayout(FlowLayout.LEFT, 10, 30));
		label.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 7));
		label.setIcon(background);
		
		add(label);
		
		// Ȩ��ư �׼Ǵް� ���̾ƿ��� ���̱�
		//homeButton.addActionListener(homeBtnEvent);
		//label.add(homeButton);
		// ���߿� �̹�������..?
		
		label.add(new BackGroundLabel("", 10, 90, 1));
		label.add(new BackGroundLabel("���� �����ϱ�", 250, 90,36));
		
		
		label.add(new BackGroundLabel("", 500, 90, 0));
		label.add(new BackGroundLabel("", 770, 25, 0));
		
		
		label.add(new BackGroundLabel("", 200, 40, 12));
		label.add(new BackGroundLabel("�����", 185, 40, 20));
		label.add(new BackGroundLabel("������", 350, 40, 20));
		
		// ����� ��ư �׼Ǵް� ���̱�
		RoundedButton rb = new RoundedButton();
		label.add(new BackGroundLabel("", 200, 100, 0));
		startingPointButton.addActionListener(showStratingBtnEvent);
		label.add(startingPointButton);
		
		label.add(new BackGroundLabel("", 80, 100, 0));
		
		// ������ ��ư
		arrivalButton.addActionListener(showArrivalBtnEvent);
		arrivalButton.setLocation(50, 50);
		label.add(arrivalButton);
		
		label.add(new BackGroundLabel("", 250, 100, 0));
		label.add(new BackGroundLabel("", 770, 110, 0));
		//add(new BackGroundLabel("", 560, 10));
		label.add(new BackGroundLabel("", 100, 45, 0));
		label.add(new BackGroundLabel("���³� :",70, 45, 15));
		
		
		yearBox.addItemListener(yearBoxEvent);
		label.add(yearBox);
		label.add(new BackGroundLabel("��", 30, 30, 15));
		
		// �� ���� �޺��ڽ�
		monBox.addItemListener(monthBoxEvent);
		label.add(monBox);
		label.add(new BackGroundLabel("��", 30, 30, 15));
		
		// �� ���� �޺��ڽ�
		dayBox.addItemListener(dayBoxEvent);
		label.add(dayBox);
		label.add(new BackGroundLabel("��", 80, 30, 15));
		//add(new BackGroundLabel("", 560, 10));
		
		// ��ȸ�ϱ� ��ư
		label.add(new BackGroundLabel("", 235, 77, 0));
		serchButton.addActionListener(serchBtnEvent);
		label.add(serchButton);
		
		
		label.add(new BackGroundLabel("", 130, 64, 0));
		//label.add(new BackGroundLabel("", 150, 30));
		
		// ���� ���������� �Ѿ�� ��ư
		label.add(new BackGroundLabel("", 260, 30, 0));
		nextButton.addActionListener(nextBtnEvent);
		label.add(nextButton);
		
		//label.add(new BackGroundLabel("", 30, 30));
		label.add(new BackGroundLabel("", 20, 30, 0));
		beforeButton.addActionListener(beforeBtnEvent);
		label.add(beforeButton);
		
		setSize(800, 650);
		setLocationRelativeTo(null);
		//this.getContentPane().setBackground(new Color(0xFFFFCC));
		//getContentPane().setBackground(Color.WHITE);
		setResizable(false);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		new SelectRouteMainFrame(new SaveInfo());
	}
	
	// ����� �������� ���� �뼱ID��...
	public void getRouteID() {
		String sql = "SELECT rt_id FROM bus_route WHERE rt_depart_from=? AND rt_arrive_at=?";
		try (
				Connection conn = OjdbcConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
		){	
			pstmt.setString(1, startingPoint);
			pstmt.setString(2, arrivalPoint);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				this.rt_id = rs.getInt("rt_id");			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// �ش� bi_id �� ��� �ð���??
	public void getTime() {
		String sql = "SELECT bi_time FROM bus_info WHERE bi_id=?";
		try (
				Connection conn = OjdbcConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
		){	
			pstmt.setInt(1, bi_id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				this.bi_time = rs.getString("bi_time");				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void getprice() {
		String sql = "SELECT rt_charge FROM bus_route WHERE rt_id=?";
		try (
				Connection conn = OjdbcConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
		){	
			pstmt.setInt(1, rt_id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				this.price = rs.getInt("rt_charge");				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
