package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Element extends JPanel {
	/*
	 * element contains gui parts - so it is its own suitable element
	 * for the ActionDisplayPanel
	 */
	
	// element attributes
	private String name;								// name of the company 
	private URL url;									// url from webpage
	private URLConnection urlCon;					
	private InputStreamReader isr;
	private BufferedReader br;
	private String value = "not found";					// variable for the value
	private double[] values =  new double[10];
	private boolean firstInitialization = true;
	private int valuePos = 0;
	private String line;				
	private String containment;							// webpage line that contains the value
	private String searchAfterContain;					// dot(full stop) or comma to search for as a starting point
	private char searchForChar;							// the char that is followed by value 
	private int startOffset;							// offset before the dot or comma
	private int endOffset;								// offset after dot or comma
	// gui elements
	private JLabel tendency = new JLabel("      ");
	private JLabel object;
	private JLabel diffToYes = new JLabel("         ");
	private JLabel valueLabel = new JLabel(value);
	private JLabel status = new JLabel();
	private Dimension defDim = new Dimension(400, 50);
	
	public Element(final String name, final String urlString, final String containment, final String searchAfterContain,
			final char searchForChar, final int startOffset, final int endOffset) throws IOException {
		// initial constructor to create the element + initial value assignment
		this.name = name;
		this.url = new URL(urlString);
		this.containment = containment;
		this.searchAfterContain = searchAfterContain;
		this.searchForChar = searchForChar;
		this.startOffset = startOffset;
		this.endOffset = endOffset;
		setLayout(new GridLayout(0,5));
		object = new JLabel(name);
		urlCon = url.openConnection();
		isr = new InputStreamReader(urlCon.getInputStream());
		br = new BufferedReader(isr);
		// basic settings
		setMinimumSize(defDim);
		setPreferredSize(defDim);
		// add components
		add(tendency);
		add(object);
		add(diffToYes);
		add(valueLabel);
		add(status);
		evaluateData();
	}
	
	public String fetchDataFromWebpage() throws IOException {
		// searches for the requested line + value and returns the value as a string
		line = br.readLine();
		while(line != null) {
			if(line.contains(containment)) {
				int target = line.indexOf(containment);
				int deci = line.indexOf(searchAfterContain, target);
				int start = deci;
				while (line.charAt(start) != searchForChar) {
					start--;
				}
				value = line.substring(start + startOffset, deci + endOffset);
				//valueLabel.setText(value);
			}
			line = br.readLine();
		}
		return value;
	}
	
	public double convertStringToDouble() throws IOException {
		// converts the string in appropriate double form and returns it		
		String rawString = fetchDataFromWebpage();
		// check if string contains fullstop and remove it
		// furthermore remove the comma and replace it by the fullstop
		int fullstopPos = rawString.indexOf('.');
		int commaPos = rawString.indexOf(',');
		String noFullstop = "";
		String commaFullstopBuffer = "";
		String finalConvertableString = "";
		// case 1: the String contains the fullstop at the wrong position
		if(fullstopPos != -1) {
			noFullstop = rawString.substring(0, fullstopPos) + rawString.substring(fullstopPos + 1);
			if(commaPos != -1) {
				finalConvertableString = noFullstop.substring(0, commaPos - 1) + '.' + noFullstop.substring(commaPos + 1) + 'd';
				// to not trigger case 2
				commaPos = -1; 
			}
		}
		// case 2: string not containing fullstop but comma to be replaced by fullstop
		if(commaPos != -1) {
			finalConvertableString = rawString.substring(0, commaPos) + '.' + rawString.substring(commaPos + 1) + 'd';
		}
		// convert the convertable String to Double
		double finalValue = Double.parseDouble(finalConvertableString);
		System.out.println(finalValue);
		return finalValue;
	}
	
	public void evaluateData() throws IOException {
		// evaluates the data and saves the last 10 values in an array for further use
		values[valuePos] = convertStringToDouble();
		
		/*
		 * set label value + label color
		 * value will be compared to previous value. no changes: yello, higher value: green, lower value: red
		 */
		
		if(!firstInitialization) {
			// special case: compare to field slot 9 
			if(valuePos == 0) {
				if(values[valuePos] == values[9]) {
					valueLabel.setText("<html><font color='yellow'>" + values[valuePos] + "</font></html>");
				} else if (values[valuePos] < values[9]) {
					valueLabel.setText("<html><font color='red'>" + values[valuePos] + "</font></html>");
				} else if (values[valuePos] > values[9]) {
					valueLabel.setText("<html><font color='green'>" + values[valuePos] + "</font></html>");
				}
			} else {
				if(values[valuePos] == values[valuePos - 1]) {
					valueLabel.setText("<html><font color='yellow'>" + values[valuePos] + "</font></html>");
				} else if (values[valuePos] < values[valuePos - 1]) {
					valueLabel.setText("<html><font color='red'>" + values[valuePos] + "</font></html>");
				} else if (values[valuePos] > values[valuePos - 1]) {
					valueLabel.setText("<html><font color='green'>" + values[valuePos] + "</font></html>");
				}
			}
		}
		
		if(firstInitialization) {
			valueLabel.setText("<html><font color='black'>" + values[valuePos] + "</font></html>");
		}
		
		if(valuePos == 9) {
			valuePos = 0;
		} else valuePos++;
		
		firstInitialization = false;
		
	}
}
