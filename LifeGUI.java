import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Hashtable;

public class LifeGUI extends JPanel {
    private final int    CELLSIZE   = 15;
    private final int    DELAY_MIN  = 25;      // delays in milliseconds
    private final int    DELAY_MAX  = 500;
    private final int    DELAY_INIT = 250;     // default to quarter second interval

    private final Color  ALIVE_COLOR = new Color(65, 44, 202, 255);
    private final Color  GRID_COLOR  = new Color(0, 0, 0, 255);
    private final Color  BACK_COLOR  = new Color(30, 30, 30, 255);

    private JButton   startButton;
    private JButton   stepButton;
    private JButton   clearButton;
    private JCheckBox gridButton;
    private JLabel    genCount;
    private int       count;

    private JSlider   genDelay;
    private Hashtable<Integer, JLabel> labelTable;

    private JComboBox presetBox;
    private JPanel    controlPanel;
    private JPanel    lifePanel;
    private Life      lifeInstance;
    private Timer     myTimer;
    private int       delay;
    private boolean   running;
    private boolean   drawGrid;

    public LifeGUI() {
        running = false;

        genDelay = new JSlider(JSlider.HORIZONTAL, DELAY_MIN, DELAY_MAX, DELAY_INIT);

        labelTable = new Hashtable<Integer, JLabel>();
        labelTable.put(new Integer(25), new JLabel("Fast"));
        labelTable.put(new Integer(DELAY_MAX), new JLabel("Slow"));
        genDelay.setLabelTable(labelTable);
        genDelay.setMajorTickSpacing(475);
        genDelay.setPaintLabels(true);

        genDelay.addChangeListener(new SliderListener());

        delay = DELAY_INIT;

        myTimer = new Timer(delay, new StepListener());

        lifeInstance = new Life(50, 40);

        controlPanel = new JPanel();

        startButton  = new JButton("Start");

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                running = !running;
                if (running) {
                    stepButton.setEnabled(false);
                    //genDelay.setEnabled(false);
                    startButton.setText("Stop");
                    myTimer.start();
                } else {
                    stepButton.setEnabled(true);
                    //genDelay.setEnabled(true);
                    startButton.setText("Start");
                    myTimer.stop();
                }
            }
        });

        clearButton = new JButton("Clear");

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (running) {
                    startButton.doClick();
                }

                lifeInstance = new Life(50, 40);
                count = 0;
                genCount.setText("Generations: " + count);
                lifePanel.repaint();
            }
        });

        stepButton = new JButton("Step");

        stepButton.addActionListener(new StepListener());

        count = 0;
        genCount = new JLabel("Generations: " + count);

        drawGrid = true;

        gridButton = new JCheckBox("Grid");

        gridButton.setSelected(true);

        gridButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                drawGrid = !drawGrid;
                lifePanel.repaint();
            }
        });

        controlPanel.add(startButton);
        controlPanel.add(stepButton);
        controlPanel.add(clearButton);
        controlPanel.add(gridButton);
        controlPanel.add(genDelay);
        controlPanel.add(genCount);

        lifePanel = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(GRID_COLOR);

                int[][] current = lifeInstance.getCurrentState();

                int xsize = 50 * CELLSIZE;
                int ysize = 40 * CELLSIZE;

                for (int i = 0; i < xsize; i += CELLSIZE) {
                    if (drawGrid) {
                        g.drawLine(i, 0, i, ysize);
                    }

                    for (int j = 0; j < ysize; j += CELLSIZE) {
                        if (drawGrid) {
                            g.drawLine(0, j, xsize, j);
                        }

                        if(current[i/CELLSIZE][j/CELLSIZE] > 1) {
                            g.fillRect(i, j, CELLSIZE, CELLSIZE);
                        } else if(current[i/CELLSIZE][j/CELLSIZE] == 1) {
                            g.setColor(ALIVE_COLOR);
                            g.fillRect(i, j, CELLSIZE, CELLSIZE);
                            g.setColor(GRID_COLOR);
                        }
                    }
                }


            }
        };

        lifePanel.setPreferredSize(new Dimension(50 * CELLSIZE, 40 * CELLSIZE));

        lifePanel.setBackground(BACK_COLOR);

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill   = GridBagConstraints.HORIZONTAL;
        c.gridx  = 0;
        c.gridy  = 0;

        add(controlPanel, c);

        c.fill  = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = .5;
        c.weighty = .5;

        add(lifePanel, c);

        lifePanel.addMouseListener(new myMouseListener());
    }

    public void setDelay(int newDelay) {
        delay = newDelay;
    }

    private class myMouseListener extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            int cellX = e.getX()/CELLSIZE;
            int cellY = e.getY()/CELLSIZE;

            lifeInstance.toggleAlive(cellX, cellY);
            lifePanel.repaint();
        }
    }

    private class StepListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            count++;
            genCount.setText("Generations: " + count);
            lifeInstance.calcNext();
            lifePanel.repaint();
        }
    }

    private class SliderListener implements ChangeListener {
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        if (!source.getValueIsAdjusting()) {
            myTimer.stop();
            myTimer = new Timer((int)source.getValue(), new StepListener());
            if (running) {
                myTimer.start();
            }
        }
    }
}
}
