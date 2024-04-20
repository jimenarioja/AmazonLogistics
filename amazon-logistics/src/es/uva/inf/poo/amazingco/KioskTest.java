package es.uva.inf.poo.amazingco;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;

import org.junit.Test;

import es.uva.inf.poo.maps.GPSCoordinate;

public class KioskTest {

	@Test
	public void testConstructorKiosk() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(9, 15);
		LocalTime closingHour=LocalTime.of(20, 40);
		int maxCapacity=20;
		double cubicMetres=100;
		double money=0;
		
		Kiosk kiosk=new Kiosk(gps,openingHour,closingHour,maxCapacity,cubicMetres);
		
		
		assertNotNull(kiosk);
		assertEquals(maxCapacity,kiosk.getMaxCapacityBox());
		assertEquals(cubicMetres,kiosk.getMaxCapacityCubicMetres(),0.1);
		assertEquals(money,kiosk.getMoney(),0.2);
	}
	
	@Test
	public void testConstructorKioskWithPackages() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(9, 15);
		LocalTime closingHour=LocalTime.of(20, 40);
		int maxCapacity=20;
		double cubicMetres=100;
		double money=0;
		String id1="1234721145";
		String id2="1234721189";
		LocalDate expirationDate1=LocalDate.of(2023, 11, 10);
		double width=0.1;
		double length=0.1;
		double height=0.1;
		double price=50;
		boolean paid=true;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		Package package2=new Package(id2,expirationDate1,width,length,height,price,paid);
		Package packages[]= {package1,package2};
		int numPackages=2;
		
		Kiosk kiosk=new Kiosk(gps,openingHour,closingHour,maxCapacity,packages,cubicMetres);
		
		assertNotNull(kiosk);
		assertFalse(kiosk.isOutOfService());
		assertEquals(maxCapacity,kiosk.getMaxCapacityBox());
		assertEquals(cubicMetres,kiosk.getMaxCapacityCubicMetres(),0.1);
		assertEquals(money,kiosk.getMoney(),0.2);
		assertEquals(numPackages,kiosk.getPackages().size());
		assertTrue(kiosk.getPackages().contains(package1));
		assertTrue(kiosk.getPackages().contains(package2));
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorKioskWithPackagesExceedMaxCapacityBox() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(9, 15);
		LocalTime closingHour=LocalTime.of(20, 40);
		int maxCapacity=1;
		double cubicMetres=100;
		String id1="1234721145";
		String id2="1234721189";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=0.1;
		double length=0.1;
		double height=0.1;
		double price=50;
		boolean paid=true;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		Package package2=new Package(id2,expirationDate1,width,length,height,price,paid);
		Package packages[]= {package1,package2};
		
		@SuppressWarnings("unused")
		Kiosk kiosk=new Kiosk(gps,openingHour,closingHour,maxCapacity,packages,cubicMetres);	
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorKioskWithPackagesExceedMaxCapacityCubicMetres() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(9, 15);
		LocalTime closingHour=LocalTime.of(20, 40);
		int maxCapacity=20;
		double cubicMetres=100;
		String id1="1234721145";
		String id2="1234721189";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=10;
		double length=10;
		double height=10;
		double price=50;
		boolean paid=true;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		Package package2=new Package(id2,expirationDate1,width,length,height,price,paid);
		Package packages[]= {package1,package2};
		
		@SuppressWarnings("unused")
		Kiosk kiosk=new Kiosk(gps,openingHour,closingHour,maxCapacity,packages,cubicMetres);
	}
	
	@Test
	public void testConstructorCopyKiosk() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(9, 15);
		LocalTime closingHour=LocalTime.of(20, 40);
		int maxCapacity=20;
		double cubicMetres=100;
		
		Kiosk kiosk=new Kiosk(gps,openingHour,closingHour,maxCapacity,cubicMetres);
		Kiosk kioskCopy=new Kiosk(kiosk);
		
		assertNotNull(kioskCopy);
		assertEquals(kiosk,kioskCopy);
		assertNotSame(kiosk,kioskCopy);
		assertEquals(kiosk.getMaxCapacityBox(),kioskCopy.getMaxCapacityBox());
		assertEquals(kiosk.getMaxCapacityCubicMetres(),kiosk.getMaxCapacityCubicMetres(),0.1);
	}
	
	@Test
	public void testConstructorCopyKioskWithPackages() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(9, 15);
		LocalTime closingHour=LocalTime.of(20, 40);
		int maxCapacity=20;
		double cubicMetres=100;
		String id1="1234721145";
		String id2="1234721189";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=0.1;
		double length=0.1;
		double height=0.1;
		double price=50;
		boolean paid=true;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		Package package2=new Package(id2,expirationDate1,width,length,height,price,paid);
		Package packages[]= {package1,package2};
		int numPackages=2;
		
		Kiosk kiosk=new Kiosk(gps,openingHour,closingHour,maxCapacity,packages,cubicMetres);
		Kiosk kioskCopy=new Kiosk(kiosk);
		
		assertNotNull(kioskCopy);
		assertEquals(kiosk,kioskCopy);
		assertNotSame(kiosk,kioskCopy);
		assertEquals(kiosk.getMaxCapacityBox(),kioskCopy.getMaxCapacityBox());
		assertEquals(kiosk.getMaxCapacityCubicMetres(),kioskCopy.getMaxCapacityCubicMetres(),0.1);
		assertTrue(kiosk.getPackages().contains(package1));
		assertTrue(kiosk.getPackages().contains(package2));
		assertEquals(numPackages,kiosk.getNumberOfPackagesStored());
	}
	
	@Test
	public void testAddPackage() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(9, 15);
		LocalTime closingHour=LocalTime.of(20, 40);
		int maxCapacity=20;
		double cubicMetres=100;
		String id1="1234721145";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=0.1;
		double length=0.1;
		double height=0.1;
		double price=50;
		boolean paid=true;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		int numPackages=1;
		
		Kiosk kiosk=new Kiosk(gps,openingHour,closingHour,maxCapacity,cubicMetres);
		kiosk.addPackage(package1);
		
		assertTrue(kiosk.getPackages().contains(package1));
		assertEquals(numPackages,kiosk.getNumberOfPackagesStored());
	}
	
	@Test
	public void testTakeOutPackageToReturn() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(9, 15);
		LocalTime closingHour=LocalTime.of(20, 40);
		int maxCapacity=20;
		double cubicMetres=100;
		String id1="1234721145";
		String id2="1234721189";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=0.1;
		double length=0.1;
		double height=0.1;
		double price=50;
		boolean paid=true;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		Package package2=new Package(id2,expirationDate1,width,length,height,price,paid);
		Package packages[]= {package1,package2};
		
		Kiosk kiosk=new Kiosk(gps,openingHour,closingHour,maxCapacity,packages,cubicMetres);
		kiosk.takeOutPackageToReturn(package2);
		
		assertFalse(kiosk.getPackages().contains(package2));
		assertTrue(package2.getReturnStatus());
		assertFalse(package2.isStored());
	}
	
	@Test
	public void testTakeOutPackageAsCustomer() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(9, 15);
		LocalTime closingHour=LocalTime.of(20, 40);
		int maxCapacity=20;
		double cubicMetres=100;
		String id1="1234721145";
		String id2="1234721189";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=0.1;
		double length=0.1;
		double height=0.1;
		double price=50;
		boolean paid=true;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		Package package2=new Package(id2,expirationDate1,width,length,height,price,paid);
		Package packages[]= {package1,package2};
		LocalDate pickUpDate=LocalDate.of(2023, 12, 22);
		LocalTime pickUpHour=LocalTime.of(12, 15);
		
		Kiosk kiosk=new Kiosk(gps,openingHour,closingHour,maxCapacity,packages,cubicMetres);
		Package packageToPickUp=kiosk.takeOutPackageAsCustomer(id1, pickUpDate, pickUpHour);
		
		assertNotNull(packageToPickUp);
		assertEquals(package1,packageToPickUp);
		assertNotSame(package1,packageToPickUp);
		assertFalse(kiosk.getPackages().contains(package1));
		assertTrue(packageToPickUp.getPickUpStatus());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testTakeOutPackageAsCustomerNotPaid() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(9, 15);
		LocalTime closingHour=LocalTime.of(20, 40);
		int maxCapacity=20;
		double cubicMetres=100;
		String id1="1234721145";
		String id2="1234721189";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=0.1;
		double length=0.1;
		double height=0.1;
		double price=50;
		boolean paid=false;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		Package package2=new Package(id2,expirationDate1,width,length,height,price,paid);
		Package packages[]= {package1,package2};
		LocalDate pickUpDate=LocalDate.of(2023, 12, 22);
		LocalTime pickUpHour=LocalTime.of(12, 15);
		
		Kiosk kiosk=new Kiosk(gps,openingHour,closingHour,maxCapacity,packages,cubicMetres);
		
		@SuppressWarnings("unused")
		Package packageToPickUp=kiosk.takeOutPackageAsCustomer(id1, pickUpDate, pickUpHour);
	}
	
	
	@Test
	public void testTakeOutPackageToPayAsCustomer() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(9, 15);
		LocalTime closingHour=LocalTime.of(20, 40);
		int maxCapacity=20;
		double cubicMetres=100;
		String id1="1234721145";
		String id2="1234721189";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=0.1;
		double length=0.1;
		double height=0.1;
		double price=50;
		boolean paid=false;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		Package package2=new Package(id2,expirationDate1,width,length,height,price,paid);
		Package packages[]= {package1,package2};
		
		Kiosk kiosk=new Kiosk(gps,openingHour,closingHour,maxCapacity,packages,cubicMetres);
		double money=50;
		LocalDate pickUpDate=LocalDate.of(2023, 12, 22);
		LocalTime pickUpHour=LocalTime.of(12, 15);
		Package packageToPickUp=kiosk.takeOutPackageToPayAsCustomer(money, id2, pickUpDate, pickUpHour);
		assertNotNull(packageToPickUp);
		assertEquals(package2,packageToPickUp);
		assertNotSame(package2,packageToPickUp);
		assertFalse(kiosk.getPackages().contains(package2));
		assertTrue(packageToPickUp.getPickUpStatus());
		assertTrue(packageToPickUp.getPickUpStatus());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testTakeOutPackageToPayAsCustomerPackagePaid() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(9, 15);
		LocalTime closingHour=LocalTime.of(20, 40);
		int maxCapacity=20;
		double cubicMetres=100;
		
		String id1="1234721145";
		String id2="1234721189";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=0.1;
		double length=0.1;
		double height=0.1;
		double price=50;
		boolean paid=true;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		Package package2=new Package(id2,expirationDate1,width,length,height,price,paid);
		Package packages[]= {package1,package2};
		
		Kiosk kiosk=new Kiosk(gps,openingHour,closingHour,maxCapacity,packages,cubicMetres);
		double money=50;
		LocalDate pickUpDate=LocalDate.of(2023, 12, 22);
		LocalTime pickUpHour=LocalTime.of(12, 15);
		@SuppressWarnings("unused")
		Package packageToPickUp=kiosk.takeOutPackageToPayAsCustomer(money, id2, pickUpDate, pickUpHour);
		
	}	
	
	@Test(expected=IllegalArgumentException.class)
	public void testTakeOutPackageToPayAsCustomerNotSamePrice() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(9, 15);
		LocalTime closingHour=LocalTime.of(20, 40);
		int maxCapacity=20;
		double cubicMetres=100;
		
		String id1="1234721145";
		String id2="1234721189";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=0.1;
		double length=0.1;
		double height=0.1;
		double price=50;
		boolean paid=false;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		Package package2=new Package(id2,expirationDate1,width,length,height,price,paid);
		Package packages[]= {package1,package2};
		
		Kiosk kiosk=new Kiosk(gps,openingHour,closingHour,maxCapacity,packages,cubicMetres);
		double money=20;
		LocalDate pickUpDate=LocalDate.of(2023, 12, 22);
		LocalTime pickUpHour=LocalTime.of(12, 15);
		@SuppressWarnings("unused")
		Package packageToPickUp=kiosk.takeOutPackageToPayAsCustomer(money, id2, pickUpDate, pickUpHour);
	}
	
	@Test
	public void testGetPackages() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(9, 15);
		LocalTime closingHour=LocalTime.of(20, 40);
		int maxCapacity=20;
		double cubicMetres=100;
		
		String id1="1234721145";
		String id2="1234721189";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=0.1;
		double length=0.1;
		double height=0.1;
		double price=50;
		boolean paid=false;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		Package package2=new Package(id2,expirationDate1,width,length,height,price,paid);
		Package packages[]= {package1,package2};
		
		Kiosk kiosk=new Kiosk(gps,openingHour,closingHour,maxCapacity,packages,cubicMetres);
		int numPackages=2;
		assertNotNull(kiosk.getPackages());
		assertEquals(numPackages,kiosk.getPackages().size());
		assertTrue(kiosk.getPackages().contains(package1));
		assertTrue(kiosk.getPackages().contains(package2));
		
	}
	
	@Test
	public void testExpandCapacity() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(9, 15);
		LocalTime closingHour=LocalTime.of(20, 40);
		int maxCapacity=20;
		double cubicMetres=100;
		
		String id1="1234721145";
		String id2="1234721189";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=0.1;
		double length=0.1;
		double height=0.1;
		double price=50;
		boolean paid=false;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		Package package2=new Package(id2,expirationDate1,width,length,height,price,paid);
		Package packages[]= {package1,package2};
		int numPackages=2;
		Kiosk kiosk=new Kiosk(gps,openingHour,closingHour,maxCapacity,packages,cubicMetres);
		

		int gap=10;
		kiosk.expandCapacity(gap);
		int total=maxCapacity+gap;
		assertTrue(kiosk.getPackages().contains(package1));
		assertTrue(kiosk.getPackages().contains(package2));
		assertEquals(numPackages,kiosk.getPackages().size());
		assertEquals(total,kiosk.getMaxCapacityBox());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testExpandCapacityNegativeGap() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(9, 15);
		LocalTime closingHour=LocalTime.of(20, 40);
		int maxCapacity=20;
		double cubicMetres=100;
		
		String id1="1234721145";
		String id2="1234721189";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=0.1;
		double length=0.1;
		double height=0.1;
		double price=50;
		boolean paid=false;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		Package package2=new Package(id2,expirationDate1,width,length,height,price,paid);
		Package packages[]= {package1,package2};
		
		Kiosk kiosk=new Kiosk(gps,openingHour,closingHour,maxCapacity,packages,cubicMetres);

		int gap=-5;
		kiosk.expandCapacity(gap);
	}
	
	@Test
	public void testReduceCapacity() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(9, 15);
		LocalTime closingHour=LocalTime.of(20, 40);
		int maxCapacity=20;
		double cubicMetres=100;
		
		String id1="1234721145";
		String id2="1234721189";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=0.1;
		double length=0.1;
		double height=0.1;
		double price=50;
		boolean paid=false;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		Package package2=new Package(id2,expirationDate1,width,length,height,price,paid);
		Package packages[]= {package1,package2};
		int numPackages=2;
		Kiosk kiosk=new Kiosk(gps,openingHour,closingHour,maxCapacity,packages,cubicMetres);

		int gap=5;
		kiosk.reduceCapacity(gap);
		int total=maxCapacity-gap;
		assertTrue(kiosk.getPackages().contains(package1));
		assertTrue(kiosk.getPackages().contains(package2));
		assertEquals(numPackages,kiosk.getPackages().size());
		assertEquals(total,kiosk.getMaxCapacityBox());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testReduceCapacityNegativeGap() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(9, 15);
		LocalTime closingHour=LocalTime.of(20, 40);
		int maxCapacity=20;
		double cubicMetres=100;
		
		String id1="1234721145";
		String id2="1234721189";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=0.1;
		double length=0.1;
		double height=0.1;
		double price=50;
		boolean paid=false;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		Package package2=new Package(id2,expirationDate1,width,length,height,price,paid);
		Package packages[]= {package1,package2};
		
		Kiosk kiosk=new Kiosk(gps,openingHour,closingHour,maxCapacity,packages,cubicMetres);
		int gap=-2;
		kiosk.reduceCapacity(gap);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testReduceCapacityMoreThanPackagesStored() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(9, 15);
		LocalTime closingHour=LocalTime.of(20, 40);
		int maxCapacity=20;
		double cubicMetres=100;
		
		String id1="1234721145";
		String id2="1234721189";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=0.1;
		double length=0.1;
		double height=0.1;
		double price=50;
		boolean paid=false;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		Package package2=new Package(id2,expirationDate1,width,length,height,price,paid);
		Package packages[]= {package1,package2};
		
		Kiosk kiosk=new Kiosk(gps,openingHour,closingHour,maxCapacity,packages,cubicMetres);
		int gap=19;
		kiosk.reduceCapacity(gap);
	}
	
	@Test
	public void testAddAndGetMoney() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(9, 15);
		LocalTime closingHour=LocalTime.of(20, 40);
		int maxCapacity=20;
		double cubicMetres=100;
		
		String id1="1234721145";
		String id2="1234721189";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=0.1;
		double length=0.1;
		double height=0.1;
		double price=50;
		boolean paid=false;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		Package package2=new Package(id2,expirationDate1,width,length,height,price,paid);
		Package packages[]= {package1,package2};
		
		Kiosk kiosk=new Kiosk(gps,openingHour,closingHour,maxCapacity,packages,cubicMetres);
		double money=50;
		kiosk.addMoney(money);
		assertEquals(money,kiosk.getMoney(),0.1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddAndGetMoneyNegativeMoney() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(9, 15);
		LocalTime closingHour=LocalTime.of(20, 40);
		int maxCapacity=20;
		double cubicMetres=100;
		
		String id1="1234721145";
		String id2="1234721189";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=0.1;
		double length=0.1;
		double height=0.1;
		double price=50;
		boolean paid=false;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		Package package2=new Package(id2,expirationDate1,width,length,height,price,paid);
		Package packages[]= {package1,package2};
		
		Kiosk kiosk=new Kiosk(gps,openingHour,closingHour,maxCapacity,packages,cubicMetres);
		double money=-50;
		kiosk.addMoney(money);
	}
	
	@Test
	public void testChargePackage() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(9, 15);
		LocalTime closingHour=LocalTime.of(20, 40);
		int maxCapacity=20;
		double cubicMetres=100;
		
		String id1="1234721145";
		String id2="1234721189";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=0.1;
		double length=0.1;
		double height=0.1;
		double price=50;
		boolean paid=false;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		Package package2=new Package(id2,expirationDate1,width,length,height,price,paid);
		Package packages[]= {package1,package2};
		
		Kiosk kiosk=new Kiosk(gps,openingHour,closingHour,maxCapacity,packages,cubicMetres);
		double money=50;
		kiosk.chargePackage(money, package1);
		assertTrue(package1.isPaid());
		assertEquals(money,kiosk.getMoney(),0.1);
	}
	
	@Test(expected=NullPointerException.class)
	public void testChargePackageNull() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(9, 15);
		LocalTime closingHour=LocalTime.of(20, 40);
		int maxCapacity=20;
		double cubicMetres=100;
		
		String id1="1234721145";
		String id2="1234721189";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=0.1;
		double length=0.1;
		double height=0.1;
		double price=50;
		boolean paid=false;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		Package package2=new Package(id2,expirationDate1,width,length,height,price,paid);
		Package packages[]= {package1,package2};
		
		Kiosk kiosk=new Kiosk(gps,openingHour,closingHour,maxCapacity,packages,cubicMetres);
		double money=50;
		Package packageToPay=null;
		kiosk.chargePackage(money, packageToPay);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testChargePackageAlreadyPaid() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(9, 15);
		LocalTime closingHour=LocalTime.of(20, 40);
		int maxCapacity=20;
		double cubicMetres=100;
		
		String id1="1234721145";
		String id2="1234721189";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=0.1;
		double length=0.1;
		double height=0.1;
		double price=50;
		boolean paid=true;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		Package package2=new Package(id2,expirationDate1,width,length,height,price,paid);
		Package packages[]= {package1,package2};
		
		Kiosk kiosk=new Kiosk(gps,openingHour,closingHour,maxCapacity,packages,cubicMetres);
		double money=50;
		kiosk.chargePackage(money, package1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testChargePackageNotCorrectAmountOfMoney() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(9, 15);
		LocalTime closingHour=LocalTime.of(20, 40);
		int maxCapacity=20;
		double cubicMetres=100;
		
		String id1="1234721145";
		String id2="1234721189";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=0.1;
		double length=0.1;
		double height=0.1;
		double price=50;
		boolean paid=false;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		Package package2=new Package(id2,expirationDate1,width,length,height,price,paid);
		Package packages[]= {package1,package2};
		
		Kiosk kiosk=new Kiosk(gps,openingHour,closingHour,maxCapacity,packages,cubicMetres);
		double money=30;
		kiosk.chargePackage(money, package1);
	}
	
	@Test
	public void testIsFullTrue() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(9, 15);
		LocalTime closingHour=LocalTime.of(20, 40);
		int maxCapacity=2;
		double cubicMetres=100;
		
		String id1="1234721145";
		String id2="1234721189";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=0.1;
		double length=0.1;
		double height=0.1;
		double price=50;
		boolean paid=false;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		Package package2=new Package(id2,expirationDate1,width,length,height,price,paid);
		Package packages[]= {package1,package2};
		
		Kiosk kiosk=new Kiosk(gps,openingHour,closingHour,maxCapacity,packages,cubicMetres);
		
		assertTrue(kiosk.isFull());
	}
	
	@Test
	public void testIsFullFalse() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(9, 15);
		LocalTime closingHour=LocalTime.of(20, 40);
		int maxCapacity=20;
		double cubicMetres=100;
		
		String id1="1234721145";
		String id2="1234721189";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=0.1;
		double length=0.1;
		double height=0.1;
		double price=50;
		boolean paid=false;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		Package package2=new Package(id2,expirationDate1,width,length,height,price,paid);
		Package packages[]= {package1,package2};
		
		Kiosk kiosk=new Kiosk(gps,openingHour,closingHour,maxCapacity,packages,cubicMetres);
		
		assertFalse(kiosk.isFull());
	}
	
	@Test
	public void testIsFullByCubicMetres() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(9, 15);
		LocalTime closingHour=LocalTime.of(20, 40);
		int maxCapacity=20;
		double cubicMetres=2;
		
		String id1="1234721145";
		String id2="1234721189";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=1;
		double length=1;
		double height=1;
		double price=50;
		boolean paid=false;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		Package package2=new Package(id2,expirationDate1,width,length,height,price,paid);
		Package packages[]= {package1,package2};
		
		Kiosk kiosk=new Kiosk(gps,openingHour,closingHour,maxCapacity,packages,cubicMetres);
		assertTrue(kiosk.isFull());
	}
	
	@Test
	public void testCanStorePackage() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(9, 15);
		LocalTime closingHour=LocalTime.of(20, 40);
		int maxCapacity=20;
		double cubicMetres=100;
		
		String id1="1234721145";
		String id2="1234721189";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=0.1;
		double length=0.1;
		double height=0.1;
		double price=50;
		boolean paid=false;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		Package package2=new Package(id2,expirationDate1,width,length,height,price,paid);
		Package packages[]= {package1};
		
		Kiosk kiosk=new Kiosk(gps,openingHour,closingHour,maxCapacity,packages,cubicMetres);
		assertTrue(kiosk.canStorePackage(package2));
	}
	
	@Test
	public void testCanStorePackageCertified() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(9, 15);
		LocalTime closingHour=LocalTime.of(20, 40);
		int maxCapacity=20;
		double cubicMetres=100;
		
		String id1="1234721145";
		String id2="1234721189";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=0.1;
		double length=0.1;
		double height=0.1;
		double price=50;
		boolean paid=false;
		String dni="12345679A";
		List<String> authorizedDni=new ArrayList<>();
		authorizedDni.add(dni);
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		Package package2=new Package(id2,expirationDate1,width,length,height,price,paid,authorizedDni);
		Package packages[]= {package1};
		
		Kiosk kiosk=new Kiosk(gps,openingHour,closingHour,maxCapacity,packages,cubicMetres);
		assertFalse(kiosk.canStorePackage(package2));
	}
	
	@Test
	public void testCanStorePackageExceedCubicMetres() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(9, 15);
		LocalTime closingHour=LocalTime.of(20, 40);
		int maxCapacity=20;
		double cubicMetres=1.5;
		
		String id1="1234721145";
		String id2="1234721189";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=1;
		double length=1;
		double height=1;
		double price=50;
		boolean paid=false;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		Package package2=new Package(id2,expirationDate1,width,length,height,price,paid);
		Package packages[]= {package1};
		
		Kiosk kiosk=new Kiosk(gps,openingHour,closingHour,maxCapacity,packages,cubicMetres);
		
		assertFalse(kiosk.canStorePackage(package2));
	}
	
	@Test
	public void testCanStorePackageExceedPackages() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(9, 15);
		LocalTime closingHour=LocalTime.of(20, 40);
		int maxCapacity=1;
		double cubicMetres=100;
		
		String id1="1234721145";
		String id2="1234721189";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=0.1;
		double length=0.1;
		double height=0.1;
		double price=50;
		boolean paid=false;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		Package package2=new Package(id2,expirationDate1,width,length,height,price,paid);
		Package packages[]= {package1};
		
		Kiosk kiosk=new Kiosk(gps,openingHour,closingHour,maxCapacity,packages,cubicMetres);
		
		assertFalse(kiosk.canStorePackage(package2));
	}
	
	@Test
	public void testGetUsedCubicMetres() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(9, 15);
		LocalTime closingHour=LocalTime.of(20, 40);
		int maxCapacity=20;
		double cubicMetres=100;
		
		String id1="1234721145";
		String id2="1234721189";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=0.1;
		double length=0.1;
		double height=0.1;
		double price=50;
		boolean paid=false;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		Package package2=new Package(id2,expirationDate1,width,length,height,price,paid);
		Package packages[]= {package1,package2};
		double usedCubicMetres=package1.getCubicMetres()+package2.getCubicMetres();
		Kiosk kiosk=new Kiosk(gps,openingHour,closingHour,maxCapacity,packages,cubicMetres);
		assertEquals(usedCubicMetres,kiosk.getUsedCubicMetres(),0.1);
	}
	
	@Test
	public void testPayToAmazingCo() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(9, 15);
		LocalTime closingHour=LocalTime.of(20, 40);
		int maxCapacity=20;
		double cubicMetres=100;
		
		String id1="1234721145";
		String id2="1234721189";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=0.1;
		double length=0.1;
		double height=0.1;
		double price=50;
		boolean paid=false;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		Package package2=new Package(id2,expirationDate1,width,length,height,price,paid);
		Package packages[]= {package1,package2};
		
		double money=50;
		Kiosk kiosk=new Kiosk(gps,openingHour,closingHour,maxCapacity,packages,cubicMetres);
		kiosk.addMoney(money);
		kiosk.payToAmazingCo();
		double newMoney=0;
		assertEquals(newMoney,kiosk.getMoney(),0.1);
	}
	
	@Test
	public void testHasCOD() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(9, 15);
		LocalTime closingHour=LocalTime.of(20, 40);
		int maxCapacity=20;
		double cubicMetres=100;
		
		String id1="1234721145";
		String id2="1234721189";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=0.1;
		double length=0.1;
		double height=0.1;
		double price=50;
		boolean paid=false;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		Package package2=new Package(id2,expirationDate1,width,length,height,price,paid);
		Package packages[]= {package1,package2};
		
		Kiosk kiosk=new Kiosk(gps,openingHour,closingHour,maxCapacity,packages,cubicMetres);
		assertTrue(kiosk.hasCOD());
	}
	
	@Test
	public void testToString() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(9, 15);
		LocalTime closingHour=LocalTime.of(20, 40);
		int maxCapacity=20;
		double cubicMetres=100;
		
		String id1="1234721145";
		String id2="1234721189";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=0.1;
		double length=0.1;
		double height=0.1;
		double price=50;
		boolean paid=false;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		Package package2=new Package(id2,expirationDate1,width,length,height,price,paid);
		Package packages[]= {package1,package2};
		String empty="";
		Kiosk kiosk=new Kiosk(gps,openingHour,closingHour,maxCapacity,packages,cubicMetres);
		assertNotNull(kiosk.toString());
		assertNotEquals(empty,kiosk.toString());
	}
	
	@Test
	public void testClone() {
		GPSCoordinate gps=new GPSCoordinate(60,120);
		LocalTime openingHour=LocalTime.of(9, 15);
		LocalTime closingHour=LocalTime.of(20, 40);
		int maxCapacity=20;
		double cubicMetres=100;
		
		String id1="1234721145";
		String id2="1234721189";
		LocalDate expirationDate1=LocalDate.of(2023, 12, 28);
		double width=0.1;
		double length=0.1;
		double height=0.1;
		double price=50;
		boolean paid=false;
		Package package1=new Package(id1,expirationDate1,width,length,height,price,paid);
		Package package2=new Package(id2,expirationDate1,width,length,height,price,paid);
		Package packages[]= {package1,package2};
		
		Kiosk kiosk=new Kiosk(gps,openingHour,closingHour,maxCapacity,packages,cubicMetres);
		Kiosk kioskClone=kiosk.clone();
		assertNotNull(kioskClone);
		assertEquals(kiosk,kioskClone);
		assertNotSame(kiosk,kioskClone);
		assertEquals(kiosk.getMaxCapacityBox(),kioskClone.getMaxCapacityBox());
		assertEquals(kiosk.getMaxCapacityCubicMetres(),kioskClone.getMaxCapacityCubicMetres(),0.1);
	}
}
 