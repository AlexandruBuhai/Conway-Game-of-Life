package gameoflifeproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics;

/**
 * <h2>Form Class</h2>
 * Creates the form and executes the required methods.
 * 
 * Class used to create the panel, populate it with objects and execute the program.
 * 
 * @author Alexandru Buhai
 * @version 1.1
 */


public class GameOfLifeForm extends javax.swing.JFrame {

    
    private int widthPanel = 100; //dimensions of the panel
    private int heightPanel = 50;
    private boolean[][] currentMove = new boolean[heightPanel][widthPanel]; //current state of the table
    private boolean[][] nextMove = new boolean[heightPanel][widthPanel]; //next state of the table
    private Image offScrImage;
    private Graphics offScrGraph;
    private int cnt; 
    private int genNumber; //number of the generation
    private boolean firstTimeBool; 
    private boolean playPressed; //has play button been pressed 
    private boolean startPressed; //has start button been pressed
    private Integer intervalCount; //timer count
    Timer timer = new Timer();
    
    /**
     * Scheduler for the timer. 
     * Takes the value from the spinner and changes the 
     * schedule for the timer. 
    */
    public void schedule() 
    {
        intervalCount = (Integer) intervalSpinner.getValue();
        timer.schedule(new MyTimer(),intervalCount);
    }
    
    
    
    /**
     * Constructor for the form.
     * <p>In the constructor we also implement the <b>actionPerformed</b> for the comboBox and draw the required shape.</p>
     */
    public GameOfLifeForm() {
        initComponents();
        firstTimeBool = true;
        playPressed = false;
        startPressed = false;
        cnt = 0;
        genNumber = -3;
        offScrImage = createImage(worldPanel.getWidth(), worldPanel.getHeight());
        offScrGraph = offScrImage.getGraphics();
        intervalCount = (Integer) intervalSpinner.getValue();

        drawComboBox.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event) 
            {                
                if(playPressed)
                {
                    drawFromComboBox(event);
                }
            }

