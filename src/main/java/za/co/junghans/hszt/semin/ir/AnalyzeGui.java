package za.co.junghans.hszt.semin.ir;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.DateFormat;
import java.util.Date;

/**
 * User: dkummer
 * Date: 30.06.11
 * Time: 10:24
 */
public class AnalyzeGui {
    private JPanel mainPanel;
    private JTextField txtKeywords;
    private JButton btnCalculate;
    private JTextArea txtResult;
    private JTextField txtUrl;
    private JButton btnPaste;
    private JButton btnCopy;
    private JTextArea txtStatus;

    private static AnalyzeGui _instance;

    public static AnalyzeGui getInstance() {
        if (null == _instance) {
            _instance = new AnalyzeGui();
        }
        return _instance;
    }

    private AnalyzeGui() {
        txtKeywords.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                super.focusGained(focusEvent);
                txtKeywords.setText("");
            }
        });
    }

    public void setActionListeners(ActionListener analyzeActionListener, ActionListener clipboardListener) {
        btnCalculate.addActionListener(analyzeActionListener);
        btnPaste.addActionListener(clipboardListener);
        btnCopy.addActionListener(clipboardListener);
    }

    public String getUrl() {
        return txtUrl.getText();
    }

    public void setUrl(String url) {
        this.txtUrl.setText(url);
    }

    public void setResult(String result) {
        txtResult.setText(result);
    }

    public String getResult() {
        return txtResult.getText();
    }

    public String getKeywords() {
        return txtKeywords.getText();
    }

    public void log(String text) {
        String time = DateFormat.getTimeInstance().format(new Date());
        txtStatus.append(time + ":" + text + "\n");
    }


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new FormLayout("fill:d:noGrow,left:4dlu:noGrow,fill:d:grow,left:4dlu:noGrow,fill:max(d;4px):noGrow", "center:d:noGrow,top:3dlu:noGrow,center:max(d;4px):noGrow,top:3dlu:noGrow,center:max(d;4px):noGrow,top:3dlu:noGrow,center:max(d;4px):noGrow,top:3dlu:noGrow,center:max(d;4px):noGrow"));
        mainPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), null));
        final JLabel label1 = new JLabel();
        label1.setText("URL");
        CellConstraints cc = new CellConstraints();
        mainPanel.add(label1, cc.xy(1, 1));
        final JLabel label2 = new JLabel();
        label2.setText("Keywords");
        mainPanel.add(label2, cc.xy(1, 3));
        txtKeywords = new JTextField();
        txtKeywords.setText("keyword1, keyword2, keyword3");
        mainPanel.add(txtKeywords, cc.xy(3, 3, CellConstraints.FILL, CellConstraints.DEFAULT));
        btnCalculate = new JButton();
        btnCalculate.setText("Calculate");
        btnCalculate.setMnemonic('C');
        btnCalculate.setDisplayedMnemonicIndex(0);
        mainPanel.add(btnCalculate, cc.xy(3, 5));
        final JLabel label3 = new JLabel();
        label3.setText("Result");
        mainPanel.add(label3, cc.xy(1, 7));
        final JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setVerticalScrollBarPolicy(22);
        mainPanel.add(scrollPane1, cc.xy(3, 7, CellConstraints.FILL, CellConstraints.FILL));
        txtResult = new JTextArea();
        txtResult.setEditable(false);
        txtResult.setRows(15);
        scrollPane1.setViewportView(txtResult);
        final JLabel label4 = new JLabel();
        label4.setText("Status");
        mainPanel.add(label4, cc.xy(1, 9));
        txtUrl = new JTextField();
        txtUrl.setText("http://www.tagesanzeiger.ch");
        mainPanel.add(txtUrl, cc.xy(3, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
        btnPaste = new JButton();
        btnPaste.setText("Paste");
        btnPaste.setMnemonic('P');
        btnPaste.setDisplayedMnemonicIndex(0);
        mainPanel.add(btnPaste, cc.xy(5, 1));
        btnCopy = new JButton();
        btnCopy.setText("Copy");
        btnCopy.setMnemonic('O');
        btnCopy.setDisplayedMnemonicIndex(1);
        mainPanel.add(btnCopy, cc.xy(5, 7));
        final JScrollPane scrollPane2 = new JScrollPane();
        scrollPane2.setVerticalScrollBarPolicy(22);
        mainPanel.add(scrollPane2, cc.xy(3, 9, CellConstraints.FILL, CellConstraints.FILL));
        txtStatus = new JTextArea();
        txtStatus.setEditable(false);
        txtStatus.setEnabled(true);
        txtStatus.setLineWrap(false);
        txtStatus.setRows(5);
        txtStatus.setWrapStyleWord(false);
        scrollPane2.setViewportView(txtStatus);
        label1.setLabelFor(txtUrl);
        label2.setLabelFor(txtKeywords);
        label3.setLabelFor(txtResult);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
