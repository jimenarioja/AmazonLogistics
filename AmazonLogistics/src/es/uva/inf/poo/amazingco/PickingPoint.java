/**
 * La clase abstracta PickingPoint es una generalizacion de cualquier establecimiento que ofrece tanto almacenamiento
 * como entrega de paquetes. 
 * 
 * El establecimiento mecionado anteriormente puede ser un GroupablePoint (PackageLocker o Kiosk), un PostOffice
 * o un PickingPointHub. Todos con carácterísticas comunes, tales como unas coordenadas gps y un horario, en el cual 
 * la hora de apertura ha de ser antes que la hora de cierre. Además, cuentan con un estado para indicar si el 
 * establecimiento correspondiente se encuentra operativo o fuera de servicio.  
 * 
 * Para poder almacenar un paquete, el establecimiento no puede estar lleno.
 * 
 * Para que un cliente pueda recoger un paquete ha de acudir a una hora en la que el establecimiento esté abierto,
 * hacerlo en una fecha no posterior a la fecha límite de recogida y proporcionar el identificador del paquete.
 * 
 * @author jimenarioja
 */

package es.uva.inf.poo.amazingco;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import es.uva.inf.poo.maps.GPSCoordinate;

public abstract class PickingPoint implements Cloneable{
	
	private static int nextId = 0;

	private int id;
	private GPSCoordinate gps;
	private LocalTime openingHour;
	private LocalTime closingHour;
	
	/**
	 * Constructor de PickingPoint
	 * @param id, int, identificador del punto de recogida
	 * @param gps, GPSCoordinate, localización del punto de recogida
	 * @param openingHour, LocalTime, horario de apertura
	 * @param closingHour, LocalTime, horario de cierre
	 * @throws NullPointerException si gps es null
	 * @throws NullPointerException si el horario de apertura es null
	 * @throws IllegalArgumentException si el horario de apertura es igual o más tarde que el de cierre
	 * @throws NullPointerException si closingHour es null
	 */
	public PickingPoint(GPSCoordinate gps, LocalTime openingHour, LocalTime closingHour) {
		id=nextId();
		setGps(gps);
		setOpeningHour(openingHour);
		setClosingHour(closingHour); 
	}
	
	
	/**
	 * Constructor copia de PickingPoint
	 * @param pickingPoint, PickingPoint
	 * @throws NullPointerException si gps es null
	 * @throws NullPointerException si el horario de apertura es null
	 * @throws IllegalArgumentException si el horario de apertura es igual o más tarde que el de cierre
	 * @throws NullPointerException si closingHour es null
	 */
	public PickingPoint(PickingPoint pickingPoint) {
		this(pickingPoint.getGps(),pickingPoint.getOpeningHour(),
				pickingPoint.getClosingHour());
		
		setId(pickingPoint.getId());
		if(pickingPoint.isOutOfService()) {
			this.outOfService();
		}
	}
	
	
	/**
	 * Modifica el id del punto de recogida
	 * @param id, int
	 */
	public void setId(int id) {
		this.id=id;
	}
	
	
	/**
	 * Modifica las coordenas gps
	 * @param gps, GPSCoordinate
	 * @throws NullPointerException si gps es null
	 */
	public void setGps(GPSCoordinate gps) {
		if(gps==null) {
			throw new NullPointerException("Las coordenadas no pueden ser null");
		}
		this.gps=new GPSCoordinate(gps.getLatitudeGD(),gps.getLongitudeGD());
	}
	
	
	/**
	 * Modifica la hora de apertura del punto de recogida
	 * @param openingHour, LocalTime, hora de apertura
	 * @throws NullPointerException si el horario de apertura es null
	 * @throws IllegalArgumentException si el horario de apertura es igual o más tarde que el de cierre
	 */
	public void setOpeningHour(LocalTime openingHour) {
		if(openingHour==null) {
			throw new NullPointerException("El horario de apertura no puede ser null");
		}
		if(getClosingHour()!=null && !openingHour.isBefore(getClosingHour())) {
			throw new IllegalArgumentException("El horario de apertura no puede ser despues que el de cierre");
		}
		this.openingHour=LocalTime.of(openingHour.getHour(), openingHour.getMinute());
	}
	
	
	/**
	 * Modifica la hora de cierre del punto de recogida
	 * @param closingHour, LocalTime, hora de cierre
	 * @throws NullPointerException si closingHour es null
	 * @throws IllegalArgumentException si closingHour es igual o antes que openingHour
	 */
	public void setClosingHour(LocalTime closingHour) {
		if(closingHour==null) {
			throw new NullPointerException("El horario de cierre no puede ser null");
		}
		if(!closingHour.isAfter(getOpeningHour())) {
			throw new IllegalArgumentException("El horario de cierre no puede ser antes que el de apertura.");
		}
		this.closingHour=LocalTime.of(closingHour.getHour(), closingHour.getMinute());
	}
	
	
	/**
	 * Consulta el id
	 * @return id, int
	 */
	public int getId() {
		return id;
	}
	
	
	/**
	 * Consulta las coordenadas gps
	 * @return gps, GPSCoordinate
	 */
	public GPSCoordinate getGps() {
		return new GPSCoordinate(gps.getLatitudeGD(),gps.getLongitudeGD());
	}
	
	
	/**
	 * Consulta el horario de apertura
	 * @return openingHour, LocalTime
	 */
	public LocalTime getOpeningHour() {
		if(openingHour==null) {
			return null;
		}
		return LocalTime.of(openingHour.getHour(), openingHour.getMinute());
	}
	
	
	/**
	 * Consulta el horario de cierre
	 * @return closingHour, LocalTime
	 */
	public LocalTime getClosingHour() {
		if(closingHour==null) {
			return null;
		}
		return LocalTime.of(closingHour.getHour(), closingHour.getMinute());
	}
	

