package GUI;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class CalcFrame extends JFrame implements ActionListener,KeyListener
{
 JButton bDig[]=new JButton[10];
 JButton bZro,bAdd,bSub,bMul,bDiv,bEql,bClr;
 JTextField tscr;
 long cur,prv;
 String op="";
 boolean shift;

 CalcFrame()
 {
  super("Calcu4lator");
  setSize(310,370);
  //setResizable();
  setLocation(300,200);

  CalcPanel cp=new CalcPanel(25,25,25,25);
  add(cp);

  tscr=new JTextField("0");
  tscr.setFont(new Font("comic sans",Font.PLAIN,25));
  tscr.setBackground(Color.black);
  tscr.setForeground(Color.white);
  tscr.setEditable(false);
  tscr.setHorizontalAlignment(JTextField.RIGHT);

  for(int i=0;i<10;i++)
  {
   bDig[i]=new JButton(i+"");
   bDig[i].setFont(new Font("lucida console",Font.PLAIN,15));
   bDig[i].setBackground(new Color(75,75,75));
   bDig[i].setForeground(Color.white);

  }

  bZro=new JButton("00");
  bZro.setFont(new Font("lucida console",Font.PLAIN,15));
  bZro.setBackground(new Color(75,75,75));
  bZro.setForeground(Color.white);

  bClr=new JButton("C");
  bClr.setFont(new Font("lucida console",Font.PLAIN,15));
  bClr.setBackground(new Color(250,100,100));
  bClr.setForeground(Color.white);

  bAdd=new JButton("+");
  bAdd.setFont(new Font("lucida console",Font.PLAIN,15));
  bAdd.setBackground(new Color(60,60,110));
  bAdd.setForeground(Color.white);

  bSub=new JButton("-");
  bSub.setFont(new Font("lucida console",Font.PLAIN,15));
  bSub.setBackground(new Color(60,60,110));
  bSub.setForeground(Color.white);

  bMul=new JButton("*");
  bMul.setFont(new Font("lucida console",Font.PLAIN,15));
  bMul.setBackground(new Color(60,60,110));
  bMul.setForeground(Color.white);

  bDiv=new JButton("/");
  bDiv.setFont(new Font("lucida console",Font.PLAIN,15));
  bDiv.setBackground(new Color(60,60,110));
  bDiv.setForeground(Color.white);
  
  bEql=new JButton("=");
  bEql.setFont(new Font("lucida console",Font.PLAIN,15));
  bEql.setBackground(new Color(150,150,60));
  bEql.setForeground(Color.white);
  
  CalcPanel p1=new CalcPanel(1,1,1,1);
  p1.setLayout(new GridLayout(1,4,10,10));
  p1.add(new JLabel());
  p1.add(new JLabel());
  p1.add(new JLabel());
  p1.add(bClr);

  CalcPanel p2=new CalcPanel(1,1,1,1);
  p2.setLayout(new GridLayout(1,4,10,10));
  p2.add(bDig[7]);
  p2.add(bDig[8]);
  p2.add(bDig[9]);
  p2.add(bAdd);

  CalcPanel p3=new CalcPanel(1,1,1,1);
  p3.setLayout(new GridLayout(1,4,10,10));
  p3.add(bDig[4]);
  p3.add(bDig[5]);
  p3.add(bDig[6]);
  p3.add(bSub);

  CalcPanel p4=new CalcPanel(1,1,1,1);
  p4.setLayout(new GridLayout(1,4,10,10));
  p4.add(bDig[1]);
  p4.add(bDig[2]);
  p4.add(bDig[3]);
  p4.add(bMul);

  CalcPanel p5=new CalcPanel(1,1,1,1);
  p5.setLayout(new GridLayout(1,4,10,10));
  p5.add(bDig[0]);
  p5.add(bZro);
  p5.add(bEql);
  p5.add(bDiv);
  
  cp.setLayout(new GridLayout(6,1,10,10));
  cp.add(tscr);
  cp.add(p1);
  cp.add(p2);
  cp.add(p3);
  cp.add(p4);
  cp.add(p5); 

  for(int i=0;i<10;i++)
  {
   bDig[i].addActionListener(this);
  }
  bZro.addActionListener(this);
  bAdd.addActionListener(this);
  bSub.addActionListener(this);
  bMul.addActionListener(this);
  bDiv.addActionListener(this);
  bEql.addActionListener(this);
  bClr.addActionListener(this);

  addKeyListener(this);

  setFocusable(true);
 }

 public void actionPerformed(ActionEvent ae)
 {
  if(ae.getSource()==bAdd)
  {
   evaluate();
   op="+";
  }
  else
  if(ae.getSource()==bSub)
  {
   evaluate();
   op="-";
  }
  else
  if(ae.getSource()==bMul)
  {
   evaluate();
   op="*";
  }
  else
  if(ae.getSource()==bDiv)
  {
   evaluate();
   op="/";
  }
  else
  if(ae.getSource()==bEql)
  {
   evaluate();
   op="=";
  }
  else
  if(ae.getSource()==bClr)
  {
   cur=prv=0;
   op="";
   tscr.setText("0");
  }
  else
  if(ae.getSource()==bZro)
  {
   cur=cur*100;
   tscr.setText(cur+""); 
  }
  else
  {
   JButton src=(JButton)ae.getSource();
   int d=Integer.parseInt(src.getText());
   cur=cur*10+d;
   tscr.setText(cur+"");

   if(op=="=")
   {
    prv=0;
    op="";
   }
  }
 }

 public void keyTyped(KeyEvent ke){}
 
 public void keyReleased(KeyEvent ke)
 {
  if(ke.getKeyCode()==16)
  {
   shift=false;
  }
 }

 public void keyPressed(KeyEvent ke)
 {
  int kc=ke.getKeyCode();

  switch(kc)
  {
   case  16 : shift=true;
              break;
   case  61 : if(shift)
               actionPerformed(new ActionEvent(bAdd,0,"+"));               
              else
               actionPerformed(new ActionEvent(bEql,0,"="));
              break;  
   case  56 :  if(shift)
                actionPerformed(new ActionEvent(bMul,0,"*"));               
              else
               actionPerformed(new ActionEvent(bDig[8],0,"8"));
              break; 
   case  48 :
   case  49 :
   case  50 :
   case  51 :
   case  52 :
   case  53 :
   case  54 :
   case  55 :
   case  57 : actionPerformed(new ActionEvent(bDig[kc-48],0,(kc-48)+""));
              break;
   case  45 : actionPerformed(new ActionEvent(bSub,0,"-"));
              break;
   case 127 : actionPerformed(new ActionEvent(bClr,0,"C"));
              break;
   case  10 : actionPerformed(new ActionEvent(bEql,0,"="));
              break;
   case  96 :
   case  97 :
   case  98 :
   case  99 :
   case 100 :
   case 101 :
   case 102 :
   case 103 :
   case 104 :
   case 105 : actionPerformed(new ActionEvent(bDig[kc-96],0,(kc-96)+""));
              break;
   case 106 : actionPerformed(new ActionEvent(bMul,0,"*"));
              break;
   case 107 : actionPerformed(new ActionEvent(bAdd,0,"+"));
              break;
   case 109 : actionPerformed(new ActionEvent(bSub,0,"-"));
              break;
   case 111 : actionPerformed(new ActionEvent(bDiv,0,"/"));
              break;
   case  27 : System.exit(0);
  }
 }

 void evaluate()
 {
  switch(op)
  {
   case "+" : prv=prv+cur;
              break;
   case "-" : prv=prv-cur;
              break;
   case "*" : prv=prv*cur;
              break;
   case "/" : prv=prv/cur;
              break;
   case "=" : 
              break;
   default  : prv=cur;
  }

  cur=0;
  tscr.setText(prv+"");
 }
}

class CalcPanel extends JPanel
{
 int top,left,bottom,right;
 CalcPanel(int top,int left,int bottom,int right)
 {
  this.top=top;
  this.left=left;
  this.bottom=bottom;
  this.right=right;
 }

 public void paintComponent(Graphics g)
 {
  super.paintComponent(g);
  setBackground(new Color(153,204,255));
 }

 public Insets getInsets()
 {
  return new Insets(top,left,bottom,right);
 }

}

class Calc
{
 public static void main(String arg[])
 {
  CalcFrame cf=new CalcFrame();
  cf.setVisible(true);
  cf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 }
}