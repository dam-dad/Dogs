package dad.dogs.api;

import java.util.List;
import java.util.Map;

class BreedsList {

	private Map<String, List<String>> message;
	private String status;

	public Map<String, List<String>> getMessage() {
		return message;
	}

	public void setMessage(Map<String, List<String>> message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
