/*
 * QCircuitView.java
 */

package qcircuit;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.nio.ByteBuffer;
import java.nio.charset.CharsetDecoder;
//import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;

/**
 * The application's main frame.
 */
public class QCircuitView extends FrameView {
    public ArrayList<QCircuit> circuits = new ArrayList<QCircuit>();
    public QViewport vp;
//    public HashMap<KeyStroke, Action> actionMap = new HashMap<KeyStroke, Action>();
    
    // Test run things.
    public ArrayList<QState> states;
    
        
    public void init() {
//        KeyStroke key1 = KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK);
//        actionMap.put(key1, new AbstractAction("action1") {
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("blah");
//            }
//        });
//        
//        KeyboardFocusManager kfm = KeyboardFocusManager.getCurrentKeyboardFocusManager();
//        kfm.addKeyEventDispatcher(new KeyEventDispatcher() {
//            public boolean dispatchKeyEvent(KeyEvent e) {
//                KeyStroke keyStroke = KeyStroke.getKeyStrokeForEvent(e);
//                if (actionMap.containsKey(keyStroke)) {
//                    final Action a = actionMap.get(keyStroke);
//                    final ActionEvent ae = new ActionEvent(e.getSource(), e.getID(), null);
//                    SwingUtilities.invokeLater(new Runnable() {
//                        public void run() {
//                            a.actionPerformed(ae);
//                        }
//                    });
//                    return true;
//                }
//                return false;
//            }
//        });
    }    
    
    public ResourceMap resourceMap;
    public PanelPropertiesBox propertiesBox;
    
