/**
 * La clase PickingPointHub representa un concentrador de gran superficie, compuesto por diferentes PackageLockers 
 * y/o Kiosks. Ha de tener, como mínimo, dos puntos de recogida. Además, todos los puntos que componen dicho 
 * concentrador han de tener las mismas coordenadas gps.
 * 
 * PickingPointHub tendrá pago contrareembolso si alguno de los puntos de recogida que lo contienen tienen este
 * método de pago. Análogamente, un paquete se podrá almacenar si hay espacio para dicho paquete en algún punto
 * de recogida. 
 * 
 * En ninguna circunstancia es posible almacenar y, en consecuencia, entregar, paquetes certificados, ya
 * que los puntos que conforman el concentrador no disponen de dicha opción.
 * 
 * @author jimenarioja
 */

package es.uva.inf.poo.amazingco;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import es.uva.inf.poo.maps.GPSCoordinate;

public class PickingPointHub extends PickingPoint implements Cloneable{

	private GroupablePoint groupablePoints[];
	private int maxCapacityGroupablePoint;
	private double maxCapacityCubicMetres;
	
	/**
	 * Constructor de PickingPointHub
	 * @param gps, GPSCoordinate, coordenadas gps
	 * @param openingHour, LocalTime, horario de apertura
	 * @param closingHour, LocalTime, horario de cierre
	 * @param groupablePoints, GroupablePoint[], conjunto de puntos de recogida
	 * @param maxCapacity, int, capacidad máxima de puntos de recogida
	 * @throws NullPointerException si gps es null
	 * @throws NullPointerException si el horario de apertura es null
	 * @throws IllegalArgumentException si el horario de apertura es igual o más tarde que el de cierre
	 * @throws NullPointerException si closingHour es null
	 * @throws IllegalArgumentException si la capacidad máxima es inferior a 2
	 * @throws IllegalArgumentException si no hay como mínimo 2 groupablePoints
	 * @throws IllegalArgumentException si se supera la capacidad máxima de groupablePoints
	 * @throws IllegalArgumentException si los groupablePoints no tienen las mismas coordenadas que pickingPointHub
	 * @throws IllegalArgumentException si se supera los metros cúbicos disponibles
	 */
	public PickingPointHub(GPSCoordinate gps, LocalTime openingHour, LocalTime closingHour, 
			GroupablePoint groupablePoints[], int maxCapacityGroupablePoint, double cubicMetres) {
		super(gps,openingHour,closingHour);
		if(maxCapacityGroupablePoint<2) {
			throw new IllegalArgumentException("Tiene que haber como mínimo 2 groupablePoints");
		}
		if(groupablePoints.length<2) {
			throw new IllegalArgumentException("Son necesarios como minimo 2");
		}
		if(groupablePoints.length>maxCapacityGroupablePoint) {
			throw new IllegalArgumentException("Supera la capacidad maxima");
		}
		double cubicMetresGroupablePoints=0;
		for(int i=0;i<groupablePoints.length;i++) {
			if(!getGps().equals(groupablePoints[i].getGps())) {
				throw new IllegalArgumentException("No tienen las mismas coordenadas");
			}
			cubicMetresGroupablePoints+=groupablePoints[i].getMaxCapacityCubicMetres();
		}
		if(cubicMetresGroupablePoints>cubicMetres) {
			
			throw new IllegalArgumentException("Se supera los metros cubicos disponibles");
		}
		setCubicMetres(cubicMetres);
		setMaxCapacityGroupablePoint(maxCapacityGroupablePoint);
		this.groupablePoints=new GroupablePoint[maxCapacityGroupablePoint];
		for(int i=0;i<groupablePoints.length;i++) {
			this.groupablePoints[i]=groupablePoints[i].clone();
		}
	}
	
	
	/**
	 * Constructor copia de pickingPointHub
	 * @param pickingPointHub, PickingPointHub
	 */
	public PickingPointHub(PickingPointHub pickingPointHub) {
		this(pickingPointHub.getGps(), pickingPointHub.getOpeningHour(),
				pickingPointHub.getClosingHour(), pickingPointHub.getGroupablePoints(),
				pickingPointHub.getMaxCapacityGroupablePoint(),pickingPointHub.getCubicMetres());
		setId(pickingPointHub.getId());
	}
	
	
	/**
	 * Modifica el tamaño máximo respecto a metros cúbicos de almacenaje
	 * @param maxCapacityCubicMetres, double, capacidad máxima
	 * @throws IllegalArgumentException si maxCapacityCubicMetres no es positivo
	 */
	public void setCubicMetres(double maxCapacityCubicMetres) {
		if(maxCapacityCubicMetres<=0) {
			throw new IllegalArgumentException("Los metros cubicos han de ser positivos");
		}
		this.maxCapacityCubicMetres=maxCapacityCubicMetres;
	}
	
	
	/**
	 * Modifica la capacidad máxima de groupablePoints que pueden formar parte de este establecimiento
	 * @param maxCapacity, int, capacidad máxima de groupablePoints
	 * @throws IllegalArgumentException si la capacidad máxima es inferior a 2
	 */
	public void setMaxCapacityGroupablePoint(int maxCapacityGroupablePoint) {
		if(maxCapacityGroupablePoint<2) {
			throw new IllegalArgumentException("La capacidad maxima ha de ser como minimo 2 puntos");
		}
		this.maxCapacityGroupablePoint=maxCapacityGroupablePoint;
	}
	
	
	@Override
	public boolean isOutOfService() {
		for(int i=0;i<groupablePoints.length;i++) {
			if(groupablePoints[i]!=null && !groupablePoints[i].isOutOfService()) {
				return false;
			}
		}
		return true;
	}
	
	
	@Override
	public void outOfService() {
		for(int i=0;i<groupablePoints.length;i++) {
			if(groupablePoints[i]!=null) {
				groupablePoints[i].outOfService();
			}
			
		}
	}
	
	
	/**
	 * Modifica todos los establecimientos a operativos
	 */   
	public void setAllNotOutOfService() {
		for(int i=0;i<groupablePoints.length;i++) {
			if(groupablePoints[i]!=null) {
				groupablePoints[i].notOutOfService();
			}
		}
	}
	
	
	/**
	 * Consulta la capacidad máxima de metros cúbicos
	 * @return maxCapacityCubicMetres, double, siempre positivo
	 */
	public double getCubicMetres() {
		return maxCapacityCubicMetres;
	}
	
	
	/**
	 * Consulta la capacidad libre en metros cúbicos
	 * @return double, capacidad libre, siempre mayor o igual que 0
	 */
	public double getEmptyCubicMetres() {
		return getCubicMetres()-getCubicMetresUsed();
	}
	
	
	/**
	 * Consulta la capacidad usada en metros cúbicos
	 * @return double, capacidad usada, siempre mayor o igual que 0
	 */
	public double getCubicMetresUsed() {
		double cubicMetres=0;
		
		for(int i=0;i<groupablePoints.length;i++) {
			if(groupablePoints[i]!=null) {
				cubicMetres+=groupablePoints[i].getMaxCapacityCubicMetres();
			}
		}
		
		return cubicMetres;
	}
	
	
	/**
	 * Consulta la capacidad máxima (siempre mayor o igual que 2)
	 * @return maxCapacityGroupablePoint, int, capacidad máxima de almacenaje respecto a groupablePoint
	 */
	public int getMaxCapacityGroupablePoint() {
		return maxCapacityGroupablePoint;
	}
	
	
	/**
	 * Consulta cuántos groupablePoints están llenos
	 * @return int, número de groupablePoints llenos, siempre mayor o igual que cero
	 */
	public boolean isFullGroupablePoints() {
		return getNumPoints()==getMaxCapacityGroupablePoint();
	}
	
	
	/**
	 * Añade un nuevo groupablePoint a este PickingPointHub
	 * @param groupablePoint, punto de recogida a añadir
	 * @throws NullPointerException si groupablePoint es null
	 * @throws IllegalArgumentException si no hay espacio para añadir este groupablePoint
	 * @throws IllegalArgumentException si el punto no tiene las mismas coordenadas que PickingPointHub
	 * @throws IllegalArgumentException si el punto ya se encuentra en la lista
	 */
	public void addGroupablePoint(GroupablePoint groupablePoint) {
		if(groupablePoint==null) {
			throw new NullPointerException("El punto no puede ser null");
		}
		if(isFullGroupablePoints() || getEmptyCubicMetres()<=groupablePoint.getMaxCapacityCubicMetres()) {
			throw new IllegalArgumentException("No hay espacio para este groupablePoint");
		}
		if(!groupablePoint.getGps().equals(getGps())) {
			throw new IllegalArgumentException("El punto no se encuentra en la localizacion correcta");
		}
		if(containsGroupablePoint(groupablePoint)) {
			throw new IllegalArgumentException("El punto ya se encuentra en la lista");
		}
		boolean saved=false;
		for(int i=0;i<getMaxCapacityGroupablePoint() && !saved;i++) {
			if(groupablePoints[i]==null) {
				groupablePoints[i]=groupablePoint.clone();
				saved=true;
			}
		}
	}
	
	
	/**
	 * Elimina un groupablePoint a partir de su id
	 * @param id, int, identificador del groupablePoint
	 * @throws IllegalArgumentException si al eliminarlo quedasen menos de 2 groupablePoints
	 * @throws IllegalArgumentException si no hay ningún groupablePoint con ese id
	 */
	public void removeGroupablePoint(int id) {
		if(getNumPoints()==2) {
			throw new IllegalArgumentException("No es posible eliminar el punto de recogida. Tiene que haber como minimo 2");
		}
		boolean found=false;
		for(int i=0;i<groupablePoints.length && !found;i++) {
			
			if(groupablePoints[i]!=null && groupablePoints[i].getId()==id) {
				groupablePoints[i]=null;
				found=true;
			}
		}
		if(!found) {
			throw new IllegalArgumentException("No hay ningun groupablePoint con ese id");
		}
	}
	
	
	/**
	 * Consulta los groupablePoints que forman el pickingPointHub
	 * @return GroupablePoint[], conjunto de groupablePoint, (como mínimo 2)
	 */
	public GroupablePoint[] getGroupablePoints() {
		GroupablePoint groupablePointsCopy[]=new GroupablePoint[getNumPoints()];
		int j=0;
		for(int i=0;i<getMaxCapacityGroupablePoint();i++) {
			if(groupablePoints[i]!=null) {
				groupablePointsCopy[j]=groupablePoints[i].clone();
				j++;
			}
		}
		
		return groupablePointsCopy;
	}
	
	
	/**
	 * Consulta el groupablePoint a partir de su id
	 * @param id, int, identificador del groupablePoint
	 * @return GroupablePoint, groupablePoint con dicho id
	 * @throws IllegalArgumentException si no hay ningún groupablePoint con ese id
	 */
	public GroupablePoint getGroupablePoint(int id) {
		for(int i=0;i<groupablePoints.length;i++) {
			if(groupablePoints[i]!=null && groupablePoints[i].getId()==id) {
				return groupablePoints[i].clone();
			}
		}
		throw new IllegalArgumentException("No hay ningun groupablePoint con ese id");
	}
	
	
	/**
	 * Consulta si un punto de recogida ya está en este PickingPointHub
	 * @param groupablePoint, punto a buscar
	 * @return boolean, true si ya está, false en caso contrario
	 * @throws NullPointerException si groupablePoint es null
	 */
	public boolean containsGroupablePoint(GroupablePoint groupablePoint) {
		if(groupablePoint==null) {
			throw new NullPointerException("El establecimiento no puede ser null");
		}
		
		for(int i=0;i<groupablePoints.length;i++) {
			if(groupablePoints[i]!=null && groupablePoints[i].equals(groupablePoint)) {
				return true;
			}
		}
		
		return false;
	}
	
	
	@Override
	public boolean isFull() {
		for(int i=0;i<groupablePoints.length;i++) {
			if(!groupablePoints[i].isFull()) {
				return false;
			}
		}
		
		return true;
	}
	
	
	/**
	 * Consulta el número de puntos de recogida almacenados
	 * @return int, número de groupablePoints
	 */
	public int getNumPoints() {
		int points=0;
		
		for(int i=0;i<groupablePoints.length;i++) {
			if(groupablePoints[i]!=null) {
				points++;
			}
		}
		
		return points;
	}
	
	
	/**
	 * Consulta los groupablePoints que tienen espacio libre
	 * @return GroupablePoint[], conjunto de groupablePoints con espacio libre
	 * @throws IllegalArgumentException si no hay groupablePoints con espacio libre
	 */
	public GroupablePoint[] getPointsNotFull() {
		if(getNumPointsNotFull()==0) {
			throw new IllegalArgumentException("No hay groupablePoints con espacio libre");
		}
		
		GroupablePoint points[]=new GroupablePoint[getNumPointsNotFull()];
		int j=0;
		
		for(int i=0;i<groupablePoints.length;i++) {
			if(groupablePoints[i]!=null && !groupablePoints[i].isFull()) { 
				points[j]=groupablePoints[i].clone();
				j++;
			}
		}
		
		return points;
	}
	
	
	/**
	 * Consulta el número de puntos de recogida aptos para pago a contrareembolso
	 * @return int, número de puntos con pago a contrareembolso
	 */
	public int getNumPointsWithCOD() {
		int points=0;
		
		for(int i=0;i<groupablePoints.length;i++) {
			if(groupablePoints[i]!=null && groupablePoints[i].hasCOD()) {
				points++;
			}
		}
		
		return points;
	}
	
	
	/**
	 * Consulta los puntos de recogida con posibilidad de pago a contrareembolso
	 * @return conjunto de GroupablePoint, aquellos con la posibilidad de pago a contrareembolso
	 * @throws IllegalArgumentException si no hay ningún groupablePoint con pago a contrareembolso
	 */
	public GroupablePoint[] getPointsWithCOD() {
		if(getNumPointsWithCOD()==0) {
			throw new IllegalArgumentException("No hay ningun punto apto para pago a contrareembolso");
		}
		GroupablePoint pointsWithCOD[]=new GroupablePoint[getNumPointsWithCOD()];
		int j=0;
		
		for(int i=0;i<groupablePoints.length;i++) {
			if(groupablePoints[i]!=null && groupablePoints[i].hasCOD()) { 
				pointsWithCOD[j]=groupablePoints[i].clone();
				j++;
			}
		}
		
		return pointsWithCOD;
	}
	
	
	/**
	 * Consulta el numero de puntos de recogida operativos y con espacio aptos para pago a contrareembolso
	 * @return int, número de groupablePoints aptos para pago a contrareembolso con espacio libre (siempre mayor o igual que cero)
	 */
	public int getNumPointsEmptySpaceWithCOD() {
		int points=0;
		
		for(int i=0;i<groupablePoints.length;i++) {
			if(groupablePoints[i]!=null && groupablePoints[i].hasCOD() && !groupablePoints[i].isFull()) {
				points++;
			}
		}
		
		return points;
	}
	
	
	/**
	 * Consulta los puntos con opción de pago COD que tengan espacio disponible
	 * @return conjunto de groupablePoint, aquellos con pago a contrareembolso y espacio disponible
	 * @throws IllegalArgumentException si no hay ningún groupablePoint con pago a contrareembolso
	 * @throws IllegalArgumentException si no hay ningún groupablePoint con pago a contrareembolso con espacio libre
	 */
	public GroupablePoint[] getPointsEmptySpaceWithCOD() {
		GroupablePoint pointsWithCOD[]=getPointsWithCOD();
		if(getNumPointsEmptySpaceWithCOD()==0) {
			throw new IllegalArgumentException("No hay ningun groupablePoint con pago a contrareembolso con espacio libre");
		}
		
		GroupablePoint pointsEmptySpace[]=new GroupablePoint[getNumPointsEmptySpaceWithCOD()];
		int j=0;
		
		for(int i=0;i<pointsWithCOD.length;i++) {
			if(!pointsWithCOD[i].isFull()) { 
				pointsEmptySpace[j]=pointsWithCOD[i].clone();
				j++;
			}
		}
		
		return pointsEmptySpace;
	}
	
	
	/**
	 * Consulta el número de huecos vacíos para puntos de recogida
	 * @return int, número de huecos vacíos (siempre mayor o igual que cero)
	 */
	public int getEmptyCapacityGroupablePoints() {
		return getMaxCapacityGroupablePoint()-getNumPoints();
	}
	
	
	/**
	 * Consulta el número de puntos de recogida que no están llenos
	 * @return pointsNotFull, int, número de puntos de recogida que no están llenos (siempre mayor o igual que 0)
	 */
	public int getNumPointsNotFull() {
		int pointsNotFull=0;
		
		for(int i=0;i<groupablePoints.length;i++) {
			if(groupablePoints[i]!=null && !groupablePoints[i].isFull()) {
				pointsNotFull++;
			}
		}
		
		return pointsNotFull;
	}
	
	
	/**
	 * Consulta el número de puntos de recogida que no están llenos
	 * @return pointsNotFull, int, número de puntos de recogida que no están llenos (siempre mayor o igual que 0)
	 */
	public int getNumPointsNotFullAndNotOutOfService() {
		int points=0;
		
		for(int i=0;i<groupablePoints.length;i++) {
			if(groupablePoints[i]!=null && !groupablePoints[i].isFull() && !groupablePoints[i].isOutOfService()) {
				points++;
			}
		}
		
		return points;
	}
	
	
	/**
	 * Consulta el número de puntos de recogida que están llenos
	 * @return int, número de puntos de recogida que están llenos (siempre mayor o igual que 0)
	 */
	public int getNumPointsFull() {
		return getMaxCapacityGroupablePoint()-getNumPointsNotFull();
	}
	
	
	/**
	 * Consulta los groupablePoints con espacio disponible para almacenar paquetes
	 * @return pointsEmptySpace[], puntos con espacio disponible
	 */
	public GroupablePoint[] getPointsEmptySpaceAndNotOutOfService() {
		GroupablePoint pointsEmptySpace[]=new GroupablePoint[getNumPointsNotFullAndNotOutOfService()];
		int j=0;
		
		for(int i=0;i<groupablePoints.length;i++) {
			if(groupablePoints[i]!=null && !groupablePoints[i].isOutOfService() && !groupablePoints[i].isFull()) {
				pointsEmptySpace[j]=groupablePoints[i].clone();
			}
		}
		
		return pointsEmptySpace;
	}
	
	
	/**
	 * Aumenta la capacidad máxima de almacenaje
	 * @param gap, capacidad a aumentar
	 * @throws IllegalArgumentException si la cantidad a aumentar no es positiva
	 */
	public void expandCapacity(int gap) {
		if(gap<0) {
			throw new IllegalArgumentException("La cantidad para aumentar ha de ser positiva");
		}
		
		setMaxCapacityGroupablePoint(getMaxCapacityGroupablePoint()+gap);
		GroupablePoint auxGroupablePoints[]=this.groupablePoints;
		this.groupablePoints=new GroupablePoint[getMaxCapacityGroupablePoint()];
		
		for(int i=0;i<auxGroupablePoints.length;i++) {
			groupablePoints[i]=auxGroupablePoints[i];
		}
		
		setMaxCapacityGroupablePoint(groupablePoints.length);
	}
	
	
	/**
	 * Reduce la capacidad máxima de almacenaje
	 * @param gap, capacidad a reducir
	 * @throws IllegalArgumentException si la cantidad a reducir no es positiva
	 * @throws IllegalArgumentException si la nueva capacidad máxima es menor que el número de puntos almacenados
	 */
	public void reduceCapacity(int gap) {
		if(gap<=0) {
			throw new IllegalArgumentException("El numero ha reducir tiene que ser positivo");
		}
		if(getMaxCapacityGroupablePoint()-gap<getNumPoints()) {
			throw new IllegalArgumentException("La capacidad no se puede reducir porque hay mas puntos almacenados");
		}
		
		GroupablePoint auxGroupablePoints[]=this.groupablePoints;
		this.groupablePoints=new GroupablePoint[getMaxCapacityGroupablePoint()-gap];
		
		for(int i=0;i<auxGroupablePoints.length;i++) {
			if(auxGroupablePoints[i]!=null) {
				groupablePoints[i]=auxGroupablePoints[i];
			}
		}
		
		setMaxCapacityGroupablePoint(groupablePoints.length);
	}
	
	
	/**
	 * Consulta el número de groupablePoints que están fuera de servicio
	 * @return int, número de groupablePoints que están fuera de servicio, siempre mayor o igual que cero
	 */
	public int getNumOutOfService() {
		int outOfService=0;
		
		for(int i=0;i<groupablePoints.length;i++) {
			if(groupablePoints[i]!=null && groupablePoints[i].isOutOfService()) {
				outOfService++;
			}
		}
		
		return outOfService;
	}
	
	
	/**
	 * Consulta el número de groupablePoints que están operativos
	 * @return int, número de groupablePoints que están operativos, siempre mayor o igual que 0
	 */
	public int getNumNotOutOfService() {
		return getNumPoints()-getNumOutOfService();
	}
	
	
	/**
	 * Consulta si el PickingPointHub se puede elegir para cobros COD
	 * @return boolean, true si se puede pagar a COD, false en caso contrario
	 */
	@Override
	public boolean hasCOD() {
		boolean suitable=false;
		
		for(int i=0;i<getMaxCapacityGroupablePoint() && !suitable;i++) {
			if(groupablePoints[i]!=null && groupablePoints[i].hasCOD()) {
				suitable=true;
			}
		}
		
		return suitable;
	}
	
	
	@Override
	public boolean canStorePackage(Package packageToStore) {
		if(packageToStore.isCertified()) {
			return false;
		}
		if(!packageToStore.isPaid()) {
			if(getNumPointsEmptySpaceWithCOD()==0) {
				return false;
			}
			GroupablePoint groupablePoints[]=getPointsEmptySpaceWithCOD(); 
			for(int i=0;i<getNumPointsEmptySpaceWithCOD();i++) {
				if(groupablePoints[i].canStorePackage(packageToStore)) {
					return true;
				}
			}
			return false;
		}
		GroupablePoint groupablePoints[]=getPointsNotFull();
		
		for(int i=0;i<getNumPointsNotFull();i++) {
			if(groupablePoints[i].canStorePackage(packageToStore)) {
				return true;
			}
		}
		
		return false;
	}
	

