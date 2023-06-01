// Retrieves access token for Spotify API 
let httpRequest = new XMLHttpRequest(); 
let authEndpoint = 'https://accounts.spotify.com/api/token';
httpRequest.open('POST', authEndpoint);

httpRequest.setRequestHeader('Authorization', 'Basic ' + btoa('bc70b7456e4046c99f46c807d5969037:4d4e2da2e3b5483192e6d10dc78fa6da'));
httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
httpRequest.send('grant_type=client_credentials');

httpRequest.onreadystatechange = function() {
    if(httpRequest.readyState == 4) {
        if(httpRequest.status == 200) {
            accessAPI(httpRequest.responseText);
        }
        else {
            console.log(httpRequest.status);
        }
    }
}

// Calls API to get new album releases
function accessAPI(results) {
    let resultsJSON = JSON.parse(results);

    let endpoint = "https://api.spotify.com/v1/browse/new-releases?country=US&limit=4";
    httpRequest.open("GET", endpoint);
    httpRequest.setRequestHeader('Authorization', 'Bearer ' + resultsJSON.access_token);
    httpRequest.send();

    httpRequest.onreadystatechange = function() {
        if(httpRequest.readyState == 4) {
            if(httpRequest.status == 200) {
                displayNewReleases(httpRequest.responseText);
            }
            else {
                console.log(httpRequest.status);
            }
        }
    }
}

// Displays new album releases on page 
function displayNewReleases(results) {
    let resultsJSON = JSON.parse(results);

    let albums = document.querySelectorAll(".album-img");
    console.log(albums);
    for (let i=0; i < albums.length; ++i) {
        let albumName = resultsJSON.albums.items[i].name;
        let albumID = resultsJSON.albums.items[i].id;
        let albumImg = resultsJSON.albums.items[i].images[0].url;
        
        let newATag = document.createElement("a");
        newATag.setAttribute("href", "details.jsp?albumid=" + albumID);

        let newImg = document.createElement("img");
        newImg.setAttribute("src", albumImg);
        newImg.setAttribute("alt", albumName);

        newATag.appendChild(newImg);
        albums[0].appendChild(newATag);
    }
}