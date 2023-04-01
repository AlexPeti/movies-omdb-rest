// Get the search button and input field
const button = document.querySelector('.btn-search');
const input = document.querySelector('.search-input');
const movieDetails = document.getElementById('movie-details');

// Add a click event listener to the search button
button.addEventListener('click', () => {
  const title = input.value;

  // Added the &includePoster=true so it also displays the movie poster
  fetch(`http://localhost:8080/api/movies/title?title=${title}&includePoster=true`)
    .then(response => response.json())
    .then(data => {
      // Update the HTML with the movie details
      movieDetails.innerHTML = `
        <h2>${data.Title}</h2>
        <button class="watchlist-button" data-movie-title="${data.Title}">+ watchlist</button>
        <img src="${data.Poster}" alt="${data.Title} poster">
        <p>${data.Plot}</p>
        <p>Directed by: ${data.Director}</p>
        <p>Starring: ${data.Actors}</p>
      `;

      // Get the watchlist button and its associated movie title
      const watchlistButton = document.querySelector('.watchlist-button');
      const movieTitle = watchlistButton.dataset.movieTitle;

      // Add a click event listener to the watchlist button
      watchlistButton.addEventListener('click', () => {
        if (!loggedInUser) {
          console.error('User not logged in');
          return;
        }

        // Make a POST request to add the movie to the user's watchlist
        fetch(`http://localhost:8080/api/users/username/${loggedInUser}/movies/watchlist`, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            title: movieTitle
          }),
          credentials: 'include'
        })
        .then(response => {
          if (response.ok) {
            // Reload the page to show the updated watchlist
            location.reload();
          } else {
            console.error(`Failed to add movie to watchlist: ${response.statusText}`);
          }
        })
        .catch(error => {
          console.error('Error adding movie to watchlist:', error);
        });
      });
    })
    .catch(error => {
      console.error('Error getting movie data:', error);
    });
});





              
        




 