	/**
	 * Consulta si puede almacenar un paquete dado
	 * @return boolean, true si lo puede almacenar, false en caso contrario
	 * @throws NullPointerException si packageToStore es null
	 */
	public boolean canStorePackage(Package packageToStore) {
		if(packageToStore==null) {
			throw new NullPointerException("El paquete a almacenar no puede ser null");
		}
		return !isFull();
	}
	
	/**
	 * Realiza las comprobaciones sobre si es posible guardar el paquete dado en el punto de recogida
	 * @param packageToStore, Package, package a guardar
	 * @throws NullPointerException si packageToStore es null
	 * @throws IllegalArgumentException si packageToStore ya ha sido recogido
	 * @throws IllegalArgumentException si packageToStore ya ha sido devuelto
	 * @throws IllegalArgumentException si packageToStore ya está almacenado
	 * @throws IllegalArgumentException si el paquete ya se encuentra en este establecimiento
	 * @throws IllegalArgumentException si no hay espacio para almacenar el paquete
	 */
	public void checkToAddPackage(Package packageToStore) {
		if(packageToStore==null) { 
			throw new NullPointerException("El paquete no puede ser null");
		}
		if(packageToStore.getPickUpStatus()) {
			throw new IllegalArgumentException("El paquete ya ha sido recogido");
		}
		if(packageToStore.getReturnStatus()) {
			throw new IllegalArgumentException("El paquete ya ha sido devuelto");
		}
		if(packageToStore.isStored()) {
			throw new IllegalArgumentException("El paquete ya esta almacenado");
		}
		if(contains(packageToStore.getId())) {
			throw new IllegalArgumentException("El paquete ya se encuentra en este establecimiento");
		}
		if(!canStorePackage(packageToStore)) {
			throw new IllegalArgumentException("No hay capacidad para almacenar el paquete");
		}
	}
	
	
	/**
	 * Consulta el número de paquetes almacenados en el punto de recogida
	 * @return int, número de paquetes almacenados, siempre mayor o igual que cero
	 */
	public int getNumberOfPackagesStored() {
		return getPackages().size();
	}
	
	
	/**
	 * El cliente recoge su paquete
	 * @param id, String, id del paquete a recoger
	 * @param pickUpDate, LocalDate, fecha de recogida
	 * @param pickUpHour, LocalTime, hora de recogida
	 * @throws NullPointerException si el id del paquete es null
	 * @throws IllegalArgumentException si el id del paquete está vacío
	 * @throws NullPointerException si pickUpDate es null
	 * @throws NullPointerException si pickUpHour es null
	 * @throws IllegalArgumentException si no encuentra ningún package con ese id
	 * @throws IllegalArgumentException si el paquete ya ha sido recogido
	 * @throws IllegalArgumentException si el paquete ha sido devuelto
	 * @throws IllegalArgumentException si ha expirado la fecha de recogida
	 * @throws IllegalArgumentException si la hora de recogida es antes que la hora de apertura
	 * @throws IllegalArgumentException si la hora de recogida es después que la de cierre
	 * @throws IllegalArgumentException si el paquete no se encuentra almacenado en este establecimiento
	 * @throws IllegalArgumentException si no encuentra ningún paquete con ese id
	 */
	public Package takeOutPackageAsCustomer(String id, LocalDate pickUpDate, LocalTime pickUpHour) {
		if(id==null) {
			throw new NullPointerException("El id del paquete no puede ser null.");
		}
		if(id.isEmpty()) {
			throw new IllegalArgumentException("El id del paquete no puede estar vacio");
		}
		if(pickUpDate==null) {
			throw new NullPointerException("El día de recogida del paquete no puede ser null.");
		}
		if(pickUpHour==null) {
			throw new NullPointerException("La hora de recogida del paquete no puede ser null.");
		}
		if(pickUpHour.isBefore(getOpeningHour())) {
			throw new IllegalArgumentException("La hora de recogida no puede ser antes que la hora de apertura");
		}
		if(pickUpHour.isAfter(getClosingHour())) {
			throw new IllegalArgumentException("La hora de recogida no puede ser después que la hora de cierre");
		}
	
		Package package1=getPackage(id);
		
		if(package1==null) {
			throw new NullPointerException("El paquete no puede ser null");
		}
		if(package1.getPickUpStatus()) {
			throw new IllegalArgumentException("El paquete ya ha sido recogido");
		}
		if(package1.getReturnStatus()) {
			throw new IllegalArgumentException("El paquete ha sido devuelto");
		}
		if(!contains(package1.getId())) {
			throw new IllegalArgumentException("El paquete no se encuentra en este establecimiento");
		}
		if(package1.getExpirationDate().isBefore(pickUpDate)) {
			throw new IllegalArgumentException("Ha expirado la fecha para recoger el paquete");
		}
		
		package1.pickUpPackage();
		package1.notStored();
		return package1;
	}
	
