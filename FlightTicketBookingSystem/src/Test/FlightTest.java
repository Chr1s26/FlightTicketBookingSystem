package Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import Service.BookingService;
import Service.RouteService;

public class FlightTest {
	
	static BookingService bookingService = new BookingService();
	static RouteService routeService = new RouteService();
	
	public static void main(String[] args) throws IOException, SQLException {
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int choice = 0;
		
		do {
			System.out.println("Choose the option");
			System.out.println("1. Book a Ticket");
			System.out.println("2. Show All Routes");
			System.out.println("0. Quit");
			
			choice = Integer.parseInt(bufferedReader.readLine());
			
			switch (choice) {
			case 1: bookingService.book(); break;
			case 2: routeService.showallRoute(); break;
			default: break;
			}
			
		}while(choice != 0);
	}

}
