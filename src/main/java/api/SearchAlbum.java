package api;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;

import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;
import se.michaelthelin.spotify.model_objects.specification.ArtistSimplified;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchAlbumsRequest;

public class SearchAlbum {
	private static SpotifyAuthorization auth = new SpotifyAuthorization();
	private static String get_accessToken() {
		String accessToken = "";
		try {
			accessToken = auth.clientCredentials_Async();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return accessToken;
	}
	
//	private static String get_query() {
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Enter a query: ");
//		String q = sc.nextLine();
//		return q;
//	}
//	

	
//	private static final SearchAlbumsRequest searchAlbumsRequest = spotifyApi.searchAlbums(get_query()).build();
	
//	public static void searchAlbums_Sync() {
//		try {
//			final Paging<AlbumSimplified> albumPaging = searchAlbumsRequest.execute();
//			AlbumSimplified[] albums = albumPaging.getItems();
//			for (AlbumSimplified a : albums) {
//				System.out.println(a.getName() + ": " + a.getId());
//			}
//		}
//		catch (IOException | SpotifyWebApiException | ParseException e) {
//	      System.out.println("Error: " + e.getMessage());
//	    }
//	}
//	
	public static AlbumSimplified[] searchAlbums_Async(String query) throws InterruptedException, ExecutionException {
		final SpotifyApi spotifyApi = new SpotifyApi.Builder()
				.setAccessToken(get_accessToken())
				.build();
		SearchAlbumsRequest searchAlbumsRequest = spotifyApi.searchAlbums(query).build();

		AlbumSimplified[] albums = null;
		try {
		      final CompletableFuture<Paging<AlbumSimplified>> pagingFuture = searchAlbumsRequest.executeAsync();
		      final Paging<AlbumSimplified> albumPaging = pagingFuture.get();
		      albums = albumPaging.getItems();
//			for (AlbumSimplified a : albums) {
//				System.out.println(a.getName() + ": " + a.getId());
//			}
		} catch (CompletionException e) {
		      System.out.println("Error: " + e.getCause().getMessage());
	    } catch (CancellationException e) {
	      System.out.println("Async operation cancelled.");
	    }
		return albums;
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
//	    searchAlbums_Sync();
	    searchAlbums_Async("test");
	} 
}
