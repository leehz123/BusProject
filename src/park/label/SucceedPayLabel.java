package park.label;

import java.awt.Font;

import javax.swing.JLabel;

public class SucceedPayLabel extends JLabel{
	
	public SucceedPayLabel() {
		super("예매가 완료되었습니다.");
		setFont(new Font("휴먼 편지체", Font.BOLD, 24));
		setBounds(110,30,400,40);
	}

}
