package tk.stepcounter.gui;

import java.awt.event.*;

/**
 * ���C���E�B���h�E�̃C�x���g�n���h���B<br>
 * �E�B���h�E������ꂽ�ۂɃv���Z�X���I�����܂��B
 */
public class MainWindowListener extends WindowAdapter {
	
	private MainWindow window;
	
	/**
	 * �R���X�g���N�^
	 */
	public MainWindowListener(MainWindow window){
		this.window = window;
	}
	
	/**
	 * �E�B���h�E������ꂽ�Ƃ��̃C�x���g�n���h��
	 */
	public void windowClosing(WindowEvent e){
		this.window.saveConfig();
		System.exit(0);
	}

}