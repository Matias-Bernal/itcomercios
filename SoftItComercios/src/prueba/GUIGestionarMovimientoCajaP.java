package prueba;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;

import cliente.LimitadorNroMax;
import cliente.ModeloTabla;

import common.Utils;

public class GUIGestionarMovimientoCajaP extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel jpPpal = null;			    private JPanel jpGestion = null;
    private JPanel jpDatos = null;			    private JPanel jpBuscador = null;
    private JButton jbCargar = null;		    
    private JButton jbAceptar = null;		    private JButton jbBorrar = null;
    private JLabel jlFechaMov = null;			
    private JLabel jlCodigo= null;				private JTextField jtfCodigo = null;
    private JLabel jlFecha= null;			    private JTextField jtfFecha = null;			
    private JLabel jlPeriodo= null;			    private JTextField jtfPeriodo = null;
    private JButton jbCambiarPeriodo= null;		
    private JLabel jlMes = null;				private JComboBox jcbMes;
	private JLabel jlAnio = null;				private JTextField jtfAnio;
    private JScrollPane jspDatos = null;
    public JTable jtDatos = null;			    private ModeloTabla modTabla = null;
    public String[] titulos = {"ID","Nro. C�digo","Fecha","Tipo de Movimiento","Importe","Forma de Pago","Descripci�n","Tipo y Nro Factura"};
    public Object[][] datos;
    private int mesLI;
	private int anioLI;

    private InputMap map = new InputMap();		
    
    public GUIGestionarMovimientoCajaP(int mes,int anio,JFrame guiPadre) {
    	super(guiPadre);
    	mesLI=mes;
		anioLI=anio;
    	datos = new Object[0][titulos.length];
        this.setSize(new java.awt.Dimension(740,540));
        this.setLocationRelativeTo(guiPadre);
        this.setResizable(false);
        this.setTitle("Gesti�n de Movimientos de Caja");
        this.setContentPane(getJPPpal());
        this.setModal(true);
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "pressed");
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "released");
    }

    public JPanel getJPPpal() {
        if (jpPpal == null) {
            jpPpal = new JPanel();
            jpPpal.setLayout(null);
            jpPpal.add(getJPGestion(), null);
            jpPpal.add(getJBAceptar(), null);
            jpPpal.add(getJPDatos(), null);
            jpPpal.add(getJPBuscador(), null);
            jpPpal.setBackground(Utils.colorFondo);
        }
        return jpPpal;
    }

    private JPanel getJPBuscador() {
    	if (jpBuscador == null) {
            jpBuscador = new JPanel();
            jlCodigo = new JLabel();
            jlCodigo.setBounds(new Rectangle(15,30,60,15));
            jlCodigo.setText("C�digo:");
            jlFecha = new JLabel();
            jlFecha.setBounds(new Rectangle(130,30,60,15));
            jlFecha.setText("Fecha:");
            jpBuscador.setLayout(null);
            jpBuscador.setBorder(javax.swing.BorderFactory.createTitledBorder(Utils.b, "Buscar",
                    javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                    javax.swing.border.TitledBorder.DEFAULT_POSITION,
                    new java.awt.Font("Dialog", java.awt.Font.BOLD, 12), java.awt.Color.black));
            jpBuscador.setBounds(new java.awt.Rectangle(415,25,300,65));
            jpBuscador.add(jlCodigo, null);
            jpBuscador.add(jlFecha, null);
            jpBuscador.add(getJLFechaMov(), null);
            jpBuscador.add(getJTFBuscadorCodigo(), null);
            jpBuscador.add(getJTFBuscadorFecha(), null);
            jpBuscador.setBackground(Utils.colorPanel);
        }
        return jpBuscador;
    }
    
    public JTextField getJTFBuscadorCodigo() {
        if (jtfCodigo == null) {
        	jtfCodigo = new JTextField();
        	jtfCodigo.setBounds(new Rectangle(65,30,50,22));
        	jtfCodigo.setDocument(new LimitadorNroMax(jtfCodigo));
        }
        return jtfCodigo;
    }
    
    public JTextField getJTFBuscadorFecha() {
        if (jtfFecha == null) {
        	jtfFecha = new JTextField();
        	jtfFecha.setBounds(new Rectangle(190,30,100,22));
        }
        return jtfFecha;
    }
    
    private JLabel getJLFechaMov() {
        if (jlFechaMov == null) {
        	jlFechaMov = new JLabel("(dd/mm/aaaa)");
        	jlFechaMov.setBounds(new java.awt.Rectangle(198,16,80,9));
        }
        return jlFechaMov;
    }
    
    private JPanel getJPDatos() {
        if (jpDatos == null) {
            jpDatos = new JPanel();
            jpDatos.setLayout(null);
            jpDatos.setBounds(new Rectangle(15,110,700,340));
            jpDatos.setBorder(javax.swing.BorderFactory.createTitledBorder(Utils.b, "Movimientos de Caja",
                    javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                    javax.swing.border.TitledBorder.DEFAULT_POSITION, null, null));
            jpDatos.add(getJSPDatos(), null);
            agregarPeriodoSelec();
            jpDatos.setBackground(Utils.colorPanel);
        }
        return jpDatos;
    }

    private JPanel getJPGestion() {
        if (jpGestion == null) {
            jpGestion = new JPanel();
            jpGestion.setLayout(null);
            jpGestion.setBounds(new Rectangle(15,25,280,65));
            jpGestion.setBorder(javax.swing.BorderFactory.createTitledBorder(Utils.b,
                    "Gesti�n", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                    javax.swing.border.TitledBorder.DEFAULT_POSITION, null, null));
            jpGestion.add(getJBCargar(), null);
            jpGestion.add(getJBBorrar(), null);
            jpGestion.setBackground(Utils.colorPanel);
        }
        return jpGestion;
    }

    public JButton getJBCargar() {
        if (jbCargar == null) {
            jbCargar = new JButton();
            jbCargar.setText("Ingresar");
            jbCargar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            jbCargar.setBounds(new Rectangle(20,25,100,25));
            jbCargar.setInputMap(0, map);
        }
        return jbCargar;
    }

    public JButton getJBBorrar() {
        if (jbBorrar == null) {
            jbBorrar = new JButton();
            jbBorrar.setText("Eliminar");
            jbBorrar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            jbBorrar.setBounds(new Rectangle(160,25,100,25));
            jbBorrar.setInputMap(0, map);
        }
        return jbBorrar;
    }

    public JButton getJBAceptar() {
        if (jbAceptar == null) {
            jbAceptar = new JButton();
            jbAceptar.setBounds(new java.awt.Rectangle(315,470,100,30));
            jbAceptar.setText("Salir");
            jbAceptar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            jbAceptar.setInputMap(0, map);
        }
        return jbAceptar;
    }

    private JScrollPane getJSPDatos() {
        if (jspDatos == null) {
                jspDatos = new JScrollPane();
                jspDatos.setBounds(new Rectangle(10,50,680,280));
                jspDatos.setViewportView(getJTDatos());
        }
        return jspDatos;
    }

    public JTable getJTDatos() {
        if (jtDatos == null) {
            modTabla = new ModeloTabla(titulos, datos);
            jtDatos = new JTable(modTabla);
            Utils.ocultarColumnaId(jtDatos);
            TableColumn columna1 = jtDatos.getColumn("Nro. C�digo");
            columna1.setPreferredWidth(80);
			columna1.setMaxWidth(80);
			columna1.setCellRenderer(Utils.alinearDerecha());
			TableColumn columna2 = jtDatos.getColumn("Fecha");
			columna2.setPreferredWidth(75);
			columna2.setMaxWidth(75);
			columna2.setCellRenderer(Utils.alinearCentro());
			TableColumn columna3 = jtDatos.getColumn("Tipo de Movimiento");
			columna3.setPreferredWidth(110);
			columna3.setMaxWidth(110);
			columna3.setCellRenderer(Utils.alinearCentro());
			
			TableColumn columna4 = jtDatos.getColumn("Importe");
         	columna4.setCellRenderer(Utils.alinearDerecha());
			TableColumn columna5 = jtDatos.getColumn("Forma de Pago");
			columna5.setCellRenderer(Utils.alinearCentro());
        }
        return jtDatos;
    }

    public void setActionListeners(ActionListener lis) {
        jbAceptar.addActionListener(lis);
        jbCargar.addActionListener(lis);
        jbBorrar.addActionListener(lis);
        jbCambiarPeriodo.addActionListener(lis);
    }
    
    public void repaint() {
        super.repaint();
    }
    
    public void setKeyListener(KeyListener lis) {
        jtfCodigo.addKeyListener(lis);
        jtfFecha.addKeyListener(lis);
    }

    public void setListSelectionListener(ListSelectionListener lis) {
        jtDatos.getSelectionModel().addListSelectionListener(lis);
    }

    public void actualizarTabla(){
        jpPpal.remove(getJPDatos());
        jpDatos = null;
        jtDatos = null;
        modTabla = null;
        jspDatos = null;
        jpPpal.add(getJPDatos(), null);
    }
    
    public JButton getJBCambiarPeriodo() {
		if (jbCambiarPeriodo == null) {
			jbCambiarPeriodo = new JButton();
			jbCambiarPeriodo.setBounds(new java.awt.Rectangle(450,20,115,20));
			jbCambiarPeriodo.setText("Cambiar Per�odo");
			jbCambiarPeriodo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbCambiarPeriodo.setInputMap(0, map);
			
		}
		return jbCambiarPeriodo;
	}
    
    public JTextField getJTFPeriodo() {
		if (jtfPeriodo == null) {
			jtfPeriodo = new JTextField();
			jtfPeriodo.setBounds(new java.awt.Rectangle(85,20,70,20));
			jtfPeriodo.setDisabledTextColor(Utils.colorTextoDisabled);
			jtfPeriodo.setEnabled(false);
		}
		return jtfPeriodo;
	}
    
    public JComboBox getJCBMes() {
		if (jcbMes == null) {
			jcbMes = new JComboBox();
			jcbMes.setBounds(new java.awt.Rectangle(255,20,60,20));
			jcbMes.setBackground(new Color(255,255,255));
			jcbMes.setForeground(java.awt.Color.black);
			jcbMes.addItem("01");
			jcbMes.addItem("02");
			jcbMes.addItem("03");
			jcbMes.addItem("04");
			jcbMes.addItem("05");
			jcbMes.addItem("06");
			jcbMes.addItem("07");
			jcbMes.addItem("08");
			jcbMes.addItem("09");
			jcbMes.addItem("10");
			jcbMes.addItem("11");
			jcbMes.addItem("12");
			jcbMes.setSelectedIndex(mesLI-1);
		}
		return jcbMes;
	}
	
	public JTextField getJTFAnio() {
		if (jtfAnio == null) {
			jtfAnio = new JTextField();
			jtfAnio.setBounds(new java.awt.Rectangle(370,20,60,20));
			jtfAnio.setDocument(new LimitadorNroMax(jtfAnio,4));
			jtfAnio.setText(String.valueOf(anioLI));
		}
		return jtfAnio;
	}
	
	public void agregarPeriodoSelec(){
		jlPeriodo = new JLabel("Per�odo:");
		jlPeriodo.setHorizontalAlignment(JLabel.RIGHT);
		jlPeriodo.setBounds(new Rectangle(20,20,60,20));
		jlMes = new JLabel("Mes:");
		jlMes.setBounds(new Rectangle(200,20,50,20));
		jlMes.setHorizontalAlignment(SwingConstants.RIGHT);
		jlAnio = new JLabel("A�o:");
		jlAnio.setBounds(new Rectangle(325,20,40,20));
		jlAnio.setHorizontalAlignment(SwingConstants.RIGHT);
		jpDatos.add(jlPeriodo);
		jpDatos.add(jlMes);
		jpDatos.add(jlAnio);
		jpDatos.add(getJTFPeriodo());
		jpDatos.add(getJCBMes());
		jpDatos.add(getJTFAnio());
		jpDatos.add(getJBCambiarPeriodo());
	}
	
}

