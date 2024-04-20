/**
 * La clase PickingPointSystem es un conjunto de establecimientos que ofrecen tanto almacenamiento como posterior 
 * entrega de paquetes. 
 * 
 * Es posible añadir nuevos puntos de recogida así como eliminarlos. Además, cuenta con distintas funcionalidades,
 * entre las que destacan: listar los puntos de recogida que se encuentran a un radio desde un punto, pudiendo
 * elegir si el establecimiento dispone de entrega certificada o pago contrareembolso; conocer el 
 * punto de recogida más próximo a una ubicación dada, pudiendo filtrar aquellos que cuentan con entrega certificada
 * y pago contrareembolso.
 * 
 * @author jimenarioja
 */

package es.uva.inf.poo.amazingco;
import java.util.ArrayList;
import java.util.List;
import es.uva.inf.poo.maps.GPSCoordinate;


public class PickingPointSystem {
	
	private List<PickingPoint> pickingPoints;
	 
	/**
	 * Constructor por defecto de PickingPointsSystem
	 */
	public PickingPointSystem() {
		pickingPoints=new ArrayList<>();
	}
	
	
	/**
	 * Constructor de PickingPointSystem
	 * @param pickingPoints, lista de PickingPoint
	 * @throws NullPointerException si pickingPoints es null
	 * @throws NullPointerException si algún pickingPoint es null
	 * @throws IllegalArgumentException si el punto ya está en la lista
	 * @throws IllegalArgumentException si esa coordenada ya está en la lista
	 */
	public PickingPointSystem(List<PickingPoint> pickingPoints) {
		setPickingPoint(pickingPoints);
	}
	
	
	/**
	 * Constructor copia de PickingPointsSystem
	 * @param pickingPointsSystem, PickingPointsSystem
	 * @throws NullPointerException si pickingPoints es null
	 * @throws NullPointerException si pickingPoint es null
	 * @throws IllegalArgumentException si el punto ya está en la lista
	 * @throws IllegalArgumentException si esa coordenada ya está en la lista
	 */
	public PickingPointSystem(PickingPointSystem pickingPointsSystem) {
		this(pickingPointsSystem.getPickingPoints());
	}
	
	
	/**
	 * Modifica el valor de pickingPoints
	 * @param pickingPoints, lista de PickingPoint
	 * @throws NullPointerException si pickingPoints es null
	 * @throws NullPointerException si pickingPoint es null
	 * @throws IllegalArgumentException si el punto ya está en la lista
	 * @throws IllegalArgumentException si esa coordenada ya está en la lista
	 */
	public void setPickingPoint(List<PickingPoint> pickingPoints) {
		if(pickingPoints==null) {
			throw new NullPointerException("La lista no puede ser null");
		}
		this.pickingPoints = new ArrayList<>();
		for(int index=0;index<pickingPoints.size();index++) {
			addPickingPoint(pickingPoints.get(index));
		}
	}
	
	
	/**
	 * Devuelve toda la lista de pickingPoints
	 * @return list<PackageLocker>
	 */
	public List<PickingPoint> getPickingPoints(){ 
		List<PickingPoint> pickingPointsCopy=new ArrayList<>();
		for(int index=0;index<pickingPoints.size();index++) {
			pickingPointsCopy.add(pickingPoints.get(index).clone());
		}
		return pickingPointsCopy;
	}
	
	
	/** 
	 * Añade un nuevo pickingPoint
	 * @param pickingPoint
	 * @throws NullPointerException si pickingPoint es null
	 * @throws IllegalArgumentException si el punto ya está en la lista
	 * @throws IllegalArgumentException si ya hay un pickingPoint con esa coordenada
	 */
	public void addPickingPoint(PickingPoint pickingPoint) {
		if(pickingPoint==null) {
			throw new NullPointerException("El pickingPoint es null");
		}
		if(pickingPoints.contains(pickingPoint)) {
			throw new IllegalArgumentException("El punto de recogida ya está");
		}
		for(int index=0;index<pickingPoints.size();index++) {
			if(pickingPoint.getGps().equals(pickingPoints.get(index).getGps())) {
				throw new IllegalArgumentException("Ya hay un pickingPoint en esa localización");
			}
		}
		pickingPoints.add(pickingPoint.clone());
	}
	
	
	/**
	 * Elimina un pickingPoint a partir de su id
	 * @param id, int, el id del pickingPoint a eliminar
	 * @throws IllegalArgumentException si el pickignPoint no se encuentra en la lista
	 */
	public void removePickingPoint(int id) {
		boolean found=false;
		for(int index=0;!found && index<pickingPoints.size();index++) {
			if(pickingPoints.get(index).getId()==id) {
				pickingPoints.remove(index);
				found=true;
			}
		}
		if(!found) {
			throw new IllegalArgumentException("No existe el pickingPoint");
		}
	}
	
	
	/**
	 * Lista los pickingPoints operativos
	 * @return list de PickingPoint operativos
	 */
	public List<PickingPoint> listPickingPointOn(){
		List<PickingPoint> pickingPointsOn=new ArrayList<>();
		for(int index=0;index<pickingPoints.size();index++) {
			if(!pickingPoints.get(index).isOutOfService()) {
				pickingPointsOn.add(pickingPoints.get(index).clone());
			}
		} 
		
		return pickingPointsOn;
	}
	
	
	/**
	 * Lista los pickingPoints fuera de servicio
	 * @return list de PickingPoint fuera de servicio
	 */
	public List<PickingPoint> listPickingPointOff(){
		List<PickingPoint> pickingPointsOff=new ArrayList<>();
		
		for(int index=0;index<pickingPoints.size();index++) {
			if(pickingPoints.get(index).isOutOfService()) {
				pickingPointsOff.add(pickingPoints.get(index).clone());
			}
		}
		
		return pickingPointsOff;
	}
	

