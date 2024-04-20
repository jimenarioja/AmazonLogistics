package es.uva.inf.poo.amazingco;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import es.uva.inf.poo.maps.GPSCoordinate;

public class PickingPointSystemTest {

	@Test
	public void testConstructorDefaultPickingPointSystem() {
		PickingPointSystem pickingPointSystem=new PickingPointSystem();
		assertNotNull(pickingPointSystem);
	}
	 
	@Test 
	public void testConstructorPickingPointSystem() {
		int numLocker1=3;
		int numLocker2=7;
		Locker locker1=new Locker(numLocker1);
		Locker locker2=new Locker(numLocker2);
		Locker lockers[]= {locker1,locker2};
		
		GPSCoordinate gps1=new GPSCoordinate(60,120);
		GPSCoordinate gps2=new GPSCoordinate(54,130);
		GPSCoordinate gps3=new GPSCoordinate(79,120);
		GPSCoordinate gps4=new GPSCoordinate(85,45);
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20, 40);
		double m3=20000;
		
		PickingPoint pickingPoint1=new PackageLocker(gps1,openingHour1,closingHour1,lockers,m3);
		PickingPoint pickingPoint2=new PackageLocker(gps2,openingHour1,closingHour1,lockers,m3);
		PickingPoint pickingPoint3=new PackageLocker(gps3,openingHour1,closingHour1,lockers,m3);
		PickingPoint pickingPoint4=new PackageLocker(gps4,openingHour1,closingHour1,lockers,m3);
		
		List<PickingPoint> pickingPoints=new ArrayList<>();
		pickingPoints.add(pickingPoint1);
		pickingPoints.add(pickingPoint2);
		pickingPoints.add(pickingPoint3);
		pickingPoints.add(pickingPoint4);
		
