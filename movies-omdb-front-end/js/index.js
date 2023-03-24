const button = document.querySelector('.btn-search');
const input = document.querySelector('.search-input');
const movieDetails = document.getElementById('movie-details');

button.addEventListener('click', () => {
  const title = input.value;
  // Added the &includePoster=true so it also displays the movie poster
  fetch(`http://localhost:8080/api/movies/title?title=${title}&includePoster=true`)
    .then(response => response.json())
    .then(data => {
      // Update the HTML with the movie details
      movieDetails.innerHTML = `
        <h2>${data.Title}</h2>
        <button class="watchlist-button">+ watchlist</button>
        <img src="${data.Poster}" alt="${data.Title} poster">
        <p>${data.Plot}</p>
        <p>Directed by: ${data.Director}</p>
        <p>Starring: ${data.Actors}</p>
      `;
      
    })
    .catch(error => {
      // Handle any errors that occur
      console.error(error);
    });
});

// Welcome message if successfully logged in

// Get query parameters from URL
const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);

// Get welcome message from query parameter
const message = urlParams.get('message');

// Display welcome message if exists
if (message) {
    document.getElementById('welcome-message').textContent = message;
}