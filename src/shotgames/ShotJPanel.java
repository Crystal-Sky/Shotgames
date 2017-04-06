package shotgames;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;
public class ShotJPanel extends JPanel
{
    private final Image backgroundImage;     
    public ShotJPanel(Image image)
    {
        super();
        this.backgroundImage = image;        
        this.repaint();
    }
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        int width = this.getWidth();
        int height = this.getHeight();
        g.drawImage(backgroundImage, 0, 0, width, height, this);
    }
}


   