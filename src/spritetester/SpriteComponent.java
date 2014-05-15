package spritetester;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;

/**
 *
 * @author Peixoto
 */
public class SpriteComponent extends JComponent {
    private final Thread spriteThread;
    private Image spritePiece;
    private final BufferedImage spriteImage;
    private int x = 0, y = 0, indexX = 0, indexY = 0;
    private int width, height;
    
    public SpriteComponent(Image spriteImage, int numberOfSteps, final int width, final int height, final int delayBySteps) {
        this.spriteImage = (BufferedImage) spriteImage;
        this.width = width;
        this.height = height;
        spriteThread = new Thread(new Runnable() {

            @Override
            public void run() {
                while(true) {
                    ++indexX;
                    if(indexX*width >= SpriteComponent.this.spriteImage.getWidth()) {
                        indexX = 0;
                        indexY++;
                    }
                    else if(indexY*height >= SpriteComponent.this.spriteImage.getHeight()) {
                        indexX = 0;
                        indexY = 0;
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(SpriteComponent.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    try {
                        repaint();
                        Thread.sleep(delayBySteps);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SpriteComponent.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }    
            }
        }); spriteThread.start();
    }
    
    public void paintComponent(Graphics g) {
        g.drawImage(spriteImage, 0, 0, width, height, indexX*width, indexY*height, indexX*width+width, indexY*height+height, null);
    }
    
}
