import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * @author Jason
 */
public class Main extends JFrame {

	private JTextArea jTextArea;
	private JMenuItem openItem, closeItem, saveItem, aboutItem;
	private JMenuItem compileItem, runItem;

	private FileDialog open, save;
	private File file;

	/**
	 * token表格
	 */
	private JTable tokenTable;
	String[] tokenColumnNames = {"符号类型", "所在行", "符号值"};
	private final TableModel tokenTableModel = new DefaultTableModel(tokenColumnNames, 0);

	/**
	 * symbol表格
	 */
	private JTable symbolTable;
	String[] symbolColumnNames = {"变量名", "变量类型", "变量值", "变量层次", "变量地址"};
	private final TableModel symbolTableModel = new DefaultTableModel(symbolColumnNames, 0);

	/**
	 * pcode表格
	 */
	private JTable pcodeTable;
	String[] pcodeColumnNames = {"F", "L", "A"};
	private final TableModel pcodeTableModel = new DefaultTableModel(pcodeColumnNames, 0);

	private JTextArea errorMessage;

	private Analysis gsa;
	private String consoleMessage;
	private boolean success = false;

	public Main() {
		init();
	}

	private void init() {
		JFrame frame = new JFrame("PL0Compiler");
		frame.setBounds(300, 300, 700, 450);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

		//菜单栏
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("文件");
		JMenu projectMenu = new JMenu("项目");
		JMenu helpMenu = new JMenu("帮助");

		jTextArea = new JTextArea(10, 40);
		jTextArea.setFont(new Font("Monospaced", Font.BOLD, 20));
		//到达指定宽度则换行
		jTextArea.setLineWrap(true);
		//应当首先利用构造函数指定JScrollPane的控制对象，此处为JTextArea，然后再添加JScrollPane
		//添加进面板
		JScrollPane jScrollPane = new JScrollPane(jTextArea);
		//设置滚动条自动出现
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jScrollPane.setViewportView(jTextArea);
		openItem = new JMenuItem("打开");
		saveItem = new JMenuItem("保存");
		closeItem = new JMenuItem("关闭");
		aboutItem = new JMenuItem("关于");
		compileItem = new JMenuItem("编译");
		runItem = new JMenuItem("运行");

		//添加两个选项卡到JMenu
		//添加字菜单项到菜单项
		menuBar.add(fileMenu);
		menuBar.add(projectMenu);
		menuBar.add(helpMenu);
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.add(closeItem);
		projectMenu.add(compileItem);
		projectMenu.add(runItem);
		helpMenu.add(aboutItem);

		//设置token表格
		tokenTable = new JTable(tokenTableModel);
		tokenTable.setPreferredScrollableViewportSize(new Dimension(300, 100));
		tokenTable.setFillsViewportHeight(true);
		JScrollPane tokenJScrollPane = new JScrollPane(tokenTable);

		//设置symbol表格
		symbolTable = new JTable(symbolTableModel);
		symbolTable.setPreferredScrollableViewportSize(new Dimension(300, 100));
		symbolTable.setFillsViewportHeight(true);
		JScrollPane symbolJScrollPane = new JScrollPane(symbolTable);

		//设置pcode表格
		pcodeTable = new JTable(pcodeTableModel);
		pcodeTable.setPreferredScrollableViewportSize(new Dimension(300, 100));
		pcodeTable.setFillsViewportHeight(true);
		JScrollPane pcodeJScrollPane = new JScrollPane(pcodeTable);

		/*
		  放置所有表格
		 */
		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new GridLayout(0, 1));
		tablePanel.add(tokenJScrollPane);
		tablePanel.add(symbolJScrollPane);
		tablePanel.add(pcodeJScrollPane);

		//出错信息
		errorMessage = new JTextArea();
		JScrollPane errorPane = new JScrollPane(errorMessage);
		errorPane.setPreferredSize(new Dimension(700, 100));

		//放置菜单项及输入框
		frame.add(menuBar, BorderLayout.NORTH);
		frame.add(jScrollPane, BorderLayout.CENTER);
		frame.add(tablePanel, BorderLayout.EAST);
		frame.add(errorPane, BorderLayout.SOUTH);

		open = new FileDialog(frame, "打开文档", FileDialog.LOAD);
		save = new FileDialog(frame, "保存文档", FileDialog.SAVE);

