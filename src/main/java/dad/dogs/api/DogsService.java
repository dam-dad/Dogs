package dad.dogs.api;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DogsService {
	
	private static final String BASE_URL = "https://dog.ceo/api/";
	
	private DogsInterface service;
	private Gson gson = new Gson();
	
	public DogsService() {
		
		ConnectionPool pool = new ConnectionPool(1, 5, TimeUnit.SECONDS);
		
		OkHttpClient client = new OkHttpClient.Builder()
                .connectionPool(pool)
                .build();
		
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(BASE_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.client(client)
				.build();
		
		service = retrofit.create(DogsInterface.class);	
	}
	
	public URL randomBreedImage() throws Exception {
		Response<BreedImage> response = service
				.randomBreedImage()
				.execute();
		assertResponse(response);
		BreedImage breedImage = response.body(); 
		return new URL(breedImage.getMessage());
	}
	
	public List<String> breedsList() throws Exception {
		Response<BreedsList> response = service
				.breedsList()
				.execute();
		assertResponse(response);
		BreedsList breedsList = response.body();
		return new ArrayList<>(breedsList.getMessage().keySet());		
	}
	
	public URL randomBreedImage(String breed) throws Exception {
		Response<BreedImage> response = service
				.randomBreedImage(breed)
				.execute();
		assertResponse(response);
		BreedImage breedImage = response.body();
		return new URL(breedImage.getMessage());
	}
	
	private void assertResponse(Response<?> response) throws Exception {
		if (!response.isSuccessful()) {
			ErrorMessage error = gson.fromJson(response.errorBody().string(), ErrorMessage.class);
			throw new Exception(error.getMessage());
		}
	}

}