    public QCircuitView(SingleFrameApplication app) {
        super(app);
        initComponents();
                
        this.vp = new QViewport(this);
        this.jSplitPane2.setRightComponent(vp);
        
        propertiesBox = new PanelPropertiesBox(this);
        propertiesBox.setBorder(new EtchedBorder());
        this.jSplitPane1.setBottomComponent(propertiesBox);
        
        init();
        
        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        
        this.resourceMap = resourceMap;
        
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                }
            }
        });
    }

    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = QCircuitApp.getApplication().getMainFrame();
            aboutBox = new QCircuitAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        QCircuitApp.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        mainPanel = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jSplitPane2 = new javax.swing.JSplitPane();
        panelToolbar = new javax.swing.JPanel();
        radioAddCircuit = new javax.swing.JRadioButton();
        radioAddCNot = new javax.swing.JRadioButton();
        boxTestRun = new javax.swing.JCheckBox();
        radioToggle = new javax.swing.JRadioButton();
        radioSelectCircuit = new javax.swing.JRadioButton();
        radioSelectGate = new javax.swing.JRadioButton();
        radioAddHadamard = new javax.swing.JRadioButton();
        radioProbRound = new javax.swing.JRadioButton();
        radioProb = new javax.swing.JRadioButton();
        radioFakeMeasure = new javax.swing.JRadioButton();
        radioAddMatrixGate = new javax.swing.JRadioButton();
        radioStateProbe = new javax.swing.JRadioButton();
        radioTrueMeasure = new javax.swing.JRadioButton();
        radioAddPauliZ = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        mitemNew = new javax.swing.JMenuItem();
        mitemOpen = new javax.swing.JMenuItem();
        mitemSaveAs = new javax.swing.JMenuItem();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        mitemHelp = new javax.swing.JMenuItem();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        groupTools = new javax.swing.ButtonGroup();
        groupRunMeasureMethod = new javax.swing.ButtonGroup();

        mainPanel.setName("mainPanel"); // NOI18N

        jSplitPane1.setDividerLocation(450);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setResizeWeight(1.0);
        jSplitPane1.setName("jSplitPane1"); // NOI18N

        jSplitPane2.setDividerLocation(180);
        jSplitPane2.setName("jSplitPane2"); // NOI18N

        panelToolbar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelToolbar.setName("panelToolbar"); // NOI18N

        groupTools.add(radioAddCircuit);
        radioAddCircuit.setSelected(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(qcircuit.QCircuitApp.class).getContext().getResourceMap(QCircuitView.class);
        radioAddCircuit.setText(resourceMap.getString("radioAddCircuit.text")); // NOI18N
        radioAddCircuit.setName("radioAddCircuit"); // NOI18N

        groupTools.add(radioAddCNot);
        radioAddCNot.setText(resourceMap.getString("radioAddCNot.text")); // NOI18N
        radioAddCNot.setName("radioAddCNot"); // NOI18N

        boxTestRun.setText(resourceMap.getString("boxTestRun.text")); // NOI18N
        boxTestRun.setName("boxTestRun"); // NOI18N
        boxTestRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxTestRunActionPerformed(evt);
            }
        });

        groupTools.add(radioToggle);
        radioToggle.setText(resourceMap.getString("radioToggle.text")); // NOI18N
        radioToggle.setName("radioToggle"); // NOI18N

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, boxTestRun, org.jdesktop.beansbinding.ELProperty.create("${selected}"), radioToggle, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        groupTools.add(radioSelectCircuit);
        radioSelectCircuit.setText(resourceMap.getString("radioSelectCircuit.text")); // NOI18N
        radioSelectCircuit.setName("radioSelectCircuit"); // NOI18N

        groupTools.add(radioSelectGate);
        radioSelectGate.setText(resourceMap.getString("radioSelectGate.text")); // NOI18N
        radioSelectGate.setName("radioSelectGate"); // NOI18N

        groupTools.add(radioAddHadamard);
        radioAddHadamard.setText(resourceMap.getString("radioAddHadamard.text")); // NOI18N
        radioAddHadamard.setName("radioAddHadamard"); // NOI18N

        groupRunMeasureMethod.add(radioProbRound);
        radioProbRound.setSelected(true);
        radioProbRound.setText(resourceMap.getString("radioProbRound.text")); // NOI18N
        radioProbRound.setName("radioProbRound"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, boxTestRun, org.jdesktop.beansbinding.ELProperty.create("${selected}"), radioProbRound, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        radioProbRound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioProbRoundActionPerformed(evt);
            }
        });

        groupRunMeasureMethod.add(radioProb);
        radioProb.setText(resourceMap.getString("radioProb.text")); // NOI18N
        radioProb.setName("radioProb"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, boxTestRun, org.jdesktop.beansbinding.ELProperty.create("${selected}"), radioProb, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        radioProb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioProbActionPerformed(evt);
            }
        });

        groupRunMeasureMethod.add(radioFakeMeasure);
        radioFakeMeasure.setText(resourceMap.getString("radioFakeMeasure.text")); // NOI18N
        radioFakeMeasure.setName("radioFakeMeasure"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, boxTestRun, org.jdesktop.beansbinding.ELProperty.create("${selected}"), radioFakeMeasure, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        radioFakeMeasure.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioFakeMeasureActionPerformed(evt);
            }
        });

        groupTools.add(radioAddMatrixGate);
        radioAddMatrixGate.setText(resourceMap.getString("radioAddMatrixGate.text")); // NOI18N
        radioAddMatrixGate.setName("radioAddMatrixGate"); // NOI18N
        radioAddMatrixGate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioAddMatrixGateActionPerformed(evt);
            }
        });

        groupTools.add(radioStateProbe);
        radioStateProbe.setText(resourceMap.getString("radioStateProbe.text")); // NOI18N
        radioStateProbe.setName("radioStateProbe"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, boxTestRun, org.jdesktop.beansbinding.ELProperty.create("${selected}"), radioStateProbe, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        groupRunMeasureMethod.add(radioTrueMeasure);
        radioTrueMeasure.setText(resourceMap.getString("radioTrueMeasure.text")); // NOI18N
        radioTrueMeasure.setName("radioTrueMeasure"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, boxTestRun, org.jdesktop.beansbinding.ELProperty.create("${selected}"), radioTrueMeasure, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        radioTrueMeasure.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioTrueMeasureActionPerformed(evt);
            }
        });

        groupTools.add(radioAddPauliZ);
        radioAddPauliZ.setText(resourceMap.getString("radioAddPauliZ.text")); // NOI18N
        radioAddPauliZ.setName("radioAddPauliZ"); // NOI18N

        javax.swing.GroupLayout panelToolbarLayout = new javax.swing.GroupLayout(panelToolbar);
        panelToolbar.setLayout(panelToolbarLayout);
        panelToolbarLayout.setHorizontalGroup(
            panelToolbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelToolbarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelToolbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radioAddCircuit)
                    .addComponent(radioAddCNot)
                    .addComponent(radioAddHadamard)
                    .addComponent(radioAddMatrixGate)
                    .addComponent(boxTestRun)
                    .addGroup(panelToolbarLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(panelToolbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(radioProb)
                            .addComponent(radioProbRound)
                            .addComponent(radioFakeMeasure)
                            .addComponent(radioTrueMeasure)))
                    .addComponent(radioSelectCircuit)
                    .addComponent(radioSelectGate)
                    .addComponent(radioToggle)
                    .addComponent(radioStateProbe)
                    .addComponent(radioAddPauliZ))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelToolbarLayout.setVerticalGroup(
            panelToolbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelToolbarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(radioAddCircuit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioAddCNot)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioAddHadamard)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioAddPauliZ)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioAddMatrixGate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioSelectCircuit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioSelectGate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioToggle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioStateProbe)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(boxTestRun)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioProbRound)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioProb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioFakeMeasure)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioTrueMeasure)
                .addContainerGap())
        );

        jSplitPane2.setLeftComponent(panelToolbar);

        jPanel3.setName("jPanel3"); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 679, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );

        jSplitPane2.setRightComponent(jPanel3);

        jSplitPane1.setTopComponent(jSplitPane2);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 865, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE)
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        mitemNew.setText(resourceMap.getString("mitemNew.text")); // NOI18N
        mitemNew.setName("mitemNew"); // NOI18N
        mitemNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitemNewActionPerformed(evt);
            }
        });
        fileMenu.add(mitemNew);

        mitemOpen.setText(resourceMap.getString("mitemOpen.text")); // NOI18N
        mitemOpen.setName("mitemOpen"); // NOI18N
        mitemOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitemOpenActionPerformed(evt);
            }
        });
        fileMenu.add(mitemOpen);

        mitemSaveAs.setText(resourceMap.getString("mitemSaveAs.text")); // NOI18N
        mitemSaveAs.setName("mitemSaveAs"); // NOI18N
        mitemSaveAs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitemSaveAsActionPerformed(evt);
            }
        });
        fileMenu.add(mitemSaveAs);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(qcircuit.QCircuitApp.class).getContext().getActionMap(QCircuitView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        mitemHelp.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        mitemHelp.setText(resourceMap.getString("mitemHelp.text")); // NOI18N
        mitemHelp.setName("mitemHelp"); // NOI18N
        mitemHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitemHelpActionPerformed(evt);
            }
        });
        helpMenu.add(mitemHelp);

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setText(resourceMap.getString("aboutMenuItem.text")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setComponent(mainPanel);
        setMenuBar(menuBar);

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

    public boolean isTestRun = false;
    
private void boxTestRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxTestRunActionPerformed
    isTestRun = boxTestRun.isSelected();
    if (isTestRun) {
        initTestRun();
    }
    for (int i = 0; i < circuits.size(); i++) {
        circuits.get(i).switchRunMode(isTestRun);
    }
    vp.repaintVP();
}//GEN-LAST:event_boxTestRunActionPerformed

    public void deleteSelectedGate() {
        if (vp.selectedCircuit != null && vp.selectedGate != null) {
            int index = vp.selectedCircuit.gates.indexOf(vp.selectedGate);
            if (index == -1) {
                vp.selectedCircuit = null;
                propertiesBox.onSelectedCircuitChanged();
                vp.selectedGate = null;
                propertiesBox.onSelectedGateChanged();
                vp.repaintVP();
                return;
            }
            vp.selectedCircuit.gates.remove(index);
            vp.selectedGate = null;
            propertiesBox.onSelectedGateChanged();
            vp.repaintVP();
        }
    }

    public void deleteSelectedCircuit() {
        if (vp.selectedCircuit != null) {
            int index = circuits.indexOf(vp.selectedCircuit);
            if (index == -1) {
                vp.selectedCircuit.color = QCircuit.COLOR_UNSELECTED;
                if (vp.selectedGate != null) {
                    vp.selectedGate.setSelected(false);
                }
                vp.selectedCircuit = null;
                propertiesBox.onSelectedCircuitChanged();
                vp.selectedGate = null;
                propertiesBox.onSelectedGateChanged();
                vp.repaintVP();
                return;
            }
            circuits.remove(index);
            if (states != null && states.size() > index) {
                states.remove(index);
            }
            vp.selectedCircuit = null;
            propertiesBox.onSelectedCircuitChanged();
            vp.selectedGate = null;
            propertiesBox.onSelectedGateChanged();
            vp.repaintVP();
        }
    }

