package lee.mpcomponents;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MPnavPanel extends JPanel {
	
	ImageIcon img1 = new ImageIcon("image/������1.png");
	ImageIcon img2 = new ImageIcon("image/������2.png");
	ImageIcon img3 = new ImageIcon("image/���ų���1.png");
	ImageIcon img4 = new ImageIcon("image/���ų���2.png");
	ImageIcon img5 = new ImageIcon("image/����Ż��1.png");
	ImageIcon img6 = new ImageIcon("image/����Ż��2.png");
	
	
	// ������ ��ư�� ������ �� ��ư�� �ش��ϴ� �гε��� MP���������ӿ� �ߵ���
	JButton MPprofileBtn = new JButton("�� ����", img1);
	JButton MPreservationBtn = new JButton("���� Ȯ��", img3);
	JButton MPleaveBtn = new JButton("���� Ż��", img5);
	
	GridLayout gl = new GridLayout(3, 1);
	
	public MPnavPanel() {
	
		setLayout(gl);
		
		//��ư �׵θ� ���ֱ�
		MPprofileBtn.setBorderPainted(false);
		MPreservationBtn.setBorderPainted(false);
		MPleaveBtn.setBorderPainted(false);
		
		//��ư ���� �����ϰ�
		MPprofileBtn.setContentAreaFilled(false);
		MPreservationBtn.setContentAreaFilled(false);
		MPleaveBtn.setContentAreaFilled(false);
		
		//Ŭ������ �� �׸� ��� ����� �� ���ֱ� 
		MPprofileBtn.setFocusPainted(false);
		MPreservationBtn.setFocusPainted(false);
		MPleaveBtn.setFocusPainted(false);
		
		//���콺 �÷��� �� ��ư �̹��� �ٲٱ� 
		MPprofileBtn.setRolloverIcon(img2);
		MPreservationBtn.setRolloverIcon(img4);
		MPleaveBtn.setRolloverIcon(img6);
		
		add(MPprofileBtn);
		add(MPreservationBtn);
		add(MPleaveBtn);
		
		//��ư�� �̹��� ũ��� 150 * 40 �ȼ�
		setBounds(0, 120, 150, 120);
		setVisible(true);
		setOpaque(false);
	}
	
}
