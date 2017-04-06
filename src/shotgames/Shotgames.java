package shotgames;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.Border;

public class Shotgames extends JFrame implements ActionListener{
    JLabel background;//背景图
    ShotJPanel main_interface;//主面板
    Timer m_timer;//计时器
    JLabel m_label;//要射击的对象    
    JButton m_pause;//开始、暂停    
    JLabel time_label;//时间
    JTextField time_text;//时间显示    
    JLabel score_label;//时间
    JTextField score_text;//时间显示
    JLabel max_score_label;//最高分
    JTextField max_score_text;//最高分显示
    JPanel temp;
    int max_score=0;
    int m_frame=0;
    int m_score=0;
    int x=0;
    int y=0;
    int changeIcon=0;
    boolean m_frozen=true;
    boolean judge=true;
    Image image; 
    ImageIcon ic2;
    public Shotgames(){
        super("平面射击游戏");
        image = new ImageIcon("1.jpg").getImage();
        background=new JLabel();
        main_interface = new ShotJPanel(image);        
        m_timer=new Timer(60,this); 
        m_label=new JLabel();    
        m_pause=new JButton("开始");
        time_label=new JLabel("剩余时间：");
        time_text=new JTextField(Integer.toString(m_frame));
        score_label=new JLabel("得分：");
        score_text=new JTextField(Integer.toString(m_score));
        max_score_label=new JLabel("最高分：");
        max_score_text=new JTextField(Integer.toString(max_score));
        temp=new JPanel();  
    }
    void showP()
    {
        if(changeIcon==3)
            changeIcon=0;
        else
            changeIcon++;
        switch(changeIcon)
        {
        case 0:ic2=new ImageIcon("010.gif");break;
        case 1:ic2=new ImageIcon("011.gif");break;
        case 2:ic2=new ImageIcon("012.gif");break;
        case 3:ic2=new ImageIcon("013.gif");break;
        } 
        //x=(int)(Math.random()*main_interface.getWidth()-100);
        //y=(int)(Math.random()*main_interface.getHeight()-100);
        //if(x<100)
            //x=x+100;
        //if(y<100)            
            //y=y+100;
        if(m_frame==0)
        {
            mb_stopAnimation();
            m_pause.setText("开始");  
            if(m_score>max_score)
                max_score=m_score;
            m_score=0;            
            judge=!judge;
            m_frozen=true;
            ic2=new ImageIcon("gameover.gif");
            m_label.setIcon(ic2);                    
        }               
    }
    void setMyshape(){
        setMiddle();  
        Container c=getContentPane();
        Border b1 = BorderFactory. createLineBorder(Color.blue, 2); 
        Border b2 = BorderFactory. createEtchedBorder(); 
        main_interface.setBorder(BorderFactory. createCompoundBorder(b1, b2));
        GridBagLayout layout = new GridBagLayout();
        c.setLayout(layout);        
        c.add(main_interface); 
        c.add(m_pause);
        c.add(time_label);
        c.add(time_text);
        c.add(score_label);
        c.add(score_text);
        c.add(max_score_label);
        c.add(max_score_text);
        c.add(temp);
        GridBagConstraints s= new GridBagConstraints();
        s.fill = GridBagConstraints.BOTH;
        s.gridwidth=7;
        s.gridheight=8;
        s.weightx = 1;
        s.weighty=1;
        layout.setConstraints(main_interface, s);
        s.gridwidth=0;
        s.gridheight=1;
        s.weightx =0;
        s.weighty=0;
        layout.setConstraints(m_pause, s);
        layout.setConstraints(time_label, s);
        layout.setConstraints(time_text, s);
        layout.setConstraints(score_label, s);
        layout.setConstraints(score_text, s);
        layout.setConstraints(max_score_label, s);
        layout.setConstraints(max_score_text, s);
        layout.setConstraints(temp, s);  
        main_interface.add(m_label);
    }
    void startGame(){
        setMyshape();
        m_timer.setInitialDelay(0);
        m_timer.setCoalesce(true);          
        m_pause.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                m_frozen=!m_frozen;
                if(m_frozen) 
                {
                    mb_stopAnimation();
                    m_pause.setText("开始");                    
                }
                else
                {                    
                    if(judge)
                    {                        
                        m_score=0;
                        time_text.setText(Integer.toString(m_frame));       
                        m_frame=300;
                        score_text.setText(Integer.toString(m_score));
                        max_score_text.setText(Integer.toString(max_score));
                        judge=!judge;
                    }
                    mb_startAnimation();                        
                    m_pause.setText("暂停");                    
                }
            }            
        });        
        m_label.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                if(!m_frozen){
                    m_score++;
                    m_frame=m_frame+1000;
                    score_text.setText(Integer.toString(m_score));                   
                }
            }
        });   
        main_interface.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e)
                {
                    main_interface.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));                    
                }
        });
    }    
    @Override
    public void actionPerformed(ActionEvent e){
        if(m_frame%15==0)
            showP();
        time_text.setText(Integer.toString(m_frame));         
        m_frame--;
        m_label.setIcon(ic2);
        m_label.setLocation(180,280);
    }
    public void start()
    {
        mb_startAnimation();
    }
    public void stop()
    {
        mb_stopAnimation();
    }
    public void mb_startAnimation(){
        if(!m_frozen && !m_timer.isRunning())
            m_timer.start();
    }
    public void mb_stopAnimation(){
        if(m_timer.isRunning())
            m_timer.stop();
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
    public static void main(String[] args){
        Shotgames app=new Shotgames();   
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
        app.setSize(640,500);        
        app.startGame();
        MyExplanation dia=new MyExplanation(app,"游戏说明");
        dia.setSize(300,200);        
        dia.setMiddle();
        app.setVisible(true);
        dia.setVisible(true);
    }   
}
