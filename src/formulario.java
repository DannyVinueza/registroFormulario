import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class formulario {
    PreparedStatement ps;
    private JPanel JPformulario;
    private JLabel titulo;
    private JLabel JLid;
    private JLabel JLnombre;
    private JLabel JLapellido;
    private JLabel JLcorreo;
    private JLabel JLdireccion;
    private JLabel JLedad;
    private JButton JBUguardar;
    private JTextField TFnombre;
    private JTextField TFid;
    private JTextField TFcelular;
    private JTextField TFcorreo;
    private JTextField TFdireccion;
    private JTextField TFedad;


    public formulario() {
        JBUguardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection con;
                try{
                    con = getConection();
                    ps = con.prepareStatement("INSERT INTO registro (ID, NOMBRE, CELULAR, CORREO, DIRECCION, EDAD) VALUES (?, ?, ?, ?, ?, ?);");
                    ps.setString(1, TFid.getText());
                    ps.setString(2, TFnombre.getText());
                    ps.setString(3, TFcelular.getText());
                    ps.setString(4,TFcorreo.getText());
                    ps.setString(5,TFdireccion.getText());
                    ps.setString(6, TFedad.getText());
                    System.out.println(ps);//Imprime para ver los datos ingresados por consola

                    int res = ps.executeUpdate();
                    if(res > 0){
                        JOptionPane.showMessageDialog(null, "Persona Guardada");
                    }else{
                        JOptionPane.showMessageDialog(null,"Error al guardar");
                    }
                    con.close();
                }catch(HeadlessException | SQLException f){
                    System.err.println(f);
                }
            }
        });
    }

    public static void main(String[] args) {
            JFrame frame = new JFrame("JPformulario");
            frame.setContentPane(new formulario().JPformulario);
            frame.setSize(1000,1000);
            frame.pack();
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static Connection getConection(){
        Connection con = null;
        String base = "formulario";
        String url = "jdbc:mysql://localhost/" + base;
        String user = "root";
        String password = "1234";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,user,password);
        }catch(ClassNotFoundException | SQLException es){
            System.err.println(es);
        }
        return con;

    }
}
