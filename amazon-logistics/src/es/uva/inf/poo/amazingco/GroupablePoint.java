/**
 * La clase abstracta GroupablePoint es una generalizacion de dos tipos de establecimiento,
 * que ofrece el almacenamiento de paquetes para su posterior recogida por parte del cliente. 
 * 
 * El establecimiento mecionado anteriormente puede ser un PackageLocker o un Kiosk. Ambos con car�cter�sticas 
 * comunes, tales un n�mero m�ximo de paquetes que pueden almacenar simult�neamente, as� como una superficie 
 * en metros c�bicos, la cual es limitada. Adem�s, cuentan con un estado para indicar si el establecimiento 
 * correspondiente se encuentra operativo o fuera de servicio. Para poder almacenar un paquete, �ste ha de cumplir 
 * tanto los requisitos de capacidad m�xima de paquetes, como de capacidad m�xima de espacio de almacenaje.
 * 
 * Bajo ninguna circunstancia es posible almacenar y, en consecuencia, entregar, paquetes certificados
 * 
 * Sin embargo, por cuestiones de organizaci�n y escalabilidad de la empresa, existe la posibilidad de ampliar o 
 * reducir la capacidad m�xima de almacenaje, permitiendo as� preponderar objetivos si un punto de recogida es muy
 * demandado y se quiere ampliar la capacidad o, por el contrario, se desperdician una gran cantidad de recursos
 * y hay una necesidad de reorientar la inversi�n.
 * 
 * @author jimenarioja
�*/

package es.uva.inf.poo.amazingco;

import java.time.LocalTime;
import java.util.List;

import es.uva.inf.poo.maps.GPSCoordinate;

public abstract class GroupablePoint extends PickingPoint implements Cloneable {

	private int maxCapacityBox;
	private double maxCapacityCubicMetres;
	private boolean statusOutOfService;
	
	/**
	 * Constructor de GroupablePoint
	 * @param gps, GPSCoordinate, coordenadas gps de GroupablePoint
	 * @param openingHour, LocalTime, horario de apertura
	 * @param closingHour, LocalTime, horario de cierre
	 * @param maxCapacityBox, int, capacidad m�xima de almacenaje de paquetes
	 * @param maxCapacityMetres, double, capacidad m�xima de metros c�bicos disponibles para almacenar paquetes
	 * @throws NullPointerException si gps es null
	 * @throws NullPointerException si el horario de apertura es null
	 * @throws IllegalArgumentException si el horario de apertura es igual o m�s tarde que el de cierre
	 * @throws NullPointerException si closingHour es null
	 * @throws IllegalArgumentException si maxCapacityBox no es positiva
	 * @throws IllegalArgumentException si maxCapacityCubicMetres no es positiva
	 */
	public GroupablePoint(GPSCoordinate gps, LocalTime openingHour, LocalTime closingHour,
			int maxCapacityBox, double maxCapacityCubicMetres) {
		super(gps,openingHour,closingHour);
		notOutOfService();
		setMaxCapacityBox(maxCapacityBox);
		setMaxCapacityCubicMetres(maxCapacityCubicMetres);
	}
	
	
	/**
	 * Constructor copia de GroupablePoint
	 * @param groupablePoint, GroupablePoint
	 * @throws NullPointerException si gps es null
	 * @throws NullPointerException si el horario de apertura es null
	 * @throws IllegalArgumentException si el horario de apertura es igual o m�s tarde que el de cierre
	 * @throws NullPointerException si closingHour es null
	 * @throws IllegalArgumentException si maxCapacityBox no es positiva
	 * @throws IllegalArgumentException si la capacidad en metros c�bicos no es positiva
	 */
	public GroupablePoint(GroupablePoint groupablePoint) {
		this(groupablePoint.getGps(),groupablePoint.getOpeningHour(),
				groupablePoint.getClosingHour(),groupablePoint.getMaxCapacityBox(),
				groupablePoint.getMaxCapacityCubicMetres());
		setId(groupablePoint.getId());
		if(groupablePoint.isOutOfService()) {
			this.outOfService();
		}
	}
	
	
	/**
	 * Modifica la capacidad m�xima de almacenaje de paquetes
	 * @param maxCapacityBox, int, capacidad de almacenaje
	 * @throws IllegalArgumentException si maxCapacityBox no es positiva
	 */
	public void setMaxCapacityBox(int maxCapacityBox) {
		if(maxCapacityBox<=0) {
			throw new IllegalArgumentException("La capacidad ha de ser positiva");
		}
		this.maxCapacityBox=maxCapacityBox;
	}
	
	
	/**
	 * Modifica la capacidad m�xima de metros c�bicos disponibles para almacenamiento de paquetes
	 * @param maxCapacityCubicMetres, double, capacidad m�xima en metros c�bicos
	 * @throws IllegalArgumentException si la capacidad en metros c�bicos no es positiva
	 */
	public void setMaxCapacityCubicMetres(double maxCapacityCubicMetres) {
		if(maxCapacityCubicMetres<=0) {
			throw new IllegalArgumentException("Los metros cubicos han de ser positivos");
		}
		this.maxCapacityCubicMetres=maxCapacityCubicMetres;
	}
	
	
	/**
	 * Consulta la capacidad m�xima de almacenaje de paquetes
	 * @return maxCapacity, int, capacidad m�xima de almacenaje de paquetes (siempre positiva)
	 */
	public int getMaxCapacityBox() {
		return maxCapacityBox;
	}
	
	
	/**
	 * Consulta la capacidad m�xima de almacenaje en metros c�bicos
	 * @return maxCapacityCubicMetres, double, capacidad m�xima de almacenaje en metros c�bicos (siempre positiva)
	 */
	public double getMaxCapacityCubicMetres() {
		return maxCapacityCubicMetres;
	}
	
	
	@Override
	public void outOfService() {
		statusOutOfService=true;
	}
	
	
	/**
	 * Modifica el estado a operativo
	 */
	public void notOutOfService() {
		statusOutOfService=false;
	}
	
	
	@Override
	public boolean isOutOfService() {
		return statusOutOfService;
	}
	
	
	@Override
	public boolean hasCertified() {
		return false;
	}
	
	
	/**
	 * Consulta la cantidad disponible de almacenaje, es decir, la cantidad de almacenaje vac�o
	 * @return int, cantidad disponible de almacenaje (siempre mayor o igual que cero)
	 */
	public int getNumberEmpty() {
		return getMaxCapacityBox()-getNumberOfPackagesStored();
	}
	
	
	@Override
	public boolean isFull() {
		return getMaxCapacityBox()==getNumberOfPackagesStored();
	}
	
	
	/**
	 * Consulta los metros c�bicos ocupados
	 * @return double, metros c�bicos ocupados (siempre mayor o igual que cero)
	 */
	public double getUsedCubicMetres() {
		List<Package> packages=getPackages();
		double cubicMetresUsed=0;
		for(int i=0;i<packages.size();i++) {
			cubicMetresUsed+=packages.get(i).getCubicMetres();
		}
		return cubicMetresUsed;
	}
	
	
	/**
	 * Consulta los metros c�bicos libres
	 * @return double, metros c�bicos libres (siempre mayor o igual que cero)
	 */
	public double getEmptyCubicMetres() {
		return getMaxCapacityCubicMetres()-getUsedCubicMetres();
	}
	
	
	/**
	 * Ampl�a la capacidad de almacenaje de los paquetes
	 * @param gap, int, capacidad a ampliar
	 * @throws IllegalArgumentException si gap es negativo 
	 */
	public abstract void expandCapacity(int gap);
	
	
	/**
	 * Reduce la capacidad m�xima de almacenaje de los paquete
	 * @param gap, int, cantidad a reducir
	 * @throws IllegalArgumentException si gap es negativo
	 * @throws IllegalArgumentException si al reducir la capacidad no es posible almacenar la cantidad de paquetes almacenados actualmente
	 */
	public abstract void reduceCapacity(int gap);	
	

