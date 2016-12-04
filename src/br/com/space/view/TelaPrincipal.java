package br.com.space.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TelaPrincipal extends JFrame {

	//private JPanel contentPane;
	
	public TelaPrincipal() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Space");
		setSize(750,700);
		setLocationRelativeTo(null);
		setUndecorated(true);
	}
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
					frame.getContentPane().add(new Fase());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
