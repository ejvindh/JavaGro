/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package first;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 *
 * @author eh
 */
public class Gui implements Initializable {

	String datadir = new File("").getAbsolutePath()+"/data/";
	final String graph_path = "graphics/";
	String booksXmlFileName = "books.xml";
	String settingsFileName = "settings.xml";
	ArrayList<String[]> dictionaries = new ArrayList<String[]>();
	ArrayList<Integer> settings = new ArrayList<Integer>();
	String[][] results = new String[2][3];
	int dict_nr;
	boolean toDanish;
	boolean fromDanish;
	boolean eks;
	boolean rev;
	int doubflag;
	String lang_longname;
    WebEngine browser;
    int txt_size;
    String txt_size_begin;
    String txt_size_end;



	@FXML
	private TextField searchtxt;

	@FXML
	private ComboBox<String> lang;

	@FXML
	private Button search;

	@FXML
	private CheckBox fromDanishButton;

	@FXML
	private CheckBox toDanishButton;

	@FXML
	private CheckBox revButton;

	@FXML
	private CheckBox eksButton;

	@FXML
	private WebView browser_base;

	@FXML
	private MenuBar menubar;

	@FXML
	private MenuItem incrTxt;

	@FXML
	private MenuItem dimTxt;

	@FXML
	private void handleSearchSelect(ActionEvent event) {
    	String search_string = searchtxt.getText();
    	String get_html_txt = return_lookup(search_string);
    	browser.loadContent(get_html_txt);
	}

	@FXML
	private void handleLangSelect(ActionEvent event) {
		//Nyt sprog er valgt
    	for (int x=0; x<2; x++) {
    		for (int y=0; y<3; y++) {
    			results[x][y]="";
    		}
    	}
    	int selected_index = lang.getSelectionModel().getSelectedIndex();
    	if (selected_index >= 0) { //initialize kalder også hertil, og giver -1 -- spring da over nedenstående
    		dict_nr = selected_index;
	 		settings.set(0, dict_nr);
	    	String flag_name_1;
	    	String flag_name_2;
		    if (Integer.parseInt(dictionaries.get(dict_nr)[4]) > 1) {
		    	flag_name_1 = "da-" + dictionaries.get(dict_nr)[0]; //dict_shortname
		    	flag_name_2 = dictionaries.get(dict_nr)[0] + "-da"; //dict_shortname
			    fromDanishButton.setDisable(false);
			    toDanishButton.setDisable(false);
		    } else {
		    	flag_name_1 = dictionaries.get(dict_nr)[0]; //dict_shortname
		    	flag_name_2 = dictionaries.get(dict_nr)[0]; //dict_shortname
			    toDanishButton.setDisable(true);
		    }
		    revButton.setDisable((Integer.parseInt(dictionaries.get(dict_nr)[4]) & 1)<1);
		    fromDanishButton.setGraphic(new ImageView(
		    		new Image(getClass().getResourceAsStream(graph_path + flag_name_1 + ".png")))
		    );
		    toDanishButton.setGraphic(new ImageView(
		    		new Image(getClass().getResourceAsStream(graph_path + flag_name_2 + ".png")))
		    );
		    String get_html_txt=show_html_in_browser();
		    browser.loadContent(get_html_txt);
    		
    	}
	}

	@FXML
	private void handleSearchBtn(ActionEvent event) {
    	String search_string = searchtxt.getText();
    	String get_html_txt = return_lookup(search_string);
    	browser.loadContent(get_html_txt);
	}

	@FXML
	private void handlefromDanishButton(ActionEvent event) {
    	settings.set(2, (fromDanishButton.isSelected()? 1:0));
    	String get_html_txt=show_html_in_browser();
    	browser.loadContent(get_html_txt);
	}

	@FXML
	private void handletoDanishButton(ActionEvent event) {
    	settings.set(1, toDanishButton.isSelected()? 1:0);
    	String get_html_txt=show_html_in_browser();
    	browser.loadContent(get_html_txt);
	}

	@FXML
	private void handlerevButton(ActionEvent event) {
    	settings.set(4, revButton.isSelected()? 1:0);
    	String get_html_txt=show_html_in_browser();
    	browser.loadContent(get_html_txt);
	}

	@FXML
	private void handleeksButton(ActionEvent event) {
    	settings.set(3, eksButton.isSelected()? 1:0);
    	String get_html_txt=show_html_in_browser();
    	browser.loadContent(get_html_txt);
	}

	@FXML
	private void handleincrTxt(ActionEvent event) {
		txt_size = txt_size + 1;
		if (txt_size > 9) txt_size = 9;
		settings.set(5, txt_size);
		set_size_variables();
		String get_html_txt=show_html_in_browser();
    	browser.loadContent(get_html_txt);
	}