public void deleteSelected() {
    if (groupTools.isSelected(radioSelectCircuit.getModel())) {
        deleteSelectedCircuit();
    } else if (groupTools.isSelected(radioSelectGate.getModel())) {
        deleteSelectedGate();
    } else {
        
    }
}

    public ComplexMatrix currentGateMatrix = null;
    public int matrixBits = -1;
    public IQGate currentLoadedGate = null;
    public QCircuit currentLoadedCircuit = null;

private void radioAddMatrixGateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioAddMatrixGateActionPerformed
    currentLoadedGate = null;
    String input = JOptionPane.showInputDialog("Please input the matrix in the form of either\none list of 2^(2n) real numbers, separated by semicolons, or\ntwo such lists (real and complex coefficients), separated from each other by a colon.", null);
    if (input != null) {
        String[] strings = input.split(":");
        if (strings.length == 1) {
            MatrixGate matrixGate = new MatrixGate(strings[0]);
            currentGateMatrix = matrixGate.matrix;
            matrixBits = matrixGate.bits;
        } else if (strings.length == 2) {
            MatrixGate matrixGate = new MatrixGate(strings[0], strings[1]);
            currentGateMatrix = matrixGate.matrix;
            matrixBits = matrixGate.bits;
        } else {
            JOptionPane.showMessageDialog(null, "Your input registers more than one : (or less than 0?).  This is invalid.", input, busyIconIndex);
        }
    }
}//GEN-LAST:event_radioAddMatrixGateActionPerformed

