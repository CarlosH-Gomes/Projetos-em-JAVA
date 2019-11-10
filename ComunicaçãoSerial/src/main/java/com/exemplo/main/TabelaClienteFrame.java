package com.exemplo.main;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.PatternSyntaxException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableRowSorter;

import com.exemplo.model.Cliente;
import com.exemplo.model.TabelaClienteModel;
import com.exemplo.reports.GeraRelatorio;
import com.exemplo.service.ClienteService;

import net.sf.jasperreports.engine.JRParameter;

public class TabelaClienteFrame extends JFrame {

	private static final long serialVersionUID = -352389726581513577L;

	
	private static final int ID       = 0;
	private static final int NOME     = 1;
	private static final int BAIRRO   = 2;
	private static final int CIDADE   = 3;
	private static final int ENDERECO = 4;
	private static final int CEP      = 5;
	private static final int NUMERO   = 6;
	
	private static final int VAZIO   = -1; 

	
	private JPanel contentPane;
	private JTable tabelaCliente;
	private JButton btnIncluir;
	private JButton btnBuscar;
	
	private JComboBox<String> comboBox;
	private JButton btnPrimeiro;
	private JButton btnAnterior;
	private JButton btnProximo;
	private JButton btnUltimo;
	
    private TabelaClienteModel tabelaClienteModel;
    private TableRowSorter<TabelaClienteModel> sortTabelaCliente;
    
    private JButton btnAlterar;
    private JTextField textFieldNome;
    
	private Integer totalData = 0;
	private Integer defaultPagina = 5;
	private Integer totalPagina = 1;
	private Integer numeroPagina = 1;
	private JButton btnRelatorio;
	private JButton btnRelatorio_1;
    
    
    
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					for ( LookAndFeelInfo laf : UIManager.getInstalledLookAndFeels()) {
						if ("Nimbus".equals(laf.getName())){
							UIManager.setLookAndFeel(laf.getClassName());
						} else {
							System.out.println();
						}
						
					}
					TabelaClienteFrame frame = new TabelaClienteFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public TabelaClienteFrame() {
		initComponents();
		createEvents();
	}
	