	/**
	 * Realiza las comprobaciones sobre si es posible que un empleado recoja el paquete y lo devuelva a la central
	 * @param packageToReturn, Package, paquete a devolver a la central
	 * @throws NullPointerException si package1 es null
	 * @throws IllegalArgumentException si el paquete ya ha sido devuelto
	 * @throws IllegalArgumentException si el paquete ya ha sido recogido
	 * @throws IllegalArgumentException si no encuentra el paquete
	 * @throws IllegalArgumentException si no encuentra ningún package con ese id
	 */
	public void checkToTakeOutPackageToReturn(Package packageToReturn) {
		if(packageToReturn==null) {
			throw new NullPointerException("El paquete no puede ser nulo.");
		}
		if(packageToReturn.getReturnStatus()) {
			throw new IllegalArgumentException("El paquete ya ha sido devuelto.");
		}
		if(packageToReturn.getPickUpStatus()) {
			throw new IllegalArgumentException("El paquete ya ha sido recogido.");
		}
		if(!packageToReturn.isStored()) {
			throw new IllegalArgumentException("El paquete no esta almacenado");
		}
	}
	
	
	/**
	 * Obtiene un id diferente al resto de ids creados con anterioridad
	 * @return int, nuevo id
	 */
	public static int nextId() {
		int aux = nextId;
		nextId++;
		return aux;
	}
	
	
	/**
	 * Consulta el paquete a partir de un id dado
	 * @param id, string, identificador del paquete
	 * @return Package, paquete buscado
	 * @throws NullPointerException si el id es null
	 * @throws IllegalArgumentException si id es una cadena vacía
	 * @throws IllegalArgumentException si no encuentra ningún package con ese id
	 */
	public Package getPackage(String id) {
		if(id==null) {
			throw new NullPointerException("El id no puede ser null");
		}
		if(id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser una cadena vacia");
		}
		List<Package> packages=getPackages();
		for(int i=0;i<packages.size();i++) {
			if(packages.get(i).getId().equals(id)) {
				return packages.get(i);
			}
		}
		throw new IllegalArgumentException("No hay ningún paquete que tenga el id indicado");
	}
	
	
	/**
	 * Consulta si el establecimiento contiene un paquete a partir de su id
	 * @param id, String, identificador del paquete
	 * @return boolean, true si algún paquete con ese identificador, false en caso contrario
	 * @throws NullPointerException si id es null
	 * @throws IllegalArgumentException si id es una cadena vacía
	 */
	public boolean contains(String id) {	
		if(id==null) {
			throw new NullPointerException("El id no puede ser null");
		}
		if(id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser una cadena vacia");
		}
		List<Package> packages=getPackages();
		for(int j=0;j<packages.size();j++) {
			if(packages.get(j).getId().equals(id)) {
				return true;
			}
		}
		return false;
	}
	

