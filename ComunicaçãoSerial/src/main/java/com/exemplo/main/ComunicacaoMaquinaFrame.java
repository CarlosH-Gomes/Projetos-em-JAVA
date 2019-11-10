package com.exemplo.main;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.exemplo.conexao.Conexao;
import com.exemplo.model.Cliente;
import com.exemplo.model.ComunicacaoMaquina;
import com.exemplo.reports.GeraRelatorio;
import com.exemplo.service.ClienteService;
import com.exemplo.service.MaquinaService;

import net.sf.jasperreports.engine.JRParameter;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.util.Date;
import java.util.HashMap;
import java.util.Calendar;
import javax.swing.JLayeredPane;



public class ComunicacaoMaquinaFrame extends JFrame {

	private static final long serialVersionUID = 5982264099348912699L;

	private JPanel contentPane;
	
	private JComboBox<String> portaComboBox;
	private JComboBox<String> baudRateComboBox;
	private JComboBox<String> dataBitsComboBox;
	private JComboBox<String> paridadeComboBox;
	private JComboBox<String> stopBitsComboBox;

	private JButton btnConectar;
	private JButton btnDesconectar;
	
	private String baudRate[] = {"300", "600", "1200", "2400","9600","14400", "19200","39400"};
	private String dataBits[] = {"5","6","7","8"};
	private String paridade[] = {"0","1","2","3","4"};
	private String stopBits[] = {"0","1","2","3"};
	
	
	private boolean portOpen = false;
	private int     intBaudRate = 0;
	private int     intDataBits = 0;
	private int     intParidade = 0; 
	private int     intStopBits = 0;
	private int 	estados = 0;
	private Conexao conexao;
	
	private String  dir;
	private JButton btnLiga;
	private JButton btnDesliga;
	private JButton btnAvancaEtapa;
	private JTextField txtEstado;
	private JTextField txtData;
	private JButton btnRelatorio;
	private JButton btnRelatorio_2;
	private JLayeredPane layeredPane_1;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ComunicacaoMaquinaFrame frame = new ComunicacaoMaquinaFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public ComunicacaoMaquinaFrame() {
		
		getPathLib();
		
		initComponents();
		
		leiaBaudRate();
		leiaDataBits();
		leiaParidade();
		leiaStopBits();
		
		leiaPortas();
		createEvents();
	}
	
	
	
	private void getPathLib() {
		setDir(System.getProperty("user.dir"));
		try {
			System.load(getDir()+"\\rxtxSerial.dll");
			System.load(getDir()+"\\rxtxParallel.dll");
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	private void createEvents() {
		btnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		     	String nomeArquivo = "relatorio_maquina";
				Map<String, Object> params = new HashMap<String, Object>();
				params.put(JRParameter.REPORT_LOCALE, new Locale("pt","BR"));
				GeraRelatorio geraRelatorio = new GeraRelatorio(nomeArquivo, params);
				geraRelatorio.generateReports();
			}
		});
		