	private void createEvents() {
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteFrame clienteFrame = new ClienteFrame(new JFrame(), tabelaCliente, tabelaClienteModel, 0, 0);
				clienteFrame.setLocationRelativeTo(null);
				clienteFrame.setVisible(true);
			
			}
		});
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tabelaCliente.getSelectedRow()!= VAZIO && tabelaCliente.getSelectedRow() < tabelaClienteModel.getRowCount()) {
					int linhaSelecionada = tabelaCliente.getSelectedRow();
					System.out.println(linhaSelecionada);
					ClienteFrame clienteFrame = new ClienteFrame(new JFrame(), tabelaCliente, tabelaClienteModel, linhaSelecionada, 1);
					clienteFrame.setLocationRelativeTo(null);
					clienteFrame.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null,"Selecione um registro na tabela" ,"Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String filtro = textFieldNome.getText();
				filtrarCampos(filtro);
			}
		});
		btnPrimeiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numeroPagina = 1;
				loadDataClienteFromTable();
			}
		});
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (numeroPagina > 1) {
					numeroPagina = numeroPagina - 1;
					loadDataClienteFromTable();
				}
			}
		});
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 if (numeroPagina < totalPagina ) {
					 numeroPagina = numeroPagina + 1;
				     loadDataClienteFromTable();
				 }
			}
		});
		btnUltimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numeroPagina = totalPagina;
				loadDataClienteFromTable();
			}
		});
		btnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		     	String nomeArquivo = "relatorio_maquina";
				Map<String, Object> params = new HashMap<String, Object>();
				params.put(JRParameter.REPORT_LOCALE, new Locale("pt","BR"));
				GeraRelatorio geraRelatorio = new GeraRelatorio(nomeArquivo, params);
				geraRelatorio.generateReports();
			}
		});
		
		btnRelatorio_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteService clienteService = new ClienteService();
				List<Cliente> listaCliente = clienteService.listarTodosClientes();
				String nomeArquivo = "relatorio_maquina";
				Map<String, Object> params = new HashMap<String, Object>();
				params.put(JRParameter.REPORT_LOCALE, new Locale("pt","BR"));
				GeraRelatorio geraRelatorio = new GeraRelatorio(nomeArquivo, params, listaCliente);
				geraRelatorio.callReport();
			}
		});
	}


	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1129, 587);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		btnIncluir = new JButton("Incluir");
		btnAlterar = new JButton("Alterar");
		
		JLabel lblNome = new JLabel("Nome:");
		
		textFieldNome = new JTextField();
		textFieldNome.setColumns(10);
	    btnBuscar = new JButton("Buscar");
		
		JToolBar toolBar = new JToolBar();
		
		btnRelatorio = new JButton("Relatório");
		
		btnRelatorio_1 = new JButton("Relatorio_2");
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(48)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNome)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, 638, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnBuscar)
							.addGap(27)
							.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnIncluir)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnAlterar)
							.addGap(133)
							.addComponent(btnRelatorio)
							.addGap(31)
							.addComponent(btnRelatorio_1))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 946, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(109, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(36)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNome)
							.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnBuscar))
						.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(42)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 332, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnIncluir)
						.addComponent(btnAlterar)
						.addComponent(btnRelatorio)
						.addComponent(btnRelatorio_1))
					.addGap(13))
		);
		
		btnPrimeiro = new JButton("");
		btnPrimeiro.setIcon(new ImageIcon(TabelaClienteFrame.class.getResource("/com/exemplo/images/go-first.png")));
		toolBar.add(btnPrimeiro);
		
		btnAnterior = new JButton("");
		btnAnterior.setIcon(new ImageIcon(TabelaClienteFrame.class.getResource("/com/exemplo/images/go-previous.png")));
		toolBar.add(btnAnterior);
		
		comboBox = new JComboBox<String>();
		toolBar.add(comboBox);
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"5", "10", "15", "20"}));
		
		btnProximo = new JButton("");
		btnProximo.setIcon(new ImageIcon(TabelaClienteFrame.class.getResource("/com/exemplo/images/go-next.png")));
		toolBar.add(btnProximo);
		
		btnUltimo = new JButton("");
		btnUltimo.setIcon(new ImageIcon(TabelaClienteFrame.class.getResource("/com/exemplo/images/go-last.png")));
		toolBar.add(btnUltimo);
		
		tabelaCliente = new JTable();
		
		scrollPane.setViewportView(tabelaCliente);
		contentPane.setLayout(gl_contentPane);
		loadDataClienteFromTable();
		setLocationRelativeTo(null);
		
	}


	public void loadDataClienteFromTable() {
		
		totalData = buscarTodosRegistroCliente();
		defaultPagina = Integer.valueOf(comboBox.getSelectedItem().toString());
		Double totalPaginacao = Math.ceil(totalData.doubleValue()/defaultPagina.doubleValue());
		
		totalPagina = totalPaginacao.intValue();
	
		if (numeroPagina.equals(1)) {
			btnPrimeiro.setEnabled(false);
			btnProximo.setEnabled(false);
		} else {
			btnPrimeiro.setEnabled(true);
			btnProximo.setEnabled(true);
		}
		
		if (numeroPagina.equals(totalPagina)) {
			btnUltimo.setEnabled(false);
			btnProximo.setEnabled(false);
		} else {
			btnUltimo.setEnabled(true);
			btnProximo.setEnabled(true);
		}
		
		if (numeroPagina > totalPagina) {
			numeroPagina = 1;
		}
		
		tabelaClienteModel = new TabelaClienteModel();
		tabelaClienteModel.setListaClientes(listarTodosClientes(numeroPagina, defaultPagina));
		tabelaCliente.setModel(tabelaClienteModel);

		tabelaCliente.setFillsViewportHeight(true);
		tabelaCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		sortTabelaCliente = new TableRowSorter<TabelaClienteModel>(tabelaClienteModel);
		tabelaCliente.setRowSorter(sortTabelaCliente);
		
		tabelaClienteModel.fireTableDataChanged();
		
		tabelaCliente.getColumnModel().getColumn(ID).setWidth(11);
		tabelaCliente.getColumnModel().getColumn(NOME).setWidth(100);
		tabelaCliente.getColumnModel().getColumn(ENDERECO).setWidth(100);
		tabelaCliente.getColumnModel().getColumn(BAIRRO).setWidth(50);
		tabelaCliente.getColumnModel().getColumn(CIDADE).setWidth(100);
		tabelaCliente.getColumnModel().getColumn(CEP).setWidth(20);
		tabelaCliente.getColumnModel().getColumn(NUMERO).setWidth(5);
		
	}
	
	
	private Integer buscarTodosRegistroCliente() {
		ClienteService clienteService = new ClienteService();
		return clienteService.calcularTotalRegistros();
	}


	public List<Cliente> listarTodosClientes(Integer numeroPagina, Integer defaultPagina){
		ClienteService clienteService = new ClienteService();
		return clienteService.listarTodosRegistrosComPaginacao((defaultPagina * (numeroPagina - 1)), defaultPagina);
	}
	
	
	private void filtrarCampos(String filtro) {

		RowFilter<TabelaClienteModel, Object> rowFilter = null;
		try {
			rowFilter = RowFilter.regexFilter(filtro);
		}catch(PatternSyntaxException e) {
			return;
		}
        sortTabelaCliente.setRowFilter(rowFilter);
	}
}
