package dad.javafx.dogs.client.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.net.URL;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dad.javafx.dogs.client.DogsService;
import dad.javafx.dogs.client.DogsServiceException;

public class DogsServiceTestCase {
	
	private DogsService service;

	@Before
	public void setUp() throws Exception {
		service = new DogsService();
	}

	@Test
	public void listBreedsTest() throws DogsServiceException {
		List<String> breeds = service.listBreeds();
		assertTrue(breeds.contains("hound"));
	}
	
	@Test
	public void randomImageByBreedTest() throws DogsServiceException {
		URL image = service.randomImageByBreed("akita");
		assertNotNull(image);
	}
	
	@Test
	public void randomImageTest() throws DogsServiceException {
		URL image = service.randomImage();
		assertNotNull(image);
	}
	
	@Test
	public void imagesByBreedTest() throws DogsServiceException {
		List<URL> images = service.imagesByBreed("akita");
		assertTrue(!images.isEmpty());
	}
	
	@Test
	public void subBreedTest() throws DogsServiceException {
		List<String> subbreeds = service.subBreeds("hound");
		assertTrue(subbreeds.contains("afghan"));
	}
	
	@Test
	public void randomImageByBreedFailTest() {
		try {
			service.randomImageByBreed("no-dog");
			assertTrue(false);
		} catch (DogsServiceException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void imagesByBreedFailTest() {
		try {
			service.imagesByBreed("no-dog");
			assertTrue(false);
		} catch (DogsServiceException e) {
			assertTrue(true);
		}
	}

}
