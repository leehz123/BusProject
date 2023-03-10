package park.panel;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.JPanel;

import hong.SaveInfo;
import park.button.BeforePayButton;
import park.button.HomeButton;

public class HomeBeforeBtnPanel extends JPanel{ // 홈버튼, 이전버튼 일렬로 나타낼 패널

	private HomeButton homeBtn ;
	private BeforePayButton befBtn ;
	
	public HomeBeforeBtnPanel(SaveInfo user) {
		LayoutManager manager = new FlowLayout(FlowLayout.LEFT); // 판넬의 레이아웃을 왼쪽부터 일렬로 컴포넌트를 출력하는 형태로 설정
		setLayout(manager);
		
		homeBtn = new HomeButton(user);
		befBtn = new BeforePayButton(user);
		
		add(homeBtn);
		add(befBtn);
			
		setBackground(Color.white);
		setBounds(10, 10, 500, 40);
	}
	
	public BeforePayButton getBefBtn() {
		return befBtn;
	}
	
	public HomeButton getHomeBtn() {
		return homeBtn;
	}
}
