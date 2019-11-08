package com.exemplo.main;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

import com.exemplo.model.Cliente;
import com.exemplo.model.TabelaClienteModel;
import com.exemplo.service.ClienteService;

public class ClienteFrame extends JFrame {


	private static final long serialVersionUID = -7666904947797883899L;
	
	
	private JPanel contentPane;
	private JTextField nomeTextField;
	private JTextField enderecoTextField;
	private JTextField bairroTextField;
	private JTextField cidadeTextField;
	private JTextField telefoneTextField;
	private JTextField cepTextField;
	private JTextField numeroTextField;
	
	private JButton btnSalvar;

	private JButton btnExcluir;
	
	private JButton btnCancelar;
	private JTextField codigoTextField;


	private JTable tabelaCliente;
	private TabelaClienteModel tabelaClienteModel;
	private int linhaSelecionada;
	private int acao;
	
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { for ( LookAndFeelInfo laf :
	 * UIManager.getInstalledLookAndFeels()) { if ("Nimbus".equals(laf.getName())){
	 * UIManager.setLookAndFeel(laf.getClassName()); } else { System.out.println();
	 * }
	 * 
	 * } ClienteFrame frame = new ClienteFrame(); frame.setVisible(true); } catch
	 * (Exception e) { e.printStackTrace(); } } }); }
	 */

	public ClienteFrame() {
		
	}
	
	public ClienteFrame(JFrame frame, JTable tabelaCliente,
			            TabelaClienteModel tabelaClienteModel,
			            int linhaSelecionada,
			            int acao ) {
		
		this.tabelaCliente = tabelaCliente;
	    this.tabelaClienteModel = tabelaClienteModel;
	    this.linhaSelecionada = linhaSelecionada;
	    this.acao = acao;
	    
		initComponents();
		
		createEvents();
		
		configurarAcao();
	}


	private void createEvents() {
		codigoTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (verificarDigitacao(codigoTextField)) {
					consultarDadosCliente(Long.parseLong(codigoTextField.getText()));
				}
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					nomeTextField.requestFocus();
				}
			}
		});
		
		nomeTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					if ( verificarDigitacao(nomeTextField) ) {
					     JOptionPane.showMessageDialog(null, "O nome do cliente deve ser informado!");
					} else {        
						enderecoTextField.requestFocus();
					}
				}
			}
		});
		enderecoTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					numeroTextField.requestFocus();
				}
			}
		});
		numeroTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					bairroTextField.requestFocus();
				}
			}
		});
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				ClienteService clienteService = new ClienteService();
				Cliente cliente = pegarDadosClienteFromTela();
				if (cliente.getId() == null ) {
					clienteService.salvarCliente(cliente);
				} else {
					clienteService.alterarCLiente(cliente);
				}
				limparDadosDigitacao();
			}
		});

		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteService clienteService = new ClienteService();
				Cliente cliente = pegarDadosClienteFromTela();
				if (cliente.getId()!=null) {
					clienteService.excluirCliente(cliente);
				}
				limparDadosDigitacao();
			}
		});
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
	}
	                  
	protected Cliente pegarDadosClienteFromTela() {
		Cliente cliente = new Cliente();
		if ( (!codigoTextField.getText().isEmpty()) && !(codigoTextField.getText() == null) ) {
			cliente.setId(Long.valueOf(codigoTextField.getText()));
		}
		cliente.setNome(nomeTextField.getText());
		cliente.setBairro(bairroTextField.getText());
		cliente.setCep(cepTextField.getText());
		cliente.setEndereco(enderecoTextField.getText());
		cliente.setCidade(cidadeTextField.getText());
		cliente.setNumero(numeroTextField.getText());
		//cliente.setTelefone(telefoneTextField.getText());
        return cliente;		
	}
	
	         
	protected void pegarDadosClienteFromTabela() {
		
		Cliente cliente = getTabelaClienteModel().getCliente(getLinhaSelecionada());
		
		codigoTextField.setText(String.valueOf(cliente.getId()));
		nomeTextField.setText(cliente.getNome());
		bairroTextField.setText(cliente.getBairro());
		cepTextField.setText(cliente.getCep());
		enderecoTextField.setText(cliente.getEndereco());
		cidadeTextField.setText(cliente.getCidade());
		numeroTextField.setText(cliente.getNumero());
		//telefoneTextField.setText(cliente.getTelefone());
	}
	
	
	protected void limparDadosDigitacao() {
		codigoTextField.setText("");
		nomeTextField.setText("");
		bairroTextField.setText("");
		enderecoTextField.setText("");
		cepTextField.setText("");
		cidadeTextField.setText("");
		numeroTextField.setText("");
		telefoneTextField.setText("");
	}


	protected boolean verificarDigitacao(JTextField campo) {
	    boolean toReturn = true;
	    if ((campo.getText().isEmpty() || campo.getText() == null) ){
	    	toReturn = false;
	    }
		return toReturn;
	}
	
	protected void consultarDadosCliente(Long id) {
		ClienteService clienteService = new ClienteService();
		Cliente cliente = clienteService.consultarCliente(id);
		pegarDadosClienteFromTabela();
	}

	
	protected void configurarAcao() {
	
		if (getAcao() == 0) {     //incluir
			btnExcluir.setEnabled(false);
		} else if ( getAcao() == 1 )  {   //alterar
			btnExcluir.setEnabled(false);
			pegarDadosClienteFromTabela();
		} else {  //excluir
			pegarDadosClienteFromTabela();  
			btnSalvar.setEnabled(false);
		}
		
	}
	

	private void initComponents() {
		setTitle("Cadastro Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 994, 616);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNome = new JLabel("Nome:");
		
		nomeTextField = new JTextField();
		nomeTextField.setColumns(10);
		
		JLabel lblEndereo = new JLabel("Endereço:");
		
		enderecoTextField = new JTextField();


		enderecoTextField.setColumns(10);
		
		JLabel lblBairro = new JLabel("Bairro:");
		
		bairroTextField = new JTextField();
		bairroTextField.setColumns(10);
		
		JLabel lblCidade = new JLabel("Cidade:");
		
		cidadeTextField = new JTextField();
		cidadeTextField.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		
		telefoneTextField = new JTextField();
		telefoneTextField.setColumns(10);
		
		JLabel lblCep = new JLabel("Cep:");
		
		cepTextField = new JTextField();
		cepTextField.setColumns(10);
		
		JLabel lblNmero = new JLabel("Número:");
		
		numeroTextField = new JTextField();
		numeroTextField.setColumns(10);
		
		btnSalvar = new JButton("Salvar");
		btnExcluir = new JButton("Excluir");
		btnCancelar = new JButton("Cancelar");
		
		JLabel lblCdigo = new JLabel("Código:");
		
		codigoTextField = new JTextField();
		codigoTextField.setColumns(10);
		//codigoTextField.setEnabled(false);
	
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(90)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblCep)
						.addComponent(lblTelefone)
						.addComponent(lblCidade)
						.addComponent(lblBairro)
						.addComponent(lblNome)
						.addComponent(lblEndereo)
						.addComponent(lblCdigo))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnSalvar)
							.addGap(18)
							.addComponent(btnExcluir)
							.addGap(18)
							.addComponent(btnCancelar))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addComponent(nomeTextField, GroupLayout.PREFERRED_SIZE, 774, GroupLayout.PREFERRED_SIZE)
							.addComponent(telefoneTextField, GroupLayout.PREFERRED_SIZE, 383, GroupLayout.PREFERRED_SIZE)
							.addComponent(cepTextField, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(enderecoTextField, Alignment.LEADING)
									.addComponent(cidadeTextField, Alignment.LEADING)
									.addComponent(bairroTextField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE))
								.addGap(33)
								.addComponent(lblNmero)
								.addGap(18)
								.addComponent(numeroTextField)))
						.addComponent(codigoTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(37, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(41)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(codigoTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCdigo))
					.addGap(36)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(nomeTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNome))
					.addGap(32)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEndereo)
						.addComponent(enderecoTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNmero)
						.addComponent(numeroTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBairro)
						.addComponent(bairroTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCidade)
						.addComponent(cidadeTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(29)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblTelefone)
						.addComponent(telefoneTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblCep)
						.addComponent(cepTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(49)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSalvar)
						.addComponent(btnExcluir)
						.addComponent(btnCancelar))
					.addContainerGap(122, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
	}

	public JTable getTabelaCliente() {
		return tabelaCliente;
	}

	public void setTabelaCliente(JTable tabelaCliente) {
		this.tabelaCliente = tabelaCliente;
	}

	public TabelaClienteModel getTabelaClienteModel() {
		return tabelaClienteModel;
	}

	public void setTabelaClienteModel(TabelaClienteModel tabelaClienteModel) {
		this.tabelaClienteModel = tabelaClienteModel;
	}

	public int getLinhaSelecionada() {
		return linhaSelecionada;
	}

	public void setLinhaSelecionada(int linhaSelecionada) {
		this.linhaSelecionada = linhaSelecionada;
	}

	public int getAcao() {
		return acao;
	}

	public void setAcao(int acao) {
		this.acao = acao;
	}
	
	
	
	
}
