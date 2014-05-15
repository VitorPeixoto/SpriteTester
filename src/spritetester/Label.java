package spritetester;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Peixoto
 */
public class Label extends JLabel {
    
    
    @Override
    public void paintComponent(Graphics g) {
        if(getIcon() != null) g.drawImage(((ImageIcon)getIcon()).getImage(), 0, 0, getIcon().getIconWidth(), getIcon().getIconHeight(), null);
    }
}