	@FXML
	private void handledimTxt(ActionEvent event) {
		txt_size = txt_size - 1;
		if (txt_size < 1) txt_size = 1;
		settings.set(5, txt_size);
		set_size_variables();
		String get_html_txt=show_html_in_browser();
    	browser.loadContent(get_html_txt);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		/* dictionaries.get(i)[0] = shortname
		 * dictionaries.get(i)[1] = [long]name
		 * dictionaries.get(i)[2] = gddfile
		 * dictionaries.get(i)[3] = datfile
		 * dictionaries.get(i)[4] = doubflag
		 * 	0 => envejs, uden reverse (storenda, stordaen, ret, frem, dansk, syn)
		 * 	1 => envejs, med reverse (vel nÃ¦ppe realistisk)
		 * 	2 => tovejs, uden reverse (no, it, fag)
		 * 	3 => tovejs, med reverse (en, ty, fr, sv, es)
		 *  settings.get(0) = hvilket nummer i dictionaries...
		 *  settings.get(1) = current_todan_flag_setting
		 *  settings.get(2) = current_fromdan_flag_setting
		 *  settings.get(3) = current_eks_flag_setting
		 *  settings.get(4) = current_rev_flag_setting
		 *  settings.get(5) = text_size */
		getDictionaries();
		if (dictionaries.size() > 0) {
			getSettings();
			dict_nr=settings.get(0);
			toDanish=settings.get(1)==1;
			fromDanish=settings.get(2)==1;
			eks=settings.get(3)==1;
			rev=settings.get(4)==1;
			txt_size=settings.get(5);
			set_size_variables();
			for (int x=0; x<2; x++) {
				for (int y=0; y<3; y++) {
					results[x][y]="";
				}
			}
			for (int i=0; i<dictionaries.size(); i++) {
				lang.getItems().addAll(dictionaries.get(i)[1]);
			}
			lang.setValue(dictionaries.get(dict_nr)[1]);
			
			fromDanishButton.setSelected(fromDanish);
			String flag_name_1;
		    if (Integer.parseInt(dictionaries.get(settings.get(0))[4]) > 1) {
		    	flag_name_1 = "da-" + dictionaries.get(settings.get(0))[0]; //dict_shortname
		    } else {
		    	flag_name_1 = dictionaries.get(settings.get(0))[0]; //dict_shortname
		    }
		    fromDanishButton.setGraphic(new ImageView(
		    		new Image(getClass().getResourceAsStream(graph_path + flag_name_1 + ".png")))
		    );

		    toDanishButton.setSelected(toDanish);
		    String flag_name_2;
		    if (Integer.parseInt(dictionaries.get(settings.get(0))[4]) > 1) {
		    	flag_name_2 = dictionaries.get(settings.get(0))[0] + "-da"; // dict_shortname
		    } else {
		    	flag_name_2 = dictionaries.get(settings.get(0))[0]; //dict_shortname;
		    }
		    toDanishButton.setGraphic(new ImageView(
		    		new Image(getClass().getResourceAsStream(graph_path + flag_name_2 + ".png")))
		    );
		    toDanishButton.setDisable(!(Integer.parseInt(dictionaries.get(settings.get(0))[4]) > 1));

		    eksButton.setSelected(eks);
		    revButton.setSelected(rev);
		    int rev_enabled = Integer.parseInt(dictionaries.get(settings.get(0))[4]) & 1;
		    revButton.setDisable(rev_enabled < 1);
		    browser = browser_base.getEngine();

	        Platform.runLater(() -> {
	            searchtxt.getScene().getAccelerators().put(
	                    new KeyCodeCombination(KeyCode.S, KeyCombination.SHORTCUT_DOWN),
	                    searchtxt::requestFocus);
	        });
	        searchtxt.setTooltip(new Tooltip("Shortcut: <ctrl/cmd d>"));

		} else {
			browser = browser_base.getEngine();
			browser.loadContent("Ingen ordbøger fundet. "
					+ "De skal ligge som angivet i books.xml-filen (i data-mappen)");
			searchtxt.setDisable(true);
			lang.setDisable(true);
			search.setDisable(true);
			fromDanishButton.setDisable(true);
			toDanishButton.setDisable(true);
			revButton.setDisable(true);
			eksButton.setDisable(true);
			menubar.setDisable(true);
			System.out.println("No dictionaries found!");
		}
		Platform.runLater(() -> {
			fromDanishButton.getScene().getAccelerators().put(
                    new KeyCodeCombination(KeyCode.F, KeyCombination.SHORTCUT_DOWN),
                    fromDanishButton::fire);
			toDanishButton.getScene().getAccelerators().put(
                    new KeyCodeCombination(KeyCode.T, KeyCombination.SHORTCUT_DOWN),
                    toDanishButton::fire);
			revButton.getScene().getAccelerators().put(
                    new KeyCodeCombination(KeyCode.R, KeyCombination.SHORTCUT_DOWN),
                    revButton::fire);
			eksButton.getScene().getAccelerators().put(
                    new KeyCodeCombination(KeyCode.E, KeyCombination.SHORTCUT_DOWN),
                    eksButton::fire);
            lang.getScene().getAccelerators().put(
                    new KeyCodeCombination(KeyCode.D, KeyCombination.SHORTCUT_DOWN),
                    lang::show);
            searchtxt.getScene().getAccelerators().put(
                    new KeyCodeCombination(KeyCode.L, KeyCombination.SHORTCUT_DOWN),
                    new Runnable() {
                        @Override
                        public void run() {
                            searchtxt.requestFocus();
                            searchtxt.selectAll();
                        }
                    }
                    );
        });
        fromDanishButton.setTooltip(new Tooltip("<ctrl/cmd f>"));
        toDanishButton.setTooltip(new Tooltip("<ctrl/cmd t>"));
        revButton.setTooltip(new Tooltip("<ctrl/cmd r>"));
        eksButton.setTooltip(new Tooltip("<ctrl/cmd e>"));
        lang.setTooltip(new Tooltip("<ctrl/cmd d>"));
        searchtxt.setTooltip(new Tooltip("<ctrl/cmd l>"));
        
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			//Slutter
			if (settings.size() > 0) write_xml(settings, datadir+settingsFileName);
		}));
	}
	
	private void getSettings() {
		// Hent settings fra sidst, eller lav en ny settings-file
		try {
			File xmlFile = new File(datadir + settingsFileName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("settings");
			Node nNode = nList.item(0);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				settings.add(Integer.parseInt(getTagValue("dict", eElement)));
				@SuppressWarnings("unused")
				String provoke_catch=dictionaries.get(settings.get(0))[0];
				//case: der er forsvundet en database siden sidste kørsel, og setting derfor nu
				//..peger på en database-fil, der ikke findes mere. Så ryger ovenstående linie i catch... ;-)
				settings.add(Integer.parseInt(getTagValue("todan", eElement)));
				settings.add(Integer.parseInt(getTagValue("fromdan", eElement)));
				settings.add(Integer.parseInt(getTagValue("eks", eElement)));
				settings.add(Integer.parseInt(getTagValue("rev", eElement)));
				settings.add(Integer.parseInt(getTagValue("txt_size", eElement)));
			}
				
		} catch (Exception e) {
			// Laver en ny settings-file, hvis der ikke findes en i forvejen
			System.out.println("Laver ny settings.xml, og indstiller til default...");
			settings.clear();
			settings.add(0);
			settings.add(1);
			settings.add(1);
			settings.add(1);
			settings.add(1);
			settings.add(5);
			write_xml(settings, datadir+settingsFileName);
		}
	}

	private static void write_xml(ArrayList<Integer> settings, String settings_filepath) {
		//Skriv indholdet af ArrayList til settings.xml-filen
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("settings");
			doc.appendChild(rootElement);

			Element dict = doc.createElement("dict");
			dict.appendChild(doc.createTextNode(settings.get(0).toString()));
			rootElement.appendChild(dict);

			Element todan = doc.createElement("todan");
			todan.appendChild(doc.createTextNode(settings.get(1).toString()));
			rootElement.appendChild(todan);

			Element fromdan = doc.createElement("fromdan");
			fromdan.appendChild(doc.createTextNode(settings.get(2).toString()));
			rootElement.appendChild(fromdan);

			Element eks = doc.createElement("eks");
			eks.appendChild(doc.createTextNode(settings.get(3).toString()));
			rootElement.appendChild(eks);

			Element rev = doc.createElement("rev");
			rev.appendChild(doc.createTextNode(settings.get(4).toString()));
			rootElement.appendChild(rev);

			Element t_size = doc.createElement("txt_size");
			t_size.appendChild(doc.createTextNode(settings.get(5).toString()));
			rootElement.appendChild(t_size);

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(settings_filepath));
			transformer.transform(source, result);
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}

	private void getDictionaries() {
		try {
			// Hvilke ordbøger findes på systemet?
			File xmlFile = new File(datadir + booksXmlFileName);
			if (!xmlFile.exists()) System.out.println(datadir + booksXmlFileName + " not found");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("book");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					boolean exists_gdd = (new File(getTagValue("gddfile", eElement))).exists();
					boolean exists_dat = (new File(getTagValue("datfile", eElement))).exists();
					if (exists_gdd && exists_dat) {
						dictionaries.add(new String [] {getTagValue("shortname", eElement), 
								getTagValue("name", eElement), getTagValue("gddfile", eElement), 
								getTagValue("datfile", eElement), getTagValue("doubflag", eElement)});
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String getTagValue(String sTag, Element eElement) {
		// Hent værdier fra xml-fil
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		return nValue.getNodeValue();
	}

	private String show_html_in_browser() {
		dict_nr = settings.get(0);
    	toDanish = settings.get(1)==1;
    	fromDanish = settings.get(2)==1;
    	eks = settings.get(3)==1;
    	rev = settings.get(4)==1;
    	doubflag = Integer.parseInt(dictionaries.get(dict_nr)[4]);
    	lang_longname = dictionaries.get(dict_nr)[1];
    	return show_results();
	}

	private String show_results() {
		//Sammenstykning af de relevante dele af opslaget -- fra Array til String
		/* results[0][0]=fromdanish_lookup
		 * results[0][1]=fromdanish_reverse
		 * results[0][2]=fromdanish_collocation_lookup (eksempelsÃ¦tninger)
		 * results[1][0]=todanish_lookup
		 * results[1][1]=todanish_reverse
		 * results[1][2]=todanish_collocation_lookup (eksempelsÃ¦tninger)
		 */
		ArrayList<String> opsamling = new ArrayList<String>();
		opsamling.add("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>" +
				"<html xmlns='http://www.w3.org/1999/xhtml' xml:lang='da' lang='da'>" +
				"<head><title>Opslag</title>" +
				"<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />" +
				"</head><body>" + txt_size_begin);
		if (results!=null) {
			if (doubflag==0 || doubflag==2) rev=false;
			if (fromDanish) {
				if (results[0][0].compareTo("")!=0) {
					if (doubflag>1) {
						opsamling.add("<h1>Dansk - " + lang_longname + "<br/><br/></h1>");
					} else {
						opsamling.add("<h1>" + lang_longname + "<br/><br/></h1>");
					}
					opsamling.add(results[0][0]);
					opsamling.add("<br/>");
				}
				if(rev && results[0][1].compareTo("")!=0) {
					opsamling.add("<h2>Forekomster i 'omvendt søgning'<br/></h2>");
					opsamling.add(results[0][1]);
					opsamling.add("<br/>");
				}
				if(eks && results[0][2].compareTo("")!=0) {
					opsamling.add("<h2>Eksempelsætninger<br/></h2>");
					opsamling.add(results[0][2]);
					opsamling.add("<br/><br/>");
				}
			}
			if (toDanish && doubflag>1) {
				if (results[1][0].compareTo("")!=0) {
					opsamling.add("<h1>" + lang_longname + "-Dansk<br/><br/></h1>");
					opsamling.add(results[1][0]);
					opsamling.add("<br/>");
				}
				if(rev && results[1][1].compareTo("")!=0) {
					opsamling.add("<h2>Forekomster i Dansk - 'omvendt søgning'<br/></h2>");
					opsamling.add(results[1][1]);
					opsamling.add("<br/>");
				}
				if(eks && results[1][2].compareTo("")!=0) {
					opsamling.add("<h2>Eksempelsætninger<br/></h2>");
					opsamling.add(results[1][2]);
				}
			}
		}
		opsamling.add(txt_size_end + "</body></html>");
		StringBuffer t_ = new StringBuffer();
		for (String t:opsamling) {
			t_.append(t);
		}
		String final_txt=t_.toString();
		System.out.println(final_txt);
		return final_txt;
	}

	private String return_lookup(String search_string) {
		dict_nr = settings.get(0);
    	toDanish = settings.get(1)==1;
    	fromDanish = settings.get(2)==1;
    	eks = settings.get(3)==1;
    	rev = settings.get(4)==1;
    	doubflag = Integer.parseInt(dictionaries.get(dict_nr)[4]);
    	lang_longname = dictionaries.get(dict_nr)[1];
    	String gdd_file_path = dictionaries.get(dict_nr)[2];
    	String dat_file_path = dictionaries.get(dict_nr)[3];
			try {
				results = Dictionary.lookup(toDanish, fromDanish, eks, rev, doubflag, 
						lang_longname, gdd_file_path, dat_file_path, search_string);
			} catch (ClassNotFoundException e) {
				// Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// Auto-generated catch block
				e.printStackTrace();
			}
    	return show_results();
	}

	private void set_size_variables() {
		int middlevalue = 5;
		if (txt_size > middlevalue) {
			txt_size_begin = "<big><big><big><big>".substring(0, 5*(txt_size-middlevalue));
			txt_size_end = "</big></big></big></big>".substring(0, 6*(txt_size-middlevalue));
		} else if (txt_size < middlevalue) {
			txt_size_begin = "<small><small><small><small>".substring(0, 7*(middlevalue-txt_size));
			txt_size_end = "</small></small></small></small>".substring(0, 8*(middlevalue-txt_size));
		} else {
			txt_size_begin = "";
			txt_size_end = "";
		}
	}
}