package api;

import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.model_objects.specification.Album;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.TrackSimplified;
import se.michaelthelin.spotify.requests.data.albums.GetAlbumRequest;
import se.michaelthelin.spotify.requests.data.albums.GetAlbumsTracksRequest;

public class AlbumDetails {
	// same as SearchAlbums class
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
	
	private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
			.setAccessToken(get_accessToken())
			.build();

	
	// getting tracklist
	public static TrackSimplified[] getTracklist(String album_id) throws InterruptedException, ExecutionException {
		final GetAlbumsTracksRequest atr = spotifyApi.getAlbumsTracks(album_id).build();
		Paging<TrackSimplified> tracklist = null;
		try {
			final CompletableFuture<Paging<TrackSimplified>> pagingFuture = atr.executeAsync();
			tracklist = pagingFuture.get();
		} catch (CompletionException e) {
		      System.out.println("Error: " + e.getCause().getMessage());
	    } catch (CancellationException e) {
	      System.out.println("Async operation cancelled.");
	    }

		TrackSimplified[] tracks = tracklist.getItems();
//		for (TrackSimplified t : tracks) {
//			System.out.println(t.getName());
//		}
		return tracks;
	}
	
	public static Album getAlbum(String album_id) throws InterruptedException, ExecutionException {
		final GetAlbumRequest albumRequest = spotifyApi.getAlbum(album_id).build();
		Album album = null;
		try {
			final CompletableFuture<Album> albumFuture = albumRequest.executeAsync();
			album = albumFuture.get();
		} catch (CompletionException e) {
		      System.out.println("Error: " + e.getCause().getMessage());
	    } catch (CancellationException e) {
	      System.out.println("Async operation cancelled.");
	    }
		return album;
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// Example of The College Dropout
	    TrackSimplified[] tr = getTracklist("4Uv86qWpGTxf7fU7lG5X6F");
	}
	
}