	@Override
	public void addPackage(Package packageToStore) {
		checkToAddPackage(packageToStore);
		boolean added=false;
		if(!packageToStore.isPaid()) { 
			for(int i=0;i<groupablePoints.length && !added;i++) {
				if(groupablePoints[i]!=null && !groupablePoints[i].isFull() && groupablePoints[i].hasCOD()) { 
					groupablePoints[i].addPackage(packageToStore);
					added=true;
				}
			}
		}
		else {
			for(int i=0;i<groupablePoints.length && !added;i++) {
				if(groupablePoints[i]!=null && !groupablePoints[i].isFull()) { 
					groupablePoints[i].addPackage(packageToStore);
					added=true;
				}
			} 
		}
		
		packageToStore.stored();
	}
	
	
	

	/**
	 * Consulta el groupablePoint donde se encuentra un paquete a partir de su id
	 * @param id, String, identificador del paquete
	 * @return GroupablePoint, groupablePoint donde está almacenado el paquete
	 * @throws NullPointerException si id es null
	 * @throws IllegalArgumentException si id es una cadena vacía
	 * @throws IllegalArgumentException si no hay ningún groupablePoint que contenga un paquete con ese id
	 */
	public GroupablePoint getGroupablePoint(String id) {
		if(id==null) {
			throw new NullPointerException("El id del paquete no puede ser null");
		}
		if(id.isEmpty()) {
			throw new IllegalArgumentException("El id no puede ser una cadena vacia");
		}
		boolean found=false;
		GroupablePoint groupablePoint=null;
		
		for(int i=0;i<groupablePoints.length && !found;i++) {
			if(groupablePoints[i]!=null && groupablePoints[i].contains(id)) {
				found=true;
				groupablePoint=groupablePoints[i].clone();
			}
		}
		
		if(!found) {
			throw new IllegalArgumentException("No hay ningun groupablePoint con ese id");
		}
		
		return groupablePoint;
	}
	

