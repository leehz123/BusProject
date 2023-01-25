package lee.mpcomponents;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import hong.SaveInfo;

public class MPcontentsPanel extends JPanel {
	
	SaveInfo saveInfo;
	
	MPprofilePanel MPprofile;
	MPleavePanel MPleave = new MPleavePanel();
	MPreservationPanel MPreservation;
	
	//ī�巹�̾ƿ�
	CardLayout MPcontentsCard = new CardLayout();
	
	ImageIcon backImg = new ImageIcon("image/mp�������гι��.png");
	
	public MPcontentsPanel(SaveInfo saveInfo) {
		this.saveInfo = saveInfo;
	
		MPprofile = new MPprofilePanel(saveInfo);
		MPreservation = new MPreservationPanel(saveInfo);
		
		setLayout(MPcontentsCard);	

		add("������", MPprofile);
		add("���ų���", MPreservation);
		add("����Ż��", MPleave);
		
		
		setBounds(230, 110, 510, 460);
		setVisible(true);
	}
}