	/**
	 * Obtiene el paquete dado el n�mero de posici�n
	 * @param index, int, n�mero de posici�n
	 * @return Package, paquete buscado
	 * @throws IllegalArgumentException si el n�mero de posici�n es negativo
	 * @throws IllegalArgumentException si el n�mero de posici�n es mayor o igual que el n�mero m�ximo de taquillas
	 */
	public Package getPackage(int index) {
		if(index<0) {
			throw new IllegalArgumentException("El numero de indice ha de ser positivo.");
		}
		if(index>=getMaxCapacityBox()) {
			throw new IllegalArgumentException("El numero de indice ha de ser menor que el n�mero m�ximo de capacidad.");
		}
		List<Package> packages=getPackages();
		return new Package(packages.get(index)); 
	}
	
	
	/**
	 * Consulta el n�mero de posici�n en la que est� un paquete a partir de su id
	 * @param id, string, que es el id del paquete
	 * @return int, posici�n del locker que almacena el paquete buscado
	 * @throws NullPointerException si el id es null
	 * @throws IllegalArgumentException si el id es una cadena vac�a
	 * @throws IllegalArgumentException si no encuentra ning�n package con ese id
	 */
	public int getIndexPackage(String id) {
		if(id==null) {
			throw new NullPointerException("El id no puede ser null");
		}
		if(id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser una cadena vacia");
		}
		List<Package> packages=getPackages();
		for(int i=0;i<packages.size();i++) {
			if(packages.get(i).getId().equals(id)) {
				return i;
			}
		}
		throw new IllegalArgumentException("No hay ning�n paquete que tenga el id indicado");
	}
	
	
	@Override
	public String toString() {
		String s="";
		
		s+="Capacidad maxima de paquetes: "+getMaxCapacityBox();
		s+="Capacidad maxima en metros cubicos: "+getMaxCapacityCubicMetres();
		s+="Status out of service: "+isOutOfService();
		s+="Suitable for COD: "+hasCOD();
		s+="Suitable for certified: "+hasCertified();
		return s;
	}

	
	@Override
	public abstract GroupablePoint clone();
}