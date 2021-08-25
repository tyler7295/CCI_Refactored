/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author tdogp
 */
public class Cci_refactoredController implements Initializable {

    @FXML
    private TextField numField;

    @FXML
    private ImageView imagePane;

    @FXML
    private Label numLabel;

    @FXML
    private Label validLabel;

    @FXML
    private Label expLabel;

    @FXML
    private TextField expField;

    @FXML
    private Label ccvLabel;

    @FXML
    private TextField ccvField;

    @FXML
    private Button verify;
    
    @FXML
    private URL url;
    
    @FXML
    private ResourceBundle rb;

    public Cci_refactoredController() {
    }
    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void verifyCard() throws FileNotFoundException {
        String number = numField.getText();
        String exp = expField.getText();
        String ccv = ccvField.getText();
        if (isValidCreditCardNumber(number).equals("visa") && isValidExpDate(exp) == true && isValidCcv(ccv).equals("base")) {
            InputStream stream = new FileInputStream("VISA-Card.png");
            Image image = new Image(stream);
            imagePane.setImage(image);
            validLabel.setText("Valid");
        }
        
        else if (isValidCreditCardNumber(number).equals("amex") && isValidExpDate(exp) == true && isValidCcv(ccv).equals("amex")) {
            InputStream stream = new FileInputStream("american_express_logo_7.jpg");
            Image image = new Image(stream);
            imagePane.setImage(image);
            validLabel.setText("Valid");
        }
        
        else if (isValidCreditCardNumber(number).equals("master") && isValidExpDate(exp) == true && isValidCcv(ccv).equals("base")) {
            InputStream stream = new FileInputStream("Mastercard logo.png");
            Image image = new Image(stream);
            imagePane.setImage(image);
            validLabel.setText("Valid");
        }
        
        else if (isValidCreditCardNumber(number).equals("jcb") && isValidExpDate(exp) == true && isValidCcv(ccv).equals("base")) {
            InputStream stream = new FileInputStream("download (1).jpg");
            Image image = new Image(stream);
            imagePane.setImage(image);
            validLabel.setText("Valid");
        }
        
        else {
            validLabel.setText("Invalid");
        }
    }

    public String isValidCreditCardNumber(String text) {
        String jcbNum = "^([3]{1}[5]{1}[0-9]{14})";
        String visaNum = "^([4]{1}[0-9]{15})";
        String masterNum = "^([5]{1}[0-9]{15})";
        String amexNum = "^([3]{1}[4,7]{1}[0-9]{14})";

        Pattern jcbNumCheck = Pattern.compile(jcbNum);
        Pattern visaNumCheck = Pattern.compile(visaNum);
        Pattern masterNumCheck = Pattern.compile(masterNum);
        Pattern amexNumCheck = Pattern.compile(amexNum);

        Matcher jcb = jcbNumCheck.matcher(text);
        Matcher visa = visaNumCheck.matcher(text);
        Matcher master = masterNumCheck.matcher(text);
        Matcher amex = amexNumCheck.matcher(text);

        if (jcb.matches()) {
            return "jcb";
        } else if (visa.matches()) {
            return "visa";
        } else if (master.matches()) {
            return "master";
        } else if (amex.matches()) {
            return "amex";
        } else {
            return "Error";
        }
    }

    public boolean isValidExpDate(String text) {
        String expDate = text.replace("/", "");
        String dateCheck = "^([0]{1}[0-9]{5})";
        String dateCheck2 = "^([1]{1}[0-2]{1}[0-9]{4})";
        Pattern date = Pattern.compile(dateCheck);
        Pattern date2 = Pattern.compile(dateCheck2);
        Matcher dateMatch = date.matcher(expDate);
        Matcher dateMatch2 = date2.matcher(expDate);

        if (dateMatch.matches() || dateMatch2.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public String isValidCcv(String text) {
        String baseCcv = "^([0-9]{3})";
        String amexCcv = "^([0-9]{4})";

        Pattern baseCcvCheck = Pattern.compile(baseCcv);
        Pattern amexCcvCheck = Pattern.compile(amexCcv);

        Matcher base = baseCcvCheck.matcher(text);
        Matcher amex2 = amexCcvCheck.matcher(text);

        if (base.matches()) {
            return "base";
        } else if (amex2.matches()) {
            return "amex";
        } else {
            return "Error";
        }
    }

}
