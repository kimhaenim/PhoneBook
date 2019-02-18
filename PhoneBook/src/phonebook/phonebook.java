package phonebook;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.JTextComponent;

class Phone{
   private String name;
   private String address;
   private String phoneNumber;
   public Phone(String name,String address, String phoneNumber) {
      this.name=name;
      this.address=address;
      this.phoneNumber=phoneNumber;
   }
   public String getaddress() {
      return address;
   }
   public String getphoneNumber() {
      return phoneNumber;
   }
}

class MyFrame extends JFrame{
   Container contentPane = getContentPane();
   JTextArea scrollpane = new JTextArea();
   JPanel menu = new JPanel();
   JButton Button[] = new JButton[5];
   
   JPanel name_phone_addr = new JPanel();
   JButton name = new JButton("�̸�");
   JButton phone = new JButton("��ȣ");
   JButton addr = new JButton("�ּ�");
   
   JPanel txt = new JPanel();
   JTextField nametxt = new JTextField("");
   JTextField phonetxt = new JTextField("");
   JTextField addrtxt = new JTextField("");
   
   HashMap<String, Phone> pb =new HashMap<String, Phone>();
   ArrayList<String> delname = new ArrayList<String>();
   
   public MyFrame() {
      setTitle("��ȭ��ȣ�� ���� ���α׷�");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      for(int i=0; i<5; i++) {
    	  Button[i]= new JButton("Button"+i);
    	  Button[i].setBackground(Color.ORANGE);
    	  Button[i].addActionListener(new MyActionListener());
    	  menu.add(Button[i]);
      }
      Button[0].setText("����");
      Button[1].setText("����");
      Button[2].setText("�˻�");
      Button[3].setText("��ȸ");
      Button[4].setText("����");
      
      name.setBackground(Color.ORANGE);
      phone.setBackground(Color.ORANGE);
      addr.setBackground(Color.ORANGE);
      
      contentPane.setBackground(Color.lightGray);
      contentPane.setLayout(null);
      
      JScrollPane scroll = new JScrollPane(scrollpane);
      scroll.setLocation(40,40);
      scroll.setSize(300,300);
      contentPane.add(scroll);
      
      menu.setLayout(new GridLayout(1,4,0,0));
            menu.setLocation(400,50);
      menu.setSize(440,40);
      contentPane.add(menu);
      
      name_phone_addr.setLayout(new GridLayout(3,1,5,5));
      name_phone_addr.setBackground(Color.lightGray);
      name_phone_addr.add(name);
      name_phone_addr.add(phone);
      name_phone_addr.add(addr);
      
      name_phone_addr.setLocation(400,120);
      name_phone_addr.setSize(100,200);
      contentPane.add(name_phone_addr);
      
      txt.setLayout(new GridLayout(3,1,5,5));
      txt.setBackground(Color.lightGray);
      txt.add(nametxt);
      txt.add(phonetxt);
      txt.add(addrtxt);
      
      txt.setLocation(520,120);
      txt.setSize(320,200);
      contentPane.add(txt);
      
      setSize(900,430);
      setVisible(true);
      
   }
   private class MyActionListener implements ActionListener{
      public void actionPerformed(ActionEvent e) {
         JButton b = (JButton)e.getSource();
         if (b.getText().equals("����")){
        	 scrollpane.setText("");
            Set<String> Keys = pb.keySet();
             Iterator<String> it = Keys.iterator();
             int count=0;
             while(it.hasNext()){
                if(nametxt.getText().equals(it.next())) {
                   count++;
                }
             }
             if (count>0) {
                scrollpane.append("�̹� �����ϴ� �̸��Դϴ�\n");
                nametxt.setText("");
                phonetxt.setText("");
                addrtxt.setText("");
                return;
             }
             pb.put(nametxt.getText(), new Phone(nametxt.getText(), phonetxt.getText(), addrtxt.getText()));
             scrollpane.append(nametxt.getText()+", "+phonetxt.getText()+", "+addrtxt.getText()+"\n"+"���ԿϷ�\n"); 
             
             for(int i=0; i<delname.size(); i++) {
                if(nametxt.getText().equals(delname.get(i))) {
                   delname.remove(i);
                }
             }
             nametxt.setText("");
             phonetxt.setText("");
             addrtxt.setText("");
         }
         else if(b.getText().equals("����")){
            scrollpane.setText("");
             int n= pb.size();
              if(n==0) {
                   scrollpane.append("��ϵ� ���/��ȣ�� �����ϴ�.\n");
               }else {
              Phone searchname = pb.get(nametxt.getText());
              for(int i=0; i<delname.size(); i++) {
                  if(nametxt.getText().equals(delname.get(i))) {
                     scrollpane.append(nametxt.getText()+"�� �̹� ������ ���/��ȣ�Դϴ�.\n");
                  }
               }
              if(searchname==null)
                 scrollpane.append("��ϵ��� ���� ���/��ȣ�Դϴ�.\n");
              else {
              pb.remove(nametxt.getText());
              delname.add(nametxt.getText());
             scrollpane.append(nametxt.getText()+", "+searchname.getphoneNumber()+", "+searchname.getaddress()+"�� �����Ǿ����ϴ�.\n");
               }
           }
              nametxt.setText("");
              phonetxt.setText("");
              addrtxt.setText("");
         }
         else if(b.getText().equals("�˻�")){
            scrollpane.setText("");
            Set<String> Keys = pb.keySet();
             Iterator<String> it = Keys.iterator();
             int n = pb.size();
             if (n==0) {
                scrollpane.append("��ϵ� ���/��ȣ�� �����ϴ�.\n");
             }else {
             Phone searchname = pb.get(nametxt.getText());
             for(int i=0; i<delname.size(); i++) {
                if(nametxt.getText().equals(delname.get(i))) {
                   scrollpane.append(nametxt.getText()+"�� �̹� ������ ���/��ȣ�Դϴ�.\n");
                }
             }
             if(searchname==null)
                 scrollpane.append(nametxt.getText()+"�� ��ϵ��� ���� ���/��ȣ�Դϴ�.\n");
             else {
                while(it.hasNext()) {
                   if(nametxt.getText().equals(it.next())){
                	   scrollpane.append(nametxt.getText()+", "+searchname.getaddress()+", "+searchname.getphoneNumber()+"\n");
                   }
                }
             }
             }
             nametxt.setText("");
             phonetxt.setText("");
             addrtxt.setText("");
             
         }
         else if(b.getText().equals("��ȸ")){
            scrollpane.setText("");
            
            Set<String> Keys = pb.keySet();
            Iterator<String> it = Keys.iterator();
            int n = pb.size();
            if(n==0) {
            	   scrollpane.append("��ϵ� ���/��ȣ�� �����ϴ�.\n");
                      }else {
                    	  
            while(it.hasNext()){
               String name = it.next();
               Phone searchname = pb.get(name);
               scrollpane.append(name+", "+searchname.getaddress()+", "+searchname.getphoneNumber()+"\n");
               }
            }
            nametxt.setText("");
            phonetxt.setText("");
            addrtxt.setText("");
         }
         else if (b.getText().equals("����")) {
            System.exit(0);
         }
     }
   }
   
}
public class phonebook {
   public static void main(String[] args) {
      MyFrame frame = new MyFrame();
   }
}
