package cliente.GestionarFacturaProveedor;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import persistencia.domain.Producto;
import cliente.LimitadorNroMax;
import cliente.LimitadorPrecio;

import common.Utils;

public class GUICambiarPrecios extends JDialog {
	private static final long serialVersionUID = 1L;
    private JPanel jpPpal = null;		    		private JPanel jpDatos = null; 
    private JButton jbAceptar = null;	    		private JButton jbCancelar = null;
    private JButton jbCalcular = null;
    private JLabel jlNombre = null; 				private JTextField jtfNombre = null;
    private JLabel jlCodigo = null;					private JTextField jtfCodigo  = null;    
	private JLabel jlKilos = null;					private JTextField jcbKilos = null;		
	private JLabel jlProveedor = null;				private JTextField jtfProveedor = null;
	private JLabel jlPrecioEntrada = null;			private JTextField jtfPrecioEntrada = null;
	private JLabel jlPrecioVentaSinIva = null;		private JTextField jtfPrecioVentaSinIva = null;
	private JLabel jlTipoPrecioEntrada = null;		private JComboBox  jcbTipoPrecioEntrada = null;
	private JLabel jlPrecioVentaConIva = null;		private JTextField jtfPrecioVentaConIva = null;
	private JLabel jlMargenGanancia=null;			private JTextField jtfMargenGanancia = null;
 	
	public String[] titServicio ={"Nombre"};
 	private Producto pDTO = null;
 	String nuevoPrecioEntrada;
 	private InputMap map = new InputMap();
 	