            private void drawFromComboBox(ActionEvent event) {
                JComboBox drawComboBox = (JComboBox) event.getSource();
                Object selected = drawComboBox.getSelectedItem();
                String itemSelected = selected.toString();
                clean();
                
                
                switch (itemSelected)
                {
                    case "Block":
                        currentMove[heightPanel/2][widthPanel/2] = true;
                        currentMove[heightPanel/2 +1][widthPanel/2] = true;
                        currentMove[heightPanel/2][widthPanel/2+1] = true;
                        currentMove[heightPanel/2+1][widthPanel/2+1] = true;
                        break;
                    case "Blinker":
                        currentMove[heightPanel/2][widthPanel/2] = true;
                        currentMove[heightPanel/2 +1][widthPanel/2] = true;
                        currentMove[heightPanel/2-1][widthPanel/2] = true;
                        
                        break;
                    case "Toad":
                        currentMove[heightPanel/2][widthPanel/2] = true;
                        currentMove[heightPanel/2][widthPanel/2+1] = true;
                        currentMove[heightPanel/2][widthPanel/2+2] = true;
                        currentMove[heightPanel/2+1][widthPanel/2-1] = true;
                        currentMove[heightPanel/2+1][widthPanel/2] = true;
                        currentMove[heightPanel/2+1][widthPanel/2+1] = true;
                        break;
                    case "Beacon":
                        currentMove[heightPanel/2][widthPanel/2] = true;
                        currentMove[heightPanel/2][widthPanel/2+1] = true;
                        currentMove[heightPanel/2+2][widthPanel/2+3] = true;
                        currentMove[heightPanel/2+1][widthPanel/2] = true;
                        currentMove[heightPanel/2+3][widthPanel/2+2] = true;
                        currentMove[heightPanel/2+3][widthPanel/2+3] = true;
                        break;
                        
                    case "Glider":
                        // startPressed = false;
                        currentMove[heightPanel/2][widthPanel/2] = true;
                        currentMove[heightPanel/2+1][widthPanel/2+1] = true;
                        currentMove[heightPanel/2+2][widthPanel/2] = true;
                        currentMove[heightPanel/2+2][widthPanel/2+1] = true;
                        currentMove[heightPanel/2+2][widthPanel/2-1] = true;
                        //startPressed = true;
                        break;
                    case "Spaceship":
                        currentMove[heightPanel/2][widthPanel/2] = true;
                        currentMove[heightPanel/2-1][widthPanel/2] = true;
                        currentMove[heightPanel/2-2][widthPanel/2+1] = true;
                        currentMove[heightPanel/2-2][widthPanel/2-1] = true;
                        currentMove[heightPanel/2-3][widthPanel/2] = true;
                        currentMove[heightPanel/2-4][widthPanel/2] = true;
                        currentMove[heightPanel/2+1][widthPanel/2-1] = true;
                        //upper top
                        currentMove[heightPanel/2+1][widthPanel/2-5] = true;
                        currentMove[heightPanel/2+1][widthPanel/2-6] = true;
                        currentMove[heightPanel/2+1][widthPanel/2-7] = true;
                        currentMove[heightPanel/2+1][widthPanel/2-8] = true;
                        //left
                        currentMove[heightPanel/2+1][widthPanel/2-12] = true;
                        currentMove[heightPanel/2][widthPanel/2-13] = true;
                        currentMove[heightPanel/2-1][widthPanel/2-13] = true;
                        currentMove[heightPanel/2-2][widthPanel/2-12] = true;
                        currentMove[heightPanel/2-2][widthPanel/2-14] = true;
                        currentMove[heightPanel/2-3][widthPanel/2-13] = true;
                        currentMove[heightPanel/2-4][widthPanel/2-13] = true;
                        
                        //upper lower
                        currentMove[heightPanel/2+2][widthPanel/2-5] = true;
                        currentMove[heightPanel/2+2][widthPanel/2-6] = true;
                        currentMove[heightPanel/2+2][widthPanel/2-7] = true;
                        currentMove[heightPanel/2+2][widthPanel/2-8] = true;
                        
                        //lower left
                        currentMove[heightPanel/2+3][widthPanel/2-9] = true;
                        currentMove[heightPanel/2+3][widthPanel/2-10] = true;
                        currentMove[heightPanel/2+3][widthPanel/2-11] = true;
                        currentMove[heightPanel/2+3][widthPanel/2-12] = true;
                        
                        //lower right
                        currentMove[heightPanel/2+3][widthPanel/2-1] = true;
                        currentMove[heightPanel/2+3][widthPanel/2-2] = true;
                        currentMove[heightPanel/2+3][widthPanel/2-3] = true;
                        currentMove[heightPanel/2+3][widthPanel/2-4] = true;
                        
                        //down
                        currentMove[heightPanel/2+5][widthPanel/2-3] = true;
                        currentMove[heightPanel/2+5][widthPanel/2-10] = true;
                        currentMove[heightPanel/2+6][widthPanel/2-9] = true;
                        currentMove[heightPanel/2+6][widthPanel/2-8] = true;
                        currentMove[heightPanel/2+6][widthPanel/2-5] = true;
                        currentMove[heightPanel/2+6][widthPanel/2-4] = true;
                        break;
                    case "Pentadecathlon":
                        currentMove[heightPanel/2][widthPanel/2] = true;
                        currentMove[heightPanel/2][widthPanel/2-1] = true;
                        currentMove[heightPanel/2+1][widthPanel/2-2] = true;
                        currentMove[heightPanel/2-1][widthPanel/2-2]= true;
                        currentMove[heightPanel/2][widthPanel/2-3] = true;
                        currentMove[heightPanel/2][widthPanel/2-4] = true;
                        currentMove[heightPanel/2][widthPanel/2+1] = true;
                        currentMove[heightPanel/2][widthPanel/2+2] = true;
                        currentMove[heightPanel/2+1][widthPanel/2+3] = true;
                        currentMove[heightPanel/2-1][widthPanel/2+3] = true;
                        currentMove[heightPanel/2][widthPanel/2+4] = true;
                        currentMove[heightPanel/2][widthPanel/2+5] = true;
                        
                        break;
                    default:
                        break;
                }
                
                rePaint();
            }
        });
        schedule();
    }
    
    /**
     * This method applies the laws for the Game of Life.
     * 
     * Check the current state and count the neighbors.
     * @param i This is the position on the y axis.
     * @param j This is the position on the x axis.
     * @return boolean This returns the state of the current square
    */
     private boolean applyLaws(int i, int j){
        int neighbors = 0;
        
        if(j > 0){
            if(currentMove[i][j-1]) 
            {
                neighbors++;
            }
            if(i>0) if(currentMove[i-1][j-1])
            {
                neighbors++;
            }
            if(i<heightPanel-1 && currentMove[i+1][j-1]) 
            {
                neighbors++;
            }
        }
        if(j < widthPanel-1){
            if(currentMove[i][j+1])
            {
                neighbors++;
            }
            if(i>0) if(currentMove[i-1][j+1])
            {
                neighbors++;
            }
            if(i<heightPanel-1) if(currentMove[i+1][j+1]) 
            {
                neighbors++;
            }
        }
        if(i>0 && currentMove[i-1][j]) 
            neighbors++;
        if(i<heightPanel-1 && currentMove[i+1][j]) 
            neighbors++;
        if(neighbors == 3) 
            return true;
        if(currentMove[i][j] && neighbors == 2) 
            return true;
        return false;
    }
    
    /**
     * This method draws on the panel.
     * <p> It first draws the lines and then it uses the <b>currentMove</b> to see what squares are 
     * alive and which aren't and color them accordingly. </p>
    */
    private void rePaint() {
        
        System.out.print("Repaint " + cnt + "\n");
        cnt++;
        if(startPressed)
        {
            genNumber++;
            if(genNumber > 0)
            {
                genLabel.setText("Generation: " + genNumber);
            }
        }
        offScrGraph.setColor(worldPanel.getBackground());
        offScrGraph.fillRect(0,0, worldPanel.getWidth(), worldPanel.getHeight());
        for(int i = 0; i < heightPanel; i++)
        {
            for(int j = 0; j < widthPanel; j++)
            {
                try
                {
                if(currentMove[i][j])
                {
                    offScrGraph.setColor(Color.ORANGE);
                    int x = j * worldPanel.getWidth()/widthPanel;
                    int y = i * worldPanel.getHeight()/heightPanel;
                    offScrGraph.fillRect(x, y, worldPanel.getWidth()/widthPanel, worldPanel.getHeight()/heightPanel);
                }
                }
                catch(Exception ex)
                {
                    System.out.println(ex.toString() + " " + i + " " + j);
                }
            }
        }
        
        offScrGraph.setColor(Color.black);
        
        
        for(int i = 1; i < heightPanel; i++)
        {
            int y = i * worldPanel.getHeight()/heightPanel;
            offScrGraph.drawLine(0, y, worldPanel.getWidth(), y);
        }
        for(int j = 1; j < widthPanel; j++)
        {
            int x = j * worldPanel.getWidth()/widthPanel;
            offScrGraph.drawLine(x, 0, x, worldPanel.getHeight());

        }

         worldPanel.getGraphics().drawImage(offScrImage, 0, 0, worldPanel);

    }
    
   /**
    * Clean currentMove and nextMove tables.
    */
    private void clean() {
        for(int i = 0; i < heightPanel; i++)
        {
            for(int j = 0; j < widthPanel; j++)
            {
                currentMove[i][j] = false;
                nextMove[i][j] = false;
            }
        }
    }
    
    /**
     * Starts the timer and resets the height and width of the table.
     */
    private void startButtonClicked() throws NumberFormatException {
        if(!heightTextField.getText().equals("") && !widthTextField.getText().equals(""))
        {
            heightPanel = Integer.parseInt(heightTextField.getText());
            widthPanel = Integer.parseInt(widthTextField.getText());
            boolean[][] newCurrentMove = new boolean[heightPanel][widthPanel];
            boolean[][] newNextMove = new boolean[heightPanel][widthPanel];
            currentMove = newCurrentMove;
            nextMove = newNextMove;
        }
        
        if(firstTimeBool)
        {
            firstTimeBool = false;
            genNumber = -4;
        }
        if(playPressed)
        {
            startPressed = !startPressed;
        }
        playPressed = true;
        
        if(playPressed && !startPressed)
        {
            startButton.setText("Start");
            intervalCount = (Integer) intervalSpinner.getValue();
        }
        else
            if(playPressed && startPressed)
            {
                startButton.setText("Pause");
            }
            else startButton.setText("Start");
    }
    /**
     * Cleans the form and resets the parameters.
     */
    private void revertButtonClicked() {
        playPressed = false;
        startPressed = false;
        startButton.setText("Play");
        // intervalTextField.setText("");
        heightTextField.setText("");
        widthTextField.setText("");
        genNumber = 0;
        genLabel.setText("Generation: " + genNumber);
        intervalCount = (Integer) intervalSpinner.getValue();
        
        clean();
        rePaint();
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        worldPanel = new javax.swing.JPanel();
        rightPanel = new javax.swing.JPanel();
        exitButton = new javax.swing.JButton();
        heightTextField = new javax.swing.JTextField();
        widthTextField = new javax.swing.JTextField();
        heightLabel = new javax.swing.JLabel();
        widthLabel = new javax.swing.JLabel();
        intervalLabel = new javax.swing.JLabel();
        genLabel = new javax.swing.JLabel();
        drawComboBox = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        startButton = new javax.swing.JButton();
        revertButton = new javax.swing.JButton();
        intervalSpinner = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1100, 550));

        worldPanel.setBackground(new java.awt.Color(51, 45, 37));
        worldPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                worldPanelMouseDragged(evt);
            }
        });
        worldPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                worldPanelMouseClicked(evt);
            }
        });
        worldPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                worldPanelComponentResized(evt);
            }
        });

        javax.swing.GroupLayout worldPanelLayout = new javax.swing.GroupLayout(worldPanel);
        worldPanel.setLayout(worldPanelLayout);
        worldPanelLayout.setHorizontalGroup(
            worldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 957, Short.MAX_VALUE)
        );
        worldPanelLayout.setVerticalGroup(
            worldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        rightPanel.setMaximumSize(new java.awt.Dimension(141, 500));
        rightPanel.setMinimumSize(new java.awt.Dimension(140, 500));

        exitButton.setText("Exit");
        exitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitButtonMouseClicked(evt);
            }
        });

        heightTextField.setMargin(new java.awt.Insets(3, 3, 3, 3));
        heightTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                heightTextFieldActionPerformed(evt);
            }
        });

        widthTextField.setMargin(new java.awt.Insets(3, 3, 3, 3));
        widthTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                widthTextFieldActionPerformed(evt);
            }
        });

        heightLabel.setText("Height");

        widthLabel.setText("Width");

        intervalLabel.setText("Interval");

        genLabel.setText("Generation: 0");

        drawComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Block", "Blinker", "Toad", "Beacon", "Glider", "Spaceship", "Pentadecathlon" }));

        java.awt.GridBagLayout jPanel1Layout = new java.awt.GridBagLayout();
        jPanel1Layout.columnWidths = new int[] {0, 0, 0};
        jPanel1Layout.rowHeights = new int[] {0, 7, 0};
        jPanel1Layout.columnWeights = new double[] {2.0};
        jPanel1Layout.rowWeights = new double[] {3.0};
        jPanel1.setLayout(jPanel1Layout);

        startButton.setText("Play");
        startButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                startButtonMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(startButton, gridBagConstraints);
        startButton.getAccessibleContext().setAccessibleName("startButton");

        revertButton.setText("Revert");
        revertButton.setMaximumSize(new java.awt.Dimension(39, 28));
        revertButton.setMinimumSize(new java.awt.Dimension(39, 28));
        revertButton.setPreferredSize(new java.awt.Dimension(39, 28));
        revertButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                revertButtonMouseClicked(evt);
            }
        });
        revertButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                revertButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 12, 0);
        jPanel1.add(revertButton, gridBagConstraints);

        intervalSpinner.setModel(new javax.swing.SpinnerNumberModel(100, 100, 300, 20));
        intervalSpinner.setMaximumSize(new java.awt.Dimension(28, 41));
        intervalSpinner.setMinimumSize(new java.awt.Dimension(28, 41));
        intervalSpinner.setPreferredSize(new java.awt.Dimension(28, 41));

        javax.swing.GroupLayout rightPanelLayout = new javax.swing.GroupLayout(rightPanel);
        rightPanel.setLayout(rightPanelLayout);
        rightPanelLayout.setHorizontalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rightPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(widthTextField, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(exitButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(drawComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                    .addComponent(genLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(widthLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(heightTextField, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(heightLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(intervalLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(intervalSpinner, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        rightPanelLayout.setVerticalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rightPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(heightLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(heightTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(widthLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(widthTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(intervalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(intervalSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(drawComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(genLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(112, 112, 112)
                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        exitButton.getAccessibleContext().setAccessibleName("exitButton");
        heightTextField.getAccessibleContext().setAccessibleName("textFieldHeight");
        widthTextField.getAccessibleContext().setAccessibleName("textFieldWidth");
        heightLabel.getAccessibleContext().setAccessibleName("labelHeight");
        widthLabel.getAccessibleContext().setAccessibleName("labelWidth");
        intervalLabel.getAccessibleContext().setAccessibleName("labelInterval");
        genLabel.getAccessibleContext().setAccessibleName("genLabel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(worldPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rightPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(worldPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(rightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        worldPanel.getAccessibleContext().setAccessibleName("worldPanel1");
        worldPanel.getAccessibleContext().setAccessibleDescription("");

        getAccessibleContext().setAccessibleName("appJFrame");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void worldPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_worldPanelMouseClicked
            
    }//GEN-LAST:event_worldPanelMouseClicked

    /**
     * Calls rePaint method if the world gets resized.
     * @param evt a java.awt.event.ComponentEvent
    */
    private void worldPanelComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_worldPanelComponentResized
        offScrImage = createImage(worldPanel.getWidth(), worldPanel.getHeight());
        offScrGraph = offScrImage.getGraphics();
         rePaint();
    }//GEN-LAST:event_worldPanelComponentResized

    /**
     * 
     * @param evt a java.awt.event.MouseEvent
    */
    private void worldPanelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_worldPanelMouseDragged
        if(playPressed)
        {
            int j = widthPanel * evt.getX() / worldPanel.getWidth();
            int i = heightPanel * evt.getY() / worldPanel.getHeight();
            if(SwingUtilities.isLeftMouseButton(evt))
            {
                currentMove[i][j] = true;
            }
            else 
                currentMove[i][j] = false;
            rePaint();
        }
    }//GEN-LAST:event_worldPanelMouseDragged

    private void widthTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_widthTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_widthTextFieldActionPerformed

    private void heightTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_heightTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_heightTextFieldActionPerformed

    /**
     * Exits the application.
     * @param evt Not used
     */
    private void exitButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMouseClicked
        System.exit(0);
    }//GEN-LAST:event_exitButtonMouseClicked

    private void revertButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_revertButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_revertButtonActionPerformed

    
    private void revertButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_revertButtonMouseClicked
        revertButtonClicked();
    }//GEN-LAST:event_revertButtonMouseClicked
    
   
    private void startButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_startButtonMouseClicked

        startButtonClicked();
    }//GEN-LAST:event_startButtonMouseClicked
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameOfLifeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameOfLifeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameOfLifeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameOfLifeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameOfLifeForm().setVisible(true);
            }
        });
    }
    
    /**
     * MyTimer class used to implement run method.
     */
    class MyTimer extends TimerTask {
            public void run() {
                if(playPressed){
                    
                    if(startPressed)
                    {
                        for(int i = 0; i < heightPanel; i++){
                            for(int j = 0; j < widthPanel; j++){
                                nextMove[i][j] = applyLaws(i,j);
                            }
                        }
                        for(int i = 0; i < heightPanel; i++){
                            for(int j = 0; j < widthPanel; j++){
                                currentMove[i][j] = nextMove[i][j];
                            }
                        }
                    }
                    rePaint();
                    
                }    
                schedule();
            }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> drawComboBox;
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel genLabel;
    private javax.swing.JLabel heightLabel;
    private javax.swing.JTextField heightTextField;
    private javax.swing.JLabel intervalLabel;
    private javax.swing.JSpinner intervalSpinner;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton revertButton;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JButton startButton;
    private javax.swing.JLabel widthLabel;
    private javax.swing.JTextField widthTextField;
    private javax.swing.JPanel worldPanel;
    // End of variables declaration//GEN-END:variables
}
