package za.co.junghans.hszt.semin.ir;

import org.apache.log4j.Logger;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClipboardActionListener implements ActionListener {

    private Clipboard clipboard;
    private AnalyzeGui form;
    private Logger log = Logger.getLogger(ClipboardActionListener.class);

    public ClipboardActionListener() {
        this.form = AnalyzeGui.getInstance();
        clipboard = form.$$$getRootComponent$$$().getToolkit().getSystemClipboard();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getActionCommand().toLowerCase().contains("paste")) {
            Transferable clipData = clipboard.getContents(clipboard);
                if (clipData != null) {
                    try {
                        if (clipData.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                            String pasty = (String) (clipData.getTransferData(DataFlavor.stringFlavor));
                            form.setUrl(pasty);
                        }
                    } catch (Exception e) {
                        log.error(e);
                        form.log(e.getMessage());
                    }
                }
        } else if (actionEvent.getActionCommand().toLowerCase().contains("copy")) {
            StringSelection data = new StringSelection(form.getFoundKeywords());
            clipboard.setContents(data, data);
        } else {
            log.error("Unknown clipboard command");
        }
    }
}
