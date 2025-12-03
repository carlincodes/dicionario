package dicionario;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Font;
import java.awt.Color;
import java.awt.Label;
import javax.swing.UIManager;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class TelaDicionario {

	private JFrame frame;
	private JLabel label_1;
	private JLabel label;
	private JComboBox comboBox;
	private Dicionario dicionarioAtual;
	private JLabel label_2;
	private JTextField textField;
	private JLabel label_4;
	private JButton btnTraduzirParaPortugues;
	private JButton btnTraduzirParaIdioma;
	private JTextArea textArea;
	private JLabel lblTermoParaSer;
	private JSeparator separator_3;
	private JSeparator separator_4;
	private JSeparator separator_5;
	private JSeparator separator_6;
	private JTextField textField_2;
	private JTextArea textArea_2;
	private JButton btnBuscarPalavraNo;
	private JButton btnBuscarPalavraEm;
	private JLabel lblResultado;
	private JLabel lblPalavra;
	private JLabel labelBandeira;
	private JLabel labelBandeiraBrasil;
	private JLabel lblNewLabel;
	private JLabel avisos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaDicionario window = new TelaDicionario();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaDicionario() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(64, 128, 128));
		frame.getContentPane().setForeground(new Color(255, 255, 255));
		frame.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		frame.setTitle("Train");
		frame.setBounds(100, 100, 788, 474);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		label_1 = new JLabel("TRAIN");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setToolTipText("");
		label_1.setForeground(new Color(102, 0, 153));
		label_1.setFont(new Font("Microsoft New Tai Lue", Font.BOLD | Font.ITALIC, 35));
		label_1.setBounds(254, -71, 261, 219);
		frame.getContentPane().add(label_1);
		
		label = new JLabel("Sistema de Tradução de Palavras de Informática");
		label.setForeground(new Color(136, 0, 136));
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setBounds(195, 23, 453, 98);
		frame.getContentPane().add(label);
		
		comboBox = new JComboBox();
		comboBox.setBackground(new Color(64, 128, 128));
        comboBox.addItem("Ingles");
        comboBox.addItem("Espanhol");
        
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
                String idiomaSelecionado = (String) comboBox.getSelectedItem();
                try {
					dicionarioAtual = new Dicionario(idiomaSelecionado);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					avisos.setText("Erro ao carregar o dicionário: " + e1.getMessage());
				}
                
        		btnTraduzirParaIdioma.setEnabled(true);
                btnTraduzirParaPortugues.setEnabled(true);
                btnBuscarPalavraNo.setEnabled(true);
                btnBuscarPalavraEm.setEnabled(true);
                
                ImageIcon bandeira = null;
                if (idiomaSelecionado.equals("Ingles")) {
                    bandeira = new ImageIcon(getClass().getResource("/imagens/ingles.png"));
                }
                
                if (idiomaSelecionado.equals("Espanhol")) {
                    bandeira = new ImageIcon(getClass().getResource("/imagens/espanhol.png"));
                }

                if (bandeira != null) {
                    labelBandeira.setIcon(bandeira);
                }
			}
		});
		comboBox.setForeground(new Color(128, 0, 128));
		comboBox.setBackground(new Color(175, 216, 216));
		comboBox.setBounds(290, 131, 198, 36);
		frame.getContentPane().add(comboBox);
		
		label_2 = new JLabel("Selecione o idioma");
		label_2.setForeground(new Color(128, 0, 128));
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_2.setBounds(328, 101, 140, 36);
		frame.getContentPane().add(label_2);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBackground(new Color(221, 160, 221));
		textField.setBounds(108, 201, 131, 36);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		label_4 = new JLabel("Busca");
		label_4.setForeground(new Color(128, 0, 128));
		label_4.setFont(new Font("Tahoma", Font.BOLD, 20));
		label_4.setBounds(364, 263, 78, 28);
		frame.getContentPane().add(label_4);
		
		btnTraduzirParaPortugues = new JButton("Traduzir para portugues");
		btnTraduzirParaPortugues.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String palavraDigitada = textField.getText();
				ArrayList<String> palavraTraduzida = null;
			    StringBuilder resultado = new StringBuilder();
				try {
					palavraTraduzida = dicionarioAtual.traduzirParaPortugues(palavraDigitada);
				    for (String palavra : palavraTraduzida) {
				        if (resultado.length() > 0) {
				            resultado.append(", \n"); 
				        }
				        resultado.append(palavra);
				    }
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					avisos.setText(e1.getMessage());
				}
		        if (palavraTraduzida.isEmpty()) {
		        	avisos.setText("Tradução não encontrada.");
		        } else {
		        	avisos.setText("Palavra traduzida com sucesso!");
		        }
			    textArea.setText(resultado.toString());

			}
		});
		btnTraduzirParaPortugues.setForeground(new Color(148, 0, 211));
		btnTraduzirParaPortugues.setBackground(new Color(224, 255, 255));
		btnTraduzirParaPortugues.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnTraduzirParaPortugues.setBounds(281, 192, 234, 21);
		frame.getContentPane().add(btnTraduzirParaPortugues);
		
		textArea = new JTextArea();
		textArea.setBackground(new Color(221, 160, 221));
		textArea.setBounds(543, 176, 100, 84);
		frame.getContentPane().add(textArea);
		
		lblTermoParaSer = new JLabel("Palavra a ser traduzida");
		lblTermoParaSer.setForeground(new Color(128, 0, 128));
		lblTermoParaSer.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTermoParaSer.setBounds(84, 172, 205, 28);
		frame.getContentPane().add(lblTermoParaSer);
		
		separator_3 = new JSeparator();
		separator_3.setForeground(new Color(128, 0, 128));
		separator_3.setBounds(176, 94, 432, 11);
		frame.getContentPane().add(separator_3);
		
		separator_4 = new JSeparator();
		separator_4.setForeground(new Color(148, 0, 211));
		separator_4.setBounds(176, 10, 432, 3);
		frame.getContentPane().add(separator_4);
		
		separator_5 = new JSeparator();
		separator_5.setOrientation(SwingConstants.VERTICAL);
		separator_5.setForeground(new Color(128, 0, 128));
		separator_5.setBounds(176, 10, 14, 84);
		frame.getContentPane().add(separator_5);
		
		separator_6 = new JSeparator();
		separator_6.setOrientation(SwingConstants.VERTICAL);
		separator_6.setForeground(new Color(148, 0, 211));
		separator_6.setBounds(606, 10, 2, 84);
		frame.getContentPane().add(separator_6);
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setColumns(10);
		textField_2.setBackground(new Color(221, 160, 221));
		textField_2.setBounds(108, 309, 131, 36);
		frame.getContentPane().add(textField_2);
		
		textArea_2 = new JTextArea();
		textArea_2.setBackground(new Color(221, 160, 221));
		textArea_2.setBounds(543, 295, 105, 90);
		frame.getContentPane().add(textArea_2);
		
		btnBuscarPalavraNo = new JButton("Buscar palavra no idioma escolhido");
		btnBuscarPalavraNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String palavraDigitada = textField_2.getText();
				ArrayList<String> palavrasEncontradas = null;
			    StringBuilder resultado = new StringBuilder();
				try {
					palavrasEncontradas = dicionarioAtual.localizarPalavraIdioma(palavraDigitada);
				    for (String palavra : palavrasEncontradas) {
				        if (resultado.length() > 0) {
				            resultado.append(", \n"); 
				        }
				        resultado.append(palavra);
				    }
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					avisos.setText(e1.getMessage());
				}
				
		        if (palavrasEncontradas.isEmpty()) {
		        	avisos.setText("Nenhuma palavra encontrada.");
		        } else {
		        	avisos.setText("Busca realizada com sucesso.");
		        }
		        
				textArea_2.setText(resultado.toString());
			}
		});
		btnBuscarPalavraNo.setForeground(new Color(148, 0, 211));
		btnBuscarPalavraNo.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnBuscarPalavraNo.setBackground(new Color(224, 255, 255));
		btnBuscarPalavraNo.setBounds(281, 333, 234, 21);
		frame.getContentPane().add(btnBuscarPalavraNo);
		
		JLabel lblTraduo = new JLabel("Tradução");
		lblTraduo.setForeground(new Color(128, 0, 128));
		lblTraduo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTraduo.setBounds(556, 147, 69, 28);
		frame.getContentPane().add(lblTraduo);
		
		btnTraduzirParaIdioma = new JButton("Traduzir para idioma escolhido");
		btnTraduzirParaIdioma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String palavraDigitada = textField.getText();
				ArrayList<String> palavraTraduzida = null;
			    StringBuilder resultado = new StringBuilder();
				try {
					palavraTraduzida = dicionarioAtual.traduzirParaIdioma(palavraDigitada);
				    for (String palavra : palavraTraduzida) {
				        if (resultado.length() > 0) {
				            resultado.append(", \n"); 
				        }
				        resultado.append(palavra);
				    }
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					avisos.setText(e1.getMessage());
				}
				
		        if (palavraTraduzida.isEmpty()) {
		        	avisos.setText("Tradução não encontrada.");
		        } else {
		        	avisos.setText("Palavra traduzida com sucesso!");
		        }
			    textArea.setText(resultado.toString());
			}
		});
		btnTraduzirParaIdioma.setForeground(new Color(148, 0, 211));
		btnTraduzirParaIdioma.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnTraduzirParaIdioma.setBackground(new Color(224, 255, 255));
		btnTraduzirParaIdioma.setBounds(281, 224, 234, 21);
		frame.getContentPane().add(btnTraduzirParaIdioma);
		
		btnBuscarPalavraEm = new JButton("Buscar palavra em português");
		btnBuscarPalavraEm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String palavraDigitada = textField_2.getText();
				ArrayList<String> palavrasEncontradas = null;
			    StringBuilder resultado = new StringBuilder();
				try {
					palavrasEncontradas = dicionarioAtual.localizarPalavraPortugues(palavraDigitada);
				    for (String palavra : palavrasEncontradas) {
				        if (resultado.length() > 0) {
				            resultado.append(", \n"); 
				        }
				        resultado.append(palavra);
				        avisos.setText("Busca realizada com sucesso.");
				    }
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					avisos.setText(e1.getMessage());
				}
		        if (palavrasEncontradas.isEmpty()) {
		        	avisos.setText("Nenhuma palavra encontrada.");
		        } else {
		        	avisos.setText("Busca realizada com sucesso.");
		        }
				textArea_2.setText(resultado.toString());
			}
		});
		btnBuscarPalavraEm.setForeground(new Color(148, 0, 211));
		btnBuscarPalavraEm.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnBuscarPalavraEm.setBackground(new Color(224, 255, 255));
		btnBuscarPalavraEm.setBounds(281, 301, 234, 21);
		frame.getContentPane().add(btnBuscarPalavraEm);
		
		lblResultado = new JLabel("Resultado");
		lblResultado.setForeground(new Color(128, 0, 128));
		lblResultado.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblResultado.setBounds(557, 268, 84, 28);
		frame.getContentPane().add(lblResultado);
		
		lblPalavra = new JLabel("Palavra");
		lblPalavra.setForeground(new Color(128, 0, 128));
		lblPalavra.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPalavra.setBounds(140, 277, 84, 28);
		frame.getContentPane().add(lblPalavra);
		
		labelBandeira = new JLabel("");
		labelBandeira.setBounds(29, 11, 137, 83);
		frame.getContentPane().add(labelBandeira);
		
		btnTraduzirParaIdioma.setEnabled(false);
        btnTraduzirParaPortugues.setEnabled(false);
        btnBuscarPalavraNo.setEnabled(false);
        btnBuscarPalavraEm.setEnabled(false);
        
        labelBandeiraBrasil = new JLabel("");
        labelBandeiraBrasil.setBounds(618, 10, 137, 83);
        frame.getContentPane().add(labelBandeiraBrasil);
        
        ImageIcon bandeiraBrasil = new ImageIcon(getClass().getResource("/imagens/brasil.png"));
		labelBandeiraBrasil.setIcon(bandeiraBrasil);
		
		lblNewLabel = new JLabel("Alertas:");
		lblNewLabel.setBounds(49, 410, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		avisos = new JLabel("");
		avisos.setBounds(100, 410, 635, 14);
		frame.getContentPane().add(avisos);
	}
}
