package dad.javafx.dogs.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import dad.javafx.dogs.client.messages.BreedsMessage;
import dad.javafx.dogs.client.messages.ImageMessage;
import dad.javafx.dogs.client.messages.ImagesMessage;

public class DogsService {
	
	public DogsService() {
		Unirest.setObjectMapper(new JacksonObjectMapper());
	}
	
	public List<String> listBreeds() throws DogsServiceException {
		try {
		
			BreedsMessage breeds = 
				Unirest
					.get("https://dog.ceo/api/breeds/list")
					.asObject(BreedsMessage.class)
					.getBody();
			
			if (!breeds.isSuccess()) {
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
		try {
			
			ImageMessage image = 
				Unirest
					.get("https://dog.ceo/api/breeds/image/random")
					.asObject(ImageMessage.class)
					.getBody();
			
			if (!image.isSuccess()) {
				throw new DogsServiceException("Error retrieving random image");
			}
			
			return new URL(image.getMessage());

		} catch (UnirestException | MalformedURLException e) {
			
			throw new DogsServiceException(e);
			
		}
	}
	
	public List<URL> imagesByBreed(String breed) throws DogsServiceException {
		try {
			
			ImagesMessage breeds = 
				Unirest
					.get("https://dog.ceo/api/breed/" + breed + "/images")
					.asObject(ImagesMessage.class)
					.getBody();
			
			if (!breeds.isSuccess()) {
				throw new DogsServiceException("Error retrieving " + breed + " images list");
			}
			
			return breeds.getMessage().stream().map(url -> {
					try {
						return new URL(url);
					} catch (MalformedURLException e) {
						e.printStackTrace();
					}
					return null;
				}).collect(Collectors.toList());

		} catch (Exception e) {
			throw new DogsServiceException(e);
		}
	}
	
	public List<String> subBreeds(String breed) throws DogsServiceException {
		try {
			
			BreedsMessage breeds = 
				Unirest
					.get("https://dog.ceo/api/breed/" + breed + "/list")
					.asObject(BreedsMessage.class)
					.getBody();
			
			if (!breeds.getStatus().equals("success")) {
				throw new DogsServiceException("Error retrieving " + breed + " sub-breeds list");
			}
			
			return breeds.getMessage();

		} catch (UnirestException e) {
			throw new DogsServiceException(e);
		}		
	}

}
