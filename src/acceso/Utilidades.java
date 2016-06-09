package acceso;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gallegomanuel88
 */
public class Utilidades {
    /**
     * Borra los campos de una jTable que le introduzcamos.
     * @param tabla nombre de la jTable que queremos borrar.
     */
    public void limpiarTabla (JTable tabla) {
        try {
            DefaultTableModel modelo=(DefaultTableModel) tabla.getModel();
            int filas=tabla.getRowCount();
            for (int i = 0;filas>i; i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {
            System.out.println("Error al limpiar la tabla.");
        }
    }
    /**
     * Carga una imagen en una jLabel.
     * @param caratula nombre de la jlabel en la que queremos mostrar la imagen.
     * @param titulo nombre de la imagen quq queremos cargar
     * @param ruta ruta donde esta la imagen.
     */
    public void traerImagen (JLabel caratula, String titulo, String ruta){
        ImageIcon imagen = new ImageIcon(this.getClass().getResource(ruta+titulo+".jpg"));
        caratula.setIcon(imagen);
    }
}
