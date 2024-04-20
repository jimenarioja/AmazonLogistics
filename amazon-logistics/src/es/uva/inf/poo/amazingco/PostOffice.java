/**
 * La clase PostOffice representa una oficina de correos, en la que, además de almacenar y entregar paquetes,
 * se tiene la opción de pago contrareembolso y entrega certificada, la cual se realiza mediante uno de los dni
 * que contiene la lista de dni autorizados. Una vez se comprueba que el dni es correcto y que esa persona está autorizada
 * para recoger el paquete, se entrega y se procede al registro del mismo, en el cual se especifica el paquete
 * entregado, la fecha de recogida y el dni que se proporcionó al realizar la recogida. Este registro es accesible
 * para diferentes consultas sobre los paquetes registrados.
 * 
 * Este establecimiento tiene capacidad ilimitada, por lo tanto, siempre va a poder almacenar paquetes.
 * 
 * Como este punto de recogida cuenta con la opción de pago contrareembolso, el cliente abonará la cantidad 
 * correspondiente a la oficina y ésta almacenará varios pagos para, más tarde, cuando sea requerido, transferir 
 * el dinero acumulado a AmazingCo.
 * 
 * @author jimenarioja
 */

package es.uva.inf.poo.amazingco;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import es.uva.inf.poo.maps.GPSCoordinate;

public class PostOffice extends PickingPoint implements IdentificationRegistry{

	private List<Package> packagesStored;
	private List<Package> packagesRegistered;
	
	private List<LocalDate> pickUpDate;
	private List<String> dnis;
	private double money;
	private boolean statusOutOfService;
	
	/**
	 * Constructor de PostOffice
	 * @param gps, GPSCoordinate, coordenadas gps
	 * @param openingHour, LocalTime, horario de apertura
	 * @param closingHour, LocalTime, horario de cierre
	 * @param money, double, dinero de cobros
	 * @throws NullPointerException si gps es null
	 * @throws NullPointerException si el horario de apertura es null
	 * @throws IllegalArgumentException si el horario de apertura es igual o más tarde que el de cierre
	 * @throws NullPointerException si closingHour es null
	 */
	public PostOffice(GPSCoordinate gps, LocalTime openingHour, LocalTime closingHour) {
		super(gps,openingHour,closingHour);
		packagesStored=new ArrayList<>();
		packagesRegistered=new ArrayList<>();
		pickUpDate=new ArrayList<>();
		dnis=new ArrayList<>();
		this.money=0;
	}
	
	
	/**
	 * Constructor de PostOffice
	 * @param id, int, identificador
	 * @param gps, GPSCoordinate, coordenadas gps
	 * @param openingHour, LocalTime, horario de apertura
	 * @param closingHour, LocalTime, horario de cierre
	 * @param money, double, dinero de cobros
	 * @throws NullPointerException si gps es null
	 * @throws NullPointerException si el horario de apertura es null
	 * @throws IllegalArgumentException si el horario de apertura es igual o más tarde que el de cierre
	 * @throws NullPointerException si closingHour es null
	 * @throws IllegalArgumentException si money no es positivo
	 */
	public PostOffice(GPSCoordinate gps, LocalTime openingHour, LocalTime closingHour,
			double money) {
		this(gps,openingHour,closingHour);
		addMoney(money);
	}
	
	
	/**
	 * Constructor copia de PostOffice
	 * @param postOffice, PostOffice
	 * @throws NullPointerException si gps es null
	 * @throws NullPointerException si el horario de apertura es null
	 * @throws IllegalArgumentException si el horario de apertura es igual o más tarde que el de cierre
	 * @throws NullPointerException si closingHour es null
	 */
	public PostOffice(PostOffice postOffice) {
		this(postOffice.getGps(), postOffice.getOpeningHour(), postOffice.getClosingHour());
		setId(postOffice.getId());
		if(postOffice.getMoney()>0) {
			addMoney(postOffice.getMoney());
		}
	}
	
	
	/**
	 * Consulta el dinero de cobros
	 * @return money, double, dinero de cobros, siempre positiva
	 */
	public double getMoney() {
		return money;
	}
	

