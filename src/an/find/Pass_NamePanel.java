package an.find;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Pass_NamePanel extends JPanel{
	JLabel nameLabel = new Find_Label("   �̸� : ");
	JTextField nameText = new Find_TextField(10);
	
	 public Pass_NamePanel(JTextField j) {
		 
		 nameText = j;
		setLayout(new FlowLayout());
		
		add(nameLabel); add(nameText);
	}
	

}