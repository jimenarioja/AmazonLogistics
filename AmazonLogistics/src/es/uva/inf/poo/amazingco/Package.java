/**
 * La clase Package representa un paquete con unas dimensiones y con una fecha l�mite de recogida, as� como un 
 * identificador �nico. El paquete tambi�n tiene un estado de recogida por el usuario, un estado sobre su 
 * devoluci�n a central y un estado de almacenamiento. Es posible que el paquete tenga entrega certificada,
 * lo cual se puede observar por su estado as� como por una lista de dni autorizados para entregar el paquete.
 *  
 * @author jimenarioja
 */

package es.uva.inf.poo.amazingco;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Package implements Cloneable{

	private String id;
	private LocalDate expirationDate;
	private boolean pickUpStatus;
	private boolean returnStatus;
	private double width;
	private double length;
	private double height;
	private double price;
	private boolean paid;
	private boolean certified;
	private List<String> authorizedDni;
	private boolean stored;
	
	/**
	 * Constructor de Package
	 * @param id, string, identificador del paquete
	 * @param expirationDate, localDate, fecha l�mite de recogida del paquete
	 * @param pickUpStatus, boolean, estado de recogida (true recogido, false en caso contrario)
	 * @param returnStatus, boolean, estado de devoluci�n (true devuelto, false en caso contrario)
	 * @param price, double, precio del paquete
	 * @param paid, boolean, si el producto ha sido pagado o no
	 * @throws NullPointerException si el id es null
	 * @throws IllegalArgumentException si el d�cimo d�gito no es el resto de la divisi�n entera entre 10.
	 * @throws IllegalArgumentException si el id no tiene 10 d�gitos.
	 * @throws IllegalArgumentException si el id contiene alg�n caracter que no sea d�gito
	 * @throws NullPointerException si expirationDate es null
	 * @throws IllegalArgumentException si width no es positivo
	 * @throws IllegalArgumentException si length no es positivo
	 * @throws IllegalArgumentException si height no es positivo
	 * @throws IllegalArgumentException si price no es positivo
	 */
	public Package(String id, LocalDate expirationDate, double width, double length, 
			double height, double price, boolean paid) {
		setId(id);
		setExpirationDate(expirationDate);
		notPickUpPackage();
		notReturnPackage();
		setWidth(width);
		setLength(length);
		setHeight(height);
		setPrice(price);
		setPaid(paid);
		notCertified();
		notStored();
	}
	
	
	/**
	 * Constructor de Package
	 * @param id, string, identificador del paquete
	 * @param expirationDate, localDate, fecha l�mite de recogida del paquete
	 * @param pickUpStatus, boolean, estado de recogida (true recogido, false en caso contrario)
	 * @param returnStatus, boolean, estado de devoluci�n (true devuelto, false en caso contrario)
	 * @param price, double, precio del paquete
	 * @param paid, boolean, si el producto ha sido pagado o no
	 * @param authorizedDni, lista de string, el conjunto de dni autorizados para recoger el paquete
	 * @throws NullPointerException si el id es null
	 * @throws IllegalArgumentException si el d�cimo d�gito no es el resto de la divisi�n entera entre 10.
	 * @throws IllegalArgumentException si el id no tiene 10 d�gitos.
	 * @throws IllegalArgumentException si el id contiene alg�n caracter que no sea d�gito
	 * @throws NullPointerException si expirationDate es null
	 * @throws IllegalArgumentException si width no es positivo
	 * @throws IllegalArgumentException si length no es positivo
	 * @throws IllegalArgumentException si height no es positivo
	 * @throws IllegalArgumentException si price no es positivo
	 * @throws IllegalArgumentException si el paquete no tiene entrega certificada
	 * @throws NullPointerException si authorizedDni es null
	 * @throws IllegalArgumentException si la lista est� vac�a
	 * @throws NullPointerException si authorizedDni contiene alg�n dni null
	 */
	public Package(String id, LocalDate expirationDate, double width, double length, 
			double height, double price, boolean paid, List<String> authorizedDni) {
		this(id,expirationDate,width,length,height,price,paid);
		certified();
		setAuthorizedDni(authorizedDni);
	}
	
	
	/**
	 * Constructor copia de Package
	 * @param packageCopy
	 * @throws NullPointerException si el id es null
	 * @throws IllegalArgumentException si el d�cimo d�gito no es el resto de la divisi�n entera entre 10.
	 * @throws IllegalArgumentException si el id no tiene 10 d�gitos.
	 * @throws IllegalArgumentException si el id contiene alg�n caracter que no sea d�gito
	 * @throws NullPointerException si expirationDate es null
	 * @throws NullPointerException si, en caso de tener entrega certificada, authorizedDni es null
	 * @throws IllegalArgumentException si, en caso de tener entrega certificada, la lista est� vac�a
	 * @throws NullPointerException si, en caso de tener entrega certificada, authorizedDni contiene alg�n dni null
	 */
	public Package(Package packageCopy) {
		this(packageCopy.getId(),packageCopy.getExpirationDate(),packageCopy.getWidth(),
				packageCopy.getLength(),packageCopy.getHeight(),packageCopy.getPrice(),
				packageCopy.isPaid());
		if(packageCopy.isCertified()) {
			setAuthorizedDni(packageCopy.getAuthorizedDni());
		}
	}
	
	
	/**
	 * Modifica el valor del id del package
	 * @param id, cualquier string
	 * @throws NullPointerException si el id es null
	 * @throws IllegalArgumentException si el d�cimo d�gito no es el resto de la divisi�n entera entre 10.
	 * @throws IllegalArgumentException si el id no tiene 10 d�gitos.
	 * @throws IllegalArgumentException si el id contiene alg�n caracter que no sea d�gito
	 */
	public void setId(String id) {
		if(id==null) {
			throw new NullPointerException("El id no puede ser null");
		}
		if(id.length()==10) {
			int addition=0;
			int i=0;
			while(i<id.length()-1 && id.charAt(i)>='0' && id.charAt(i)<='9') {
				addition+=id.charAt(i)-'0';
				i++;
			}
			if(i!=id.length()-1) {
				throw new IllegalArgumentException("Todos los caracteres han de ser digitos");
			}
			if(addition%10!=id.charAt(id.length()-1)-'0') {
				throw new IllegalArgumentException("El d�cimo d�gito tiene que ser el modulo de la divisi�n entera de 10.");
			}
			this.id=id;
		}
		else {
			throw new IllegalArgumentException("El id no tiene 10 d�gitos");
		}
	}
	
	
	/**
	 * Modifica la fecha final del paquete
	 * @param finalDate, cualquier fecha
	 * @throws NullPointerException si expirationDate es null
	 */
	public void setExpirationDate(LocalDate expirationDate) {
		if(expirationDate==null) {
			throw new NullPointerException("La fecha no puede ser null");
		}
		this.expirationDate = LocalDate.of(expirationDate.getYear(), expirationDate.getMonth(), expirationDate.getDayOfMonth());
	}

	
	/**
	 * Modifica el ancho del paquete
	 * @param width, double, ancho del paquete
	 * @throws IllegalArgumentException si no es positivo
	 */
	public void setWidth(double width) {
		if(width<=0) {
			throw new IllegalArgumentException("Ancho del paquete no v�lido");
		}
		this.width=width;
	}
	
	
	/**
	 * Modifica el largo del paquete
	 * @param length, double, largo del paquete
	 * @throws IllegalArgumentException si no es positivo
	 */
	public void setLength(double length) {
		if(length<=0) {
			throw new IllegalArgumentException("Ancho del paquete no v�lido");
		}
		this.length=length;
	}
	
	
	/**
	 * Modifica el alto del paquete
	 * @param height, double, alto del paquete
	 * @throws IllegalArgumentException si no es positivo
	 */
	public void setHeight(double height) {
		if(height<=0) {
			throw new IllegalArgumentException("Ancho del paquete no v�lido");
		}
		this.height=height;
	}
	
	
	/**
	 * Modifica el precio del paquete
	 * @param price, double, precio del paquete
	 * @throws IllegalArgumentException si el precio no es positivo
	 */
	public void setPrice(double price) {
		if(price<=0) {
			throw new IllegalArgumentException("El precio ha de ser positivo");
		}
		this.price=price;
	}
	
	
	/**
	 * Modifica el estado de pagado
	 * @param paid, boolean, true si ya est� pagado, false en caso contrario
	 */
	public void setPaid(boolean paid) {
		this.paid=paid;
	}
	
	
	/**
	 * Modifica el estado para que la entrega no sea certificada
	 */
	public void notCertified() {
		certified=false;
	}
	
	
	/**
	 * Modifica el estado para que la entrega sea certificada
	 */
	public void certified() {
		certified=true;
	}
	
	
	/**
	 * Modifica el conjunto de dni autorizados para la entrega certificada
	 * @param authorizedDni, lista de String con los dni autorizados
	 * @throws IllegalArgumentException si el paquete no tiene entrega certificada
	 * @throws NullPointerException si authorizedDni es null
	 * @throws IllegalArgumentException si la lista est� vac�a
	 * @throws NullPointerException si authorizedDni contiene alg�n dni null
	 */
	public void setAuthorizedDni(List<String> authorizedDni) {
		if(!isCertified()) {
			throw new IllegalArgumentException("El paquete no tiene entrega certificada");
		}
		if(authorizedDni==null) {
			throw new NullPointerException("La lista de dni de personas autorizadas es null");
		}
		if(authorizedDni.isEmpty()) {
			throw new IllegalArgumentException("La lista esta vacia");
		}
		if(authorizedDni.contains(null)) {
			throw new NullPointerException("La lista contiene alg�n dni null");
		}
		this.authorizedDni=new ArrayList<>();
		this.authorizedDni=authorizedDni;
	}
	
	
	/**
	 * Modifica el estado a almacenado
	 * @throws IllegalArgumentException si el paquete est� recogido
	 * @throws IllegalArgumentException si el paquete est� devuelto
	 */
	public void stored() {
		if(getPickUpStatus()) {
			throw new IllegalArgumentException("El paquete no puede estar recogido");
		}
		if(getReturnStatus()) {
			throw new IllegalArgumentException("El paquete no puede estar devuelto");
		}
		stored=true;
	}
	
	
	/**
	 * Modifica el estado a no almacenado
	 * @throws IllegalArgumentException si el paquete no est� ni almacenado ni devuelto
	 */
	public void notStored() {
		stored=false;
	}
	
	
	/**
	 * Modifica el estado del paquete a pagado
	 */
	public void pay() {
		paid=true;
	}
	
	
	/**
	 * Consulta el valor del id
	 * @return id, string
	 */
	public String getId() {
		return id;
	}
	
	
	/**
	 * Consulta la fecha l�mite
	 * @return finalDate, una fecha LocalDate
	 */
	public LocalDate getExpirationDate() {
		return LocalDate.of(expirationDate.getYear(), expirationDate.getMonth(), expirationDate.getDayOfMonth());
	}
	
	
	/**
	 * Consulta si el paquete ha sido recogido o no
	 * @return boolean, (true si ha sido recogido, false en caso contrario)
	 */
	public boolean getPickUpStatus() {
		return pickUpStatus;
	}
	
	
	/**
	 * Consulta si el paquete ha sido devuelto o no
	 * @return boolean, (true si ha sido devuelto, false en caso contrario)
	 */
	public boolean getReturnStatus() {
		return returnStatus;
	}
	
	
	/**
	 * Consulta el ancho del paquete
	 * @return width, double, ancho del paquete
	 */
	public double getWidth() {
		return width;
	}
	

	/**
	 * Consulta el largo del paquete
	 * @return length, double, largo del paquete
	 */
	public double getLength() {
		return length;
	}
	

	/**
	 * Consulta el alto del paquete
	 * @return height, double, alto del paquete
	 */
	public double getHeight() {
		return height;
	}
	
	
	/**
	 * Consulta los metros c�bicos que ocupa el paquete
	 * @return double, metros c�bicos, siempre positivo
	 */
	public double getCubicMetres() {
		return getWidth()*getLength()*getHeight();
	}
	
	
	/**
	 * Consulta el precio del paquete
	 * @return price, double, precio del paquete
	 */
	public double getPrice() {
		return price;
	}
	
	
	/**
	 * Consulta si el paquete est� pagado
	 * @return paid, boolean, true si est� pagado, false en caso contrario
	 */
	public boolean isPaid() {
		return paid;
	}
	
	
	/**
	 * Consulta si el paquete tiene entrega certificada
	 * @return certified, boolean, si el paquete tiene entrega certificada
	 */
	public boolean isCertified() {
		return certified;
	}
	
	
	/**
	 * Consulta la lista de dni de personas autorizadas para recoger el paquete
	 * @return authorizedDni, List de String, lista con los dni
	 * @throw IllegalArgumentException si el paquete no est� certificado
	 */
	public List<String> getAuthorizedDni(){
		if(!isCertified()) {
			throw new IllegalArgumentException("El paquete no esta certificado");
		}
		return authorizedDni;
	}
	
	
	/**
	 * Consulta si el paquete est� almacenado
	 * @return stored, boolean, true si est� almacenado, false en caso contrario
	 */
	public boolean isStored() {
		return stored;
	}
	
	
	/**
	 * Comprueba si la fecha dada es m�s tarde que la de fin de almacenaje
	 * @param date, LocalDate
	 * @return boolean, (true si date es antes que la fecha final, false en otro caso)
	 * @throws NullPointerException si date es null
	 */
	public boolean outOfTime(LocalDate date) {
		if(date==null) {
			throw new NullPointerException("La fecha no puede ser null");
		}
		return getExpirationDate().isBefore(date);
	}
	
	
	/**
	 * Cambia el estado del paquete a recogido
	 * @throws IllegalArgumentException si el paquete ya ha sido recogido
	 * @throws IllegalArgumentException si el paquete ya ha sido devuelto
	 */
	public void pickUpPackage() {
		if(getPickUpStatus()) {
			throw new IllegalArgumentException("El paquete ya ha sido recogido");
		}
		if(getReturnStatus()) {
			throw new IllegalArgumentException("El paquete ha sido devuelto");
		}
		pickUpStatus=true;
	}
	
	
	/**
	 * Cambia el estado del paquete a no recogido
	 * @throws IllegalArgumentException si el paquete ya ha sido recogido
	 * @throws IllegalArgumentException si el paquete ya ha sido devuelto
	 */
	public void notPickUpPackage() {
		if(getPickUpStatus()) {
			throw new IllegalArgumentException("El paquete ya ha sido recogido");
		}
		if(getReturnStatus()) {
			throw new IllegalArgumentException("El paquete ha sido devuelto");
		}
		pickUpStatus=false;
	}	
	
	
	/**
	 * Cambia el estado del paquete a devuelto
	 * @throws IllegalArgumentException si el paquete ya ha sido recogido
	 * @throws IllegalArgumentException si el paquete ya ha sido devuelto
	 */
	public void returnPackage() {
		if(getPickUpStatus()) {
			throw new IllegalArgumentException("El paquete ya ha sido recogido");
		}
		if(getReturnStatus()) {
			throw new IllegalArgumentException("El paquete ya ha sido devuelto");
		}
		returnStatus=true;
	}
	
	
	/**
	 * Cambia el estado del paquete a no devuelto
	 * @throws IllegalArgumentException si el paquete ya ha sido recogido
	 * @throws IllegalArgumentException si el paquete ya ha sido devuelto
	 */
	public void notReturnPackage() {
		if(getPickUpStatus()) {
			throw new IllegalArgumentException("El paquete ya ha sido recogido");
		}
		if(getReturnStatus()) {
			throw new IllegalArgumentException("El paquete ya ha sido devuelto");
		}
		returnStatus=false;		
	}
	
	
	/**
	 * Aplaza la fecha l�mite de recogida una cantidad de d�as
	 * @param increaseOfDays, int, n�mero de d�as de incremento en la fecha de recogida
	 * @throws IllegalArgumentException si el paquete ya ha sido recogido
	 * @throws IllegalArgumentException si el paquete ya ha sido devuelto
	 */
	public void delayPickUpDay(int increaseOfDays) {
		if(getPickUpStatus()) {
			throw new IllegalArgumentException("El paquete ya ha sido recogido.");
		}
		if(getReturnStatus()) {
			throw new IllegalArgumentException("El paquete ya ha sido devuelto.");
		}
		setExpirationDate(getExpirationDate().plusDays(increaseOfDays));
	}
	
	
	@Override
	public String toString() {
		String s="";
		
		s+="Id: "+getId();
		s+="\nExpiration date: "+getExpirationDate();
		s+="\nPick up status: "+getPickUpStatus();
		s+="\nReturn status: "+getReturnStatus();
		s+="\nStore: "+isStored();
		s+="\nPackage dimensions: "+getWidth()+"x"+getLength()+"x"+getHeight();
		s+="\nPrice: "+getPrice();
		s+="\nPaid: "+isPaid();
		s+="\nCertified: "+isCertified();
		if(isCertified()) {
			s+="\nAuthorized dni: "+getAuthorizedDni();
		}
		return s;
	}
	
	
	@Override
	public boolean equals(Object o) {
		if(this==o) {
			return true;
		}
		Package p = (Package)o;
		return id.equals(p.getId());
	}
	
	
	@Override
	public Package clone() {
		return new Package(this);
	}
}