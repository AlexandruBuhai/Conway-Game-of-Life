import gameoflifeproject.GameOfLifeForm;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/* Icon - https://icons8.com/license CC BY-ND 3.0 No changes made 
package gameoflifeproject;
*/
/**
 *
 * @author Alexandru Buhai
 */
public class GameOfLifeProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        GameOfLifeProject app = new GameOfLifeProject();
        app.startApp();
      
    }
    public void startApp()
    {
        System.out.println("Good day! Dobrý den! Buna ziua");
        
        String workingDir = System.getProperty("user.dir");
        workingDir = workingDir + "/images/icons-c.png";
        //System.out.println(workingDir);
        Image image = Toolkit.getDefaultToolkit().getImage(workingDir);
        GameOfLifeForm obj = new GameOfLifeForm();
        
        obj.setIconImage(image);
        obj.setLocationRelativeTo(null);
        obj.setVisible(true);
    }
    
}
