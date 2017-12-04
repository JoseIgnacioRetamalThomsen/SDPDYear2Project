// Jose Retamal SDPD 2 lab exam 10/11/2017 -Test Class-
package fabsound;

public class TestClass {

	public static void main(String[] args) {

		// 5.
		// Create Object seanSmith of type MusicTeacher
		System.out.println("***************************************************************");
		System.out.println("Part num 5. :");
		System.out.println("***************************************************************\n");

		MusicTeacher seanSmith = new MusicTeacher(003, "Sean Smith", 60F, 25);
		seanSmith.display();

		// 6.
		// Create amyLupton type EventManager
		System.out.println("\n***************************************************************");
		System.out.println("Part num 6. :");
		System.out.println("***************************************************************\n");
		// hours worker 37.5 +5 = 42.5

		EventManager amyLupton = new EventManager("Amy Lupton", 50F, 42.5F);
		amyLupton.display();

		// 7.
		// Interface with abstract defaul and static method
		System.out.println("\n***************************************************************");
		System.out.println("Part num 7. :");
		System.out.println("***************************************************************\n");

		EventManager carlWilson = new EventManager(001, "Carl Wilson", 45F, 20, 42.5F);// id,name,hourlyrate,holidays,
																						// hours
																						// worker
		
		carlWilson.displayExamFee();
		
		//try to set MEDICAL_Cover
		BusinessMusicCertification.setMedicalCover(0);
		EventManager.setMedicalCover(0);

		// 8.
		// Create BookingAgent paulaKelly
		System.out.println("\n***************************************************************");
		System.out.println("Part num 8. :");
		System.out.println("***************************************************************\n");

		BookingAgent paulaKelly = new BookingAgent("Paula Kelly", 65.00F, 5000f, 49.5f);
		paulaKelly.setBonus(5000);
		paulaKelly.display();

		// 9.
		// Polymorphism
		System.out.println("\n***************************************************************");
		System.out.println("Part num 9.Polymorphism :");
		System.out.println("***************************************************************\n");

		Musician bobHorgan = new BookingAgent(007, "Bob Horgan", 60F, 2000);

		// call display method in BookingAgent
		bobHorgan.display();
		System.out.println(((Musician) bobHorgan).calculateSallary() + "\n");

		// 10.
		// Use static Methods
		System.out.println("\n***************************************************************");
		System.out.println("Part num 10.Statics:");
		System.out.println("***************************************************************\n");

		System.out.println("interface MEDICAL_COVER is always:" + BusinessMusicCertification.MEDICAL_COVER);
		Musician.setMedicalCover(2000);
		EventManager joe = new EventManager(8, "joe", 25, 5);
		BookingAgent ann = new BookingAgent(9, "ann", 25, 50);

		joe.display();
		ann.display();

		// 11.
		// Equals method
		System.out.println("\n***************************************************************");
		System.out.println("Part num 11.Equals :");
		System.out.println("***************************************************************\n");
		
		EventManager myHusband = new EventManager(10, "couple", 43,12);
		EventManager myWife = new EventManager(10, "couple", 43,12);
		
		//test for equals objects
		System.out.println(" myHusband.equals(myWife): " +myHusband.equals(myWife));
		//test for different objects
		System.out.println(" myHusband.equals(joe): " +myHusband.equals(joe));
	}
}
