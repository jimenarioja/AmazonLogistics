/**
 * Un Kiosk es un establecimiento equiparable a un negocio local. Los clientes pueden ir a recoger sus paquetes 
 * almacenados en este punto de recogida con la posibilidad de pagar contrareembolso. 
 * 
 * En ese caso, el cliente abonará la cantidad correspondiente al Kiosk y este almacenará varios pagos para, más tarde, cuando sea requerido,
 * transferir el dinero acumulado a AmazingCo.
 * 
 * @author jimenarioja
 */

package es.uva.inf.poo.amazingco;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import es.uva.inf.poo.maps.GPSCoordinate;

public class Kiosk extends GroupablePoint implements Cloneable {
	 
	private Package packages[];
	private double money;
	
	/**
	 * Constructor de Kiosk
	 * @param gps, GPSCoordinate, localización de PackageLocker
	 * @param openingHour, LocalTime, horario de apertura
	 * @param closingHour, LocalTime, horario de cierre
	 * @param lockers, array de Locker, conjunto de taquillas
	 * @throws NullPointerException si gps es null
	 * @throws NullPointerException si el horario de apertura es null
	 * @throws IllegalArgumentException si el horario de apertura es igual o más tarde que el de cierre
	 * @throws NullPointerException si closingHour es null
	 * @throws IllegalArgumentException si maxCapacity no es positiva
	 */
	public Kiosk(GPSCoordinate gps, LocalTime openingHour, LocalTime closingHour,
			int maxCapacity, double cubicMetres) {
		super(gps,openingHour,closingHour,maxCapacity,cubicMetres);
		packages = new Package[maxCapacity];
		money=0;
	}
	
	/**
	 * Constructor de Kiosk
	 * @param gps, GPSCoordinate, localización de PackageLocker
	 * @param openingHour, LocalTime, horario de apertura
	 * @param closingHour, LocalTime, horario de cierre
	 * @param lockers, array de Locker, conjunto de taquillas
	 * @throws NullPointerException si gps es null
	 * @throws NullPointerException si el horario de apertura es null
	 * @throws IllegalArgumentException si el horario de apertura es igual o más tarde que el de cierre
	 * @throws NullPointerException si closingHour es null
	 * @throws IllegalArgumentException si no hay espacio para almacenar tantos paquetes
	 * @throws IllegalArgumentException si se supera la capacidad máxima de metros cúbicos
	 */
	public Kiosk(GPSCoordinate gps, LocalTime openingHour, LocalTime closingHour,
			int maxCapacity, Package packages[], double cubicMetres) {
		this(gps,openingHour,closingHour,maxCapacity,cubicMetres);
		if(packages.length>maxCapacity) {
			throw new IllegalArgumentException("No hay espacio suficiente para almacenar tantos paquetes");
		}
		double cubicMetresUsed=0;
		for(int i=0;i<packages.length;i++) {
			cubicMetresUsed+=packages[i].getCubicMetres();
		}
		if(cubicMetresUsed>cubicMetres) {
			throw new IllegalArgumentException("Se supera la capacidad maxima de metros cubicos");
		}
		for(int i=0;i<packages.length;i++) {
			packages[i].stored();
			this.packages[i]=new Package(packages[i]);
		}
	}
	
	/**
	 * Constructor copia de Kiosk
	 * @param kiosk, Kiosk
	 * @throws NullPointerException si gps es null
	 * @throws NullPointerException si el horario de apertura es null
	 * @throws IllegalArgumentException si el horario de apertura es igual o más tarde que el de cierre
	 * @throws NullPointerException si closingHour es null
	 */
	public Kiosk(Kiosk kiosk) {
		this(kiosk.getGps(),kiosk.getOpeningHour(),
				kiosk.getClosingHour(),kiosk.getMaxCapacityBox(),kiosk.getMaxCapacityCubicMetres());
		if(kiosk.getPackages()!=null) {
			for(int i=0;i<kiosk.getPackages().size();i++) {
				addPackage(kiosk.getPackage(i));
			}
		}
		setId(kiosk.getId());
		if(kiosk.isOutOfService()) {
			this.outOfService();
		}
	}
	
	@Override
	public void addPackage(Package packageToStore) {
		checkToAddPackage(packageToStore);
		
		boolean found=false;
		for(int i=0;i<packages.length && !found;i++) {
			if(packages[i]==null) {
				packageToStore.stored();
				packages[i]=new Package(packageToStore);
				
				found=true;
			}
		}
	}
	
	@Override
	public void takeOutPackageToReturn(Package packageToReturn) {
		checkToTakeOutPackageToReturn(packageToReturn);
		packageToReturn.returnPackage();
		packageToReturn.notStored();
		packages[getIndexPackage(packageToReturn.getId())]=null;
	}
	
	@Override
	public Package takeOutPackageAsCustomer(String id, LocalDate pickUpDate, LocalTime pickUpHour) {
		Package packageToPickUp=super.takeOutPackageAsCustomer(id, pickUpDate, pickUpHour);
		if(!packageToPickUp.isPaid()) {
			throw new IllegalArgumentException("El paquete no esta pagado");
		}
		for(int i=0;i<packages.length;i++) {
			if(packages[i]!=null && packages[i].equals(packageToPickUp)) {
				packages[i]=null;
			}
		}
		return packageToPickUp;
	}
	
