package hong.selectroute;

import java.awt.Color;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;

import hong.OjdbcConnection;
import hong.SaveInfo;
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
import hong.selectseat.SelectSeatMainFrame;

public class SelectRouteMainFrame extends JFrame {
	
	SaveInfo saveInfo = new SaveInfo();
	
	HomeButton homeButton = new HomeButton();				// 홈 버튼
	StartingPointButton startingPointButton = new StartingPointButton();	// 출발 버튼
	ArrivalButton arrivalButton = new ArrivalButton(); 	// 도착 버튼
	SerchButton serchButton = new SerchButton(); // 조회하기 버튼
	NextButton nextButton = new NextButton();
	
	MonthComboBox monBox = new MonthComboBox();				// 달 선택 콤보박스
	DayComboBox dayBox = new DayComboBox();					// 일 선택 콤보박스
	
	SelectStartingButtonEvent startingBtnEvent = new SelectStartingButtonEvent(this); // 출발지 선택 버튼 이벤트
	SelectArrivalButtonEvent arrivalBtnEvent = new SelectArrivalButtonEvent(this); // 도착지 선택 버튼 이벤트
	HomeButtonEvent homeBtnEvent = new HomeButtonEvent(this); // 홈 버튼 이벤트
	ShowStartingTerminalEvent showStratingBtnEvent = new ShowStartingTerminalEvent(this); // 출발지들 보여주는 이벤트.
	ShowArriveTerminalEvent showArrivalBtnEvent = new ShowArriveTerminalEvent(this); // 도착지들 보여주는 이벤트.
	MonthComboBoxEvent monthBoxEvent = new MonthComboBoxEvent(this); // 달 선택 콤보박스 이벤트
	DayComboBoxEvent dayBoxEvent = new DayComboBoxEvent(this); // 일 선택 콤보박스 이벤트
	SerchButtonEvent serchBtnEvent = new SerchButtonEvent(this); // 조회하기 버튼 이벤트
	SelectBusButtonEvent selectBusBtnEvent = new SelectBusButtonEvent(this); // 버스 선택해서 예약하기 버튼 이벤트
	NextButtonEvent nextBtnEvent = new NextButtonEvent(this);
	
	SelectSeatMainFrame seatMainFrame;
	SelectTerminalMainFrame stmFrame;
	SelectBusFrame busFrame;
	
	private String startingPoint;
	private String arrivalPoint;
	private int month;
	private int day;
	private int routeID;
	private int busID;
	
	// 달에 따른 day들을 일 선택 콤보박스에 추가하는 메서드
	public void addDays(int month) {
		dayBox.addItems(month);
	}
	
	// 일 선택 콤보박스를 비워주는 메서드
	public void resetDays() {
		dayBox.reset();
	}
		
		
	// 출발지를 선택하면 버튼 이름을 출발지로(ex:동서울) 바꿔준다
	public void departFrom(String starting) {
		startingPointButton.setTextStpBtn(starting);
	}
		
	// 도착지를 선택하면 버튼 이름을 출발지로(ex:부산) 바꿔준다
	public void arriveAt(String destination) {
		arrivalButton.setTextArvBtn(destination);
	}
		
	// 출발지 버튼을 누르면 터미널들을 보여주는 메서드. 각각의 터미널 버튼에 액션을 추가해준다.
	public void showStartingTerminals() {
		stmFrame = new SelectTerminalMainFrame();
		for (JButton btn : stmFrame.getAllBtns()) {
			btn.addActionListener(startingBtnEvent);
		}
	}
	
	// 도착지 버튼을 누르면 터미널들을 보여주는 메서드. 각각의 터미널 버튼에 액션을 추가해준다.
	public void showArriveTerminals() {
		stmFrame = new SelectTerminalMainFrame();
		for (JButton btn : stmFrame.getAllBtns()) {
			btn.addActionListener(arrivalBtnEvent);
		}
	}
		
	// 출발지를 선택하면 출발지를 입력받는다.
	public void setStartingPoint(String startingPoint) {
		this.startingPoint = startingPoint;
	}
		
	// 도착지를 선택하면 도착지를 입력받는다.
	public void setArrivalPoint(String arrivalPoint) {
		this.arrivalPoint = arrivalPoint;
	}
		
	// 달 입력 받기
	public void setMonth(int month) {
		this.month = month;
	}
		
	// 일 입력 받기
	public void setDay(int day) {
		this.day = day;
	}
		
	// 출발지나 도착지를 선택하면 그 창만 닫아준다.
	public void stmFlameClose() {
		
		stmFrame.dispose();
	}
	
	// 노선, 날짜에 맞는 버스들의 정보를 보여주는 메서드
	public void showBus() {
		busFrame = new SelectBusFrame();
		busFrame.showBusInfo(routeID, month, day);
		busFrame.getCanReserve();
		busFrame.addInfo();
		
		for (JButton btn : busFrame.getAllBtns()) {
			btn.getName();
			btn.addActionListener(selectBusBtnEvent);
		}
	}
	
	public void setBusID(int busID) {
		this.busID = busID;
	}
	
	public void busFrameClose() {
		busFrame.dispose();
	}
	
	public void showSeatFrame() {
		seatMainFrame = new SelectSeatMainFrame(saveInfo);
	}
	
	public void thisFrameClose() {
		this.dispose();
	}
	
	public void save() {
		saveInfo.set_bi_id(busID);
		saveInfo.set_rt_id(routeID); 
		saveInfo.set_depart_from(startingPoint);
		saveInfo.set_arrive_at(arrivalPoint);
	}
	
	
	public SelectRouteMainFrame() {
		
		super("버스 예약 시스템");
		// FlowLayout으로 설정 / gap 10 , 30
		setLayout(new FlowLayout(FlowLayout.LEFT, 10, 30));
		
		// 홈버튼 액션달고 레이아웃에 붙이기
		homeButton.addActionListener(homeBtnEvent);
		add(homeButton);
		// 나중에 이미지넣을..?
		add(new BackGroundLabel("", 370, 100));
		
		// 출발지 버튼 액션달고 붙이기
		startingPointButton.addActionListener(showStratingBtnEvent);
		add(startingPointButton);
		add(new BackGroundLabel("=>", 60, 200));
		
		// 도착지 버튼
		arrivalButton.addActionListener(showArrivalBtnEvent);
		add(arrivalButton);
		//add(new BackGroundLabel("", 560, 10));
		add(new BackGroundLabel("출발 날짜 :",80, 30));
		
		// 월 선택 콤보박스
		monBox.addItemListener(monthBoxEvent);
		add(monBox);
		add(new BackGroundLabel("월", 60, 30));
		
		// 일 선택 콤보박스
		dayBox.addItemListener(dayBoxEvent);
		add(dayBox);
		add(new BackGroundLabel("일", 30, 30));
		//add(new BackGroundLabel("", 560, 10));
		
		// 조회하기 버튼
		serchButton.addActionListener(serchBtnEvent);
		add(serchButton);
		
		
		add(new BackGroundLabel("", 150, 30));
		nextButton.addActionListener(nextBtnEvent);
		add(nextButton);
		
		setBounds(300, 100, 600, 650);
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		new SelectRouteMainFrame();
	}
	
	
	// 출발지 도착지에 따른 노선ID를...
	public void getRouteID() {
		String sql = "SELECT rt_id FROM bus_route WHERE rt_depart_from=? AND rt_arrive_at=?";
		try (
				Connection conn = OjdbcConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
		){	
			pstmt.setString(1, startingPoint);
			pstmt.setString(2, arrivalPoint);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				this.routeID = rs.getInt("rt_id");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
