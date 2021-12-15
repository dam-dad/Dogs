package dad.javafx.dogs.client.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.net.URL;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dad.dogs.api.DogsService;


public class DogsServiceTestCase {
	
	private DogsService service;

	@Before
	public void setUp() throws Exception {
		service = new DogsService();
	}

	@Test
	public void listBreedsTest() throws Exception {
		List<String> breeds = service.breedsList();
		assertTrue(breeds.contains("hound"));
	}
	
	@Test
	public void randomBreedImageTest() throws Exception {
		URL image = service.randomBreedImage("akita");
		assertNotNull(image);
	}
	
	@Test
	public void randomImageTest() throws Exception {
		URL image = service.randomBreedImage();
		assertNotNull(image);
	}
	
	@Test
	public void randomBreedImageFailTest() {
		try {
			service.randomBreedImage("no-dog");
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}
	}

}
