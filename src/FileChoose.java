import javax.swing.*;
import java.awt.*;
import java.io.File;

public class FileChoose extends Component {
    public String fileLocation() {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int choice = jFileChooser.showOpenDialog(this);
        if(choice == jFileChooser.APPROVE_OPTION) {
            File chosenFile = jFileChooser.getSelectedFile();
            return chosenFile.getAbsolutePath();
        }
        return "";
    }
}
