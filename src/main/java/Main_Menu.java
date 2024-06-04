import com.melloware.jintellitype.JIntellitype;
import com.melloware.jintellitype.HotkeyListener;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.util.stream.Collectors;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import javax.crypto.KeyGenerator;
import org.apache.poi.xwpf.usermodel.*; // This imports all classes from the package
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;
import java.text.DecimalFormat;

import java.awt.Color; 
import javax.swing.ImageIcon; // Import ImageIcon
import java.awt.Image; // Import Image for scaling
import java.net.URL; // Import URL for ImageIcon
//import org.openxml4j.opc.OPCPackage; // Import this for Package
//import org.openxml4j.opc.PackagePart;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Riki A
 */
public class Main_Menu extends javax.swing.JFrame {

    /**
     * Creates new form Main_Menu
     */
    
    private KeyListener keyListener;
    
    String path;
    
    Timer timer;
    double totalElapsedTime;
    long totalMemoryUsed;
 
    int isList = 1;
    int isArray = 0;
    int isHash = 0;
    
    String mode = "default";
   
    
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";
    
    
    // Default Secret Key (Base64 encoded)
    private static final String DEFAULT_KEY = "+x7/8tAshjWNJ6kplFmkuw==";
    private SecretKey secretKey ;
    
 

    
    boolean isEncrypt = false;
  
   
    
    
   
