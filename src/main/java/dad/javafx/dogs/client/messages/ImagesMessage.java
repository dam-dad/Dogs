package dad.javafx.dogs.client.messages;

import java.net.URL;
import java.util.List;

public class ImagesMessage extends Message {

	private List<URL> message;

	public List<URL> getMessage() {
		return message;
	}

	public void setMessage(List<URL> message) {
		this.message = message;
	}

}
