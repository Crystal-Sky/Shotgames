
package shotgames;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class MyExplanation extends JDialog
{
    JButton determine;
    JLabel explanation;
    JLabel picture;
    ImageIcon ic2;
    JDialog d;
    public MyExplanation(Frame owner,String title)
    {
        super(owner,title,true);    
        determine=new JButton();
        explanation=new JLabel();
        picture=new JLabel();
        showP();
        
    }
    void showP()
    {
        Container c=getContentPane();
        ic2=new ImageIcon("011.gif");
        picture.setIcon(ic2);
        explanation.setText("用鼠标操作，点击小鸟，每点中一次得一分");
        determine.setText("确定");
        GridBagLayout layout = new GridBagLayout();
        c.setLayout(layout); 
        c.add(picture);
        c.add(explanation);
        c.add(determine);  
        GridBagConstraints s= new GridBagConstraints();
        s.fill = GridBagConstraints.NONE;
        s.gridwidth=0;
        s.gridheight=1;
        s.weightx =0;
        s.weighty=0;
        layout.setConstraints(picture, s);
        layout.setConstraints(explanation, s);
        layout.setConstraints(determine, s);
        determine.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                dispose();
            }
        });
    }    
    public void setMiddle(){
        //将主窗口设置在中间
        int Width=this.getWidth();                               //获得窗口宽
        int Height=this.getHeight();                             //获得窗口高
        Toolkit kit = Toolkit.getDefaultToolkit();              //定义工具包
        Dimension screenSize = kit.getScreenSize();             //获取屏幕的尺寸
        int screenWidth = screenSize.width;                     //获取屏幕的宽
        int screenHeight = screenSize.height;                   //获取屏幕的高
        this.setLocation(screenWidth/2-Width/2, screenHeight/2-Height/2);//设置窗口居中显示
    }
}
