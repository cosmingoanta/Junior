import java.util.ArrayList;
import java.util.List;

public class RentedCars {
	
	private List<String> carList;

	public RentedCars() {
		carList = new ArrayList<String>();
	}

	public void addCar(String car) {
		carList.add(car);
	}

	public void removeCar(String car) {
		carList.remove(car);
	}
	
	public int size() {
		return carList.size();
	}
	
	public void showCars() {
		System.out.println("The cars owned are: ");
		for(String s : carList) {
			System.out.print(s + " ");
		}
	}

	public List<String> getCarList() {
		return carList;
	}

	
	
	
}