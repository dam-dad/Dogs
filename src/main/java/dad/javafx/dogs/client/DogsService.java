package dad.javafx.dogs.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import dad.javafx.dogs.client.messages.BreedsMessage;
import dad.javafx.dogs.client.messages.ImageMessage;

public class DogsService {
	
	public DogsService() {
		Unirest.setObjectMapper(new UnirestObjectMapper());
	}
	
	public List<String> listBreeds() throws DogsServiceException {
		try {
		
			BreedsMessage breeds = 
				Unirest
					.get("https://dog.ceo/api/breeds/list")
					.asObject(BreedsMessage.class)
					.getBody();
			
			if (!breeds.getStatus().equals("success")) {
				throw new DogsServiceException("Error retrieving breeds list");
			}
			
			return breeds.getMessage();

		} catch (UnirestException e) {
			throw new DogsServiceException(e);
		}
	}
	
	public URL randomImageByBreed(String breed) throws DogsServiceException {
		try {
			
			ImageMessage image = 
				Unirest
					.get("https://dog.ceo/api/breed/" + breed + "/images/random")
					.asObject(ImageMessage.class)
					.getBody();
			
			if (!image.isSuccess()) {
				throw new DogsServiceException("Error retrieving " + breed + " random image");
			}
			
			return new URL(image.getMessage());

		} catch (UnirestException | MalformedURLException e) {
			
			throw new DogsServiceException(e);
			
		}
	}
	
	public URL randomImage() throws DogsServiceException {
		// TODO 
		return null;
	}
	
	public List<URL> imagesByBreed(String breed) throws DogsServiceException {
		// TODO 
		return null;
	}
	
	public List<String> subBreeds() throws DogsServiceException {
		// TODO 
		return null;		
	}
	
	public static void main(String[] args) throws DogsServiceException {
		DogsService service = new DogsService();
		System.out.println(service.listBreeds());
		System.out.println(service.randomImageByBreed("akita"));
	}

}