    public GUICambiarPrecios(JDialog guiPadre,Producto p,String nuevoPrE) {
    	super(guiPadre);
    	pDTO=p;
    	nuevoPrecioEntrada=nuevoPrE;
        this.setSize(new java.awt.Dimension(360,455));
        this.setTitle("Precios del Producto");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setContentPane(getJPPpal());
        this.setModal(true);
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "pressed");
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "released");
    }
    
    private JPanel getJPPpal() {
        if (jpPpal == null) {
            jpPpal = new JPanel();
            jpPpal.setLayout(null);
            jpPpal.setSize(new java.awt.Dimension(360,455));
            jpPpal.add(getJPDatos(),null);
            jpPpal.add(getJBAceptar(), null);
            jpPpal.add(getJBCancelar(), null);
            jpPpal.setBackground(Utils.colorFondo);
        }
        return jpPpal;
    }
        
    private JPanel getJPDatos() {
        if (jpDatos == null) {
        	jlCodigo = new JLabel();
            jlCodigo.setBounds(new Rectangle(10,30,100,15));
            jlCodigo.setText("C�digo");
            jlCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
            jlNombre = new JLabel("Nombre");
            jlNombre.setBounds(new Rectangle(10,62,100,15));
            jlNombre.setHorizontalAlignment(SwingConstants.RIGHT);
            jlProveedor = new JLabel();
            jlProveedor.setBounds(new Rectangle(10,94,100,15));
            jlProveedor.setText("Proveedor");
            jlProveedor.setHorizontalAlignment(SwingConstants.RIGHT);
            jlKilos = new JLabel();
            jlKilos.setBounds(new Rectangle(10,126,100,15));
            jlKilos.setText("Precio por Kilos?");
            jlKilos.setHorizontalAlignment(SwingConstants.RIGHT);
            jlPrecioEntrada = new JLabel();
            jlPrecioEntrada.setBounds(new Rectangle(10,158,150,15));
            jlPrecioEntrada.setText("Precio Entrada");
            jlPrecioEntrada.setHorizontalAlignment(SwingConstants.RIGHT);
            
            jlTipoPrecioEntrada = new JLabel();
            jlTipoPrecioEntrada.setBounds(new Rectangle(10,190,150,15));
            jlTipoPrecioEntrada.setText("Tipo de Precio Entrada(*)");
            jlTipoPrecioEntrada.setHorizontalAlignment(SwingConstants.RIGHT);
            
            jlMargenGanancia = new JLabel();
			jlMargenGanancia.setBounds(new Rectangle(10,222,150,15));
			jlMargenGanancia.setText("Ganancia en % (*)");
			jlMargenGanancia.setHorizontalAlignment(SwingConstants.RIGHT);
            
            jlPrecioVentaSinIva = new JLabel();
			jlPrecioVentaSinIva.setBounds(new Rectangle(10,286,150,15));
			jlPrecioVentaSinIva.setText("Precio Venta Sin Iva(*)");
			jlPrecioVentaSinIva.setHorizontalAlignment(SwingConstants.RIGHT);
			
			jlPrecioVentaConIva = new JLabel();
			jlPrecioVentaConIva.setBounds(new Rectangle(10,318,150,15));
			jlPrecioVentaConIva.setText("Precio Venta Con Iva(*)");
			jlPrecioVentaConIva.setHorizontalAlignment(SwingConstants.RIGHT);
			jpDatos = new JPanel();
            jpDatos.setLayout(null);
            jpDatos.setBorder(javax.swing.BorderFactory.createTitledBorder(Utils.b, "Datos del Producto", 
                    javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, 
                    javax.swing.border.TitledBorder.DEFAULT_POSITION, 
                    new java.awt.Font("Dialog", java.awt.Font.BOLD, 12), java.awt.Color.black));
            jpDatos.setBounds(new java.awt.Rectangle(15,15,320,350));
            jpDatos.add(jlNombre, null);
            jpDatos.add(jlCodigo, null);
            jpDatos.add(jlKilos, null);
            jpDatos.add(jlPrecioEntrada, null);
            jpDatos.add(jlTipoPrecioEntrada, null);
            jpDatos.add(jlMargenGanancia, null);
            jpDatos.add(jlPrecioVentaSinIva, null);
            jpDatos.add(jlPrecioVentaConIva, null);
            jpDatos.add(jlProveedor, null);
            jpDatos.add(getJTFNombre(), null);
            jpDatos.add(getJTFCodigo(), null);
            jpDatos.add(getJTFPrecioEntrada(), null);
            jpDatos.add(getJCBTipoPrecioEntrada(), null);
            jpDatos.add(getJTFMargenGanancia(), null);
            jpDatos.add(getJBCalcular(), null);
            jpDatos.add(getJTFPrecioVentaSinIva(), null);
            jpDatos.add(getJTFPrecioVentaConIva(), null);
            jpDatos.add(getJCBPrecioKilos(), null);
            jpDatos.add(getJTFProveedor(), null);
            if (pDTO!=null) {
                jtfNombre.setText(pDTO.getNombre());
                jtfCodigo.setText(String.valueOf(pDTO.getCodigo()));
                jtfProveedor.setText(pDTO.getProveedor().getNombre());
                jtfPrecioEntrada.setText(nuevoPrecioEntrada);
                jtfPrecioVentaSinIva.setText(Utils.ordenarDosDecimales(pDTO.getPrecioVentaSinIva()));
                jtfPrecioVentaConIva.setText(Utils.ordenarDosDecimales(pDTO.getPrecioVentaConIva()));
                jtfMargenGanancia.setText(String.valueOf(pDTO.getGanancia()));
                if(pDTO.isPrecioKilos()){
                	jcbKilos.setText("SI");
                }else{
                	jcbKilos.setText("NO"); 
                }
                if(pDTO.isPrecioEntCIva()){
                	jcbTipoPrecioEntrada.setSelectedItem("Con IVA");
                }else{
                	jcbTipoPrecioEntrada.setSelectedItem("SIN IVA"); 
                }
                
            }
            jpDatos.setBackground(Utils.colorPanel);
        }
        return jpDatos;
    }

    public JTextField getJTFNombre() {
        if (jtfNombre == null) {
            jtfNombre = new JTextField();
            jtfNombre.setBounds(new Rectangle(120,62,180,22));
            jtfNombre.setDisabledTextColor(Utils.colorTextoDisabled);
            jtfNombre.setEnabled(false);
        }
        return jtfNombre;
    }
    
    public JTextField getJTFCodigo() {
		if (jtfCodigo==null){
			jtfCodigo=new JTextField();
			jtfCodigo.setBounds(new java.awt.Rectangle(120,30,180,22));
			jtfCodigo.setBackground(new Color(255,255,255));
			jtfCodigo.setDocument(new LimitadorNroMax(jtfCodigo,6));
			jtfCodigo.setDisabledTextColor(Utils.colorTextoDisabled);
			jtfCodigo.setEnabled(false);
		}
		return jtfCodigo;
	}
   
    public JTextField getJCBPrecioKilos() {
        if (jcbKilos == null) {
        	jcbKilos  = new JTextField();
        	jcbKilos.setBounds(new java.awt.Rectangle(120,126,100,22));
        	jcbKilos.setDisabledTextColor(Utils.colorTextoDisabled);
        	jcbKilos.setEnabled(false);
        }
        return jcbKilos;
    }
    
    public JTextField getJTFPrecioEntrada() {
        if (jtfPrecioEntrada == null) {
        	jtfPrecioEntrada = new JTextField();
        	jtfPrecioEntrada.setBounds(new Rectangle(170,158,100,22));
        	jtfPrecioEntrada.setDocument(new LimitadorPrecio(jtfPrecioEntrada));
        	jtfPrecioEntrada.setDisabledTextColor(Utils.colorTextoDisabled);
        	jtfPrecioEntrada.setEnabled(false);
        }
        return jtfPrecioEntrada;
    }
    

    public JComboBox getJCBTipoPrecioEntrada() {
        if (jcbTipoPrecioEntrada == null) {
        	jcbTipoPrecioEntrada  = new JComboBox();
        	jcbTipoPrecioEntrada.setBounds(new java.awt.Rectangle(170,190,100,22));
        	jcbTipoPrecioEntrada.setBackground(new Color(255,255,255));
        	jcbTipoPrecioEntrada.setForeground(java.awt.Color.black);
        	jcbTipoPrecioEntrada.addItem("SIN IVA");
        	jcbTipoPrecioEntrada.addItem("CON IVA");
        }
        return jcbTipoPrecioEntrada;
    }
    
    public JTextField getJTFMargenGanancia() {
		if (jtfMargenGanancia == null) {
			jtfMargenGanancia = new JTextField();
			jtfMargenGanancia.setBounds(new Rectangle(170,222,100,22));
			jtfMargenGanancia.setDocument(new LimitadorNroMax(jtfMargenGanancia,3));
		}
		return jtfMargenGanancia;
	}
    
    public JButton getJBCalcular() {
        if (jbCalcular == null) {
        	jbCalcular = new JButton();
        	jbCalcular.setBounds(new java.awt.Rectangle(20,250,280,25));
        	jbCalcular.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        	jbCalcular.setText("CALCULAR  PRECIO  VENTA");
        	jbCalcular.setName("CalcularPV");
        	jbCalcular.setInputMap(0, map);
        }
        return jbCalcular;
    }
    
    public JTextField getJTFPrecioVentaSinIva() {
        if (jtfPrecioVentaSinIva == null) {
        	jtfPrecioVentaSinIva = new JTextField();
        	jtfPrecioVentaSinIva.setBounds(new Rectangle(170,286,100,22));
        	jtfPrecioVentaSinIva.setDocument(new LimitadorPrecio(jtfPrecioVentaSinIva));
        }
        return jtfPrecioVentaSinIva;
    }
    
    public JTextField getJTFPrecioVentaConIva() {
        if (jtfPrecioVentaConIva == null) {
        	jtfPrecioVentaConIva = new JTextField();
        	jtfPrecioVentaConIva.setBounds(new Rectangle(170,318,100,22));
        	jtfPrecioVentaConIva.setDocument(new LimitadorPrecio(jtfPrecioVentaConIva));
        }
        return jtfPrecioVentaConIva;
    }
    
    public JTextField getJTFProveedor() {
        if (jtfProveedor == null) {
        	jtfProveedor = new JTextField();
        	jtfProveedor.setBounds(new Rectangle(120,94,180,22));
        	jtfProveedor.setDisabledTextColor(Utils.colorTextoDisabled);
        	jtfProveedor.setEnabled(false);
        }
        return jtfProveedor;
    }
    
    public JButton getJBAceptar() {
        if (jbAceptar == null) {
            jbAceptar = new JButton();
            jbAceptar.setBounds(new java.awt.Rectangle(60,385,100,30));
            jbAceptar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            jbAceptar.setText("Aceptar");
            jbAceptar.setName("AceptarCP");
            jbAceptar.setInputMap(0, map);
        }
        return jbAceptar;
    }
    
    public JButton getJBCancelar() {
        if (jbCancelar == null) {
            jbCancelar = new JButton();
            jbCancelar.setBounds(new java.awt.Rectangle(190,385,100,30));
            jbCancelar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            jbCancelar.setText("Cancelar");
            jbCancelar.setName("CancelarCP");
            jbCancelar.setInputMap(0, map);
        }
        return jbCancelar;
    }
    
    public void setActionListenersCPrecios(ActionListener lis) {
        jbAceptar.addActionListener(lis);
        jbCancelar.addActionListener(lis);
        jbCalcular.addActionListener(lis);
    }
    
}