	/**
	 * Modifica el estado a fuera de servicio
	 */
	public void outOfService() {
		this.statusOutOfService=true;
	}
	
	
	/**
	 * Modifica el estado a operativo
	 */
	public void notOutOfService() {
		this.statusOutOfService=false;
	}
	
	
	@Override
	public boolean isOutOfService() {
		return statusOutOfService;
	}
	
	
	@Override
	public Package takeOutPackageAsCustomer(String id, LocalDate pickUpDate, LocalTime pickUpHour) {
		Package packageToPickUp=super.takeOutPackageAsCustomer(id, pickUpDate, pickUpHour);
		packagesStored.remove(packageToPickUp);
		return packageToPickUp;
	}
	
	
	/**
	 * Un cliente recoge su paquete y lo paga a contrareembolso
	 * @param id, String, identificador del paquete
	 * @param pickUpDate, LocalDate, fecha de recogida del paquete
	 * @param pickUpHour, LocalTime, hora de recogida del paquete
	 * @param money, dinero a abonar
	 * @return Package, paquete que recoge el cliente
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
	 * @throws IllegalArgumentException si el paquete ya está pagado
	 * @throws IllegalArgumentException si el paquete tiene entrega certificada y no se proporciona dni
	 */
	public Package takeOutPackageAsCustomerToPay(String id, LocalDate pickUpDate, LocalTime pickUpHour, double money) {
		Package package1=takeOutPackageAsCustomer(id,pickUpDate,pickUpHour);
		if(package1.isPaid()) {
			throw new IllegalArgumentException("El paquete ya esta pagado");
		}
		if(package1.isCertified()) {
			throw new IllegalArgumentException("No es posible realizar entrega certificada");
		}
		chargePackage(money,package1);
		return package1;
	}
	
	
	/**
	 * Un cliente recoge su paquete certificado
	 * @param id, String, identificador del paquete
	 * @param pickUpDate, LocalDate, fecha de recogida
	 * @param pickUpHour, LocalTime, hora de recogida
	 * @param dni, String, dni de la persona que recoge el paquete
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
	 * @throws IllegalArgumentException si el paquete no está certificado
	 * @throws IllegalArgumentException si el paquete no está pagado y no se abona la cantidad correspondiente
	 */
	public Package takeOutPackageAsCustomerCertified(String id, LocalDate pickUpDate, LocalTime pickUpHour, String dni) {
		Package package1=takeOutPackageAsCustomer(id,pickUpDate,pickUpHour);
		if(!package1.isCertified()) {
			throw new IllegalArgumentException("El paquete no esta certificado");
		}
		if(!package1.isPaid()) {
			throw new IllegalArgumentException("El paquete no esta pagado");
		}
		registerCertifiedPackagePickup(package1, dni, pickUpDate);
		return package1;
	}
	
	
	/**
	 * El cliente recoge su paquete certificado y lo paga a contrareembolso
	 * @param id, String, identificador del paquete
	 * @param pickUpDate, LocalDate, fecha de recogida
	 * @param pickUpHour, LocalTime, hora de recogida
	 * @param money, double, cantidad a abonar
	 * @param dni, String, dni de la persona que recoge el paquete
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
	 * @throws IllegalArgumentException si el paquete ya está pagado
	 * @throws IllegalArgumentException si el paquete no está certificado
	 * @throws IllegalArgumentException si money no se corresponde con el precio del paquete
	 */
	public Package takeOutPackageAsCustomerCertifiedToPay(String id, LocalDate pickUpDate, LocalTime pickUpHour, double money,String dni) {
		Package package1=takeOutPackageAsCustomer(id,pickUpDate,pickUpHour);
		if(package1.isPaid()) {
			throw new IllegalArgumentException("El paquete esta pagado");
		}
		if(!package1.isCertified()) {
			throw new IllegalArgumentException("El paquete no esta certificado");
		}
		chargePackage(money,package1);
		registerCertifiedPackagePickup(package1, dni, pickUpDate);
		return package1;
	}
	
	
	@Override
	public void takeOutPackageToReturn(Package packageToReturn) {
		checkToTakeOutPackageToReturn(packageToReturn);
		packageToReturn.returnPackage();
		packageToReturn.notStored();
		packagesStored.remove(packageToReturn);
	}
	
	
	public int getIndexPackage(String id) {
		for(int index=0;index<packagesRegistered.size();index++) {
			if(packagesRegistered.get(index).getId().equals(id)) {
				return index;
			}
		}
		throw new IllegalArgumentException("No hay ningun paquete con ese id");
	}
	
	
	/**
	 * Añade el dinero cobrado a la caja
	 * @param money, double, dinero cobrado
	 * @throws IllegalArgumentException si money no es positivo
	 */
	public void addMoney(double money) {
		if(money<=0) {
			throw new IllegalArgumentException("La cantidad a ingresar ha de ser positiva");
		}
		this.money=getMoney()+money;
	}
	

	/**
	 * Cobra el paquete
	 * @param money, double, dinero a cobrar
	 * @param packageToCharge, Package, paquete a pagar
	 * @throws IllegalArgumentException si la cantidad que se abona no se corresponde con el precio del paquete
	 */
	public void chargePackage(double money, Package packageToCharge) {
		if(money!=packageToCharge.getPrice()) {
			throw new IllegalArgumentException("El pago no es correcto");
		}
		addMoney(getMoney()+money);
		packageToCharge.pay();
	}
	
	
	/**
	 * Paga a AmazingCo
	 */
	public void payToAmazingCo() {
		money=0;
	}
	

	@Override
	public boolean isPackageRegistered(String packageCode) {
		for(int i=0;i<packagesRegistered.size();i++) {
			if(packagesRegistered.get(i).getId().equals(packageCode)) {
				return true;
			}
		}
		return false;
	}

	
	@Override
	public Package getPackageRegistered(String packageCode) {
		for(int i=0;i<packagesRegistered.size();i++) {
			if(packagesRegistered.get(i).getId().equals(packageCode)) {
				return packagesRegistered.get(i);
			}
		}
		throw new IllegalArgumentException("No hay ningun paquete registrado con ese id");
	}

	
	@Override
	public String getRegisteredIdFor(String packageCode) {
		for(int i=0;i<packagesRegistered.size();i++) {
			if(packagesRegistered.get(i).getId().equals(packageCode)) {
				return packagesRegistered.get(i).getId();
			}
		}
		throw new IllegalArgumentException("No hay ningun paquete registrado con ese id");
	}
	

