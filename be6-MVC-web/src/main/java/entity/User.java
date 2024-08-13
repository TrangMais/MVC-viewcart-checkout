package entity;

public class User {
	
	private String username;
	private String password;
	private String gender;
	private String hobbies;
	private String languages;
	private String city;
	
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", gender=" + gender + ", hobbies=" + hobbies
				+ ", languages=" + languages + ", city=" + city + "]";
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String username, String password, String gender, String hobbies, String languages, String city) {
		super();
		this.username = username;
		this.password = password;
		this.gender = gender;
		this.hobbies = hobbies;
		this.languages = languages;
		this.city = city;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHobbies() {
		return hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

	public String getLanguages() {
		return languages;
	}

	public void setLanguages(String languages) {
		this.languages = languages;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	
		

}