		btnRelatorio_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteService clienteService = new ClienteService();
				List<Cliente> listaCliente = clienteService.listarTodosClientes();
				String nomeArquivo = "relatorio_maquina2";
				Map<String, Object> params = new HashMap<String, Object>();
				params.put(JRParameter.REPORT_LOCALE, new Locale("pt","BR"));
				GeraRelatorio geraRelatorio = new GeraRelatorio(nomeArquivo, params, listaCliente);
				geraRelatorio.callReport();
			}
		});
	}
	
	private void leiaPortas() {
		Conexao conexao = new Conexao();
		
		List<String> portasSistema = new ArrayList<>();
		
		portasSistema = conexao.leiaPortas();
		
		if (portasSistema.isEmpty() ) {
			JOptionPane.showMessageDialog(null,"Nenhuma porta encontrada! - Verifique", 
					                      "Erro", JOptionPane.ERROR_MESSAGE);
		}
		
	
		portaComboBox.setModel(new DefaultComboBoxModel<String>(
				                     portasSistema.toArray(new String[portasSistema.size()]))
				               );
	}
	
	private void leiaBaudRate() {
		baudRateComboBox.setModel(new DefaultComboBoxModel<String>(this.getBaudRate()));
	}
	
	
	private void leiaDataBits() {
		dataBitsComboBox.setModel(new DefaultComboBoxModel<String>(this.dataBits));
	}
	
	
	private void leiaParidade() {
		paridadeComboBox.setModel(new DefaultComboBoxModel<String>(this.getParidade()));
	}
	
	
	private void leiaStopBits() {
		stopBitsComboBox.setModel(new DefaultComboBoxModel<String>(this.getStopBits()));
	}
	
	
	private void checaBotao() {
		if(estados == 0) {
			btnLiga.setEnabled(true);
			btnDesliga.setEnabled(false);
			btnAvancaEtapa.setEnabled(false);
		}else {
			btnLiga.setEnabled(false);
			btnDesliga.setEnabled(true);
			btnAvancaEtapa.setEnabled(true);
		}
		 
	}
	private void criarConexao(ActionEvent e) {
		
		if (Objects.isNull(conexao)) {
			if (getIntBaudRate()==0) {
				conexao = new Conexao();
			} else {
				conexao = new Conexao(getIntBaudRate());
			}
			portOpen = conexao.openConnetion(getPortaComboBox().getSelectedItem().toString());
		}
  	
	}
	
	protected ComunicacaoMaquina pegarDadosMaquinaFromTela() {
		ComunicacaoMaquina maquina = new ComunicacaoMaquina();
		Date date=new Date();
		
		maquina.setEstado(txtEstado.getText());
		maquina.setData(date);
        return maquina;		
	}
	
	private void enviarMensagemDesliga(ActionEvent e) {
		Thread tarefa = new Thread() {
			String c="D";
			public void run() {
			          conexao.sendData(c);
			
			          estados = 0;
			          atualizaLabel();
			          checaBotao(); 
			          try {
			        	  Thread.sleep(10000);
			          }catch (InterruptedException e) {
					}
			}
		};
		tarefa.start();
	}
	
	private void enviarMensagemLiga(ActionEvent e) {
		Thread tarefa = new Thread() {
			String c="L";
			public void run() {
			          conexao.sendData(c);
			          estados = 1;
			          atualizaLabel();
			          checaBotao(); 
			          try {
			        	  Thread.sleep(10000);
			          }catch (InterruptedException e) {
					}
			}
		};
		tarefa.start();
	}
	
	private void avancaEtapa(ActionEvent e) {
		Thread tarefa = new Thread() {
			String c="A";
			
			public void run() {
				if(estados != 0) {
						estados = estados + 1;
						atualizaLabel();
						checaBotao();
			          conexao.sendData(c);
			          try {
			        	  Thread.sleep(10000);
			          }catch (InterruptedException e) {
					}
				}
			}
		};
		tarefa.start();
	}
	
	private void atualizaLabel() {
		
		MaquinaService maquinaService = new MaquinaService();
		ComunicacaoMaquina maquina = new ComunicacaoMaquina();
		Date date = new Date();
		 
		txtData.setText("Data: "+date);	
		if(estados >= 9 ) {
			estados = 0;
		}		
		if(estados == 0) {
			
			txtEstado.setText("Desligado");
			
		}else if(estados == 1) {
			txtEstado.setText("Encher");
			
			
		}else if(estados == 2) {
			txtEstado.setText("Agitar");
			conexao.sendData("2");
		}else if(estados == 3) {
			txtEstado.setText("Drenar");
			conexao.sendData("3");
		}else if(estados == 4) {
			txtEstado.setText("Centrifugar");
			conexao.sendData("4");
		}else if(estados == 5) {
			txtEstado.setText("Enxague");
			conexao.sendData("5");
		}else if(estados == 6) {
			txtEstado.setText("Agitar");
			conexao.sendData("6");
		}else if(estados == 7) {
			txtEstado.setText("Drenar");
			conexao.sendData("7");
		}else if(estados == 8) {
			txtEstado.setText("Centrifugar");
			conexao.sendData("8");
		}
		maquina = pegarDadosMaquinaFromTela();
		maquinaService.salvarMaquina(maquina);
	}
	
	
	private void initComponents() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 558, 361);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 536, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(77, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 301, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(44, Short.MAX_VALUE))
		);
		
		JLayeredPane layeredPane = new JLayeredPane();
		tabbedPane.addTab("Conexão", null, layeredPane, null);
		
		JLabel lblPorta = new JLabel("Porta:");
		lblPorta.setBounds(34, 34, 50, 14);
		layeredPane.add(lblPorta);
		
		portaComboBox = new JComboBox<String>();
		portaComboBox.setBounds(94, 34, 349, 20);
		layeredPane.add(portaComboBox);
		
		JLabel lblBaudRate = new JLabel("Baud Rate:");
		lblBaudRate.setBounds(10, 65, 74, 14);
		layeredPane.add(lblBaudRate);
		
		baudRateComboBox = new JComboBox<String>();
		baudRateComboBox.setBounds(94, 65, 349, 20);
		layeredPane.add(baudRateComboBox);
		
		JLabel lblDataBits = new JLabel("Data Bits:");
		lblDataBits.setBounds(17, 103, 67, 14);
		layeredPane.add(lblDataBits);
		
		dataBitsComboBox = new JComboBox<String>();
		dataBitsComboBox.setBounds(94, 103, 349, 20);
		layeredPane.add(dataBitsComboBox);
		
		JLabel lblParidade = new JLabel("Paridade:");
		lblParidade.setBounds(17, 134, 67, 14);
		layeredPane.add(lblParidade);
		
		paridadeComboBox = new JComboBox<String>();
		paridadeComboBox.setBounds(94, 134, 349, 20);
		layeredPane.add(paridadeComboBox);
		
		JLabel lblStopBits = new JLabel("Stop Bits:");
		lblStopBits.setBounds(17, 172, 67, 14);
		layeredPane.add(lblStopBits);
		
		stopBitsComboBox = new JComboBox<String>();
		stopBitsComboBox.setBounds(94, 172, 349, 20);
		layeredPane.add(stopBitsComboBox);
		
		btnConectar = new JButton("Conectar");
		btnConectar.setBounds(115, 215, 110, 23);
		layeredPane.add(btnConectar);
		
		btnDesconectar = new JButton("Desconectar");
		btnDesconectar.setBounds(261, 215, 110, 23);
		layeredPane.add(btnDesconectar);
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				criarConexao(e);
			}
		});
		
		layeredPane_1 = new JLayeredPane();
		tabbedPane.addTab("Controle Maquina", null, layeredPane_1, null);
		
		txtEstado = new JTextField();
		txtEstado.setEditable(false);
		txtEstado.setBounds(111, 68, 211, 20);
		layeredPane_1.add(txtEstado);
		txtEstado.setColumns(10);
		
		btnLiga = new JButton("Liga");
		btnLiga.setBounds(64, 99, 85, 23);
		layeredPane_1.add(btnLiga);
		
		btnDesliga = new JButton("Desliga");
		btnDesliga.setBounds(184, 99, 85, 23);
		layeredPane_1.add(btnDesliga);
		
		 btnAvancaEtapa = new JButton("Avançar Etapa");
		 btnAvancaEtapa.setBounds(293, 99, 137, 23);
		 layeredPane_1.add(btnAvancaEtapa);
		 
		 txtData = new JTextField();
		 txtData.setEditable(false);
		 txtData.setBounds(111, 37, 211, 20);
		 layeredPane_1.add(txtData);
		 txtData.setColumns(10);
		 
		 btnRelatorio = new JButton("Relatorio");
		 btnRelatorio.setBounds(104, 159, 120, 23);
		 layeredPane_1.add(btnRelatorio);
		 
		 btnRelatorio_2 = new JButton("Relatorio 2");
		 btnRelatorio_2.setBounds(234, 159, 120, 23);
		 layeredPane_1.add(btnRelatorio_2);
		 
		 JLabel lblDataEHora = new JLabel("Data e Hora:");
		 lblDataEHora.setBounds(26, 40, 75, 14);
		 layeredPane_1.add(lblDataEHora);
		 
		 JLabel lblEstadoAtual = new JLabel("Estado Atual:");
		 lblEstadoAtual.setBounds(26, 71, 75, 14);
		 layeredPane_1.add(lblEstadoAtual);
		 btnAvancaEtapa.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		avancaEtapa(e);
		 	}
		 });
		btnDesliga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enviarMensagemDesliga(e);
			}
		});
		btnLiga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				enviarMensagemLiga(e);
				
			}

			
		});
		contentPane.setLayout(gl_contentPane);
		
		leiaPortas();
		atualizaLabel();
		//Date data = new Date();
		//txtData.setText("Data: "+data);
		
	}

	public JComboBox<String> getPortaComboBox() {
		return portaComboBox;
	}

	public void setPortaComboBox(JComboBox<String> portaComboBox) {
		this.portaComboBox = portaComboBox;
	}

	public JComboBox<String> getBaudRateComboBox() {
		return baudRateComboBox;
	}

	public void setBaudRateComboBox(JComboBox<String> baudRateComboBox) {
		this.baudRateComboBox = baudRateComboBox;
	}

	public JComboBox<String> getDataBitsComboBox() {
		return dataBitsComboBox;
	}

	public void setDataBitsComboBox(JComboBox<String> dataBitsComboBox) {
		this.dataBitsComboBox = dataBitsComboBox;
	}

	public JComboBox<String> getParidadeComboBox() {
		return paridadeComboBox;
	}

	public void setParidadeComboBox(JComboBox<String> paridadeComboBox) {
		this.paridadeComboBox = paridadeComboBox;
	}

	public JComboBox<String> getStopBitsComboBox() {
		return stopBitsComboBox;
	}

	public void setStopBitsComboBox(JComboBox<String> stopBitsComboBox) {
		this.stopBitsComboBox = stopBitsComboBox;
	}

	public String[] getBaudRate() {
		return baudRate;
	}

	public void setBaudRate(String[] baudRate) {
		this.baudRate = baudRate;
	}

	public String[] getDataBits() {
		return dataBits;
	}

	public void setDataBits(String[] dataBits) {
		this.dataBits = dataBits;
	}

	public String[] getParidade() {
		return paridade;
	}

	public void setParidade(String[] paridade) {
		this.paridade = paridade;
	}

	public String[] getStopBits() {
		return stopBits;
	}

	public void setStopBits(String[] stopBits) {
		this.stopBits = stopBits;
	}

	public boolean isPortOpen() {
		return portOpen;
	}

	public void setPortOpen(boolean portOpen) {
		this.portOpen = portOpen;
	}

	public int getIntBaudRate() {
		return intBaudRate;
	}

	public void setIntBaudRate(int intBaudRate) {
		this.intBaudRate = intBaudRate;
	}

	public int getIntDataBits() {
		return intDataBits;
	}

	public void setIntDataBits(int intDataBits) {
		this.intDataBits = intDataBits;
	}

	public int getIntParidade() {
		return intParidade;
	}

	public void setIntParidade(int intParidade) {
		this.intParidade = intParidade;
	}

	public int getIntStopBits() {
		return intStopBits;
	}

	public void setIntStopBits(int intStopBits) {
		this.intStopBits = intStopBits;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}
}
