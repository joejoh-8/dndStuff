package dndStuffGUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.NumberFormatter;

import dndStuffRandom.DnDrand;

public class GUI {
	private JPanel outPanel;
	private JTextArea display;

	public GUI(DnDrand rand) {

		JFrame frame = new JFrame("Main menu");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BoxLayout boxLayout = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);
		frame.setLayout(boxLayout);

		outPanel = new JPanel();
		outPanel.setBorder(new TitledBorder(new EtchedBorder(), "Output"));
		display = new JTextArea(20, 20);
		display.setEditable(false);
		JScrollPane scroll = new JScrollPane(display);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		outPanel.add(scroll);

		JComboBox generators = new JComboBox(rand.generators);
		generators.setSelectedIndex(0);
		Dimension genSize = new Dimension(200, 30);
		generators.setMinimumSize(genSize);
		generators.setPreferredSize(genSize);
		generators.setMaximumSize(genSize);

		NumberFormat longFormat = NumberFormat.getIntegerInstance();
		NumberFormatter numberFormatter = new NumberFormatter(longFormat);
		numberFormatter.setValueClass(Long.class);
		numberFormatter.setAllowsInvalid(false);
		numberFormatter.setMinimum(0l);
		JFormattedTextField seedIN = new JFormattedTextField(numberFormatter);
		Dimension InSize = new Dimension(200, 20);
		seedIN.setMinimumSize(InSize );
		seedIN.setPreferredSize(InSize );
		seedIN.setMaximumSize(InSize );

		JButton genButton = new JButton("Generera mera!");
		genButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					long seed;
					try {
						seed = (long) seedIN.getValue();
					} catch (Exception e) {
						seed = 0;
					}
					int[] temp = rand.Generate(generators.getSelectedIndex(), seed);
					String tempString = "";
					for (int i = 0; i < 6; i++) {
						tempString = tempString + temp[i] + " ";
					}
					tempString = tempString + "\n";
					display.append(tempString);
				} catch (Exception e) {
					display.append("Error\n");
				}

			}

		});

		Box genBox = Box.createVerticalBox();
		genBox.add(generators);
		genBox.add(seedIN);
		genBox.add(genButton);
		genBox.add(outPanel);
		frame.add(genBox);

//		Box bigBox = Box.createHorizontalBox();
//		bigBox.add(genBox);
//		bigBox.add(outPanel);
//		frame.add(bigBox);
		
		Dimension fSize = new Dimension(300, 475);
		frame.setMinimumSize(fSize);
		frame.setLocation(200, 100);
		frame.pack();
		frame.setVisible(true);
	}
}
