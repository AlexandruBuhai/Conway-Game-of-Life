package gameoflifeproject;
/* 
 * The MIT License
 *
 * Copyright 2018 abuhai.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
import gameoflifeproject.GameOfLifeForm;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

/* Icon - https://icons8.com/license CC BY-ND 3.0 No changes made 
package gameoflifeproject;
*/

/**
 * <h1>Conway's Game of Life Project</h1>
 * <p>
 * <b>Java Project for MFF UK under Assoc. Prof. Petr Hnětynka and PhD. Paolo Arcaini. For the course NPRG013.</b><br>
 * Conway game of life - A zero-player game, is a cellular automaton devised by
 * the the British mathematician John Horton Conway in 1970. It's a simple "game"
 * based on 4 basic rules:<br><br>
 * 
 * 1. Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.<br>
 * 2. Any live cell with two or three live neighbours lives on to the next generation.<br>
 * 3. Any live cell with more than three live neighbours dies, as if by overpopulation.<br>
 * 4. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction. <br>
 * </p>
 * <p> 
 * I chose this project because I was always interested in how patters form and how,
 * a simple set of rules can create complex pattern and shapes.
 * </p>
 * @author Alexandru Buhai
 * @version 1.1
 * @since 2018-01-17
 * <p>
 * <a href="https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life">
 * Game of Life </a> <br>
 * <a href="https://en.wikipedia.org/wiki/John_Horton_Conway"> 
 * John Horton Conway</a>
 * </p>
 * <p><b>Copyright MIT</b></p>
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
        System.out.println("Good day! Dobrý den! Buna ziua!");
        
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
