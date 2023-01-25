package lee.mpcomponents;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import lee.RoundedButton;


public class MPprofilePanel_2 extends JPanel {
	
	RoundedButton MPbackBtn = new RoundedButton("�ڷΰ���");
	RoundedButton MPcompleteBtn = new RoundedButton("�����Ϸ�");
	
	JPanel MPlabelPanel = new JPanel();//���̾ƿ��� �׸���鷹�̾ƿ�����
	
	//�׸���鷹�̾ƿ�
	GridBagLayout gbl = new GridBagLayout();
	GridBagConstraints gblc = new GridBagConstraints();

	JLabel MPviewLb1 = new JLabel("   �̸� "); //�� �� ���ڸ� ������ Ƣ��� �־ �տ� ���� �־���
	JLabel MPviewLb2 = new JLabel("�ڵ��� ��ȣ ");
	JLabel MPviewLb3 = new JLabel("�� ��й�ȣ ");
	JLabel MPviewLb4 = new JLabel("��й�ȣ Ȯ�� ");
	
	
	
	
	
	JTextField MPnameTf = new JTextField(25);
	JTextField MPphoneTf_1 = new JTextField(4);
	JTextField MPphoneTf_2 = new JTextField(4);
	JTextField MPphoneTf_3 = new JTextField(4);
	JPasswordField MPnewpwTf = new JPasswordField(25);
	JPasswordField MPchknewpwTf = new JPasswordField(25);
	
