package park.panel;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.JPanel;

import hong.SaveInfo;
import park.button.BeforePayButton;
import park.button.HomeButton;

public class HomeBeforeBtnPanel extends JPanel{ // Ȩ��ư, ������ư �Ϸķ� ��Ÿ�� �г�

	private HomeButton homeBtn ;
	private BeforePayButton befBtn ;
	
	public HomeBeforeBtnPanel(SaveInfo user) {
		LayoutManager manager = new FlowLayout(FlowLayout.LEFT); // �ǳ��� ���̾ƿ��� ���ʺ��� �Ϸķ� ������Ʈ�� ����ϴ� ���·� ����
		setLayout(manager);
		
//		homeBtn = new HomeButton(user);
//		befBtn = new BeforePayButton(user);
		
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
