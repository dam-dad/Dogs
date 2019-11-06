package dad.javafx.dogs.client.messages;

import java.util.List;

public class ImagesMessage extends Message {

	private List<String> message;

	public List<String> getMessage() {
		return message;
	}

	public void setMessage(List<String> message) {
		this.message = message;
	}

}
