import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JoeAutomotiveGUI extends JFrame {
   private JCheckBox chkbox_oilChange;
   private JCheckBox chkbox_inspection;
   private JCheckBox chkbox_lubeJob;
   private JCheckBox chkbox_muffler;
   private JCheckBox chkbox_radiatorFlush;
   private JCheckBox chkbox_tire;
   private JCheckBox chkbox_transmissionFlush;
   private JTextPane txtPane_bill;
   private JButton btn_showBill;
   private JSpinner spinner_labourHours;
   private JPanel service_panel;
   private JPanel mainPane;
   
   // declaring the constants
   private final static double OIL_CHANGE = 35.00;
   private final static double LUBE_JOB = 25.00;
   private final static double RADIATOR_FLUSH = 50.00;
   private final static double TRANSMISSION_FLUSH = 120.00;
   private final static double INSPECTION = 35.00;
   private final static double MUFFLER_REPLACE = 200.00;
   private final static double TIRE_ROTATE = 20.00;
   private final static double LABOUR_PER_HOUR = 60.00;
   /**
   * Launch the application.
   */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               JoeAutomotiveGUI frame = new JoeAutomotiveGUI();
               frame.setTitle("Joe's Automotive");
               frame.setResizable(false);
               frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }
   /**
   * Create the frame.
   */
   public JoeAutomotiveGUI() {
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 420, 552);
      mainPane = new JPanel();
      mainPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(mainPane);
      mainPane.setLayout(null);
      service_panel = new JPanel();
      service_panel.setBorder(new TitledBorder(null, "Select the maintenance services.",
      TitledBorder.LEADING, TitledBorder.TOP, null, null));
      service_panel.setBounds(30, 11, 320, 227);
      mainPane.add(service_panel);
      service_panel.setLayout(null);
      addCheckBoxes();
      txtPane_bill = new JTextPane();
      mainPane.add(txtPane_bill);
      txtPane_bill.setBounds(30, 393, 320, 109);
      txtPane_bill.setEditable(false);
      JScrollPane jp = new JScrollPane(txtPane_bill);
      mainPane.add(jp);
      jp.setBounds(30, 393, 320, 109);
      JLabel lbl_billingInfo = new JLabel("Billing Information");
      lbl_billingInfo.setFont(new Font("Tahoma", Font.PLAIN, 13));
      lbl_billingInfo.setBounds(30, 352, 135, 21);
      mainPane.add(lbl_billingInfo);
      btn_showBill = new JButton("Show Bill");
      btn_showBill.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            if(!validateInput()) {
               JOptionPane.showMessageDialog(null, "Please select at least 1 service");
            } else {
               displayBillAmount();
            }
         }
      });
      btn_showBill.setBounds(248, 307, 103, 32);
      mainPane.add(btn_showBill);
      spinner_labourHours = new JSpinner();
      spinner_labourHours.setFont(new Font("Tahoma", Font.PLAIN, 15));
      spinner_labourHours.setModel(new SpinnerNumberModel(1.0, 0.0, 15.0, 1.0));
      spinner_labourHours.setBounds(40, 281, 66, 32);
      mainPane.add(spinner_labourHours);
      JLabel lblNewLabel = new JLabel("Select Number of labor hours");
      lblNewLabel.setBounds(30, 253, 189, 21);
      mainPane.add(lblNewLabel);
   }
   
   // method to add checkboxes
   private void addCheckBoxes() {
      chkbox_oilChange = new JCheckBox("Oil Change");
      chkbox_oilChange.setBounds(21, 34, 138, 23);
      service_panel.add(chkbox_oilChange);
      chkbox_inspection = new JCheckBox("Inspection");
      chkbox_inspection.setBounds(150, 34, 138, 23);
      service_panel.add(chkbox_inspection);
      chkbox_lubeJob = new JCheckBox("Lube Job");
      chkbox_lubeJob.setBounds(21, 81, 97, 23);
      service_panel.add(chkbox_lubeJob);
      chkbox_muffler = new JCheckBox("Muffler Replacement");
      chkbox_muffler.setBounds(150, 81, 162, 23);
      service_panel.add(chkbox_muffler);
      chkbox_radiatorFlush = new JCheckBox("Radiator Flush");
      chkbox_radiatorFlush.setBounds(21, 131, 138, 23);
      service_panel.add(chkbox_radiatorFlush);
      chkbox_tire = new JCheckBox("Tire Rotation");
      chkbox_tire.setBounds(150, 131, 121, 23);
      service_panel.add(chkbox_tire);
      chkbox_transmissionFlush = new JCheckBox("Transmission Flush");
      chkbox_transmissionFlush.setBounds(21, 180, 162, 23);
      service_panel.add(chkbox_transmissionFlush);
   }
   
   // method to check whether at least 1 checkbox was selected or not
   private boolean validateInput() {
      if(chkbox_oilChange.isSelected())
         return true;
      if(chkbox_inspection.isSelected())
         return true;
      if(chkbox_lubeJob.isSelected())
         return true;
      if(chkbox_muffler.isSelected())
         return true;
      if(chkbox_radiatorFlush.isSelected())
         return true;
      if(chkbox_tire.isSelected())
         return true;
      if(chkbox_transmissionFlush.isSelected())
         return true;
      return false;
   }
   
   // method to calculate and display the bill amount
   private void displayBillAmount() {
      double bill = 0;
      String out = "";
      
      if(chkbox_oilChange.isSelected()) {
         bill += OIL_CHANGE;
         out += "Oil Change = $" + OIL_CHANGE + "\n";
      }
      
      if(chkbox_inspection.isSelected()) {
         bill += INSPECTION;
         out += "Inspection = $" + INSPECTION + "\n";
      }
      
      if(chkbox_lubeJob.isSelected()) {
         bill += LUBE_JOB;
         out += "Lube Job = $" + LUBE_JOB + "\n";
      }
      if(chkbox_muffler.isSelected()) {
         bill += MUFFLER_REPLACE;
         out += "Muffler Replace = $" + MUFFLER_REPLACE + "\n";
      }
      
      if(chkbox_radiatorFlush.isSelected()) {
         bill += RADIATOR_FLUSH;
         out += "Radiator Flush = $" + RADIATOR_FLUSH+ "\n";
      }
      
      if(chkbox_tire.isSelected()) {
         bill += TIRE_ROTATE;
         out += "Tire Rotate = $" + TIRE_ROTATE+ "\n";
      }
      
      if(chkbox_transmissionFlush.isSelected()) {
         bill += TRANSMISSION_FLUSH;
         out += "Transmission Flush = $" + TRANSMISSION_FLUSH + "\n";
      }
      
      double hours = (Double)spinner_labourHours.getValue();
      double labourCharge= hours * LABOUR_PER_HOUR;
      if(hours > 0) {
         bill += labourCharge;
         out += "Labour Charge ($60 / hour) = " + hours + " * " + LABOUR_PER_HOUR +" = $" + labourCharge + "\n";
      }
      
      out += "Total Charges = $" + bill + "\n";
      txtPane_bill.setText(out);
   }
}