/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jusmartlabsystem.SuperAdminDash;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import jusmartlabsystem.FormDB.DatabaseHelper;

/**
 *
 * @author PC
 */
public class ManageReportsSuperadmin extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(frmSuperAdminDashboard2.class.getName());
  
    

    /**
     * Creates new form frmAdminDashboard2
     */
    public ManageReportsSuperadmin() {
        initComponents();
        tblProblemSubmitedStudents.setAutoResizeMode(
        javax.swing.JTable.AUTO_RESIZE_OFF);
        tblProblemSubmitedStudents.setRowHeight(32);
        tblProblemSubmitedStudents.setFont(
        new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        tblProblemSubmitedStudents.getTableHeader().setFont(
        new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        tblProblemSubmitedStudents.getTableHeader()
        .setPreferredSize(new java.awt.Dimension(100, 30));
        tblProblemSubmitedStudents.getColumnModel().getColumn(0).setPreferredWidth(70);   // ID
        tblProblemSubmitedStudents.getColumnModel().getColumn(1).setPreferredWidth(170);  // Faculty
        tblProblemSubmitedStudents.getColumnModel().getColumn(2).setPreferredWidth(80);   // Batch
        tblProblemSubmitedStudents.getColumnModel().getColumn(3).setPreferredWidth(100);  // Class
        tblProblemSubmitedStudents.getColumnModel().getColumn(4).setPreferredWidth(200);  // Name
        tblProblemSubmitedStudents.getColumnModel().getColumn(5).setPreferredWidth(100);  // Server
        tblProblemSubmitedStudents.getColumnModel().getColumn(6).setPreferredWidth(100);  // PC
        tblProblemSubmitedStudents.getColumnModel().getColumn(7).setPreferredWidth(160);  // Problem Type
        tblProblemSubmitedStudents.getColumnModel().getColumn(8).setPreferredWidth(180);  // Date
        tblProblemSubmitedStudents.getColumnModel().getColumn(9).setPreferredWidth(250);  // Description
        tblProblemSubmitedStudents.getColumnModel().getColumn(10).setPreferredWidth(100); // Status
        ManageReportAdminBtn.setBackground(new java.awt.Color(51,51,51));
        loadSubmittedProblems();
        if(frmSuperAdminDashboard2.darkMode){
        applyDarkMode();

   }
        tblProblemSubmitedStudents.setDefaultRenderer(
        Object.class,

        new javax.swing.table.DefaultTableCellRenderer(){

        @Override
        public java.awt.Component getTableCellRendererComponent(

        javax.swing.JTable table,
        Object value,
        boolean isSelected,
        boolean hasFocus,
        int row,
        int column){

        java.awt.Component c =
        super.getTableCellRendererComponent(
        table, value, isSelected,
        hasFocus, row, column);

        String status =
        table.getValueAt(row, 10).toString();

        if(isSelected){

        c.setBackground(table.getSelectionBackground());
        c.setForeground(java.awt.Color.BLACK);

        }
        else if(status.equals("Pending")){

        c.setBackground(new java.awt.Color(255,220,220));
        c.setForeground(java.awt.Color.BLACK);

        }
        else{

        c.setBackground(new java.awt.Color(220,255,220));
        c.setForeground(java.awt.Color.BLACK);

        }
        
        if(isSelected){

        c.setBackground(table.getSelectionBackground());
        c.setForeground(java.awt.Color.WHITE);

    }
    else{

        c.setForeground(java.awt.Color.BLACK);

}

        return c;

        }

        });
        
    }
    private void loadSubmittedProblems() {

    try {

        Connection con = DatabaseHelper.getConnection();

        String sql =
        "SELECT " +
        "p.ProblemID, " +
        "f.FacultyName, " +
        "b.BatchName, " +
        "c.ClassName, " +
        "s.FullName, " +
        "p.ServerNo, " +
        "p.ComputerNumber, " +
        "p.ProblemType, " +
        "p.SubmittedAt, " +
        "p.ProblemDescription, " +
        "p.Status " +

        "FROM problemreports p " +

        "JOIN students s ON p.StudentID = s.StudentID " +
        "JOIN classes c ON s.ClassID = c.ClassID " +
        "JOIN batches b ON c.BatchID = b.BatchID " +
        "JOIN faculties f ON b.FacultyID = f.FacultyID";

        PreparedStatement pst =
        con.prepareStatement(sql);

        ResultSet rs =
        pst.executeQuery();

        DefaultTableModel model =
        (DefaultTableModel)
        tblProblemSubmitedStudents.getModel();

        model.setRowCount(0);

        while(rs.next()) {

            model.addRow(new Object[]{
                
                rs.getString("ProblemID"),
                rs.getString("FacultyName"),
                rs.getString("BatchName"),
                rs.getString("ClassName"),
                rs.getString("FullName"),
                rs.getString("ServerNo"),
                rs.getString("ComputerNumber"),
                rs.getString("ProblemType"),
                rs.getString("SubmittedAt"),
                rs.getString("ProblemDescription"),
                rs.getString("Status")
                    

            });

        }

    }

    catch (Exception e) {

        javax.swing.JOptionPane.showMessageDialog(this, e);

    }

}
    
    private void searchReports(){

    try {

        String search =
        txtSearch.getText();

        Connection con =
        DatabaseHelper.getConnection();

        String sql =
        "SELECT " +
        "p.ProblemID, " +
        "f.FacultyName, " +
        "b.BatchName, " +
        "c.ClassName, " +
        "s.FullName, " +
        "p.ServerNo, " +
        "p.ComputerNumber, " +
        "p.ProblemType, " +
        "p.SubmittedAt, " +
        "p.ProblemDescription, " +
        "p.Status " +

        "FROM problemreports p " +

        "JOIN students s ON p.StudentID=s.StudentID " +
        "JOIN classes c ON s.ClassID=c.ClassID " +
        "JOIN batches b ON c.BatchID=b.BatchID " +
        "JOIN faculties f ON b.FacultyID=f.FacultyID " +

        "WHERE s.StudentID LIKE ? "
        + "OR s.FullName LIKE ? "
        + "OR p.ServerNo LIKE ?";

        PreparedStatement pst =
        con.prepareStatement(sql);

        pst.setString(1, "%" + search + "%");
        pst.setString(2, "%" + search + "%");
        pst.setString(3, "%" + search + "%");

        ResultSet rs =
        pst.executeQuery();

        DefaultTableModel model =
        (DefaultTableModel)
        tblProblemSubmitedStudents.getModel();

        model.setRowCount(0);

        while(rs.next()){

            model.addRow(new Object[]{
                rs.getString("ProblemID"),
                rs.getString("FacultyName"),
                rs.getString("BatchName"),
                rs.getString("ClassName"),
                rs.getString("FullName"),
                rs.getString("ServerNo"),
                rs.getString("ComputerNumber"),
                rs.getString("ProblemType"),
                rs.getString("SubmittedAt"),
                rs.getString("ProblemDescription"),
                rs.getString("Status")

            });

        }

    }
    catch(Exception e){

        javax.swing.JOptionPane.showMessageDialog(this, e);

    }

}
    
        
    private void applyDarkMode(){
        
    jPanel14.setBackground(
    new java.awt.Color(20,20,20));

    DashboardContents2.setBackground(
    new java.awt.Color(30,30,30));
    
    DashboardAdminBtn.setBackground(
    new java.awt.Color(45,45,45));

    ManageReportAdminBtn.setBackground(
    new java.awt.Color(45,45,45));

    AdminProfileBtn.setBackground(
    new java.awt.Color(45,45,45));

    ChangeProfileBtn.setBackground(
    new java.awt.Color(45,45,45));

}
   private void applyLightMode(){

    DashboardContents2.setBackground(
    java.awt.Color.WHITE);

    jPanel14.setBackground(
    new java.awt.Color(51,153,255));

    DashboardAdminBtn.setBackground(
    new java.awt.Color(51,153,255));

    ManageReportAdminBtn.setBackground(
    new java.awt.Color(51,153,255));

    AdminProfileBtn.setBackground(
    new java.awt.Color(51,153,255));

    ChangeProfileBtn.setBackground(
    new java.awt.Color(51,153,255));

}
    
    private void resetButtonColors() {

    DashboardAdminBtn.setBackground(new java.awt.Color(51,153,255));
    ManageReportAdminBtn.setBackground(new java.awt.Color(51,153,255));
    AdminProfileBtn.setBackground(new java.awt.Color(51,153,255));
    ChangeProfileBtn.setBackground(new java.awt.Color(51,153,255));

}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel14 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        DashboardAdminBtn = new javax.swing.JButton();
        ManageReportAdminBtn = new javax.swing.JButton();
        AdminProfileBtn = new javax.swing.JButton();
        ChangeProfileBtn = new javax.swing.JButton();
        DashboardContents2 = new javax.swing.JPanel();
        ExitBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProblemSubmitedStudents = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        btnFix = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        btnDeleteAll = new javax.swing.JButton();
        cmbStatusFilter = new javax.swing.JComboBox<>();
        btnSearch = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        btnDarkMode = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel14.setBackground(new java.awt.Color(51, 153, 255));
        jPanel14.setMinimumSize(new java.awt.Dimension(190, 215));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel13.setBackground(new java.awt.Color(51, 51, 51));

        jPanel12.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel14.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 153, -1, -1));

        DashboardAdminBtn.setBackground(new java.awt.Color(51, 153, 255));
        DashboardAdminBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        DashboardAdminBtn.setForeground(new java.awt.Color(255, 255, 255));
        DashboardAdminBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jusmartlabsystem/Resources/home (4).png"))); // NOI18N
        DashboardAdminBtn.setText("Dashboard        ");
        DashboardAdminBtn.setActionCommand("Dashboard........");
        DashboardAdminBtn.setBorder(null);
        DashboardAdminBtn.setContentAreaFilled(false);
        DashboardAdminBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DashboardAdminBtn.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        DashboardAdminBtn.setIconTextGap(10);
        DashboardAdminBtn.addActionListener(this::DashboardAdminBtnActionPerformed);
        jPanel14.add(DashboardAdminBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 31, 180, 40));

        ManageReportAdminBtn.setBackground(new java.awt.Color(51, 153, 255));
        ManageReportAdminBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ManageReportAdminBtn.setForeground(new java.awt.Color(255, 255, 255));
        ManageReportAdminBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jusmartlabsystem/Resources/document.png"))); // NOI18N
        ManageReportAdminBtn.setText("Manage Report");
        ManageReportAdminBtn.setBorder(null);
        ManageReportAdminBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ManageReportAdminBtn.addActionListener(this::ManageReportAdminBtnActionPerformed);
        jPanel14.add(ManageReportAdminBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 77, 180, 40));

        AdminProfileBtn.setBackground(new java.awt.Color(51, 153, 255));
        AdminProfileBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        AdminProfileBtn.setForeground(new java.awt.Color(255, 255, 255));
        AdminProfileBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jusmartlabsystem/Resources/user.png"))); // NOI18N
        AdminProfileBtn.setText("Admin Profile    ");
        AdminProfileBtn.setBorder(null);
        AdminProfileBtn.setContentAreaFilled(false);
        AdminProfileBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AdminProfileBtn.addActionListener(this::AdminProfileBtnActionPerformed);
        jPanel14.add(AdminProfileBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 129, 180, 40));

        ChangeProfileBtn.setBackground(new java.awt.Color(51, 153, 255));
        ChangeProfileBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ChangeProfileBtn.setForeground(new java.awt.Color(255, 255, 255));
        ChangeProfileBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jusmartlabsystem/Resources/change.png"))); // NOI18N
        ChangeProfileBtn.setText("Change Profile   ");
        ChangeProfileBtn.setToolTipText("");
        ChangeProfileBtn.setBorder(null);
        ChangeProfileBtn.setContentAreaFilled(false);
        ChangeProfileBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ChangeProfileBtn.addActionListener(this::ChangeProfileBtnActionPerformed);
        jPanel14.add(ChangeProfileBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 175, 180, 40));

        DashboardContents2.setBackground(new java.awt.Color(255, 255, 255));
        DashboardContents2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ExitBtn.setBackground(new java.awt.Color(51, 153, 255));
        ExitBtn.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        ExitBtn.setForeground(new java.awt.Color(255, 255, 255));
        ExitBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jusmartlabsystem/Resources/closelatest(30).png"))); // NOI18N
        ExitBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ExitBtn.addActionListener(this::ExitBtnActionPerformed);
        DashboardContents2.add(ExitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 10, 50, 30));

        jPanel2.setBackground(new java.awt.Color(0, 153, 255));
        jPanel2.setForeground(new java.awt.Color(0, 153, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 930, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        DashboardContents2.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 930, 2));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 153, 255));
        jLabel1.setText("Submitted Problems Table");
        DashboardContents2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, 290, -1));

        tblProblemSubmitedStudents.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblProblemSubmitedStudents.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Faculty", "Batch", "Class", "Name", "Server No", "Compu No", "Problem Type", "Date", "Problem Desc", "Status"
            }
        ));
        tblProblemSubmitedStudents.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblProblemSubmitedStudents.setRowHeight(30);
        jScrollPane1.setViewportView(tblProblemSubmitedStudents);

        DashboardContents2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 930, 360));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        btnFix.setBackground(new java.awt.Color(51, 153, 255));
        btnFix.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnFix.setForeground(new java.awt.Color(255, 255, 255));
        btnFix.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jusmartlabsystem/Resources/check-mark.png"))); // NOI18N
        btnFix.setText("Fix");
        btnFix.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFix.addActionListener(this::btnFixActionPerformed);

        btnDelete.setBackground(new java.awt.Color(255, 0, 51));
        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jusmartlabsystem/Resources/bin_2.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDelete.addActionListener(this::btnDeleteActionPerformed);

        btnRefresh.setBackground(new java.awt.Color(51, 153, 255));
        btnRefresh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRefresh.setForeground(new java.awt.Color(255, 255, 255));
        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jusmartlabsystem/Resources/refresh-page-option_2.png"))); // NOI18N
        btnRefresh.setText("Refresh");
        btnRefresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRefresh.addActionListener(this::btnRefreshActionPerformed);

        btnPrint.setBackground(new java.awt.Color(51, 153, 255));
        btnPrint.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnPrint.setForeground(new java.awt.Color(255, 255, 255));
        btnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jusmartlabsystem/Resources/printer (2).png"))); // NOI18N
        btnPrint.setText("Print");
        btnPrint.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPrint.addActionListener(this::btnPrintActionPerformed);

        btnDeleteAll.setBackground(new java.awt.Color(255, 0, 51));
        btnDeleteAll.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDeleteAll.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jusmartlabsystem/Resources/bin_2.png"))); // NOI18N
        btnDeleteAll.setText("Delete all");
        btnDeleteAll.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDeleteAll.addActionListener(this::btnDeleteAllActionPerformed);

        cmbStatusFilter.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cmbStatusFilter.setForeground(new java.awt.Color(102, 102, 102));
        cmbStatusFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Pending", "Fixed" }));
        cmbStatusFilter.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmbStatusFilter.addActionListener(this::cmbStatusFilterActionPerformed);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(btnFix, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDelete)
                .addGap(12, 12, 12)
                .addComponent(cmbStatusFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRefresh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDeleteAll)
                .addContainerGap(116, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFix, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleteAll, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbStatusFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        DashboardContents2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 470, 930, 80));

        btnSearch.setBackground(new java.awt.Color(51, 153, 255));
        btnSearch.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jusmartlabsystem/Resources/magnifying-glass_2.png"))); // NOI18N
        btnSearch.setText("Search   ");
        btnSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSearch.addActionListener(this::btnSearchActionPerformed);
        DashboardContents2.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 110, 30));

        txtSearch.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });
        DashboardContents2.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 190, 30));

        btnDarkMode.setBackground(new java.awt.Color(51, 153, 255));
        btnDarkMode.setForeground(new java.awt.Color(255, 255, 255));
        btnDarkMode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jusmartlabsystem/Resources/user-interface(theme).png"))); // NOI18N
        btnDarkMode.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDarkMode.addActionListener(this::btnDarkModeActionPerformed);
        DashboardContents2.add(btnDarkMode, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 10, 50, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(DashboardContents2, javax.swing.GroupLayout.PREFERRED_SIZE, 1098, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, 1030, Short.MAX_VALUE)
            .addComponent(DashboardContents2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ExitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitBtnActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_ExitBtnActionPerformed

    private void cmbStatusFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbStatusFilterActionPerformed
        // TODO add your handling code here:
        try {

    String selected =
    cmbStatusFilter.getSelectedItem().toString();

    Connection con =
    DatabaseHelper.getConnection();

    String sql;

    if(selected.equals("All")){

        sql =
        "SELECT " +
        "p.ProblemID, " +
        "f.FacultyName, " +
        "b.BatchName, " +
        "c.ClassName, " +
        "s.FullName, " +
        "p.ServerNo, " +
        "p.ComputerNumber, " +
        "p.ProblemType, " +
        "p.SubmittedAt, " +
        "p.ProblemDescription, " +
        "p.Status " +
        "FROM problemreports p " +
        "JOIN students s ON p.StudentID=s.StudentID " +
        "JOIN classes c ON s.ClassID=c.ClassID " +
        "JOIN batches b ON c.BatchID=b.BatchID " +
        "JOIN faculties f ON b.FacultyID=f.FacultyID";

    } else {

        sql =
        "SELECT " +
        "p.ProblemID, " +
        "f.FacultyName, " +
        "b.BatchName, " +
        "c.ClassName, " +
        "s.FullName, " +
        "p.ServerNo, " +
        "p.ComputerNumber, " +
        "p.ProblemType, " +
        "p.SubmittedAt, " +
        "p.ProblemDescription, " +
        "p.Status " +
        "FROM problemreports p " +
        "JOIN students s ON p.StudentID=s.StudentID " +
        "JOIN classes c ON s.ClassID=c.ClassID " +
        "JOIN batches b ON c.BatchID=b.BatchID " +
        "JOIN faculties f ON b.FacultyID=f.FacultyID "
        + "WHERE p.Status=?";
    }

    PreparedStatement pst =
    con.prepareStatement(sql);

    if(!selected.equals("All")){

        pst.setString(1, selected);

    }

    ResultSet rs =
    pst.executeQuery();

    DefaultTableModel model =
    (DefaultTableModel)
    tblProblemSubmitedStudents.getModel();

    model.setRowCount(0);

    while(rs.next()){

        model.addRow(new Object[]{
            rs.getString("ProblemID"),
            rs.getString("FacultyName"),
            rs.getString("BatchName"),
            rs.getString("ClassName"),
            rs.getString("FullName"),
            rs.getString("ServerNo"),
            rs.getString("ComputerNumber"),
            rs.getString("ProblemType"),
            rs.getString("SubmittedAt"),
            rs.getString("ProblemDescription"),
            rs.getString("Status")

        });

    }

}
catch(Exception e){

    javax.swing.JOptionPane.showMessageDialog(this, e);

}
    }//GEN-LAST:event_cmbStatusFilterActionPerformed

    private void ChangeProfileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChangeProfileBtnActionPerformed
        // TODO add your handling code here:
        resetButtonColors();
        ChangeProfileBtn.setBackground(new java.awt.Color(51,51,51));
        frmSuperAdminChangeProfile frmAdminChangeProfile = new frmSuperAdminChangeProfile();
        frmAdminChangeProfile.setVisible(true);
        
    }//GEN-LAST:event_ChangeProfileBtnActionPerformed

    private void AdminProfileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdminProfileBtnActionPerformed
        // TODO add your handling code here:
        resetButtonColors();
        AdminProfileBtn.setBackground(new java.awt.Color(51,51,51));
        frmSuperAdminProfile frmAdminProfile = new frmSuperAdminProfile();
        frmAdminProfile.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_AdminProfileBtnActionPerformed

    private void ManageReportAdminBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ManageReportAdminBtnActionPerformed
        // TODO add your handling code here:
        resetButtonColors();
        ManageReportAdminBtn.setBackground(new java.awt.Color(51,51,51));
        ManageReportsSuperadmin MngReportamdn = new ManageReportsSuperadmin();
        MngReportamdn.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_ManageReportAdminBtnActionPerformed

    private void DashboardAdminBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DashboardAdminBtnActionPerformed
        // TODO add your handling code here:
        resetButtonColors();
        DashboardAdminBtn.setBackground(new java.awt.Color(51,51,51));
        frmSuperAdminDashboard2 frmAdminDashboard2 = new frmSuperAdminDashboard2();
        frmAdminDashboard2.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_DashboardAdminBtnActionPerformed

    private void btnFixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFixActionPerformed
        // TODO add your handling code here:
        try {

    int row = tblProblemSubmitedStudents.getSelectedRow();

    if(row == -1){

        javax.swing.JOptionPane.showMessageDialog(this,
        "Please select report");

        return;
    }

    String status =
    tblProblemSubmitedStudents.getValueAt(row, 10).toString();

    if(status.equals("Fixed")){

        javax.swing.JOptionPane.showMessageDialog(this,
        "Already fixed");

        return;
    }

    String studentName =
    tblProblemSubmitedStudents.getValueAt(row, 4).toString();

    Connection con =
    DatabaseHelper.getConnection();

    String sql =
    "UPDATE problemreports p "
    + "JOIN students s "
    + "ON p.StudentID=s.StudentID "
    + "SET p.Status='Fixed' "
    + "WHERE s.FullName=?";

    PreparedStatement pst =
    con.prepareStatement(sql);

    pst.setString(1, studentName);

    pst.executeUpdate();

    javax.swing.JOptionPane.showMessageDialog(this,
    "Problem fixed successfully");

    loadSubmittedProblems();

}
catch(Exception e){

    javax.swing.JOptionPane.showMessageDialog(this, e);

}
    }//GEN-LAST:event_btnFixActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        try {

    int row =
    tblProblemSubmitedStudents.getSelectedRow();

    if(row == -1){

        javax.swing.JOptionPane.showMessageDialog(this,
        "Please select report");

        return;
    }

    String status =
    tblProblemSubmitedStudents.getValueAt(row, 10).toString();

    if(!status.equals("Fixed")){

        javax.swing.JOptionPane.showMessageDialog(this,
        "Cannot delete pending report");

        return;
    }

    String studentName =
    tblProblemSubmitedStudents.getValueAt(row, 4).toString();

    Connection con =
    DatabaseHelper.getConnection();

    String sql =
    "DELETE p FROM problemreports p "
    + "JOIN students s "
    + "ON p.StudentID=s.StudentID "
    + "WHERE s.FullName=?";

    PreparedStatement pst =
    con.prepareStatement(sql);

    pst.setString(1, studentName);

    pst.executeUpdate();

    javax.swing.JOptionPane.showMessageDialog(this,
    "Deleted successfully");

    loadSubmittedProblems();

}
catch(Exception e){

    javax.swing.JOptionPane.showMessageDialog(this, e);

}
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
        loadSubmittedProblems();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnDeleteAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteAllActionPerformed
        // TODO add your handling code here:
        try {

    Connection con =
    DatabaseHelper.getConnection();

    String checkSql =
    "SELECT * FROM problemreports "
    + "WHERE Status='Pending'";

    PreparedStatement pst1 =
    con.prepareStatement(checkSql);

    ResultSet rs =
    pst1.executeQuery();

    if(rs.next()){

        javax.swing.JOptionPane.showMessageDialog(this,
        "Some reports still pending");

        return;
    }

    String deleteSql =
    "DELETE FROM problemreports";

    PreparedStatement pst2 =
    con.prepareStatement(deleteSql);

    pst2.executeUpdate();

    javax.swing.JOptionPane.showMessageDialog(this,
    "All reports deleted");

    loadSubmittedProblems();

}
catch(Exception e){

    javax.swing.JOptionPane.showMessageDialog(this, e);

}
    }//GEN-LAST:event_btnDeleteAllActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        searchReports();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        // TODO add your handling code here:
        if(txtSearch.getText().trim().equals("")){

    loadSubmittedProblems();

}
else{

    searchReports();

}
    }//GEN-LAST:event_txtSearchKeyReleased

    private void btnDarkModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDarkModeActionPerformed
        // TODO add your handling code here:
       if(frmSuperAdminDashboard2.darkMode == false){

        frmSuperAdminDashboard2.darkMode = true;

        applyDarkMode();

    }

    else{

        frmSuperAdminDashboard2.darkMode = false;

        applyLightMode();

    }
    }//GEN-LAST:event_btnDarkModeActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPrintActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new ManageReportsSuperadmin().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AdminProfileBtn;
    private javax.swing.JButton ChangeProfileBtn;
    private javax.swing.JButton DashboardAdminBtn;
    private javax.swing.JPanel DashboardContents2;
    private javax.swing.JButton ExitBtn;
    private javax.swing.JButton ManageReportAdminBtn;
    private javax.swing.JButton btnDarkMode;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDeleteAll;
    private javax.swing.JButton btnFix;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cmbStatusFilter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblProblemSubmitedStudents;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