private void radioProbRoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioProbRoundActionPerformed
    vp.repaintVP();
}//GEN-LAST:event_radioProbRoundActionPerformed

private void radioProbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioProbActionPerformed
    vp.repaintVP();
}//GEN-LAST:event_radioProbActionPerformed

private void radioFakeMeasureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioFakeMeasureActionPerformed
    vp.repaintVP();
}//GEN-LAST:event_radioFakeMeasureActionPerformed

    public HelpFrame helpFrame = null;

private void mitemHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitemHelpActionPerformed
    if (helpFrame == null) {
        helpFrame = new HelpFrame(resourceMap.getString("appHelpLabel.text"));
    }
    helpFrame.setVisible(true);
}//GEN-LAST:event_mitemHelpActionPerformed

private void mitemNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitemNewActionPerformed
    circuits = new ArrayList<QCircuit>();
    states = null;
//    isTestRun = false;
//    boxTestRun.setSelected(false);
    initTestRun();
    vp.selectedCircuit = null;
    propertiesBox.onSelectedCircuitChanged();
    vp.selectedGate = null;
    propertiesBox.onSelectedGateChanged();
    vp.repaintVP();
}//GEN-LAST:event_mitemNewActionPerformed

    public JFileChooser chooser = new JFileChooser();

private void mitemSaveAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitemSaveAsActionPerformed
    if (chooser.showSaveDialog(this.getFrame()) == JFileChooser.APPROVE_OPTION) {
        saveAll(chooser.getSelectedFile());
    }
}//GEN-LAST:event_mitemSaveAsActionPerformed

private void mitemOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitemOpenActionPerformed
    if (chooser.showOpenDialog(this.getFrame()) == JFileChooser.APPROVE_OPTION) {
        loadAll(chooser.getSelectedFile());
        if (isTestRun) {
            initTestRun();
        }
        vp.repaintVP();
    }
}//GEN-LAST:event_mitemOpenActionPerformed

private void radioTrueMeasureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioTrueMeasureActionPerformed
    vp.repaintVP();
}//GEN-LAST:event_radioTrueMeasureActionPerformed