	/**
	 * Lista los pickingPoint en un radio dado a partir de un packageLocker
	 * @param radio, double, el radio
	 * @param gps, GPSCoordinate, coordenadas que representan el centro de búsqueda
	 * @return list de packageLocker que se encuentran en dicho rango
	 * @throws IllegalArgumentException si el radio de búsqueda es negativo
	 * @throws NullPointerException si gps es null
	 */
	public List<PickingPoint> listPickingPointRadio(double radio, GPSCoordinate gps){
		if(radio<=0) { 
			throw new IllegalArgumentException("El radio de busqueda no puede ser negativo");
		}
		if(gps==null) {
			throw new NullPointerException("Las coordenadas no pueden ser null");
		}
		
		List<PickingPoint> pickingPointRadio=new ArrayList<>();
		for(int index=0;index<pickingPoints.size();index++) {
			if((gps.getDistanceTo(pickingPoints.get(index).getGps()))<=radio) {
				pickingPointRadio.add(pickingPoints.get(index).clone());
			}
		}
		
		return pickingPointRadio;
	}
	
	
	/**
	 * Lista los pickingPoint adecuado a partir de un radio, de una coordenada y un Package
	 * @param packageToSearch, Package, paquete
	 * @param radio, double, radio de búsqueda
	 * @param gps, GPSCoordinate, coordenadas gps que representan el centro de búsqueda
	 * @return lista de pickingPoint en la que se pueden almacenar el paquete dado
	 * @throws NullPointerException si el paquete es null
	 * @throws IllegalArgumentException si el radio no es positivo
	 * @throws NullPointerException si las coordenadas son null
	 */
	public List<PickingPoint> listPickingPointRadioPackage(Package packageToSearch, double radio, GPSCoordinate gps){
		if(packageToSearch==null) {
			throw new NullPointerException("El paquete no puede ser null");
		}
		if(radio<=0) {
			throw new IllegalArgumentException("El radio ha de ser positivo");
		}
		if(gps==null) {
			throw new NullPointerException("Las coordenadas gps no pueden ser null");
		}
		if(packageToSearch.isCertified()) {
			 return listPickingPointWithCertifiedRadio(gps,radio);
		}
		else if(!packageToSearch.isPaid()) {
			return listPickingPointWithCODRadio(gps,radio);
		}
		return listPickingPointRadio(radio,gps);
	}
	
	
	/**
	 * Lista todos los pickingPoint con opción de pagar contra reembolso
	 * @return list<PickingPoint>, lista de pickingPoints que admiten pago a contrareembolso
	 */
	public List<PickingPoint> listPickingPointWithCOD(){
		List<PickingPoint> listPickingPointsCOD=new ArrayList<>();
		
		for(int index=0;index<pickingPoints.size();index++) {
			if(pickingPoints.get(index).hasCOD()) {
				listPickingPointsCOD.add(pickingPoints.get(index).clone());
			}
		}
		
		return listPickingPointsCOD;
	}
	
	
	/**
	 * Lista todos los pickingPoint con opción de entrega certificada
	 * @return list<PickingPoint>, lista de pickingPoints que admiten entrega certificada
	 */
	public List<PickingPoint> listPickingPointWithCertified(){
		List<PickingPoint> listPickingPointsCertified=new ArrayList<>();
		
		for(int index=0;index<pickingPoints.size();index++) {
			if(pickingPoints.get(index).hasCertified()) {
				listPickingPointsCertified.add(pickingPoints.get(index));
			}
		}
		
		return listPickingPointsCertified;
	}
	
	
	/**
	 * Lista de todos los pickingPoint con opción de pagar contra reembolso dado un radio y una coordenada gps
	 * @param gps, GPSCoordinate, coordenadas gps que representan el centro de búsqueda
	 * @param radio, double, radio de búsqueda
	 * @return List<PickingPoint>, lista de pickingPoints con opción de pago a contrareembolso que se encuentran en dicho radio
	 * @throws NullPointerException si las coordenadas son null
	 * @throws IllegalArgumentException si el radio no es positivo
	 */
	public List<PickingPoint> listPickingPointWithCODRadio(GPSCoordinate gps, double radio){
		if(gps==null) {
			throw new NullPointerException("Las coordenadas gps no pueden ser null");
		}
		if(radio<=0) {
			throw new IllegalArgumentException("El radio no puede ser negativo");
		}
		List<PickingPoint> listPickingPointWithCOD=listPickingPointWithCOD();
		List<PickingPoint> listPickingPoint=new ArrayList<>();
		for(int index=0;index<listPickingPointWithCOD.size();index++) {
			if((gps.getDistanceTo(listPickingPointWithCOD.get(index).getGps()))<=radio) {
				listPickingPoint.add(listPickingPointWithCOD.get(index));
			}
		}
		
		return listPickingPoint;
	}
	
	
	/**
	 * Lista todos los pickingPoint con opción de entrega certificada dado un radio y una coordenada gps
	 * @param gps, GPSCoordinate, coordenadas gps que representan el centro de búsqueda
	 * @param radio, double, radio de búsqueda
	 * @return List<PickingPoint>, lista de pickingPoint con opción de entrega certificada que se encuentran en dicho radio
	 * @throws NullPointerException si las coordenadas son null
	 * @throws IllegalArgumentException si el radio no es positivo
	 */
	public List<PickingPoint> listPickingPointWithCertifiedRadio(GPSCoordinate gps, double radio){
		if(gps==null) {
			throw new NullPointerException("Las coordenadas gps no pueden ser null");
		}
		if(radio<=0) {
			throw new IllegalArgumentException("El radio ha de ser positivo");
		}
		List<PickingPoint> listPickingPointWithCertified=listPickingPointWithCertified();
		
		List<PickingPoint> listPickingPoint=new ArrayList<>();
		for(int index=0;index<listPickingPointWithCertified.size();index++) {
			
			if((gps.getDistanceTo(listPickingPointWithCertified.get(index).getGps()))<=radio) {
				listPickingPoint.add(listPickingPointWithCertified.get(index));
			}
		}
		return listPickingPoint;
	}
	
	
	/**
	 * Consulta el pickingPoint más cercano dadas unas coordenadas
	 * @param gps, GPSCoordinate, coordenadas de búsqueda
	 * @return pickingPoint, el pickingPoint más cercano a gps
	 * @throws NullPointerException, si gps es null
	 * @throws IllegalArgumentException si no hay ningún pickingPoint en la lista
	 */
	public PickingPoint getNearestPickingPoint(GPSCoordinate gps) {
		if(gps==null) {
			throw new NullPointerException("Las coordenadas no pueden ser null");
		}
		if(pickingPoints.size()==0) {
			throw new IllegalArgumentException("No hay ningun PickingPoint");
		}
		
		PickingPoint nearest=pickingPoints.get(0);
		for(int index=0;index<pickingPoints.size();index++) {
			if(nearest.getGps().getDistanceTo(gps)>pickingPoints.get(index).getGps().getDistanceTo(gps)) {
				nearest=pickingPoints.get(index);
			}
		}
		
		return nearest.clone();
	}
	
	
	/**
	 * Consulta el pickingPoint más cercano con opción de pago a contrareembolso
	 * @param gps, GPSCoordinate, coordenadas gps
	 * @return pickingPoint, el más cercano
	 * @throws NullPointerException si gps es null
	 * @throws IllegalArgumentException si no hay ningún pickingPoint con opción de COD
	 */
	public PickingPoint getNearestWithCOD(GPSCoordinate gps) {
		if(gps==null) {
			throw new NullPointerException("Las coordenadas no pueden ser null");
		}
		List<PickingPoint> listPickingPointWithCOD=listPickingPointWithCOD();
		if(listPickingPointWithCOD.size()==0) {
			throw new IllegalArgumentException("No hay ningun pickingPoint con COD");
		}
		
		PickingPoint nearest=listPickingPointWithCOD.get(0);
		for(int index=1;index<listPickingPointWithCOD.size();index++) {
			if(nearest.getGps().getDistanceTo(gps)>listPickingPointWithCOD.get(index).getGps().getDistanceTo(gps)) {
				nearest=listPickingPointWithCOD.get(index);
			}
		}
		
		return nearest;
	}
	
	
	/**
	 * Consulta el pickingPoint más cercano con entrega certificada
	 * @param gps, GPSCoordinate, coordenadas gps que representan el centro de búsqueda
	 * @return PickingPoint, pickingPoint con entrega certificada más cercano 
	 * @throws NullPointerException si gps es null
	 * @throws IllegalArgumentException si no hay ningún pickingPoint con opción de entrega certificada
	 */
	public PickingPoint getNearestWithCertified(GPSCoordinate gps) {
		if(gps==null) {
			throw new NullPointerException("Las coordenadas no pueden ser null");
		}
		List<PickingPoint> listPickingPointWithCertified=listPickingPointWithCertified();
		if(listPickingPointWithCertified.size()==0) {
			throw new IllegalArgumentException("No hay ningun pickingPoint con entrega certificada");
		}
		PickingPoint nearest=listPickingPointWithCertified.get(0);
		for(int index=1;index<listPickingPointWithCertified.size();index++) {
			if(nearest.getGps().getDistanceTo(gps)>listPickingPointWithCertified.get(index).getGps().getDistanceTo(gps)) {
				nearest=listPickingPointWithCertified.get(index);
			}
		}
		
		return nearest;
	}
	
	
	/**
	 * Lista los pickingPoints con alguna taquilla vacía
	 * @return list de packageLocker que tienen alguna taquilla vacía
	 */
	public List<PickingPoint> listPickingPointFreeSpace(){
		List<PickingPoint> pickingPointFreeSpace=new ArrayList<>();
		
		for(int index=0;index<pickingPoints.size();index++) {
			if(!pickingPoints.get(index).isFull()) {
				pickingPointFreeSpace.add(pickingPoints.get(index).clone());
			}
		} 
		
		return pickingPointFreeSpace;
	} 
	
	
	/**
	 * Consulta la longitud de pickingpointsystem
	 * @return int (entero mayor o igual que cero)
	 */
	public int getNumberPickingPoints() {
		return pickingPoints.size();
	}
	
	
	@Override
	public String toString() {
		String s="";
		
		for(int index=0;index<pickingPoints.size();index++) {
			s+="\n"+pickingPoints.get(index);
		}
		
		return s;
	}
	
	
	@Override
	public boolean equals(Object o) {
		if(this==o) {
			return true;
		}

		PickingPointSystem pickingPointsSystem=(PickingPointSystem) o;
		if(pickingPointsSystem.getNumberPickingPoints()!=getNumberPickingPoints()) {
			return false;
		}
		
		for( PickingPoint p : getPickingPoints()) {
			if( !pickingPointsSystem.getPickingPoints().contains(p)) {
				return false;
			}
		}
		return true;
	}
	
}
