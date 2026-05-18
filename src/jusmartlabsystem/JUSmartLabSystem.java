/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jusmartlabsystem;

//import jusmartlabsystem.FormProblemReport.frmStudentReport;
import jusmartlabsystem.FormProblemReport.frmStudentReport;
import jusmartlabsystem.AdminDash.ManageReportsAdmin;
import jusmartlabsystem.AdminDash.frmAdminDashboard2;
import jusmartlabsystem.FormLogin.frmLogin;

/**
 *
 * @author PC
 */
public class JUSmartLabSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       try {

        for (javax.swing.UIManager.LookAndFeelInfo info :
                javax.swing.UIManager.getInstalledLookAndFeels()) {

            if ("Nimbus".equals(info.getName())) {

                javax.swing.UIManager.setLookAndFeel(info.getClassName());
                break;

            }

        }

    } catch (Exception e) {

        e.printStackTrace();

    }

    java.awt.EventQueue.invokeLater(() -> {

        new frmStudentReport().setVisible(true);

    });
               
  
              
          
        
    }
    
}
