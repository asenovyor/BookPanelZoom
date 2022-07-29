/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookpanelzoom;

import be.savat.components.JBookPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
/**
 *
 * @author Asenov
 */
public class BookPanelZoom {
    
    private static int book_page_width  = 350;
    private static int book_page_height = 500;
  
    public static void main(String[] args) 
    {
  
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double wd = screenSize.getWidth();
        double hd = screenSize.getHeight();
          
        int w1 = (int)( (wd - 740 ) / 2 );  
        int h1 = (int)( (hd - 600 ) / 2 );        
        
        int book_page_width_scale  = 35;
        int book_page_height_scale = 50;
          
        JBookPanel newPaper = new JBookPanel() 
        {

            @Override
            protected Image loadPage(int index) 
            {
               return new ImageIcon(BookPanelZoom.class.getResource(pageLocation + pageName
                        + index + "." + pageExtension)).getImage();
            }
        };
         
    //  newPaper.setPages("", "2103DS1-DS", "jpg", 5, 350, 500);
        newPaper.setPages("", "2103DS1-DS", "jpg", 5, book_page_width, book_page_height);
        newPaper.setMargins(0, 0);
        newPaper.setBackground(new Color(157,185,235));
        newPaper.setLeftPageIndex(-1);
             

        JScrollPane panel = new JScrollPane( newPaper );

        newPaper.setPreferredSize(new Dimension(740, 600));
        
        panel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        panel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            

        JFrame f = new JFrame();
        
        f.getContentPane().add( panel, BorderLayout.CENTER);
        
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 //     f.getContentPane().add(newPaper);
        f.setSize(new Dimension(740, 600));
        f.setResizable( true );
//      f.setLocationRelativeTo(null);
        f.setLocation(w1, h1 );
        f.setTitle("Тефтерчето на Васил Левски");
 //     f.setVisible(true);
        
        f.addComponentListener(new ComponentAdapter() 
        {
            public void componentResized(ComponentEvent e) 
            {
               //    System.out.println("Size Changed");
                
              int fw = f.getWidth();
              int fh = f.getHeight();
        
              int bw = book_page_width * 2 + 40;
              int bh = book_page_height + 100;
        
              int npw, nph;
        
              if ( fw > bw ) npw = fw;
              else           npw = bw;
        
              if ( fh > bh ) nph = fh;
              else           nph = bh;        
          
              newPaper.setPages("", "2103DS1-DS", "jpg", 5, book_page_width, book_page_height);                  
              newPaper.setPreferredSize(new Dimension( npw , nph ) );
        

              f.invalidate();
              f.revalidate();
              f.repaint();                
            }
        });

        JPanel btnPanel = new JPanel();
            
        
        JButton plusBtn  = new JButton("+");
        JButton minusBtn = new JButton("-");

        
        plusBtn.setPreferredSize(new Dimension(50, 30)); 
        minusBtn.setPreferredSize(new Dimension(50, 30)); 
        
        btnPanel.add(plusBtn);
        btnPanel.add(minusBtn);
 
        
        f.getContentPane().add(btnPanel, BorderLayout.SOUTH);   

        //f.pack();
        
        f.setVisible(true);        
        
              
        
      plusBtn.addActionListener((ActionEvent ae) -> 
      {
       
        book_page_width += book_page_width_scale;
        book_page_height += book_page_height_scale;
        
        int fw = f.getWidth();
        int fh = f.getHeight();
        
        int bw = book_page_width * 2 + 40;
        int bh = book_page_height + 100;
        
        int npw, nph;
        
        if ( fw > bw ) npw = fw;
        else           npw = bw;
        
        if ( fh > bh ) nph = fh;
        else           nph = bh;        
          
        newPaper.setPages("", "2103DS1-DS", "jpg", 5, book_page_width, book_page_height);                  
        newPaper.setPreferredSize(new Dimension( npw , nph ) );
        
        //newPaper.setPreferredSize(new Dimension( 2000 , 2000 ) );
        
         System.out.format("The sum of %d and %d  is %d . " ,fw, bw, npw);

        f.invalidate();
        f.revalidate();
        f.repaint();
           
       });    
      
      minusBtn.addActionListener((ActionEvent ae) -> 
      {
        if ( book_page_width == 350 ) return;
        
        book_page_width -= book_page_width_scale;
        book_page_height -= book_page_height_scale;
        
        int fw = f.getWidth();
        int fh = f.getHeight();
        
        int bw = book_page_width * 2 + 40;
        int bh = book_page_height + 100;
        
        int npw, nph;
        
        if ( fw > bw ) npw = fw;
        else           npw = bw;
        
        if ( fh > bh ) nph = fh;
        else           nph = bh;        
          
        newPaper.setPages("", "2103DS1-DS", "jpg", 8, book_page_width, book_page_height);                  
        newPaper.setPreferredSize(new Dimension( npw , nph ) );

        f.invalidate();
        f.revalidate();
        f.repaint();
        
      });       

      
    }
    
}


