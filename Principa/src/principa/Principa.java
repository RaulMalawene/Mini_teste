
package principa;

import javax.swing.SwingUtilities;


public class Principa {

   
    public static void main(String[] args) {
           SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            new Calculador();
        }
    });
    }
    
}
