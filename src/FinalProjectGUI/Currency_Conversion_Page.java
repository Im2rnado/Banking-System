/**
 *
 * @author Ibrahim, Yassin, Islam, Hatem, and Khattab
 */
package FinalProjectGUI;

import FinalProjectClasses.*;
import javax.swing.JOptionPane;
import java.util.LinkedHashMap;

public class Currency_Conversion_Page extends javax.swing.JFrame {

    private CurrencyConverter currencyConverter;
    private Customer customer;

    public Currency_Conversion_Page(Customer customer) {
        this.customer = customer;
        LinkedHashMap<String, Double> exchangeRates = new LinkedHashMap<>();
        exchangeRates.put("USD", 1.0);
        exchangeRates.put("EGP", 50.56);
        exchangeRates.put("EUR", 0.98);
        exchangeRates.put("GBP", 0.82);
        exchangeRates.put("JPY", 157.6);
        exchangeRates.put("CHF", 0.92);
        exchangeRates.put("AUD", 1.62);
        exchangeRates.put("CAD", 1.44);
        exchangeRates.put("CNY", 7.33);
        exchangeRates.put("INR", 86.16);
        exchangeRates.put("NZD", 1.8);
        exchangeRates.put("AED", 3.67);
        exchangeRates.put("SAR", 3.75);
        exchangeRates.put("KWD", 0.31);
        exchangeRates.put("BHD", 0.38);
        exchangeRates.put("QAR", 3.65);

        currencyConverter = new CurrencyConverter(1, "USD", exchangeRates);
        initComponents();
        initializeCurrencyFields();
    }

    private void initializeCurrencyFields() {
        sourceCurrencyField.removeAllItems();
        targetCurrencyField.removeAllItems();

        LinkedHashMap<String, Double> currencies = currencyConverter.getExchangeRates();

        for (String key : currencies.keySet()) {
            sourceCurrencyField.addItem(key);
            targetCurrencyField.addItem(key);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        amountLabel = new javax.swing.JLabel();
        sourceCurrencyLabel = new javax.swing.JLabel();
        targetCurrencyLabel = new javax.swing.JLabel();
        amountField = new javax.swing.JTextField();
        sourceCurrencyField = new javax.swing.JComboBox<String>();
        targetCurrencyField = new javax.swing.JComboBox<String>();
        convertButton = new javax.swing.JButton();
        returnButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();

        jPanel4.setBackground(new java.awt.Color(204, 255, 255));

        jLabel10.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        jLabel10.setText("Text");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addComponent(jLabel10)
                                .addContainerGap(256, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel10)
                                .addContainerGap(236, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel5.setBackground(new java.awt.Color(204, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        titleLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        titleLabel.setText("Currency Converter");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(102, Short.MAX_VALUE)
                                .addComponent(titleLabel)
                                .addGap(81, 81, 81))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(titleLabel)
                                .addContainerGap(32, Short.MAX_VALUE))
        );

        amountLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        amountLabel.setText("Amount");

        sourceCurrencyLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        sourceCurrencyLabel.setText("Source Currency");

        targetCurrencyLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        targetCurrencyLabel.setText("Target Currency");

        amountField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amountFieldActionPerformed(evt);
            }
        });

        sourceCurrencyField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sourceCurrencyFieldActionPerformed(evt);
            }
        });

        targetCurrencyField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                targetCurrencyFieldActionPerformed(evt);
            }
        });

        convertButton.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        convertButton.setText("Convert");
        convertButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        convertButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                convertButtonActionPerformed(evt);
            }
        });

        returnButton.setFont(new java.awt.Font("Helvetica Neue", 3, 13)); // NOI18N
        returnButton.setText("Return");
        returnButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        returnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnButtonActionPerformed(evt);
            }
        });

        exitButton.setFont(new java.awt.Font("Helvetica Neue", 3, 13)); // NOI18N
        exitButton.setText("Exit");
        exitButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                                .addComponent(targetCurrencyLabel)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(targetCurrencyField, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                                                .addComponent(sourceCurrencyLabel)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(sourceCurrencyField, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                                                .addComponent(amountLabel)
                                                                .addGap(161, 161, 161)
                                                                .addComponent(amountField, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addContainerGap(66, Short.MAX_VALUE))
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(convertButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(43, 43, 43)
                                                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(returnButton, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap())))
        );
        jPanel5Layout.setVerticalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(42, 42, 42)
                                                .addComponent(amountLabel))
                                        .addComponent(amountField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(sourceCurrencyLabel)
                                        .addComponent(sourceCurrencyField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(targetCurrencyLabel)
                                        .addComponent(targetCurrencyField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(31, 31, 31)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(convertButton, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addGap(0, 23, Short.MAX_VALUE)
                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(returnButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void convertButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_convertButtonActionPerformed
        try {
            double amount = Double.parseDouble(amountField.getText());
            String sourceCurrency = (String) sourceCurrencyField.getSelectedItem();
            String targetCurrency = (String) targetCurrencyField.getSelectedItem();

            if (sourceCurrency == null || targetCurrency == null) {
                JOptionPane.showMessageDialog(this, "Please select both currencies", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double convertedAmount = currencyConverter.convert(amount, sourceCurrency, targetCurrency);
            if (convertedAmount >= 0) {
                JOptionPane.showMessageDialog(this, String.format("Converted Amount: %.2f %s", convertedAmount, targetCurrency));
            } else {
                JOptionPane.showMessageDialog(this, "Invalid currency or conversion error.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric amount.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_convertButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void amountFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amountFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_amountFieldActionPerformed

    private void sourceCurrencyFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sourceCurrencyFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sourceCurrencyFieldActionPerformed

    private void targetCurrencyFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_targetCurrencyFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_targetCurrencyFieldActionPerformed

    private void returnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnButtonActionPerformed
        Customer_Dashboard x = new Customer_Dashboard(this.customer);
        x.setVisible(true);
        dispose();

    }//GEN-LAST:event_returnButtonActionPerformed

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
            java.util.logging.Logger.getLogger(Currency_Conversion_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Currency_Conversion_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Currency_Conversion_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Currency_Conversion_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            private Customer customer;

            public void run() {
                new Currency_Conversion_Page(this.customer).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField amountField;
    private javax.swing.JLabel amountLabel;
    private javax.swing.JButton convertButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JButton returnButton;
    private javax.swing.JComboBox<String> sourceCurrencyField;
    private javax.swing.JComboBox<String> targetCurrencyField;
    private javax.swing.JLabel sourceCurrencyLabel;
    private javax.swing.JLabel targetCurrencyLabel;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