		event();
		frame.setVisible(true);
	}

	private void event() {
		closeItem.addActionListener(e -> System.exit(0));

		aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(null, "PL0Compiler\n"
				+ "made by shiyi001\ni_am_shiyi@163.com"));

		//菜单条目监听：打开
		openItem.addActionListener(e -> {
			open.setVisible(true);

			String dirPath = open.getDirectory();
			String fileName = open.getFile();
			if (dirPath == null || fileName == null) {
				return;
			}
			file = new File(dirPath, fileName);

			jTextArea.setText("");//打开文件之前清空文本区域

			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				String line;
				while ((line = br.readLine()) != null) {
					//将给定文本追加到文档结尾。如果模型为 null 或者字符串为 null 或空，则不执行任何操作。
					//虽然大多数 Swing 方法不是线程安全的，但此方法是线程安全的。
					jTextArea.append(line + "\r\n");
				}
			} catch (IOException ex) {
				throw new RuntimeException("读取失败！");
			}
		});

		//菜单条目监听：保存
		saveItem.addActionListener(e -> {
			if (file == null) {
				newFile();
			}
			saveFile();
		});

		compileItem.addActionListener(e -> compile());

		runItem.addActionListener(e -> {
			if (success) {
				//该函数还没有做好
				run();
			}
		});
	}

	/**
	 * 运行Pcode，目前不知道怎么传递输入，尤其是输入写在循环中时。
	 */
	private void run() {
		gsa.interpreter();
	}

	/**
	 * 编译
	 */
	private void compile() {
		if (file == null) {
			JOptionPane.showMessageDialog(null, "请先保存文件");
			newFile();
		}
		saveFile();
		gsa = new Analysis(file);
		clean();
		if (success = gsa.compile()) {
			displayAllToken();
			displayAllSymbol();
			displayAllPcode();
			consoleMessage += "compile succeed!\n";
			//consoleMessage += "请输入" + readNum + "个数，每行一个\n";
			errorMessage.setText(consoleMessage);
		} else {
			displayErrorMessage();
			consoleMessage += "compile failed!";
			errorMessage.setText(consoleMessage);
		}
	}

	/**
	 * 编译前清理一些表格和字符串
	 */
	private void clean() {
		flushTable(tokenTable);
		flushTable(symbolTable);
		flushTable(pcodeTable);
		errorMessage.setText("");
		consoleMessage = "";
		success = false;
	}

	private void displayErrorMessage() {
		consoleMessage = "";
		List<String> errors = gsa.getErrorMessage();
		for (String error : errors) {
			consoleMessage += error + "\n";
		}
	}

	private void displayAllToken() {
		DefaultTableModel model = (DefaultTableModel) tokenTable.getModel();
		List<Token> allToken = gsa.getAllToken();
		for (Token token : allToken) {
			Object[] rowValues = {token.getSt(), token.getLine(), token.getValue()};
			model.addRow(rowValues);
		}
	}

	private void displayAllSymbol() {
		DefaultTableModel model = (DefaultTableModel) symbolTable.getModel();
		List<PerSymbol> allSymbol = gsa.getAllSymbol();
		for (PerSymbol symbol : allSymbol) {
			Object[] rowValues = {symbol.getName(), symbol.getType(), symbol.getValue(), symbol.getLevel(), symbol.getAddress()};
			model.addRow(rowValues);
		}
	}

	private void displayAllPcode() {
		DefaultTableModel model = (DefaultTableModel) pcodeTable.getModel();
		List<PerPcode> allPcode = gsa.getAllPcode();
		for (PerPcode pcode : allPcode) {
			Object[] rowValues = {pcode.getF(), pcode.getL(), pcode.getA()};
			model.addRow(rowValues);
		}
	}

	private void flushTable(JTable table) {
		//清除表格数据
		((DefaultTableModel) table.getModel()).getDataVector().clear();
		//通知模型更新
		((DefaultTableModel) table.getModel()).fireTableDataChanged();
		//刷新表格
		table.updateUI();
	}

	/**
	 * 新建一个文件
	 */
	private void newFile() {
		if (file == null) {
			save.setVisible(true);
			String dirPath = save.getDirectory();
			String fileName = save.getFile();
			if (dirPath == null || fileName == null) {
				return;
			}
			file = new File(dirPath, fileName);
		}
	}

	/**
	 * 保存文件
	 */
	private void saveFile() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			String text = jTextArea.getText();
			bw.write(text);
			bw.close();
		} catch (IOException ex) {
			throw new RuntimeException();
		}
	}

	public static void main(String[] args) {
		new Main();
	}
}