	/**
	 * El cliente recoge el paquete y lo paga
	 * @param money, double, cantidad a abonar
	 * @param id, String, identificador de paquete
	 * @param pickUpDate, LocalDate, fecha de recogida
	 * @param pickUpHour, LocalTime, hora de recogida
	 * @return Package, paquete a recoger
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
	 * @throws NullPointerException si packageToPay es null
	 * @throws IllegalArgumentException si el paquete ya está pagado
	 * @throws IllegalArgumentException si la cantidad abonada no coincide con el precio del paquete
	 */
	public Package takeOutPackageToPayAsCustomer(double money, String id, LocalDate pickUpDate, LocalTime pickUpHour) {
		for(int i=0;i<packages.length;i++) {
			if(packages[i]!=null && packages[i].getId().equals(id) ) {
				if(packages[i].isPaid()) {
					throw new IllegalArgumentException("El paquete ya esta pagado");
				}
				if(packages[i].getPrice()!=money) {
					throw new IllegalArgumentException("La cantidad a abonar no coincide con el precio del paquete");
				}
				addMoney(money);
				packages[i].pay();
			}
		}
		return takeOutPackageAsCustomer(id,pickUpDate,pickUpHour);
	}
	
	
	@Override
	public List<Package> getPackages() {
		List<Package> packagesCopy=new ArrayList<>();
		for(int index=0;index<packages.length;index++) {
			
			if(packages[index]!=null) {
				packagesCopy.add(new Package(packages[index]));
			}
		}
		
		return packagesCopy;
	}	
	
	@Override
	public void expandCapacity(int gap) {
		if(gap<=0) {
			throw new IllegalArgumentException("La cantidad a ampliar ha de ser positiva");
		}
		Package auxPackages[]=this.packages;
		this.packages=new Package[getMaxCapacityBox()+gap];
		for(int i=0;i<auxPackages.length;i++) {
			packages[i]=auxPackages[i];
		}
		setMaxCapacityBox(packages.length);
	}
	
	@Override
	public void reduceCapacity(int gap) {
		if(gap<=0) {
			throw new IllegalArgumentException("La cantidad a reducir ha de ser positiva");
		}
		if(getMaxCapacityBox()-gap<getNumberOfPackagesStored()) {
			throw new IllegalArgumentException("No es posible reducir la cantidad ya que hay mas paquetes");
		}
		Package auxPackages[]=this.packages;
		this.packages=new Package[getMaxCapacityBox()-gap];
		setMaxCapacityBox(packages.length);
		for(int i=0;i<this.packages.length;i++) {
			packages[i]=auxPackages[i];
		}
	}
	
	/**
	 * Consulta el dinero acumulado
	 */
	public double getMoney() {
		return money;
	}
		
	/**
	 * Añade el dinero a la cantidad acumulada
	 * @param money, double, dinero a abonar
	 * @throws IllegalArgumentException si la cantidad a ingresar no es positiva
	 */
	public void addMoney(double money) {
		if(money<=0) {
			throw new IllegalArgumentException("La cantidad a ingresar ha de ser positiva");
		}
		this.money+=money;
	}
	
	/**
	 * Cobra el paquete
	 * @param money, double, dinero a pagar
	 * @param packageToPay, Package, paquete a pagar
	 * @throws NullPointerException si packageToPay es null
	 * @throws IllegalArgumentException si el paquete ya está pagado
	 * @throws IllegalArgumentException si la cantidad abonada no coincide con el precio del paquete
	 */
	public void chargePackage(double money,Package packageToPay) {
		if(packageToPay==null) {
			throw new NullPointerException("El paquete no puede ser null");
		}
		if(packageToPay.isPaid()) {
			throw new IllegalArgumentException("El paquete ya esta pagado");
		}
		if(packageToPay.getPrice()!=money) {
			throw new IllegalArgumentException("La cantidad a abonar no coincide con el precio del paquete");
		}
		packageToPay.pay();
		addMoney(money);
	}
	
	
	@Override
	public boolean isFull() {
		return super.isFull() || getUsedCubicMetres()==getMaxCapacityCubicMetres();
	}
	
	@Override
	public boolean canStorePackage(Package packageToStore) {
		if(packageToStore.isCertified()) {
			return false;
		}
		return super.canStorePackage(packageToStore) && getEmptyCubicMetres()>=packageToStore.getCubicMetres();
	}
	
	
	@Override
	public double getUsedCubicMetres() {
		double cubicMetresUsed=0;
		for(int i=0;i<packages.length;i++) {
			if(packages[i]!=null) {
				cubicMetresUsed+=packages[i].getCubicMetres();
			}
		}
		return cubicMetresUsed;
	}
	
	/**
	 * Paga a la empresa AmazingCo el dinero que ha recaudado tras la recogida de pagos a contrareembolso
	 */
	public void payToAmazingCo() {
		money=0;
	}
	
	@Override
	public boolean hasCOD() {
		return true;
	}
	
	@Override
	public String toString() { 
		String s="";
		s=super.toString();
		
		return s;
	}
	
	@Override
	public Kiosk clone() {
		return new Kiosk(this);
	}

}
