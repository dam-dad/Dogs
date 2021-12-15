package dad.dogs.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface DogsInterface {

	@GET("breeds/image/random")
	public Call<BreedImage> randomBreedImage();

	@GET("breeds/list/all")
	public Call<BreedsList> breedsList();
	
	@GET("breed/{breed}/images/random")
	public Call<BreedImage> randomBreedImage(@Path("breed") String breed);

}