		PickingPointSystem pickingPointSystem=new PickingPointSystem(pickingPoints);
		assertNotNull(pickingPointSystem);
		assertNotSame(pickingPoints,pickingPointSystem.getPickingPoints());
		assertEquals(pickingPoints,pickingPointSystem.getPickingPoints());
	}
	
	@Test(expected=NullPointerException.class)
	public void testConstructorPickingPointSystemNull() {
		PickingPointSystem pickingPoint=null;
		@SuppressWarnings("unused")
		PickingPointSystem pickingPointSystem=new PickingPointSystem(pickingPoint);
	}

	@Test(expected=NullPointerException.class)
	public void testConstructorPickingPointSystemSomePickingPointNull() {
		int numLocker1=3;
		int numLocker2=7;
		Locker locker1=new Locker(numLocker1);
		Locker locker2=new Locker(numLocker2);
		Locker lockers[]= {locker1,locker2};
		
		GPSCoordinate gps1=new GPSCoordinate(60,120);
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20, 40);
		double m3=20000000;
		
		PickingPoint pickingPoint1=new PackageLocker(gps1,openingHour1,closingHour1,lockers,m3);
		PickingPoint pickingPoint2=null;
		List<PickingPoint> pickingPoints=new ArrayList<>();
		pickingPoints.add(pickingPoint1);
		pickingPoints.add(pickingPoint2);
		@SuppressWarnings("unused")
		PickingPointSystem pickingPointSystem=new PickingPointSystem(pickingPoints);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorPickingPointSystemSomePickingPointRepeat() {
		int numLocker1=3;
		int numLocker2=7;
		Locker locker1=new Locker(numLocker1);
		Locker locker2=new Locker(numLocker2);
		Locker lockers[]= {locker1,locker2};
		
		GPSCoordinate gps1=new GPSCoordinate(60,120);
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20,40);
		double m31=20000;
		
		
		
		PickingPoint pickingPoint1=new PackageLocker(gps1,openingHour1,closingHour1,lockers,m31);
		
		List<PickingPoint> pickingPoints=new ArrayList<>();
		pickingPoints.add(pickingPoint1);
		pickingPoints.add(pickingPoint1);
		@SuppressWarnings("unused")
		PickingPointSystem pickingPointSystem=new PickingPointSystem(pickingPoints);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorPickingPointSystemSameGPS() {
		int numLocker1=3;
		int numLocker2=7;
		Locker locker1=new Locker(numLocker1);
		Locker locker2=new Locker(numLocker2);
		Locker lockers[]= {locker1,locker2};
		
		GPSCoordinate gps1=new GPSCoordinate(60,120);
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20,40);
		double m31=20000;
		
		GPSCoordinate gps2=new GPSCoordinate(60,120);
		LocalTime openingHour2=LocalTime.of(8,00);
		LocalTime closingHour2=LocalTime.of(22,30);
		double m32=100000;
		
		PickingPoint pickingPoint1=new PackageLocker(gps1,openingHour1,closingHour1,lockers,m31);
		PickingPoint pickingPoint2=new PackageLocker(gps2,openingHour2,closingHour2,lockers,m32);
		List<PickingPoint> pickingPoints=new ArrayList<>();
		pickingPoints.add(pickingPoint1);
		pickingPoints.add(pickingPoint2);
		@SuppressWarnings("unused")
		PickingPointSystem pickingPointSystem=new PickingPointSystem(pickingPoints);
	}
	
	@Test
	public void testConstructorCopia() {
		int numLocker1=3;
		int numLocker2=7;
		Locker locker1=new Locker(numLocker1);
		Locker locker2=new Locker(numLocker2);
		Locker lockers[]= {locker1,locker2};
		
		GPSCoordinate gps1=new GPSCoordinate(60,120);
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20,40);
		double m31=20000;
		
		GPSCoordinate gps2=new GPSCoordinate(72,89);
		LocalTime openingHour2=LocalTime.of(8,00);
		LocalTime closingHour2=LocalTime.of(22,30);
		double m32=100000;
		
		PickingPoint pickingPoint1=new PackageLocker(gps1,openingHour1,closingHour1,lockers,m31);
		PickingPoint pickingPoint2=new PackageLocker(gps2,openingHour2,closingHour2,lockers,m32);
		List<PickingPoint> pickingPoints=new ArrayList<>();
		pickingPoints.add(pickingPoint1);
		pickingPoints.add(pickingPoint2);
		PickingPointSystem pickingPointSystem=new PickingPointSystem(pickingPoints);
		PickingPointSystem pickingPointSystemCopy=new PickingPointSystem(pickingPointSystem);
		assertNotNull(pickingPointSystemCopy);
		assertNotSame(pickingPointSystem,pickingPointSystemCopy);
		assertEquals(pickingPointSystem,pickingPointSystemCopy);
		assertNotSame(pickingPointSystem.getPickingPoints(),pickingPointSystem.getPickingPoints());
		assertEquals(pickingPointSystem.getPickingPoints(),pickingPointSystem.getPickingPoints());
	}
	
	@Test
	public void testGettersSettersPickingPoints() {
		int numLocker1=3;
		int numLocker2=7;
		Locker locker1=new Locker(numLocker1);
		Locker locker2=new Locker(numLocker2);
		Locker lockers[]= {locker1,locker2};
		
		GPSCoordinate gps1=new GPSCoordinate(60,120);
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20,40);
		double m31=20000;
		
		GPSCoordinate gps2=new GPSCoordinate(72,89);
		LocalTime openingHour2=LocalTime.of(8,00);
		LocalTime closingHour2=LocalTime.of(22,30);
		double m32=100000;
		
		PickingPointSystem pickingPointSystem=new PickingPointSystem();
		PickingPoint pickingPoint1=new PackageLocker(gps1,openingHour1,closingHour1,lockers,m31);
		PickingPoint pickingPoint2=new PackageLocker(gps2,openingHour2,closingHour2,lockers,m32);
		List<PickingPoint> pickingPoints=new ArrayList<>();
		pickingPoints.add(pickingPoint1);
		pickingPoints.add(pickingPoint2);
		pickingPointSystem.setPickingPoint(pickingPoints);
		
		assertNotNull(pickingPointSystem.getPickingPoints());
		assertNotSame(pickingPointSystem.getPickingPoints(),pickingPoints);
		assertEquals(pickingPointSystem.getPickingPoints(),pickingPoints);
	}
	
	@Test(expected=NullPointerException.class)
	public void testGettersSettersPickingPointsNull() {

		
		PickingPointSystem pickingPointSystem=new PickingPointSystem();
		
		List<PickingPoint> pickingPoints=null;
		pickingPointSystem.setPickingPoint(pickingPoints);
		
		
	}
	
	@Test
	public void testAddPickingPointPackageLocker() {
		PickingPointSystem pickingPointSystem=new PickingPointSystem();
		int numLocker1=3;
		int numLocker2=7;
		Locker locker1=new Locker(numLocker1);
		Locker locker2=new Locker(numLocker2);
		Locker lockers[]= {locker1,locker2};
		
		GPSCoordinate gps1=new GPSCoordinate(60,120);
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20,40);
		double m31=20000;
		PickingPoint pickingPoint=new PackageLocker(gps1,openingHour1,closingHour1,lockers,m31);
		
		pickingPointSystem.addPickingPoint(pickingPoint);
		assertTrue(pickingPointSystem.getPickingPoints().contains(pickingPoint));
	}
	
	@Test
	public void testAddPickingPointKiosk() {
		PickingPointSystem pickingPointSystem=new PickingPointSystem();
		
		
		GPSCoordinate gps1=new GPSCoordinate(60,120);
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20,40);
		int maxCapacity=10;
		double m31=20000;
		PickingPoint pickingPoint=new Kiosk(gps1,openingHour1,closingHour1,maxCapacity,m31);
		
		pickingPointSystem.addPickingPoint(pickingPoint);
		assertTrue(pickingPointSystem.getPickingPoints().contains(pickingPoint));
	}
	
	@Test
	public void testAddPickingPointPickingPointHub() {
		PickingPointSystem pickingPointSystem=new PickingPointSystem();

		
	
		
		
		
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double m3=300000;
		double m31=50000;
		double m32=78000;
		double m33=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,m31);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,m32);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,m33);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,m3);
		pickingPointSystem.addPickingPoint(pickingPointHub);
		assertTrue(pickingPointSystem.getPickingPoints().contains(pickingPointHub));
	}
	
	@Test
	public void testAddPickingPointPostOffice() {
		PickingPointSystem pickingPointSystem=new PickingPointSystem();
		
		
		GPSCoordinate gps1=new GPSCoordinate(60,120);
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20,40);
		
		PickingPoint pickingPoint=new PostOffice(gps1,openingHour1,closingHour1);
		
		pickingPointSystem.addPickingPoint(pickingPoint);
		assertTrue(pickingPointSystem.getPickingPoints().contains(pickingPoint));
	}
	
	@Test(expected=NullPointerException.class)
	public void testAddPickingPointNull() {
		PickingPoint pickingPoint=null;
		PickingPointSystem pickingPointSystem=new PickingPointSystem();
		pickingPointSystem.addPickingPoint(pickingPoint);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddPickingPointIsOnList() {
		PickingPointSystem pickingPointSystem=new PickingPointSystem();
		int numLocker1=3;
		int numLocker2=7;
		Locker locker1=new Locker(numLocker1);
		Locker locker2=new Locker(numLocker2);
		Locker lockers[]= {locker1,locker2};
		
		GPSCoordinate gps1=new GPSCoordinate(60,120);
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20,40);
		double m31=20000;
		PickingPoint pickingPoint=new PackageLocker(gps1,openingHour1,closingHour1,lockers,m31);
		
		pickingPointSystem.addPickingPoint(pickingPoint);
		pickingPointSystem.addPickingPoint(pickingPoint);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddPickingPointSameGps() {
		PickingPointSystem pickingPointSystem=new PickingPointSystem();
		int numLocker1=3;
		int numLocker2=7;
		Locker locker1=new Locker(numLocker1);
		Locker locker2=new Locker(numLocker2);
		Locker lockers[]= {locker1,locker2};
		
		GPSCoordinate gps1=new GPSCoordinate(60,120);
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20,40);
		double m31=20000;
		PickingPoint pickingPoint1=new PackageLocker(gps1,openingHour1,closingHour1,lockers,m31);
		

		GPSCoordinate gps2=new GPSCoordinate(60,120);
		LocalTime openingHour2=LocalTime.of(10,15);
		LocalTime closingHour2=LocalTime.of(21,40);
		double m32=50000;
		PickingPoint pickingPoint2=new PackageLocker(gps2,openingHour2,closingHour2,lockers,m32);
		pickingPointSystem.addPickingPoint(pickingPoint1);
		pickingPointSystem.addPickingPoint(pickingPoint2);
	}
	
	@Test
	public void testRemovePickingPointPackageLocker() {
		int numLocker1=3;
		int numLocker2=7;
		Locker locker1=new Locker(numLocker1);
		Locker locker2=new Locker(numLocker2);
		Locker lockers[]= {locker1,locker2};
		
		GPSCoordinate gps1=new GPSCoordinate(60,120);
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20,40);
		double m31=20000;
		PickingPoint pickingPoint1=new PackageLocker(gps1,openingHour1,closingHour1,lockers,m31);
		List<PickingPoint> pickingPoints=new ArrayList<>();
		pickingPoints.add(pickingPoint1);
		PickingPointSystem pickingPointSystem=new PickingPointSystem(pickingPoints);
		
		pickingPointSystem.removePickingPoint(pickingPoint1.getId());
		assertFalse(pickingPointSystem.getPickingPoints().contains(pickingPoint1));
	}
	
	@Test
	public void testRemovePickingPointKiosk() {
		
		
		
		GPSCoordinate gps1=new GPSCoordinate(60,120);
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20,40);
		int maxCapacity=10;
		double m31=20000;
		PickingPoint pickingPoint=new Kiosk(gps1,openingHour1,closingHour1,maxCapacity,m31);
		List<PickingPoint> pickingPoints=new ArrayList<>();
		pickingPoints.add(pickingPoint);
		PickingPointSystem pickingPointSystem=new PickingPointSystem(pickingPoints);
		pickingPointSystem.removePickingPoint(pickingPoint.getId());
		assertFalse(pickingPointSystem.getPickingPoints().contains(pickingPoint));
	}
	
	@Test
	public void testRemovePickingPointPickingPointHub() {
		

	
		
		
		
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double m3=300000;
		double m31=50000;
		double m32=78000;
		double m33=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,m31);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,m32);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,m33);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		
		PickingPointHub pickingPoint=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,m3);
		List<PickingPoint> pickingPoints=new ArrayList<>();
		pickingPoints.add(pickingPoint);
		PickingPointSystem pickingPointSystem=new PickingPointSystem(pickingPoints);
		pickingPointSystem.removePickingPoint(pickingPoint.getId());
		assertFalse(pickingPointSystem.getPickingPoints().contains(pickingPoint));
	}
	
	@Test
	public void testRemovePickingPointPostOffice() {
		
		
		
		GPSCoordinate gps1=new GPSCoordinate(60,120);
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20,40);
		
		PickingPoint pickingPoint=new PostOffice(gps1,openingHour1,closingHour1);
		
		List<PickingPoint> pickingPoints=new ArrayList<>();
		pickingPoints.add(pickingPoint);
		PickingPointSystem pickingPointSystem=new PickingPointSystem(pickingPoints);
		pickingPointSystem.removePickingPoint(pickingPoint.getId());
		assertFalse(pickingPointSystem.getPickingPoints().contains(pickingPoint));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRemovePickingPointNotFound() {
		int numLocker1=3;
		int numLocker2=7;
		Locker locker1=new Locker(numLocker1);
		Locker locker2=new Locker(numLocker2);
		Locker lockers[]= {locker1,locker2};
		
		GPSCoordinate gps1=new GPSCoordinate(60,120);
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20,40);
		double m31=20000;
		PickingPoint pickingPoint1=new PackageLocker(gps1,openingHour1,closingHour1,lockers,m31);
		List<PickingPoint> pickingPoints=new ArrayList<>();
		
		PickingPointSystem pickingPointSystem=new PickingPointSystem(pickingPoints);
		pickingPointSystem.removePickingPoint(pickingPoint1.getId());
	}
	
	@Test
	public void testListPickingPointOn() {
		int numLocker1=3;
		int numLocker2=7;
		Locker locker1=new Locker(numLocker1);
		Locker locker2=new Locker(numLocker2);
		Locker lockers[]= {locker1,locker2};
		
		GPSCoordinate gps1=new GPSCoordinate(60,120);
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20,40);
		double m31=20000;
		
		GPSCoordinate gps2=new GPSCoordinate(72,89);
		LocalTime openingHour2=LocalTime.of(8,00);
		LocalTime closingHour2=LocalTime.of(22,30);
		double m32=100000;
		
		PickingPointSystem pickingPointSystem=new PickingPointSystem();
		PickingPoint pickingPoint1=new PackageLocker(gps1,openingHour1,closingHour1,lockers,m31);
		PickingPoint pickingPoint2=new PackageLocker(gps2,openingHour2,closingHour2,lockers,m32);
		
		pickingPoint2.outOfService();
		
		pickingPointSystem.addPickingPoint(pickingPoint1);
		pickingPointSystem.addPickingPoint(pickingPoint2);
		List<PickingPoint> listPickingPointOnExpected=new ArrayList<>();
		listPickingPointOnExpected.add(pickingPoint1);
		
		assertNotNull(pickingPointSystem.listPickingPointOn());
		assertTrue(pickingPointSystem.listPickingPointOn().contains(pickingPoint1));
		assertEquals(pickingPointSystem.listPickingPointOn(),listPickingPointOnExpected);
		assertNotSame(pickingPointSystem.listPickingPointOn(),listPickingPointOnExpected);
		assertEquals(pickingPointSystem.listPickingPointOn().size(),listPickingPointOnExpected.size());
		
	}
	
	@Test
	public void testPickingPointOff() {
		PickingPointSystem pickingPointsSystem=new PickingPointSystem();
		
		int numLocker2=1;
		Locker locker2=new Locker(numLocker2);
		int numLocker3=7;
		Locker locker3=new Locker(numLocker3);
		
		Locker lockers[]= {locker2,locker3};
		
		
		GPSCoordinate gps1=new GPSCoordinate(60,120);
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(21,15);
		double m31=20000;
			
		
		PickingPoint pickingPoint1=new PackageLocker(gps1,openingHour1,closingHour1,lockers,m31);

		GPSCoordinate gps2=new GPSCoordinate(39,76);
		LocalTime openingHour2=LocalTime.of(9,15);
		LocalTime closingHour2=LocalTime.of(20,15);
		double m32=50000;
				
		
		PickingPoint pickingPoint2=new PackageLocker(gps2,openingHour2,closingHour2,lockers,m32);
		pickingPoint2.outOfService();
	
		GPSCoordinate gps3=new GPSCoordinate(67,125);
		LocalTime openingHour3=LocalTime.of(9,15);
		LocalTime closingHour3=LocalTime.of(20,15);
		double m33=75000;
		 
		PackageLocker pickingPoint3=new PackageLocker(gps3,openingHour3,closingHour3,lockers,m33);
		
		pickingPointsSystem.addPickingPoint(pickingPoint1);
		pickingPointsSystem.addPickingPoint(pickingPoint2);
		pickingPointsSystem.addPickingPoint(pickingPoint3);
		int numPickingPointsOff=1;
		List<PickingPoint> pickingPointsOff=new ArrayList<>();
		pickingPointsOff.add(pickingPoint2);
		
		assertNotNull(pickingPointsSystem.listPickingPointOff());
		assertEquals(pickingPointsSystem.listPickingPointOff().size(),numPickingPointsOff);
		assertEquals(pickingPointsSystem.listPickingPointOff(),pickingPointsOff);
		assertNotSame(pickingPointsSystem.listPickingPointOff(),pickingPointsOff);
	}
	
	@Test
	public void testListPickingPointRadio() {
		int numLocker1=3;
		int numLocker2=7;
		Locker locker1=new Locker(numLocker1);
		Locker locker2=new Locker(numLocker2);
		Locker lockers[]= {locker1,locker2};
		
		GPSCoordinate gps1=new GPSCoordinate(60,120);
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20,40);
		double m31=20000;
		GPSCoordinate gps2=new GPSCoordinate(83,128);
		LocalTime openingHour2=LocalTime.of(7,15);
		LocalTime closingHour2=LocalTime.of(22,40);
		double m32=50000;
		PickingPoint pickingPoint1=new PackageLocker(gps1,openingHour1,closingHour1,lockers,m31);
		PickingPoint pickingPoint2=new PackageLocker(gps2,openingHour2,closingHour2,lockers,m32);
		List<PickingPoint> pickingPoints=new ArrayList<>();
		pickingPoints.add(pickingPoint1);
		pickingPoints.add(pickingPoint2);
		PickingPointSystem pickingPointSystem=new PickingPointSystem(pickingPoints);
		
		double radio=2000;
		GPSCoordinate gps=new GPSCoordinate(59,121);
		pickingPointSystem.listPickingPointRadio(radio, gps);
		List<PickingPoint> pickingPointRadio=new ArrayList<>();
		pickingPointRadio.add(pickingPoint1);
		assertNotNull(pickingPointSystem.listPickingPointRadio(radio, gps));
		assertEquals(pickingPointRadio,pickingPointSystem.listPickingPointRadio(radio,gps));
		assertNotSame(pickingPointRadio,pickingPointSystem.listPickingPointRadio(radio, gps));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testListPickingPointNegativeRadio() {
		int numLocker1=3;
		int numLocker2=7;
		Locker locker1=new Locker(numLocker1);
		Locker locker2=new Locker(numLocker2);
		Locker lockers[]= {locker1,locker2};
		
		GPSCoordinate gps1=new GPSCoordinate(60,120);
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20,40);
		double m31=20000;
		
		PickingPoint pickingPoint1=new PackageLocker(gps1,openingHour1,closingHour1,lockers,m31);
		List<PickingPoint> pickingPoints=new ArrayList<>();
		pickingPoints.add(pickingPoint1);
		PickingPointSystem pickingPointSystem=new PickingPointSystem(pickingPoints);
		double radio=-50;
		GPSCoordinate gps=new GPSCoordinate(59,121);
		pickingPointSystem.listPickingPointRadio(radio,gps);
	}
	
	@Test(expected=NullPointerException.class)
	public void testListPickingPointRadioGpsNull() {
		int numLocker1=3;
		int numLocker2=7;
		Locker locker1=new Locker(numLocker1);
		Locker locker2=new Locker(numLocker2);
		Locker lockers[]= {locker1,locker2};
		
		GPSCoordinate gps1=new GPSCoordinate(60,120);
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20,40);
		double m31=20000;
		
		PickingPoint pickingPoint1=new PackageLocker(gps1,openingHour1,closingHour1,lockers,m31);
		List<PickingPoint> pickingPoints=new ArrayList<>();
		pickingPoints.add(pickingPoint1);
		PickingPointSystem pickingPointSystem=new PickingPointSystem(pickingPoints);
		double radio=50;
		GPSCoordinate gps=null;
		pickingPointSystem.listPickingPointRadio(radio,gps);
	}
	
	@Test
	public void testListPickingPointRadioPackageSuitableForAll() {
		String id1="1234567895";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=10;
		double length=10;
		double height=10;
		double price=50;
		boolean paid=true;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		int numLocker1=3;
		int numLocker2=7;
		Locker locker1=new Locker(numLocker1);
		Locker locker2=new Locker(numLocker2);
		Locker lockers[]= {locker1,locker2};
		
		GPSCoordinate gps1=new GPSCoordinate(60,120);
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20,40);
		double m31=20000;
				
		PickingPoint pickingPoint1=new PackageLocker(gps1,openingHour1,closingHour1,lockers,m31);
		
		
		List<PickingPoint> pickingPoints=new ArrayList<>();
		pickingPoints.add(pickingPoint1);
		
		PickingPointSystem pickingPointSystem=new PickingPointSystem(pickingPoints);
		
		double radio=50;
		GPSCoordinate gps=new GPSCoordinate(60,120);
		List<PickingPoint> pickingPointSuitable=new ArrayList<>();
		pickingPointSuitable.add(pickingPoint1);
		
		pickingPointSystem.listPickingPointRadioPackage(package1, radio, gps);
		
		assertNotNull(pickingPointSystem.listPickingPointRadioPackage(package1, radio, gps));
		assertEquals(pickingPointSuitable,pickingPointSystem.listPickingPointRadioPackage(package1,radio,gps));
		assertNotSame(pickingPointSuitable,pickingPointSystem.listPickingPointRadioPackage(package1, radio, gps));
		assertNotSame(pickingPointSystem,pickingPointSystem.listPickingPointRadioPackage(package1, radio, gps));
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testListPickingPointRadioPackageNegativeRadio() {
		String id1="1234567895";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=10;
		double length=10;
		double height=10;
		double price=50;
		boolean paid=true;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		int numLocker1=3;
		int numLocker2=7;
		Locker locker1=new Locker(numLocker1);
		Locker locker2=new Locker(numLocker2);
		Locker lockers[]= {locker1,locker2};
		
		GPSCoordinate gps1=new GPSCoordinate(60,120);
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20,40);
		double m31=20000;
				
		PickingPoint pickingPoint1=new PackageLocker(gps1,openingHour1,closingHour1,lockers,m31);
		
		
		List<PickingPoint> pickingPoints=new ArrayList<>();
		pickingPoints.add(pickingPoint1);
		
		PickingPointSystem pickingPointSystem=new PickingPointSystem(pickingPoints);
		
		double radio=-50;
		GPSCoordinate gps=new GPSCoordinate(60,120);
		List<PickingPoint> pickingPointSuitable=new ArrayList<>();
		pickingPointSuitable.add(pickingPoint1);
		
		pickingPointSystem.listPickingPointRadioPackage(package1, radio, gps);
		
	}
	
	@Test
	public void testListPickingPointRadioPackageSuitableForCOD() {
		String id1="1234567895";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=10;
		double length=10;
		double height=10;
		double price=50;
		boolean paid=false;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		int numLocker1=3;
		int numLocker2=7;
		Locker locker1=new Locker(numLocker1);
		Locker locker2=new Locker(numLocker2);
		Locker lockers[]= {locker1,locker2};
		
		GPSCoordinate gps1=new GPSCoordinate(60,120);
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20,40);
		double m31=20000;
		GPSCoordinate gps2=new GPSCoordinate(55,150);
		LocalTime openingHour2=LocalTime.of(9,15);
		LocalTime closingHour2=LocalTime.of(20,40);
		int maxCapacity=40;
		double m32=20000;
				
		PickingPoint pickingPoint1=new PackageLocker(gps1,openingHour1,closingHour1,lockers,m31);
		
		PickingPoint pickingPoint2=new Kiosk(gps2,openingHour2,closingHour2,maxCapacity,m32);
		
		List<PickingPoint> pickingPoints=new ArrayList<>();
		pickingPoints.add(pickingPoint1);
		pickingPoints.add(pickingPoint2);
		
		PickingPointSystem pickingPointSystem=new PickingPointSystem(pickingPoints);
		
		double radio=2000;
		GPSCoordinate gps=new GPSCoordinate(61,120);
		int pickingPointSuitable=1;
		
		pickingPointSystem.listPickingPointRadioPackage(package1, radio, gps);
		
		assertNotNull(pickingPointSystem.listPickingPointRadioPackage(package1, radio, gps));
		assertEquals(pickingPointSuitable,pickingPointSystem.listPickingPointRadioPackage(package1,radio,gps).size());
		assertNotSame(pickingPointSuitable,pickingPointSystem.listPickingPointRadioPackage(package1, radio, gps));
		assertNotSame(pickingPointSystem,pickingPointSystem.listPickingPointRadioPackage(package1, radio, gps));
	}
	
	@Test
	public void testListPickingPointRadioPackageSuitableForCertified() {
		String id1="1234567895";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=10;
		double length=10;
		double height=10;
		double price=50;
		boolean paid=true;
		String authorizedDni1="123456789A";
		List<String> authorizedDni=new ArrayList<>();
		authorizedDni.add(authorizedDni1);
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid,authorizedDni);
		int numLocker1=3;
		int numLocker2=7;
		Locker locker1=new Locker(numLocker1);
		Locker locker2=new Locker(numLocker2);
		Locker lockers[]= {locker1,locker2};
		
		GPSCoordinate gps1=new GPSCoordinate(60,120);
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20,40);
		double m31=20000;
		GPSCoordinate gps2=new GPSCoordinate(55,150);
		LocalTime openingHour2=LocalTime.of(9,15);
		LocalTime closingHour2=LocalTime.of(20,40);

				
		PickingPoint pickingPoint1=new PackageLocker(gps1,openingHour1,closingHour1,lockers,m31);
		
		PickingPoint pickingPoint2=new PostOffice(gps2,openingHour2,closingHour2);
		
		List<PickingPoint> pickingPoints=new ArrayList<>();
		pickingPoints.add(pickingPoint1);
		pickingPoints.add(pickingPoint2);
		
		PickingPointSystem pickingPointSystem=new PickingPointSystem(pickingPoints);
		
		double radio=2000;
		GPSCoordinate gps=new GPSCoordinate(60,120);
		List<PickingPoint> pickingPointSuitable=new ArrayList<>();
		pickingPointSuitable.add(pickingPoint2);
		
		pickingPointSystem.listPickingPointRadioPackage(package1, radio, gps);
		
		assertNotNull(pickingPointSystem.listPickingPointRadioPackage(package1, radio, gps));
		assertEquals(pickingPointSuitable,pickingPointSystem.listPickingPointRadioPackage(package1,radio,gps));
		assertNotSame(pickingPointSuitable,pickingPointSystem.listPickingPointRadioPackage(package1, radio, gps));
		assertNotSame(pickingPointSystem,pickingPointSystem.listPickingPointRadioPackage(package1, radio, gps));
	}
	
	@Test(expected=NullPointerException.class)
	public void testListPickingPointRadioPackageNull() {
		int numLocker1=3;
		int numLocker2=7;
		Locker locker1=new Locker(numLocker1);
		Locker locker2=new Locker(numLocker2);
		Locker lockers[]= {locker1,locker2};
		
		GPSCoordinate gps1=new GPSCoordinate(60,120);
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20,40);
		double m31=20000;
				
		PickingPoint pickingPoint1=new PackageLocker(gps1,openingHour1,closingHour1,lockers,m31);
		
		List<PickingPoint> pickingPoints=new ArrayList<>();
		pickingPoints.add(pickingPoint1);
		
		PickingPointSystem pickingPointSystem=new PickingPointSystem(pickingPoints);
		
		double radio=50;
		GPSCoordinate gps=new GPSCoordinate(60,120);
		List<PickingPoint> pickingPointSuitable=new ArrayList<>();
		pickingPointSuitable.add(pickingPoint1);
		Package package1=null;
		
		pickingPointSystem.listPickingPointRadioPackage(package1, radio, gps);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testListPickingPointRadioNegativePackage() {
		String id1="1234567895";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=10;
		double length=10;
		double height=10;
		double price=50;
		boolean paid=true;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		int numLocker1=3;
		int numLocker2=7;
		Locker locker1=new Locker(numLocker1);
		Locker locker2=new Locker(numLocker2);
		Locker lockers[]= {locker1,locker2};
		
		GPSCoordinate gps1=new GPSCoordinate(60,120);
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20,40);
		double m31=20000;
				
		PickingPoint pickingPoint1=new PackageLocker(gps1,openingHour1,closingHour1,lockers,m31);
		pickingPoint1.addPackage(package1);
		
		List<PickingPoint> pickingPoints=new ArrayList<>();
		pickingPoints.add(pickingPoint1);
		
		PickingPointSystem pickingPointSystem=new PickingPointSystem(pickingPoints);
		
		double radio=-50;
		GPSCoordinate gps=new GPSCoordinate(60,120);
		List<PickingPoint> pickingPointSuitable=new ArrayList<>();
		pickingPointSuitable.add(pickingPoint1);
		
		pickingPointSystem.listPickingPointRadioPackage(package1, radio, gps);
	}
	
	@Test(expected=NullPointerException.class)
	public void testListPickingPointRadioPackageGpsNull() {
		String id1="1234567895";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=0.1;
		double length=0.1;
		double height=0.1;
		double price=50;
		boolean paid=true;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		int numLocker1=3;
		int numLocker2=7;
		Locker locker1=new Locker(numLocker1);
		Locker locker2=new Locker(numLocker2);
		Locker lockers[]= {locker1,locker2};
		
		GPSCoordinate gps1=new GPSCoordinate(60,120);
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20,40);
		double m31=20000;
				
		PickingPoint pickingPoint1=new PackageLocker(gps1,openingHour1,closingHour1,lockers,m31);
		pickingPoint1.addPackage(package1);
		
		List<PickingPoint> pickingPoints=new ArrayList<>();
		pickingPoints.add(pickingPoint1);
		
		PickingPointSystem pickingPointSystem=new PickingPointSystem(pickingPoints);
		
		double radio=50;
		GPSCoordinate gps=null;
		List<PickingPoint> pickingPointSuitable=new ArrayList<>();
		pickingPointSuitable.add(pickingPoint1);
		
		pickingPointSystem.listPickingPointRadioPackage(package1, radio, gps);
	}
	
	@Test
	public void testListPickingPointWithCOD() {
		int numLocker1=3;
		int numLocker2=7;
		Locker locker1=new Locker(numLocker1);
		Locker locker2=new Locker(numLocker2);
		Locker lockers[]= {locker1,locker2};
		
		GPSCoordinate gps1=new GPSCoordinate(60,120);
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20,40);
		double m31=20000;
		GPSCoordinate gps2=new GPSCoordinate(55,150);
		LocalTime openingHour2=LocalTime.of(9,15);
		LocalTime closingHour2=LocalTime.of(20,40);
		int maxCapacity=40;
		double m32=20000;
				
		PickingPoint pickingPoint1=new PackageLocker(gps1,openingHour1,closingHour1,lockers,m31);
		
		PickingPoint pickingPoint2=new Kiosk(gps2,openingHour2,closingHour2,maxCapacity,m32);
		
		List<PickingPoint> pickingPoints=new ArrayList<>();
		pickingPoints.add(pickingPoint1);
		pickingPoints.add(pickingPoint2);
		
		PickingPointSystem pickingPointSystem=new PickingPointSystem(pickingPoints);

		List<PickingPoint> pickingPointSuitable=new ArrayList<>();
		pickingPointSuitable.add(pickingPoint2);
		
		pickingPointSystem.listPickingPointWithCOD();
		
		assertNotNull(pickingPointSystem.listPickingPointWithCOD());
		assertEquals(pickingPointSuitable,pickingPointSystem.listPickingPointWithCOD());
		assertNotSame(pickingPointSuitable,pickingPointSystem.listPickingPointWithCOD());
		assertNotSame(pickingPointSystem,pickingPointSystem.listPickingPointWithCOD());
	}
	
	@Test
	public void testListPickingPointCertified() {
		int numLocker1=3;
		int numLocker2=7;
		Locker locker1=new Locker(numLocker1);
		Locker locker2=new Locker(numLocker2);
		Locker lockers[]= {locker1,locker2};
		
		GPSCoordinate gps1=new GPSCoordinate(60,120);
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20,40);
		double m31=20000;
		GPSCoordinate gps2=new GPSCoordinate(55,150);
		LocalTime openingHour2=LocalTime.of(9,15);
		LocalTime closingHour2=LocalTime.of(20,40);
		
				
		PickingPoint pickingPoint1=new PackageLocker(gps1,openingHour1,closingHour1,lockers,m31);
		
		PickingPoint pickingPoint2=new PostOffice(gps2,openingHour2,closingHour2);
		
		List<PickingPoint> pickingPoints=new ArrayList<>();
		pickingPoints.add(pickingPoint1);
		pickingPoints.add(pickingPoint2);
		
		PickingPointSystem pickingPointSystem=new PickingPointSystem(pickingPoints);
		
		List<PickingPoint> pickingPointSuitable=new ArrayList<>();
		pickingPointSuitable.add(pickingPoint2);
		
		pickingPointSystem.listPickingPointWithCertified();
		
		assertNotNull(pickingPointSystem.listPickingPointWithCertified());
		assertEquals(pickingPointSuitable,pickingPointSystem.listPickingPointWithCertified());
		assertNotSame(pickingPointSuitable,pickingPointSystem.listPickingPointWithCertified());
		assertNotSame(pickingPointSystem,pickingPointSystem.listPickingPointWithCertified());
	}
	
	@Test
	public void testListPickingPointWithCODRadio() {
		int numLocker1=3;
		int numLocker2=7;
		Locker locker1=new Locker(numLocker1);
		Locker locker2=new Locker(numLocker2);
		Locker lockers[]= {locker1,locker2};
		
		GPSCoordinate gps1=new GPSCoordinate(60,120);
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20,40);
		double m31=20000;
		GPSCoordinate gps2=new GPSCoordinate(55,150);
		LocalTime openingHour2=LocalTime.of(9,15);
		LocalTime closingHour2=LocalTime.of(20,40);
		int maxCapacity=40;
		double m32=20000;
				
		PickingPoint pickingPoint1=new PackageLocker(gps1,openingHour1,closingHour1,lockers,m31);
		
		PickingPoint pickingPoint2=new Kiosk(gps2,openingHour2,closingHour2,maxCapacity,m32);
		
		List<PickingPoint> pickingPoints=new ArrayList<>();
		pickingPoints.add(pickingPoint1);
		pickingPoints.add(pickingPoint2);
		
		PickingPointSystem pickingPointSystem=new PickingPointSystem(pickingPoints);
		
		double radio=2000;
		GPSCoordinate gps=new GPSCoordinate(60,120);
		List<PickingPoint> pickingPointSuitable=new ArrayList<>();
		int numPointsSuitable=1;
		
		pickingPointSystem.listPickingPointWithCODRadio(gps,radio);
		
		assertNotNull(pickingPointSystem.listPickingPointWithCODRadio(gps,radio));
		assertEquals(numPointsSuitable,pickingPointSystem.listPickingPointWithCODRadio(gps,radio).size());
		assertNotSame(pickingPointSuitable,pickingPointSystem.listPickingPointWithCODRadio(gps,radio));
		assertNotSame(pickingPointSystem,pickingPointSystem.listPickingPointWithCODRadio(gps,radio));
	}
	
	
	@Test(expected=NullPointerException.class)
	public void testListPickingPointWithCODRadioGpsNull() {
		int numLocker1=3;
		int numLocker2=7;
		Locker locker1=new Locker(numLocker1);
		Locker locker2=new Locker(numLocker2);
		Locker lockers[]= {locker1,locker2};
		
		GPSCoordinate gps1=new GPSCoordinate(60,120);
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20,40);
		double m31=20000;
		GPSCoordinate gps2=new GPSCoordinate(55,150);
		LocalTime openingHour2=LocalTime.of(9,15);
		LocalTime closingHour2=LocalTime.of(20,40);
		int maxCapacity=40;
		double m32=20000;
				
		PickingPoint pickingPoint1=new PackageLocker(gps1,openingHour1,closingHour1,lockers,m31);
		
		PickingPoint pickingPoint2=new Kiosk(gps2,openingHour2,closingHour2,maxCapacity,m32);
		
		List<PickingPoint> pickingPoints=new ArrayList<>();
		pickingPoints.add(pickingPoint1);
		pickingPoints.add(pickingPoint2);
		
		PickingPointSystem pickingPointSystem=new PickingPointSystem(pickingPoints);
		
		double radio=50;
		GPSCoordinate gps=null;
		
		
		
		pickingPointSystem.listPickingPointWithCODRadio(gps,radio);
		
		
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testListPickingPointWithCODRadioNegative() {
		int numLocker1=3;
		int numLocker2=7;
		Locker locker1=new Locker(numLocker1);
		Locker locker2=new Locker(numLocker2);
		Locker lockers[]= {locker1,locker2};
		
		GPSCoordinate gps1=new GPSCoordinate(60,120);
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20,40);
		double m31=20000;
		GPSCoordinate gps2=new GPSCoordinate(55,150);
		LocalTime openingHour2=LocalTime.of(9,15);
		LocalTime closingHour2=LocalTime.of(20,40);
		int maxCapacity=40;
		double m32=20000;
				
		PickingPoint pickingPoint1=new PackageLocker(gps1,openingHour1,closingHour1,lockers,m31);
		
		PickingPoint pickingPoint2=new Kiosk(gps2,openingHour2,closingHour2,maxCapacity,m32);
		
		List<PickingPoint> pickingPoints=new ArrayList<>();
		pickingPoints.add(pickingPoint1);
		pickingPoints.add(pickingPoint2);
		
		PickingPointSystem pickingPointSystem=new PickingPointSystem(pickingPoints);
		
		double radio=-50;
		GPSCoordinate gps=new GPSCoordinate(60,120);
		
		pickingPointSystem.listPickingPointWithCODRadio(gps,radio);
		
		
	}
	
	@Test
	public void testListPickingPointWithCertifiedRadio() {
		int numLocker1=3;
		int numLocker2=7;
		Locker locker1=new Locker(numLocker1);
		Locker locker2=new Locker(numLocker2);
		Locker lockers[]= {locker1,locker2};
		
		GPSCoordinate gps1=new GPSCoordinate(60,120);
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20,40);
		double m31=20000;
		GPSCoordinate gps2=new GPSCoordinate(55,150);
		LocalTime openingHour2=LocalTime.of(9,15);
		LocalTime closingHour2=LocalTime.of(20,40);

		PickingPoint pickingPoint1=new PackageLocker(gps1,openingHour1,closingHour1,lockers,m31);
		
		PickingPoint pickingPoint2=new PostOffice(gps2,openingHour2,closingHour2);
		
		List<PickingPoint> pickingPoints=new ArrayList<>();
		pickingPoints.add(pickingPoint1);
		pickingPoints.add(pickingPoint2);
		
		PickingPointSystem pickingPointSystem=new PickingPointSystem(pickingPoints);
		
		double radio=2000;
		GPSCoordinate gps=new GPSCoordinate(60,120);
		List<PickingPoint> pickingPointSuitable=new ArrayList<>();
		pickingPointSuitable.add(pickingPoint2);
		
		pickingPointSystem.listPickingPointWithCertifiedRadio(gps, radio);
		
		assertNotNull(pickingPointSystem.listPickingPointWithCertifiedRadio(gps,radio));
		assertEquals(pickingPointSuitable,pickingPointSystem.listPickingPointWithCertifiedRadio(gps,radio));
		assertNotSame(pickingPointSuitable,pickingPointSystem.listPickingPointWithCertifiedRadio(gps,radio));
		assertNotSame(pickingPointSystem,pickingPointSystem.listPickingPointWithCertifiedRadio(gps,radio));
	}
	
	
	@Test(expected=NullPointerException.class)
	public void testListPickingPointWithCertifiedRadioGpsNull() {
		int numLocker1=3;
		int numLocker2=7;
		Locker locker1=new Locker(numLocker1);
		Locker locker2=new Locker(numLocker2);
		Locker lockers[]= {locker1,locker2};
		
		GPSCoordinate gps1=new GPSCoordinate(60,120);
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20,40);
		double m31=20000;
		GPSCoordinate gps2=new GPSCoordinate(55,150);
		LocalTime openingHour2=LocalTime.of(9,15);
		LocalTime closingHour2=LocalTime.of(20,40);

		PickingPoint pickingPoint1=new PackageLocker(gps1,openingHour1,closingHour1,lockers,m31);
		
		PickingPoint pickingPoint2=new PostOffice(gps2,openingHour2,closingHour2);
		
		List<PickingPoint> pickingPoints=new ArrayList<>();
		pickingPoints.add(pickingPoint1);
		pickingPoints.add(pickingPoint2);
		
		PickingPointSystem pickingPointSystem=new PickingPointSystem(pickingPoints);
		
		double radio=50;
		GPSCoordinate gps=null;
		List<PickingPoint> pickingPointSuitable=new ArrayList<>();
		pickingPointSuitable.add(pickingPoint2);
		
		pickingPointSystem.listPickingPointWithCertifiedRadio(gps, radio);
		
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testListPickingPointWithCertifiedRadioNegativeRadio() {
		int numLocker1=3;
		int numLocker2=7;
		Locker locker1=new Locker(numLocker1);
		Locker locker2=new Locker(numLocker2);
		Locker lockers[]= {locker1,locker2};
		
		GPSCoordinate gps1=new GPSCoordinate(60,120);
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20,40);
		double m31=20000;
		GPSCoordinate gps2=new GPSCoordinate(55,150);
		LocalTime openingHour2=LocalTime.of(9,15);
		LocalTime closingHour2=LocalTime.of(20,40);

		PickingPoint pickingPoint1=new PackageLocker(gps1,openingHour1,closingHour1,lockers,m31);
		
		PickingPoint pickingPoint2=new PostOffice(gps2,openingHour2,closingHour2);
		
		List<PickingPoint> pickingPoints=new ArrayList<>();
		pickingPoints.add(pickingPoint1);
		pickingPoints.add(pickingPoint2);
		
		PickingPointSystem pickingPointSystem=new PickingPointSystem(pickingPoints);
		
		double radio=-50;
		GPSCoordinate gps=new GPSCoordinate(60,120);
		List<PickingPoint> pickingPointSuitable=new ArrayList<>();
		pickingPointSuitable.add(pickingPoint2);
		
		pickingPointSystem.listPickingPointWithCertifiedRadio(gps, radio);
	
	}
	
	@Test
	public void testGetNearestPickingPoint() {
		int numLocker1=3;
		int numLocker2=7;
		Locker locker1=new Locker(numLocker1);
		Locker locker2=new Locker(numLocker2);
		Locker lockers[]= {locker1,locker2};
		
		GPSCoordinate gps1=new GPSCoordinate(60,120);
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20,40);
		double m31=20000;
		GPSCoordinate gps2=new GPSCoordinate(20,157);
		LocalTime openingHour2=LocalTime.of(8,00);
		LocalTime closingHour2=LocalTime.of(21,30);
		double m32=20000;
		
		PickingPoint pickingPoint1=new PackageLocker(gps1,openingHour1,closingHour1,lockers,m31);
		PickingPoint pickingPoint2=new PackageLocker(gps2,openingHour2,closingHour2,lockers,m32);
		
		List<PickingPoint> pickingPoints=new ArrayList<>();
		pickingPoints.add(pickingPoint1);
		pickingPoints.add(pickingPoint2);
		
		PickingPointSystem pickingPointSystem=new PickingPointSystem(pickingPoints);
		GPSCoordinate gps=new GPSCoordinate(63,122);
		assertNotNull(pickingPointSystem.getNearestPickingPoint(gps));
		assertEquals(pickingPoint1,pickingPointSystem.getNearestPickingPoint(gps));
		assertNotSame(pickingPoint1,pickingPointSystem.getNearestPickingPoint(gps));
	}
	
	@Test(expected=NullPointerException.class)
	public void testGetNearestPickingPointGpsNull() {
		int numLocker1=3;
		int numLocker2=7;
		Locker locker1=new Locker(numLocker1);
		Locker locker2=new Locker(numLocker2);
		Locker lockers[]= {locker1,locker2};
		
		GPSCoordinate gps1=new GPSCoordinate(60,120);
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20,40);
		double m31=20000;
		GPSCoordinate gps2=new GPSCoordinate(20,157);
		LocalTime openingHour2=LocalTime.of(8,00);
		LocalTime closingHour2=LocalTime.of(21,30);
		double m32=50000;
		
		PickingPoint pickingPoint1=new PackageLocker(gps1,openingHour1,closingHour1,lockers,m31);
		PickingPoint pickingPoint2=new PackageLocker(gps2,openingHour2,closingHour2,lockers,m32);
		
		List<PickingPoint> pickingPoints=new ArrayList<>();
		pickingPoints.add(pickingPoint1);
		pickingPoints.add(pickingPoint2);
		
		PickingPointSystem pickingPointSystem=new PickingPointSystem(pickingPoints);
		GPSCoordinate gps=null;
		pickingPointSystem.getNearestPickingPoint(gps);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetNearestPickingPointNoPickingPoint() {
		PickingPointSystem pickingPointSystem=new PickingPointSystem();
		GPSCoordinate gps=new GPSCoordinate(78,92);
		pickingPointSystem.getNearestPickingPoint(gps);
	}
	
	@Test
	public void testGetNearestWithCOD() {
		int numLocker1=3;
		int numLocker2=7;
		Locker locker1=new Locker(numLocker1);
		Locker locker2=new Locker(numLocker2);
		Locker lockers[]= {locker1,locker2};
		
		GPSCoordinate gps1=new GPSCoordinate(60,120);
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20,40);
		double m31=20000;
		GPSCoordinate gps2=new GPSCoordinate(70,134);
		LocalTime openingHour2=LocalTime.of(8,00);
		LocalTime closingHour2=LocalTime.of(21,30);
		int maxCapacity=20;
		double m32=20000;
		
		PickingPoint pickingPoint1=new PackageLocker(gps1,openingHour1,closingHour1,lockers,m31);
		PickingPoint pickingPoint2=new Kiosk(gps2,openingHour2,closingHour2,maxCapacity,m32);
		
		List<PickingPoint> pickingPoints=new ArrayList<>();
		pickingPoints.add(pickingPoint1);
		pickingPoints.add(pickingPoint2);
		
		PickingPointSystem pickingPointSystem=new PickingPointSystem(pickingPoints);
		GPSCoordinate gps=new GPSCoordinate(63,122);
		assertNotNull(pickingPointSystem.getNearestWithCOD(gps));
		assertEquals(pickingPoint2,pickingPointSystem.getNearestWithCOD(gps));
		assertNotSame(pickingPoint2,pickingPointSystem.getNearestWithCOD(gps));
	}
	
	
	@Test(expected=NullPointerException.class)
	public void testGetNearestWithCODGpsNull() {
		int numLocker1=3;
		int numLocker2=7;
		Locker locker1=new Locker(numLocker1);
		Locker locker2=new Locker(numLocker2);
		Locker lockers[]= {locker1,locker2};
		
		GPSCoordinate gps1=new GPSCoordinate(60,120);
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20,40);
		double m31=20000;
		GPSCoordinate gps2=new GPSCoordinate(70,134);
		LocalTime openingHour2=LocalTime.of(8,00);
		LocalTime closingHour2=LocalTime.of(21,30);
		int maxCapacity=20;
		double m32=20000;
		
		PickingPoint pickingPoint1=new PackageLocker(gps1,openingHour1,closingHour1,lockers,m31);
		PickingPoint pickingPoint2=new Kiosk(gps2,openingHour2,closingHour2,maxCapacity,m32);
		
		List<PickingPoint> pickingPoints=new ArrayList<>();
		pickingPoints.add(pickingPoint1);
		pickingPoints.add(pickingPoint2);
		
		PickingPointSystem pickingPointSystem=new PickingPointSystem(pickingPoints);
		GPSCoordinate gps=null;
		pickingPointSystem.getNearestWithCOD(gps);
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetNearestWithCODNoPickingPointsWithCOD() {
		int numLocker1=3;
		int numLocker2=7;
		Locker locker1=new Locker(numLocker1);
		Locker locker2=new Locker(numLocker2);
		Locker lockers[]= {locker1,locker2};
		
		GPSCoordinate gps1=new GPSCoordinate(60,120);
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20,40);
		double m31=20000;
		GPSCoordinate gps2=new GPSCoordinate(70,134);
		LocalTime openingHour2=LocalTime.of(8,00);
		LocalTime closingHour2=LocalTime.of(21,30);
		
		double m32=20000;
		
		PickingPoint pickingPoint1=new PackageLocker(gps1,openingHour1,closingHour1,lockers,m31);
		PickingPoint pickingPoint2=new PackageLocker(gps2,openingHour2,closingHour2,lockers,m32);
		
		List<PickingPoint> pickingPoints=new ArrayList<>();
		pickingPoints.add(pickingPoint1);
		pickingPoints.add(pickingPoint2);
		
		PickingPointSystem pickingPointSystem=new PickingPointSystem(pickingPoints);
		GPSCoordinate gps=new GPSCoordinate(63,122);
		pickingPointSystem.getNearestWithCOD(gps);
	}
	
	@Test
	public void testGetNearestWithCertified() {
		int numLocker1=3;
		int numLocker2=7;
		Locker locker1=new Locker(numLocker1);
		Locker locker2=new Locker(numLocker2);
		Locker lockers[]= {locker1,locker2};
		
		GPSCoordinate gps1=new GPSCoordinate(60,120);
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20,40);
		double m31=20000;
		GPSCoordinate gps2=new GPSCoordinate(70,134);
		LocalTime openingHour2=LocalTime.of(8,00);
		LocalTime closingHour2=LocalTime.of(21,30);

		
		PickingPoint pickingPoint1=new PackageLocker(gps1,openingHour1,closingHour1,lockers,m31);
		PickingPoint pickingPoint2=new PostOffice(gps2,openingHour2,closingHour2);
		
		List<PickingPoint> pickingPoints=new ArrayList<>();
		pickingPoints.add(pickingPoint1);
		pickingPoints.add(pickingPoint2);
		
		PickingPointSystem pickingPointSystem=new PickingPointSystem(pickingPoints);
		GPSCoordinate gps=new GPSCoordinate(63,122);
		assertNotNull(pickingPointSystem.getNearestWithCertified(gps));
		assertEquals(pickingPoint2,pickingPointSystem.getNearestWithCertified(gps));
		assertNotSame(pickingPoint2,pickingPointSystem.getNearestWithCertified(gps));
	}
	
	@Test(expected=NullPointerException.class)
	public void testGetNearestWithCertifiedGpsNull() {
		int numLocker1=3;
		int numLocker2=7;
		Locker locker1=new Locker(numLocker1);
		Locker locker2=new Locker(numLocker2);
		Locker lockers[]= {locker1,locker2};
		
		GPSCoordinate gps1=new GPSCoordinate(60,120);
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20,40);
		double m31=20000;
		GPSCoordinate gps2=new GPSCoordinate(70,134);
		LocalTime openingHour2=LocalTime.of(8,00);
		LocalTime closingHour2=LocalTime.of(21,30);

		
		PickingPoint pickingPoint1=new PackageLocker(gps1,openingHour1,closingHour1,lockers,m31);
		PickingPoint pickingPoint2=new PostOffice(gps2,openingHour2,closingHour2);
		
		List<PickingPoint> pickingPoints=new ArrayList<>();
		pickingPoints.add(pickingPoint1);
		pickingPoints.add(pickingPoint2);
		
		PickingPointSystem pickingPointSystem=new PickingPointSystem(pickingPoints);
		GPSCoordinate gps=null;
		pickingPointSystem.getNearestWithCertified(gps);
	}
	
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetNearestWithCertifiedNoPickingPoint() {
		int numLocker1=3;
		int numLocker2=7;
		Locker locker1=new Locker(numLocker1);
		Locker locker2=new Locker(numLocker2);
		Locker lockers[]= {locker1,locker2};
		
		GPSCoordinate gps1=new GPSCoordinate(60,120);
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20,40);
		double m31=20000;
		GPSCoordinate gps2=new GPSCoordinate(70,134);
		LocalTime openingHour2=LocalTime.of(8,00);
		LocalTime closingHour2=LocalTime.of(21,30);
		int maxCapacity=20;
		double m32=20000;
		
		PickingPoint pickingPoint1=new PackageLocker(gps1,openingHour1,closingHour1,lockers,m31);
		PickingPoint pickingPoint2=new Kiosk(gps2,openingHour2,closingHour2,maxCapacity,m32);
		
		List<PickingPoint> pickingPoints=new ArrayList<>();
		pickingPoints.add(pickingPoint1);
		pickingPoints.add(pickingPoint2);
		
		PickingPointSystem pickingPointSystem=new PickingPointSystem(pickingPoints);
		GPSCoordinate gps=new GPSCoordinate(63,122);
		@SuppressWarnings("unused")
		PickingPoint pickignPoint=pickingPointSystem.getNearestWithCertified(gps);
	}
	
	@Test
	public void testListPickingPointFreeSpace() {
		int numLocker1=3;
		Locker locker1=new Locker(numLocker1);
		
		
		Locker lockers[]= {locker1};
		
		String id1="1234567895";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=0.1;
		double length=0.1;
		double height=0.1;
		double price=50;
		boolean paid=true;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		
		
		GPSCoordinate gps1=new GPSCoordinate(60,120);
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20,40);
		double m31=20000;
		GPSCoordinate gps2=new GPSCoordinate(70,134);
		LocalTime openingHour2=LocalTime.of(8,00);
		LocalTime closingHour2=LocalTime.of(21,30);
		int maxCapacity=20;
		double m32=20000;
		
		PickingPoint pickingPoint1=new PackageLocker(gps1,openingHour1,closingHour1,lockers,m31);
		pickingPoint1.addPackage(package1);
		PickingPoint pickingPoint2=new Kiosk(gps2,openingHour2,closingHour2,maxCapacity,m32);
		
		List<PickingPoint> pickingPoints=new ArrayList<>();
		pickingPoints.add(pickingPoint1);
		pickingPoints.add(pickingPoint2);
		
		PickingPointSystem pickingPointSystem=new PickingPointSystem(pickingPoints);
		List<PickingPoint> pickingPointsFreeSpace=new ArrayList<>();
		pickingPointsFreeSpace.add(pickingPoint2);
		
		assertNotNull(pickingPointSystem.listPickingPointFreeSpace());
		assertNotSame(pickingPointsFreeSpace,pickingPointSystem.listPickingPointFreeSpace());
		assertEquals(pickingPointsFreeSpace,pickingPointSystem.listPickingPointFreeSpace());
	}
	
	@Test
	public void testGetNumberPickingPoints() {
		int numLocker1=3;
		int numLocker2=7;
		Locker locker1=new Locker(numLocker1);
		Locker locker2=new Locker(numLocker2);
		Locker lockers[]= {locker1,locker2};
		
		GPSCoordinate gps1=new GPSCoordinate(60,120);
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20,40);
		double m31=20000;
		GPSCoordinate gps2=new GPSCoordinate(70,134);
		LocalTime openingHour2=LocalTime.of(8,00);
		LocalTime closingHour2=LocalTime.of(21,30);

		
		PickingPoint pickingPoint1=new PackageLocker(gps1,openingHour1,closingHour1,lockers,m31);
		PickingPoint pickingPoint2=new PostOffice(gps2,openingHour2,closingHour2);
		
		List<PickingPoint> pickingPoints=new ArrayList<>();
		pickingPoints.add(pickingPoint1);
		pickingPoints.add(pickingPoint2);
		int numPickingPoints=2;
		
		PickingPointSystem pickingPointSystem=new PickingPointSystem(pickingPoints);
		assertEquals(numPickingPoints,pickingPointSystem.getNumberPickingPoints());
	}
	
	@Test
	public void testToString() {
		int numLocker1=3;
		int numLocker2=7;
		Locker locker1=new Locker(numLocker1);
		Locker locker2=new Locker(numLocker2);
		Locker lockers[]= {locker1,locker2};
		
		GPSCoordinate gps1=new GPSCoordinate(60,120);
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20,40);
		double m31=20000;
		GPSCoordinate gps2=new GPSCoordinate(70,134);
		LocalTime openingHour2=LocalTime.of(8,00);
		LocalTime closingHour2=LocalTime.of(21,30);
		
		PickingPoint pickingPoint1=new PackageLocker(gps1,openingHour1,closingHour1,lockers,m31);
		PickingPoint pickingPoint2=new PostOffice(gps2,openingHour2,closingHour2);
		
		List<PickingPoint> pickingPoints=new ArrayList<>();
		pickingPoints.add(pickingPoint1);
		pickingPoints.add(pickingPoint2);
		
		PickingPointSystem pickingPointSystem=new PickingPointSystem(pickingPoints);
		assertNotNull(pickingPointSystem.toString());
		assertNotEquals(pickingPointSystem.toString(),"");
	}
	
	@Test
	public void testEquals() {
		int numLocker1=3;
		int numLocker2=7;
		Locker locker1=new Locker(numLocker1);
		Locker locker2=new Locker(numLocker2);
		Locker lockers[]= {locker1,locker2};
		
		GPSCoordinate gps1=new GPSCoordinate(60,120);
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20,40);
		double m31=20000;
		
		PickingPoint pickingPoint1=new PackageLocker(gps1,openingHour1,closingHour1,lockers,m31);
		
		List<PickingPoint> pickingPoints=new ArrayList<>();
		pickingPoints.add(pickingPoint1);
		
		PickingPointSystem pickingPointSystem1=new PickingPointSystem(pickingPoints);
		PickingPointSystem pickingPointSystem2=new PickingPointSystem(pickingPoints);
		
		assertTrue(pickingPointSystem1.equals(pickingPointSystem2));
	}
	
	@Test
	public void testEqualsSameObject() {
		int numLocker1=3;
		int numLocker2=7;
		Locker locker1=new Locker(numLocker1);
		Locker locker2=new Locker(numLocker2);
		Locker lockers[]= {locker1,locker2};
		
		GPSCoordinate gps1=new GPSCoordinate(60,120);
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20,40);
		double m31=20000;
		
		PickingPoint pickingPoint1=new PackageLocker(gps1,openingHour1,closingHour1,lockers,m31);
		
		List<PickingPoint> pickingPoints=new ArrayList<>();
		pickingPoints.add(pickingPoint1);
		
		PickingPointSystem pickingPointSystem1=new PickingPointSystem(pickingPoints);
		
		assertTrue(pickingPointSystem1.equals(pickingPointSystem1));
	}
	
	@Test
	public void testEqualsDifferentNumberOfPickingPoints() {
		int numLocker1=3;
		int numLocker2=7;
		Locker locker1=new Locker(numLocker1);
		Locker locker2=new Locker(numLocker2);
		Locker lockers[]= {locker1,locker2};
		
		GPSCoordinate gps1=new GPSCoordinate(60,120);
		GPSCoordinate gps2=new GPSCoordinate(79,100);
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20,40);
		double m31=20000;
		
		PickingPoint pickingPoint1=new PackageLocker(gps1,openingHour1,closingHour1,lockers,m31);
		PickingPoint pickingPoint2=new PackageLocker(gps2,openingHour1,closingHour1,lockers,m31);
		
		List<PickingPoint> pickingPoints=new ArrayList<>();
		pickingPoints.add(pickingPoint1);
		pickingPoints.add(pickingPoint2);
		
		List<PickingPoint> pickingPoints1=new ArrayList<>();
		pickingPoints1.add(pickingPoint1);
		
		PickingPointSystem pickingPointSystem1=new PickingPointSystem(pickingPoints);
		PickingPointSystem pickingPointSystem2=new PickingPointSystem(pickingPoints1);
		
		assertFalse(pickingPointSystem1.equals(pickingPointSystem2));
	}
	
	@Test
	public void testEqualsDifferentPickingPoints() {
		int numLocker1=3;
		int numLocker2=7;
		Locker locker1=new Locker(numLocker1);
		Locker locker2=new Locker(numLocker2);
		Locker lockers[]= {locker1,locker2};
		
		GPSCoordinate gps1=new GPSCoordinate(60,120);
		GPSCoordinate gps2=new GPSCoordinate(79,100);
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20,40);
		double m31=20000;
		
		PickingPoint pickingPoint1=new PackageLocker(gps1,openingHour1,closingHour1,lockers,m31);
		PickingPoint pickingPoint2=new PackageLocker(gps2,openingHour1,closingHour1,lockers,m31);
		
		List<PickingPoint> pickingPoints=new ArrayList<>();
		pickingPoints.add(pickingPoint2);
		
		List<PickingPoint> pickingPoints1=new ArrayList<>();
		pickingPoints1.add(pickingPoint1);
		
		PickingPointSystem pickingPointSystem1=new PickingPointSystem(pickingPoints);
		PickingPointSystem pickingPointSystem2=new PickingPointSystem(pickingPoints1);
		
		assertFalse(pickingPointSystem1.equals(pickingPointSystem2));
	}
	
}

