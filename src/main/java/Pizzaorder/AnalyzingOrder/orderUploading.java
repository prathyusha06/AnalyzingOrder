package Pizzaorder.AnalyzingOrder;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Label;

public class orderUploading extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private File file;
	private JTextField textField_1;
    private String destinationPath;
	private String fileName;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					orderUploading frame = new orderUploading();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public orderUploading() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSourceFile = new JLabel("source File");
		lblSourceFile.setBounds(10, 28, 73, 14);
		contentPane.add(lblSourceFile);
		
		textField = new JTextField();
		textField.setBounds(80, 25, 249, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		Button button = new Button("select file");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int returnVal = fileChooser.showOpenDialog(fileChooser);
				List<String> words=new ArrayList<String>();
			
				if (returnVal == JFileChooser.APPROVE_OPTION) {
				     file = fileChooser.getSelectedFile();
				     if(file!=null) {
				    	textField.setText(file.getAbsolutePath());
                      fileName=file.getName();
                       
				     }
				}
			}
		});
		button.setBounds(354, 28, 70, 22);
		contentPane.add(button);
		
		Button button_1 = new Button("convert");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<String> dataOfOrder=new ArrayList<String>();
				
		    	Path path= Paths.get(file.getAbsolutePath());
                try {	
             	List<String> Data=Files.readAllLines(path);
             	FileWriter fw = new FileWriter(textField_1.getText()+"\\"+fileName);
    			BufferedWriter bw = new BufferedWriter(fw);
             	    bw.write(Data.get(0)+"\n");
             	    bw.newLine();
					Data.remove(0);
					Collections.sort(Data);
					for(String lines:Data) {
					/*lines=lines.replaceAll("/t/t", " ");*/
					String[] orderArray=lines.split("\t\t");
					if(orderArray.length==2) {
						//PrintWriter out = new PrintWriter(new FileWriter("file_name"));
						bw.write("\n"+orderArray[0]+"\t\t"+new Date((Long.valueOf(orderArray[1]))*1000));
						bw.newLine();
						dataOfOrder.add(orderArray[0]+"\t\t"+new Date((Long.valueOf(orderArray[1]))*1000));
					}
					
				}
					bw.close();
                } catch (IOException e1) {
					e1.printStackTrace();
				}
           
			}
		});
		button_1.setBounds(159, 151, 70, 22);
		contentPane.add(button_1);
		
		Label label = new Label("Destination");
		label.setBounds(10, 63, 62, 22);
		contentPane.add(label);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(80, 65, 249, 20);
		contentPane.add(textField_1);
		
		Button button_2 = new Button("Select Folder");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 JFileChooser chooser = new JFileChooser();
				    chooser.setDialogTitle("Select Directory");
				    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				    chooser.setAcceptAllFileFilterUsed(false);
				    int returnVal = chooser.showOpenDialog(chooser);
				    if (returnVal == JFileChooser.APPROVE_OPTION) {
					     
				    	File fileSave = chooser.getSelectedFile();
					     if(fileSave!=null) {
					    	textField_1.setText(chooser.getSelectedFile().toString());
					     }
					     }
				    destinationPath=chooser.getSelectedFile().toString();
			}
		});
		button_2.setBounds(354, 63, 70, 22);
		contentPane.add(button_2);
	}
}