	@Override
	public LocalDate getPickupDateFor(String packageCode) {
		for(int i=0;i<packagesRegistered.size();i++) {
			if(packagesRegistered.get(i).getId().equals(packageCode)) {
				return pickUpDate.get(i);
			}
		}
		throw new IllegalArgumentException("No hay ningun paquete registrado con ese id");
	}
	

	/**
	 * Realiza el registro de la recogida certificada de un paquete 
	 * @param p paquete cuya recogida se registra, requiere que {@code p!= null}, 
	 * que no esté registrado {@code !isPackageRegistered(p.getCode())} y que p sea un paquete certificado 
	 * @param dni de la persona que recoge, requiere que el dni esté entre los autorizados en el paquete p para la recogida
	 * @param pickupDate fecha en la que se produce la recogida, requiere {@code pickupDate != null} 
	 * y pickupDate no es posterior a la fecha de vencimiento de la recogida
	 * @throws NullPointerException si certifiedPackage es null
	 * @throws IllegalArgumentException si el paquete no está certificado
	 * @throws IllegalArgumentException si el paquete ya ha sido recogido
	 * @throws IllegalArgumentException si el paquete ya ha sido devuelto
	 * @throws NullPointerException si dni es null
	 * @throws IllegalArgumentException si dni es una cadena vacía
	 * @throws NullPointerException si pickUpDate es null
	 * @throws IllegalArgumentException si ha expirado la fecha de recogida, es decir, que la fecha
	 * de recogida es más tarde que la fecha límite de recogida
	 * @throws IllegalArgumentException si el paquete no se encuentra almacenado en este establecimiento
	 * @throws IllegalArgumentException si el paquete ya se encuentra en el registro de paquetes certificados
	 * @throws IllegalArgumentException si el dni dado no se encuentra en la lista de dni autorizados
	 */
	@Override
	public void registerCertifiedPackagePickup(Package certifiedPackage, String dni, LocalDate pickupDate) {
		if(certifiedPackage==null) {
			throw new NullPointerException("El paquete no puede ser null");
		}
		if(!certifiedPackage.isCertified()) {
			throw new IllegalArgumentException("El paquete no esta certificado");
		}
		if(certifiedPackage.getPickUpStatus()) {
			throw new IllegalArgumentException("El paquete ya ha sido recogido");
		}
		if(certifiedPackage.getReturnStatus()) {
			throw new IllegalArgumentException("El paquete ya ha sido devuelto");
		}
		if(dni==null) {
			throw new NullPointerException("El dni no puede ser null");
		}
		if(dni.isEmpty()) {
			throw new IllegalArgumentException("El dni ha de tener contenido");
		}
		if(pickupDate==null) {
			throw new NullPointerException("La fecha de recogida no puede ser null");
		}
		if(pickupDate.isAfter(certifiedPackage.getExpirationDate())) {
			throw new IllegalArgumentException("La fecha limite de recogida ha expirado");
		}
		if(!packagesStored.contains(certifiedPackage)) {
			throw new IllegalArgumentException("El paquete no se encuentra almacenado en este establecimiento");
		}
		if(packagesRegistered.contains(certifiedPackage)) {
			throw new IllegalArgumentException("El paquete ya se encuentra en el registro de certificados");
		}
		if(!certifiedPackage.getAuthorizedDni().contains(dni)) {
			throw new IllegalArgumentException("Dni no autorizado");
		}
		packagesRegistered.add(certifiedPackage);
		packagesRegistered.add(certifiedPackage);	
		pickUpDate.add(pickupDate);
		dnis.add(dni);
	}
	
	
	@Override
	public void addPackage(Package packageToStore) {
		checkToAddPackage(packageToStore);
		
		packagesStored.add(packageToStore);
		packageToStore.stored();
	}
	

	@Override
	public List<Package> getPackages() {
		
		List<Package> packages=new ArrayList<>();
		for(int i=0;i<packagesStored.size();i++) {
			if(packagesStored.get(i)!=null) {
				packages.add(packagesStored.get(i));
			}
		}
		return packages;
	}

	
	@Override
	public boolean hasCOD() {
		return true;
	}
	
	
	@Override
	public boolean hasCertified() {
		return true;
	}

	
	@Override
	public boolean isFull() {
		return false;
	}
	
	
	@Override
	public String toString() {
		String s="";
		
		s+=super.toString(); 
		s+="Status out of service: "+isOutOfService();
		s+="Money: "+getMoney();
		s+="Suitable for COD: "+hasCOD();
		s+="Suitable for certified: "+hasCertified();
		
		return s;
	}
	
	
	@Override
	public PostOffice clone() {
		return new PostOffice(this);
	}

	

	
	
}