    public Main_Menu() {
        initComponents();
        
        setIconImage(new ImageIcon(getClass().getResource("/images/encrypto.png")).getImage()); // Assuming the icon is in the 'images' folder
        devPanel.setVisible(false);

        pack(); // Repack the JFrame to fit new contents
        revalidate();
        repaint();
        
        // Initialize with the default key
        setSecretKey(DEFAULT_KEY);
        
        
        radioList.setSelected(true);
        
        // Disable content area filling for btnEncrypt
        btnEncrypt.setContentAreaFilled(false);
        btnEncrypt.setOpaque(false); 

        // Disable content area filling for btnDecrypt
        btnDecrypt.setContentAreaFilled(false);
        btnDecrypt.setOpaque(false); 
        
        btnNewKey.setContentAreaFilled(false);
        btnNewKey.setOpaque(false); 
        
      

        
        // Set a border without a shadow
        btnEncrypt.setBorder(new javax.swing.border.LineBorder(Color.WHITE, 1));
        btnDecrypt.setBorder(new javax.swing.border.LineBorder(Color.WHITE, 1)); 
         // Convert the hex color to a Color object
        Color customColor = Color.decode("#B34E8C");
        btnNewKey.setBorder(new javax.swing.border.LineBorder(customColor, 1));
        
        
       
    
       
        
        
        // Register hotkey for Ctrl + E
        JIntellitype.getInstance().registerHotKey(1, JIntellitype.MOD_CONTROL, (int) 'E');

        // Register hotkey for Ctrl + D
        JIntellitype.getInstance().registerHotKey(2, JIntellitype.MOD_CONTROL, (int) 'D');
        
        
        // Register hotkey for Ctrl + D
        JIntellitype.getInstance().registerHotKey(3, JIntellitype.MOD_CONTROL, (int) 'U');
        
        // Register hotkey for Ctrl + Shift + U
        JIntellitype.getInstance().registerHotKey(4, JIntellitype.MOD_CONTROL | JIntellitype.MOD_SHIFT, (int) 'U');
        
        
        
        

        // Add hotkey listener
        JIntellitype.getInstance().addHotKeyListener(new HotkeyListener() {
            @Override
            public void onHotKey(int key) {
                // Check which hotkey is triggered
                switch (key) {
                    case 1: // Ctrl + E
//                        memoLabel.setText("Detected Ctrl + E");
                        btnEncryptActionPerformed(null);
                        break;
                    case 2: // Ctrl + D
//                        memoLabel.setText("Detected Ctrl + D");
                        btnDecryptActionPerformed(null);
                        // Do something for Ctrl + D
                        break;
                        
                    case 3: // Ctrl + u
//                        memoLabel.setText("Detected Ctrl + U");
                        devPanel.setVisible(true);
                        pack(); // Repack the JFrame to fit new contents
                        revalidate();
                        repaint();
                        // Do something for Ctrl + D
                        break;
                    case 4: // Ctrl + u
//                      memoLabel.setText("Detected Ctrl + shift + U");
                        devPanel.setVisible(false);
                        pack(); // Repack the JFrame to fit new contents
                        revalidate();
                        repaint();
                        // Do something for Ctrl + D
                        break;
                }
            }
        });
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
   
    private SecretKey getSecretKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
            keyGenerator.init(128);
            secretKey = keyGenerator.generateKey();
//          return keyGenerator.generateKey();

            // Get the byte representation of the key
            byte[] keyBytes = secretKey.getEncoded();

            // Convert the byte array to a Base64-encoded string
            String encodedKey = Base64.getEncoder().encodeToString(keyBytes);
            System.out.println(encodedKey);
            return secretKey;
            
            

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Main_Menu.class.getName()).log(Level.SEVERE, null, ex);
            // Handle the exception, like showing an error message
            JOptionPane.showMessageDialog(this, "Error generating secret key: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null; // Return null or throw an exception, depending on your requirements
        }
    }
    
    private void setSecretKey(String encodedKey) {
        try {
            // Decode the Base64-encoded key
            byte[] decodedKeyBytes = Base64.getDecoder().decode(encodedKey); 

            // Create a SecretKeySpec object from the decoded bytes
            secretKey = new SecretKeySpec(decodedKeyBytes, "AES"); 
            System.out.println("Key set");
        } catch (IllegalArgumentException e) {
            // Handle decoding errors
            JOptionPane.showMessageDialog(this, "Invalid key format.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private String encrypt(String plainText) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);


    }

    private String decrypt(String encryptedText) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        return new String(decryptedBytes);



    }
    
    
    
     private JDialog createAutoClosingDialog(String message, int delay) {
        final JOptionPane optionPane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE);
        final JDialog dialog = optionPane.createDialog(this, "Message");

        Timer timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        timer.setRepeats(false);
        timer.start();

        return dialog;
    }
     
    private void processWordFile(File file) throws IOException {
    if (file.exists() && file.length() > 0) {
        try (FileInputStream fis = new FileInputStream(file);
             XWPFDocument doc = new XWPFDocument(fis)) {
            // Encrypt the text
            
//            SecretKey secretKey = getSecretKey();
               
              

            // Modify text in each paragraph
            for (XWPFParagraph paragraph : doc.getParagraphs()) {
                // Get the text from the paragraph
                String originalText = paragraph.getText();
                
               

                String cryptoText;
                try {
                    // Encrypt the text
                    if(isEncrypt){
                        
                        cryptoText = encrypt(originalText);
                        System.out.println("Encrypt using List DS");
                    }else{
                        cryptoText = decrypt(originalText);
                        System.out.println("Decrypt using List DS");
                    }
                } catch (Exception ex) {
                    // Handle the exception, like showing an error message
                    JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    cryptoText = originalText; // Set a default value
                }

                // Remove all existing runs from the paragraph
                List<XWPFRun> runs = paragraph.getRuns();
                for (int i = runs.size() - 1; i >= 0; i--) {
                    paragraph.removeRun(i);
                }

                // Add a new run with the encrypted text
                XWPFRun newRun = paragraph.createRun();
                newRun.setText(cryptoText);
            }

            // Save the modified document
            try (FileOutputStream fos = new FileOutputStream(file)) {
                doc.write(fos);
            }
        } catch (IOException ex) {
            Logger.getLogger(Main_Menu.class.getName()).log(Level.SEVERE, null, ex);
            // Handle the exception, like showing an error message
            JOptionPane.showMessageDialog(this, "Error encrypting file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        // Handle the case where the file does not exist or is empty
        JOptionPane.showMessageDialog(this, "File does not exist or is empty", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }
    
    
        private void processWordFileArray(File file) throws IOException {
        if (file.exists() && file.length() > 0) {
            try (FileInputStream fis = new FileInputStream(file);
                 XWPFDocument doc = new XWPFDocument(fis)) {

                // Modify text in each paragraph
                for (XWPFParagraph paragraph : doc.getParagraphs()) {
                    // Get the text from the paragraph
                    String originalText = paragraph.getText();

                    String cryptoText;
                    try {
                        // Encrypt or decrypt the text
                        if (isEncrypt) {
                            cryptoText = encrypt(originalText);
                            System.out.println("Encrypt using Array DS");
                        } else {
                            cryptoText = decrypt(originalText);
                            System.out.println("Decrypt using Array DS");
                        }
                    } catch (Exception ex) {
                        // Handle the exception, like showing an error message
                        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        cryptoText = originalText; // Set a default value
                    }

                    // Get the runs as an array
                    XWPFRun[] runs = paragraph.getRuns().toArray(new XWPFRun[0]);

                    // Remove all existing runs from the paragraph
                    for (int i = runs.length - 1; i >= 0; i--) {
                        paragraph.removeRun(i);
                    }

                    // Add a new run with the encrypted or decrypted text
                    XWPFRun newRun = paragraph.createRun();
                    newRun.setText(cryptoText);
                }

                // Save the modified document
                try (FileOutputStream fos = new FileOutputStream(file)) {
                    doc.write(fos);
                }
            } catch (IOException ex) {
                Logger.getLogger(Main_Menu.class.getName()).log(Level.SEVERE, null, ex);
                // Handle the exception, like showing an error message
                JOptionPane.showMessageDialog(this, "Error encrypting file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Handle the case where the file does not exist or is empty
            JOptionPane.showMessageDialog(this, "File does not exist or is empty", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
       private void processWordFileHash(File file) throws IOException {
        if (file.exists() && file.length() > 0) {
            try (FileInputStream fis = new FileInputStream(file);
                 XWPFDocument doc = new XWPFDocument(fis)) {

                // Modify text in each paragraph
                for (XWPFParagraph paragraph : doc.getParagraphs()) {
                    // Get the text from the paragraph
                    String originalText = paragraph.getText();

                    String cryptoText;
                    try {
                        // Encrypt or decrypt the text
                        if (isEncrypt) {
                            cryptoText = encrypt(originalText);
                            System.out.println("Encrypt using Hash DS");
                        } else {
                            cryptoText = decrypt(originalText);
                            System.out.println("Decrypt using Hash DS");
                        }
                    } catch (Exception ex) {
                        // Handle the exception, like showing an error message
                        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        cryptoText = originalText; // Set a default value
                    }

                    // Store runs in a HashMap with their indices as keys
                    Map<Integer, XWPFRun> runsMap = new HashMap<>();
                    List<XWPFRun> runs = paragraph.getRuns();
                    for (int i = 0; i < runs.size(); i++) {
                        runsMap.put(i, runs.get(i));
                    }

                    // Remove all existing runs from the paragraph
                    for (int i = runsMap.size() - 1; i >= 0; i--) {
                        paragraph.removeRun(i);
                    }

                    // Add a new run with the encrypted or decrypted text
                    XWPFRun newRun = paragraph.createRun();
                    newRun.setText(cryptoText);
                }

                // Save the modified document
                try (FileOutputStream fos = new FileOutputStream(file)) {
                    doc.write(fos);
                }
            } catch (IOException ex) {
                Logger.getLogger(Main_Menu.class.getName()).log(Level.SEVERE, null, ex);
                // Handle the exception, like showing an error message
                JOptionPane.showMessageDialog(this, "Error encrypting file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Handle the case where the file does not exist or is empty
            JOptionPane.showMessageDialog(this, "File does not exist or is empty", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
       
       
    private long measureMemoryUsage() {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc(); // Suggest garbage collection
        return runtime.totalMemory() - runtime.freeMemory();
    }

//    private void measureTimeAndMemory(Runnable task) {
//        // Measure initial memory usage
//        long initialMemory = measureMemoryUsage();
//
//        // Measure start time
//        long startTime = System.nanoTime();
//
//        // Execute the task
//        task.run();
//
//        // Measure end time
//        long endTime = System.nanoTime();
//
//        // Measure final memory usage
//        long finalMemory = measureMemoryUsage();
//
//        // Calculate elapsed time and memory usage
//        long elapsedTime = endTime - startTime;
//        long memoryUsed = finalMemory - initialMemory;
//
//        // Convert elapsed time to milliseconds
//        double elapsedTimeMillis = elapsedTime / 1_000_000.0;
//
//        // Display results
//        System.out.printf("Elapsed time: %.2f ms\n", elapsedTimeMillis);
//        System.out.printf("Memory used: %d bytes\n", memoryUsed);
//        totalElapsedTime+= elapsedTimeMillis;
//        totalMemoryUsed+= memoryUsed;
//        System.out.printf("totalElapsedTime: %.2f ms\n", totalElapsedTime);
//        System.out.printf("totalMemoryUsed: %d bytes\n", totalMemoryUsed);
//        
//        // Convert totalElapsedTime to string
//        String totalElapsedTimeString = String.format("%.2f s", totalElapsedTime/1000);
//        String totalMemoryUsedString = String.format("%d kb", totalMemoryUsed/1000);
//        timeLabel.setText(totalElapsedTimeString);
//        memoLabel.setText(totalMemoryUsedString);
//        
//    }
    
    
    
      private void measureTimeAndMemory(Runnable task) {
        // Measure initial memory usage
        long initialMemory = measureMemoryUsage();

        // Measure start time
        long startTime = System.nanoTime();

        // Execute the task
        task.run();

        // Measure end time
        long endTime = System.nanoTime();

        // Measure final memory usage
        long finalMemory = measureMemoryUsage();

        // Calculate elapsed time and memory usage
        long elapsedTime = endTime - startTime;
        long memoryUsed = finalMemory - initialMemory;

        // Convert elapsed time to milliseconds
        double elapsedTimeMillis = elapsedTime / 1_000_000.0;

        // Display results
        System.out.printf("Elapsed time: %.2f ms\n", elapsedTimeMillis);
        System.out.printf("Memory used: %d bytes\n", memoryUsed);
        totalElapsedTime+= elapsedTimeMillis;
        totalMemoryUsed+= Math.abs(memoryUsed); // Take the absolute value to prevent negative accumulation
        System.out.printf("totalElapsedTime: %.2f ms\n", totalElapsedTime);
        System.out.printf("totalMemoryUsed: %d bytes\n", totalMemoryUsed);
        
        // Convert totalElapsedTime to string
        String totalElapsedTimeString = String.format("%.4f s", totalElapsedTime/1000);
        String totalMemoryUsedString = String.format("%d kb", totalMemoryUsed/1000);
        txtTime.setText(totalElapsedTimeString);
        txtMemo.setText(totalMemoryUsedString);
        
    }
    

    private void processFileAndMeasurePerformance(File file, String method) throws IOException {
        switch (method) {
            case "array":
                measureTimeAndMemory(() -> {
                    try {
                        processWordFileArray(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                break;
            case "hash":
                measureTimeAndMemory(() -> {
                    try {
                        processWordFileHash(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                break;
            default:
                measureTimeAndMemory(() -> {
                    try {
                        processWordFile(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                break;
        }
    }
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        devPanel = new javax.swing.JPanel();
        radioList = new javax.swing.JRadioButton();
        radioArray = new javax.swing.JRadioButton();
        radioHash = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        txtTime = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtMemo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtPath = new javax.swing.JTextField();
        btnDecrypt = new javax.swing.JButton();
        btnEncrypt = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        btnNewKey = new javax.swing.JButton();
        encrytoLogo = new javax.swing.JLabel();
        logo = new javax.swing.JLabel();

        jFormattedTextField1.setText("jFormattedTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));

        devPanel.setBackground(new java.awt.Color(58, 58, 58));
        devPanel.setForeground(new java.awt.Color(255, 255, 255));

        radioList.setForeground(new java.awt.Color(255, 255, 255));
        radioList.setText("List");
        radioList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioListActionPerformed(evt);
            }
        });

        radioArray.setForeground(new java.awt.Color(255, 255, 255));
        radioArray.setText("Array");
        radioArray.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioArrayActionPerformed(evt);
            }
        });

        radioHash.setForeground(new java.awt.Color(255, 255, 255));
        radioHash.setText("Hash");
        radioHash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioHashActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("DS options");

        txtTime.setBackground(new java.awt.Color(51, 51, 51));
        txtTime.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        txtTime.setForeground(new java.awt.Color(255, 255, 255));
        txtTime.setCaretColor(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Montserrat", 1, 10)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("t|s|:");

        txtMemo.setBackground(new java.awt.Color(51, 51, 51));
        txtMemo.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        txtMemo.setForeground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Montserrat", 1, 10)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("m|kb|:");

        javax.swing.GroupLayout devPanelLayout = new javax.swing.GroupLayout(devPanel);
        devPanel.setLayout(devPanelLayout);
        devPanelLayout.setHorizontalGroup(
            devPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(devPanelLayout.createSequentialGroup()
                .addGroup(devPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, devPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel8))
                    .addGroup(devPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(devPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(radioHash, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(radioArray, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(radioList, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTime, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMemo, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(devPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        devPanelLayout.setVerticalGroup(
            devPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(devPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(radioArray)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioList)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioHash)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtMemo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel3.setDoubleBuffered(false);

        jLabel1.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ENCRYPTO");

        jLabel7.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("path files");

        txtPath.setBackground(new java.awt.Color(51, 51, 51));
        txtPath.setForeground(new java.awt.Color(255, 255, 255));
        txtPath.setText("D:\\\\sandbox");
        txtPath.setSelectionColor(new java.awt.Color(255, 255, 255));

        btnDecrypt.setBackground(new java.awt.Color(51, 51, 51));
        btnDecrypt.setFont(new java.awt.Font("Montserrat", 1, 10)); // NOI18N
        btnDecrypt.setForeground(new java.awt.Color(255, 255, 255));
        btnDecrypt.setText("DECRYPT");
        btnDecrypt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDecryptActionPerformed(evt);
            }
        });

        btnEncrypt.setBackground(new java.awt.Color(51, 51, 51));
        btnEncrypt.setFont(new java.awt.Font("Montserrat", 1, 10)); // NOI18N
        btnEncrypt.setForeground(new java.awt.Color(255, 255, 255));
        btnEncrypt.setText("ENCRYPT");
        btnEncrypt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEncryptActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Montserrat", 0, 8)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("ctrl+e : encrypt | ctrl+d : decrypt | ctrl+u : dev mode");

        btnNewKey.setBackground(new java.awt.Color(51, 51, 51));
        btnNewKey.setForeground(new java.awt.Color(255, 255, 255));
        btnNewKey.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/key.png"))); // NOI18N
        btnNewKey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewKeyActionPerformed(evt);
            }
        });

        encrytoLogo.setText(" ");

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/encrypto.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(encrytoLogo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addComponent(btnNewKey, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btnDecrypt, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEncrypt, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel7)
                                .addComponent(txtPath, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(encrytoLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel1)))
                .addGap(13, 13, 13)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDecrypt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEncrypt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnNewKey, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(devPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(devPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEncryptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEncryptActionPerformed
        path = txtPath.getText();
        isEncrypt = true;

        // Show dialog for 2 seconds
        JDialog dialog = createAutoClosingDialog("Encrypting. . .", 1000);
        dialog.setVisible(true);
        
            // Process all files in the directory
        File directory = new File(path);
        if (directory.isDirectory()) {
            File[] files = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".docx"));
            if (files != null) {
                totalElapsedTime = 0;
                totalMemoryUsed = 0;
                for (File file : files) {
                    try {
//                        processWordFileHash(file); // Code that might throw IOException
                          processFileAndMeasurePerformance(file, mode);
                        

                    } catch (IOException ex) {
                        Logger.getLogger(Main_Menu.class.getName()).log(Level.SEVERE, null, ex);
                        // Handle the exception, like showing an error message
                        JOptionPane.showMessageDialog(this, "Error encrypting file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }


    }//GEN-LAST:event_btnEncryptActionPerformed

    private void btnDecryptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDecryptActionPerformed
       path = txtPath.getText();
       isEncrypt = false;

        // Show dialog for 2 seconds
        JDialog dialog = createAutoClosingDialog("Decrypting. . .", 1000);
        dialog.setVisible(true);
        
            // Process all files in the directory
        File directory = new File(path);
        if (directory.isDirectory()) {
            File[] files = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".docx"));
            if (files != null) {
                totalElapsedTime = 0;
                totalMemoryUsed = 0;
                for (File file : files) {
                    try {
//                        processWordFileHash(file); // Code that might throw IOException
                          processFileAndMeasurePerformance(file, mode);
                        

                    } catch (IOException ex) {
                        Logger.getLogger(Main_Menu.class.getName()).log(Level.SEVERE, null, ex);
                        // Handle the exception, like showing an error message
                        JOptionPane.showMessageDialog(this, "Error encrypting file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }//GEN-LAST:event_btnDecryptActionPerformed

    private void radioListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioListActionPerformed
        // TODO add your handling code here:
        if(radioList.isSelected()){
            
            radioArray.setSelected(false);
            radioHash.setSelected(false);
            
            isList = 1;
            isArray = 0;
            isHash = 0;
            
            mode = "default";
            
            
            System.out.println("isList: " + isList);
            System.out.println("isArray: " + isArray);
            System.out.println("isHash: " + isHash);
        }
    }//GEN-LAST:event_radioListActionPerformed

    private void radioArrayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioArrayActionPerformed
        // TODO add your handling code here:
        if(radioArray.isSelected()){
            radioList.setSelected(false);
            radioHash.setSelected(false);
            
            isList = 0;
            isArray = 1;
            isHash = 0;
            
            mode = "array";
            
            System.out.println("isList: " + isList);
            System.out.println("isArray: " + isArray);
            System.out.println("isHash: " + isHash);
        }
    }//GEN-LAST:event_radioArrayActionPerformed

    private void radioHashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioHashActionPerformed
        // TODO add your handling code here:
        if(radioHash.isSelected()){
            radioArray.setSelected(false);
            radioList.setSelected(false);
            
            isList = 0;
            isArray = 0;
            isHash = 1;
            
            mode = "hash";
            
            System.out.println("isList: " + isList);
            System.out.println("isArray: " + isArray);
            System.out.println("isHash: " + isHash);
        }
    }//GEN-LAST:event_radioHashActionPerformed

    private void btnNewKeyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewKeyActionPerformed
        // TODO add your handling code here:
                // Show dialog for 2 seconds
        JDialog dialog = createAutoClosingDialog("Generating new key. . .", 1000);
        dialog.setVisible(true);
        getSecretKey();
    }//GEN-LAST:event_btnNewKeyActionPerformed

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
            java.util.logging.Logger.getLogger(Main_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main_Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDecrypt;
    private javax.swing.JButton btnEncrypt;
    private javax.swing.JButton btnNewKey;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JPanel devPanel;
    private javax.swing.JLabel encrytoLogo;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel logo;
    private javax.swing.JRadioButton radioArray;
    private javax.swing.JRadioButton radioHash;
    private javax.swing.JRadioButton radioList;
    private javax.swing.JTextField txtMemo;
    private javax.swing.JTextField txtPath;
    private javax.swing.JTextField txtTime;
    // End of variables declaration//GEN-END:variables
}