	JLabel MPprofilerestrictLb = new JLabel("<html><pre style=\"font-family:���; font-size:13;\">�̸��� 10���ڱ��� �Է� �����մϴ�.<br>��й�ȣ�� ����+Ư������+���� 8~20�ڷ� �����Ǿ�� �մϴ�.</pre></htnl>"); 
	
	
	JPanel phoneNumPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	
	JLabel stateLb = new JLabel();
	
	
	public MPprofilePanel_2() {
		setLayout(null);

		//�ؽ�Ʈ�ʵ�� �׵θ� ���ְ� ��� ���� 
		MPnameTf.setBackground(new Color(219,219,219));
		MPnameTf.setBorder(null);
		MPphoneTf_1.setBackground(new Color(219,219,219));
		MPphoneTf_1.setBorder(null);
		MPphoneTf_2.setBackground(new Color(219,219,219));
		MPphoneTf_2.setBorder(null);
		MPphoneTf_3.setBackground(new Color(219,219,219));
		MPphoneTf_3.setBorder(null);
		MPnewpwTf.setBackground(new Color(219,219,219));
		MPnewpwTf.setBorder(null);
		MPchknewpwTf.setBackground(new Color(219,219,219));
		MPchknewpwTf.setBorder(null);
		
		
		
		//�󺧵� ��Ʈ �����ϰ� ��ġ ũ�� ���� + add
		MPviewLb1.setFont(new Font("���", Font.BOLD, 14));
		MPviewLb2.setFont(new Font("���", Font.BOLD, 14));
		MPviewLb3.setFont(new Font("���", Font.BOLD, 14));
		MPviewLb4.setFont(new Font("���", Font.BOLD, 14));
		MPprofilerestrictLb.setBounds(67, 330, 400, 85);
		add(MPprofilerestrictLb);
		
		//����й�ȣ �ؽ�Ʈ�ʵ� �Է��� �� �ܷ� ǥ�� 
		MPnewpwTf.setEchoChar('��');
		MPchknewpwTf.setEchoChar('��');
		
		
		//�׸���鷹�̾ƿ� �����ϴ� �κ�_______________________________________________________________
		//��ǲ�г� ����
		MPlabelPanel.setLayout(new GridLayout());
		MPlabelPanel.setBounds(57, 5, 400, 300);
		MPlabelPanel.setLayout(gbl);
		MPlabelPanel.setBackground(Color.WHITE);
		//�׸���鷹�̾ƿ� ���� + ������Ʈ �ֱ�__________ 
		gblc.weightx = 1.0; //����, ���� �е�? ���ϱ�
		gblc.weighty = 0.0;
		
		//�̸�
		gbInsert(MPviewLb1, 0, 0, 1, 1);
		gbInsert(MPnameTf, 1, 0, 3, 1);
		//��ȭ��ȣ 
        phoneNumPanel.add(MPphoneTf_1);
        phoneNumPanel.add(new JLabel(" - "));
        phoneNumPanel.add(MPphoneTf_2);
        phoneNumPanel.add(new JLabel(" - "));
        phoneNumPanel.add(MPphoneTf_3);	
        phoneNumPanel.setBackground(Color.WHITE);
		gbInsert(MPviewLb2, 0, 1, 1, 1);
		gbInsert(phoneNumPanel, 1, 1, 3, 1);		
		//��й�ȣ
		gbInsert(MPviewLb3, 0, 2, 1, 1);
		gbInsert(MPnewpwTf, 1, 2, 3, 1);	
		//��й�ȣ Ȯ��
		gbInsert(MPviewLb4, 0, 3, 1, 1);
		gbInsert(MPchknewpwTf, 1, 3, 3, 1);
		//��й�ȣ �Է� ���� �� 
		gbInsert(stateLb, 1, 4, 4, 1);
		//________________________________________________________________________________________
		
		
		
		
		
		//�ڵ������� �ؽ�Ʈ�ʵ� ����~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// 010, 016, 017... �Է��ϴ� �ؽ�Ʈ �ʵ� Ű������ ����
		MPphoneTf_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				JTextField src = (JTextField) e.getSource();
				if((src.getText().length() >= 3)) e.consume(); //3���� ����
				if(!Character.isDigit(e.getKeyChar())) e.consume();	//���ڸ� �Է� ����
			}
		});
		
		// 1234 - 1234 �Է��ϴ� �ؽ�Ʈ �ʵ� Ű������ ����
		KeyAdapter kA = new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				JTextField src = (JTextField) e.getSource();
				if((src.getText().length() >= 4)) e.consume(); //4���� ����
				if(!Character.isDigit(e.getKeyChar())) e.consume(); //���ڸ� �Է� ����
			}
		};
		MPphoneTf_2.addKeyListener(kA);
		MPphoneTf_3.addKeyListener(kA);
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		
		
		//�̸� �ؽ�Ʈ�ʵ� ����~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		MPnameTf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				JTextField src = (JTextField) e.getSource();
				if((src.getText().length() >= 10)) e.consume(); //10���� ����
				if(Character.isDigit(e.getKeyChar())) e.consume(); //���ڸ� �Է� ����
			}
		});
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		
		
		//�� ��й�ȣ �ؽ�Ʈ�ʵ� Ű ������~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		MPnewpwTf.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				JTextField src = (JTextField) e.getSource();
				if((src.getText().length() >= 20)) e.consume(); //20���� ����
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
				JTextField src = (JTextField) e.getSource();
				if (src.getText().equals(MPgetPwd(MPchknewpwTf))) {
					stateLb.setForeground(new Color(255,158,238));
					stateLb.setText("��й�ȣ�� ��ġ�մϴ�.");
				} else {
					stateLb.setForeground(new Color(217,158,255));
					stateLb.setText("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
				}
				
				if(src.getText().length() == 0) {
					stateLb.setText("");
				}
			}
		});
				
				
		//��й�ȣ Ȯ�� �ؽ�Ʈ�ʵ� Ű������~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		MPchknewpwTf.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				JTextField src = (JTextField) e.getSource();
				if((src.getText().length() >= 20)) e.consume(); //20���� ����
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
	
				JTextField src = (JTextField) e.getSource();
				if (src.getText().equals(MPgetPwd(MPnewpwTf))) {
					stateLb.setForeground(new Color(255,158,238));
					stateLb.setText("��й�ȣ�� ��ġ�մϴ�.");
				} else {
					stateLb.setForeground(new Color(217,158,255));
					stateLb.setText("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
				}
				
				if(src.getText().length() == 0) {
					stateLb.setText("");
				}
			}
		});
		
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		
		
		
		
		//�����Ϸ��ư ����
		MPcompleteBtn.setBounds(277, 280, 90, 38);
		MPcompleteBtn.setBorderPainted(false);
		MPcompleteBtn.setFont(new Font("�޸�����ü", Font.BOLD, 17));
		
		//�ڷΰ��� ��ư ����
		MPbackBtn.setBounds(147, 280, 90, 38 );
		MPbackBtn.setBorderPainted(false);
		MPbackBtn.setFont(new Font("�޸�����ü", Font.BOLD, 17));
		
		add(MPcompleteBtn);
		add(MPbackBtn);
		add(MPlabelPanel);
		
		setBackground(Color.WHITE);
	}
	
	
	
	public void gbInsert(Component c, int gridx, int gridy, int gridwidth, int gridheight) {
		//gridx���� ������� ����ȣ, ���ȣ, �ʺ�, ���̸� �ǹ�
		
		gblc.fill = gblc.BOTH;//��ü�� ä���°ŷ� 
		gblc.gridx = gridx;
		gblc.gridy = gridy;
		gblc.gridwidth = gridwidth;
		gblc.gridheight = gridheight;
		gbl.setConstraints(c,  gblc);
		gblc.insets = new Insets(15, 10, 10, 10);
		MPlabelPanel.add(c);
	}
	

	
	//JPasswordField���� ���� char[]�迭�� String���� ��ȯ
	public String MPgetPwd(JPasswordField MPnewpwTf) {
		StringBuilder pw = new StringBuilder();	
		char[] secret_pw = MPnewpwTf.getPassword(); 

	    for(char cha : secret_pw){         
	        Character.toString(cha); 
	        pw.append(cha);
	    }
		return pw.toString();
	}

	
	
	public String MPgetPhoneNum() {
		return MPphoneTf_1.getText() + MPphoneTf_2.getText() + MPphoneTf_3.getText();
	}


}