	@Override
	public void takeOutPackageToReturn(Package packageToReturn) {
		checkToTakeOutPackageToReturn(packageToReturn);
		GroupablePoint groupablePoint=getGroupablePoint(packageToReturn.getId());
		boolean found=false;
		
		for(int i=0;i<groupablePoint.getMaxCapacityBox() && !found;i++) {
			if(packageToReturn.equals(groupablePoints[i].getPackage(i))) {
				packageToReturn.returnPackage();
				packageToReturn.notStored();
				groupablePoints[i]=null;
				found=true;
			}
		}
		
		if(!found) {
			throw new IllegalArgumentException("El paquete no se encuentra en este establecimiento");
		}
	}
	
	
	@Override
	public Package takeOutPackageAsCustomer(String id, LocalDate pickUpDate, LocalTime pickUpHour) {
		Package packageToPickUp=super.takeOutPackageAsCustomer(id, pickUpDate, pickUpHour);
		GroupablePoint groupablePoint=getGroupablePoint(packageToPickUp.getId());
		boolean found=false;
		
		for(int i=0;i<groupablePoint.getMaxCapacityBox() && !found;i++) {
			if(packageToPickUp.equals(groupablePoints[i].getPackage(i))) {
				groupablePoints[i]=null;
				found=true;
			}
		}
		
		if(!found) {
			throw new IllegalArgumentException("El paquete no se encuentra en este establecimiento");
		}
		
		return packageToPickUp;
	}
	
	
	@Override
	public boolean hasCertified() {
		return false;
	}
	
	
	@Override
	public Package getPackage(String id) {
		GroupablePoint groupablePoint=getGroupablePoint(id);
		return groupablePoint.getPackage(id);
	}	
	