	/**
	 * Añade un paquete para almacenar
	 * @param packageToStore, Package, paquete a almacenar
	 * @throws NullPointerException si packageToStore es null
	 * @throws IllegalArgumentException si packageToStore ya ha sido recogido
	 * @throws IllegalArgumentException si packageToStore ya ha sido devuelto
	 * @throws IllegalArgumentException si packageToStore ya está almacenado
	 * @throws IllegalArgumentException si el paquete ya se encuentra en este establecimiento
	 * @throws IllegalArgumentException si no hay espacio para almacenar el paquete
	 */
	public abstract void addPackage(Package packageToStore);
	
	
	/**
	 * Devuelve el paquete a la central
	 * @param packageToStore, Package, paquete a devolver
	 * @throws NullPointerException si package1 es null
	 * @throws IllegalArgumentException si el paquete ya ha sido devuelto
	 * @throws IllegalArgumentException si el paquete ya ha sido recogido
	 * @throws IllegalArgumentException si no encuentra el paquete
	 * @throws IllegalArgumentException si no encuentra ningún package con ese id
	 */
	public abstract void takeOutPackageToReturn(Package packageToStore);
	
	
	/**
	 * Consulta los paquetes almacenados en el establecimiento
	 * @return List<Package>, lista de paquetes almacenados
	 */
	public abstract List<Package> getPackages();

	
	/**
	 * Consulta si el establecimiento cuenta con la opción de pago a contrareembolso
	 * @return boolean, true si tiene pago a contrareembolso, false en caso contrario
	 */
	public abstract boolean hasCOD();
	
	
	/**
	 * Consulta si el establecimiento ha llegado a su capacidad máxima
	 * @return boolean, true si está lleno, false en caso contrario
	 */
	public abstract boolean isFull();
	
	
	/**
	 * Consulta si un establecimiento tiene opción de entregas certificadas
	 * @return boolean, true si puede realizar entregas certificadas, false en caso contrario
	 */
	public abstract boolean hasCertified();
	
	
	/**
	 * Consulta si el punto de recogida está fuera de servicio
	 * @return true si está fuera de servicio, false si está operativo
	 */
	public abstract boolean isOutOfService();
	
	
	/**
	 * Modifica el punto de recogida a fuera de servicio
	 */
	public abstract void outOfService();
	
	
	@Override
	public String toString() {
		String s="";
		
		s+="\nId: "+getId();
		s+="\nGps: "+getGps();
		s+="\nOpening hour: "+getOpeningHour();
		s+="\nClosing hour: "+getClosingHour();
		s+="\nNumber of packages stored: "+getNumberOfPackagesStored();
		
		return s;
	}
	
	
	@Override
	public boolean equals(Object o) {
		if(o==this) {
			return true;
		}
		PickingPoint pickingPoint=(PickingPoint) o;
		return getId()==pickingPoint.getId();
	}
	
	
	@Override
	public abstract PickingPoint clone();
	
}
