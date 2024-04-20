/**
 * Un PackageLocker representa un establecimiento en cuyo interior se encuentran un conjunto de taquillas con 
 * dimensiones en las cuales se pueden almacenar paquetes (siempre que las dimensiones del paquete sean menores
 * que las dimensiones de la taquilla donde se va a guardar).
 * 
 * @author jimenarioja
 */


package es.uva.inf.poo.amazingco;

import es.uva.inf.poo.maps.GPSCoordinate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PackageLocker extends GroupablePoint implements Cloneable{

	private Locker lockers[];
	
	/**
	 * Constructor de PackageLocker
	 * @param gps, GPSCoordinate, localización de PackageLocker
	 * @param openingHour, LocalTime, horario de apertura
	 * @param closingHour, LocalTime, horario de cierre
	 * @param lockers, array de Locker, conjunto de taquillas
	 * @throws NullPointerException si gps es null
	 * @throws NullPointerException si el horario de apertura es null
	 * @throws IllegalArgumentException si el horario de apertura es igual o más tarde que el de cierre
	 * @throws NullPointerException si closingHour es null
	 * @throws IllegalArgumentException si se supera la capacidad máxima de metros cúbicos disponible
	 */
	public PackageLocker(GPSCoordinate gps, LocalTime openingHour, LocalTime closingHour,
			Locker lockers[], double cubicMetres) {
		super(gps,openingHour,closingHour,lockers.length,cubicMetres);
		double cubicMetresAux=0;
		for(int i=0;i<lockers.length;i++) {
			cubicMetresAux+=lockers[i].getCubicMetres();
		}
		if(cubicMetresAux>cubicMetres) {
			throw new IllegalArgumentException("Se ha superado la capacidad maxima");
		}
		this.lockers = new Locker[lockers.length]; 
		for(int i=0;i<lockers.length;i++) {
			this.lockers[i]=lockers[i].clone();
		}
	}
	
	
	/**
	 * Constructor copia de PackageLocker
	 * @param packageLocker, PackageLocker
	 * @throws NullPointerException si gps es null
	 * @throws NullPointerException si el horario de apertura es null
	 * @throws IllegalArgumentException si el horario de apertura es igual o más tarde que el de cierre
	 * @throws NullPointerException si closingHour es null
	 * @throws IllegalArgumentException si se supera la capacidad máxima de metros cúbicos disponible
	 */
	public PackageLocker(PackageLocker packageLocker) {
		this(packageLocker.getGps(),packageLocker.getOpeningHour(),
				packageLocker.getClosingHour(),packageLocker.getLockers(),packageLocker.getMaxCapacityBox());
		setId(packageLocker.getId());
		if(packageLocker.isOutOfService()) {
			this.outOfService();
		}
	}	
	
	
	/**
	 * Crea un nuevo número de taquilla
	 * @return newNumLocker, int (siempre mayor o igual que 0)
	 */
	public int getNewNumberOfLocker() {
		int newNumLocker=0;
		for(int index=0;index<lockers.length;index++) {
			newNumLocker+=lockers[index].getNumLocker()+1;
		}
		return newNumLocker;
	}
	
	
	/**
	 * Consulta todas las taquillas
	 * @return lockersCopy, Locker[], array de taquillas
	 */
	public Locker[] getLockers() {
		Locker lockersCopy[]=new Locker[lockers.length];
		for(int index=0;index<lockers.length;index++) {
			lockersCopy[index]=lockers[index].clone();
		}
		return lockersCopy;
	}
	
	
	@Override
	public List<Package> getPackages() {
		List<Package> packagesCopy=new ArrayList<>();
		for(int index=0;index<lockers.length;index++) {
			if(lockers[index].getStoredPackage()!=null) {
				packagesCopy.add(lockers[index].getStoredPackage().clone());
			}
		}
		return packagesCopy;
	}
	
	
	@Override
	public void addPackage(Package packageToStore) {
		checkToAddPackage(packageToStore);
		
		boolean found=false;
		for(int i=0;i<lockers.length && !found;i++) {
			if(lockers[i].getStoredPackage()==null) {
				lockers[i].setStoredPackage(packageToStore.clone());
				packageToStore.stored();
				found=true;
			}
		}
	}
	
	
	@Override
	public Package takeOutPackageAsCustomer(String id, LocalDate pickUpDate, LocalTime pickUpHour) {
		Package packageToPickUp=super.takeOutPackageAsCustomer(id, pickUpDate, pickUpHour);
		lockers[getIndexPackage(packageToPickUp.getId())]=null;
		return packageToPickUp;
	}
	
	
	@Override
	public void takeOutPackageToReturn(Package packageToReturn) {
		checkToTakeOutPackageToReturn(packageToReturn);
		packageToReturn.returnPackage();
		packageToReturn.notStored();
		lockers[getIndexPackage(packageToReturn.getId())]=null;	
	}
	
	
	@Override
	public boolean canStorePackage(Package packageToStore) {
		if(!packageToStore.isPaid()) {
			return false;
		}
		if(packageToStore.isCertified()) {
			return false;
		}
		boolean isPosible=false;
		for(int i=0;i<lockers.length && !isPosible;i++) {
			if(lockers[i].getStoredPackage()==null && lockers[i].getLength()>=packageToStore.getLength() &&
					lockers[i].getHeight()>=packageToStore.getHeight() &&
					lockers[i].getWidth()>=packageToStore.getWidth()) {
				isPosible=true;
			}
				
		}
		return isPosible && super.canStorePackage(packageToStore);
	}
	
	
	@Override
	public void expandCapacity(int gap) {
		if(gap<=0) {
			throw new IllegalArgumentException("El numero de taquillas tiene que ser positivo");
		}
		Locker auxLockers[]=this.lockers;
		this.lockers=new Locker[getMaxCapacityBox()+gap];
		for(int i=0;i<auxLockers.length;i++) {
			lockers[i]=auxLockers[i];
		}
		setMaxCapacityBox(lockers.length);
	}
	
	
	@Override
	public void reduceCapacity(int gap) {
		if(gap<0) {
			throw new IllegalArgumentException("El numero de taquillas tiene que ser positivo");
		}
		if(getMaxCapacityBox()-gap<getNumberOfPackagesStored()) {
			throw new IllegalArgumentException("El numero de taquillas no se puede reducir porque hay mas paquetes almacenados");
		}
		Locker auxLockers[]=this.lockers;
		this.lockers=new Locker[getMaxCapacityBox()-gap];
		for(int i=0;i<auxLockers.length;i++) {
			if(auxLockers[i]!=null) {
				lockers[i]=auxLockers[i];
			}
		}
		setMaxCapacityBox(lockers.length);
	}	
	

	@Override
	public boolean hasCOD() {
		return false;
	}

	
	@Override
	public String toString() {
		String s="";
		
		s=super.toString();
		s+="Number of lockers: "+getMaxCapacityBox();
		s+="Status out of service: "+isOutOfService();
		s+="Suitable for COD: "+hasCOD();
		s+="Suitable for certified: "+hasCertified();
		
		return s;
	}

	
	@Override
	public PackageLocker clone() {
		return new PackageLocker(this);
	}
	
}
