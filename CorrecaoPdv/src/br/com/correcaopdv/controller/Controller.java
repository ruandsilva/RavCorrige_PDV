package br.com.correcaopdv.controller;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel;
import com.jtattoo.plaf.hifi.HiFiLookAndFeel;
import com.jtattoo.plaf.texture.TextureLookAndFeel;

import br.com.correcaopdv.service.Service;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Controller extends JFrame {

	private JPanel contentPane;
	private JTextField txtNota;
	private Service service;
	private JTextField txtBanco;
	private static String banco;
	private JTextField txtNotaBc;
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					//Properties props = new Properties();  
				    //props.put("logoString", "Seu Nome");  
				    //AluminiumLookAndFeel().setCurrentTheme(props);
				    
				    UIManager.setLookAndFeel(new HiFiLookAndFeel());
				    //(new Controller()).setVisible(true);
					Controller frame = new Controller();
					frame.setResizable(false);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Controller() throws SQLException {
		
		service = new Service();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 576, 537);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblLogo = new JLabel();
		lblLogo.setBounds(228, 12, 111, 106);
		//////
		ImageIcon icon = new ImageIcon(Controller.class.getResource("/br/com/correcaopdv/img/NFE.png"));
		Image ima = icon.getImage();
		Image imagem = ima.getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), Image.SCALE_DEFAULT);
		ImageIcon ico = new ImageIcon(imagem);
		contentPane.setLayout(null);
		lblLogo.setIcon(ico);
		contentPane.add(lblLogo);
		
		JButton btnBcVl = new JButton("BASE DE CALCULO E VALOR DE IMPOSTOS");
		btnBcVl.setVisible(false);
		btnBcVl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(JOptionPane.showConfirmDialog(Controller.this, "Tem certeza que deseja prosseguir? É um caminho sem volta..") == 0) {
				
					try {
						
						service.zerarBasesDeCalculo(banco);
						JOptionPane.showMessageDialog(null,"Bases e Valores Ajustados");
						txtNota.setText("");
						txtNotaBc.setText("");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}	
		});
		btnBcVl.setFont(new Font("Eras Medium ITC", Font.BOLD, 18));
		btnBcVl.setBounds(12, 200, 542, 60);
		contentPane.add(btnBcVl);
		
		JButton btnAusenciaG = new JButton("AUSENCIA DE TROCO GERAL");
		btnAusenciaG.setVisible(false);
		btnAusenciaG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(JOptionPane.showConfirmDialog(Controller.this, "Tem certeza que deseja prosseguir? Use com Moderação..") == 0) {
				
					try {
						
						service.ausenciaDeTrocoGeral(banco);
						JOptionPane.showMessageDialog(null,"Notas em contingencia ajustadas");
						txtNota.setText("");
						txtNotaBc.setText("");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}	
		});
		btnAusenciaG.setFont(new Font("Eras Medium ITC", Font.BOLD, 18));
		btnAusenciaG.setBounds(12, 339, 542, 60);
		contentPane.add(btnAusenciaG);
		
		JButton btnAusenciaE = new JButton("AUSENCIA DE TROCO INDV");
		btnAusenciaE.setVisible(false);
		btnAusenciaE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(JOptionPane.showConfirmDialog(Controller.this, "Tem certeza que deseja prosseguir? Ajustar isso para a nota" +" " +txtNota.getText() +"?") == 0) {
				
					String nota = txtNota.getText();
					
					try {
						
						service.ausenciaDeTrocoIndividual(banco, nota);
						
						JOptionPane.showMessageDialog(null, "Nota" +" " +nota +" ajustada com sucesso!");
						txtNota.setText("");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnAusenciaE.setFont(new Font("Eras Medium ITC", Font.BOLD, 18));
		btnAusenciaE.setBounds(203, 410, 351, 60);
		contentPane.add(btnAusenciaE);
		
		txtNota = new JTextField();
		txtNota.setFont(new Font("Eras Medium ITC", Font.BOLD, 30));
		txtNota.setBounds(12, 410, 187, 60);
		txtNota.setVisible(false);
		
		ImageIcon iconL = new ImageIcon(Controller.class.getResource("/br/com/correcaopdv/img/search.png"));
		Image imaL = iconL.getImage();
		Image imagemL = imaL.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
		ImageIcon icoL = new ImageIcon(imagemL);
		contentPane.add(txtNota);
		txtNota.setColumns(10);
		
		JLabel lblUtilitarioDeAjustes = new JLabel("UTILITARIO DE AJUSTES DO MENEWPDV");
		lblUtilitarioDeAjustes.setFont(new Font("Eras Medium ITC", Font.BOLD, 22));
		lblUtilitarioDeAjustes.setBounds(60, 130, 442, 28);
		contentPane.add(lblUtilitarioDeAjustes);
		
		txtNotaBc = new JTextField();
		txtNotaBc.setFont(new Font("Eras Medium ITC", Font.BOLD, 30));
		txtNotaBc.setBounds(12, 268, 187, 60);
		txtNotaBc.setVisible(false);
		contentPane.add(txtNotaBc);
		txtNotaBc.setColumns(10);
		
		JButton btnBcVlIndv = new JButton("BC E VALOR DE IMPOSTOS INDV");
		btnBcVlIndv.setVisible(false);
		btnBcVlIndv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					if(JOptionPane.showConfirmDialog(Controller.this, "Tem certeza que deseja prosseguir? Ajustar isso para a nota" +" " +txtNotaBc.getText() +"?") == 0) {
					
					String nota2 = txtNotaBc.getText();
					
					try {
						service.zerarBasesDeCalculoIndividual(banco, nota2);
						JOptionPane.showMessageDialog(null, "Nota" +" " +nota2 +" ajustada com sucesso!");
						txtNotaBc.setText("");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnBcVlIndv.setFont(new Font("Eras Medium ITC", Font.BOLD, 18));
		btnBcVlIndv.setBounds(203, 271, 351, 60);
		contentPane.add(btnBcVlIndv);
		
		txtBanco = new JTextField();
		txtBanco.setFont(new Font("Eras Medium ITC", Font.BOLD, 18));
		txtBanco.setColumns(10);
		txtBanco.setBounds(12, 271, 456, 60);
		contentPane.add(txtBanco);
		
		//
		
		
		
		JButton btnBuscar = new JButton(icoL);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser file = new JFileChooser("Selecione o Netuno"); 
		        file.setFileSelectionMode(JFileChooser.FILES_ONLY);
		        int i= file.showSaveDialog(null);
		        if (i==1){
		        	
		        	txtBanco.setText("");
		        	file.setVisible(false);
		          
		        }else if(i == JFileChooser.CANCEL_OPTION){
		        	file.setVisible(false);
		          
		        }else {
		        	
		        	File arquivo = file.getSelectedFile();
			          
			        txtBanco.setText(arquivo.getPath());
			          
			        file.setVisible(false);
		        }
				contentPane.add(file);
				
			}
		});
		btnBuscar.setBounds(474, 271, 80, 60);
		contentPane.add(btnBuscar);
		//
		
		
		
		JLabel lblAbaixoCaminhoDo = new JLabel("Abaixo clique na lupa para buscar o NETUNO:");
		lblAbaixoCaminhoDo.setFont(new Font("Eras Medium ITC", Font.BOLD, 20));
		lblAbaixoCaminhoDo.setBounds(12, 231, 542, 29);
		contentPane.add(lblAbaixoCaminhoDo);
		
		ImageIcon iconC = new ImageIcon(Controller.class.getResource("/br/com/correcaopdv/img/config.png"));
		Image imaC = iconC.getImage();
		Image imagemC = imaC.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon icoC = new ImageIcon(imagemC);
		
		JButton btnConfig = new JButton(icoC);
		btnConfig.setVisible(false);
		
		JButton btnConfirmar = new JButton("CONFIRMAR");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				banco = txtBanco.getText();
				
				//Aqueles que Aparecem
				btnBcVl.setVisible(true);
				btnAusenciaG.setVisible(true);
				btnAusenciaE.setVisible(true);
				btnBcVlIndv.setVisible(true);
				txtNota.setVisible(true);
				txtNotaBc.setVisible(true);
				btnConfig.setVisible(true);
				
				//Aqueles que somem
				btnConfirmar.setVisible(false);
				btnBuscar.setVisible(false);
				lblAbaixoCaminhoDo.setVisible(false);
				txtBanco.setVisible(false);
				
				
			}
		});
		btnConfirmar.setFont(new Font("Eras Medium ITC", Font.BOLD, 18));
		btnConfirmar.setBounds(12, 342, 193, 60);
		contentPane.add(btnConfirmar);
		
		
		
		//Ação do Botão Config
		btnConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Aqueles que somem
				btnBcVl.setVisible(false);
				btnAusenciaG.setVisible(false);
				btnAusenciaE.setVisible(false);
				btnConfig.setVisible(false);
				btnBcVlIndv.setVisible(false);
				txtNota.setVisible(false);
				txtNota.setVisible(false);
				txtNotaBc.setVisible(false);
				
				//Aqueles que Aparecem
				btnConfirmar.setVisible(true);
				btnBuscar.setVisible(true);
				lblAbaixoCaminhoDo.setVisible(true);
				txtBanco.setVisible(true);
				
			}
		});
		btnConfig.setFont(new Font("Eras Medium ITC", Font.BOLD, 18));
		btnConfig.setBounds(0, 0, 53, 49);
		contentPane.add(btnConfig);
	}
}