private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
    this.showAboutBox();
}//GEN-LAST:event_aboutMenuItemActionPerformed

    public int VERSION = 4;

    public void saveAll(File file) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            DataOutputStream dos = new DataOutputStream(fos);
            dos.writeInt(VERSION);
            dos.writeUTF("QCircuitSet");
            dos.writeInt(circuits.size());
            for (int i = 0; i < circuits.size(); i++) {
                QCircuit c = circuits.get(i);
                dos.writeInt(c.bits);
                dos.writeDouble(c.origin.x);
                dos.writeDouble(c.origin.y);
                dos.writeDouble(c.excessWire);
                dos.writeDouble(c.gateSize);
                dos.writeDouble(c.gateSpace);
                dos.writeDouble(c.scale);
                dos.writeDouble(c.wireSpace);
                dos.writeInt(c.gates.size());
                for (int j = 0; j < c.gates.size(); j++) {
                    IQGate ig = c.gates.get(j);
                    if (ig instanceof CNot) {
                        dos.writeUTF("CNot");
                        dos.writeInt(((CNot)ig).bits.length);
                        for (int k = 0; k < ((CNot)ig).bits.length; k++) {
                            dos.writeInt(((CNot)ig).bits[k]);
                        }
                        dos.writeInt(((CNot)ig).bitcount);
                    } else if (ig instanceof Hadamard) {
                        dos.writeUTF("Hadamard");
                        dos.writeInt(((Hadamard)ig).bit);                        
                        dos.writeInt(((Hadamard)ig).bitcount);
                    } else if (ig instanceof PauliZ) {
                        dos.writeUTF("PauliZ");
                        dos.writeInt(((PauliZ)ig).bit);                        
                        dos.writeInt(((PauliZ)ig).bitcount);
                    } else if (ig instanceof MatrixGate) {
                        dos.writeUTF("Matrix");
                        dos.writeInt(((MatrixGate)ig).bits);
                        byte[] bucket = ((MatrixGate) ig).matrix.toSquareExportString().getBytes("US-ASCII");
                        dos.writeInt(bucket.length);
                        dos.write(bucket);
                    } else {
                        dos.writeUTF("Unknown");
                    }
                }
            }
            dos.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(QCircuitView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(QCircuitView.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fos.flush();
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(QCircuitView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void runBenchmarks(int i) {
        long startTime = -1;
        long thisTime = -1;
        long lastTime = -1;
        QCircuit c = new QCircuit(i);
        c.gates.add(new Hadamard(0, i));
        c.gates.add(new Hadamard(1, i));
        propertiesBox.showCircuit(c);
        propertiesBox.areaCircuitText.setLineWrap(true);
        propertiesBox.areaCircuitText.setWrapStyleWord(false);
        startTime = System.currentTimeMillis();
        lastTime = System.currentTimeMillis();
        thisTime = System.currentTimeMillis();
        System.out.println("Started CTM: \t" + (thisTime - lastTime) + " \t" + (thisTime - startTime));
        lastTime = thisTime;
        ComplexMatrix cm = c.toMatrix();
        thisTime = System.currentTimeMillis();
        System.out.println("Mtx done: \t" + (thisTime - lastTime) + " \t" + (thisTime - startTime));
        lastTime = thisTime;
        String s = cm.toSquareExportString();
        thisTime = System.currentTimeMillis();
        System.out.println("String done: \t" + (thisTime - lastTime) + " \t" + (thisTime - startTime));
        lastTime = thisTime;

        FileWriter fw;
        try {
            fw = new FileWriter(new File("/tmp/mtx.txt"));
            BufferedWriter bw = new BufferedWriter(fw);
            bw.append(s);
            bw.flush();
            fw.flush();
            fw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PanelPropertiesBox.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PanelPropertiesBox.class.getName()).log(Level.SEVERE, null, ex);
        }
        thisTime = System.currentTimeMillis();
        System.out.println("File saved: \t" + (thisTime - lastTime) + " \t" + (thisTime - startTime));
        lastTime = thisTime;

        propertiesBox.areaCircuitText.setText(s);
        thisTime = System.currentTimeMillis();
        System.out.println("Text set: \t" + (thisTime - lastTime) + " \t" + (thisTime - startTime));
        lastTime = thisTime;
        System.out.println();
    }

    public void saveCircuit(QCircuit c, File file) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            DataOutputStream dos = new DataOutputStream(fos);
            dos.writeInt(VERSION);
            dos.writeUTF("QCircuitUnit");
            dos.writeInt(c.bits);
            dos.writeDouble(c.origin.x);
            dos.writeDouble(c.origin.y);
            dos.writeDouble(c.excessWire);
            dos.writeDouble(c.gateSize);
            dos.writeDouble(c.gateSpace);
            dos.writeDouble(c.scale);
            dos.writeDouble(c.wireSpace);
            dos.writeInt(c.gates.size());
            for (int j = 0; j < c.gates.size(); j++) {
                IQGate ig = c.gates.get(j);
                if (ig instanceof CNot) {
                    dos.writeUTF("CNot");
                    dos.writeInt(((CNot) ig).bits.length);
                    for (int k = 0; k < ((CNot) ig).bits.length; k++) {
                        dos.writeInt(((CNot) ig).bits[k]);
                    }
                    dos.writeInt(((CNot) ig).bitcount);
                } else if (ig instanceof Hadamard) {
                    dos.writeUTF("Hadamard");
                    dos.writeInt(((Hadamard) ig).bit);
                    dos.writeInt(((Hadamard) ig).bitcount);
                } else if (ig instanceof PauliZ) {
                    dos.writeUTF("PauliZ");
                    dos.writeInt(((PauliZ) ig).bit);
                    dos.writeInt(((PauliZ) ig).bitcount);
                } else if (ig instanceof MatrixGate) {
                    dos.writeUTF("Matrix");
                    dos.writeInt(((MatrixGate) ig).bits);
                    byte[] bucket = ((MatrixGate) ig).matrix.toSquareExportString().getBytes("US-ASCII");
                    dos.writeInt(bucket.length);
                    dos.write(bucket);
                } else {
                    dos.writeUTF("Unknown");
                }
            }
            dos.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(QCircuitView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(QCircuitView.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fos.flush();
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(QCircuitView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void saveGate(IQGate ig, File file) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            DataOutputStream dos = new DataOutputStream(fos);
            dos.writeInt(VERSION);
            dos.writeUTF("QCircuitGate");
            if (ig instanceof CNot) {
                dos.writeUTF("CNot");
                dos.writeInt(((CNot) ig).bits.length);
                for (int k = 0; k < ((CNot) ig).bits.length; k++) {
                    dos.writeInt(((CNot) ig).bits[k]);
                }
                dos.writeInt(((CNot) ig).bitcount);
            } else if (ig instanceof Hadamard) {
                dos.writeUTF("Hadamard");
                dos.writeInt(((Hadamard) ig).bit);
                dos.writeInt(((Hadamard) ig).bitcount);
            } else if (ig instanceof PauliZ) {
                dos.writeUTF("PauliZ");
                dos.writeInt(((PauliZ) ig).bit);
                dos.writeInt(((PauliZ) ig).bitcount);
            } else if (ig instanceof MatrixGate) {
                dos.writeUTF("Matrix");
                dos.writeInt(((MatrixGate) ig).bits);
                byte[] bucket = ((MatrixGate) ig).matrix.toSquareExportString().getBytes("US-ASCII");
                dos.writeInt(bucket.length);
                dos.write(bucket);
            } else {
                dos.writeUTF("Unknown");
            }
            dos.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(QCircuitView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(QCircuitView.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fos.flush();
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(QCircuitView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void loadAll(File file) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            DataInputStream dis = new DataInputStream(fis);
            int version = dis.readInt();
            switch (version) {
                case 1:
                {
                    int circuitsSize = dis.readInt();
                    circuits = new ArrayList<QCircuit>();
                    for (int i = 0; i < circuitsSize; i++) {
                        int bits = dis.readInt();
                        double cox = dis.readDouble();
                        double coy = dis.readDouble();
                        QCircuit c = new QCircuit(bits, cox, coy);
                        c.excessWire = dis.readDouble();
                        c.gateSize = dis.readDouble();
                        c.gateSpace = dis.readDouble();
                        c.scale = dis.readDouble();
                        c.wireSpace = dis.readDouble();
                        int gateCount = dis.readInt();
                        c.gates = new ArrayList<IQGate>();
                        for (int j = 0; j < gateCount; j++) {
                            String type = dis.readUTF();
                            if ("CNot".equals(type)) {
                                int bitcount = dis.readInt();
                                int[] bitsarray = new int[bitcount];
                                for (int k = 0; k < bitcount; k++) {
                                    bitsarray[k] = dis.readInt();
                                }
                                CNot cn = new CNot(bitsarray, 0);
                                c.gates.add(cn);
                            } else if ("Hadamard".equals(type)) {
                                int bit = dis.readInt();
                                Hadamard h = new Hadamard(bit, 0);
                                c.gates.add(h);
                            } else if ("Matrix".equals(type)) {
                                int bitcount = dis.readInt();
                                String csv = dis.readUTF();
                                String[] mtxs = csv.split(":");
                                MatrixGate mg = new MatrixGate(mtxs[0], mtxs[1]);
                                c.gates.add(mg);
                            } else if ("Unknown".equals(type)) {
                                System.err.println("Read attempt to store a gate that was unknown.");
                            } else {
                                System.err.println("Unknown gate type: " + type);
                            }
                        }
                        circuits.add(c);
                    }
                    break;
                }
                case 2:
                case 3:
                case 4:
                {
                    String fileType = dis.readUTF();
                    if ("QCircuitSet".equals(fileType)) {
                        int circuitsSize = dis.readInt();
                        circuits = new ArrayList<QCircuit>();
                        for (int i = 0; i < circuitsSize; i++) {
                            int bits = dis.readInt();
                            double cox = dis.readDouble();
                            double coy = dis.readDouble();
                            QCircuit c = new QCircuit(bits, cox, coy);
                            c.excessWire = dis.readDouble();
                            c.gateSize = dis.readDouble();
                            c.gateSpace = dis.readDouble();
                            c.scale = dis.readDouble();
                            c.wireSpace = dis.readDouble();
                            int gateCount = dis.readInt();
                            c.gates = new ArrayList<IQGate>();
                            for (int j = 0; j < gateCount; j++) {
                                String type = dis.readUTF();
                                if ("CNot".equals(type)) {
                                    int bitcount = dis.readInt();
                                    int[] bitsarray = new int[bitcount];
                                    for (int k = 0; k < bitcount; k++) {
                                        bitsarray[k] = dis.readInt();
                                    }
                                    int totalbits = dis.readInt();
                                    CNot cn = new CNot(bitsarray, totalbits);
                                    c.gates.add(cn);
                                } else if ("Hadamard".equals(type)) {
                                    int bit = dis.readInt();
                                    int totalbits = dis.readInt();
                                    Hadamard h = new Hadamard(bit, totalbits);
                                    c.gates.add(h);
                                } else if ("PauliZ".equals(type)) {
                                    int bit = dis.readInt();
                                    int totalbits = dis.readInt();
                                    PauliZ z = new PauliZ(bit, totalbits);
                                    c.gates.add(z);
                                } else if ("Matrix".equals(type)) {
                                    int bitcount = dis.readInt();
                                    String csv = null;
                                    if (version == 2) {
                                        csv = dis.readUTF();
                                    } else if (version == 3) {
                                        int bucketLength = dis.readInt();
                                        byte[] bucket = new byte[bucketLength];
                                        dis.read(bucket);
//                                        csv = StandardCharsets.US_ASCII.decode(ByteBuffer.wrap(bucket)).toString();
                                    }
                                    String[] mtxs = csv.split(":");
                                    MatrixGate mg = new MatrixGate(mtxs[0], mtxs[1]);
                                    c.gates.add(mg);
                                } else if ("Unknown".equals(type)) {
                                    System.err.println("Read attempt to store a gate that was unknown.");
                                } else {
                                    System.err.println("Unknown gate type: " + type);
                                }
                            }
                            circuits.add(c);
                        }
                    } else if ("QCircuitUnit".equals(fileType)) {
                        int bits = dis.readInt();
                        double cox = dis.readDouble();
                        double coy = dis.readDouble();
                        QCircuit c = new QCircuit(bits, cox, coy);
                        c.excessWire = dis.readDouble();
                        c.gateSize = dis.readDouble();
                        c.gateSpace = dis.readDouble();
                        c.scale = dis.readDouble();
                        c.wireSpace = dis.readDouble();
                        int gateCount = dis.readInt();
                        c.gates = new ArrayList<IQGate>();
                        for (int j = 0; j < gateCount; j++) {
                            String type = dis.readUTF();
                            if ("CNot".equals(type)) {
                                int bitcount = dis.readInt();
                                int[] bitsarray = new int[bitcount];
                                for (int k = 0; k < bitcount; k++) {
                                    bitsarray[k] = dis.readInt();
                                }
                                int totalbits = dis.readInt();
                                CNot cn = new CNot(bitsarray, totalbits);
                                c.gates.add(cn);
                            } else if ("Hadamard".equals(type)) {
                                int bit = dis.readInt();
                                int totalbits = dis.readInt();
                                Hadamard h = new Hadamard(bit, totalbits);
                                c.gates.add(h);
                            } else if ("PauliZ".equals(type)) {
                                int bit = dis.readInt();
                                int totalbits = dis.readInt();
                                PauliZ z = new PauliZ(bit, totalbits);
                                c.gates.add(z);
                            } else if ("Matrix".equals(type)) {
                                int bitcount = dis.readInt();
                                String csv = null;
                                if (version == 2) {
                                    csv = dis.readUTF();
                                } else if (version == 3) {
                                    int bucketLength = dis.readInt();
                                    byte[] bucket = new byte[bucketLength];
                                    dis.read(bucket);
//                                    csv = StandardCharsets.US_ASCII.decode(ByteBuffer.wrap(bucket)).toString();
                                }
                                String[] mtxs = csv.split(":");
                                MatrixGate mg = new MatrixGate(mtxs[0], mtxs[1]);
                                c.gates.add(mg);
                            } else if ("Unknown".equals(type)) {
                                System.err.println("Read attempt to store a gate that was unknown.");
                            } else {
                                System.err.println("Unknown gate type: " + type);
                            }
                        }
                        currentLoadedCircuit = c;
                        radioAddCircuit.setSelected(true);
                    } else if ("QCircuitGate".equals(fileType)) {
                        String type = dis.readUTF();
                        if ("CNot".equals(type)) {
                            int bitcount = dis.readInt();
                            int[] bitsarray = new int[bitcount];
                            for (int k = 0; k < bitcount; k++) {
                                bitsarray[k] = dis.readInt();
                            }
                            int totalbits = dis.readInt();
                            CNot cn = new CNot(bitsarray, totalbits);
                            currentLoadedGate = cn;
                            radioAddMatrixGate.setSelected(true);
                        } else if ("Hadamard".equals(type)) {
                            int bit = dis.readInt();
                            int totalbits = dis.readInt();
                            Hadamard h = new Hadamard(bit, totalbits);
                            currentLoadedGate = h;
                            radioAddMatrixGate.setSelected(true);
                        } else if ("PauliZ".equals(type)) {
                            int bit = dis.readInt();
                            int totalbits = dis.readInt();
                            PauliZ z = new PauliZ(bit, totalbits);
                            currentLoadedGate = z;
                            radioAddMatrixGate.setSelected(true);
                        } else if ("Matrix".equals(type)) {
                            int bitcount = dis.readInt();
                            String csv = null;
                            if (version == 2) {
                                csv = dis.readUTF();
                            } else if (version == 3) {
                                int bucketLength = dis.readInt();
                                byte[] bucket = new byte[bucketLength];
                                dis.read(bucket);
//                                csv = StandardCharsets.US_ASCII.decode(ByteBuffer.wrap(bucket)).toString();
                            }
                            String[] mtxs = csv.split(":");
                            MatrixGate mg = new MatrixGate(mtxs[0], mtxs[1]);
                            currentLoadedGate = mg;
                            radioAddMatrixGate.setSelected(true);
                        } else if ("Unknown".equals(type)) {
                            System.err.println("Read attempt to store a gate that was unknown.");
                            currentLoadedGate = null;
                        } else {
                            System.err.println("Unknown gate type: " + type);
                            currentLoadedGate = null;
                        }
                    } else {
                        throw new IllegalArgumentException("File is not a QCircuitSet/Unit/Gate file!  Instead: " + fileType);
                    }
                    break;
                }
                default:
                    break;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(QCircuitView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(QCircuitView.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(QCircuitView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

public void initTestRun() {
    this.states = new ArrayList<QState>();
    for (int i = 0; i < circuits.size(); i++) {
        QState s = new QState(circuits.get(i).bits);
        s.initZeros();
        this.states.add(s);
    }
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox boxTestRun;
    public javax.swing.ButtonGroup groupRunMeasureMethod;
    public javax.swing.ButtonGroup groupTools;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem mitemHelp;
    private javax.swing.JMenuItem mitemNew;
    private javax.swing.JMenuItem mitemOpen;
    private javax.swing.JMenuItem mitemSaveAs;
    private javax.swing.JPanel panelToolbar;
    public javax.swing.JRadioButton radioAddCNot;
    public javax.swing.JRadioButton radioAddCircuit;
    public javax.swing.JRadioButton radioAddHadamard;
    public javax.swing.JRadioButton radioAddMatrixGate;
    public javax.swing.JRadioButton radioAddPauliZ;
    public javax.swing.JRadioButton radioFakeMeasure;
    public javax.swing.JRadioButton radioProb;
    public javax.swing.JRadioButton radioProbRound;
    public javax.swing.JRadioButton radioSelectCircuit;
    public javax.swing.JRadioButton radioSelectGate;
    public javax.swing.JRadioButton radioStateProbe;
    public javax.swing.JRadioButton radioToggle;
    public javax.swing.JRadioButton radioTrueMeasure;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;
}
