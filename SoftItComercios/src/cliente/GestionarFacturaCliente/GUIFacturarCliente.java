package cliente.GestionarFacturaCliente;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

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
import cliente.LimitadorPrecio;
import cliente.ModeloTabla;

import com.toedter.calendar.JDateChooser;
import common.Utils;

public class GUIFacturarCliente extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel jpPpal = null;				private JPanel jpDatosProd = null;
    private JPanel jpDatosFactura = null;		private JPanel jpDatosItems=null;
   	private JButton jbAgregarProd = null;  	 	private JButton jbBuscarC=null;
    private JButton jbEliminarProd=null;		private JButton jbConfirmarFact=null;
    private JLabel jlSelecProd = null;			private JLabel jlFechaFactura=null;
    private JLabel jlCodigo = null;	     		private JLabel jlImporte = null;
    private JLabel jlBusqueda = null;			private JLabel jlNroFactura = null;
    private JLabel jlNombreC=null;				private JLabel jlCuit = null;
    private JLabel jlCantidad = null;			private JLabel jlITotal = null;
    private JLabel jlKilos = null;				private JLabel jlImporteIva = null;
    private JLabel jlIvaIncl = null;			private JLabel jlDescuento = null;
    private JTextField jtfBusqueda = null;
    private JTextField jtfCodigo = null;     	private JTextField jtfImporte = null;
    private JTextField jtfCantidad = null;   	private JTextField jtfITotal = null;
    private JTextField jtfNombreC=null;			private JTextField jtCuit = null;
    private JTextField jtfIngrBrutos;			private JTextField jtfRemitoNro;
    private JTextField jtfDescuento = null;
    private JTextField jtfImporteIva = null;
    private JComboBox jcbCodigo = null;
    private JDateChooser jDataCFecha = null;
    private JScrollPane jspDatosInsc=null;
    public final String[] titulos ={"C�digo","Cant.","Kg.","Producto","Precio Unit.","Descuento %","Precio Total"};
    public Object[][] datos;
    public JTable tabla;					
    public ModeloTabla modTabla = null;
    public Long nroFactura;
    public Vector codProd= new Vector();
	private JTextField jcbTipoIva;
	private JComboBox jcbCondVta;
	private JLabel jlCondVta=null;
	private JLabel jlIva=null;
	private JLabel jlRemNro;
	private JLabel jlIngrBr;
	private JTextField jtfKilos;
	private String tipoF;
	private InputMap map = new InputMap();
    
    public GUIFacturarCliente(String tipo,JFrame guiPadre) {
    	super(guiPadre);
    	tipoF=tipo;
    	this.setSize(new java.awt.Dimension(740,540));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Facturacion Cliente - Factura Tipo "+tipoF);
        this.setContentPane(getJPPpal());
        this.setModal(true);
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "pressed");
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "released");
    }

    public JPanel getJPPpal() {
        if (jpPpal == null) {
            jpPpal = new JPanel();
            jpPpal.setLayout(null);
            jpPpal.add(getJPDatosProducto(), null);
            jpPpal.add(getJPDatosFactura(), null);
            Object[] temp  = {" "," "," "," "," "," "," "};
            datos = new Object[1][titulos.length];
            datos[0]=temp;
            jpPpal.add(getJPDatosItems(), null);
            jpPpal.setBackground(Utils.colorFondo);
        }
        return jpPpal;
    }

    private JPanel getJPDatosProducto() {
        if (jpDatosProd == null) {
        	jpDatosProd = new JPanel();
        	jpDatosProd.setLayout(null);
        	jpDatosProd.setBounds(new java.awt.Rectangle(8,371,716,124));
        	jpDatosProd.setBorder(javax.swing.BorderFactory.createTitledBorder(Utils.b, "Ingreso de Productos",
                    javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                    javax.swing.border.TitledBorder.DEFAULT_POSITION, null, null));
        	jpDatosProd.setBackground(Utils.colorPanel);
        	jlBusqueda = new JLabel();
            jlBusqueda.setBounds(new Rectangle(15,28,200,22));
            jlBusqueda.setText("Buscar Producto:");
        	jlSelecProd = new JLabel();
        	jlSelecProd.setBounds(new Rectangle(225,28,150,22));
        	jlSelecProd.setText("Seleccione Producto ");
        	jlCodigo = new JLabel();
            jlCodigo.setBounds(new Rectangle(15,58,200,22));
            jlCodigo.setText("C�digo_Producto:");
            jlImporte = new JLabel();
            jlImporte.setBounds(new java.awt.Rectangle(550,58,70,22));
            jlImporte.setText("Importe:");
            jlIvaIncl = new JLabel();
            jlIvaIncl.setBounds(new java.awt.Rectangle(620,58,70,22));
            jlIvaIncl.setText("Iva Incluido");
            JLabel jlAgregar = new JLabel();
            jlAgregar.setBounds(new java.awt.Rectangle(15,84,685,32));
            jlAgregar.setBorder(javax.swing.BorderFactory.createTitledBorder(Utils.b, "",
                    javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                    javax.swing.border.TitledBorder.DEFAULT_POSITION, null, null));
            jlCantidad = new JLabel();
            jlCantidad.setBounds(new java.awt.Rectangle(30,88,100,22));
            jlCantidad.setText("Ingrese Cantidad:");
            jlKilos = new JLabel();
            jlKilos.setBounds(new Rectangle(225,88,90,22));
            jlKilos.setText("Ingrese Kg.:");
            jlDescuento = new JLabel();
            jlDescuento.setBounds(new Rectangle(395,88,180,22));
            jlDescuento.setText("Ingrese Descuento:           %");
            
            jpDatosProd.add(jlSelecProd, null);
            jpDatosProd.add(jlCodigo, null);
            jpDatosProd.add(jlBusqueda, null);
            jpDatosProd.add(jlImporte, null);
            jpDatosProd.add(jlCantidad, null);
            jpDatosProd.add(jlKilos, null);
            jpDatosProd.add(jlDescuento, null);
            jpDatosProd.add(jlAgregar, null);
            jpDatosProd.add(getJTFCodigo(), null);
            jpDatosProd.add(getJTFImporte(), null);
            jpDatosProd.add(getJTFBusqueda(), null);
            jpDatosProd.add(getJTFCantidad(), null);
            jpDatosProd.add(getJTFKilos(), null);
            jpDatosProd.add(getJTFDescuento(), null);
            jpDatosProd.add(getJBAgregarProd(), null);
            if(tipoF.compareTo("B")==0)
            	jpDatosProd.add(jlIvaIncl, null);
        }
        return jpDatosProd;
    }
    
    private JPanel getJPDatosFactura() {
        if (jpDatosFactura == null) {
        	jpDatosFactura = new JPanel();
        	jpDatosFactura.setLayout(null);
        	jpDatosFactura.setBorder(javax.swing.BorderFactory.createTitledBorder(Utils.b, "Datos de Factura Tipo "+tipoF,
                    javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                    javax.swing.border.TitledBorder.DEFAULT_POSITION,
                    new java.awt.Font("Dialog", java.awt.Font.BOLD, 16), java.awt.Color.black));
        	jpDatosFactura.setBounds(new java.awt.Rectangle(8,15,716,117));
        	jpDatosFactura.setBackground(Utils.colorPanel);
        	jlNombreC = new JLabel();
            jlNombreC.setBounds(new Rectangle(230,28,60,22));
            jlNombreC.setHorizontalAlignment(SwingConstants.RIGHT);
            jlNombreC.setText("Nombre:");
            jlCuit = new JLabel();
        	jlCuit.setBounds(new java.awt.Rectangle(510,28,30,22));
        	jlCuit.setHorizontalAlignment(SwingConstants.RIGHT);
        	jlCuit.setText("Cuit:");
        	jlFechaFactura = new JLabel();
            jlFechaFactura.setBounds(new Rectangle(10,86,40,22));
            jlFechaFactura.setHorizontalAlignment(SwingConstants.RIGHT);
            jlFechaFactura.setText("Fecha:");
            jlIva = new JLabel();
            jlIva.setBounds(new Rectangle(230,57,60,22));
            jlIva.setHorizontalAlignment(SwingConstants.RIGHT);
            jlIva.setText("Iva:");
            jlCondVta = new JLabel();
            jlCondVta.setBounds(new Rectangle(230,86,120,22));
            jlCondVta.setHorizontalAlignment(SwingConstants.RIGHT);
            jlCondVta.setText("Cond de Venta:");
            jlRemNro = new JLabel();
            jlRemNro.setBounds(new Rectangle(450,86,90,22));
            jlRemNro.setHorizontalAlignment(SwingConstants.RIGHT);
            jlRemNro.setText("Remito N�:");
            jlIngrBr = new JLabel();
            jlIngrBr.setBounds(new Rectangle(450,57,90,22));
            jlIngrBr.setHorizontalAlignment(SwingConstants.RIGHT);
            jlIngrBr.setText("Ing. Brutos:");
            jlNroFactura = new JLabel();
            jlNroFactura.setBounds(new Rectangle(15,60,190,22));
            jpDatosFactura.add(jlNombreC, null);
            jpDatosFactura.add(jlCuit, null);
            jpDatosFactura.add(jlFechaFactura, null);
            jpDatosFactura.add(jlIva, null);
            jpDatosFactura.add(jlCondVta, null);
            jpDatosFactura.add(jlRemNro, null);
            jpDatosFactura.add(jlIngrBr, null);
            jpDatosFactura.add(getJBBuscarC(), null);
            jpDatosFactura.add(getJTFNombreC(), null);
            jpDatosFactura.add(getJtCuit(), null);
            jpDatosFactura.add(getJDateChooserFecha(),null);
            jpDatosFactura.add(getJCCondVta(),null);
            jpDatosFactura.add(getJCTipoIva(),null);
            jpDatosFactura.add(getJTFRemitoNro(),null);
            jpDatosFactura.add(getJTFIngrBrutos(),null);
        }
        return jpDatosFactura;
    }
    
    public JDateChooser getJDateChooserFecha() {
		if (jDataCFecha == null) {
			jDataCFecha = new JDateChooser("dd - MMMMM - yyyy",false);
			jDataCFecha.setBounds(new java.awt.Rectangle(60,86,170,22));
		}
		return jDataCFecha;
	}
    
    public JButton getJBBuscarC() {
        if (jbBuscarC == null) {
            jbBuscarC = new JButton();
            jbBuscarC.setText("Seleccione el Cliente");
            jbBuscarC.setName("BuscarC");
            jbBuscarC.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            jbBuscarC.setBounds(new java.awt.Rectangle(30,28,150,22));
            jbBuscarC.setInputMap(0, map);
        }
        return jbBuscarC;
    }

    public JButton getJBAgregarProd() {
        if (jbAgregarProd == null) {
        	jbAgregarProd = new JButton();
        	jbAgregarProd.setText("Agregar Producto");
        	jbAgregarProd.setName("AgregarProd");
        	jbAgregarProd.setBounds(new java.awt.Rectangle(565,88,120,25));
        	jbAgregarProd.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        	jbAgregarProd.setEnabled(false);
        	jbAgregarProd.setInputMap(0, map);
        }
        return jbAgregarProd;
    }
    
    public JButton getJBEliminarProd() {
        if (jbEliminarProd == null) {
        	jbEliminarProd = new JButton();
        	jbEliminarProd.setText("Eliminar Producto de Factura");
        	jbEliminarProd.setName("EliminarP");
        	jbEliminarProd.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        	jbEliminarProd.setBounds(new java.awt.Rectangle(30,175,200,22));
        	jbEliminarProd.setEnabled(false);
        	jbEliminarProd.setInputMap(0, map);
        }
        return jbEliminarProd;
    }
    
    public JButton getJBConfirmaFact() {
        if (jbConfirmarFact == null) {
        	jbConfirmarFact = new JButton();
        	jbConfirmarFact.setText("CONFIRMAR FACTURA");
        	jbConfirmarFact.setName("ConfirmarFact");
        	jbConfirmarFact.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        	jbConfirmarFact.setBounds(new java.awt.Rectangle(280,183,150,30));
        	jbConfirmarFact.setEnabled(false);
        	jbConfirmarFact.setInputMap(0, map);
        }
        return jbConfirmarFact;
    }
    
    public JTextField getJTFCodigo() {
        if (jtfCodigo == null) {
        	jtfCodigo = new JTextField();
        	jtfCodigo.setBounds(new Rectangle(120,58,400,22));
        	jtfCodigo.setDisabledTextColor(Utils.colorTextoDisabled);
           	jtfCodigo.setEnabled(false);
        }
        return jtfCodigo;
    }
    
    public JTextField getJTFBusqueda() {
        if (jtfBusqueda == null) {
        	jtfBusqueda = new JTextField();
        	jtfBusqueda.setBounds(new Rectangle(120,28,90,22));
        }
        return jtfBusqueda;
    }
    
    public JComboBox getJCBCodigo() {
        if (jcbCodigo == null) {
        	jcbCodigo = new JComboBox();
        	jcbCodigo.setBounds(new Rectangle(350,28,350,22));
        	jcbCodigo.removeAllItems();
        	for(int i=0;i<codProd.size();i++){
				String codPr=(String)codProd.elementAt(i);
				jcbCodigo.addItem(codPr);
			}
        	jcbCodigo.setBackground(new Color(255,255,255));
        	jcbCodigo.setForeground(java.awt.Color.black);
	   }
        return jcbCodigo;
    }
    
    public void mostrarCombo(){
    	jpDatosProd.remove(getJCBCodigo());
	 	getJCBCodigo();
	 	jcbCodigo = new JComboBox();
	 	jcbCodigo.setBounds(new Rectangle(350,28,350,22));
	 	jcbCodigo.setBackground(new Color(255,255,255));
    	jcbCodigo.setForeground(java.awt.Color.black);
		for(int i=0;i<codProd.size();i++){
			String codPr=(String)codProd.elementAt(i);
			jcbCodigo.addItem(codPr);
		}
		jpDatosProd.add(getJCBCodigo(), null);
		this.repaint();
    	repaint();
    }
    
    public void ocultarCombo(){
    	jpDatosProd.remove(getJCBCodigo());
	 	this.repaint();
    	repaint();
    }
    
    public JTextField getJTFImporte() {
        if (jtfImporte == null) {
        	jtfImporte = new JTextField();
        	jtfImporte.setBounds(new Rectangle(610,58,90,22));
        	jtfImporte.setDisabledTextColor(Utils.colorTextoDisabled);
        	jtfImporte.setEnabled(false);
        }
        return jtfImporte;
    }
    
    public JTextField getJTFImporteIva() {
        if (jtfImporteIva == null) {
        	jtfImporteIva = new JTextField();
        	jtfImporteIva.setBounds(new Rectangle(600,175,100,22));
        	jtfImporteIva.setDisabledTextColor(Utils.colorTextoDisabled);
        	jtfImporteIva.setEnabled(false);
        }
        return jtfImporteIva;
    }
    
    public JTextField getJTFITotal() {
        if (jtfITotal == null) {
        	jtfITotal = new JTextField();
        	jtfITotal.setBounds(new Rectangle(600,200,100,22));
        	jtfITotal.setDisabledTextColor(Utils.colorTextoDisabled);
        	jtfITotal.setEnabled(false);
        }
        return jtfITotal;
    }
   
    public JTextField getJTFCantidad() {
        if (jtfCantidad == null) {
        	jtfCantidad = new JTextField();
        	jtfCantidad.setBounds(new Rectangle(135,88,60,22));
        	jtfCantidad.setDocument(new LimitadorNroMax(jtfCantidad,6));
        	jtfCantidad.setText("1");
        }
        return jtfCantidad;
    }
    
    public JTextField getJTFKilos() {
        if (jtfKilos == null) {
        	jtfKilos = new JTextField();
        	jtfKilos.setBounds(new Rectangle(300,88,60,22));
        	jtfKilos.setDocument(new LimitadorPrecio(jtfKilos));
        	jtfKilos.setText("");
        }
        return jtfKilos;
    }
    
    public JTextField getJTFDescuento() {
        if (jtfDescuento == null) {
        	jtfDescuento = new JTextField();
        	jtfDescuento.setBounds(new Rectangle(510,88,25,22));
        	jtfDescuento.setDocument(new LimitadorNroMax(jtfDescuento,2));

        }
        return jtfDescuento;
    }
    
    public JTextField getJTFNombreC() {
        if (jtfNombreC == null) {
            jtfNombreC = new JTextField();
            jtfNombreC.setBounds(new Rectangle(300,28,150,22));
            jtfNombreC.setDisabledTextColor(Utils.colorTextoDisabled);
            jtfNombreC.setEnabled(false);
        }
        return jtfNombreC;
    }
    	
	public JTextField getJtCuit() {
		if (jtCuit == null) {
			jtCuit = new JTextField();
			jtCuit.setBounds(new java.awt.Rectangle(550,28,150,22));
			jtCuit.setDisabledTextColor(Utils.colorTextoDisabled);
			jtCuit.setEnabled(false);
		}
		return jtCuit;
	}
	
	public JTextField getJTFRemitoNro() {
        if (jtfRemitoNro == null) {
        	jtfRemitoNro = new JTextField();
        	jtfRemitoNro.setBounds(new Rectangle(550,86,150,22));
        	jtfRemitoNro.setDisabledTextColor(Utils.colorTextoDisabled);
        	jtfRemitoNro.setEnabled(false);
        }
        return jtfRemitoNro;
    }
	
	public JTextField getJTFIngrBrutos() {
        if (jtfIngrBrutos == null) {
        	jtfIngrBrutos = new JTextField();
        	jtfIngrBrutos.setBounds(new Rectangle(550,57,150,22));
        	jtfIngrBrutos.setEnabled(false);
        	jtfIngrBrutos.setDisabledTextColor(Utils.colorTextoDisabled);
        }
        return jtfIngrBrutos;
    }
	
	public JTextField getJCTipoIva() {
        if (jcbTipoIva == null) {
        	jcbTipoIva = new JTextField();
        	jcbTipoIva.setBounds(new Rectangle(300,57,150,22));
        	jcbTipoIva.setEnabled(false);
        	jcbTipoIva.setDisabledTextColor(Utils.colorTextoDisabled);
        	
	   }
        return jcbTipoIva;
    }
	
	public JComboBox getJCCondVta() {
        if (jcbCondVta == null) {
        	jcbCondVta = new JComboBox();
        	jcbCondVta.setBounds(new Rectangle(360,86,90,22));
        	jcbCondVta.addItem("CONTADO");
        	jcbCondVta.addItem("CTA. CTE.");
        	jcbCondVta.setBackground(new Color(255,255,255));
        	jcbCondVta.setForeground(java.awt.Color.black);
	   }
        return jcbCondVta;
    }
	
	private JPanel getJPDatosItems() {
		if (jpDatosItems == null) {
			jpDatosItems = new JPanel();
			jpDatosItems.setLayout(null);
			jpDatosItems.setBounds(new Rectangle(8,137,716,229));
			jpDatosItems.setBorder(javax.swing.BorderFactory.createTitledBorder(Utils.b, "Listado de Productos Comprados",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION, null, null));
			jlITotal = new JLabel();
			jlITotal.setBounds(new java.awt.Rectangle(490,200,100,15));
			jlITotal.setHorizontalAlignment(SwingConstants.RIGHT);
			jlITotal.setText("Importe Total:");
			jlImporteIva = new JLabel();
			jlImporteIva.setBounds(new java.awt.Rectangle(490,175,100,15));
			jlImporteIva.setHorizontalAlignment(SwingConstants.RIGHT);
			jpDatosItems.add(jlITotal, null);
			jpDatosItems.add(getJTFITotal(), null);
			jpDatosItems.add(getJSPDatosI(), null);
			if(tipoF.compareTo("A")==0){
				jlImporteIva.setText("% Iva:");
				jpDatosItems.add(jlImporteIva, null);
				jpDatosItems.add(getJTFImporteIva(), null);
			}
			jpDatosItems.setBackground(Utils.colorPanel);
			jpDatosItems.add(getJBEliminarProd(), null);
			jpDatosItems.add(getJBConfirmaFact(), null);
		}
		return jpDatosItems;
	}
	
	private JScrollPane getJSPDatosI() {
		if (jspDatosInsc == null) {
			jspDatosInsc = new JScrollPane();
			jspDatosInsc.setBounds(new Rectangle(10,20,690,150));
			jspDatosInsc.setViewportView(getJTDatosI());
		}
		return jspDatosInsc;
	}
	
	public JTable getJTDatosI() {
		if (tabla == null) {
			modTabla = new ModeloTabla(titulos, datos);
			tabla = new JTable(modTabla);
			TableColumn columna0 = tabla.getColumn("C�digo");
			columna0.setPreferredWidth(80);
			columna0.setCellRenderer(Utils.alinearDerecha());
            TableColumn columna1 = tabla.getColumn("Cant.");
			columna1.setPreferredWidth(60);
            columna1.setMaxWidth(60); 
            columna1.setCellRenderer(Utils.alinearDerecha());
            TableColumn columna2 = tabla.getColumn("Kg.");
            columna2.setPreferredWidth(60); 
            columna2.setMaxWidth(60);
            columna2.setCellRenderer(Utils.alinearDerecha());
            TableColumn columna3 = tabla.getColumn("Precio Unit.");
            columna3.setPreferredWidth(80);
            columna3.setCellRenderer(Utils.alinearDerecha());
            TableColumn columna4 = tabla.getColumn("Descuento %");
            columna4.setPreferredWidth(90);
            columna4.setMaxWidth(90);
            columna4.setCellRenderer(Utils.alinearDerecha());
            TableColumn columna5 = tabla.getColumn("Precio Total");
            columna5.setPreferredWidth(80);
            columna5.setCellRenderer(Utils.alinearDerecha());
		}
		return tabla;
	}
	 
	public void actualizarTabla(){
		jpPpal.remove(getJPDatosItems());
		jpDatosItems = null;
		tabla = null;
		modTabla = null;
		jspDatosInsc = null;
		jpPpal.add(getJPDatosItems(), null);
	}
	 
	public void actualizarNroFactura(){
		jlNroFactura.setText("Nro Factura: "+Utils.nroFact(nroFactura));
		jpDatosFactura.add(jlNroFactura, null);
		
	}
	
	
	public void setListSelectionListener(ListSelectionListener lis) {
		tabla.getSelectionModel().addListSelectionListener(lis);
	}
	
	public void setActionListeners(ActionListener lis) {
		jbAgregarProd.addActionListener(lis);
		jbBuscarC.addActionListener(lis);
		jbEliminarProd.addActionListener(lis);
		jbConfirmarFact.addActionListener(lis);
	}
	
	public void setActionListeners2(ActionListener lis) {
		jcbCodigo.addActionListener(lis);
	}
	
	public void setKeyListeners2(KeyListener lis) {
		jtfBusqueda.addKeyListener(lis);
	}
	
}

