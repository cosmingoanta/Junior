import java.io.Serializable;
import java.util.Objects;

public class Guest implements Serializable {

	private String firstName;
	private String lastName;
	private String phone;
	private String email;

	public Guest() {

	}

	public Guest(String firstName, String lastName, String phone, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return ("Guest: " + firstName + " " + lastName + ", phone nr. : " + phone + ", email address: " + email + ".");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Guest aux = (Guest) obj;
		if (Objects.equals(firstName, aux.firstName) && Objects.equals(lastName, aux.lastName)
				&& Objects.equals(email, aux.email) && Objects.equals(phone, aux.phone))
			return true;
		return false;
	}

	
}
