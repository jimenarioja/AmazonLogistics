/**
 * La clase Locker representa una taquilla con dimensiones que tiene la capacidad de almacenar un
 * paquete cuyo tamaño sea inferior al de la taquilla.
 * 
 * La taquilla tiene un identificador único dentro de un establecimiento
 * 
 * @author jimenarioja
 */

package es.uva.inf.poo.amazingco;

public class Locker implements Cloneable{

	private Package storedPackage;
	private int numLocker;
	private double width;
	private double length;
	private double height;
	
	/**
	 * Constructor de taquilla sabiendo su número
	 * @param numLocker, int, número de taquilla
	 * @throws IllegalArgumentException si numLocker es negativo
	 */
	public Locker(int numLocker) {
		storedPackage=null;
		setNumLocker(numLocker);
		width=0.5;
		length=0.5;
		height=0.5;
	}
	
	
	/**
	 * Constructor de taquilla sabiendo su número y el paquete que va a almacenar
	 * @param numLocker, int, número de taquilla
	 * @param storedPackage, Package, paquete a almacenar
	 * @throws IllegalArgumentException si numLocker es negativo
	 */
	public Locker(int numLocker, Package storedPackage) {
		setStoredPackage(storedPackage);
		setNumLocker(numLocker);
		width=storedPackage.getWidth()+10;
		length=storedPackage.getLength()+10;
		height=storedPackage.getHeight()+10;
	}
	
	
	/**
	 * Constructor de taquilla
	 * @param storedPackage, Package en su interior
	 * @param numLocker, int, número de taquilla
	 * @param width, double, ancho de taquilla
	 * @param length, double, largo de taquilla
	 * @param height, double, alto de taquilla
	 * @throws IllegalArgumentException si numLocker es negativo
	 * @throws IllegalArgumentException si width no es positivo
	 * @throws IllegalArgumentException si length no es positivo
	 * @throws IllegalArgumentException si height no es positivo
	 */
	public Locker(Package storedPackage, int numLocker,double width, double length, double height) {
		if(storedPackage != null) {
			setStoredPackage(storedPackage);
		}
		setNumLocker(numLocker);
		setWidth(width);
		setLength(length);
		setHeight(height);
	}
	
	
	/**
	 * Constructor de taquilla
	 * @param numLocker, int, número de taquilla
	 * @param width, double, ancho de taquilla
	 * @param length, double, largo de taquilla
	 * @param height, double, alto de taquilla
	 * @throws IllegalArgumentException si numLocker es negativo
	 * @throws IllegalArgumentException si width no es positivo
	 * @throws IllegalArgumentException si length no es positivo
	 * @throws IllegalArgumentException si height no es positivo
	 */
	public Locker(int numLocker,double width, double length, double height) {
		setNumLocker(numLocker);
		setWidth(width);
		setLength(length);
		setHeight(height);
	}
	
	
	/**
	 * Constructor copia de taquilla
	 * @param locker, taquilla a copiar
	 * @throws IllegalArgumentException si numLocker es negativo
	 * @throws IllegalArgumentException si width no es positivo
	 * @throws IllegalArgumentException si length no es positivo
	 * @throws IllegalArgumentException si height no es positivo
	 */
	public Locker(Locker locker) {
		this(locker.getStoredPackage(),locker.getNumLocker(),locker.getWidth(), locker.getLength(),locker.getHeight());
	}
	
	
	/**
	 * Modifica el paquete del interior de la taquilla
	 * @param storedPackage, Package
	 * @throws NullPointerException si storedPackage es null
	 * @throws IllegalArgumentException si storedPackage ya ha sido recogido
	 * @throws IllegalArgumentExcpetion si storedPackage ya ha sido devuelto
	 */
	public void setStoredPackage(Package storedPackage) {
		if(storedPackage == null) {
			throw new NullPointerException("El paquete no puede ser null");
		}
		if(storedPackage.getPickUpStatus()){ 
			throw new IllegalArgumentException("El paquete que se quiere guardar ya ha sido recogido");
		}
		if(storedPackage.getReturnStatus()) {
			throw new IllegalArgumentException("El paquete que se quiere guardar ya ha sido devuelto");
		}
		this.storedPackage=new Package(storedPackage);
	}
	
	
	/**
	 * Modifica el número de taquilla
	 * @param numLocker, int, número de taquilla
	 * @throws IllegalArgumentException si numLocker es negativo
	 */
	public void setNumLocker(int numLocker) {
		if(numLocker<0) {
			throw new IllegalArgumentException("El número de taquilla no puede ser negativo");
		}
		this.numLocker=numLocker;
	}
	
	
	/**
	 * Modifica el ancho de taquilla
	 * @param width, double, ancho de taquilla
	 * @throws IllegalArgumentException si width no es positivo
	 */
	public void setWidth(double width) {
		if(width<=0) {
			throw new IllegalArgumentException("El ancho de la taquilla ha de ser positivo");
		}
		this.width=width;
	}
	
	
	/**
	 * Modifica el largo de taquilla
	 * @param length, double, largo de taquilla
	 * @throws IllegalArgumentException si length no es positivo
	 */
	public void setLength(double length) {
		if(length<=0) {
			throw new IllegalArgumentException("El largo de la taquilla ha de ser positivo");
		}
		this.length=length;		
	}
	
	
	/**
	 * Modifica el alto de taquilla
	 * @param height, double, alto de taquilla
	 * @throws IllegalArgumentException si height no es positivo
	 */
	public void setHeight(double height) {
		if(height<=0) {
			throw new IllegalArgumentException("El alto de la taquilla ha de ser positivo");
		}
		this.height=height;
	}
	
	
	/**
	 * Consulta el paquete que se encuentra en el interior de la taquilla
	 * @return storedPackage, Package
	 */
	public Package getStoredPackage() {
		if(storedPackage == null) {
			return null;
		}
		return new Package(storedPackage);
	}
	
	
	/**
	 * Consulta el número de taquilla
	 * @return numLocker, int
	 */
	public int getNumLocker() {
		return numLocker;
	}
	
	
	/**
	 * Consulta el ancho de taquilla
	 * @return width, double positivo
	 */
	public double getWidth() {
		return width;
	}
	
	
	/**
	 * Consulta el largo de taquilla
	 * @return length, double positivo
	 */
	public double getLength() {
		return length;
	}
	
	
	/**
	 * Consulta el alto de taquilla
	 * @return height, double positivo
	 */
	public double getHeight() {
		return height;
	}
	
	
	/**
	 * Consulta los metros cúbicos de la taquilla
	 * @return double, siempre positivo
	 */
	public double getCubicMetres() {
		return getWidth()*getLength()*getHeight();
	}
	
	
	@Override
	public String toString() {
		String s="";
		
		s+="Package: "+getStoredPackage();
		s+="Number of locker: "+getNumLocker();
		s+="Width: "+getWidth();
		s+="Length: "+getLength();
		s+="Height: "+getHeight();
		
		return s;
	}
	
	
	@Override
	public boolean equals(Object o) {
		if(this==o) {
			return true;
		}
		
		Locker locker = (Locker)o;
		return numLocker==locker.getNumLocker();
	}
	
	
	@Override
	public Locker clone() {
		return new Locker(this);
	}
}
