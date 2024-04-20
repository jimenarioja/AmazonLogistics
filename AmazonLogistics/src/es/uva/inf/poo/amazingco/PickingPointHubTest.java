package es.uva.inf.poo.amazingco;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import es.uva.inf.poo.maps.GPSCoordinate;

public class PickingPointHubTest {

	@Test
	public void testConstructorPickingPointHub() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30); 
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		
		assertNotNull(pickingPointHub);
		assertEquals(gps,pickingPointHub.getGps());
		assertEquals(openingHour,pickingPointHub.getOpeningHour());
		assertEquals(closingHour,pickingPointHub.getClosingHour());
		assertEquals(maxCapacity,pickingPointHub.getMaxCapacityGroupablePoint());
		assertEquals(cubicMetres,pickingPointHub.getCubicMetres(),0.1);
		assertArrayEquals(groupablePoint,pickingPointHub.getGroupablePoints());
		assertNotSame(groupablePoint,pickingPointHub.getGroupablePoints());
	}
	
	@Test(expected=NullPointerException.class)
	public void testConstructorPickingPointHubGpsNull() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		GPSCoordinate gps2=null;
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		@SuppressWarnings("unused")
		PickingPointHub pickingPointHub=new PickingPointHub(gps2,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
	}

	@Test(expected=NullPointerException.class)
	public void testConstructorPickingPointHubOpeningHourNull() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime openingHour2=null;
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		@SuppressWarnings("unused")
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour2,closingHour,groupablePoint,maxCapacity,cubicMetres);
	}

	@Test(expected=NullPointerException.class)
	public void testConstructorPickingPointHubClosingHourNull() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		LocalTime closingHour2=null;
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		@SuppressWarnings("unused")
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour2,groupablePoint,maxCapacity,cubicMetres);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorPickingPointHubOpeningHourNotBeforeClosingHour() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		LocalTime openingHour2=LocalTime.of(12,30);
		LocalTime closingHour2=LocalTime.of(10,45);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		@SuppressWarnings("unused")
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour2,closingHour2,groupablePoint,maxCapacity,cubicMetres);	
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorPickingPointHubMaxCapacityLessThanTwo() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=1;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		@SuppressWarnings("unused")
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorPickingPointHubLessThanTwoGroupablePoints() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		
		double cubicMetres=300000;
		double cubicMetres1=50000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		
		GroupablePoint groupablePoint[]= {groupablePoint1};
		@SuppressWarnings("unused")
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorPickingPointHubExceedsMaxCapacityGroupablePoints() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=2;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		@SuppressWarnings("unused")
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorPickingPointHubNotSameGps() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		GPSCoordinate gps1=new GPSCoordinate(55,20);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps1,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps1,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		@SuppressWarnings("unused")
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorPickingPointHubExceedsMaxCapacityCubicMetres() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=100000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		@SuppressWarnings("unused")
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
	}
	
	@Test
	public void testConstructorCopyPickingPointHub() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		PickingPointHub pickingPointHubCopy=new PickingPointHub(pickingPointHub);
		
		assertNotNull(pickingPointHubCopy);
		assertNotSame(pickingPointHub,pickingPointHubCopy);
		assertEquals(pickingPointHub,pickingPointHubCopy);
		assertEquals(pickingPointHub.getMaxCapacityGroupablePoint(),pickingPointHubCopy.getMaxCapacityGroupablePoint());
		assertEquals(pickingPointHub.getCubicMetres(),pickingPointHubCopy.getCubicMetres(),0.1);
		assertArrayEquals(pickingPointHub.getGroupablePoints(),pickingPointHub.getGroupablePoints());
		assertNotSame(pickingPointHub.getGroupablePoints(),pickingPointHub.getGroupablePoints());
	}
	
	@Test
	public void testGettersSettersCubicMetres() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		double newCubicMetres=725000;
		pickingPointHub.setCubicMetres(newCubicMetres);
		assertEquals(newCubicMetres,pickingPointHub.getCubicMetres(),0.1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGettersSettersCubicMetresNegative() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		double newCubicMetres=-725000;
		pickingPointHub.setCubicMetres(newCubicMetres);
	}
	
	@Test
	public void testGettersSettersMaxCapacityGroupablePoints() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		int newMaxCapacity=10;
		pickingPointHub.setMaxCapacityGroupablePoint(newMaxCapacity);
		assertEquals(newMaxCapacity,pickingPointHub.getMaxCapacityGroupablePoint());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGettersSettersMaxCapacityGroupablePointsLessThanTwo() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		int newMaxCapacity=1;
		pickingPointHub.setMaxCapacityGroupablePoint(newMaxCapacity);	
	}
	
	@Test
	public void testOutOfService() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		pickingPointHub.outOfService();
		assertTrue(pickingPointHub.isOutOfService());
		assertTrue(pickingPointHub.getGroupablePoint(groupablePoint1.getId()).isOutOfService());
		assertTrue(pickingPointHub.getGroupablePoint(groupablePoint2.getId()).isOutOfService());
		assertTrue(pickingPointHub.getGroupablePoint(groupablePoint3.getId()).isOutOfService());
	
	}
	
	@Test
	public void testSetAllNotOutOfService() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		groupablePoint1.outOfService();
		groupablePoint2.outOfService();
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		pickingPointHub.setAllNotOutOfService();
		assertFalse(pickingPointHub.isOutOfService());
		assertFalse(pickingPointHub.getGroupablePoint(groupablePoint1.getId()).isOutOfService());
		assertFalse(pickingPointHub.getGroupablePoint(groupablePoint2.getId()).isOutOfService());
		assertFalse(pickingPointHub.getGroupablePoint(groupablePoint3.getId()).isOutOfService());
	}
	
	@Test
	public void testGetEmptyCubicMetres() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		double emptyCubicMetres=147000;
		assertEquals(emptyCubicMetres,pickingPointHub.getEmptyCubicMetres(),0.1);
	}
	
	@Test
	public void testGetUsedCubicMetres() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		double emptyCubicMetres=153000;
		assertEquals(emptyCubicMetres,pickingPointHub.getCubicMetresUsed(),0.1);
	}
	
	@Test
	public void testIsFullGroupablePoints() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=3;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		assertTrue(pickingPointHub.isFullGroupablePoints());
	}
	
	@Test
	public void testIsNotFullGroupablePoints() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		assertFalse(pickingPointHub.isFullGroupablePoints());
	}
	
	@Test
	public void testAddGroupablePoint() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		
		pickingPointHub.addGroupablePoint(groupablePoint3);
		assertEquals(groupablePoint3,pickingPointHub.getGroupablePoint(groupablePoint3.getId()));
	}
	
	@Test(expected=NullPointerException.class)
	public void testAddGroupablePointNull() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=null;
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		
		pickingPointHub.addGroupablePoint(groupablePoint3);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddGroupablePointIsFull() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=2;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		
		pickingPointHub.addGroupablePoint(groupablePoint3);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddGroupablePointNotSameGps() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		GPSCoordinate gps3=new GPSCoordinate(46,84);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps3,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		
		pickingPointHub.addGroupablePoint(groupablePoint3);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddGroupablePointIsOnList() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		
		pickingPointHub.addGroupablePoint(groupablePoint2);
	}
	
	@Test
	public void testRemoveGroupablePoint() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		
		pickingPointHub.removeGroupablePoint(groupablePoint2.getId());
		int numPoints=2;
		assertEquals(numPoints,pickingPointHub.getNumPoints());
		assertFalse(pickingPointHub.containsGroupablePoint(groupablePoint2));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRemoveGroupablePointLessThanTwo() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		pickingPointHub.removeGroupablePoint(groupablePoint2.getId());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRemoveGroupablePointNotFound() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		int id=groupablePoint1.getId()+groupablePoint2.getId();
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		pickingPointHub.removeGroupablePoint(id);
	}
	
	@Test
	public void testGetGroupablePoints() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		
		assertNotNull(pickingPointHub.getGroupablePoints());
		assertArrayEquals(groupablePoint,pickingPointHub.getGroupablePoints());
	}
	
	@Test
	public void testGetGroupablePoint() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		
		assertNotNull(pickingPointHub.getGroupablePoint(groupablePoint2.getId()));
		assertEquals(groupablePoint2,pickingPointHub.getGroupablePoint(groupablePoint2.getId()));
		assertNotSame(groupablePoint2,pickingPointHub.getGroupablePoint(groupablePoint2.getId()));
		assertTrue(pickingPointHub.getGroupablePoint(groupablePoint2.getId()).equals(groupablePoint2));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetGroupablePointIdNotFound() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		int id=groupablePoint1.getId()+groupablePoint2.getId()+groupablePoint3.getId();
		pickingPointHub.getGroupablePoint(id);
	}
	
	@Test
	public void testContainsGroupablePoint() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		
		assertTrue(pickingPointHub.containsGroupablePoint(groupablePoint2));
	}
	
	@Test
	public void testNotContainsGroupablePoint() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		
		assertFalse(pickingPointHub.containsGroupablePoint(groupablePoint3));
	}
	
	@Test(expected=NullPointerException.class)
	public void testContainsGroupablePointNull() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		GroupablePoint groupablePoint4=null;
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		pickingPointHub.containsGroupablePoint(groupablePoint4);
	}
	
	@Test
	public void testIsFull() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=2;
		int maxCapacity1=1;

		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		
		
		
		String id1="1234567895";
		String id2="2134567895";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=10;
		double length=10;
		double height=10;
		double price=50;
		boolean paid=true;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		Package package2=new Package(id2,expirationDate1,width,length,height,price,paid);
		Package packages1[]= {package1};
		Package packages2[]= {package2};
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,packages1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity1,packages2,cubicMetres2);
	
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		assertTrue(pickingPointHub.isFull());
	}
	
	@Test
	public void testNotIsFull() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		
		assertFalse(pickingPointHub.isFull());
	}
	
	@Test
	public void testGetNumPoints() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		int numPoints=3;
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		assertEquals(numPoints,pickingPointHub.getNumPoints());
	}
	
	@Test
	public void testGetPointsNotFull() {
		int numLocker1=3;
		Locker locker1=new Locker(numLocker1);
		Locker lockers[]= {locker1};
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20, 40);
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		String id1="1234567895";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=0.1;
		double length=0.1;
		double height=0.1;
		double price=50;
		boolean paid=true;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		
		GroupablePoint groupablePointFull=new PackageLocker(gps,openingHour1,closingHour1,lockers,cubicMetres1);
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		groupablePointFull.addPackage(package1);
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3,groupablePointFull};
		GroupablePoint groupablePointsNotFull[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		int numPointsNotFull=3;
		assertNotNull(pickingPointHub.getPointsNotFull());
		assertEquals(numPointsNotFull,pickingPointHub.getPointsNotFull().length);
		assertArrayEquals(groupablePointsNotFull,pickingPointHub.getPointsNotFull());
		assertNotSame(groupablePointsNotFull,pickingPointHub.getPointsNotFull());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetPointsNotFullNoPoints() {
		int numLocker1=3;
		Locker locker1=new Locker(numLocker1);
		Locker lockers[]= {locker1};
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20, 40);
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;

		double cubicMetres=300000;
		double cubicMetres1=50000;

		String id1="1234567895";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=0.1;
		double length=0.1;
		double height=0.1;
		double price=50;
		boolean paid=true;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		String id2="1234567895";
		Package package2=new Package(id2,expirationDate1,width,length,height,price,paid);
		GroupablePoint groupablePointFull=new PackageLocker(gps,openingHour1,closingHour1,lockers,cubicMetres1);
		GroupablePoint groupablePointFull2=new PackageLocker(gps,openingHour1,closingHour1,lockers,cubicMetres1);
	
		groupablePointFull.addPackage(package1);
		groupablePointFull2.addPackage(package2);
		
		GroupablePoint groupablePoint[]= {groupablePointFull,groupablePointFull2};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		pickingPointHub.getPointsNotFull();
	}
	
	@Test
	public void testGetNumPointsWithCOD() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=6;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		int numLocker1=3;
		Locker locker1=new Locker(numLocker1);
		Locker lockers[]= {locker1};

		
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		GroupablePoint groupablePoint4=new PackageLocker(gps,openingHour,closingHour,lockers,cubicMetres1);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3,groupablePoint4};
		int numPointsWithCOD=3;
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		assertEquals(numPointsWithCOD,pickingPointHub.getNumPointsWithCOD());
	}
	
	@Test
	public void testGetPointsWithCOD() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=5;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		int numLocker1=3;
		Locker locker1=new Locker(numLocker1);
		Locker lockers[]= {locker1};
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		GroupablePoint groupablePoint4=new PackageLocker(gps,openingHour,closingHour,lockers,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3,groupablePoint4};
		GroupablePoint groupablePointsWithCOD[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		int numPointsWithCOD=3;
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		assertNotNull(pickingPointHub.getPointsWithCOD());
		assertEquals(numPointsWithCOD,pickingPointHub.getPointsWithCOD().length);
		assertArrayEquals(groupablePointsWithCOD,pickingPointHub.getPointsWithCOD());
		assertNotSame(groupablePointsWithCOD,pickingPointHub.getPointsWithCOD());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testGetPointsWithCODNoPoints() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=3;

		double cubicMetres=300000;

		
		int numLocker1=3;
		Locker locker1=new Locker(numLocker1);
		Locker lockers[]= {locker1};
		
		GroupablePoint groupablePoint1=new PackageLocker(gps,openingHour,closingHour,lockers,cubicMetres);
		GroupablePoint groupablePoint2=new PackageLocker(gps,openingHour,closingHour,lockers,cubicMetres);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		pickingPointHub.getPointsWithCOD();
	}
	
	@Test
	public void testGetNumPointsEmptySpaceWithCOD() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=6;
		int maxCapacity1=1;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
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
		
		Package packages[]= {package1};
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,packages,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		GroupablePoint groupablePoint4=new PackageLocker(gps,openingHour,closingHour,lockers,cubicMetres1);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3,groupablePoint4};
		int numPointsEmptySpaceWithCOD=2;
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		assertEquals(numPointsEmptySpaceWithCOD,pickingPointHub.getNumPointsEmptySpaceWithCOD());
	}
	
	@Test
	public void testGetNumPointsEmptySpaceWithCODNoPointsFreeWithCOD() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=6;
		int maxCapacity1=1;
		int maxCapacity2=1;
		
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		
		
		
		String id1="1234567895";
		String id2="2134567895";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=10;
		double length=10;
		double height=10;
		double price=50;
		boolean paid=true;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		Package package2=new Package(id2,expirationDate1,width,length,height,price,paid);

		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		groupablePoint1.addPackage(package1);
		groupablePoint2.addPackage(package2);
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		int numPoints=0;
		assertEquals(numPoints,pickingPointHub.getNumPointsEmptySpaceWithCOD());
	}
	
	
	
	@Test
	public void testGetPointsEmptySpaceWithCOD() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=3;
		int maxCapacity1=1;
		int maxCapacity2=10;
		
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
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
		
		
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint4=new PackageLocker(gps,openingHour,closingHour,lockers,cubicMetres3);
		groupablePoint1.addPackage(package1);
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint4};
		GroupablePoint groupablePoints[]= {groupablePoint2};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		assertNotNull(pickingPointHub.getPointsEmptySpaceWithCOD());
		
		
		assertNotNull(pickingPointHub.getPointsEmptySpaceWithCOD());
		assertArrayEquals(pickingPointHub.getPointsEmptySpaceWithCOD(),groupablePoints);
		assertNotSame(pickingPointHub.getPointsEmptySpaceWithCOD(),groupablePoints);
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetPointsEmptySpaceWithCODNoPoints() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=3;
		int maxCapacity1=200;
		
		double cubicMetres=300000;
		double cubicMetres1=50000;
		
		
		int numLocker1=3;
		Locker locker1=new Locker(numLocker1);
		Locker lockers[]= {locker1};
		
		String id1="1234567895";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=10;
		double length=10;
		double height=10;
		double price=50;
		boolean paid=true;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		
		Package packages[]= {package1};
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,packages,cubicMetres1);
		GroupablePoint groupablePoint4=new PackageLocker(gps,openingHour,closingHour,lockers,cubicMetres);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint4};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		pickingPointHub.getPointsEmptySpaceWithCOD();
		
	}
	
	@Test
	public void testGetEmptyCapacityGroupablePoints() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		int emptyCapacity=1;
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		assertEquals(emptyCapacity,pickingPointHub.getEmptyCapacityGroupablePoints());
		
	}
	
	@Test
	public void testGetNumPointsNotFull() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=1;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		String id1="1234567895";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=10;
		double length=10;
		double height=10;
		double price=50;
		boolean paid=true;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		
		Package packages[]= {package1};
		
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,packages,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		int numPointsNotFull=2;
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		assertEquals(numPointsNotFull,pickingPointHub.getNumPointsNotFull());
		
	}
	
	@Test
	public void testGetNumPointsFull() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		String id1="1234567895";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=10;
		double length=10;
		double height=10;
		double price=50;
		boolean paid=true;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		
		Package packages[]= {package1};
		
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,packages,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		int numPointsFull=1;
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		assertEquals(numPointsFull,pickingPointHub.getNumPointsFull());
	}
	
	@Test
	public void testGetPointsEmptySpaceAndNotOutOfService() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=1;
		int maxCapacity2=100;
		
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		String id1="1234567895";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=10;
		double length=10;
		double height=10;
		double price=50;
		boolean paid=true;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		
		Package packages[]= {package1};
		
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,packages,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity,cubicMetres3);
		groupablePoint3.outOfService();
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};

		GroupablePoint groupablePoints2[]= {groupablePoint2};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		assertNotNull(pickingPointHub.getPointsEmptySpaceAndNotOutOfService());
		assertArrayEquals(pickingPointHub.getPointsEmptySpaceAndNotOutOfService(),groupablePoints2);
		assertNotSame(pickingPointHub.getPointsEmptySpaceAndNotOutOfService(),groupablePoint2);
	}

	@Test
	public void testExpandCapacity() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		int gap=3;
		pickingPointHub.expandCapacity(gap);
		assertEquals(pickingPointHub.getMaxCapacityGroupablePoint(),gap+maxCapacity);
		assertTrue(pickingPointHub.containsGroupablePoint(groupablePoint1));
		assertTrue(pickingPointHub.containsGroupablePoint(groupablePoint2));
		assertTrue(pickingPointHub.containsGroupablePoint(groupablePoint3));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testExpandCapacityNegativeGap() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		int gap=-3;
		pickingPointHub.expandCapacity(gap);
	}
	
	@Test
	public void testReduceCapacity() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		int gap=1;
		pickingPointHub.reduceCapacity(gap);
		assertEquals(pickingPointHub.getMaxCapacityGroupablePoint(),maxCapacity-gap);
		assertTrue(pickingPointHub.containsGroupablePoint(groupablePoint1));
		assertTrue(pickingPointHub.containsGroupablePoint(groupablePoint2));
		assertTrue(pickingPointHub.containsGroupablePoint(groupablePoint3));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testReduceCapacityNegativeGap() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		int gap=-10;
		pickingPointHub.reduceCapacity(gap);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testReduceCapacityLessThanTwoGroupablePoint() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		int gap=3;
		pickingPointHub.reduceCapacity(gap);
	}
	
	@Test
	public void testGetNumOutOfService() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		groupablePoint1.outOfService();
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		int numOutOfService=1;
		assertEquals(numOutOfService,pickingPointHub.getNumOutOfService());
	}
	
	@Test
	public void testGetNumNotOutOfService() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		groupablePoint1.outOfService();
		groupablePoint2.outOfService();
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		int numNotOutOfService=1;
		assertEquals(numNotOutOfService,pickingPointHub.getNumNotOutOfService());
	}
	
	@Test
	public void testHasCODTrue() {
	
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		
		assertTrue(pickingPointHub.hasCOD());
	
	}
	
	@Test
	public void testHasCODFalse() {
		int numLocker1=3;
		Locker locker1=new Locker(numLocker1);
		Locker lockers[]= {locker1};
		LocalTime openingHour1=LocalTime.of(9,15);
		LocalTime closingHour1=LocalTime.of(20, 40);
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		
		String id1="1234567895";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=0.1;
		double length=0.1;
		double height=0.1;
		double price=50;
		boolean paid=true;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		String id2="1234567895";
		Package package2=new Package(id2,expirationDate1,width,length,height,price,paid);
		GroupablePoint groupablePointFull=new PackageLocker(gps,openingHour1,closingHour1,lockers,cubicMetres1);
		GroupablePoint groupablePointFull2=new PackageLocker(gps,openingHour1,closingHour1,lockers,cubicMetres2);
	
		groupablePointFull.addPackage(package1);
		groupablePointFull2.addPackage(package2);
		
		GroupablePoint groupablePoint[]= {groupablePointFull,groupablePointFull2};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		assertFalse(pickingPointHub.hasCOD());
	}
	
	@Test
	public void testCanStorePackageTrue() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		
		
		String id1="1234567895";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=10;
		double length=10;
		double height=10;
		double price=50;
		boolean paid=true;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		
		assertTrue(pickingPointHub.canStorePackage(package1));
	}
	
	@Test
	public void testCanStorePackageFalse() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		
		String id1="1234567895";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=1000;
		double length=1000;
		double height=1000;
		double price=50;
		boolean paid=true;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		
		assertFalse(pickingPointHub.canStorePackage(package1));
	}
	 
	
	@Test
	public void testCanStorePackageCertified() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;

		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		
		int numLocker1=3;
		Locker locker1=new Locker(numLocker1);
		Locker lockers[]= {locker1};
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new PackageLocker(gps,openingHour,closingHour,lockers,cubicMetres2);
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		
		
		String id1="1234567895";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=10;
		double length=10;
		double height=10;
		double price=50;
		boolean paid=true;
		String dni="123456789A";
		List<String> authorizedDni=new ArrayList<>();
		authorizedDni.add(dni);
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid,authorizedDni);
		
		assertFalse(pickingPointHub.canStorePackage(package1));
	}
	
	@Test
	public void testCanStorePackageNotPaidNotPosibleCOD() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;

		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		
		int numLocker1=3;
		Locker locker1=new Locker(numLocker1);
		Locker lockers[]= {locker1};
		GroupablePoint groupablePoint1=new PackageLocker(gps,openingHour,closingHour,lockers,cubicMetres1);
		GroupablePoint groupablePoint2=new PackageLocker(gps,openingHour,closingHour,lockers,cubicMetres2);
	
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		
		
		String id1="1234567895";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=10;
		double length=10;
		double height=10;
		double price=50;
		boolean paid=false;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		
		assertFalse(pickingPointHub.canStorePackage(package1));
	}
	
	@Test
	public void testAddPackagePaid() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		
		
		String id1="1234567895";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=10;
		double length=10;
		double height=10;
		double price=50;
		boolean paid=true;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		
		pickingPointHub.addPackage(package1);
		assertTrue(pickingPointHub.getPackages().contains(package1));
	}
	
	@Test
	public void testAddPackageNotPaid() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		
		
		String id1="1234567895";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=10;
		double length=10;
		double height=10;
		double price=50;
		boolean paid=false;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		
		pickingPointHub.addPackage(package1);
		assertTrue(pickingPointHub.getPackages().contains(package1));
	}
	
	@Test
	public void testGetGroupablePointFromIdPackage() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);

		String id1="1234567895";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=10;
		double length=10;
		double height=10;
		double price=50;
		boolean paid=true;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		
		groupablePoint1.addPackage(package1);
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
	
		assertNotNull(pickingPointHub.getGroupablePoint(id1));
		assertEquals(groupablePoint1,pickingPointHub.getGroupablePoint(id1));
		assertNotSame(groupablePoint1,pickingPointHub.getGroupablePoint(id1));
	}
	
	@Test(expected=NullPointerException.class)
	public void testGetGroupablePointFromIdNullPackage() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		
		
		String id1="1234567895";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=10;
		double length=10;
		double height=10;
		double price=50;
		boolean paid=true;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		
		groupablePoint1.addPackage(package1);
		String id=null;
		pickingPointHub.getGroupablePoint(id);
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetGroupablePointFromIdEmptyPackage() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		
		
		String id1="1234567895";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=10;
		double length=10;
		double height=10;
		double price=50;
		boolean paid=true;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		
		groupablePoint1.addPackage(package1);
		String id="";
		pickingPointHub.getGroupablePoint(id);
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetGroupablePointFromIdPackageNotFound() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		
		
		String id1="1234567895";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=10;
		double length=10;
		double height=10;
		double price=50;
		boolean paid=true;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		
		groupablePoint1.addPackage(package1);
		String id="1234567890";
		pickingPointHub.getGroupablePoint(id);
		
	}
	
	@Test
	public void testTakeOutPackageToReturn() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		String id1="1234567895";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=10;
		double length=10;
		double height=10;
		double price=50;
		boolean paid=true;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		groupablePoint1.addPackage(package1);
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		
		pickingPointHub.takeOutPackageToReturn(package1);
		assertFalse(pickingPointHub.getPackages().contains(package1));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testTakeOutPackageToReturnNotFound() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		
		
		String id1="1234567895";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=10;
		double length=10;
		double height=10;
		double price=50;
		boolean paid=true;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		
		pickingPointHub.takeOutPackageToReturn(package1);
		
	}
	
	@Test
	public void testTakeOutPackageAsCustomer() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		String id1="1234567895";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=10;
		double length=10;
		double height=10;
		double price=50;
		boolean paid=true;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		groupablePoint1.addPackage(package1);
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		
		
		
		LocalDate pickUpDate=LocalDate.of(2023, 12, 22);
		LocalTime pickUpHour=LocalTime.of(12, 15);
		Package packageToPickUp=pickingPointHub.takeOutPackageAsCustomer(id1,pickUpDate,pickUpHour);
		assertNotNull(packageToPickUp);
		assertEquals(package1,packageToPickUp);
		assertNotSame(package1,packageToPickUp);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testTakeOutPackageAsCustomerNotFound() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		
		
		String id1="1234567895";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=10;
		double length=10;
		double height=10;
		double price=50;
		boolean paid=true;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		
		pickingPointHub.takeOutPackageToReturn(package1);
	}
	
	public void testHasCertified() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		
		
		assertFalse(pickingPointHub.hasCertified());
	
	}
	
	
	@Test
	public void testGetPackage() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		String id1="1234567895";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=10;
		double length=10;
		double height=10;
		double price=50;
		boolean paid=true;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		
		
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		groupablePoint1.addPackage(package1);
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		Package package2=pickingPointHub.getPackage(id1);
		
		assertNotNull(package2);
		assertNotSame(package1,package2);
		assertEquals(package1,package2);
	}

	@Test
	public void testGetNumPackages() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		String id1="1234567895";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=10;
		double length=10;
		double height=10;
		double price=50;
		boolean paid=true;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		
		int numPackages=1;
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		groupablePoint1.addPackage(package1);
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		
		
		
		assertEquals(numPackages,pickingPointHub.getNumPackages());
	}
	
	@Test
	public void testGetPackages() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		String id1="1234567895";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=10;
		double length=10;
		double height=10;
		double price=50;
		boolean paid=true;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		
		List<Package> packages1=new ArrayList<>();
		packages1.add(package1);
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		groupablePoint1.addPackage(package1);
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		
		
		
		assertNotNull(pickingPointHub.getPackages());
		assertEquals(packages1,pickingPointHub.getPackages());
		assertNotSame(packages1,pickingPointHub.getPackages());
	}
	
	@Test
	public void testToString() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		
		
		String id1="1234567895";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=10;
		double length=10;
		double height=10;
		double price=50;
		boolean paid=true;
		String empty="";
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		groupablePoint1.addPackage(package1);
		assertNotNull(pickingPointHub.toString());
		assertNotEquals(empty,pickingPointHub.toString());
	}
	
	@Test
	public void testClone() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(7,30);
		LocalTime closingHour=LocalTime.of(21,30);
		int maxCapacity=4;
		int maxCapacity1=200;
		int maxCapacity2=100;
		int maxCapacity3=70;
		double cubicMetres=300000;
		double cubicMetres1=50000;
		double cubicMetres2=78000;
		double cubicMetres3=25000;
		
		GroupablePoint groupablePoint1=new Kiosk(gps,openingHour,closingHour,maxCapacity1,cubicMetres1);
		GroupablePoint groupablePoint2=new Kiosk(gps,openingHour,closingHour,maxCapacity2,cubicMetres2);
		GroupablePoint groupablePoint3=new Kiosk(gps,openingHour,closingHour,maxCapacity3,cubicMetres3);
		
		GroupablePoint groupablePoint[]= {groupablePoint1,groupablePoint2,groupablePoint3};
		
		PickingPointHub pickingPointHub=new PickingPointHub(gps,openingHour,closingHour,groupablePoint,maxCapacity,cubicMetres);
		
		
		String id1="1234567895";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=10;
		double length=10;
		double height=10;
		double price=50;
		boolean paid=true;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		groupablePoint1.addPackage(package1);
		PickingPointHub pickingPointHubClone=pickingPointHub.clone();
		assertNotNull(pickingPointHubClone);
		assertNotSame(pickingPointHub,pickingPointHubClone);
		assertEquals(pickingPointHub,pickingPointHubClone);
	}
	
}