	/**
	 * Consulta el número de paquetes que hay en total en el establecimiento
	 * @return numberOfPackages, int, número de paquetes, siempre mayor o igual que cero
	 */
	public int getNumPackages() {
		int numPackages=0;
		
		for(int i=0;i<groupablePoints.length;i++) {
			if(groupablePoints[i]!=null) {
				numPackages+=groupablePoints[i].getNumberOfPackagesStored();
			}
		}
		return numPackages;
	}
	
	
	@Override
	public List<Package> getPackages() {
		List<Package> packages=new ArrayList<>();
		
		for(int i=0;i<groupablePoints.length;i++) {
			if(groupablePoints[i]!=null) {
				for(int j=0;j<groupablePoints[i].getNumberOfPackagesStored();j++) {
					if(groupablePoints[i].getPackage(j)!=null) {
						packages.add(groupablePoints[i].getPackage(j));
					}
				}
			}
		}
		
		return packages;
	}
	
	
	@Override
	public String toString() {
		String s="";
		
		s=super.toString();
		s+="Suitable for COD: "+hasCOD();
		s+="Suitable for certified: "+hasCertified();
		s+="Capacidad maxima de GroupablePoints: "+getMaxCapacityGroupablePoint();
		s+="\nCapacidad maxima en metros cubicos: "+getCubicMetres();
		
		for(int i=0;i<groupablePoints.length;i++) {
			s+="\n"+groupablePoints[i];
		}
		
		return s;
	}
	

	@Override
	public PickingPointHub clone() {
		return new PickingPointHub(this);
	}
}
